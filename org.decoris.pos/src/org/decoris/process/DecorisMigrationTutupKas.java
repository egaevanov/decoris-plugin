package org.decoris.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.decoris.model.MDecorisPOS;
import org.decoris.model.X_C_DecorisPOS;
import org.decoris.model.X_C_Decoris_CloseCash;

/**
 * 
 * @author Tegar N
 *
 */

public class DecorisMigrationTutupKas extends SvrProcess{

	Timestamp p_DateTrx  = null;
	Properties ctx = Env.getCtx();
//	private int AD_User_ID = Env.getAD_User_ID(ctx);
	int AD_Client_ID = Env.getAD_Client_ID(ctx);
	int CreatedByPOS_ID = 0;
	String clientValue = "";
	String posName = "";
	String kasirName = "";
	String DocumentNo = "";
	Integer seqTutupKas = 0;
	private int C_DecorisPOS_ID =0;
	Timestamp closeCashDate  = null;

	
	
	X_C_DecorisPOS decPOS = null;
	X_C_Decoris_CloseCash dec_closeCash = null;
	boolean ok = true;

	
	@Override
	protected void prepare() {
		
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null);
//				else if (name.equals("DateOrdered"))
//					p_DateTrx = (Timestamp)para[i].getParameterAsTimestamp();
//				else if (name.equals("CloseCashDate"))
//					closeCashDate = (Timestamp)para[i].getParameterAsTimestamp();
//				else if (name.equals("seqTutupKas"))
//					seqTutupKas = (int)para[i].getParameterAsInt();	
//				else if (name.equals("DocumentNo"))
//					DocumentNo = (String)para[i].getParameterAsString();	
//				else if (name.equals("CreatedByPOS_ID"))
//					CreatedByPOS_ID = (int)para[i].getParameterAsInt();	
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		
	}

	@Override
	protected String doIt() throws Exception {
		// TODO Auto-generated method stub
		//String p_dateSeq =new SimpleDateFormat("dd-MM-yyyy").format(p_DateTrx);
		String msg = "";
		
		
		//Timestamp ts = new Timestamp(t*1000);
		//String s = new SimpleDateFormat("MM/dd/yyyy-mm-dd hh:mm:ss").format(ts);
		 				
		//Kasir_ID
		//String sqlKasir = "SELECT C_BPartner_ID FROM AD_User WHERE AD_Client_ID = ? AND AD_User_ID = ?";
		//CreatedByPOS_ID =  DB.getSQLValueEx(ctx.toString(), sqlKasir.toString(), new Object[]{AD_Client_ID,AD_User_ID});
		
		//POSName
		//String sqlPOSName = "SELECT Name FROM C_POS WHERE AD_Client_ID = ? AND CreatedByPOS_ID = ?";
		//posName =  DB.getSQLValueStringEx(ctx.toString(), sqlPOSName.toString(), new Object[]{AD_Client_ID,CreatedByPOS_ID,seqTutupKas});
		
		//KasirName
		//MUser user = new MUser(ctx, AD_User_ID, get_TrxName());
		//kasirName = user.getName();
		
		//Client Value
		//MClient client = new MClient(ctx, AD_Client_ID, get_TrxName());
		//clientValue = client.getValue();
		
		//seq tutup kas
//		StringBuilder sqlSeq = new StringBuilder();
//			sqlSeq.append("SELECT MAX(seqTutupKas) ");
//			sqlSeq.append("FROM C_DecorisPOS ");
//			sqlSeq.append("WHERE AD_Client_ID= ? ");
//			sqlSeq.append("AND CreatedByPOS_ID = ? ");
//			sqlSeq.append("AND DateOrdered = '" + p_DateTrx.toString() + "'");
//			

		
		
			
	//	seqTutupKas = DB.getSQLValueEx(ctx.toString(), sqlSeq.toString(), new Object []{AD_Client_ID,CreatedByPOS_ID});	
		
//		if (seqTutupKas == null){
//			seqTutupKas = 0;
//		}
//		
//		if (seqTutupKas >= 0){
//			seqTutupKas = seqTutupKas + 1;
//		}
		
		
//		documentNo = clientValue+"/"+posName+"/"+p_dateSeq+"/"+kasirName+"/"+"00"+seqTutupKas.toString() ;
		
		//getData Tutup Kas
		StringBuilder sqlTutupKas = new StringBuilder();
			sqlTutupKas.append("SELECT C_DecorisPOS_ID ");
			sqlTutupKas.append(" FROM C_DecorisPOS ");
			sqlTutupKas.append(" WHERE AD_Client_ID= ? ");
			//sqlTutupKas.append(" AND CreatedByPOS_ID = ? ");
			sqlTutupKas.append(" AND IsTutupKas = 'Y' ");
			//sqlTutupKas.append(" AND DateOrdered = ?");
			sqlTutupKas.append(" AND DocumentNoTutupKas = ?");



		BigDecimal bayarTunai1 = Env.ZERO;
		BigDecimal bayarTunai2 = Env.ZERO;	
		BigDecimal bayarTunai3 = Env.ZERO;
		BigDecimal bayarBank1 = Env.ZERO;
		BigDecimal bayarBank2 = Env.ZERO;	
		BigDecimal bayarBank3 = Env.ZERO;
		BigDecimal bayarLeasing1 = Env.ZERO;	
		BigDecimal bayarLeasing2 = Env.ZERO;	
		BigDecimal bayarLeasing3 = Env.ZERO;	
		BigDecimal bayarLainlain1  = Env.ZERO;	
		BigDecimal bayarLainlain2  = Env.ZERO;	
		BigDecimal bayarLainlain3  = Env.ZERO;
		BigDecimal kasKeluar1  = Env.ZERO;	
		BigDecimal kasKeluar2  = Env.ZERO;	
		BigDecimal taxAmt  = Env.ZERO;	

		ArrayList<String> detailList = new ArrayList<String>();

		String sqlDecoris = "SELECT distinct DocumentNoTutupkas FROM C_DecorisPOS WHERE IsTutupKas='Y' AND AD_Client_ID = ? "
				+ " AND C_Decoris_CloseCash_ID is null";
		
		PreparedStatement pstmtmig = null;
		ResultSet rsmig = null;
		
		try {
			pstmtmig = DB.prepareStatement(sqlDecoris.toString(), get_TrxName());
			pstmtmig.setInt(1, AD_Client_ID);

			rsmig = pstmtmig.executeQuery();
			while (rsmig.next()) {
				
				detailList.add(rsmig.getString(1));
				
			}

		} catch (SQLException e) {
			log.log(Level.SEVERE, sqlTutupKas.toString(), e);
			ok = false;
			msg = "Error";
		} finally {
			DB.close(rsmig, pstmtmig);
			rsmig = null;
			pstmtmig = null;
		}
		
		
		for (int i = 0; i<detailList.size() ; i++  ){

			DocumentNo = detailList.get(i);
			int countTrx = 0; 

		Integer cekDecorisPOS_ID = DB.getSQLValueEx(null, sqlTutupKas.toString(), new Object[]{AD_Client_ID,DocumentNo});
			
			if(cekDecorisPOS_ID==null){
				cekDecorisPOS_ID = 0;
			}	
			
		if(cekDecorisPOS_ID <=0){		
			msg = "Tutup Kas Tidak Berhasil,Tidak Ada Dokumen Untuk Tutup Kas";
			return msg;
		}
			
		MDecorisPOS deccc = new MDecorisPOS(ctx, cekDecorisPOS_ID, null);
		
		CreatedByPOS_ID = deccc.getCreatedByPOS_ID();
		p_DateTrx = deccc.getDateOrdered();
		String sqlByarTunai1 = "SELECT Coalesce(SUM(Pembayaran1),0) FROM C_DecorisPOS WHERE DocumentNotutupKas = ? AND IsTutupKas = 'Y' AND AD_Client_ID = ? AND CreatedByPOS_ID = ? AND DateOrdered = ? AND payType1 = 'TUNAI'";
		bayarTunai1 = DB.getSQLValueBDEx(null, sqlByarTunai1, new Object[]{DocumentNo,AD_Client_ID,CreatedByPOS_ID,p_DateTrx});
		String sqlByarBank1 = "SELECT Coalesce(SUM(Pembayaran1),0) FROM C_DecorisPOS WHERE DocumentNotutupKas = ? AND IsTutupKas = 'Y' AND AD_Client_ID = ? AND CreatedByPOS_ID = ? AND DateOrdered = ? AND payType1 = 'BANK'";
		bayarBank1 = DB.getSQLValueBDEx(null, sqlByarBank1 , new Object[]{DocumentNo,AD_Client_ID,CreatedByPOS_ID,p_DateTrx});
		String sqlByarLeasing1 = "SELECT Coalesce(SUM(Pembayaran1),0) FROM C_DecorisPOS WHERE DocumentNotutupKas = ? AND IsTutupKas = 'Y' AND AD_Client_ID = ? AND CreatedByPOS_ID = ? AND DateOrdered = ? AND payType1 = 'LEASING'";
		bayarLeasing1 = DB.getSQLValueBDEx(null, sqlByarLeasing1, new Object[]{DocumentNo,AD_Client_ID,CreatedByPOS_ID,p_DateTrx});
		String sqlByarLainLain1 = "SELECT Coalesce(SUM(Pembayaran1),0) FROM C_DecorisPOS WHERE DocumentNotutupKas = ? AND IsTutupKas = 'Y' AND AD_Client_ID = ? AND CreatedByPOS_ID = ? AND DateOrdered = ? AND payType1 = 'HUTANG'";
		bayarLainlain1 = DB.getSQLValueBDEx(null, sqlByarLainLain1, new Object[]{DocumentNo,AD_Client_ID,CreatedByPOS_ID,p_DateTrx});
		String sqlKasKeluar1 = "SELECT Coalesce(SUM(Pembayaran1),0) FROM C_DecorisPOS WHERE DocumentNotutupKas = ? AND IsTutupKas = 'Y' AND AD_Client_ID = ? AND CreatedByPOS_ID = ? AND DateOrdered = ? AND payType1 Is null AND pembayaran1 < 0";
		kasKeluar1 = DB.getSQLValueBDEx(null, sqlKasKeluar1, new Object[]{DocumentNo,AD_Client_ID,CreatedByPOS_ID,p_DateTrx});
		
		String sqlByarTunai2 = "SELECT Coalesce(SUM(Pembayaran2),0) FROM C_DecorisPOS WHERE DocumentNotutupKas = ? AND IsTutupKas = 'Y' AND AD_Client_ID = ? AND CreatedByPOS_ID = ? AND DateOrdered = ? AND payType2 = 'TUNAI'";
		bayarTunai2 = DB.getSQLValueBDEx(null, sqlByarTunai2, new Object[]{DocumentNo,AD_Client_ID,CreatedByPOS_ID,p_DateTrx});
		String sqlByarBank2 = "SELECT Coalesce(SUM(Pembayaran2),0) FROM C_DecorisPOS WHERE DocumentNotutupKas = ? AND IsTutupKas = 'Y' AND AD_Client_ID = ? AND CreatedByPOS_ID = ? AND DateOrdered = ? AND payType2 = 'BANK'";
		bayarBank2 = DB.getSQLValueBDEx(null, sqlByarBank2 , new Object[]{DocumentNo,AD_Client_ID,CreatedByPOS_ID,p_DateTrx});
		String sqlByarLeasing2 = "SELECT Coalesce(SUM(Pembayaran2),0) FROM C_DecorisPOS WHERE DocumentNotutupKas = ? AND IsTutupKas = 'Y' AND AD_Client_ID = ? AND CreatedByPOS_ID = ? AND DateOrdered = ? AND payType2 = 'LEASING'";
		bayarLeasing2 = DB.getSQLValueBDEx(null, sqlByarLeasing2, new Object[]{DocumentNo,AD_Client_ID,CreatedByPOS_ID,p_DateTrx});
		String sqlByarLainLain2 = "SELECT Coalesce(SUM(Pembayaran2),0) FROM C_DecorisPOS WHERE DocumentNotutupKas = ? AND IsTutupKas = 'Y' AND AD_Client_ID = ? AND CreatedByPOS_ID = ? AND DateOrdered = ? AND payType2 = 'HUTANG'";
		bayarLainlain2 = DB.getSQLValueBDEx(null, sqlByarLainLain2, new Object[]{DocumentNo,AD_Client_ID,CreatedByPOS_ID,p_DateTrx});
		String sqlKasKeluar2 = "SELECT Coalesce(SUM(Pembayaran2),0) FROM C_DecorisPOS WHERE DocumentNotutupKas = ? AND IsTutupKas = 'Y' AND AD_Client_ID = ? AND CreatedByPOS_ID = ? AND DateOrdered = ? AND payType2 Is null AND pembayaran2 < 0";
		kasKeluar2 = DB.getSQLValueBDEx(null, sqlKasKeluar2, new Object[]{DocumentNo,AD_Client_ID,CreatedByPOS_ID,p_DateTrx});

		String sqlByarTunai3 = "SELECT Coalesce(SUM(Pembayaran3),0) FROM C_DecorisPOS WHERE DocumentNotutupKas = ? AND IsTutupKas = 'Y' AND AD_Client_ID = ? AND CreatedByPOS_ID = ? AND DateOrdered = ? AND payType3 = 'TUNAI'";
		bayarTunai3 = DB.getSQLValueBDEx(null, sqlByarTunai3, new Object[]{DocumentNo,AD_Client_ID,CreatedByPOS_ID,p_DateTrx});
		String sqlByarBank3 = "SELECT Coalesce(SUM(Pembayaran3),0) FROM C_DecorisPOS WHERE DocumentNotutupKas = ? AND IsTutupKas = 'Y' AND AD_Client_ID = ? AND CreatedByPOS_ID = ? AND DateOrdered = ? AND payType3 = 'BANK'";
		bayarBank3 = DB.getSQLValueBDEx(null, sqlByarBank3 , new Object[]{DocumentNo,AD_Client_ID,CreatedByPOS_ID,p_DateTrx});
		String sqlByarLeasing3 = "SELECT Coalesce(SUM(Pembayaran3),0) FROM C_DecorisPOS WHERE DocumentNotutupKas = ? AND IsTutupKas = 'Y' AND AD_Client_ID = ? AND CreatedByPOS_ID = ? AND DateOrdered = ? AND payType3 = 'LEASING'";
		bayarLeasing3 = DB.getSQLValueBDEx(null, sqlByarLeasing3, new Object[]{DocumentNo,AD_Client_ID,CreatedByPOS_ID,p_DateTrx});
		String sqlByarLainLain3 = "SELECT Coalesce(SUM(Pembayaran3),0) FROM C_DecorisPOS WHERE DocumentNotutupKas = ? AND IsTutupKas = 'Y' AND AD_Client_ID = ? AND CreatedByPOS_ID = ? AND DateOrdered = ? AND payType3 = 'HUTANG'";
		bayarLainlain3 = DB.getSQLValueBDEx(null, sqlByarLainLain3, new Object[]{DocumentNo,AD_Client_ID,CreatedByPOS_ID,p_DateTrx});

		String sqltax= "SELECT Coalesce(SUM(taxAmt),0) FROM C_DecorisPOS WHERE DocumentNotutupKas = ? AND IsTutupKas = 'Y' AND AD_Client_ID = ? AND CreatedByPOS_ID = ? AND DateOrdered = ?";
		taxAmt = DB.getSQLValueBDEx(null, sqltax, new Object[]{DocumentNo,AD_Client_ID,CreatedByPOS_ID,p_DateTrx});
		
		
		String sqlAD_Org = "SELECT AD_Org_ID FROM C_DecorisPOS WHERE AD_Client_ID = ? AND CreatedByPOS_ID = ?";
		Integer Org_ID = DB.getSQLValueEx(null, sqlAD_Org, new Object[]{AD_Client_ID,CreatedByPOS_ID});
		if(Org_ID == null){
			Org_ID = 0;
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(Env.getContextAsDate(ctx, "#Date"));
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
//		Timestamp now = new Timestamp(cal.getTimeInMillis());
			

		BigDecimal jmlTunai = bayarTunai1.add(bayarTunai2).add(bayarTunai3);
		BigDecimal jmlBank = bayarBank1.add(bayarBank2).add(bayarBank3);
		BigDecimal jmlLeasing = bayarLeasing1.add(bayarLeasing2).add(bayarLeasing3);
		BigDecimal jmlLainlain = bayarLainlain1.add(bayarLainlain2).add(bayarLainlain3);
		BigDecimal kasKeluar = kasKeluar1.add(kasKeluar2);	
		
		if(cekDecorisPOS_ID > 0){
			
			MDecorisPOS dec = new MDecorisPOS(ctx, cekDecorisPOS_ID, null);
			
			dec_closeCash = new X_C_Decoris_CloseCash(ctx, 0, null);
			dec_closeCash.setAD_Org_ID(Org_ID);
			dec_closeCash.setDocumentNo(DocumentNo);
			dec_closeCash.setCloseCashDate(dec.getUpdated());
			dec_closeCash.setDateTrx(dec.getDateOrdered());
			dec_closeCash.setCash(jmlTunai);
			dec_closeCash.setBankPayment(jmlBank);
			dec_closeCash.setleasingpayment(jmlLeasing);
			dec_closeCash.setlainlain(jmlLainlain);
			dec_closeCash.setCashIn(jmlTunai);
			dec_closeCash.setCashOut(kasKeluar);
			dec_closeCash.setseqTutupKas(dec.getseqTutupKas());
			dec_closeCash.setCreatedByPOS_ID(dec.getCreatedByPOS_ID());
			dec_closeCash.setTaxAmt(taxAmt);
			dec_closeCash.saveEx();
			
		}
		
		
	
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sqlTutupKas.toString(), get_TrxName());
			pstmt.setInt(1, AD_Client_ID);
			//pstmt.setInt(2, CreatedByPOS_ID);
			//pstmt.setTimestamp(3, p_DateTrx);
			pstmt.setString(2, DocumentNo);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				countTrx++;
				C_DecorisPOS_ID = rs.getInt(1);
				decPOS = new X_C_DecorisPOS(ctx, C_DecorisPOS_ID, null);
				decPOS.setIsTutupKas(true);
//				decPOS.setDocumentNoTutupKas(DocumentNo);
//				decPOS.setseqTutupKas(seqTutupKas);
				decPOS.set_ValueNoCheck("C_Decoris_CloseCash_ID", dec_closeCash.getC_Decoris_CloseCash_ID());
				decPOS.saveEx();
				
			}

		} catch (SQLException e) {
			log.log(Level.SEVERE, sqlTutupKas.toString(), e);
			ok = false;
			msg = "Error";
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		
		if(dec_closeCash !=null){
			dec_closeCash.setTrxCount(countTrx);
			dec_closeCash.saveEx();
			msg = "Tutup Kas Berhasil Dilakukan";
		}
		}
		
		return msg;
	}

	
	
}
