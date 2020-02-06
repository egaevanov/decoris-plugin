package org.decoris.process;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DBException;
import org.adempiere.model.ImportValidator;
import org.adempiere.process.ImportProcess;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MContactInterest;
import org.compiere.model.MLocation;
import org.compiere.model.MUser;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.X_I_BPartner;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class InsertIBPartner extends SvrProcess implements ImportProcess {
	
	Properties ctx = Env.getCtx();
	private int cnt = 0;
	int AD_Client_ID ;//= Env.getAD_Client_ID(ctx);
	String bp = "";	
 	
		
	
	private boolean parseXMLString(String message) throws  SAXException, ParserConfigurationException, IOException, AdempiereException {
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
			    	    X_I_BPartner businessPartner = new X_I_BPartner (Env.getCtx (), 0, null);
			    		
				    	NodeList details = records.item(i).getChildNodes();
				    	for(int j = 0 ; j < details.getLength();j++) {
				    		Node n = details.item(j);
				    		String column = n.getNodeName();
				    	    
				    		if (column.equals("CustomerName")){
				    			businessPartner.setName(n.getTextContent());		    			
				    		}
				    		else if (column.equals("Value")){
				    			businessPartner.setValue(n.getTextContent());
				    		}
				    		else if (column.equals("IsCustomer")){
				    			businessPartner.setIsCustomer(Boolean.getBoolean(n.getTextContent()));
				    		}
				    		else if (column.equals("Description")){
				    			businessPartner.setDescription(n.getTextContent());
				    		}
				    		else if (column.equals("C_BPartner_UU")){
				    			businessPartner.set_ValueOfColumn("C_Bpartner_UU", n.getTextContent());
				    		}
				    		else if (column.equals("BPLocationName")){ 
				    			businessPartner.setAddress1(n.getTextContent());
				    		}
				    		else if (column.equals("BPLocationName2")){ 
				    			businessPartner.setAddress2(n.getTextContent());
				    		}
				    		else if (column.equals("BPLocationCity")){ 
				    			businessPartner.setCity(n.getTextContent());
				    		}
				    		else if (column.equals("BPLocationCountry")){ 
				    			//businessPartner.set_ValueOfColumn("CountryName", n.getTextContent());
				    			businessPartner.set_ValueOfColumn("CountryName", "Indonesia");
				    			businessPartner.setC_Country_ID(209);
				    		}
				    		else if (column.equals("BPLocationPos")){ 
				    			businessPartner.setPostal(n.getTextContent());
				    		}
				    		else if (column.equals("NamaIbu")){ 
				    			businessPartner.set_ValueNoCheck("IbuKandung", n.getTextContent());
				    		}
				    		else if (column.equals("NoKTP")){
				    			businessPartner.set_ValueNoCheck("NoKTP", n.getTextContent());
				    		}
				    		else if (column.equals("JumlahPoin")){
				    			businessPartner.set_ValueOfColumn("PoinReward", BigDecimal.valueOf(Double.parseDouble(n.getTextContent())));		    			
				    		}
				    		else if (column.equals("KreditLimit")){
				    			businessPartner.set_ValueOfColumn("so_creditlimit", BigDecimal.valueOf(Double.parseDouble(n.getTextContent())));
				    		}
				    		else if (column.equals("IsSalesRep")){
				    			businessPartner.set_ValueOfColumn("IsSalesRep", Boolean.parseBoolean(n.getTextContent()));
				    		}
				    		else if (column.equals("IsVisible")){
				    			businessPartner.set_ValueOfColumn("BPartner_IsActive", Boolean.parseBoolean(n.getTextContent()));
				    		}
				    		else if (column.equals("IsEmployee")){
				    			businessPartner.set_ValueOfColumn("IsEmployee", Boolean.parseBoolean(n.getTextContent()));
				    		}
				    		else if (column.equals("NameContact")){
//				    			String sqlUser= " C_Bpartner_ID = ?";
//				    			List<MUser> user = new Query(businessPartner.getCtx(), MUser.Table_Name, sqlUser, businessPartner.get_TrxName())
//				    							.setClient_ID()
//				    							.setParameters(businessPartner.getC_BPartner_ID())
//				    							.setOnlyActiveRecords(true)
//				    							.list();
				    			businessPartner.setContactName(n.getTextContent());
				    		}
				    		else if (column.equals("Phone")){ 
				    			businessPartner.setPhone(n.getTextContent());
				    		}
				    		else if (column.equals("Phone2")){ 
				    			businessPartner.setPhone2(n.getTextContent());
				    		}
				    		else if (column.equals("TanggalLahir")){ 
				    			businessPartner.setBirthday(Timestamp.valueOf(n.getTextContent()));
				    		}
				    		else if (column.equals("Email")){ 
				    			businessPartner.setEMail(n.getTextContent());
				    		}
				    		else if (column.equals("Fax")){ 
				    			businessPartner.setFax(n.getTextContent());
				    		}
				    		else if (column.equals("Tempat_Lahir")){ 
				    			businessPartner.setContactDescription(n.getTextContent());
				    		}
				    		businessPartner.setRegionName("Indonesia");
				    		businessPartner.setAD_Org_ID(0);
				    		// Debug Function
				    		String text = n.getTextContent();
				    		System.out.println( "Node =  " + column + " Text = " + text);
				    	}
//				    	if (businessPartner.getC_BPartner_ID() == 0)
//				    		businessPartner.setC_BPartner_ID(getBPartnerDefault(businessPartner.getCtx(), "Standard", businessPartner.get_TrxName()).get_ID());
				    	if (!businessPartner.save()) return false; //saves each I_Bpartner for each Detail XML
				    	cnt++;
				    } 
//			    	int recordlength = records.getLength();
//			    	
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
				AD_Client_ID = ((BigDecimal)para[i].getParameter()).intValue();
		}
		
	}

	@Override
	protected String doIt() throws Exception {
		// TODO Auto-generated method stub
		String sqlBPartner = "SELECT BPartner FROM I_BPartner_Temp WHERE AD_Client_ID = ? AND insert_ibpartner = 'N' ";
		bp = DB.getSQLValueString(ctx.toString(), sqlBPartner, AD_Client_ID);
		parseXMLString(bp);
	    String sqlUpdateIBPartnerTemp = "UPDATE I_BPartner_Temp SET insert_ibpartner = 'Y' WHERE AD_Client_ID = ? ";
	    DB.executeUpdate(sqlUpdateIBPartnerTemp, AD_Client_ID,  get_TrxName());	
	    importBPartner();
	    			    
		return null;
	}
	
	
	private void importBPartner() {
		StringBuilder sql = null;
		int no = 0;
		String clientCheck = getWhereClause();

		//	****	Prepare	****

		//	Delete Old Imported
//		if (m_deleteOldImported)
//		{
//			sql = new StringBuilder ("DELETE I_BPartner ")
//					.append("WHERE I_IsImported='Y'").append(clientCheck);
//			no = DB.executeUpdateEx(sql.toString(), get_TrxName());
//			if (log.isLoggable(Level.FINE)) log.fine("Delete Old Impored =" + no);
//		}

		//	Set Client, Org, IsActive, Created/Updated
		sql = new StringBuilder ("UPDATE I_BPartner ")
				.append("SET AD_Client_ID = COALESCE (AD_Client_ID, ").append(AD_Client_ID).append("),")
						.append(" AD_Org_ID = COALESCE (AD_Org_ID, 0),")
						.append(" IsActive = COALESCE (IsActive, 'Y'),")
						.append(" Created = COALESCE (Created, SysDate),")
						.append(" CreatedBy = COALESCE (CreatedBy, 0),")
						.append(" Updated = COALESCE (Updated, SysDate),")
						.append(" UpdatedBy = COALESCE (UpdatedBy, 0),")
						.append(" I_ErrorMsg = ' ',")
						.append(" I_IsImported = 'N' ")
						.append("WHERE I_IsImported<>'Y' OR I_IsImported IS NULL");
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.FINE)) log.fine("Reset=" + no);

		ModelValidationEngine.get().fireImportValidate(this, null, null, ImportValidator.TIMING_BEFORE_VALIDATE);
		
		//	Set BP_Group
		sql = new StringBuilder ("UPDATE I_BPartner i ")
				.append("SET GroupValue=(SELECT MAX(Value) FROM C_BP_Group g WHERE g.IsDefault='Y'")
				.append(" AND g.AD_Client_ID=i.AD_Client_ID) ");
		sql.append("WHERE GroupValue IS NULL AND C_BP_Group_ID IS NULL")
				.append(" AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.FINE)) log.fine("Set Group Default=" + no);
		//
		sql = new StringBuilder ("UPDATE I_BPartner i ")
				.append("SET C_BP_Group_ID=(SELECT C_BP_Group_ID FROM C_BP_Group g")
				.append(" WHERE i.GroupValue=g.Value AND g.AD_Client_ID=i.AD_Client_ID) ")
				.append("WHERE C_BP_Group_ID IS NULL")
				.append(" AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.FINE)) log.fine("Set Group=" + no);
		//
		sql = new StringBuilder ("UPDATE I_BPartner ")
				.append("SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Group, ' ")
				.append("WHERE C_BP_Group_ID IS NULL")
				.append(" AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.CONFIG)) log.config("Invalid Group=" + no);

		//	Set Country
		/**
		sql = new StringBuffer ("UPDATE I_BPartner i "
			+ "SET CountryCode=(SELECT CountryCode FROM C_Country c WHERE c.IsDefault='Y'"
			+ " AND c.AD_Client_ID IN (0, i.AD_Client_ID) AND ROWNUM=1) "
			+ "WHERE CountryCode IS NULL AND C_Country_ID IS NULL"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		log.fine("Set Country Default=" + no);
		 **/
		//
		sql = new StringBuilder ("UPDATE I_BPartner i ")
				.append("SET C_Country_ID=(SELECT C_Country_ID FROM C_Country c")
				.append(" WHERE Coalesce(i.CountryCode, 'ID')=c.CountryCode AND c.AD_Client_ID IN (0, i.AD_Client_ID)) ")
				.append("WHERE C_Country_ID IS NULL")
				.append(" AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.FINE)) log.fine("Set Country=" + no);
		// by pass country
//		sql = new StringBuilder ("UPDATE I_BPartner ")
//				.append("SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Country, ' ")
//				.append("WHERE C_Country_ID IS NULL AND (City IS NOT NULL OR Address1 IS NOT NULL)")
//				.append(" AND I_IsImported<>'Y'").append(clientCheck);
//		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
//		if (log.isLoggable(Level.CONFIG)) log.config("Invalid Country=" + no);

		//	Set Region
		sql = new StringBuilder ("UPDATE I_BPartner i ")
				.append("Set RegionName=(SELECT MAX(Name) FROM C_Region r")
				.append(" WHERE r.IsDefault='Y' AND r.C_Country_ID=i.C_Country_ID")
				.append(" AND r.AD_Client_ID IN (0, i.AD_Client_ID)) " );
		sql.append("WHERE RegionName IS NULL AND C_Region_ID IS NULL")
				.append(" AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.FINE)) log.fine("Set Region Default=" + no);
		//
		sql = new StringBuilder ("UPDATE I_BPartner i ")
				.append("Set C_Region_ID=(SELECT C_Region_ID FROM C_Region r")
				.append(" WHERE r.Name=i.RegionName AND r.C_Country_ID=i.C_Country_ID")
				.append(" AND r.AD_Client_ID IN (0, i.AD_Client_ID)) ")
				.append("WHERE C_Region_ID IS NULL")
				.append(" AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.FINE)) log.fine("Set Region=" + no);
		//
		sql = new StringBuilder ("UPDATE I_BPartner i ")
				.append("SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Region, ' ")
				.append("WHERE C_Region_ID IS NULL ")
				.append(" AND EXISTS (SELECT * FROM C_Country c")
				.append(" WHERE c.C_Country_ID=i.C_Country_ID AND c.HasRegion='Y')")
				.append(" AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.CONFIG)) log.config("Invalid Region=" + no);

		//	Set Greeting
		sql = new StringBuilder ("UPDATE I_BPartner i ")
				.append("SET C_Greeting_ID=(SELECT C_Greeting_ID FROM C_Greeting g")
				.append(" WHERE i.BPContactGreeting=g.Name AND g.AD_Client_ID IN (0, i.AD_Client_ID)) ")
				.append("WHERE C_Greeting_ID IS NULL AND BPContactGreeting IS NOT NULL")
				.append(" AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.FINE)) log.fine("Set Greeting=" + no);
		//
		sql = new StringBuilder ("UPDATE I_BPartner i ")
				.append("SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Greeting, ' ")
				.append("WHERE C_Greeting_ID IS NULL AND BPContactGreeting IS NOT NULL")
				.append(" AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.CONFIG)) log.config("Invalid Greeting=" + no);

		//	Existing User ?
		sql = new StringBuilder ("UPDATE I_BPartner i ")
				.append("SET (C_BPartner_ID,AD_User_ID)=")
				.append("(SELECT C_BPartner_ID,AD_User_ID FROM AD_User u ")
				.append("WHERE i.EMail=u.EMail AND u.AD_Client_ID=i.AD_Client_ID) ")
				.append("WHERE i.EMail IS NOT NULL AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.FINE)) log.fine("Found EMail User=" + no);

		//	Existing BPartner ? Match Value
		sql = new StringBuilder ("UPDATE I_BPartner i ")
				.append("SET C_BPartner_ID=(SELECT C_BPartner_ID FROM C_BPartner p")
				.append(" WHERE i.Value=p.Value AND p.AD_Client_ID=i.AD_Client_ID) ")
				.append("WHERE C_BPartner_ID IS NULL AND Value IS NOT NULL")
				.append(" AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.FINE)) log.fine("Found BPartner=" + no);

		//	Existing Contact ? Match Name
		sql = new StringBuilder ("UPDATE I_BPartner i ")
				.append("SET AD_User_ID=(SELECT MAX(AD_User_ID) FROM AD_User c")
				//changed Syahnan@Kosta-Consulting
				// Existing Contact ? Match BP (1 BP 1 User/Contact)
				//.append(" WHERE i.ContactName=c.Name AND i.C_BPartner_ID=c.C_BPartner_ID AND c.AD_Client_ID=i.AD_Client_ID) ")
				.append(" WHERE i.C_BPartner_ID=c.C_BPartner_ID AND c.AD_Client_ID=i.AD_Client_ID AND c.IsActive = 'Y') ")
				.append("WHERE C_BPartner_ID IS NOT NULL AND AD_User_ID IS NULL AND ContactName IS NOT NULL")
				.append(" AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.FINE)) log.fine("Found Contact=" + no);

		//	Updated C_Bpartner_UU
		
		sql = new StringBuilder ("UPDATE I_BPartner i ")
				.append("SET C_BPartner_ID =")
				.append("(SELECT C_BPartner_ID FROM C_Bpartner  u ")
				.append("WHERE i.C_Bpartner_UU = u.C_Bpartner_UU AND u.AD_Client_ID=i.AD_Client_ID) ")
				.append("WHERE i.C_Bpartner_UU IS NOT NULL AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.FINE)) log.fine("Found C BpartnerUU = " + no);
		

		//	Existing Location ? Exact Match
		sql = new StringBuilder ("UPDATE I_BPartner i ")
				.append("SET C_BPartner_Location_ID=(SELECT Max( C_BPartner_Location_ID) ")
				.append(" FROM C_BPartner_Location bpl INNER JOIN C_BPartner bp ON (bp.C_BPartner_ID=bpl.C_BPartner_ID)")
				.append(" WHERE i.C_BPartner_ID=bpl.C_BPartner_ID AND bpl.IsActive='Y') ")				
				.append("WHERE C_BPartner_Location_ID IS NULL")
				//.append(" FROM C_BPartner_Location bpl INNER JOIN C_Location l ON (bpl.C_Location_ID=l.C_Location_ID)")
				//.append(" WHERE i.C_BPartner_ID=bpl.C_BPartner_ID AND bpl.AD_Client_ID=i.AD_Client_ID")
				//.append(" AND (i.Address1=l.Address1 OR (i.Address1 IS NULL AND l.Address1 IS NULL))")
				//.append(" AND (i.Address2=l.Address2 OR (i.Address2 IS NULL AND l.Address2 IS NULL))")
				//.append(" AND (i.City=l.City OR (i.City IS NULL AND l.City IS NULL))")
				//.append(" AND (i.Postal=l.Postal OR (i.Postal IS NULL AND l.Postal IS NULL))")
				//.append(" AND (i.Postal_Add=l.Postal_Add OR (l.Postal_Add IS NULL AND l.Postal_Add IS NULL))")
				//.append(" AND (i.C_Region_ID=l.C_Region_ID OR (l.C_Region_ID IS NULL AND i.C_Region_ID IS NULL))")
				//.append(" AND i.C_Country_ID=l.C_Country_ID) ")
				//.append("WHERE C_BPartner_ID IS NOT NULL AND C_BPartner_Location_ID IS NULL")
				.append(" AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.FINE)) log.fine("Found Location=" + no);

		//	Interest Area
		sql = new StringBuilder ("UPDATE I_BPartner i ") 
				.append("SET R_InterestArea_ID=(SELECT R_InterestArea_ID FROM R_InterestArea ia ")
				.append("WHERE i.InterestAreaName=ia.Name AND ia.AD_Client_ID=i.AD_Client_ID) ")
				.append("WHERE R_InterestArea_ID IS NULL AND InterestAreaName IS NOT NULL")
				.append(" AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.FINE)) log.fine("Set Interest Area=" + no);

		// Value is mandatory error
		sql = new StringBuilder ("UPDATE I_BPartner ")
				.append("SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Value is mandatory, ' ")
				.append("WHERE Value IS NULL ")
				.append(" AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.CONFIG)) log.config("Value is mandatory=" + no);
		
		ModelValidationEngine.get().fireImportValidate(this, null, null, ImportValidator.TIMING_AFTER_VALIDATE);

		try {
			commitEx();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		if (p_IsValidateOnly)
//		{
//			return "Validated";
//		}
		//	-------------------------------------------------------------------
		int noInsert = 0;
		int noUpdate = 0;

		//	Go through Records
		sql = new StringBuilder ("SELECT * FROM I_BPartner ")
				.append("WHERE I_IsImported<>'Y'").append(clientCheck);
		// gody: 20070113 - Order so the same values are consecutive.
		sql.append(" ORDER BY Value, I_BPartner_ID");
		PreparedStatement pstmt =  null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			rs = pstmt.executeQuery();

			// Remember Previous BP Value BP is only first one, others are contacts.
			// All contacts share BP location.
			// bp and bpl declarations before loop, we need them for data.
			String Old_BPValue = "" ; 
			MBPartner bp = null;
			MBPartnerLocation bpl = null;

			while (rs.next())
			{	
				// Remember Value - only first occurance of the value is BP
				String New_BPValue = rs.getString("Value") ;

				X_I_BPartner impBP = new X_I_BPartner (getCtx(), rs, get_TrxName());
				StringBuilder msglog = new StringBuilder("I_BPartner_ID=") .append(impBP.getI_BPartner_ID())
						.append(", C_BPartner_ID=").append(impBP.getC_BPartner_ID())
						.append(", C_BPartner_Location_ID=").append(impBP.getC_BPartner_Location_ID())
						.append(", AD_User_ID=").append(impBP.getAD_User_ID());
				if (log.isLoggable(Level.FINE)) log.fine(msglog.toString());


				if ( ! New_BPValue.equals(Old_BPValue)) {
					//	****	Create/Update BPartner	****
					bp = null;
					//,int asda = impBP.getC_BPartner_ID(); 
					if (impBP.getC_BPartner_ID() == 0)	//	Insert new BPartner
					{
						bp = new MBPartner(impBP);
						ModelValidationEngine.get().fireImportValidate(this, impBP, bp, ImportValidator.TIMING_AFTER_IMPORT);
						
						setTypeOfBPartner(impBP,bp);

						bp.set_ValueOfColumn("IbuKandung", impBP.get_ValueAsString("IbuKandung"));
						bp.set_ValueOfColumn("NoKTP", impBP.get_ValueAsString("NoKTP"));
						bp.set_ValueOfColumn("PoinReward", (BigDecimal)impBP.get_Value("PoinReward") );
						bp.setC_BPartner_UU(impBP.get_ValueAsString("C_BPartner_UU"));
						bp.setSO_CreditLimit((BigDecimal)impBP.get_Value("so_creditlimit"));
						
						if (bp.save())
						{
							impBP.setC_BPartner_ID(bp.getC_BPartner_ID());
							msglog = new StringBuilder("Insert BPartner - ").append(bp.getC_BPartner_ID());
							if (log.isLoggable(Level.FINEST)) log.finest(msglog.toString());
							noInsert++;
						}
						else
						{
							sql = new StringBuilder ("UPDATE I_BPartner i ")
									.append("SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
							.append("'Cannot Insert BPartner, ' ")
							.append("WHERE I_BPartner_ID=").append(impBP.getI_BPartner_ID());
							DB.executeUpdateEx(sql.toString(), get_TrxName());
							continue;
						}
					}
					else				//	Update existing BPartner
					{
						bp = new MBPartner(getCtx(), impBP.getC_BPartner_ID(), get_TrxName());
						//	if (impBP.getValue() != null)			//	not to overwite
						//		bp.setValue(impBP.getValue());
						
						bp.set_ValueOfColumn("IbuKandung", impBP.get_ValueAsString("IbuKandung"));
						bp.set_ValueOfColumn("NoKTP", impBP.get_ValueAsString("NoKTP"));
						bp.set_ValueOfColumn("PoinReward", (BigDecimal)impBP.get_Value("PoinReward"));
						bp.setSO_CreditLimit((BigDecimal)impBP.get_Value("so_creditlimit"));
						
						if (impBP.getName() != null)
						{
							bp.setName(impBP.getName());
							bp.setName2(impBP.getName2());
						}
						if (impBP.getDUNS() != null)
							bp.setDUNS(impBP.getDUNS());
						if (impBP.getTaxID() != null)
							bp.setTaxID(impBP.getTaxID());
						if (impBP.getNAICS() != null)
							bp.setNAICS(impBP.getNAICS());
						if (impBP.getDescription() != null)
							bp.setDescription(impBP.getDescription());
						if (impBP.getC_BP_Group_ID() != 0)
							bp.setC_BP_Group_ID(impBP.getC_BP_Group_ID());
						ModelValidationEngine.get().fireImportValidate(this, impBP, bp, ImportValidator.TIMING_AFTER_IMPORT);
						
						setTypeOfBPartner(impBP,bp);
						//
						if (bp.save())
						{
							msglog = new StringBuilder("Update BPartner - ").append(bp.getC_BPartner_ID());
							if (log.isLoggable(Level.FINEST)) log.finest(msglog.toString());
							noUpdate++;
						}
						else
						{
							sql = new StringBuilder ("UPDATE I_BPartner i ")
									.append("SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
							.append("'Cannot Update BPartner, ' ")
							.append("WHERE I_BPartner_ID=").append(impBP.getI_BPartner_ID());
							DB.executeUpdateEx(sql.toString(), get_TrxName());
							continue;
						}
					}

					//	****	Create/Update BPartner Location	****
					bpl = null;
					if (impBP.getC_BPartner_Location_ID() != 0)		//	Update Location
					{
						bpl = new MBPartnerLocation(getCtx(), impBP.getC_BPartner_Location_ID(), get_TrxName());
						MLocation location = new MLocation(getCtx(), bpl.getC_Location_ID(), get_TrxName());
						location.setC_Country_ID(impBP.getC_Country_ID());
						location.setC_Region_ID(impBP.getC_Region_ID());
						location.setCity(impBP.getCity());
						location.setAddress1(impBP.getAddress1());
						location.setAddress2(impBP.getAddress2());
						location.setPostal(impBP.getPostal());
						location.setPostal_Add(impBP.getPostal_Add());
						if (!location.save())
							log.warning("Location not updated");
						else
							bpl.setC_Location_ID(location.getC_Location_ID());
						if (impBP.getPhone() != null)
							bpl.setPhone(impBP.getPhone());
						if (impBP.getPhone2() != null)
							bpl.setPhone2(impBP.getPhone2());
						if (impBP.getFax() != null)
							bpl.setFax(impBP.getFax());
						ModelValidationEngine.get().fireImportValidate(this, impBP, bpl, ImportValidator.TIMING_AFTER_IMPORT);
						bpl.saveEx();
					}
					else 	//	New Location
						if (impBP.getC_Country_ID() != 0
								&& impBP.getAddress1() != null 
								&& impBP.getCity() != null)
						{
							MLocation location = new MLocation(getCtx(), impBP.getC_Country_ID(), 
									impBP.getC_Region_ID(), impBP.getCity(), get_TrxName());
							location.setAddress1(impBP.getAddress1());
							location.setAddress2(impBP.getAddress2());
							location.setPostal(impBP.getPostal());
							location.setPostal_Add(impBP.getPostal_Add());
							if (location.save()){
								msglog = new StringBuilder("Insert Location - ").append(location.getC_Location_ID());
								if (log.isLoggable(Level.FINEST)) log.finest(msglog.toString());
							}	
							else
							{
								rollback();
								noInsert--;
								sql = new StringBuilder ("UPDATE I_BPartner i ")
										.append("SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
								.append("'Cannot Insert Location, ' ")
								.append("WHERE I_BPartner_ID=").append(impBP.getI_BPartner_ID());
								DB.executeUpdateEx(sql.toString(), get_TrxName());
								continue;
							}
							//
							bpl = new MBPartnerLocation (bp);
							bpl.setC_Location_ID(location.getC_Location_ID());
							bpl.setPhone(impBP.getPhone());
							bpl.setPhone2(impBP.getPhone2());
							bpl.setFax(impBP.getFax());
							ModelValidationEngine.get().fireImportValidate(this, impBP, bpl, ImportValidator.TIMING_AFTER_IMPORT);
							if (bpl.save())
							{
								msglog = new StringBuilder("Insert BP Location - ").append(bpl.getC_BPartner_Location_ID());
								if (log.isLoggable(Level.FINEST)) log.finest(msglog.toString());
								impBP.setC_BPartner_Location_ID(bpl.getC_BPartner_Location_ID());
							}
							else
							{
								rollback();
								noInsert--;
								sql = new StringBuilder ("UPDATE I_BPartner i ")
										.append("SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
								.append("'Cannot Insert BPLocation, ' ")
								.append("WHERE I_BPartner_ID=").append(impBP.getI_BPartner_ID());
								DB.executeUpdateEx(sql.toString(), get_TrxName());
								continue;
							}
						}
				}

				Old_BPValue = New_BPValue ;

				//	****	Create/Update Contact	****
				MUser user = null;
				if (impBP.getAD_User_ID() != 0)
				{
					user = new MUser (getCtx(), impBP.getAD_User_ID(), get_TrxName());
					if (user.getC_BPartner_ID() == 0)
						user.setC_BPartner_ID(bp.getC_BPartner_ID());
					else if (user.getC_BPartner_ID() != bp.getC_BPartner_ID())
					{
						rollback();
						noInsert--;
						sql = new StringBuilder ("UPDATE I_BPartner i ")
								.append("SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
						.append("'BP of User <> BP, ' ")
						.append("WHERE I_BPartner_ID=").append(impBP.getI_BPartner_ID());
						DB.executeUpdateEx(sql.toString(), get_TrxName());
						continue;
					}
					if (impBP.getC_Greeting_ID() != 0)
						user.setC_Greeting_ID(impBP.getC_Greeting_ID());
					String name = impBP.getContactName();
					if (name == null || name.length() == 0)
						name = impBP.getEMail();
					user.setName(name);
					if (impBP.getTitle() != null)
						user.setTitle(impBP.getTitle());
					if (impBP.getContactDescription() != null)
						user.setDescription(impBP.getContactDescription());
					if (impBP.getComments() != null)
						user.setComments(impBP.getComments());
					if (impBP.getPhone() != null)
						user.setPhone(impBP.getPhone());
					if (impBP.getPhone2() != null)
						user.setPhone2(impBP.getPhone2());
					if (impBP.getFax() != null)
						user.setFax(impBP.getFax());
					if (impBP.getEMail() != null)
						user.setEMail(impBP.getEMail());
					if (impBP.getBirthday() != null)
						user.setBirthday(impBP.getBirthday());
					if (bpl != null)
						user.setC_BPartner_Location_ID(bpl.getC_BPartner_Location_ID());
					ModelValidationEngine.get().fireImportValidate(this, impBP, user, ImportValidator.TIMING_AFTER_IMPORT);
					if (user.save())
					{
						msglog = new StringBuilder("Update BP Contact - ").append(user.getAD_User_ID());
						if (log.isLoggable(Level.FINEST)) log.finest(msglog.toString());
					}
					else
					{
						rollback();
						noInsert--;
						sql = new StringBuilder ("UPDATE I_BPartner i ")
								.append("SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
						.append("'Cannot Update BP Contact, ' ")
						.append("WHERE I_BPartner_ID=").append(impBP.getI_BPartner_ID());
						DB.executeUpdateEx(sql.toString(), get_TrxName());
						continue;
					}
				}
				else 	//	New Contact
					if (impBP.getContactName() != null || impBP.getEMail() != null)
					{
						user = new MUser (bp);
						if (impBP.getC_Greeting_ID() != 0)
							user.setC_Greeting_ID(impBP.getC_Greeting_ID());
						String name = impBP.getContactName();
						if (name == null || name.length() == 0)
							name = impBP.getEMail();
						user.setName(name);
						user.setTitle(impBP.getTitle());
						user.setDescription(impBP.getContactDescription());
						user.setComments(impBP.getComments());
						user.setPhone(impBP.getPhone());
						user.setPhone2(impBP.getPhone2());
						user.setFax(impBP.getFax());
						user.setEMail(impBP.getEMail());
						user.setBirthday(impBP.getBirthday());
						if (bpl != null)
							user.setC_BPartner_Location_ID(bpl.getC_BPartner_Location_ID());
						ModelValidationEngine.get().fireImportValidate(this, impBP, user, ImportValidator.TIMING_AFTER_IMPORT);
						if (user.save())
						{
							msglog = new StringBuilder("Insert BP Contact - ").append(user.getAD_User_ID());
							if (log.isLoggable(Level.FINEST)) log.finest(msglog.toString());
							impBP.setAD_User_ID(user.getAD_User_ID());
						}
						else
						{
							rollback();
							noInsert--;
							sql = new StringBuilder ("UPDATE I_BPartner i ")
									.append("SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
							.append("'Cannot Insert BPContact, ' ")
							.append("WHERE I_BPartner_ID=").append(impBP.getI_BPartner_ID());
							DB.executeUpdateEx(sql.toString(), get_TrxName());
							continue;
						}
					}

				//	Interest Area
				if (impBP.getR_InterestArea_ID() != 0 && user != null)
				{
					MContactInterest ci = MContactInterest.get(getCtx(), 
							impBP.getR_InterestArea_ID(), user.getAD_User_ID(), 
							true, get_TrxName());
					ci.saveEx();		//	don't subscribe or re-activate
				}
				//
				impBP.setI_IsImported(true);
				impBP.setProcessed(true);
				impBP.setProcessing(false);
				impBP.saveEx();
				commitEx();
			}	//	for all I_Product
			DB.close(rs, pstmt);
		}
		catch (SQLException e)
		{
			rollback();
			//log.log(Level.SEVERE, "", e);
			throw new DBException(e, sql.toString());
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
			//	Set Error to indicator to not imported
			sql = new StringBuilder ("UPDATE I_BPartner ")
					.append("SET I_IsImported='N', Updated=SysDate ")
					.append("WHERE I_IsImported<>'Y'").append(clientCheck);
			no = DB.executeUpdateEx(sql.toString(), get_TrxName());
			addLog (0, null, new BigDecimal (no), "@Errors@");
			addLog (0, null, new BigDecimal (noInsert), "@C_BPartner_ID@: @Inserted@");
			addLog (0, null, new BigDecimal (noUpdate), "@C_BPartner_ID@: @Updated@");
		}			
		
	}
	
	//@Override
		public String getWhereClause()
		{
			StringBuilder msgreturn = new StringBuilder(" AND AD_Client_ID=").append(AD_Client_ID);
			return msgreturn.toString();
		}


		//@Override
		public String getImportTableName()
		{
			return X_I_BPartner.Table_Name;
		}
		
		/**
		 * Set type of Business Partner 
		 *
		 * @param X_I_BPartner impBP
		 * @param MBPartner bp
		 */
		private void setTypeOfBPartner(X_I_BPartner impBP, MBPartner bp){
			if (impBP.isVendor()){		
				bp.setIsVendor(true);
				bp.setIsCustomer(false); // It is put to false since by default in C_BPartner is true
			}else{
				bp.setIsVendor(false);
			}
			
			if (impBP.isEmployee()){ 		
				bp.setIsEmployee(true);
				bp.setIsCustomer(false); // It is put to false since by default in C_BPartner is true
			}else{
				bp.setIsEmployee(false);
			}
			
			// it has to be the last if, to subscribe the bp.setIsCustomer (false) of the other two
			if (impBP.isCustomer()){		
				bp.setIsCustomer(true);
			}
			if (impBP.get_ValueAsBoolean("IsSalesRep") == true){		
				bp.setIsSalesRep(true);
			}else{
				bp.setIsSalesRep(false);
			}

			if (impBP.get_ValueAsBoolean("BPartner_IsActive") == true){		
				bp.setIsActive(true);
			}else{
				bp.setIsActive(false);
			}
		}	// setTypeOfBPartner

}
