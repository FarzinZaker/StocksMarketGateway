/**
 * PowerInstrumentResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package stocks.tse.ws;

public class PowerInstrumentResponse  implements java.io.Serializable {
    private stocks.tse.ws.PowerInstrumentResponsePowerInstrumentResult powerInstrumentResult;

    public PowerInstrumentResponse() {
    }

    public PowerInstrumentResponse(
           stocks.tse.ws.PowerInstrumentResponsePowerInstrumentResult powerInstrumentResult) {
           this.powerInstrumentResult = powerInstrumentResult;
    }


    /**
     * Gets the powerInstrumentResult value for this PowerInstrumentResponse.
     * 
     * @return powerInstrumentResult
     */
    public stocks.tse.ws.PowerInstrumentResponsePowerInstrumentResult getPowerInstrumentResult() {
        return powerInstrumentResult;
    }


    /**
     * Sets the powerInstrumentResult value for this PowerInstrumentResponse.
     * 
     * @param powerInstrumentResult
     */
    public void setPowerInstrumentResult(stocks.tse.ws.PowerInstrumentResponsePowerInstrumentResult powerInstrumentResult) {
        this.powerInstrumentResult = powerInstrumentResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PowerInstrumentResponse)) return false;
        PowerInstrumentResponse other = (PowerInstrumentResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.powerInstrumentResult==null && other.getPowerInstrumentResult()==null) || 
             (this.powerInstrumentResult!=null &&
              this.powerInstrumentResult.equals(other.getPowerInstrumentResult())));
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
        if (getPowerInstrumentResult() != null) {
            _hashCode += getPowerInstrumentResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PowerInstrumentResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">PowerInstrumentResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("powerInstrumentResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tsetmc.com/", "PowerInstrumentResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>PowerInstrumentResponse>PowerInstrumentResult"));
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
