/**
 * FutureInformationResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package stocks.tse.ws;

public class FutureInformationResponse  implements java.io.Serializable {
    private stocks.tse.ws.FutureInformationResponseFutureInformationResult futureInformationResult;

    public FutureInformationResponse() {
    }

    public FutureInformationResponse(
           stocks.tse.ws.FutureInformationResponseFutureInformationResult futureInformationResult) {
           this.futureInformationResult = futureInformationResult;
    }


    /**
     * Gets the futureInformationResult value for this FutureInformationResponse.
     * 
     * @return futureInformationResult
     */
    public stocks.tse.ws.FutureInformationResponseFutureInformationResult getFutureInformationResult() {
        return futureInformationResult;
    }


    /**
     * Sets the futureInformationResult value for this FutureInformationResponse.
     * 
     * @param futureInformationResult
     */
    public void setFutureInformationResult(stocks.tse.ws.FutureInformationResponseFutureInformationResult futureInformationResult) {
        this.futureInformationResult = futureInformationResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FutureInformationResponse)) return false;
        FutureInformationResponse other = (FutureInformationResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.futureInformationResult==null && other.getFutureInformationResult()==null) || 
             (this.futureInformationResult!=null &&
              this.futureInformationResult.equals(other.getFutureInformationResult())));
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
        if (getFutureInformationResult() != null) {
            _hashCode += getFutureInformationResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FutureInformationResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">FutureInformationResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("futureInformationResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tsetmc.com/", "FutureInformationResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>FutureInformationResponse>FutureInformationResult"));
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
