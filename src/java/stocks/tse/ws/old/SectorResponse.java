/**
 * SectorResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package stocks.tse.ws.old;

public class SectorResponse  implements java.io.Serializable {
    private SectorResponseSectorResult sectorResult;

    public SectorResponse() {
    }

    public SectorResponse(
           SectorResponseSectorResult sectorResult) {
           this.sectorResult = sectorResult;
    }


    /**
     * Gets the sectorResult value for this SectorResponse.
     * 
     * @return sectorResult
     */
    public SectorResponseSectorResult getSectorResult() {
        return sectorResult;
    }


    /**
     * Sets the sectorResult value for this SectorResponse.
     * 
     * @param sectorResult
     */
    public void setSectorResult(SectorResponseSectorResult sectorResult) {
        this.sectorResult = sectorResult;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
    	if (obj == null) return false;
        if (!(obj instanceof SectorResponse)) return false;
        SectorResponse other = (SectorResponse) obj;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.sectorResult==null && other.getSectorResult()==null) || 
             (this.sectorResult!=null &&
              this.sectorResult.equals(other.getSectorResult())));
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
        if (getSectorResult() != null) {
            _hashCode += getSectorResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SectorResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">SectorResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sectorResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tsetmc.com/", "SectorResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>SectorResponse>SectorResult"));
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
