<?xml version="1.0" encoding="utf-8"?>
<wsdl:definitions xmlns:s="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://schemas.xmlsoap.org/wsdl/soap12/" xmlns:http="http://schemas.xmlsoap.org/wsdl/http/" xmlns:mime="http://schemas.xmlsoap.org/wsdl/mime/" xmlns:tns="http://tempuri.org/" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tm="http://microsoft.com/wsdl/mime/textMatching/" xmlns:soapenc="http://schemas.xmlsoap.org/soap/encoding/" targetNamespace="http://tempuri.org/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">
  <wsdl:types>
    <s:schema elementFormDefault="qualified" targetNamespace="http://tempuri.org/">
      <s:element name="GetContractsList">
        <s:complexType />
      </s:element>
      <s:element name="GetContractsListResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetContractsListResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetServersList">
        <s:complexType />
      </s:element>
      <s:element name="GetServersListResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetServersListResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetContractShortInfo">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="ContractCode" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetContractShortInfoResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetContractShortInfoResult" type="tns:Short_Future_Market_Data" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="Short_Future_Market_Data">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="ContractDescription" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="LastTradedPrice" type="s:double" />
          <s:element minOccurs="1" maxOccurs="1" name="C_LastTradedPriceChangesPercent" type="s:double" />
        </s:sequence>
      </s:complexType>
      <s:element name="GetContractInfo">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="ContractCode" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetContractInfoResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetContractInfoResult" type="tns:Future_Market_Data" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="Future_Market_Data">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="LastTradingDate" type="s:dateTime" />
          <s:element minOccurs="0" maxOccurs="1" name="PersianLastTradingDate" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="ContractCode" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="ContractDescription" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="ContractSize" type="s:double" />
          <s:element minOccurs="0" maxOccurs="1" name="ContractSizeUnitFaDesc" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="ContractSizeUnitEnDesc" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="ContractCurrencyFaDesc" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="ContractCurrencyEnDesc" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="ContractCurrencyDecimalPlaces" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="LastSettlementPrice" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="LastSettlementPriceDate" type="s:dateTime" />
          <s:element minOccurs="0" maxOccurs="1" name="PersianLastSettlementPriceDate" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="YesterdayOpenInterests" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="BidTotalVolume" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="BidVolume1" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="BidPrice1" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="BidVolume2" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="BidPrice2" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="BidVolume3" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="BidPrice3" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="BidVolume4" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="BidPrice4" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="BidVolume5" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="BidPrice5" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="AskTotalVolume" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="AskVolume1" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="AskPrice1" type="s:double" />
          <s:element minOccurs="1" maxOccurs="1" name="AskVolume2" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="AskPrice2" type="s:double" />
          <s:element minOccurs="1" maxOccurs="1" name="AskVolume3" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="AskPrice3" type="s:double" />
          <s:element minOccurs="1" maxOccurs="1" name="AskVolume4" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="AskPrice4" type="s:double" />
          <s:element minOccurs="1" maxOccurs="1" name="AskVolume5" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="AskPrice5" type="s:double" />
          <s:element minOccurs="1" maxOccurs="1" name="OrdersDateTime" type="s:dateTime" />
          <s:element minOccurs="0" maxOccurs="1" name="PersianOrdersDateTime" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="FirstTradedPrice" type="s:double" />
          <s:element minOccurs="1" maxOccurs="1" name="FirstTradedPriceTime" type="s:dateTime" />
          <s:element minOccurs="0" maxOccurs="1" name="PersianFirstTradedPriceTime" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="C_FirstTradedPriceChanges" type="s:double" />
          <s:element minOccurs="1" maxOccurs="1" name="C_FirstTradedPriceChangesPercent" type="s:double" />
          <s:element minOccurs="1" maxOccurs="1" name="LastTradedPrice" type="s:double" />
          <s:element minOccurs="1" maxOccurs="1" name="LastTradedPriceTime" type="s:dateTime" />
          <s:element minOccurs="0" maxOccurs="1" name="PersianLastTradedPriceTime" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="C_LastTradedPriceChanges" type="s:double" />
          <s:element minOccurs="1" maxOccurs="1" name="C_LastTradedPriceChangesPercent" type="s:double" />
          <s:element minOccurs="1" maxOccurs="1" name="HighTradedPrice" type="s:double" />
          <s:element minOccurs="1" maxOccurs="1" name="C_HighTradedPriceChanges" type="s:double" />
          <s:element minOccurs="1" maxOccurs="1" name="C_HighTradedPriceChangesPercent" type="s:double" />
          <s:element minOccurs="1" maxOccurs="1" name="LowTradedPrice" type="s:double" />
          <s:element minOccurs="1" maxOccurs="1" name="C_LowTradedPriceChanges" type="s:double" />
          <s:element minOccurs="1" maxOccurs="1" name="C_LowTradedPriceChangesPercent" type="s:double" />
          <s:element minOccurs="1" maxOccurs="1" name="AverageTradedPrice" type="s:double" />
          <s:element minOccurs="1" maxOccurs="1" name="OpeningPrice" type="s:double" />
          <s:element minOccurs="1" maxOccurs="1" name="ClosingPrice" type="s:double" />
          <s:element minOccurs="1" maxOccurs="1" name="TradesCount" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="TradesVolume" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="TradesValue" type="s:double" />
          <s:element minOccurs="0" maxOccurs="1" name="TradesValueCurrencyFaDesc" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="TradesValueCurrencyEnDesc" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="OpenInterests" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="C_OpenInterestsChanges" type="s:double" />
          <s:element minOccurs="1" maxOccurs="1" name="OpenInterestsChangesPercent" type="s:double" />
          <s:element minOccurs="1" maxOccurs="1" name="LastUpdate" type="s:dateTime" />
          <s:element minOccurs="0" maxOccurs="1" name="PersianLastUpdate" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="Expired" type="s:boolean" />
        </s:sequence>
      </s:complexType>
      <s:element name="Now">
        <s:complexType />
      </s:element>
      <s:element name="NowResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="NowResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetAtiTotalGrid">
        <s:complexType />
      </s:element>
      <s:element name="GetAtiTotalGridResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetAtiTotalGridResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
    </s:schema>
  </wsdl:types>
  <wsdl:message name="GetContractsListSoapIn">
    <wsdl:part name="parameters" element="tns:GetContractsList" />
  </wsdl:message>
  <wsdl:message name="GetContractsListSoapOut">
    <wsdl:part name="parameters" element="tns:GetContractsListResponse" />
  </wsdl:message>
  <wsdl:message name="GetServersListSoapIn">
    <wsdl:part name="parameters" element="tns:GetServersList" />
  </wsdl:message>
  <wsdl:message name="GetServersListSoapOut">
    <wsdl:part name="parameters" element="tns:GetServersListResponse" />
  </wsdl:message>
  <wsdl:message name="GetContractShortInfoSoapIn">
    <wsdl:part name="parameters" element="tns:GetContractShortInfo" />
  </wsdl:message>
  <wsdl:message name="GetContractShortInfoSoapOut">
    <wsdl:part name="parameters" element="tns:GetContractShortInfoResponse" />
  </wsdl:message>
  <wsdl:message name="GetContractInfoSoapIn">
    <wsdl:part name="parameters" element="tns:GetContractInfo" />
  </wsdl:message>
  <wsdl:message name="GetContractInfoSoapOut">
    <wsdl:part name="parameters" element="tns:GetContractInfoResponse" />
  </wsdl:message>
  <wsdl:message name="NowSoapIn">
    <wsdl:part name="parameters" element="tns:Now" />
  </wsdl:message>
  <wsdl:message name="NowSoapOut">
    <wsdl:part name="parameters" element="tns:NowResponse" />
  </wsdl:message>
  <wsdl:message name="GetAtiTotalGridSoapIn">
    <wsdl:part name="parameters" element="tns:GetAtiTotalGrid" />
  </wsdl:message>
  <wsdl:message name="GetAtiTotalGridSoapOut">
    <wsdl:part name="parameters" element="tns:GetAtiTotalGridResponse" />
  </wsdl:message>
  <wsdl:portType name="Fut_Live_Loc_ServiceSoap">
    <wsdl:operation name="GetContractsList">
      <wsdl:input message="tns:GetContractsListSoapIn" />
      <wsdl:output message="tns:GetContractsListSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetServersList">
      <wsdl:input message="tns:GetServersListSoapIn" />
      <wsdl:output message="tns:GetServersListSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetContractShortInfo">
      <wsdl:input message="tns:GetContractShortInfoSoapIn" />
      <wsdl:output message="tns:GetContractShortInfoSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetContractInfo">
      <wsdl:input message="tns:GetContractInfoSoapIn" />
      <wsdl:output message="tns:GetContractInfoSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="Now">
      <wsdl:input message="tns:NowSoapIn" />
      <wsdl:output message="tns:NowSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetAtiTotalGrid">
      <wsdl:input message="tns:GetAtiTotalGridSoapIn" />
      <wsdl:output message="tns:GetAtiTotalGridSoapOut" />
    </wsdl:operation>
  </wsdl:portType>
  <wsdl:binding name="Fut_Live_Loc_ServiceSoap" type="tns:Fut_Live_Loc_ServiceSoap">
    <soap:binding transport="http://schemas.xmlsoap.org/soap/http" />
    <wsdl:operation name="GetContractsList">
      <soap:operation soapAction="http://tempuri.org/GetContractsList" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetServersList">
      <soap:operation soapAction="http://tempuri.org/GetServersList" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetContractShortInfo">
      <soap:operation soapAction="http://tempuri.org/GetContractShortInfo" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetContractInfo">
      <soap:operation soapAction="http://tempuri.org/GetContractInfo" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Now">
      <soap:operation soapAction="http://tempuri.org/Now" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetAtiTotalGrid">
      <soap:operation soapAction="http://tempuri.org/GetAtiTotalGrid" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:binding name="Fut_Live_Loc_ServiceSoap12" type="tns:Fut_Live_Loc_ServiceSoap">
    <soap12:binding transport="http://schemas.xmlsoap.org/soap/http" />
    <wsdl:operation name="GetContractsList">
      <soap12:operation soapAction="http://tempuri.org/GetContractsList" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetServersList">
      <soap12:operation soapAction="http://tempuri.org/GetServersList" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetContractShortInfo">
      <soap12:operation soapAction="http://tempuri.org/GetContractShortInfo" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetContractInfo">
      <soap12:operation soapAction="http://tempuri.org/GetContractInfo" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Now">
      <soap12:operation soapAction="http://tempuri.org/Now" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetAtiTotalGrid">
      <soap12:operation soapAction="http://tempuri.org/GetAtiTotalGrid" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:service name="Fut_Live_Loc_Service">
    <wsdl:port name="Fut_Live_Loc_ServiceSoap" binding="tns:Fut_Live_Loc_ServiceSoap">
      <soap:address location="http://cdn.ime.co.ir/Services/Fut_Live_Loc_Service.asmx" />
    </wsdl:port>
    <wsdl:port name="Fut_Live_Loc_ServiceSoap12" binding="tns:Fut_Live_Loc_ServiceSoap12">
      <soap12:address location="http://cdn.ime.co.ir/Services/Fut_Live_Loc_Service.asmx" />
    </wsdl:port>
  </wsdl:service>
</wsdl:definitions>