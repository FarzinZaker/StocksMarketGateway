/**
 * StateTypeResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package stocks.tse.ws.old;

public class StateTypeResponse  implements java.io.Serializable {
    private StateTypeResponseStateTypeResult stateTypeResult;

    public StateTypeResponse() {
    }

    public StateTypeResponse(
           StateTypeResponseStateTypeResult stateTypeResult) {
           this.stateTypeResult = stateTypeResult;
    }


    /**
     * Gets the stateTypeResult value for this StateTypeResponse.
     * 
     * @return stateTypeResult
     */
    public StateTypeResponseStateTypeResult getStateTypeResult() {
        return stateTypeResult;
    }


    /**
     * Sets the stateTypeResult value for this StateTypeResponse.
     * 
     * @param stateTypeResult
     */
    public void setStateTypeResult(StateTypeResponseStateTypeResult stateTypeResult) {
        this.stateTypeResult = stateTypeResult;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
    	if (obj == null) return false;
        if (!(obj instanceof StateTypeResponse)) return false;
        StateTypeResponse other = (StateTypeResponse) obj;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.stateTypeResult==null && other.getStateTypeResult()==null) || 
             (this.stateTypeResult!=null &&
              this.stateTypeResult.equals(other.getStateTypeResult())));
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
        if (getStateTypeResult() != null) {
            _hashCode += getStateTypeResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(StateTypeResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">StateTypeResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stateTypeResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tsetmc.com/", "StateTypeResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>StateTypeResponse>StateTypeResult"));
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
