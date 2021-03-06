/**
 * AdjPriceResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package stocks.tse.ws.old;

public class AdjPriceResponse  implements java.io.Serializable {
    private AdjPriceResponseAdjPriceResult adjPriceResult;

    public AdjPriceResponse() {
    }

    public AdjPriceResponse(
           AdjPriceResponseAdjPriceResult adjPriceResult) {
           this.adjPriceResult = adjPriceResult;
    }


    /**
     * Gets the adjPriceResult value for this AdjPriceResponse.
     * 
     * @return adjPriceResult
     */
    public AdjPriceResponseAdjPriceResult getAdjPriceResult() {
        return adjPriceResult;
    }


    /**
     * Sets the adjPriceResult value for this AdjPriceResponse.
     * 
     * @param adjPriceResult
     */
    public void setAdjPriceResult(AdjPriceResponseAdjPriceResult adjPriceResult) {
        this.adjPriceResult = adjPriceResult;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
    	if (obj == null) return false;
        if (!(obj instanceof AdjPriceResponse)) return false;
        AdjPriceResponse other = (AdjPriceResponse) obj;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.adjPriceResult==null && other.getAdjPriceResult()==null) || 
             (this.adjPriceResult!=null &&
              this.adjPriceResult.equals(other.getAdjPriceResult())));
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
        if (getAdjPriceResult() != null) {
            _hashCode += getAdjPriceResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AdjPriceResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">AdjPriceResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("adjPriceResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tsetmc.com/", "AdjPriceResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>AdjPriceResponse>AdjPriceResult"));
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
