/**
 * Fut_Live_Loc_ServiceSoap_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package stocks.rate.ws;

public interface Fut_Live_Loc_ServiceSoap_PortType extends java.rmi.Remote {
    public java.lang.String getContractsList() throws java.rmi.RemoteException;
    public java.lang.String getServersList() throws java.rmi.RemoteException;
    public stocks.rate.ws.Short_Future_Market_Data getContractShortInfo(java.lang.String contractCode) throws java.rmi.RemoteException;
    public stocks.rate.ws.Future_Market_Data getContractInfo(java.lang.String contractCode) throws java.rmi.RemoteException;
    public java.lang.String now() throws java.rmi.RemoteException;
    public java.lang.String getAtiTotalGrid() throws java.rmi.RemoteException;
}
