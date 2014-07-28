/**
 * TsePublicV2Locator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package stocks.tse.ws;

public class TsePublicV2Locator extends org.apache.axis.client.Service implements stocks.tse.ws.TsePublicV2 {

    public TsePublicV2Locator() {
    }


    public TsePublicV2Locator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public TsePublicV2Locator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for TsePublicV2Soap
    private java.lang.String TsePublicV2Soap_address = "http://service.tsetmc.com/WebService/TsePublicV2.asmx";

    public java.lang.String getTsePublicV2SoapAddress() {
        return TsePublicV2Soap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String TsePublicV2SoapWSDDServiceName = "TsePublicV2Soap";

    public java.lang.String getTsePublicV2SoapWSDDServiceName() {
        return TsePublicV2SoapWSDDServiceName;
    }

    public void setTsePublicV2SoapWSDDServiceName(java.lang.String name) {
        TsePublicV2SoapWSDDServiceName = name;
    }

    public stocks.tse.ws.TsePublicV2Soap_PortType getTsePublicV2Soap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(TsePublicV2Soap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getTsePublicV2Soap(endpoint);
    }

    public stocks.tse.ws.TsePublicV2Soap_PortType getTsePublicV2Soap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            stocks.tse.ws.TsePublicV2Soap_BindingStub _stub = new stocks.tse.ws.TsePublicV2Soap_BindingStub(portAddress, this);
            _stub.setPortName(getTsePublicV2SoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setTsePublicV2SoapEndpointAddress(java.lang.String address) {
        TsePublicV2Soap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (stocks.tse.ws.TsePublicV2Soap_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                stocks.tse.ws.TsePublicV2Soap_BindingStub _stub = new stocks.tse.ws.TsePublicV2Soap_BindingStub(new java.net.URL(TsePublicV2Soap_address), this);
                _stub.setPortName(getTsePublicV2SoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("TsePublicV2Soap".equals(inputPortName)) {
            return getTsePublicV2Soap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tsetmc.com/", "TsePublicV2");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tsetmc.com/", "TsePublicV2Soap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("TsePublicV2Soap".equals(portName)) {
            setTsePublicV2SoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
