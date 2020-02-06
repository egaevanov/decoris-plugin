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

/** Generated Interface for C_Decoris_PreSales
 *  @author iDempiere (generated) 
 *  @version Release 3.1
 */
@SuppressWarnings("all")
public interface I_C_Decoris_PreSales 
{

    /** TableName=C_Decoris_PreSales */
    public static final String Table_Name = "C_Decoris_PreSales";

    /** AD_Table_ID=1000071 */
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

    /** Column name AD_User_ID */
    public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

	/** Set User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID);

	/** Get User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID();

	public org.compiere.model.I_AD_User getAD_User() throws RuntimeException;

    /** Column name AlamatLain */
    public static final String COLUMNNAME_AlamatLain = "AlamatLain";

	/** Set AlamatLain	  */
	public void setAlamatLain (String AlamatLain);

	/** Get AlamatLain	  */
	public String getAlamatLain();

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

    /** Column name C_Currency_ID */
    public static final String COLUMNNAME_C_Currency_ID = "C_Currency_ID";

	/** Set Currency.
	  * The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID);

	/** Get Currency.
	  * The Currency for this record
	  */
	public int getC_Currency_ID();

	public org.compiere.model.I_C_Currency getC_Currency() throws RuntimeException;

    /** Column name C_Decoris_CloseCash_ID */
    public static final String COLUMNNAME_C_Decoris_CloseCash_ID = "C_Decoris_CloseCash_ID";

	/** Set Decoris Close Cash	  */
	public void setC_Decoris_CloseCash_ID (int C_Decoris_CloseCash_ID);

	/** Get Decoris Close Cash	  */
	public int getC_Decoris_CloseCash_ID();

	public I_C_Decoris_CloseCash getC_Decoris_CloseCash() throws RuntimeException;

    /** Column name C_Decoris_PreSales_ID */
    public static final String COLUMNNAME_C_Decoris_PreSales_ID = "C_Decoris_PreSales_ID";

	/** Set C_Decoris_PreSales_ID	  */
	public void setC_Decoris_PreSales_ID (int C_Decoris_PreSales_ID);

	/** Get C_Decoris_PreSales_ID	  */
	public int getC_Decoris_PreSales_ID();

    /** Column name C_Decoris_PreSales_UU */
    public static final String COLUMNNAME_C_Decoris_PreSales_UU = "C_Decoris_PreSales_UU";

	/** Set C_Decoris_PreSales_UU	  */
	public void setC_Decoris_PreSales_UU (String C_Decoris_PreSales_UU);

	/** Get C_Decoris_PreSales_UU	  */
	public String getC_Decoris_PreSales_UU();

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

	public I_C_DecorisPOS getC_DecorisPOS() throws RuntimeException;

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

    /** Column name C_PreOrder_ID */
    public static final String COLUMNNAME_C_PreOrder_ID = "C_PreOrder_ID";

	/** Set C_PreOrder_ID	  */
	public void setC_PreOrder_ID (int C_PreOrder_ID);

	/** Get C_PreOrder_ID	  */
	public int getC_PreOrder_ID();

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

    /** Column name dpp */
    public static final String COLUMNNAME_dpp = "dpp";

	/** Set dpp	  */
	public void setdpp (BigDecimal dpp);

	/** Get dpp	  */
	public BigDecimal getdpp();

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

    /** Column name IsTutupKas */
    public static final String COLUMNNAME_IsTutupKas = "IsTutupKas";

	/** Set Tutup Kas	  */
	public void setIsTutupKas (boolean IsTutupKas);

	/** Get Tutup Kas	  */
	public boolean isTutupKas();

    /** Column name JumlahPembayaranBank */
    public static final String COLUMNNAME_JumlahPembayaranBank = "JumlahPembayaranBank";

	/** Set JumlahPembayaranBank	  */
	public void setJumlahPembayaranBank (BigDecimal JumlahPembayaranBank);

	/** Get JumlahPembayaranBank	  */
	public BigDecimal getJumlahPembayaranBank();

