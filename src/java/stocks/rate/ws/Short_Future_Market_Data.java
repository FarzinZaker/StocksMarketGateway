/**
 * Short_Future_Market_Data.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package stocks.rate.ws;

public class Short_Future_Market_Data  implements java.io.Serializable {
    private java.lang.String contractDescription;

    private double lastTradedPrice;

    private double c_LastTradedPriceChangesPercent;

    public Short_Future_Market_Data() {
    }

    public Short_Future_Market_Data(
           java.lang.String contractDescription,
           double lastTradedPrice,
           double c_LastTradedPriceChangesPercent) {
           this.contractDescription = contractDescription;
           this.lastTradedPrice = lastTradedPrice;
           this.c_LastTradedPriceChangesPercent = c_LastTradedPriceChangesPercent;
    }


    /**
     * Gets the contractDescription value for this Short_Future_Market_Data.
     * 
     * @return contractDescription
     */
    public java.lang.String getContractDescription() {
        return contractDescription;
    }


    /**
     * Sets the contractDescription value for this Short_Future_Market_Data.
     * 
     * @param contractDescription
     */
    public void setContractDescription(java.lang.String contractDescription) {
        this.contractDescription = contractDescription;
    }


    /**
     * Gets the lastTradedPrice value for this Short_Future_Market_Data.
     * 
     * @return lastTradedPrice
     */
    public double getLastTradedPrice() {
        return lastTradedPrice;
    }


    /**
     * Sets the lastTradedPrice value for this Short_Future_Market_Data.
     * 
     * @param lastTradedPrice
     */
    public void setLastTradedPrice(double lastTradedPrice) {
        this.lastTradedPrice = lastTradedPrice;
    }


    /**
     * Gets the c_LastTradedPriceChangesPercent value for this Short_Future_Market_Data.
     * 
     * @return c_LastTradedPriceChangesPercent
     */
    public double getC_LastTradedPriceChangesPercent() {
        return c_LastTradedPriceChangesPercent;
    }


    /**
     * Sets the c_LastTradedPriceChangesPercent value for this Short_Future_Market_Data.
     * 
     * @param c_LastTradedPriceChangesPercent
     */
    public void setC_LastTradedPriceChangesPercent(double c_LastTradedPriceChangesPercent) {
        this.c_LastTradedPriceChangesPercent = c_LastTradedPriceChangesPercent;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Short_Future_Market_Data)) return false;
        Short_Future_Market_Data other = (Short_Future_Market_Data) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.contractDescription==null && other.getContractDescription()==null) || 
             (this.contractDescription!=null &&
              this.contractDescription.equals(other.getContractDescription()))) &&
            this.lastTradedPrice == other.getLastTradedPrice() &&
            this.c_LastTradedPriceChangesPercent == other.getC_LastTradedPriceChangesPercent();
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
        if (getContractDescription() != null) {
            _hashCode += getContractDescription().hashCode();
        }
        _hashCode += new Double(getLastTradedPrice()).hashCode();
        _hashCode += new Double(getC_LastTradedPriceChangesPercent()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Short_Future_Market_Data.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "Short_Future_Market_Data"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contractDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ContractDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastTradedPrice");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "LastTradedPrice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("c_LastTradedPriceChangesPercent");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "C_LastTradedPriceChangesPercent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
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
