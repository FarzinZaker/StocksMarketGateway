/**
 * SectorStateResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package stocks.tse.ws;

public class SectorStateResponse  implements java.io.Serializable {
    private stocks.tse.ws.SectorStateResponseSectorStateResult sectorStateResult;

    public SectorStateResponse() {
    }

    public SectorStateResponse(
           stocks.tse.ws.SectorStateResponseSectorStateResult sectorStateResult) {
           this.sectorStateResult = sectorStateResult;
    }


    /**
     * Gets the sectorStateResult value for this SectorStateResponse.
     * 
     * @return sectorStateResult
     */
    public stocks.tse.ws.SectorStateResponseSectorStateResult getSectorStateResult() {
        return sectorStateResult;
    }


    /**
     * Sets the sectorStateResult value for this SectorStateResponse.
     * 
     * @param sectorStateResult
     */
    public void setSectorStateResult(stocks.tse.ws.SectorStateResponseSectorStateResult sectorStateResult) {
        this.sectorStateResult = sectorStateResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SectorStateResponse)) return false;
        SectorStateResponse other = (SectorStateResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.sectorStateResult==null && other.getSectorStateResult()==null) || 
             (this.sectorStateResult!=null &&
              this.sectorStateResult.equals(other.getSectorStateResult())));
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
        if (getSectorStateResult() != null) {
            _hashCode += getSectorStateResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SectorStateResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">SectorStateResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sectorStateResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tsetmc.com/", "SectorStateResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>SectorStateResponse>SectorStateResult"));
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
