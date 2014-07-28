/**
 * IndexB2Response.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package stocks.tse.ws.old;

public class IndexB2Response  implements java.io.Serializable {
    private IndexB2ResponseIndexB2Result indexB2Result;

    public IndexB2Response() {
    }

    public IndexB2Response(
           IndexB2ResponseIndexB2Result indexB2Result) {
           this.indexB2Result = indexB2Result;
    }


    /**
     * Gets the indexB2Result value for this IndexB2Response.
     * 
     * @return indexB2Result
     */
    public IndexB2ResponseIndexB2Result getIndexB2Result() {
        return indexB2Result;
    }


    /**
     * Sets the indexB2Result value for this IndexB2Response.
     * 
     * @param indexB2Result
     */
    public void setIndexB2Result(IndexB2ResponseIndexB2Result indexB2Result) {
        this.indexB2Result = indexB2Result;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
    	if (obj == null) return false;
        if (!(obj instanceof IndexB2Response)) return false;
        IndexB2Response other = (IndexB2Response) obj;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.indexB2Result==null && other.getIndexB2Result()==null) || 
             (this.indexB2Result!=null &&
              this.indexB2Result.equals(other.getIndexB2Result())));
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
        if (getIndexB2Result() != null) {
            _hashCode += getIndexB2Result().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IndexB2Response.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">IndexB2Response"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indexB2Result");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tsetmc.com/", "IndexB2Result"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>IndexB2Response>IndexB2Result"));
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
