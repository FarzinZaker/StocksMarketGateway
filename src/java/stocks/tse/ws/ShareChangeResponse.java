/**
 * ShareChangeResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package stocks.tse.ws;

public class ShareChangeResponse  implements java.io.Serializable {
    private stocks.tse.ws.ShareChangeResponseShareChangeResult shareChangeResult;

    public ShareChangeResponse() {
    }

    public ShareChangeResponse(
           stocks.tse.ws.ShareChangeResponseShareChangeResult shareChangeResult) {
           this.shareChangeResult = shareChangeResult;
    }


    /**
     * Gets the shareChangeResult value for this ShareChangeResponse.
     * 
     * @return shareChangeResult
     */
    public stocks.tse.ws.ShareChangeResponseShareChangeResult getShareChangeResult() {
        return shareChangeResult;
    }


    /**
     * Sets the shareChangeResult value for this ShareChangeResponse.
     * 
     * @param shareChangeResult
     */
    public void setShareChangeResult(stocks.tse.ws.ShareChangeResponseShareChangeResult shareChangeResult) {
        this.shareChangeResult = shareChangeResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ShareChangeResponse)) return false;
        ShareChangeResponse other = (ShareChangeResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.shareChangeResult==null && other.getShareChangeResult()==null) || 
             (this.shareChangeResult!=null &&
              this.shareChangeResult.equals(other.getShareChangeResult())));
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
        if (getShareChangeResult() != null) {
            _hashCode += getShareChangeResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ShareChangeResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">ShareChangeResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shareChangeResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tsetmc.com/", "ShareChangeResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>ShareChangeResponse>ShareChangeResult"));
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
