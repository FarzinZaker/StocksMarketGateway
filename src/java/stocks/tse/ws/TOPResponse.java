/**
 * TOPResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package stocks.tse.ws;

public class TOPResponse  implements java.io.Serializable {
    private stocks.tse.ws.TOPResponseTOPResult TOPResult;

    public TOPResponse() {
    }

    public TOPResponse(
           stocks.tse.ws.TOPResponseTOPResult TOPResult) {
           this.TOPResult = TOPResult;
    }


    /**
     * Gets the TOPResult value for this TOPResponse.
     * 
     * @return TOPResult
     */
    public stocks.tse.ws.TOPResponseTOPResult getTOPResult() {
        return TOPResult;
    }


    /**
     * Sets the TOPResult value for this TOPResponse.
     * 
     * @param TOPResult
     */
    public void setTOPResult(stocks.tse.ws.TOPResponseTOPResult TOPResult) {
        this.TOPResult = TOPResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TOPResponse)) return false;
        TOPResponse other = (TOPResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.TOPResult==null && other.getTOPResult()==null) || 
             (this.TOPResult!=null &&
              this.TOPResult.equals(other.getTOPResult())));
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
        if (getTOPResult() != null) {
            _hashCode += getTOPResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TOPResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">TOPResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TOPResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tsetmc.com/", "TOPResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>TOPResponse>TOPResult"));
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
