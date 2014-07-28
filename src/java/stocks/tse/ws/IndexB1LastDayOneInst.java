/**
 * IndexB1LastDayOneInst.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package stocks.tse.ws;

public class IndexB1LastDayOneInst  implements java.io.Serializable {
    private java.lang.String userName;

    private java.lang.String password;

    private long idxCode;

    private org.apache.axis.types.UnsignedByte flow;

    public IndexB1LastDayOneInst() {
    }

    public IndexB1LastDayOneInst(
           java.lang.String userName,
           java.lang.String password,
           long idxCode,
           org.apache.axis.types.UnsignedByte flow) {
           this.userName = userName;
           this.password = password;
           this.idxCode = idxCode;
           this.flow = flow;
    }


    /**
     * Gets the userName value for this IndexB1LastDayOneInst.
     * 
     * @return userName
     */
    public java.lang.String getUserName() {
        return userName;
    }


    /**
     * Sets the userName value for this IndexB1LastDayOneInst.
     * 
     * @param userName
     */
    public void setUserName(java.lang.String userName) {
        this.userName = userName;
    }


    /**
     * Gets the password value for this IndexB1LastDayOneInst.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this IndexB1LastDayOneInst.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the idxCode value for this IndexB1LastDayOneInst.
     * 
     * @return idxCode
     */
    public long getIdxCode() {
        return idxCode;
    }


    /**
     * Sets the idxCode value for this IndexB1LastDayOneInst.
     * 
     * @param idxCode
     */
    public void setIdxCode(long idxCode) {
        this.idxCode = idxCode;
    }


    /**
     * Gets the flow value for this IndexB1LastDayOneInst.
     * 
     * @return flow
     */
    public org.apache.axis.types.UnsignedByte getFlow() {
        return flow;
    }


    /**
     * Sets the flow value for this IndexB1LastDayOneInst.
     * 
     * @param flow
     */
    public void setFlow(org.apache.axis.types.UnsignedByte flow) {
        this.flow = flow;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IndexB1LastDayOneInst)) return false;
        IndexB1LastDayOneInst other = (IndexB1LastDayOneInst) obj;
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
            this.idxCode == other.getIdxCode() &&
            ((this.flow==null && other.getFlow()==null) || 
             (this.flow!=null &&
              this.flow.equals(other.getFlow())));
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
        _hashCode += new Long(getIdxCode()).hashCode();
        if (getFlow() != null) {
            _hashCode += getFlow().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IndexB1LastDayOneInst.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tsetmc.com/", ">IndexB1LastDayOneInst"));
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
        elemField.setFieldName("idxCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tsetmc.com/", "IdxCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flow");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tsetmc.com/", "Flow"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
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
