/**
 * CompanyResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package stocks.tse.ws;

public class CompanyResponse  implements java.io.Serializable {
    private stocks.tse.ws.CompanyResponseCompanyResult companyResult;

    public CompanyResponse() {
    }

    public CompanyResponse(
           stocks.tse.ws.CompanyResponseCompanyResult companyResult) {
           this.companyResult = companyResult;
    }


    /**
     * Gets the companyResult value for this CompanyResponse.
     * 
     * @return companyResult
     */
    public stocks.tse.ws.CompanyResponseCompanyResult getCompanyResult() {
        return companyResult;
    }


    /**
     * Sets the companyResult value for this CompanyResponse.
     * 
     * @param companyResult
     */
    public void setCompanyResult(stocks.tse.ws.CompanyResponseCompanyResult companyResult) {
        this.companyResult = companyResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CompanyResponse)) return false;
        CompanyResponse other = (CompanyResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.companyResult==null && other.getCompanyResult()==null) || 
             (this.companyResult!=null &&
              this.companyResult.equals(other.getCompanyResult())));
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
        if (getCompanyResult() != null) {
            _hashCode += getCompanyResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CompanyResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">CompanyResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("companyResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tsetmc.com/", "CompanyResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>CompanyResponse>CompanyResult"));
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
