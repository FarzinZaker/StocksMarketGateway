/**
 * InstTradeResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package stocks.tse.ws.old;

public class InstTradeResponse  implements java.io.Serializable {
    private InstTradeResponseInstTradeResult instTradeResult;

    public InstTradeResponse() {
    }

    public InstTradeResponse(
           InstTradeResponseInstTradeResult instTradeResult) {
           this.instTradeResult = instTradeResult;
    }


    /**
     * Gets the instTradeResult value for this InstTradeResponse.
     * 
     * @return instTradeResult
     */
    public InstTradeResponseInstTradeResult getInstTradeResult() {
        return instTradeResult;
    }


    /**
     * Sets the instTradeResult value for this InstTradeResponse.
     * 
     * @param instTradeResult
     */
    public void setInstTradeResult(InstTradeResponseInstTradeResult instTradeResult) {
        this.instTradeResult = instTradeResult;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
    	if (obj == null) return false;
        if (!(obj instanceof InstTradeResponse)) return false;
        InstTradeResponse other = (InstTradeResponse) obj;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.instTradeResult==null && other.getInstTradeResult()==null) || 
             (this.instTradeResult!=null &&
              this.instTradeResult.equals(other.getInstTradeResult())));
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
        if (getInstTradeResult() != null) {
            _hashCode += getInstTradeResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InstTradeResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">InstTradeResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("instTradeResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tsetmc.com/", "InstTradeResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>InstTradeResponse>InstTradeResult"));
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
