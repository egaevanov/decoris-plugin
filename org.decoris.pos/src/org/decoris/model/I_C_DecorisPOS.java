/******************************************************************************
 * Product: iDempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2012 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.decoris.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for C_DecorisPOS
 *  @author iDempiere (generated) 
 *  @version Release 3.1
 */
@SuppressWarnings("all")
public interface I_C_DecorisPOS 
{

    /** TableName=C_DecorisPOS */
    public static final String Table_Name = "C_DecorisPOS";

    /** AD_Table_ID=1000054 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name C_BankAccount_ID */
    public static final String COLUMNNAME_C_BankAccount_ID = "C_BankAccount_ID";

	/** Set Bank Account.
	  * Account at the Bank
	  */
	public void setC_BankAccount_ID (int C_BankAccount_ID);

	/** Get Bank Account.
	  * Account at the Bank
	  */
	public int getC_BankAccount_ID();

	public org.compiere.model.I_C_BankAccount getC_BankAccount() throws RuntimeException;

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner .
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner .
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name C_BPartner_Location_ID */
    public static final String COLUMNNAME_C_BPartner_Location_ID = "C_BPartner_Location_ID";

	/** Set Partner Location.
	  * Identifies the (ship to) address for this Business Partner
	  */
	public void setC_BPartner_Location_ID (int C_BPartner_Location_ID);

	/** Get Partner Location.
	  * Identifies the (ship to) address for this Business Partner
	  */
	public int getC_BPartner_Location_ID();

	public org.compiere.model.I_C_BPartner_Location getC_BPartner_Location() throws RuntimeException;

    /** Column name C_Decoris_CloseCash_ID */
    public static final String COLUMNNAME_C_Decoris_CloseCash_ID = "C_Decoris_CloseCash_ID";

	/** Set Decoris Close Cash	  */
	public void setC_Decoris_CloseCash_ID (int C_Decoris_CloseCash_ID);

	/** Get Decoris Close Cash	  */
	public int getC_Decoris_CloseCash_ID();

	public I_C_Decoris_CloseCash getC_Decoris_CloseCash() throws RuntimeException;

    /** Column name C_DecorisPOS_ID */
    public static final String COLUMNNAME_C_DecorisPOS_ID = "C_DecorisPOS_ID";

	/** Set Decoris POS.
	  * Decoris POS
	  */
	public void setC_DecorisPOS_ID (int C_DecorisPOS_ID);

	/** Get Decoris POS.
	  * Decoris POS
	  */
	public int getC_DecorisPOS_ID();

    /** Column name C_DecorisPOS_UU */
    public static final String COLUMNNAME_C_DecorisPOS_UU = "C_DecorisPOS_UU";

	/** Set C_DecorisPOS_UU	  */
	public void setC_DecorisPOS_UU (String C_DecorisPOS_UU);

	/** Get C_DecorisPOS_UU	  */
	public String getC_DecorisPOS_UU();

    /** Column name C_DocType_ID */
    public static final String COLUMNNAME_C_DocType_ID = "C_DocType_ID";

	/** Set Document Type.
	  * Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID);

	/** Get Document Type.
	  * Document type or rules
	  */
	public int getC_DocType_ID();

	public org.compiere.model.I_C_DocType getC_DocType() throws RuntimeException;

    /** Column name C_Invoice_ID */
    public static final String COLUMNNAME_C_Invoice_ID = "C_Invoice_ID";

	/** Set Invoice.
	  * Invoice Identifier
	  */
	public void setC_Invoice_ID (int C_Invoice_ID);

	/** Get Invoice.
	  * Invoice Identifier
	  */
	public int getC_Invoice_ID();

	public org.compiere.model.I_C_Invoice getC_Invoice() throws RuntimeException;

    /** Column name C_Order_ID */
    public static final String COLUMNNAME_C_Order_ID = "C_Order_ID";

	/** Set Order.
	  * Order
	  */
	public void setC_Order_ID (int C_Order_ID);

