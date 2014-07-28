/**
 * BestLimitOneInsResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package stocks.tse.ws;

public class BestLimitOneInsResponse  implements java.io.Serializable {
    private stocks.tse.ws.BestLimitOneInsResponseBestLimitOneInsResult bestLimitOneInsResult;

    public BestLimitOneInsResponse() {
    }

    public BestLimitOneInsResponse(
           stocks.tse.ws.BestLimitOneInsResponseBestLimitOneInsResult bestLimitOneInsResult) {
           this.bestLimitOneInsResult = bestLimitOneInsResult;
    }


    /**
     * Gets the bestLimitOneInsResult value for this BestLimitOneInsResponse.
     * 
     * @return bestLimitOneInsResult
     */
    public stocks.tse.ws.BestLimitOneInsResponseBestLimitOneInsResult getBestLimitOneInsResult() {
        return bestLimitOneInsResult;
    }


    /**
     * Sets the bestLimitOneInsResult value for this BestLimitOneInsResponse.
     * 
     * @param bestLimitOneInsResult
     */
    public void setBestLimitOneInsResult(stocks.tse.ws.BestLimitOneInsResponseBestLimitOneInsResult bestLimitOneInsResult) {
        this.bestLimitOneInsResult = bestLimitOneInsResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BestLimitOneInsResponse)) return false;
        BestLimitOneInsResponse other = (BestLimitOneInsResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.bestLimitOneInsResult==null && other.getBestLimitOneInsResult()==null) || 
             (this.bestLimitOneInsResult!=null &&
              this.bestLimitOneInsResult.equals(other.getBestLimitOneInsResult())));
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
        if (getBestLimitOneInsResult() != null) {
            _hashCode += getBestLimitOneInsResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BestLimitOneInsResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">BestLimitOneInsResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bestLimitOneInsResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tsetmc.com/", "BestLimitOneInsResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>BestLimitOneInsResponse>BestLimitOneInsResult"));
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
