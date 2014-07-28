/**
 * InstTrade.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package stocks.tse.ws;

public class InstTrade  implements java.io.Serializable {
    private java.lang.String userName;

    private java.lang.String password;

    private long inscode;

    private int dateFrom;

    private int dateTo;

    public InstTrade() {
    }

    public InstTrade(
           java.lang.String userName,
           java.lang.String password,
           long inscode,
           int dateFrom,
           int dateTo) {
           this.userName = userName;
           this.password = password;
           this.inscode = inscode;
           this.dateFrom = dateFrom;
           this.dateTo = dateTo;
    }


    /**
     * Gets the userName value for this InstTrade.
     * 
     * @return userName
     */
    public java.lang.String getUserName() {
        return userName;
    }


    /**
     * Sets the userName value for this InstTrade.
     * 
     * @param userName
     */
    public void setUserName(java.lang.String userName) {
        this.userName = userName;
    }


    /**
     * Gets the password value for this InstTrade.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this InstTrade.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the inscode value for this InstTrade.
     * 
     * @return inscode
     */
    public long getInscode() {
        return inscode;
    }


    /**
     * Sets the inscode value for this InstTrade.
     * 
     * @param inscode
     */
    public void setInscode(long inscode) {
        this.inscode = inscode;
    }


    /**
     * Gets the dateFrom value for this InstTrade.
     * 
     * @return dateFrom
     */
    public int getDateFrom() {
        return dateFrom;
    }


    /**
     * Sets the dateFrom value for this InstTrade.
     * 
     * @param dateFrom
     */
    public void setDateFrom(int dateFrom) {
        this.dateFrom = dateFrom;
    }


    /**
     * Gets the dateTo value for this InstTrade.
     * 
     * @return dateTo
     */
    public int getDateTo() {
        return dateTo;
    }


    /**
     * Sets the dateTo value for this InstTrade.
     * 
     * @param dateTo
     */
    public void setDateTo(int dateTo) {
        this.dateTo = dateTo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InstTrade)) return false;
        InstTrade other = (InstTrade) obj;
        if (obj == null) return false;
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
            this.inscode == other.getInscode() &&
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
        _hashCode += new Long(getInscode()).hashCode();
        _hashCode += getDateFrom();
        _hashCode += getDateTo();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InstTrade.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">InstTrade"));
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
        elemField.setFieldName("inscode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tsetmc.com/", "Inscode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