	/** Get Order.
	  * Order
	  */
	public int getC_Order_ID();

	public org.compiere.model.I_C_Order getC_Order() throws RuntimeException;

    /** Column name C_Payment_ID */
    public static final String COLUMNNAME_C_Payment_ID = "C_Payment_ID";

	/** Set Payment.
	  * Payment identifier
	  */
	public void setC_Payment_ID (int C_Payment_ID);

	/** Get Payment.
	  * Payment identifier
	  */
	public int getC_Payment_ID();

	public org.compiere.model.I_C_Payment getC_Payment() throws RuntimeException;

    /** Column name C_PaymentTerm_ID */
    public static final String COLUMNNAME_C_PaymentTerm_ID = "C_PaymentTerm_ID";

	/** Set Payment Term.
	  * The terms of Payment (timing, discount)
	  */
	public void setC_PaymentTerm_ID (int C_PaymentTerm_ID);

	/** Get Payment Term.
	  * The terms of Payment (timing, discount)
	  */
	public int getC_PaymentTerm_ID();

	public org.compiere.model.I_C_PaymentTerm getC_PaymentTerm() throws RuntimeException;

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name CreatedByPOS_ID */
    public static final String COLUMNNAME_CreatedByPOS_ID = "CreatedByPOS_ID";

	/** Set CreatedByPOS_ID	  */
	public void setCreatedByPOS_ID (int CreatedByPOS_ID);

	/** Get CreatedByPOS_ID	  */
	public int getCreatedByPOS_ID();

	public org.compiere.model.I_C_BPartner getCreatedByPOS() throws RuntimeException;

    /** Column name DateOrdered */
    public static final String COLUMNNAME_DateOrdered = "DateOrdered";

	/** Set Date Ordered.
	  * Date of Order
	  */
	public void setDateOrdered (Timestamp DateOrdered);

	/** Get Date Ordered.
	  * Date of Order
	  */
	public Timestamp getDateOrdered();

    /** Column name DateTrx */
    public static final String COLUMNNAME_DateTrx = "DateTrx";

	/** Set Transaction Date.
	  * Transaction Date
	  */
	public void setDateTrx (Timestamp DateTrx);

	/** Get Transaction Date.
	  * Transaction Date
	  */
	public Timestamp getDateTrx();

    /** Column name DeliveryRule */
    public static final String COLUMNNAME_DeliveryRule = "DeliveryRule";

	/** Set Delivery Rule.
	  * Defines the timing of Delivery
	  */
	public void setDeliveryRule (String DeliveryRule);

	/** Get Delivery Rule.
	  * Defines the timing of Delivery
	  */
	public String getDeliveryRule();

    /** Column name DeliveryViaRule */
    public static final String COLUMNNAME_DeliveryViaRule = "DeliveryViaRule";

	/** Set Delivery Via.
	  * How the order will be delivered
	  */
	public void setDeliveryViaRule (String DeliveryViaRule);

	/** Get Delivery Via.
	  * How the order will be delivered
	  */
	public String getDeliveryViaRule();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name DiscountAmt */
    public static final String COLUMNNAME_DiscountAmt = "DiscountAmt";

	/** Set Discount Amount.
	  * Calculated amount of discount
	  */
	public void setDiscountAmt (BigDecimal DiscountAmt);

	/** Get Discount Amount.
	  * Calculated amount of discount
	  */
	public BigDecimal getDiscountAmt();

    /** Column name DocumentNo */
    public static final String COLUMNNAME_DocumentNo = "DocumentNo";

	/** Set Document No.
	  * Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo);

	/** Get Document No.
	  * Document sequence number of the document
	  */
	public String getDocumentNo();

    /** Column name DocumentNoTutupKas */
    public static final String COLUMNNAME_DocumentNoTutupKas = "DocumentNoTutupKas";

	/** Set Document No Tutup Kas	  */
	public void setDocumentNoTutupKas (String DocumentNoTutupKas);

	/** Get Document No Tutup Kas	  */
	public String getDocumentNoTutupKas();

