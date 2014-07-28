/**
 * InstWithBestLimitResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package stocks.tse.ws.old;

public class InstWithBestLimitResponse  implements java.io.Serializable {
    private InstWithBestLimitResponseInstWithBestLimitResult instWithBestLimitResult;

    public InstWithBestLimitResponse() {
    }

    public InstWithBestLimitResponse(
           InstWithBestLimitResponseInstWithBestLimitResult instWithBestLimitResult) {
           this.instWithBestLimitResult = instWithBestLimitResult;
    }


    /**
     * Gets the instWithBestLimitResult value for this InstWithBestLimitResponse.
     * 
     * @return instWithBestLimitResult
     */
    public InstWithBestLimitResponseInstWithBestLimitResult getInstWithBestLimitResult() {
        return instWithBestLimitResult;
    }


    /**
     * Sets the instWithBestLimitResult value for this InstWithBestLimitResponse.
     * 
     * @param instWithBestLimitResult
     */
    public void setInstWithBestLimitResult(InstWithBestLimitResponseInstWithBestLimitResult instWithBestLimitResult) {
        this.instWithBestLimitResult = instWithBestLimitResult;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
    	if (obj == null) return false;
        if (!(obj instanceof InstWithBestLimitResponse)) return false;
        InstWithBestLimitResponse other = (InstWithBestLimitResponse) obj;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.instWithBestLimitResult==null && other.getInstWithBestLimitResult()==null) || 
             (this.instWithBestLimitResult!=null &&
              this.instWithBestLimitResult.equals(other.getInstWithBestLimitResult())));
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
        if (getInstWithBestLimitResult() != null) {
            _hashCode += getInstWithBestLimitResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InstWithBestLimitResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">InstWithBestLimitResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("instWithBestLimitResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tsetmc.com/", "InstWithBestLimitResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>InstWithBestLimitResponse>InstWithBestLimitResult"));
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
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
