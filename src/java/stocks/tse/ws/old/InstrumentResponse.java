/**
 * InstrumentResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package stocks.tse.ws.old;

public class InstrumentResponse  implements java.io.Serializable {
    private InstrumentResponseInstrumentResult instrumentResult;

    public InstrumentResponse() {
    }

    public InstrumentResponse(
           InstrumentResponseInstrumentResult instrumentResult) {
           this.instrumentResult = instrumentResult;
    }


    /**
     * Gets the instrumentResult value for this InstrumentResponse.
     * 
     * @return instrumentResult
     */
    public InstrumentResponseInstrumentResult getInstrumentResult() {
        return instrumentResult;
    }


    /**
     * Sets the instrumentResult value for this InstrumentResponse.
     * 
     * @param instrumentResult
     */
    public void setInstrumentResult(InstrumentResponseInstrumentResult instrumentResult) {
        this.instrumentResult = instrumentResult;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
    	if (obj == null) return false;
        if (!(obj instanceof InstrumentResponse)) return false;
        InstrumentResponse other = (InstrumentResponse) obj;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.instrumentResult==null && other.getInstrumentResult()==null) || 
             (this.instrumentResult!=null &&
              this.instrumentResult.equals(other.getInstrumentResult())));
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
        if (getInstrumentResult() != null) {
            _hashCode += getInstrumentResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InstrumentResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">InstrumentResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("instrumentResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tsetmc.com/", "InstrumentResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>InstrumentResponse>InstrumentResult"));
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
