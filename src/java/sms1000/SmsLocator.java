/**
 * SmsLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package sms1000;

public class SmsLocator extends org.apache.axis.client.Service implements sms1000.Sms {

    public SmsLocator() {
    }


    public SmsLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SmsLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for smsSoap
    private java.lang.String smsSoap_address = "http://rahyabbulk.ir:8020/webservice/sms.asmx";

    public java.lang.String getsmsSoapAddress() {
        return smsSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String smsSoapWSDDServiceName = "smsSoap";

    public java.lang.String getsmsSoapWSDDServiceName() {
        return smsSoapWSDDServiceName;
    }

    public void setsmsSoapWSDDServiceName(java.lang.String name) {
        smsSoapWSDDServiceName = name;
    }

    public sms1000.SmsSoap_PortType getsmsSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(smsSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getsmsSoap(endpoint);
    }

    public sms1000.SmsSoap_PortType getsmsSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            sms1000.SmsSoap_BindingStub _stub = new sms1000.SmsSoap_BindingStub(portAddress, this);
            _stub.setPortName(getsmsSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setsmsSoapEndpointAddress(java.lang.String address) {
        smsSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (sms1000.SmsSoap_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                sms1000.SmsSoap_BindingStub _stub = new sms1000.SmsSoap_BindingStub(new java.net.URL(smsSoap_address), this);
                _stub.setPortName(getsmsSoapWSDDServiceName());
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
        if ("smsSoap".equals(inputPortName)) {
            return getsmsSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://srv-pardis.sms1000.ir/", "sms");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://srv-pardis.sms1000.ir/", "smsSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("smsSoap".equals(portName)) {
            setsmsSoapEndpointAddress(address);
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
