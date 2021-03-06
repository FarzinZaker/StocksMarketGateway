/**
 * AuctionResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package stocks.tse.ws;

public class AuctionResponse  implements java.io.Serializable {
    private stocks.tse.ws.AuctionResponseAuctionResult auctionResult;

    public AuctionResponse() {
    }

    public AuctionResponse(
           stocks.tse.ws.AuctionResponseAuctionResult auctionResult) {
           this.auctionResult = auctionResult;
    }


    /**
     * Gets the auctionResult value for this AuctionResponse.
     * 
     * @return auctionResult
     */
    public stocks.tse.ws.AuctionResponseAuctionResult getAuctionResult() {
        return auctionResult;
    }


    /**
     * Sets the auctionResult value for this AuctionResponse.
     * 
     * @param auctionResult
     */
    public void setAuctionResult(stocks.tse.ws.AuctionResponseAuctionResult auctionResult) {
        this.auctionResult = auctionResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AuctionResponse)) return false;
        AuctionResponse other = (AuctionResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.auctionResult==null && other.getAuctionResult()==null) || 
             (this.auctionResult!=null &&
              this.auctionResult.equals(other.getAuctionResult())));
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
        if (getAuctionResult() != null) {
            _hashCode += getAuctionResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AuctionResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">AuctionResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("auctionResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tsetmc.com/", "AuctionResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>AuctionResponse>AuctionResult"));
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