    /** Column name dpp */
    public static final String COLUMNNAME_dpp = "dpp";

	/** Set dpp	  */
	public void setdpp (BigDecimal dpp);

	/** Get dpp	  */
	public BigDecimal getdpp();

    /** Column name GrandTotal */
    public static final String COLUMNNAME_GrandTotal = "GrandTotal";

	/** Set Grand Total.
	  * Total amount of document
	  */
	public void setGrandTotal (BigDecimal GrandTotal);

	/** Get Grand Total.
	  * Total amount of document
	  */
	public BigDecimal getGrandTotal();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name IsLeasing */
    public static final String COLUMNNAME_IsLeasing = "IsLeasing";

	/** Set Leasing	  */
	public void setIsLeasing (boolean IsLeasing);

	/** Get Leasing	  */
	public boolean isLeasing();

    /** Column name IsManualDocumentNo */
    public static final String COLUMNNAME_IsManualDocumentNo = "IsManualDocumentNo";

	/** Set IsManualDocumentNo	  */
	public void setIsManualDocumentNo (boolean IsManualDocumentNo);

	/** Get IsManualDocumentNo	  */
	public boolean isManualDocumentNo();

    /** Column name IsMultiLocator */
    public static final String COLUMNNAME_IsMultiLocator = "IsMultiLocator";

	/** Set IsMultiLocator	  */
	public void setIsMultiLocator (boolean IsMultiLocator);

	/** Get IsMultiLocator	  */
	public boolean isMultiLocator();

    /** Column name IsPembatalan */
    public static final String COLUMNNAME_IsPembatalan = "IsPembatalan";

	/** Set Pembatalan	  */
	public void setIsPembatalan (boolean IsPembatalan);

	/** Get Pembatalan	  */
	public boolean isPembatalan();

    /** Column name IsPembayaran */
    public static final String COLUMNNAME_IsPembayaran = "IsPembayaran";

	/** Set Pembayaran/Penerimaan	  */
	public void setIsPembayaran (boolean IsPembayaran);

	/** Get Pembayaran/Penerimaan	  */
	public boolean isPembayaran();

    /** Column name IsPenjualan */
    public static final String COLUMNNAME_IsPenjualan = "IsPenjualan";

	/** Set Penjualan	  */
	public void setIsPenjualan (boolean IsPenjualan);

	/** Get Penjualan	  */
	public boolean isPenjualan();

    /** Column name IsPickup */
    public static final String COLUMNNAME_IsPickup = "IsPickup";

	/** Set Pickup	  */
	public void setIsPickup (boolean IsPickup);

	/** Get Pickup	  */
	public boolean isPickup();

    /** Column name IsPrinted */
    public static final String COLUMNNAME_IsPrinted = "IsPrinted";

	/** Set Printed.
	  * Indicates if this document / line is printed
	  */
	public void setIsPrinted (boolean IsPrinted);

	/** Get Printed.
	  * Indicates if this document / line is printed
	  */
	public boolean isPrinted();

    /** Column name IsPrintedSJ */
    public static final String COLUMNNAME_IsPrintedSJ = "IsPrintedSJ";

	/** Set IsPrintedSJ	  */
	public void setIsPrintedSJ (boolean IsPrintedSJ);

	/** Get IsPrintedSJ	  */
	public boolean isPrintedSJ();

    /** Column name IsReceipt */
    public static final String COLUMNNAME_IsReceipt = "IsReceipt";

	/** Set Receipt.
	  * This is a sales transaction (receipt)
	  */
	public void setIsReceipt (boolean IsReceipt);

	/** Get Receipt.
	  * This is a sales transaction (receipt)
	  */
	public boolean isReceipt();

    /** Column name IsReturn */
    public static final String COLUMNNAME_IsReturn = "IsReturn";

	/** Set Is Return	  */
	public void setIsReturn (boolean IsReturn);

	/** Get Is Return	  */
	public boolean isReturn();

