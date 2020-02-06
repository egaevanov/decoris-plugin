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
import org.compiere.model.MAttributeSetInstance;
import org.compiere.model.MBPartner;
import org.compiere.model.MLocator;
import org.compiere.model.MPriceList;
import org.compiere.model.MProduct;
import org.compiere.model.MTax;
import org.compiere.model.MUser;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.decoris.model.I_C_Decoris_PreSales;
import org.decoris.model.X_C_Decoris_PreSales;
import org.decoris.model.X_C_Decoris_PreSalesLine;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class syncPreSales  extends SvrProcess{

	private int p_AD_Client_ID = 0;
	//private int p_AD_Org_ID = 0;
	static Properties ctx = Env.getCtx();
	private int cnt = 0;
	
	
	@Override
	protected void prepare() {

		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
//			String name = para[i].getParameterName();
//			if (name.equals("AD_Client_ID"))
//				p_AD_Client_ID = ((BigDecimal)para[i].getParameter()).intValue();
//			else if (name.equals("AD_Org_ID"))
//				p_AD_Org_ID = ((BigDecimal)para[i].getParameter()).intValue();
		}
				
	}

	@Override
	protected String doIt() throws Exception {
		String msg = "";
		StringBuilder SQLFromOrdTmp = new StringBuilder();
		SQLFromOrdTmp.append("SELECT orders ");
		SQLFromOrdTmp.append("FROM I_Order_Temp ");
		SQLFromOrdTmp.append("WHERE AD_Client_ID = ? ");
		SQLFromOrdTmp.append("AND pos = 'E' ");
		SQLFromOrdTmp.append("AND insert_iorder = 'N' ");
		SQLFromOrdTmp.append("ORDER BY Created DESC ");
			
		String orders = DB.getSQLValueString(ctx.toString(), SQLFromOrdTmp.toString(), getAD_Client_ID());		 
		
		if(orders  == null){
			
			msg = "Tidak Ada Data";
			return msg;

		}
		msg = parseXMLString(orders);
		
		if(!msg.equals("OK")){
			return msg;
		}
		
	    String sqlUpdateIOrderTemp = "UPDATE I_Order_Temp SET insert_iorder = 'Y' WHERE AD_Client_ID = ? ";
	    DB.executeUpdate(sqlUpdateIOrderTemp, p_AD_Client_ID,  get_TrxName());		    
	
	   return msg;	
	
	} 
	
	
	private String parseXMLString(String message) throws  SAXException, ParserConfigurationException, IOException, AdempiereException {
		//uncomment for testing, together with above
//				message = "<?xml version=\"1.0\"?><entityDetail><type>I_Order</type><BPartnerValue>Joe Block</BPartnerValue><detail><DocTypeName>POS Order</DocTypeName><AD_Client_ID>11</AD_Client_ID><AD_Org_ID>11</AD_Org_ID><DocumentNo>40</DocumentNo><DateOrdered>2011-09-08 14:52:52.152</DateOrdered><ProductValue>Rake-Metal</ProductValue><QtyOrdered>1.0</QtyOrdered><PriceActual>12.0</PriceActual><TaxAmt>0.0</TaxAmt></detail></entityDetail>";
//				Adempiere.startupEnvironment(true);	
				if (message.equalsIgnoreCase("No queued message"))
					return "Tidak Ada Data";
				
				cnt=0;
				int count = 0;
				String success = "";
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				
				DocumentBuilder db = dbf.newDocumentBuilder();
			    Document doc = db.parse(new ByteArrayInputStream(message.getBytes()));
			    Element docEle = doc.getDocumentElement(); 
			    NodeList records = docEle.getElementsByTagName("detail");
			    int AD_Org = 0;
			    int M_Warehouse_ID = 0;
			    int C_DocType_ID = 0;
//			    int M_PriceList_ID = 0;
//			    int C_BPartner_ID = 0;
	    	    X_C_Decoris_PreSales preSales = new X_C_Decoris_PreSales (Env.getCtx (), 0, null);

			    	for(int i = 0 ; i < records.getLength();i++) {
			    	    X_C_Decoris_PreSalesLine line = new X_C_Decoris_PreSalesLine(getCtx(), 0, get_TrxName());
			    	    count++;

			    		
				    	NodeList details = records.item(i).getChildNodes();
				    	for(int j = 0 ; j < details.getLength();j++) {
				    		
				    		Node n = details.item(j);
				    		String column = n.getNodeName();
				    	    @SuppressWarnings("unused")
							String transactionType = "";
				    		// Presales HEADER
				    	    
				    	    if(count == 1){
					    	    if(column.equals("SourceOrder")){
					    			preSales.set_CustomColumn("flgsource", n.getTextContent());				    	    	
					    	    }

				    			else if (column.equals("DocTypeName"))
					    		{
				    				
				    				String decPOsdocType = "SELECT C_DocType_ID "
				    						+ " FROM C_DocType "
				    						+ " WHERE AD_Client_ID = ?"
				    						+ " AND DocBaseType = ? ";
				    				String docDec = "PRS";
				    				
				    				
				    				int decDoc_ID = DB.getSQLValueEx(null, decPOsdocType, new Object[]{getAD_Client_ID(),docDec});

				    				C_DocType_ID = decDoc_ID;
				    				
					    		}
				    			
					    		else if (column.equals("ClientValue")){
//					    			StringBuilder SQLClient = new StringBuilder();
//					    			SQLClient.append("SELECT AD_Client_ID ");
//					    			SQLClient.append("FROM AD_Client ");
//					    			SQLClient.append("WHERE Value = '" + n.getTextContent()+"'");
//	
//					    			AD_Client = DB.getSQLValueEx(null, SQLClient.toString()); 
					    			//p_AD_Client_ID = AD_Client;
					    			p_AD_Client_ID = getAD_Client_ID();
					    		}	
					    			
					    		else if (column.equals("OrgValue")){
					    			StringBuilder SQLOrg = new StringBuilder();
					    			SQLOrg.append("SELECT AD_Org_ID ");
					    			SQLOrg.append("FROM AD_Org ");
					    			SQLOrg.append("WHERE AD_Client_ID = ? ");
					    			SQLOrg.append("AND Value = '" + n.getTextContent()+"'");
					    			AD_Org = DB.getSQLValueEx(null, SQLOrg.toString(),getAD_Client_ID());

					    			//p_AD_Org_ID = AD_Org;
					    			//preSales.setClientOrg(AD_Client, AD_Org);
					    			
					    		}		    	    	
					    	    else if (column.equals(I_C_Decoris_PreSales.COLUMNNAME_DocumentNo)) {
					    	    	preSales.set_ValueOfColumn("OrderIdOri",n.getTextContent());
					    	    }
					    	    else if (column.equals(I_C_Decoris_PreSales.COLUMNNAME_DateOrdered)) {
					    	    	preSales.setDateOrdered(Timestamp.valueOf(n.getTextContent()));
					    	    }
					    		else if (column.equals("M_Locator_UU")){
					    			MLocator loc = getLocatorByUU(getCtx(), n.getTextContent(), get_TrxName());
					    			if (loc !=null)
					    				preSales.setM_Locator_ID(loc.getM_Locator_ID());
				    					line.setM_Locator_ID(loc.getM_Locator_ID());
					    			StringBuilder SQLWarehouse = new StringBuilder();
					    			SQLWarehouse.append("SELECT M_WareHouse_ID ");
					    			SQLWarehouse.append("FROM M_Locator ");
					    			SQLWarehouse.append("WHERE AD_Client_ID = ?");
					    			SQLWarehouse.append("AND M_Locator_ID = ? ");
	
					    			int M_Warehouse = DB.getSQLValueEx(null, SQLWarehouse.toString(), new Object[]{getAD_Client_ID(),loc.getM_Locator_ID()});
					    			
					    			M_Warehouse_ID = M_Warehouse;
					    		//	preSales.setM_Warehouse_ID(M_Warehouse);
					    			
					    		}
					    		else if (column.equals(I_C_Decoris_PreSales.COLUMNNAME_SalesRep_ID)){
					    			MUser salesRep = getSalesByUU(getCtx(), n.getTextContent(), get_TrxName());
					    			if(salesRep != null){
					    				preSales.setAD_User_ID(salesRep.getAD_User_ID());
					    				preSales.setSalesRep_ID(salesRep.getAD_User_ID());
					    			}
					    		}
					    		else if (column.equals("IsPickUp")){
					    			boolean isPickup = false;
					    			if(n.getTextContent().toUpperCase().equals("TRUE")){
					    				isPickup = true;
					    				System.out.println("node"+n.getNodeValue());
					    				System.out.println("text"+n.getTextContent());

					    			}
					    			preSales.setIsPickup(isPickup);
					    			
					    		}
					    		else if (column.equals("DeliveryAddress")){
					    			preSales.setAlamatLain(n.getTextContent());
					    			
					    		}
					    		
					    		else if (column.equals("BPartner_UU")){
					    			MBPartner bp = getBPartnerByUU(getCtx(), n.getTextContent(), get_TrxName());
					    			
					    			if(bp!=null){
					    				preSales.setC_BPartner_ID(bp.getC_BPartner_ID());
//					    				C_BPartner_ID = bp.getC_BPartner_ID();
					    			}
					    		}
					    		else if(column.equals("Description")){
					    			preSales.setDescription(n.getTextContent());
					    		}
					    		else if(column.equals("M_PriceList_UU")){
					    			MPriceList priceList = getPriceListByUU(getCtx(), n.getTextContent(), get_TrxName());
					    	    	if(priceList != null){
//					    	    		M_PriceList_ID = priceList.getM_PriceList_ID();
					    	    		preSales.setM_PriceList_ID(priceList.getM_PriceList_ID());
					    	    		
					    	    	}
					    	    }

					    	    
					    	    if(p_AD_Client_ID!=0 && AD_Org != 0 && C_DocType_ID!=0 && M_Warehouse_ID != 0 ){
					    	    	
					    	    	preSales.setClientOrg(p_AD_Client_ID, AD_Org);
					    	    	preSales.setC_DocType_ID(C_DocType_ID);
					    	    	preSales.setM_Warehouse_ID(M_Warehouse_ID);
					    	    	preSales.saveEx();
					    	    	
					    	    }
					    	    
				    	    }				    	    
				    	   
				    	    
				    	    if(column.equals("M_Product_UU")){
				    	    	MProduct prod = getProductByUU(getCtx(), n.getTextContent(), get_TrxName());
				    	    	if(prod != null){
				    	    		
				    	    		
				    	    		line.setM_Product_ID(prod.getM_Product_ID());	
				    	    	
				    	    	}
				    	    }else if (column.equals("M_Locator_UU")){
				    			MLocator loc = getLocatorByUU(getCtx(), n.getTextContent(), get_TrxName());
				    			if (loc !=null)
			    					line.setM_Locator_ID(loc.getM_Locator_ID());
				    			
				    		}
				    	    else if(column.equals("QtyOrdered")){
								
								line.setQtyEntered(BigDecimal.valueOf(Double.parseDouble(n.getTextContent())));
				    	    	
				    	    }else if(column.equals("C_Tax_ID")){
				    	    	MTax tax = getTaxByUU(getCtx(), n.getTextContent(), get_TrxName());
				    	    	if(tax != null){
				    	    		line.setC_Tax_ID(tax.getC_Tax_ID());
				    	    	}
				    	    }else if(column.equals("PriceActual")){								
								line.setPriceEntered(BigDecimal.valueOf(Double.parseDouble(n.getTextContent())));
								
				    	    }else if(column.equals("M_AttributeSetInstance_ID")){
				    	    	MAttributeSetInstance asi = getAttributeSetInsByUU(getCtx(), n.getTextContent(), get_TrxName());
				    	    	if(asi!= null){
				    	    		line.setM_AttributeSetInstance_ID(asi.getM_AttributeSetInstance_ID());
				    	    	}
				    	    }
				    	    
				    	    
				    	}
				    	
				    	
				    	preSales.setStatus(X_C_Decoris_PreSales.STATUS_InProgress);
				    	preSales.setC_Currency_ID(303);
				    	preSales.saveEx();
			    		if (!preSales.save()) 
			    			return "Tidak Dapat Menyimpan Data Presales";
				    	cnt++;

				    	line.setClientOrg(getAD_Client_ID(), AD_Org);
				    	line.setC_Decoris_PreSales_ID(preSales.getC_Decoris_PreSales_ID());
				    	line.saveEx();
				    					    	
//				    	if(line != null){
//				    	
//					    	BigDecimal DPP = Env.ZERO;
//		    	    		BigDecimal PPN = Env.ZERO;
//	
//		    	    		int M_Product_ID = line.getM_Product_ID();
//	
//		    	    		MProduct pr = new MProduct(ctx, M_Product_ID, null);
//	
//		    	    		StringBuilder SQLTaxRate = new StringBuilder();
//		    	    		SQLTaxRate.append("SELECT Rate ");
//		    	    		SQLTaxRate.append("FROM C_Tax ");
//		    	    		SQLTaxRate.append("WHERE AD_Client_ID = ? ");
//		    	    		SQLTaxRate.append("AND C_TaxCategory_ID = ? ");
//	
//		    	    		BigDecimal taxRate = DB.getSQLValueBDEx(null,
//		    	    				SQLTaxRate.toString(),
//		    	    				new Object[] { getAD_Client_ID(), pr.getC_TaxCategory_ID() });
//	
//	    	    			BigDecimal ttlHarga = line.getPriceEntered().multiply(line.getQtyEntered());
//
//		    	    		if (taxRate.compareTo(Env.ZERO) > 0) {
//	
//		    	    			BigDecimal divider = new BigDecimal("1.1");
//		    	    			DPP = ttlHarga.divide(divider, 2, RoundingMode.HALF_UP);
//		    	    			PPN = ttlHarga.subtract(DPP);
//
//
//		    	    		}
//		    	    		preSales.setTotalBelanja(preSales.getTotalBelanja().add(ttlHarga));
//		    	    		preSales.setTotalDiskon(preSales.getTotalDiskon().add(line.getDiskonLine()));
//		    	    		preSales.setdpp(preSales.getdpp().add(DPP));
//		    	    		preSales.setTaxAmt(preSales.getTaxAmt().add(PPN));
//		    	    		preSales.saveEx();
//		    	    		
//		    	    		preSales.settotal(preSales.getTotalBelanja().subtract(preSales.getTotalDiskon()));
//		    	    		preSales.saveEx();
//				    	}

				    } 
			    	
			    	if (preSales.getC_BPartner_ID() == 0)
			    		preSales.setC_BPartner_ID(getBPartnerDefault(getCtx(), "Standard", get_TrxName()).get_ID());
			    
			    	
			    	if (records.getLength()==cnt)
			    		success = "OK";
			    	{
			    		//to handle import of other data such as payments or returns
			    	}
					    
				return success; //ensure the import orders are correct count to XML details
				
			}
	
	
	private static MLocator getLocatorByUU(Properties ctx, String M_Locator_UU, String trxName){
		MLocator loc = new Query(ctx, MLocator.Table_Name, MLocator.COLUMNNAME_M_Locator_UU + "=? ", trxName)
						.setClient_ID()
						.setParameters(new Object[]{M_Locator_UU})
						.first();
		return loc;
	}
	
	private static MUser getSalesByUU(Properties ctx, String AD_User_UU, String trxName){
		MUser sales = new Query(ctx, MUser.Table_Name, MUser.COLUMNNAME_AD_User_UU + "=? ", trxName)
						.setClient_ID()
						.setParameters(new Object[]{AD_User_UU})
						.first();
		return sales;
	}
	
	private static MBPartner getBPartnerByUU(Properties ctx, String C_BPartner_UU, String trxName){
		MBPartner bpartner = new Query(ctx, MBPartner.Table_Name, MBPartner.COLUMNNAME_C_BPartner_UU + "=? ", trxName)
						.setClient_ID()
						.setParameters(new Object[]{C_BPartner_UU})
						.first();
		return bpartner;
	}
	
	private static MProduct getProductByUU(Properties ctx, String M_Product_UU, String trxName){
		MProduct prod= new Query(ctx, MProduct.Table_Name, MProduct.COLUMNNAME_M_Product_UU+ "=? ", trxName)
						.setClient_ID()
						.setParameters(new Object[]{M_Product_UU})
						.first();
		return prod;
	}
	
	private static MPriceList getPriceListByUU(Properties ctx, String M_PriceList_UU, String trxName){
		MPriceList priceList= new Query(ctx, MPriceList.Table_Name, MPriceList.COLUMNNAME_M_PriceList_UU+ "=? ", trxName)
						.setClient_ID()
						.setParameters(new Object[]{M_PriceList_UU})
						.first();
		return priceList;
	}
	
//	private static MDocType getDocTypeBySoSubType(Properties ctx, String soSubType, String trxName){
//		MDocType dt = new Query(ctx, MDocType.Table_Name, MDocType.COLUMNNAME_DocSubTypeSO + "=? ", trxName)
//						.setClient_ID()
//						.setParameters(new Object[]{soSubType})
//						.first();
//		return dt;
//	}
	
	
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
	
	private static MTax getTaxByUU(Properties ctx, String M_Tax_UU, String trxName){
		MTax tax = new Query(ctx, MTax.Table_Name, MTax.COLUMNNAME_C_Tax_UU + "=? ", trxName)
			.setClient_ID()
			.setParameters(new Object[]{M_Tax_UU})
			.first();
		return tax;
	}	

}
