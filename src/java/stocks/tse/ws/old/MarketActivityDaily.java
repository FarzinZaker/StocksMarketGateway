/**
 * MarketActivityDaily.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package stocks.tse.ws.old;

public class MarketActivityDaily  implements java.io.Serializable {
    private String userName;

    private String password;

    private int dateFrom;

    private int dateTo;

    public MarketActivityDaily() {
    }

    public MarketActivityDaily(
           String userName,
           String password,
           int dateFrom,
           int dateTo) {
           this.userName = userName;
           this.password = password;
           this.dateFrom = dateFrom;
           this.dateTo = dateTo;
    }


    /**
     * Gets the userName value for this MarketActivityDaily.
     * 
     * @return userName
     */
    public String getUserName() {
        return userName;
    }


    /**
     * Sets the userName value for this MarketActivityDaily.
     * 
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }


    /**
     * Gets the password value for this MarketActivityDaily.
     * 
     * @return password
     */
    public String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this MarketActivityDaily.
     * 
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * Gets the dateFrom value for this MarketActivityDaily.
     * 
     * @return dateFrom
     */
    public int getDateFrom() {
        return dateFrom;
    }


    /**
     * Sets the dateFrom value for this MarketActivityDaily.
     * 
     * @param dateFrom
     */
    public void setDateFrom(int dateFrom) {
        this.dateFrom = dateFrom;
    }


    /**
     * Gets the dateTo value for this MarketActivityDaily.
     * 
     * @return dateTo
     */
    public int getDateTo() {
        return dateTo;
    }


    /**
     * Sets the dateTo value for this MarketActivityDaily.
     * 
     * @param dateTo
     */
    public void setDateTo(int dateTo) {
        this.dateTo = dateTo;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
    	if (obj == null) return false;
        if (!(obj instanceof MarketActivityDaily)) return false;
        MarketActivityDaily other = (MarketActivityDaily) obj;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.userName==null && other.getUserName()==null) || 
             (this.userName!=null &&
              this.userName.equals(other.getUserName()))) &&
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            this.dateFrom == other.getDateFrom() &&
            this.dateTo == other.getDateTo();
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
        if (getUserName() != null) {
            _hashCode += getUserName().hashCode();
        }
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        _hashCode += getDateFrom();
        _hashCode += getDateTo();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MarketActivityDaily.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">MarketActivityDaily"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("password");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateFrom");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tsetmc.com/", "DateFrom"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateTo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tsetmc.com/", "DateTo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
