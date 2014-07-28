/**
 * TradeLastDayResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package stocks.tse.ws.old;

public class TradeLastDayResponse  implements java.io.Serializable {
    private TradeLastDayResponseTradeLastDayResult tradeLastDayResult;

    public TradeLastDayResponse() {
    }

    public TradeLastDayResponse(
           TradeLastDayResponseTradeLastDayResult tradeLastDayResult) {
           this.tradeLastDayResult = tradeLastDayResult;
    }


    /**
     * Gets the tradeLastDayResult value for this TradeLastDayResponse.
     * 
     * @return tradeLastDayResult
     */
    public TradeLastDayResponseTradeLastDayResult getTradeLastDayResult() {
        return tradeLastDayResult;
    }


    /**
     * Sets the tradeLastDayResult value for this TradeLastDayResponse.
     * 
     * @param tradeLastDayResult
     */
    public void setTradeLastDayResult(TradeLastDayResponseTradeLastDayResult tradeLastDayResult) {
        this.tradeLastDayResult = tradeLastDayResult;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
    	if (obj == null) return false;
        if (!(obj instanceof TradeLastDayResponse)) return false;
        TradeLastDayResponse other = (TradeLastDayResponse) obj;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.tradeLastDayResult==null && other.getTradeLastDayResult()==null) || 
             (this.tradeLastDayResult!=null &&
              this.tradeLastDayResult.equals(other.getTradeLastDayResult())));
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
        if (getTradeLastDayResult() != null) {
            _hashCode += getTradeLastDayResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TradeLastDayResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">TradeLastDayResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tradeLastDayResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tsetmc.com/", "TradeLastDayResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>TradeLastDayResponse>TradeLastDayResult"));
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
