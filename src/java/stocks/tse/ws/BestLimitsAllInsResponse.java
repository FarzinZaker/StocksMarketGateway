/**
 * BestLimitsAllInsResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package stocks.tse.ws;

public class BestLimitsAllInsResponse  implements java.io.Serializable {
    private stocks.tse.ws.BestLimitsAllInsResponseBestLimitsAllInsResult bestLimitsAllInsResult;

    public BestLimitsAllInsResponse() {
    }

    public BestLimitsAllInsResponse(
           stocks.tse.ws.BestLimitsAllInsResponseBestLimitsAllInsResult bestLimitsAllInsResult) {
           this.bestLimitsAllInsResult = bestLimitsAllInsResult;
    }


    /**
     * Gets the bestLimitsAllInsResult value for this BestLimitsAllInsResponse.
     * 
     * @return bestLimitsAllInsResult
     */
    public stocks.tse.ws.BestLimitsAllInsResponseBestLimitsAllInsResult getBestLimitsAllInsResult() {
        return bestLimitsAllInsResult;
    }


    /**
     * Sets the bestLimitsAllInsResult value for this BestLimitsAllInsResponse.
     * 
     * @param bestLimitsAllInsResult
     */
    public void setBestLimitsAllInsResult(stocks.tse.ws.BestLimitsAllInsResponseBestLimitsAllInsResult bestLimitsAllInsResult) {
        this.bestLimitsAllInsResult = bestLimitsAllInsResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BestLimitsAllInsResponse)) return false;
        BestLimitsAllInsResponse other = (BestLimitsAllInsResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.bestLimitsAllInsResult==null && other.getBestLimitsAllInsResult()==null) || 
             (this.bestLimitsAllInsResult!=null &&
              this.bestLimitsAllInsResult.equals(other.getBestLimitsAllInsResult())));
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
        if (getBestLimitsAllInsResult() != null) {
            _hashCode += getBestLimitsAllInsResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BestLimitsAllInsResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">BestLimitsAllInsResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bestLimitsAllInsResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tsetmc.com/", "BestLimitsAllInsResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>BestLimitsAllInsResponse>BestLimitsAllInsResult"));
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
