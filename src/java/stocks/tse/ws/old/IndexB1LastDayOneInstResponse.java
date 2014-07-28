/**
 * IndexB1LastDayOneInstResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package stocks.tse.ws.old;

public class IndexB1LastDayOneInstResponse  implements java.io.Serializable {
    private IndexB1LastDayOneInstResponseIndexB1LastDayOneInstResult indexB1LastDayOneInstResult;

    public IndexB1LastDayOneInstResponse() {
    }

    public IndexB1LastDayOneInstResponse(
           IndexB1LastDayOneInstResponseIndexB1LastDayOneInstResult indexB1LastDayOneInstResult) {
           this.indexB1LastDayOneInstResult = indexB1LastDayOneInstResult;
    }


    /**
     * Gets the indexB1LastDayOneInstResult value for this IndexB1LastDayOneInstResponse.
     * 
     * @return indexB1LastDayOneInstResult
     */
    public IndexB1LastDayOneInstResponseIndexB1LastDayOneInstResult getIndexB1LastDayOneInstResult() {
        return indexB1LastDayOneInstResult;
    }


    /**
     * Sets the indexB1LastDayOneInstResult value for this IndexB1LastDayOneInstResponse.
     * 
     * @param indexB1LastDayOneInstResult
     */
    public void setIndexB1LastDayOneInstResult(IndexB1LastDayOneInstResponseIndexB1LastDayOneInstResult indexB1LastDayOneInstResult) {
        this.indexB1LastDayOneInstResult = indexB1LastDayOneInstResult;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
    	if (obj == null) return false;
        if (!(obj instanceof IndexB1LastDayOneInstResponse)) return false;
        IndexB1LastDayOneInstResponse other = (IndexB1LastDayOneInstResponse) obj;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.indexB1LastDayOneInstResult==null && other.getIndexB1LastDayOneInstResult()==null) || 
             (this.indexB1LastDayOneInstResult!=null &&
              this.indexB1LastDayOneInstResult.equals(other.getIndexB1LastDayOneInstResult())));
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
        if (getIndexB1LastDayOneInstResult() != null) {
            _hashCode += getIndexB1LastDayOneInstResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IndexB1LastDayOneInstResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">IndexB1LastDayOneInstResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indexB1LastDayOneInstResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tsetmc.com/", "IndexB1LastDayOneInstResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>IndexB1LastDayOneInstResponse>IndexB1LastDayOneInstResult"));
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
