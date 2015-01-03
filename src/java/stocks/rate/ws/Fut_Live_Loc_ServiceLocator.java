/**
 * Fut_Live_Loc_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package stocks.rate.ws;

public class Fut_Live_Loc_ServiceLocator extends org.apache.axis.client.Service implements stocks.rate.ws.Fut_Live_Loc_Service {

    public Fut_Live_Loc_ServiceLocator() {
    }


    public Fut_Live_Loc_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public Fut_Live_Loc_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for Fut_Live_Loc_ServiceSoap
    private java.lang.String Fut_Live_Loc_ServiceSoap_address = "http://cdn.ime.co.ir/Services/Fut_Live_Loc_Service.asmx";

    public java.lang.String getFut_Live_Loc_ServiceSoapAddress() {
        return Fut_Live_Loc_ServiceSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String Fut_Live_Loc_ServiceSoapWSDDServiceName = "Fut_Live_Loc_ServiceSoap";

    public java.lang.String getFut_Live_Loc_ServiceSoapWSDDServiceName() {
        return Fut_Live_Loc_ServiceSoapWSDDServiceName;
    }

    public void setFut_Live_Loc_ServiceSoapWSDDServiceName(java.lang.String name) {
        Fut_Live_Loc_ServiceSoapWSDDServiceName = name;
    }

    public stocks.rate.ws.Fut_Live_Loc_ServiceSoap_PortType getFut_Live_Loc_ServiceSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(Fut_Live_Loc_ServiceSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getFut_Live_Loc_ServiceSoap(endpoint);
    }

    public stocks.rate.ws.Fut_Live_Loc_ServiceSoap_PortType getFut_Live_Loc_ServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            stocks.rate.ws.Fut_Live_Loc_ServiceSoap_BindingStub _stub = new stocks.rate.ws.Fut_Live_Loc_ServiceSoap_BindingStub(portAddress, this);
            _stub.setPortName(getFut_Live_Loc_ServiceSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setFut_Live_Loc_ServiceSoapEndpointAddress(java.lang.String address) {
        Fut_Live_Loc_ServiceSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (stocks.rate.ws.Fut_Live_Loc_ServiceSoap_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                stocks.rate.ws.Fut_Live_Loc_ServiceSoap_BindingStub _stub = new stocks.rate.ws.Fut_Live_Loc_ServiceSoap_BindingStub(new java.net.URL(Fut_Live_Loc_ServiceSoap_address), this);
                _stub.setPortName(getFut_Live_Loc_ServiceSoapWSDDServiceName());
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
        if ("Fut_Live_Loc_ServiceSoap".equals(inputPortName)) {
            return getFut_Live_Loc_ServiceSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "Fut_Live_Loc_Service");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "Fut_Live_Loc_ServiceSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("Fut_Live_Loc_ServiceSoap".equals(portName)) {
            setFut_Live_Loc_ServiceSoapEndpointAddress(address);
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