    /** Column name IsSOTrx */
    public static final String COLUMNNAME_IsSOTrx = "IsSOTrx";

	/** Set Sales Transaction.
	  * This is a Sales Transaction
	  */
	public void setIsSOTrx (boolean IsSOTrx);

	/** Get Sales Transaction.
	  * This is a Sales Transaction
	  */
	public boolean isSOTrx();

    /** Column name IsSpektra */
    public static final String COLUMNNAME_IsSpektra = "IsSpektra";

	/** Set Spektra	  */
	public void setIsSpektra (boolean IsSpektra);

	/** Get Spektra	  */
	public boolean isSpektra();

    /** Column name IsTutupKas */
    public static final String COLUMNNAME_IsTutupKas = "IsTutupKas";

	/** Set Tutup Kas	  */
	public void setIsTutupKas (boolean IsTutupKas);

	/** Get Tutup Kas	  */
	public boolean isTutupKas();

    /** Column name leaseprovider */
    public static final String COLUMNNAME_leaseprovider = "leaseprovider";

	/** Set leaseprovider	  */
	public void setleaseprovider (String leaseprovider);

	/** Get leaseprovider	  */
	public String getleaseprovider();

    /** Column name LocatorNoMulti_ID */
    public static final String COLUMNNAME_LocatorNoMulti_ID = "LocatorNoMulti_ID";

	/** Set LocatorNoMulti_ID	  */
	public void setLocatorNoMulti_ID (int LocatorNoMulti_ID);

	/** Get LocatorNoMulti_ID	  */
	public int getLocatorNoMulti_ID();

    /** Column name M_PriceList_ID */
    public static final String COLUMNNAME_M_PriceList_ID = "M_PriceList_ID";

	/** Set Price List.
	  * Unique identifier of a Price List
	  */
	public void setM_PriceList_ID (int M_PriceList_ID);

	/** Get Price List.
	  * Unique identifier of a Price List
	  */
	public int getM_PriceList_ID();

	public org.compiere.model.I_M_PriceList getM_PriceList() throws RuntimeException;

    /** Column name M_Warehouse_ID */
    public static final String COLUMNNAME_M_Warehouse_ID = "M_Warehouse_ID";

	/** Set Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID);

	/** Get Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID();

	public org.compiere.model.I_M_Warehouse getM_Warehouse() throws RuntimeException;

    /** Column name orderdoctype_id */
    public static final String COLUMNNAME_orderdoctype_id = "orderdoctype_id";

	/** Set orderdoctype_id	  */
	public void setorderdoctype_id (int orderdoctype_id);

	/** Get orderdoctype_id	  */
	public int getorderdoctype_id();

    /** Column name PaymentRule */
    public static final String COLUMNNAME_PaymentRule = "PaymentRule";

	/** Set Payment Rule.
	  * How you pay the invoice
	  */
	public void setPaymentRule (String PaymentRule);

	/** Get Payment Rule.
	  * How you pay the invoice
	  */
	public String getPaymentRule();

    /** Column name PayType1 */
    public static final String COLUMNNAME_PayType1 = "PayType1";

	/** Set Tipe Pembayaran Pertama	  */
	public void setPayType1 (String PayType1);

	/** Get Tipe Pembayaran Pertama	  */
	public String getPayType1();

    /** Column name PayType2 */
    public static final String COLUMNNAME_PayType2 = "PayType2";

	/** Set Tipe Pembayaran Kedua	  */
	public void setPayType2 (String PayType2);

	/** Get Tipe Pembayaran Kedua	  */
	public String getPayType2();

    /** Column name PayType3 */
    public static final String COLUMNNAME_PayType3 = "PayType3";

	/** Set Tipe Pembayaran Ketiga	  */
	public void setPayType3 (String PayType3);

	/** Get Tipe Pembayaran Ketiga	  */
	public String getPayType3();

    /** Column name PayType4 */
    public static final String COLUMNNAME_PayType4 = "PayType4";

	/** Set Tipe Pembayaran Keempat	  */
	public void setPayType4 (String PayType4);

