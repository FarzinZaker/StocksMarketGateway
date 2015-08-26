/**
 * PaymentIFBinding.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package stocks.payment.saman;

public interface PaymentIFBinding extends javax.xml.rpc.Service {
    public String getPaymentIFBindingSoapAddress();

    public stocks.payment.saman.PaymentIFBindingSoap_PortType getPaymentIFBindingSoap() throws javax.xml.rpc.ServiceException;

    public stocks.payment.saman.PaymentIFBindingSoap_PortType getPaymentIFBindingSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
