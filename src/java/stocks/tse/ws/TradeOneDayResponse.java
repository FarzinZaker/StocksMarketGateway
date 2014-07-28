/**
 * TradeOneDayResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package stocks.tse.ws;

public class TradeOneDayResponse  implements java.io.Serializable {
    private stocks.tse.ws.TradeOneDayResponseTradeOneDayResult tradeOneDayResult;

    public TradeOneDayResponse() {
    }

    public TradeOneDayResponse(
           stocks.tse.ws.TradeOneDayResponseTradeOneDayResult tradeOneDayResult) {
           this.tradeOneDayResult = tradeOneDayResult;
    }


    /**
     * Gets the tradeOneDayResult value for this TradeOneDayResponse.
     * 
     * @return tradeOneDayResult
     */
    public stocks.tse.ws.TradeOneDayResponseTradeOneDayResult getTradeOneDayResult() {
        return tradeOneDayResult;
    }


    /**
     * Sets the tradeOneDayResult value for this TradeOneDayResponse.
     * 
     * @param tradeOneDayResult
     */
    public void setTradeOneDayResult(stocks.tse.ws.TradeOneDayResponseTradeOneDayResult tradeOneDayResult) {
        this.tradeOneDayResult = tradeOneDayResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TradeOneDayResponse)) return false;
        TradeOneDayResponse other = (TradeOneDayResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.tradeOneDayResult==null && other.getTradeOneDayResult()==null) || 
             (this.tradeOneDayResult!=null &&
              this.tradeOneDayResult.equals(other.getTradeOneDayResult())));
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
        if (getTradeOneDayResult() != null) {
            _hashCode += getTradeOneDayResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TradeOneDayResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">TradeOneDayResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tradeOneDayResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tsetmc.com/", "TradeOneDayResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>TradeOneDayResponse>TradeOneDayResult"));
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
