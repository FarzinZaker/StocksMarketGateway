/**
 * InstAffectResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package stocks.tse.ws.old;

public class InstAffectResponse  implements java.io.Serializable {
    private InstAffectResponseInstAffectResult instAffectResult;

    public InstAffectResponse() {
    }

    public InstAffectResponse(
           InstAffectResponseInstAffectResult instAffectResult) {
           this.instAffectResult = instAffectResult;
    }


    /**
     * Gets the instAffectResult value for this InstAffectResponse.
     * 
     * @return instAffectResult
     */
    public InstAffectResponseInstAffectResult getInstAffectResult() {
        return instAffectResult;
    }


    /**
     * Sets the instAffectResult value for this InstAffectResponse.
     * 
     * @param instAffectResult
     */
    public void setInstAffectResult(InstAffectResponseInstAffectResult instAffectResult) {
        this.instAffectResult = instAffectResult;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
    	if (obj == null) return false;
        if (!(obj instanceof InstAffectResponse)) return false;
        InstAffectResponse other = (InstAffectResponse) obj;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.instAffectResult==null && other.getInstAffectResult()==null) || 
             (this.instAffectResult!=null &&
              this.instAffectResult.equals(other.getInstAffectResult())));
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
        if (getInstAffectResult() != null) {
            _hashCode += getInstAffectResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InstAffectResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">InstAffectResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("instAffectResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tsetmc.com/", "InstAffectResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>InstAffectResponse>InstAffectResult"));
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
