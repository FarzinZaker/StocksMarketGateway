/**
 * IPaymentGateway.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package stocks.payment.mellat;

public interface IPaymentGateway extends java.rmi.Remote {
    public String bpVerifyRequest(long terminalId, String userName, String userPassword, long orderId, long saleOrderId, long saleReferenceId) throws java.rmi.RemoteException;
    public String bpRefundInquiryRequest(long terminalId, String userName, String userPassword, long orderId, long refundOrderId, long refundReferenceId) throws java.rmi.RemoteException;
    public String bpRefundVerifyRequest(long terminalId, String userName, String userPassword, long orderId, long refundOrderId, long refundReferenceId) throws java.rmi.RemoteException;
    public String bpSettleRequest(long terminalId, String userName, String userPassword, long orderId, long saleOrderId, long saleReferenceId) throws java.rmi.RemoteException;
    public String bpDynamicPayRequest(long terminalId, String userName, String userPassword, long orderId, long amount, String localDate, String localTime, String additionalData, String callBackUrl, long payerId, long subServiceId) throws java.rmi.RemoteException;
    public String bpReversalRequest(long terminalId, String userName, String userPassword, long orderId, long saleOrderId, long saleReferenceId) throws java.rmi.RemoteException;
    public String bpPayRequest(long terminalId, String userName, String userPassword, long orderId, long amount, String localDate, String localTime, String additionalData, String callBackUrl, long payerId) throws java.rmi.RemoteException;
    public String bpSaleReferenceIdRequest(long terminalId, String userName, String userPassword, long orderId, long saleOrderId) throws java.rmi.RemoteException;
    public String bpInquiryRequest(long terminalId, String userName, String userPassword, long orderId, long saleOrderId, long saleReferenceId) throws java.rmi.RemoteException;
    public String bpRefundRequest(long terminalId, String userName, String userPassword, long orderId, long saleOrderId, long saleReferenceId, long refundAmount) throws java.rmi.RemoteException;
}
