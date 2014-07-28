/**
 * IndexInstrumentResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package stocks.tse.ws;

public class IndexInstrumentResponse  implements java.io.Serializable {
    private stocks.tse.ws.IndexInstrumentResponseIndexInstrumentResult indexInstrumentResult;

    public IndexInstrumentResponse() {
    }

    public IndexInstrumentResponse(
           stocks.tse.ws.IndexInstrumentResponseIndexInstrumentResult indexInstrumentResult) {
           this.indexInstrumentResult = indexInstrumentResult;
    }


    /**
     * Gets the indexInstrumentResult value for this IndexInstrumentResponse.
     * 
     * @return indexInstrumentResult
     */
    public stocks.tse.ws.IndexInstrumentResponseIndexInstrumentResult getIndexInstrumentResult() {
        return indexInstrumentResult;
    }


    /**
     * Sets the indexInstrumentResult value for this IndexInstrumentResponse.
     * 
     * @param indexInstrumentResult
     */
    public void setIndexInstrumentResult(stocks.tse.ws.IndexInstrumentResponseIndexInstrumentResult indexInstrumentResult) {
        this.indexInstrumentResult = indexInstrumentResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IndexInstrumentResponse)) return false;
        IndexInstrumentResponse other = (IndexInstrumentResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.indexInstrumentResult==null && other.getIndexInstrumentResult()==null) || 
             (this.indexInstrumentResult!=null &&
              this.indexInstrumentResult.equals(other.getIndexInstrumentResult())));
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
        if (getIndexInstrumentResult() != null) {
            _hashCode += getIndexInstrumentResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IndexInstrumentResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">IndexInstrumentResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indexInstrumentResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tsetmc.com/", "IndexInstrumentResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>IndexInstrumentResponse>IndexInstrumentResult"));
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
