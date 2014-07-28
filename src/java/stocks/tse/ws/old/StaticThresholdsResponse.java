/**
 * StaticThresholdsResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package stocks.tse.ws.old;

public class StaticThresholdsResponse  implements java.io.Serializable {
    private StaticThresholdsResponseStaticThresholdsResult staticThresholdsResult;

    public StaticThresholdsResponse() {
    }

    public StaticThresholdsResponse(
           StaticThresholdsResponseStaticThresholdsResult staticThresholdsResult) {
           this.staticThresholdsResult = staticThresholdsResult;
    }


    /**
     * Gets the staticThresholdsResult value for this StaticThresholdsResponse.
     * 
     * @return staticThresholdsResult
     */
    public StaticThresholdsResponseStaticThresholdsResult getStaticThresholdsResult() {
        return staticThresholdsResult;
    }


    /**
     * Sets the staticThresholdsResult value for this StaticThresholdsResponse.
     * 
     * @param staticThresholdsResult
     */
    public void setStaticThresholdsResult(StaticThresholdsResponseStaticThresholdsResult staticThresholdsResult) {
        this.staticThresholdsResult = staticThresholdsResult;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
    	if (obj == null) return false;
        if (!(obj instanceof StaticThresholdsResponse)) return false;
        StaticThresholdsResponse other = (StaticThresholdsResponse) obj;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.staticThresholdsResult==null && other.getStaticThresholdsResult()==null) || 
             (this.staticThresholdsResult!=null &&
              this.staticThresholdsResult.equals(other.getStaticThresholdsResult())));
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
        if (getStaticThresholdsResult() != null) {
            _hashCode += getStaticThresholdsResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(StaticThresholdsResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">StaticThresholdsResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("staticThresholdsResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tsetmc.com/", "StaticThresholdsResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>StaticThresholdsResponse>StaticThresholdsResult"));
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