	/** Get Tipe Pembayaran Keempat	  */
	public String getPayType4();

    /** Column name Pembayaran1 */
    public static final String COLUMNNAME_Pembayaran1 = "Pembayaran1";

	/** Set Pembayaran Pertama	  */
	public void setPembayaran1 (BigDecimal Pembayaran1);

	/** Get Pembayaran Pertama	  */
	public BigDecimal getPembayaran1();

    /** Column name Pembayaran2 */
    public static final String COLUMNNAME_Pembayaran2 = "Pembayaran2";

	/** Set Pembayaran Kedua	  */
	public void setPembayaran2 (BigDecimal Pembayaran2);

	/** Get Pembayaran Kedua	  */
	public BigDecimal getPembayaran2();

    /** Column name Pembayaran3 */
    public static final String COLUMNNAME_Pembayaran3 = "Pembayaran3";

	/** Set Pembayaran Ketiga	  */
	public void setPembayaran3 (BigDecimal Pembayaran3);

	/** Get Pembayaran Ketiga	  */
	public BigDecimal getPembayaran3();

    /** Column name Pembayaran4 */
    public static final String COLUMNNAME_Pembayaran4 = "Pembayaran4";

	/** Set Pembayaran Keempat	  */
	public void setPembayaran4 (BigDecimal Pembayaran4);

	/** Get Pembayaran Keempat	  */
	public BigDecimal getPembayaran4();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name SalesRep_ID */
    public static final String COLUMNNAME_SalesRep_ID = "SalesRep_ID";

	/** Set Sales Representative.
	  * Sales Representative or Company Agent
	  */
	public void setSalesRep_ID (int SalesRep_ID);

	/** Get Sales Representative.
	  * Sales Representative or Company Agent
	  */
	public int getSalesRep_ID();

	public org.compiere.model.I_AD_User getSalesRep() throws RuntimeException;

    /** Column name seqTutupKas */
    public static final String COLUMNNAME_seqTutupKas = "seqTutupKas";

	/** Set seqTutupKas	  */
	public void setseqTutupKas (int seqTutupKas);

	/** Get seqTutupKas	  */
	public int getseqTutupKas();

    /** Column name Supervisor_ID */
    public static final String COLUMNNAME_Supervisor_ID = "Supervisor_ID";

	/** Set Supervisor.
	  * Supervisor for this user/organization - used for escalation and approval
	  */
	public void setSupervisor_ID (int Supervisor_ID);

	/** Get Supervisor.
	  * Supervisor for this user/organization - used for escalation and approval
	  */
	public int getSupervisor_ID();

	public org.compiere.model.I_AD_User getSupervisor() throws RuntimeException;

    /** Column name TaxAmt */
    public static final String COLUMNNAME_TaxAmt = "TaxAmt";

	/** Set Tax Amount.
	  * Tax Amount for a document
	  */
	public void setTaxAmt (BigDecimal TaxAmt);

	/** Get Tax Amount.
	  * Tax Amount for a document
	  */
	public BigDecimal getTaxAmt();

    /** Column name TotalKembalian */
    public static final String COLUMNNAME_TotalKembalian = "TotalKembalian";

	/** Set Total Kembalian	  */
	public void setTotalKembalian (BigDecimal TotalKembalian);

	/** Get Total Kembalian	  */
	public BigDecimal getTotalKembalian();

    /** Column name TotalLines */
    public static final String COLUMNNAME_TotalLines = "TotalLines";

	/** Set Total Lines.
	  * Total of all document lines
	  */
	public void setTotalLines (BigDecimal TotalLines);

	/** Get Total Lines.
	  * Total of all document lines
	  */
	public BigDecimal getTotalLines();

    /** Column name TotalTunai */
    public static final String COLUMNNAME_TotalTunai = "TotalTunai";

	/** Set TotalTunai	  */
	public void setTotalTunai (BigDecimal TotalTunai);

	/** Get TotalTunai	  */
	public BigDecimal getTotalTunai();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();
}
