/**
 * InstrumentStateDescOneInsResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package stocks.tse.ws.old;

public class InstrumentStateDescOneInsResponse  implements java.io.Serializable {
    private InstrumentStateDescOneInsResponseInstrumentStateDescOneInsResult instrumentStateDescOneInsResult;

    public InstrumentStateDescOneInsResponse() {
    }

    public InstrumentStateDescOneInsResponse(
           InstrumentStateDescOneInsResponseInstrumentStateDescOneInsResult instrumentStateDescOneInsResult) {
           this.instrumentStateDescOneInsResult = instrumentStateDescOneInsResult;
    }


    /**
     * Gets the instrumentStateDescOneInsResult value for this InstrumentStateDescOneInsResponse.
     * 
     * @return instrumentStateDescOneInsResult
     */
    public InstrumentStateDescOneInsResponseInstrumentStateDescOneInsResult getInstrumentStateDescOneInsResult() {
        return instrumentStateDescOneInsResult;
    }


    /**
     * Sets the instrumentStateDescOneInsResult value for this InstrumentStateDescOneInsResponse.
     * 
     * @param instrumentStateDescOneInsResult
     */
    public void setInstrumentStateDescOneInsResult(InstrumentStateDescOneInsResponseInstrumentStateDescOneInsResult instrumentStateDescOneInsResult) {
        this.instrumentStateDescOneInsResult = instrumentStateDescOneInsResult;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
    	if (obj == null) return false;
        if (!(obj instanceof InstrumentStateDescOneInsResponse)) return false;
        InstrumentStateDescOneInsResponse other = (InstrumentStateDescOneInsResponse) obj;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.instrumentStateDescOneInsResult==null && other.getInstrumentStateDescOneInsResult()==null) || 
             (this.instrumentStateDescOneInsResult!=null &&
              this.instrumentStateDescOneInsResult.equals(other.getInstrumentStateDescOneInsResult())));
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
        if (getInstrumentStateDescOneInsResult() != null) {
            _hashCode += getInstrumentStateDescOneInsResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InstrumentStateDescOneInsResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">InstrumentStateDescOneInsResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("instrumentStateDescOneInsResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tsetmc.com/", "InstrumentStateDescOneInsResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>InstrumentStateDescOneInsResponse>InstrumentStateDescOneInsResult"));
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
