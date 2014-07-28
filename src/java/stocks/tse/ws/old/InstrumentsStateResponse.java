/**
 * InstrumentsStateResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package stocks.tse.ws.old;

public class InstrumentsStateResponse  implements java.io.Serializable {
    private InstrumentsStateResponseInstrumentsStateResult instrumentsStateResult;

    public InstrumentsStateResponse() {
    }

    public InstrumentsStateResponse(
           InstrumentsStateResponseInstrumentsStateResult instrumentsStateResult) {
           this.instrumentsStateResult = instrumentsStateResult;
    }


    /**
     * Gets the instrumentsStateResult value for this InstrumentsStateResponse.
     * 
     * @return instrumentsStateResult
     */
    public InstrumentsStateResponseInstrumentsStateResult getInstrumentsStateResult() {
        return instrumentsStateResult;
    }


    /**
     * Sets the instrumentsStateResult value for this InstrumentsStateResponse.
     * 
     * @param instrumentsStateResult
     */
    public void setInstrumentsStateResult(InstrumentsStateResponseInstrumentsStateResult instrumentsStateResult) {
        this.instrumentsStateResult = instrumentsStateResult;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
    	if (obj == null) return false;
        if (!(obj instanceof InstrumentsStateResponse)) return false;
        InstrumentsStateResponse other = (InstrumentsStateResponse) obj;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.instrumentsStateResult==null && other.getInstrumentsStateResult()==null) || 
             (this.instrumentsStateResult!=null &&
              this.instrumentsStateResult.equals(other.getInstrumentsStateResult())));
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
        if (getInstrumentsStateResult() != null) {
            _hashCode += getInstrumentsStateResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InstrumentsStateResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">InstrumentsStateResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("instrumentsStateResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tsetmc.com/", "InstrumentsStateResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>InstrumentsStateResponse>InstrumentsStateResult"));
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
