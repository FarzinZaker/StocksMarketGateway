/**
 * InstrumentDetailAllInsResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package stocks.tse.ws.old;

public class InstrumentDetailAllInsResponse  implements java.io.Serializable {
    private InstrumentDetailAllInsResponseInstrumentDetailAllInsResult instrumentDetailAllInsResult;

    public InstrumentDetailAllInsResponse() {
    }

    public InstrumentDetailAllInsResponse(
           InstrumentDetailAllInsResponseInstrumentDetailAllInsResult instrumentDetailAllInsResult) {
           this.instrumentDetailAllInsResult = instrumentDetailAllInsResult;
    }


    /**
     * Gets the instrumentDetailAllInsResult value for this InstrumentDetailAllInsResponse.
     * 
     * @return instrumentDetailAllInsResult
     */
    public InstrumentDetailAllInsResponseInstrumentDetailAllInsResult getInstrumentDetailAllInsResult() {
        return instrumentDetailAllInsResult;
    }


    /**
     * Sets the instrumentDetailAllInsResult value for this InstrumentDetailAllInsResponse.
     * 
     * @param instrumentDetailAllInsResult
     */
    public void setInstrumentDetailAllInsResult(InstrumentDetailAllInsResponseInstrumentDetailAllInsResult instrumentDetailAllInsResult) {
        this.instrumentDetailAllInsResult = instrumentDetailAllInsResult;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
    	if (obj == null) return false;
        if (!(obj instanceof InstrumentDetailAllInsResponse)) return false;
        InstrumentDetailAllInsResponse other = (InstrumentDetailAllInsResponse) obj;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.instrumentDetailAllInsResult==null && other.getInstrumentDetailAllInsResult()==null) || 
             (this.instrumentDetailAllInsResult!=null &&
              this.instrumentDetailAllInsResult.equals(other.getInstrumentDetailAllInsResult())));
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
        if (getInstrumentDetailAllInsResult() != null) {
            _hashCode += getInstrumentDetailAllInsResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InstrumentDetailAllInsResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">InstrumentDetailAllInsResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("instrumentDetailAllInsResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tsetmc.com/", "InstrumentDetailAllInsResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>InstrumentDetailAllInsResponse>InstrumentDetailAllInsResult"));
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
