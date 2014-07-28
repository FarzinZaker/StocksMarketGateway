/**
 * TsePublicV2.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package stocks.tse.ws.old;

public interface TsePublicV2 extends javax.xml.rpc.Service {
    public String getTsePublicV2Soap12Address();

    public TsePublicV2Soap_PortType getTsePublicV2Soap12() throws javax.xml.rpc.ServiceException;

    public TsePublicV2Soap_PortType getTsePublicV2Soap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public String getTsePublicV2SoapAddress();

    public TsePublicV2Soap_PortType getTsePublicV2Soap() throws javax.xml.rpc.ServiceException;

    public TsePublicV2Soap_PortType getTsePublicV2Soap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
