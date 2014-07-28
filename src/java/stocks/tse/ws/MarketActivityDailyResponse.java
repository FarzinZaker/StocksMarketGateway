/**
 * MarketActivityDailyResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package stocks.tse.ws;

public class MarketActivityDailyResponse  implements java.io.Serializable {
    private stocks.tse.ws.MarketActivityDailyResponseMarketActivityDailyResult marketActivityDailyResult;

    public MarketActivityDailyResponse() {
    }

    public MarketActivityDailyResponse(
           stocks.tse.ws.MarketActivityDailyResponseMarketActivityDailyResult marketActivityDailyResult) {
           this.marketActivityDailyResult = marketActivityDailyResult;
    }


    /**
     * Gets the marketActivityDailyResult value for this MarketActivityDailyResponse.
     * 
     * @return marketActivityDailyResult
     */
    public stocks.tse.ws.MarketActivityDailyResponseMarketActivityDailyResult getMarketActivityDailyResult() {
        return marketActivityDailyResult;
    }


    /**
     * Sets the marketActivityDailyResult value for this MarketActivityDailyResponse.
     * 
     * @param marketActivityDailyResult
     */
    public void setMarketActivityDailyResult(stocks.tse.ws.MarketActivityDailyResponseMarketActivityDailyResult marketActivityDailyResult) {
        this.marketActivityDailyResult = marketActivityDailyResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MarketActivityDailyResponse)) return false;
        MarketActivityDailyResponse other = (MarketActivityDailyResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.marketActivityDailyResult==null && other.getMarketActivityDailyResult()==null) || 
             (this.marketActivityDailyResult!=null &&
              this.marketActivityDailyResult.equals(other.getMarketActivityDailyResult())));
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
        if (getMarketActivityDailyResult() != null) {
            _hashCode += getMarketActivityDailyResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MarketActivityDailyResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">MarketActivityDailyResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("marketActivityDailyResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tsetmc.com/", "MarketActivityDailyResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>MarketActivityDailyResponse>MarketActivityDailyResult"));
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
