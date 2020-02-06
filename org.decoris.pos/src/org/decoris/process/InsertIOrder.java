package org.decoris.process;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_I_Order;
import org.compiere.model.I_M_Product;
import org.compiere.model.MAttributeSetInstance;
import org.compiere.model.MBPartner;
import org.compiere.model.MBankAccount;
import org.compiere.model.MCharge;
import org.compiere.model.MDocType;
import org.compiere.model.MLocator;
import org.compiere.model.MOrder;
import org.compiere.model.MOrg;
import org.compiere.model.MPOS;
import org.compiere.model.MPayment;
import org.compiere.model.MProduct;
import org.compiere.model.MTax;
import org.compiere.model.MTaxCategory;
import org.compiere.model.MUser;
import org.compiere.model.MWarehouse;
import org.compiere.model.Query;
import org.compiere.model.X_C_POSTenderType;
import org.compiere.model.X_I_Order;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class InsertIOrder  extends SvrProcess {
	
	static Properties ctx = Env.getCtx();
	private static int p_AD_Client_ID; //= Env.getAD_Client_ID(ctx);
	@SuppressWarnings("unused")
	private static int p_AD_Org_ID;
	private ProcessInfo	m_pi;
	private int cnt = 0;
	@SuppressWarnings("unused")
	private String errorMessage = "";
	@SuppressWarnings("unused")
	private String orders = "";
	@SuppressWarnings("unused")
	private String pos= "";
	private MPOS mpos;
	
	private boolean parseXMLString(MPOS pos, String message) throws  SAXException, ParserConfigurationException, IOException, AdempiereException {
		//uncomment for testing, together with above
//				message = "<?xml version=\"1.0\"?><entityDetail><type>I_Order</type><BPartnerValue>Joe Block</BPartnerValue><detail><DocTypeName>POS Order</DocTypeName><AD_Client_ID>11</AD_Client_ID><AD_Org_ID>11</AD_Org_ID><DocumentNo>40</DocumentNo><DateOrdered>2011-09-08 14:52:52.152</DateOrdered><ProductValue>Rake-Metal</ProductValue><QtyOrdered>1.0</QtyOrdered><PriceActual>12.0</PriceActual><TaxAmt>0.0</TaxAmt></detail></entityDetail>";
//				Adempiere.startupEnvironment(true);	
				if (message.equalsIgnoreCase("No queued message"))
					return false;
				cnt=0;
				boolean success = false;
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				
				DocumentBuilder db = dbf.newDocumentBuilder();
			    Document doc = db.parse(new ByteArrayInputStream(message.getBytes()));
			    Element docEle = doc.getDocumentElement(); 
			    NodeList records = docEle.getElementsByTagName("detail");

			    	for(int i = 0 ; i < records.getLength();i++) {
						//check for detail = POS Order only. Other data will be handle in later brackets
//			    	    if (!records.item(i).getFirstChild().getTextContent().equals("POS Order"))
//			    	    	continue;
			    	    X_I_Order order = new X_I_Order (Env.getCtx (), 0, null);
			    	    	    	
			    	    order.set_ValueOfColumn("M_Warehouse_ID", pos.getM_Warehouse_ID());
			    		order.set_ValueOfColumn("M_PriceList_ID", pos.getM_PriceList_ID());
			    		order.set_ValueOfColumn("C_Currency_ID", pos.getM_PriceList().getC_Currency_ID());
			    		order.set_ValueOfColumn("IsSoTrx", "Y");
			    		//order.set_ValueOfColumn("C_Charge_ID", pos.get_Value("C_Charge_ID"));
			    		MTax taxx = null;
			    		
				    	NodeList details = records.item(i).getChildNodes();
				    	for(int j = 0 ; j < details.getLength();j++) {
				    		Node n = details.item(j);
				    		String column = n.getNodeName();
				    	    @SuppressWarnings("unused")
							String transactionType = "";
				    		//get Customer Name
				    		// ORDER HEADER
				    	    if (column.equals(I_I_Order.COLUMNNAME_DocTypeName))
				    		{
				    			MDocType dt = null;
				    			String docTypeName = n.getTextContent();
				    			if (docTypeName.equalsIgnoreCase("POS Order")){
				    				transactionType = "Order";
				    				dt = getDocTypeBySoSubType(order.getCtx(), MDocType.DOCSUBTYPESO_POSOrder, order.get_TrxName());	
				    			} else if (docTypeName.equalsIgnoreCase("Prepay Order")){
				    				transactionType = "Order";
				    				dt = getDocTypeBySoSubType(order.getCtx(), MDocType.DOCSUBTYPESO_PrepayOrder, order.get_TrxName());
				    			} else if (docTypeName.equalsIgnoreCase("Credit Order")){
				    				transactionType = "Order";
				    				dt = getDocTypeBySoSubType(order.getCtx(), MDocType.DOCSUBTYPESO_OnCreditOrder, order.get_TrxName());
				    			} else if (docTypeName.equalsIgnoreCase("Standard Order")){
				    				transactionType = "Order";
				    				dt = getDocTypeBySoSubType(order.getCtx(), MDocType.DOCSUBTYPESO_StandardOrder, order.get_TrxName());
				    			}else if (docTypeName.equalsIgnoreCase("AR Receipt")){
				    				transactionType = "Payment";
				    				MDocType[] dts = MDocType.getOfDocBaseType(order.getCtx(), MDocType.DOCBASETYPE_ARReceipt);
				    				dt = dts[0];
				    			}else if (docTypeName.equalsIgnoreCase("AP Payment")){
				    				transactionType = "Payment";
				    				MDocType[] dts = MDocType.getOfDocBaseType(order.getCtx(), MDocType.DOCBASETYPE_APPayment);
				    				dt = dts[0];
				    			}else if (docTypeName.equalsIgnoreCase("AP Prepayment")){
				    				transactionType = "Payment";
				    				MDocType[] dts = MDocType.getOfDocBaseType(order.getCtx(), MDocType.DOCBASETYPE_APPayment);
				    				dt = dts[0];
				    				order.set_CustomColumn("IsPrepayment", true);
				    			}else if (docTypeName.equalsIgnoreCase("AR Receipt")){ //add by dika
				    				transactionType = "Payment";
				    				MDocType[] dts = MDocType.getOfDocBaseType(order.getCtx(), MDocType.DOCBASETYPE_ARReceipt);
				    				dt = dts[0];
				    				order.set_CustomColumn("IsPresales", true);
				    			}
				    			
				    			if (dt !=null)
				    				order.setC_DocType_ID(dt.get_ID());
				    			else
				    				throw new AdempiereException("DocumentType Tidak ditemukan!!");
				    		}
			    			
				    		else if (column.equals("BPartner_UU")){
				    			MBPartner bpartner = getBPartnerByUU(order.getCtx(), n.getTextContent(), order.get_TrxName());
				    			if (bpartner !=null)
				    				order.setC_BPartner_ID(bpartner.get_ID());
				    		}	
				    			
				    		else if (column.equals(I_I_Order.COLUMNNAME_BPartnerValue)){
				    			order.setBPartnerValue(n.getTextContent());
				    		}		    	    	
				    	    else if (column.equals(I_I_Order.COLUMNNAME_DateOrdered)) {
				    	    	order.setDateOrdered(Timestamp.valueOf(n.getTextContent()));
				    	    	order.setDateAcct(Timestamp.valueOf(n.getTextContent()));
				    	    }
				    	    else if (column.equals("MovementDate")) {
				    	    	order.set_ValueOfColumn("MovementDate", Timestamp.valueOf(n.getTextContent()));
				    	    }	
				    		else if (column.equals(I_I_Order.COLUMNNAME_AD_Client_ID))
				    			order.set_ValueOfColumn(I_I_Order.COLUMNNAME_AD_Client_ID, Integer.parseInt(n.getTextContent()));
				    		
				    		else if (column.equals("ClientValue"))
				    			order.set_ValueOfColumn("ClientValue", n.getTextContent() );
				    		else if (column.equals("OrgValue"))
				    			order.setAD_Org_ID( getOrgID(order.getCtx(), n.getTextContent().toString(), order.get_TrxName() ) );
				    		
				    		else if (column.equals("M_Locator_UU")){
				    			MLocator loc = getLocatorByUU(order.getCtx(), n.getTextContent(), order.get_TrxName());
				    			if (loc !=null)
				    				order.set_CustomColumn("M_Locator_ID", loc.get_ID());
				    		}
				    	    //add dika
				    		else if (column.equals("multilocator")){
				    			if (n.getTextContent().equalsIgnoreCase("true")){
				    				order.set_CustomColumn("multilocator", "Y");
				    				//.set_ValueOfColumn("DecorisMultiLocator", "Y");
				    				//order.set_CustomColumn("M_Locator_ID", null);
				    			}
				    			else
				    				order.set_ValueOfColumn("multilocator", "N");
				    		}
				    		
				    		else if (column.equals(I_I_Order.COLUMNNAME_DocumentNo))
				    			order.setDocumentNo((n.getTextContent()));
				    		
				    		else if (column.equals(I_I_Order.COLUMNNAME_SalesRep_ID)){
				    			MBPartner bpartner = getBPartnerByUU(order.getCtx(), n.getTextContent(), order.get_TrxName());
				    			if (bpartner !=null){
					    			MUser salesreps[] = MUser.getOfBPartner(order.getCtx(), bpartner.get_ID(), order.get_TrxName());
					    			if (salesreps.length !=0)
					    			{
					    				order.setSalesRep_ID(salesreps[0].get_ID());	
					    			}
				    			}
				    		}
				    		else if (column.equals("Reference_ID")){
				    			MBPartner bpartner = getBPartnerByUU(order.getCtx(), n.getTextContent(), order.get_TrxName());
				    			if (bpartner != null)
				    				order.set_ValueOfColumn("Referensi_ID", bpartner.get_ID());
				    		}
				    		else if (column.equals("Warehouse_To")){
				    			MWarehouse wh = getWarehouseByUU(order.getCtx(), n.getTextContent(), order.get_TrxName());
				    			if (wh !=null)
				    				order.set_ValueOfColumn("M_WarehouseTo_ID", wh.get_ID());
				    		}
				    		else if (column.equals("IsPickUp")){ 
				    			if (n.getTextContent().equalsIgnoreCase("true"))
				    				order.set_ValueOfColumn("DeliveryViaRule", MOrder.DELIVERYVIARULE_Pickup);
				    			else
				    				order.set_ValueOfColumn("DeliveryViaRule", MOrder.DELIVERYVIARULE_Delivery);
				    		}
				    		else if (column.equals("DeliveryAddress")){ 
				    			order.setDescription(n.getTextContent());
				    		}
				    		else if (column.equals("IsPresales")){  //add by dika
				    			if (n.getTextContent().equalsIgnoreCase("true"))
				    				order.set_ValueOfColumn("IsPresales", "Y");
				    			else
				    				order.set_ValueOfColumn("IsPresales", "N");
				    		}
				    		
				    		// ORDER LINE
				    		else if (column.equals(I_I_Order.COLUMNNAME_PriceActual)){
				    			if (pos.getM_PriceList().isTaxIncluded()){
				    				if (taxx !=null){
				    					BigDecimal priceActual = BigDecimal.valueOf(Double.parseDouble(n.getTextContent())).setScale(2, BigDecimal.ROUND_DOWN);
				    					BigDecimal taxAmt = taxx.calculateTax(BigDecimal.valueOf(Double.parseDouble(n.getTextContent())), false, 2, BigDecimal.ROUND_DOWN);
				    					order.setPriceActual(priceActual.add(taxAmt).setScale(0, BigDecimal.ROUND_HALF_UP));
				    				}else{
				    					order.setPriceActual(BigDecimal.valueOf(Double.parseDouble(n.getTextContent())));
				    				}
				    			}else{
				    				order.setPriceActual(BigDecimal.valueOf(Double.parseDouble(n.getTextContent())));
				    			}
				    		}
				    		else if (column.equals(I_M_Product.COLUMNNAME_M_Product_UU))
				    		{
				    			MProduct[] products = MProduct.get(order.getCtx(), I_M_Product.COLUMNNAME_M_Product_UU + " =  '" + n.getTextContent() + "'", order.get_TrxName());
				    			for (MProduct product : products){
				    				order.setM_Product_ID(product.get_ID());
				    				order.setC_UOM_ID(product.getC_UOM_ID());
				    			}
				    			if ( products.length <= 0){ // Setting default Charge Diskon
//				    				String sqlDiskon = " Value = ? And AD_Client_ID = ? ";
//				    				MProduct prod = new Query(order.getCtx(), MProduct.Table_Name, sqlDiskon, order.get_TrxName())
//				    							.setParameters("Diskon", order.getAD_Client_ID())
//				    							.setOnlyActiveRecords(true)
//				    							.first();
//				    				order.setM_Product_ID(prod.getM_Product_ID());
//				    				order.setC_UOM_ID(prod.getC_UOM_ID());
				    				order.setC_Charge_ID(pos.get_ValueAsInt("C_Charge_ID"));
				    				order.setC_UOM_ID(100); // each uom
				    				
				    			}
				    		}
				    					    		
				    		else if (column.equals(I_I_Order.COLUMNNAME_QtyOrdered))
				    			order.setQtyOrdered(BigDecimal.valueOf(Double.parseDouble(n.getTextContent())));

				    		else if (column.equals("C_Tax_ID")){
				    			MTaxCategory taxCat = getTaxCategoryByUU(order.getCtx(), n.getTextContent(), order.get_TrxName());
				    			if (taxCat != null){
					    			taxx = getTaxByCategory(order.getCtx(), taxCat.get_ID(), order.get_TrxName(), taxCat.getName());
					    			if ( taxx != null)
					    				order.setC_Tax_ID(taxx.get_ID());
					    			else 
					    				errorMessage += ", Tax Rate Not Exists. ";
				    			}
				    			else 
				    				errorMessage += ", Tax Category Not Exists. ";
				    		}
				    		
				    		else if (column.equals("M_AttributeSetInstance_ID")) {
				    			order.set_ValueNoCheck("M_AttributeSetInstance_UU", (n.getTextContent()));		 
				    			
				    			MAttributeSetInstance att = getAttributeSetInsByUU(order.getCtx(), n.getTextContent(), order.get_TrxName());
				    			if(att != null){ 
				    				order.set_ValueNoCheck("M_AttributeSetInstance_ID", att.get_ID());
				    			}
				    		}
				    	
				    		// MIXED POS PAYMENTS
				    		else if (column.equals("PaymentRule")){
				    			String paymentTenderType="";
				    			//Penjualan & Pembayaran Pelanggan
				    			if (n.getTextContent().equalsIgnoreCase("cash") || n.getTextContent().equalsIgnoreCase("cashrefund")){
				    				paymentTenderType=MPayment.TENDERTYPE_Cash;
				    				order.set_ValueOfColumn("C_BankAccount_ID", pos.getC_BankAccount_ID());
				    			}
				    			else if (n.getTextContent().equalsIgnoreCase("bank"))
				    				paymentTenderType=MPayment.TENDERTYPE_Cash;
				    			else if (n.getTextContent().equalsIgnoreCase("card") || n.getTextContent().equalsIgnoreCase("creditcard"))
				    				paymentTenderType=MPayment.TENDERTYPE_CreditCard;
				    			else if (n.getTextContent().equalsIgnoreCase("debitcard"))
				    				paymentTenderType=MPayment.TENDERTYPE_DirectDeposit;
				    			else if (n.getTextContent().equalsIgnoreCase("leasing"))
				    				paymentTenderType=MPayment.TENDERTYPE_Lease;
				    			else if (n.getTextContent().equalsIgnoreCase("magcard") || n.getTextContent().equalsIgnoreCase("magcardrefund"))
				    				paymentTenderType=MPayment.TENDERTYPE_Check;
				    			//Pembayaran
				    			else if (n.getTextContent().equalsIgnoreCase("cashin") || n.getTextContent().equalsIgnoreCase("cashout") || n.getTextContent().equalsIgnoreCase("cashadvancedout")){
				    				paymentTenderType=MPayment.TENDERTYPE_Cash;
				    				order.set_ValueOfColumn("C_BankAccount_ID", pos.getC_BankAccount_ID());
				    			}
				    			
				    			X_C_POSTenderType posTenderType = new Query(order.getCtx(), X_C_POSTenderType.Table_Name, X_C_POSTenderType.COLUMNNAME_TenderType + "=? ", order.get_TrxName())
				    											.setParameters(new Object[]{paymentTenderType})
				    											.setOnlyActiveRecords(true)
				    											.first();
				    			if (posTenderType != null)
				    				order.set_ValueOfColumn("C_PosTenderType_ID", posTenderType.get_ID());
				    			if (paymentTenderType != null)	
				    				order.set_ValueOfColumn("paymentrulepo", paymentTenderType);
				    		}
				    			
				    		else if (column.equals("PayAmt")){
				    			//order.set_ValueOfColumn("PayAmt", BigDecimal.valueOf(Double.parseDouble(n.getTextContent())).abs());
				    			order.set_ValueOfColumn("PayAmt", BigDecimal.valueOf(Double.parseDouble(n.getTextContent())));
				    		}
				    		else if (column.equals("LeasingName")){
				    			order.set_ValueOfColumn("LeaseProvider", n.getTextContent() );
//				    			if (n.getTextContent().equalsIgnoreCase("Spektra"))
//				    				order.set_ValueOfColumn("LeaseProvider", n.getTextContent() );
//				    			else
//				    				order.set_ValueOfColumn("LeaseProvider", "Lain-lain");
				    			
				    			StringBuilder SQLCekBankLeasing = new StringBuilder();
				    			SQLCekBankLeasing.append("SELECT C_BankAccount_ID");
				    			SQLCekBankLeasing.append(" FROM c_bankaccount_leaseprovider");
				    			SQLCekBankLeasing.append(" WHERE AD_Client_ID = "+ Env.getAD_Client_ID(getCtx()));
				    			SQLCekBankLeasing.append(" AND leaseprovider = '" + n.getTextContent() + "'");
				    			SQLCekBankLeasing.append(" AND isactive = 'Y'");

				    			Integer bankAccount = DB.getSQLValueEx(null, SQLCekBankLeasing.toString());
				    			
				    			if(bankAccount == null){
				    				bankAccount = 0;
				    			}
				    			
				    				
				    			if (n.getTextContent() != null || n.getTextContent() != "" || !n.getTextContent().isEmpty()){
				    				if(bankAccount > 0){
				    					order.set_ValueOfColumn("C_BankAccount_ID", bankAccount);
				    				}else{
				    					order.set_ValueOfColumn("C_BankAccount_ID", pos.getC_BankAccount_ID());				    					
				    				}
				    			}
				    		}
				    		else if (column.equals("Tendered")){
				    			order.set_ValueOfColumn("Tendered", BigDecimal.valueOf(Double.parseDouble(n.getTextContent())));
				    		}
				    		else if (column.equals("C_BankAccount_ID")){ 
				    			MBankAccount bankAccount = getBankAccountByUU(order.getCtx(), n.getTextContent(), order.get_TrxName());
				    			if(bankAccount != null)
				    				order.set_ValueOfColumn("C_BankAccount_ID", bankAccount.get_ID());
				    		}
				    		else if (column.equals("CardName")){
				    			order.set_ValueOfColumn("CardName", n.getTextContent());
				    		}
				    		else if (column.equals("C_BPartner_ID")){ 
				    			MBPartner bpartner = getBPartnerByUU(order.getCtx(), n.getTextContent(), order.get_TrxName());
				    			if (bpartner.get_ID() != 0)
				    				order.set_ValueOfColumn("C_BPartner_ID", bpartner.get_ID());
				    		}
				    		else if (column.equals("C_Charge_ID")){ 
				    			MCharge charge = getChargeByUU(order.getCtx(), n.getTextContent(), order.get_TrxName());
				    			if(charge != null)
				    				order.set_ValueOfColumn("C_Charge_ID", charge.get_ID());
				    		}
				    		else if (column.equals("activecash")){ 
				    			order.set_ValueOfColumn("C_ActiveCash_UU", n.getTextContent());
				    		}
				    		else if (column.equals("Created_ID")){
				    			if (!"0".equals(n.getTextContent())){
				    				MBPartner bp = getBPartnerByUU(order.getCtx(), n.getTextContent(), order.get_TrxName());
					    				if(bp != null){
					    					order.set_ValueOfColumn("CreatedByPOS_UU", n.getTextContent());
						    				order.set_ValueNoCheck("CreatedByPOS_ID", bp.getC_BPartner_ID());
					    				}
				    				}
					    		}

				    		else if (column.equals("Description")){ 
				    			order.set_ValueOfColumn("Description", n.getTextContent());
				    		}
				    		// Debug Function
//				    		String text = n.getTextContent();
//				    		System.out.println( "Node =  " + column + " Text = " + text);
				    	}
				    	if (order.getC_BPartner_ID() == 0 && order.getBPartnerValue() == null)
				    		order.setC_BPartner_ID(getBPartnerDefault(order.getCtx(), "Standard", order.get_TrxName()).get_ID());
				    	if (!order.save()) return false; //saves each I_Order line for each Detail XML
				    	cnt++;
				    } 
//			    	int recordlength = records.getLength();
			    	
			    	if (records.getLength()==cnt)
			    		success = true;
			    	{
			    		//to handle import of other data such as payments or returns
			    	}
					    
				return (success); //ensure the import orders are correct count to XML details
				
			}

	@Override
	protected void prepare() {
		// TODO Auto-generated method stub
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (name.equals("AD_Client_ID"))
				p_AD_Client_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("AD_Org_ID"))
				p_AD_Org_ID = ((BigDecimal)para[i].getParameter()).intValue();
		}
		
	}

	@Override
	protected String doIt() throws Exception {
		// TODO Auto-generated method stub
		
//		AD_Client_ID = getAD_Client_ID();
				
		String sqlGetPosName = "select pos from i_order_temp where ad_client_id = ? order by created desc";	 
		String posName = DB.getSQLValueString(ctx.toString(), sqlGetPosName, p_AD_Client_ID).toString();
//		System.out.println("posName" +posName);
		String sqlGetCposId = "select c_pos_id from c_pos where ad_client_id = ? and name = ?";	     
//	    Integer cposId = (Integer) DB.getSQLValue(ctx.toString(), sqlGetCposId, new Object[]{p_AD_Client_ID,posName});
		Integer cposId = DB.getSQLValue(ctx.toString(), sqlGetCposId, new Object[]{p_AD_Client_ID,posName});
	    
//		System.out.println("cposId" + cposId.toString());
		    
	    mpos  = new MPOS(getCtx(), cposId, get_TrxName());
	
		String sqlGetOrders = "select orders from i_order_temp where ad_client_id = ? and insert_iorder = 'N' ";	 
		String orders = DB.getSQLValueString(ctx.toString(), sqlGetOrders, p_AD_Client_ID);		
	   
		
		parseXMLString(mpos, orders);
		
	    String sqlUpdateIOrderTemp = "UPDATE I_Order_Temp SET insert_iorder = 'Y' WHERE AD_Client_ID = ? ";
	    DB.executeUpdate(sqlUpdateIOrderTemp, p_AD_Client_ID,  get_TrxName());		    
	
	   return null;	
	}	
	
	/**
	 *  Get AD_User_ID
	 *  @return AD_User_ID of Process owner
	 */
	protected int getAD_Client_ID()
	{
		if (m_pi.getAD_Client_ID() == null)
		{
			getAD_User_ID();	//	sets also Client
			if (m_pi.getAD_Client_ID() == null)
				return 0;
		}
		return m_pi.getAD_Client_ID().intValue();
	}	//	getAD_Client_ID
	
	
	private static int getOrgID(Properties ctx , String orgValue, String trxName){
		int AD_Org_ID = 0;
		MOrg org = new Query(ctx, MOrg.Table_Name, " Value = ? ", trxName)
						.setParameters(orgValue)
						.setOnlyActiveRecords(true)
						.setClient_ID()
						.first();
		if (org != null)
			AD_Org_ID = org.getAD_Org_ID();
		return AD_Org_ID;
	}
	
	private static MLocator getLocatorByUU(Properties ctx, String M_Locator_UU, String trxName){
		MLocator loc = new Query(ctx, MLocator.Table_Name, MLocator.COLUMNNAME_M_Locator_UU + "=? ", trxName)
						.setClient_ID()
						.setParameters(new Object[]{M_Locator_UU})
						.first();
		return loc;
	}
	
	private static MBankAccount getBankAccountByUU(Properties ctx, String C_BankAccount_UU, String trxName){
		MBankAccount bank = new Query(ctx, MBankAccount.Table_Name, MBankAccount.COLUMNNAME_C_BankAccount_UU + "=? ", trxName)
						.setClient_ID()
						.setParameters(new Object[]{C_BankAccount_UU})
						.first();
		return bank;
	}
	
	private static MBPartner getBPartnerByUU(Properties ctx, String C_BPartner_UU, String trxName){
		MBPartner bpartner = new Query(ctx, MBPartner.Table_Name, MBPartner.COLUMNNAME_C_BPartner_UU + "=? ", trxName)
						.setClient_ID()
						.setParameters(new Object[]{C_BPartner_UU})
						.first();
		return bpartner;
	}
	
	private static MCharge getChargeByUU(Properties ctx, String C_Charge_UU, String trxName){
		MCharge charge = new Query(ctx, MCharge.Table_Name, MCharge.COLUMNNAME_C_Charge_UU + "=? ", trxName)
						.setClient_ID()
						.setParameters(new Object[]{C_Charge_UU})
						.first();
		return charge;
	}
	
	private static MWarehouse getWarehouseByUU(Properties ctx, String M_Warehouse_UU, String trxName){
		MWarehouse wh = new Query(ctx, MWarehouse.Table_Name, MWarehouse.COLUMNNAME_M_Warehouse_UU + "=? ", trxName)
						.setClient_ID()
						.setParameters(new Object[]{M_Warehouse_UU})
						.first();
		return wh;
	}
	
	private static MDocType getDocTypeBySoSubType(Properties ctx, String soSubType, String trxName){
		MDocType dt = new Query(ctx, MDocType.Table_Name, MDocType.COLUMNNAME_DocSubTypeSO + "=? ", trxName)
						.setClient_ID()
						.setParameters(new Object[]{soSubType})
						.first();
		return dt;
	}
	
	private static MTax getTaxByCategory(Properties ctx, int C_TaxCategory_ID, String trxName, String nameTaxCat){
		MTax tax = new Query(ctx, MTax.Table_Name, MTax.COLUMNNAME_C_TaxCategory_ID + "=? AND Name = ? ", trxName)
						.setClient_ID()
						.setParameters(new Object[]{C_TaxCategory_ID, nameTaxCat})
						.first();
		return tax;
	}
	
	private static MTaxCategory getTaxCategoryByUU(Properties ctx, String C_TaxCategory_UU, String trxName){
		MTaxCategory taxCat = new Query(ctx, MTaxCategory.Table_Name, MTaxCategory.COLUMNNAME_C_TaxCategory_UU + "=? ", trxName)
						.setClient_ID()
						.setParameters(new Object[]{C_TaxCategory_UU})
						.first();
		return taxCat;
	}
	
	private static MBPartner getBPartnerDefault(Properties ctx, String valueName, String trxName){
		MBPartner bpartner = new Query(ctx, MBPartner.Table_Name, MBPartner.COLUMNNAME_Name + "=? ", trxName)
						.setClient_ID()
						.setParameters(new Object[]{valueName})
						.first();
		return bpartner;
	}
	
	private static MAttributeSetInstance getAttributeSetInsByUU(Properties ctx, String M_AttributeSetInstance_UU, String trxName){
		MAttributeSetInstance asi = new Query(ctx, MAttributeSetInstance.Table_Name, MAttributeSetInstance.COLUMNNAME_M_AttributeSetInstance_UU + "=? ", trxName)
								.setClient_ID()
								.setParameters(new Object[]{M_AttributeSetInstance_UU})
								.first();
		return asi;
	}
	
//	private static MBankAccount getBankAccountByLeaseProvider(Properties ctx, String C_BankAccount_UU, String trxName){
//		MBankAccount bank = new Query(ctx, MBankAccount.Table_Name, "leaseprovider =? ", trxName)
//						.setClient_ID()
//						.setParameters(new Object[]{"Spektra"})
//						.first();
//		return bank;
//	}

}
