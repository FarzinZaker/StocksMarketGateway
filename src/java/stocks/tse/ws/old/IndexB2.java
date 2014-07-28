/**
 * IndexB2.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package stocks.tse.ws.old;

public class IndexB2  implements java.io.Serializable {
    private String userName;

    private String password;

    private int DEven;

    public IndexB2() {
    }

    public IndexB2(
           String userName,
           String password,
           int DEven) {
           this.userName = userName;
           this.password = password;
           this.DEven = DEven;
    }


    /**
     * Gets the userName value for this IndexB2.
     * 
     * @return userName
     */
    public String getUserName() {
        return userName;
    }


    /**
     * Sets the userName value for this IndexB2.
     * 
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }


    /**
     * Gets the password value for this IndexB2.
     * 
     * @return password
     */
    public String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this IndexB2.
     * 
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * Gets the DEven value for this IndexB2.
     * 
     * @return DEven
     */
    public int getDEven() {
        return DEven;
    }


    /**
     * Sets the DEven value for this IndexB2.
     * 
     * @param DEven
     */
    public void setDEven(int DEven) {
        this.DEven = DEven;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
    	if (obj == null) return false;
        if (!(obj instanceof IndexB2)) return false;
        IndexB2 other = (IndexB2) obj;
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
            this.DEven == other.getDEven();
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
        _hashCode += getDEven();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IndexB2.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">IndexB2"));
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
        elemField.setFieldName("DEven");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tsetmc.com/", "DEven"));
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
