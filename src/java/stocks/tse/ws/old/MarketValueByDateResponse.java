/**
 * MarketValueByDateResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package stocks.tse.ws.old;

public class MarketValueByDateResponse  implements java.io.Serializable {
    private java.math.BigDecimal marketValueByDateResult;

    public MarketValueByDateResponse() {
    }

    public MarketValueByDateResponse(
           java.math.BigDecimal marketValueByDateResult) {
           this.marketValueByDateResult = marketValueByDateResult;
    }


    /**
     * Gets the marketValueByDateResult value for this MarketValueByDateResponse.
     * 
     * @return marketValueByDateResult
     */
    public java.math.BigDecimal getMarketValueByDateResult() {
        return marketValueByDateResult;
    }


    /**
     * Sets the marketValueByDateResult value for this MarketValueByDateResponse.
     * 
     * @param marketValueByDateResult
     */
    public void setMarketValueByDateResult(java.math.BigDecimal marketValueByDateResult) {
        this.marketValueByDateResult = marketValueByDateResult;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
    	if (obj == null) return false;
        if (!(obj instanceof MarketValueByDateResponse)) return false;
        MarketValueByDateResponse other = (MarketValueByDateResponse) obj;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.marketValueByDateResult==null && other.getMarketValueByDateResult()==null) || 
             (this.marketValueByDateResult!=null &&
              this.marketValueByDateResult.equals(other.getMarketValueByDateResult())));
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
        if (getMarketValueByDateResult() != null) {
            _hashCode += getMarketValueByDateResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MarketValueByDateResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">MarketValueByDateResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("marketValueByDateResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tsetmc.com/", "MarketValueByDateResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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
