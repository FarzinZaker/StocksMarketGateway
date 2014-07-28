/**
 * SubSectorResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package stocks.tse.ws;

public class SubSectorResponse  implements java.io.Serializable {
    private stocks.tse.ws.SubSectorResponseSubSectorResult subSectorResult;

    public SubSectorResponse() {
    }

    public SubSectorResponse(
           stocks.tse.ws.SubSectorResponseSubSectorResult subSectorResult) {
           this.subSectorResult = subSectorResult;
    }


    /**
     * Gets the subSectorResult value for this SubSectorResponse.
     * 
     * @return subSectorResult
     */
    public stocks.tse.ws.SubSectorResponseSubSectorResult getSubSectorResult() {
        return subSectorResult;
    }


    /**
     * Sets the subSectorResult value for this SubSectorResponse.
     * 
     * @param subSectorResult
     */
    public void setSubSectorResult(stocks.tse.ws.SubSectorResponseSubSectorResult subSectorResult) {
        this.subSectorResult = subSectorResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SubSectorResponse)) return false;
        SubSectorResponse other = (SubSectorResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.subSectorResult==null && other.getSubSectorResult()==null) || 
             (this.subSectorResult!=null &&
              this.subSectorResult.equals(other.getSubSectorResult())));
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
        if (getSubSectorResult() != null) {
            _hashCode += getSubSectorResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SubSectorResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">SubSectorResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subSectorResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tsetmc.com/", "SubSectorResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>SubSectorResponse>SubSectorResult"));
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
