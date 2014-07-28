/**
 * NSCStartResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package stocks.tse.ws;

public class NSCStartResponse  implements java.io.Serializable {
    private stocks.tse.ws.NSCStartResponseNSCStartResult NSCStartResult;

    public NSCStartResponse() {
    }

    public NSCStartResponse(
           stocks.tse.ws.NSCStartResponseNSCStartResult NSCStartResult) {
           this.NSCStartResult = NSCStartResult;
    }


    /**
     * Gets the NSCStartResult value for this NSCStartResponse.
     * 
     * @return NSCStartResult
     */
    public stocks.tse.ws.NSCStartResponseNSCStartResult getNSCStartResult() {
        return NSCStartResult;
    }


    /**
     * Sets the NSCStartResult value for this NSCStartResponse.
     * 
     * @param NSCStartResult
     */
    public void setNSCStartResult(stocks.tse.ws.NSCStartResponseNSCStartResult NSCStartResult) {
        this.NSCStartResult = NSCStartResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof NSCStartResponse)) return false;
        NSCStartResponse other = (NSCStartResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.NSCStartResult==null && other.getNSCStartResult()==null) || 
             (this.NSCStartResult!=null &&
              this.NSCStartResult.equals(other.getNSCStartResult())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getNSCStartResult() != null) {
            _hashCode += getNSCStartResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(NSCStartResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">NSCStartResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NSCStartResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tsetmc.com/", "NSCStartResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>NSCStartResponse>NSCStartResult"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