    /** Column name JumlahPembayaranHutang */
    public static final String COLUMNNAME_JumlahPembayaranHutang = "JumlahPembayaranHutang";

	/** Set JumlahPembayaranHutang	  */
	public void setJumlahPembayaranHutang (BigDecimal JumlahPembayaranHutang);

	/** Get JumlahPembayaranHutang	  */
	public BigDecimal getJumlahPembayaranHutang();

    /** Column name JumlahPembayaranLeasing */
    public static final String COLUMNNAME_JumlahPembayaranLeasing = "JumlahPembayaranLeasing";

	/** Set JumlahPembayaranLeasing	  */
	public void setJumlahPembayaranLeasing (BigDecimal JumlahPembayaranLeasing);

	/** Get JumlahPembayaranLeasing	  */
	public BigDecimal getJumlahPembayaranLeasing();

    /** Column name JumlahPembayaranTunai */
    public static final String COLUMNNAME_JumlahPembayaranTunai = "JumlahPembayaranTunai";

	/** Set JumlahPembayaranTunai	  */
	public void setJumlahPembayaranTunai (BigDecimal JumlahPembayaranTunai);

	/** Get JumlahPembayaranTunai	  */
	public BigDecimal getJumlahPembayaranTunai();

    /** Column name leaseprovider */
    public static final String COLUMNNAME_leaseprovider = "leaseprovider";

	/** Set leaseprovider	  */
	public void setleaseprovider (String leaseprovider);

	/** Get leaseprovider	  */
	public String getleaseprovider();

    /** Column name M_Locator_ID */
    public static final String COLUMNNAME_M_Locator_ID = "M_Locator_ID";

	/** Set Locator.
	  * Warehouse Locator
	  */
	public void setM_Locator_ID (int M_Locator_ID);

	/** Get Locator.
	  * Warehouse Locator
	  */
	public int getM_Locator_ID();

	public I_M_Locator getM_Locator() throws RuntimeException;

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

    /** Column name OrderDocType_ID */
    public static final String COLUMNNAME_OrderDocType_ID = "OrderDocType_ID";

	/** Set OrderDocType_ID	  */
	public void setOrderDocType_ID (int OrderDocType_ID);

	/** Get OrderDocType_ID	  */
	public int getOrderDocType_ID();

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

    /** Column name PreSalesStatus */
    public static final String COLUMNNAME_PreSalesStatus = "PreSalesStatus";

	/** Set PreSalesStatus	  */
	public void setPreSalesStatus (String PreSalesStatus);

	/** Get PreSalesStatus	  */
	public String getPreSalesStatus();

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

    /** Column name Status */
    public static final String COLUMNNAME_Status = "Status";

	/** Set Status.
	  * Status of the currently running check
	  */
	public void setStatus (String Status);

	/** Get Status.
	  * Status of the currently running check
	  */
	public String getStatus();

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

    /** Column name total */
    public static final String COLUMNNAME_total = "total";

	/** Set total	  */
	public void settotal (BigDecimal total);

	/** Get total	  */
	public BigDecimal gettotal();

    /** Column name TotalBayar */
    public static final String COLUMNNAME_TotalBayar = "TotalBayar";

	/** Set TotalBayar	  */
	public void setTotalBayar (BigDecimal TotalBayar);

	/** Get TotalBayar	  */
	public BigDecimal getTotalBayar();

    /** Column name TotalBelanja */
    public static final String COLUMNNAME_TotalBelanja = "TotalBelanja";

	/** Set TotalBelanja	  */
	public void setTotalBelanja (BigDecimal TotalBelanja);

	/** Get TotalBelanja	  */
	public BigDecimal getTotalBelanja();

    /** Column name TotalDiskon */
    public static final String COLUMNNAME_TotalDiskon = "TotalDiskon";

	/** Set AlamatLain	  */
	public void setTotalDiskon (BigDecimal TotalDiskon);

	/** Get AlamatLain	  */
	public BigDecimal getTotalDiskon();

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
