<?xml version="1.0" encoding="utf-8"?>
<wsdl:definitions xmlns:s="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://schemas.xmlsoap.org/wsdl/soap12/" xmlns:mime="http://schemas.xmlsoap.org/wsdl/mime/" xmlns:tns="http://tsetmc.com/" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tm="http://microsoft.com/wsdl/mime/textMatching/" xmlns:http="http://schemas.xmlsoap.org/wsdl/http/" xmlns:soapenc="http://schemas.xmlsoap.org/soap/encoding/" targetNamespace="http://tsetmc.com/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">
  <wsdl:types>
    <s:schema elementFormDefault="qualified" targetNamespace="http://tsetmc.com/">
      <s:element name="ClientType">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="ClientTypeResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="ClientTypeResult">
              <s:complexType>
                <s:sequence>
                  <s:any minOccurs="0" maxOccurs="unbounded" namespace="http://www.w3.org/2001/XMLSchema" processContents="lax" />
                  <s:any minOccurs="1" namespace="urn:schemas-microsoft-com:xml-diffgram-v1" processContents="lax" />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="Auction">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="from" type="s:long" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="AuctionResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="AuctionResult">
              <s:complexType>
                <s:sequence>
                  <s:any minOccurs="0" maxOccurs="unbounded" namespace="http://www.w3.org/2001/XMLSchema" processContents="lax" />
                  <s:any minOccurs="1" namespace="urn:schemas-microsoft-com:xml-diffgram-v1" processContents="lax" />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="Board">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="BoardResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="BoardResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="Sector">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="SectorResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="SectorResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="SubSector">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="SubSectorResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="SubSectorResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="TradeLastDay">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="Flow" type="s:unsignedByte" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="TradeLastDayResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="TradeLastDayResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="TradeOneDay">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="SelDate" type="s:int" />
            <s:element minOccurs="1" maxOccurs="1" name="Flow" type="s:unsignedByte" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="TradeOneDayResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="TradeOneDayResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="TradeOneDayAll">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="SelDate" type="s:int" />
            <s:element minOccurs="1" maxOccurs="1" name="Flow" type="s:unsignedByte" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="TradeOneDayAllResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="TradeOneDayAllResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="InstTrade">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="Inscode" type="s:long" />
            <s:element minOccurs="1" maxOccurs="1" name="DateFrom" type="s:int" />
            <s:element minOccurs="1" maxOccurs="1" name="DateTo" type="s:int" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="InstTradeResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="InstTradeResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="MarketActivityDaily">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="DateFrom" type="s:int" />
            <s:element minOccurs="1" maxOccurs="1" name="DateTo" type="s:int" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="MarketActivityDailyResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="MarketActivityDailyResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="BestLimitsAllIns">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="Flow" type="s:unsignedByte" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="BestLimitsAllInsResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="BestLimitsAllInsResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="BestLimitOneIns">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="InsCode" type="s:long" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="BestLimitOneInsResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="BestLimitOneInsResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="InstWithBestLimit">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="Flow" type="s:unsignedByte" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="InstWithBestLimitResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="InstWithBestLimitResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="Instrument">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="Flow" type="s:unsignedByte" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="InstrumentResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="InstrumentResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="InstrumentFilterByDate">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="DEven" type="s:int" />
            <s:element minOccurs="1" maxOccurs="1" name="Flow" type="s:unsignedByte" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="InstrumentFilterByDateResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="InstrumentFilterByDateResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="Company">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="Flow" type="s:unsignedByte" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="CompanyResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="CompanyResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="InstrumentsState">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="Flow" type="s:unsignedByte" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="InstrumentsStateResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="InstrumentsStateResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="IndexB1LastDayLastData">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="Flow" type="s:unsignedByte" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="IndexB1LastDayLastDataResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="IndexB1LastDayLastDataResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="IndexB1LastDayOneInst">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="IdxCode" type="s:long" />
            <s:element minOccurs="1" maxOccurs="1" name="Flow" type="s:unsignedByte" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="IndexB1LastDayOneInstResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="IndexB1LastDayOneInstResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="IndexB2">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="DEven" type="s:int" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="IndexB2Response">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="IndexB2Result">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="IndexInstrument">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="IdxCode" type="s:long" />
            <s:element minOccurs="1" maxOccurs="1" name="Flow" type="s:unsignedByte" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="IndexInstrumentResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="IndexInstrumentResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="InstAffect">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="InstAffectResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="InstAffectResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="TOP">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="Flow" type="s:unsignedByte" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="TOPResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="TOPResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="Msg">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="MsgResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="MsgResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="StaticThresholds">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="Flow" type="s:unsignedByte" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="StaticThresholdsResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="StaticThresholdsResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="MarketValue">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="MarketValueResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="MarketValueResult" type="s:decimal" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="MarketValueByDate">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="DEven" type="s:int" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="MarketValueByDateResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="MarketValueByDateResult" type="s:decimal" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="FutureInformation">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="DEven" type="s:int" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="FutureInformationResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="FutureInformationResult">
              <s:complexType>
                <s:sequence>
                  <s:any minOccurs="0" maxOccurs="unbounded" namespace="http://www.w3.org/2001/XMLSchema" processContents="lax" />
                  <s:any minOccurs="1" namespace="urn:schemas-microsoft-com:xml-diffgram-v1" processContents="lax" />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="AdjPrice">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="Flow" type="s:unsignedByte" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="AdjPriceResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="AdjPriceResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="ShareChange">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="Flow" type="s:unsignedByte" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="ShareChangeResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="ShareChangeResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="MarketActivityLast">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="Flow" type="s:unsignedByte" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="MarketActivityLastResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="MarketActivityLastResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="SectorState">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="DEven" type="s:int" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="SectorStateResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="SectorStateResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="NSCStart">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="NSCStartResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="NSCStartResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="PowerInstrument">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="PowerInstrumentResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="PowerInstrumentResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="PowerInstrumentHistory">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="From" type="s:long" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="PowerInstrumentHistoryResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="PowerInstrumentHistoryResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
    </s:schema>
  </wsdl:types>
  <wsdl:message name="ClientTypeSoapIn">
    <wsdl:part name="parameters" element="tns:ClientType" />
  </wsdl:message>
  <wsdl:message name="ClientTypeSoapOut">
    <wsdl:part name="parameters" element="tns:ClientTypeResponse" />
  </wsdl:message>
  <wsdl:message name="AuctionSoapIn">
    <wsdl:part name="parameters" element="tns:Auction" />
  </wsdl:message>
  <wsdl:message name="AuctionSoapOut">
    <wsdl:part name="parameters" element="tns:AuctionResponse" />
  </wsdl:message>
  <wsdl:message name="BoardSoapIn">
    <wsdl:part name="parameters" element="tns:Board" />
  </wsdl:message>
  <wsdl:message name="BoardSoapOut">
    <wsdl:part name="parameters" element="tns:BoardResponse" />
  </wsdl:message>
  <wsdl:message name="SectorSoapIn">
    <wsdl:part name="parameters" element="tns:Sector" />
  </wsdl:message>
  <wsdl:message name="SectorSoapOut">
    <wsdl:part name="parameters" element="tns:SectorResponse" />
  </wsdl:message>
  <wsdl:message name="SubSectorSoapIn">
    <wsdl:part name="parameters" element="tns:SubSector" />
  </wsdl:message>
  <wsdl:message name="SubSectorSoapOut">
    <wsdl:part name="parameters" element="tns:SubSectorResponse" />
  </wsdl:message>
  <wsdl:message name="TradeLastDaySoapIn">
    <wsdl:part name="parameters" element="tns:TradeLastDay" />
  </wsdl:message>
  <wsdl:message name="TradeLastDaySoapOut">
    <wsdl:part name="parameters" element="tns:TradeLastDayResponse" />
  </wsdl:message>
  <wsdl:message name="TradeOneDaySoapIn">
    <wsdl:part name="parameters" element="tns:TradeOneDay" />
  </wsdl:message>
  <wsdl:message name="TradeOneDaySoapOut">
    <wsdl:part name="parameters" element="tns:TradeOneDayResponse" />
  </wsdl:message>
  <wsdl:message name="TradeOneDayAllSoapIn">
    <wsdl:part name="parameters" element="tns:TradeOneDayAll" />
  </wsdl:message>
  <wsdl:message name="TradeOneDayAllSoapOut">
    <wsdl:part name="parameters" element="tns:TradeOneDayAllResponse" />
  </wsdl:message>
  <wsdl:message name="InstTradeSoapIn">
    <wsdl:part name="parameters" element="tns:InstTrade" />
  </wsdl:message>
  <wsdl:message name="InstTradeSoapOut">
    <wsdl:part name="parameters" element="tns:InstTradeResponse" />
  </wsdl:message>
  <wsdl:message name="MarketActivityDailySoapIn">
    <wsdl:part name="parameters" element="tns:MarketActivityDaily" />
  </wsdl:message>
  <wsdl:message name="MarketActivityDailySoapOut">
    <wsdl:part name="parameters" element="tns:MarketActivityDailyResponse" />
  </wsdl:message>
  <wsdl:message name="BestLimitsAllInsSoapIn">
    <wsdl:part name="parameters" element="tns:BestLimitsAllIns" />
  </wsdl:message>
  <wsdl:message name="BestLimitsAllInsSoapOut">
    <wsdl:part name="parameters" element="tns:BestLimitsAllInsResponse" />
  </wsdl:message>
  <wsdl:message name="BestLimitOneInsSoapIn">
    <wsdl:part name="parameters" element="tns:BestLimitOneIns" />
  </wsdl:message>
  <wsdl:message name="BestLimitOneInsSoapOut">
    <wsdl:part name="parameters" element="tns:BestLimitOneInsResponse" />
  </wsdl:message>
  <wsdl:message name="InstWithBestLimitSoapIn">
    <wsdl:part name="parameters" element="tns:InstWithBestLimit" />
  </wsdl:message>
  <wsdl:message name="InstWithBestLimitSoapOut">
    <wsdl:part name="parameters" element="tns:InstWithBestLimitResponse" />
  </wsdl:message>
  <wsdl:message name="InstrumentSoapIn">
    <wsdl:part name="parameters" element="tns:Instrument" />
  </wsdl:message>
  <wsdl:message name="InstrumentSoapOut">
    <wsdl:part name="parameters" element="tns:InstrumentResponse" />
  </wsdl:message>
  <wsdl:message name="InstrumentFilterByDateSoapIn">
    <wsdl:part name="parameters" element="tns:InstrumentFilterByDate" />
  </wsdl:message>
  <wsdl:message name="InstrumentFilterByDateSoapOut">
    <wsdl:part name="parameters" element="tns:InstrumentFilterByDateResponse" />
  </wsdl:message>
  <wsdl:message name="CompanySoapIn">
    <wsdl:part name="parameters" element="tns:Company" />
  </wsdl:message>
  <wsdl:message name="CompanySoapOut">
    <wsdl:part name="parameters" element="tns:CompanyResponse" />
  </wsdl:message>
  <wsdl:message name="InstrumentsStateSoapIn">
    <wsdl:part name="parameters" element="tns:InstrumentsState" />
  </wsdl:message>
  <wsdl:message name="InstrumentsStateSoapOut">
    <wsdl:part name="parameters" element="tns:InstrumentsStateResponse" />
  </wsdl:message>
  <wsdl:message name="IndexB1LastDayLastDataSoapIn">
    <wsdl:part name="parameters" element="tns:IndexB1LastDayLastData" />
  </wsdl:message>
  <wsdl:message name="IndexB1LastDayLastDataSoapOut">
    <wsdl:part name="parameters" element="tns:IndexB1LastDayLastDataResponse" />
  </wsdl:message>
  <wsdl:message name="IndexB1LastDayOneInstSoapIn">
    <wsdl:part name="parameters" element="tns:IndexB1LastDayOneInst" />
  </wsdl:message>
  <wsdl:message name="IndexB1LastDayOneInstSoapOut">
    <wsdl:part name="parameters" element="tns:IndexB1LastDayOneInstResponse" />
  </wsdl:message>
  <wsdl:message name="IndexB2SoapIn">
    <wsdl:part name="parameters" element="tns:IndexB2" />
  </wsdl:message>
  <wsdl:message name="IndexB2SoapOut">
    <wsdl:part name="parameters" element="tns:IndexB2Response" />
  </wsdl:message>
  <wsdl:message name="IndexInstrumentSoapIn">
    <wsdl:part name="parameters" element="tns:IndexInstrument" />
  </wsdl:message>
  <wsdl:message name="IndexInstrumentSoapOut">
    <wsdl:part name="parameters" element="tns:IndexInstrumentResponse" />
  </wsdl:message>
  <wsdl:message name="InstAffectSoapIn">
    <wsdl:part name="parameters" element="tns:InstAffect" />
  </wsdl:message>
  <wsdl:message name="InstAffectSoapOut">
    <wsdl:part name="parameters" element="tns:InstAffectResponse" />
  </wsdl:message>
  <wsdl:message name="TOPSoapIn">
    <wsdl:part name="parameters" element="tns:TOP" />
  </wsdl:message>
  <wsdl:message name="TOPSoapOut">
    <wsdl:part name="parameters" element="tns:TOPResponse" />
  </wsdl:message>
  <wsdl:message name="MsgSoapIn">
    <wsdl:part name="parameters" element="tns:Msg" />
  </wsdl:message>
  <wsdl:message name="MsgSoapOut">
    <wsdl:part name="parameters" element="tns:MsgResponse" />
  </wsdl:message>
  <wsdl:message name="StaticThresholdsSoapIn">
    <wsdl:part name="parameters" element="tns:StaticThresholds" />
  </wsdl:message>
  <wsdl:message name="StaticThresholdsSoapOut">
    <wsdl:part name="parameters" element="tns:StaticThresholdsResponse" />
  </wsdl:message>
  <wsdl:message name="MarketValueSoapIn">
    <wsdl:part name="parameters" element="tns:MarketValue" />
  </wsdl:message>
  <wsdl:message name="MarketValueSoapOut">
    <wsdl:part name="parameters" element="tns:MarketValueResponse" />
  </wsdl:message>
  <wsdl:message name="MarketValueByDateSoapIn">
    <wsdl:part name="parameters" element="tns:MarketValueByDate" />
  </wsdl:message>
  <wsdl:message name="MarketValueByDateSoapOut">
    <wsdl:part name="parameters" element="tns:MarketValueByDateResponse" />
  </wsdl:message>
  <wsdl:message name="FutureInformationSoapIn">
    <wsdl:part name="parameters" element="tns:FutureInformation" />
  </wsdl:message>
  <wsdl:message name="FutureInformationSoapOut">
    <wsdl:part name="parameters" element="tns:FutureInformationResponse" />
  </wsdl:message>
  <wsdl:message name="AdjPriceSoapIn">
    <wsdl:part name="parameters" element="tns:AdjPrice" />
  </wsdl:message>
  <wsdl:message name="AdjPriceSoapOut">
    <wsdl:part name="parameters" element="tns:AdjPriceResponse" />
  </wsdl:message>
  <wsdl:message name="ShareChangeSoapIn">
    <wsdl:part name="parameters" element="tns:ShareChange" />
  </wsdl:message>
  <wsdl:message name="ShareChangeSoapOut">
    <wsdl:part name="parameters" element="tns:ShareChangeResponse" />
  </wsdl:message>
  <wsdl:message name="MarketActivityLastSoapIn">
    <wsdl:part name="parameters" element="tns:MarketActivityLast" />
  </wsdl:message>
  <wsdl:message name="MarketActivityLastSoapOut">
    <wsdl:part name="parameters" element="tns:MarketActivityLastResponse" />
  </wsdl:message>
  <wsdl:message name="SectorStateSoapIn">
    <wsdl:part name="parameters" element="tns:SectorState" />
  </wsdl:message>
  <wsdl:message name="SectorStateSoapOut">
    <wsdl:part name="parameters" element="tns:SectorStateResponse" />
  </wsdl:message>
  <wsdl:message name="NSCStartSoapIn">
    <wsdl:part name="parameters" element="tns:NSCStart" />
  </wsdl:message>
  <wsdl:message name="NSCStartSoapOut">
    <wsdl:part name="parameters" element="tns:NSCStartResponse" />
  </wsdl:message>
  <wsdl:message name="PowerInstrumentSoapIn">
    <wsdl:part name="parameters" element="tns:PowerInstrument" />
  </wsdl:message>
  <wsdl:message name="PowerInstrumentSoapOut">
    <wsdl:part name="parameters" element="tns:PowerInstrumentResponse" />
  </wsdl:message>
  <wsdl:message name="PowerInstrumentHistorySoapIn">
    <wsdl:part name="parameters" element="tns:PowerInstrumentHistory" />
  </wsdl:message>
  <wsdl:message name="PowerInstrumentHistorySoapOut">
    <wsdl:part name="parameters" element="tns:PowerInstrumentHistoryResponse" />
  </wsdl:message>
  <wsdl:portType name="TsePublicV2Soap">
    <wsdl:operation name="ClientType">
      <wsdl:input message="tns:ClientTypeSoapIn" />
      <wsdl:output message="tns:ClientTypeSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="Auction">
      <wsdl:input message="tns:AuctionSoapIn" />
      <wsdl:output message="tns:AuctionSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="Board">
      <wsdl:input message="tns:BoardSoapIn" />
      <wsdl:output message="tns:BoardSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="Sector">
      <wsdl:input message="tns:SectorSoapIn" />
      <wsdl:output message="tns:SectorSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="SubSector">
      <wsdl:input message="tns:SubSectorSoapIn" />
      <wsdl:output message="tns:SubSectorSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="TradeLastDay">
      <wsdl:input message="tns:TradeLastDaySoapIn" />
      <wsdl:output message="tns:TradeLastDaySoapOut" />
    </wsdl:operation>
    <wsdl:operation name="TradeOneDay">
      <wsdl:input message="tns:TradeOneDaySoapIn" />
      <wsdl:output message="tns:TradeOneDaySoapOut" />
    </wsdl:operation>
    <wsdl:operation name="TradeOneDayAll">
      <wsdl:input message="tns:TradeOneDayAllSoapIn" />
      <wsdl:output message="tns:TradeOneDayAllSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="InstTrade">
      <wsdl:input message="tns:InstTradeSoapIn" />
      <wsdl:output message="tns:InstTradeSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="MarketActivityDaily">
      <wsdl:input message="tns:MarketActivityDailySoapIn" />
      <wsdl:output message="tns:MarketActivityDailySoapOut" />
    </wsdl:operation>
    <wsdl:operation name="BestLimitsAllIns">
      <wsdl:input message="tns:BestLimitsAllInsSoapIn" />
      <wsdl:output message="tns:BestLimitsAllInsSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="BestLimitOneIns">
      <wsdl:input message="tns:BestLimitOneInsSoapIn" />
      <wsdl:output message="tns:BestLimitOneInsSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="InstWithBestLimit">
      <wsdl:input message="tns:InstWithBestLimitSoapIn" />
      <wsdl:output message="tns:InstWithBestLimitSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="Instrument">
      <wsdl:input message="tns:InstrumentSoapIn" />
      <wsdl:output message="tns:InstrumentSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="InstrumentFilterByDate">
      <wsdl:input message="tns:InstrumentFilterByDateSoapIn" />
      <wsdl:output message="tns:InstrumentFilterByDateSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="Company">
      <wsdl:input message="tns:CompanySoapIn" />
      <wsdl:output message="tns:CompanySoapOut" />
    </wsdl:operation>
    <wsdl:operation name="InstrumentsState">
      <wsdl:input message="tns:InstrumentsStateSoapIn" />
      <wsdl:output message="tns:InstrumentsStateSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="IndexB1LastDayLastData">
      <wsdl:input message="tns:IndexB1LastDayLastDataSoapIn" />
      <wsdl:output message="tns:IndexB1LastDayLastDataSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="IndexB1LastDayOneInst">
      <wsdl:input message="tns:IndexB1LastDayOneInstSoapIn" />
      <wsdl:output message="tns:IndexB1LastDayOneInstSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="IndexB2">
      <wsdl:input message="tns:IndexB2SoapIn" />
      <wsdl:output message="tns:IndexB2SoapOut" />
    </wsdl:operation>
    <wsdl:operation name="IndexInstrument">
      <wsdl:input message="tns:IndexInstrumentSoapIn" />
      <wsdl:output message="tns:IndexInstrumentSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="InstAffect">
      <wsdl:input message="tns:InstAffectSoapIn" />
      <wsdl:output message="tns:InstAffectSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="TOP">
      <wsdl:input message="tns:TOPSoapIn" />
      <wsdl:output message="tns:TOPSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="Msg">
      <wsdl:input message="tns:MsgSoapIn" />
      <wsdl:output message="tns:MsgSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="StaticThresholds">
      <wsdl:input message="tns:StaticThresholdsSoapIn" />
      <wsdl:output message="tns:StaticThresholdsSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="MarketValue">
      <wsdl:input message="tns:MarketValueSoapIn" />
      <wsdl:output message="tns:MarketValueSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="MarketValueByDate">
      <wsdl:input message="tns:MarketValueByDateSoapIn" />
      <wsdl:output message="tns:MarketValueByDateSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="FutureInformation">
      <wsdl:input message="tns:FutureInformationSoapIn" />
      <wsdl:output message="tns:FutureInformationSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="AdjPrice">
      <wsdl:input message="tns:AdjPriceSoapIn" />
      <wsdl:output message="tns:AdjPriceSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="ShareChange">
      <wsdl:input message="tns:ShareChangeSoapIn" />
      <wsdl:output message="tns:ShareChangeSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="MarketActivityLast">
      <wsdl:input message="tns:MarketActivityLastSoapIn" />
      <wsdl:output message="tns:MarketActivityLastSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="SectorState">
      <wsdl:input message="tns:SectorStateSoapIn" />
      <wsdl:output message="tns:SectorStateSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="NSCStart">
      <wsdl:input message="tns:NSCStartSoapIn" />
      <wsdl:output message="tns:NSCStartSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="PowerInstrument">
      <wsdl:input message="tns:PowerInstrumentSoapIn" />
      <wsdl:output message="tns:PowerInstrumentSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="PowerInstrumentHistory">
      <wsdl:input message="tns:PowerInstrumentHistorySoapIn" />
      <wsdl:output message="tns:PowerInstrumentHistorySoapOut" />
    </wsdl:operation>
  </wsdl:portType>
  <wsdl:binding name="TsePublicV2Soap" type="tns:TsePublicV2Soap">
    <soap:binding transport="http://schemas.xmlsoap.org/soap/http" />
    <wsdl:operation name="ClientType">
      <soap:operation soapAction="http://tsetmc.com/ClientType" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Auction">
      <soap:operation soapAction="http://tsetmc.com/Auction" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Board">
      <soap:operation soapAction="http://tsetmc.com/Board" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Sector">
      <soap:operation soapAction="http://tsetmc.com/Sector" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SubSector">
      <soap:operation soapAction="http://tsetmc.com/SubSector" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="TradeLastDay">
      <soap:operation soapAction="http://tsetmc.com/TradeLastDay" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="TradeOneDay">
      <soap:operation soapAction="http://tsetmc.com/TradeOneDay" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="TradeOneDayAll">
      <soap:operation soapAction="http://tsetmc.com/TradeOneDayAll" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="InstTrade">
      <soap:operation soapAction="http://tsetmc.com/InstTrade" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="MarketActivityDaily">
      <soap:operation soapAction="http://tsetmc.com/MarketActivityDaily" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="BestLimitsAllIns">
      <soap:operation soapAction="http://tsetmc.com/BestLimitsAllIns" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="BestLimitOneIns">
      <soap:operation soapAction="http://tsetmc.com/BestLimitOneIns" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="InstWithBestLimit">
      <soap:operation soapAction="http://tsetmc.com/InstWithBestLimit" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Instrument">
      <soap:operation soapAction="http://tsetmc.com/Instrument" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="InstrumentFilterByDate">
      <soap:operation soapAction="http://tsetmc.com/InstrumentFilterByDate" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Company">
      <soap:operation soapAction="http://tsetmc.com/Company" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="InstrumentsState">
      <soap:operation soapAction="http://tsetmc.com/InstrumentsState" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="IndexB1LastDayLastData">
      <soap:operation soapAction="http://tsetmc.com/IndexB1LastDayLastData" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="IndexB1LastDayOneInst">
      <soap:operation soapAction="http://tsetmc.com/IndexB1LastDayOneInst" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="IndexB2">
      <soap:operation soapAction="http://tsetmc.com/IndexB2" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="IndexInstrument">
      <soap:operation soapAction="http://tsetmc.com/IndexInstrument" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="InstAffect">
      <soap:operation soapAction="http://tsetmc.com/InstAffect" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="TOP">
      <soap:operation soapAction="http://tsetmc.com/TOP" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Msg">
      <soap:operation soapAction="http://tsetmc.com/Msg" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="StaticThresholds">
      <soap:operation soapAction="http://tsetmc.com/StaticThresholds" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="MarketValue">
      <soap:operation soapAction="http://tsetmc.com/MarketValue" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="MarketValueByDate">
      <soap:operation soapAction="http://tsetmc.com/MarketValueByDate" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="FutureInformation">
      <soap:operation soapAction="http://tsetmc.com/FutureInformation" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="AdjPrice">
      <soap:operation soapAction="http://tsetmc.com/AdjPrice" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="ShareChange">
      <soap:operation soapAction="http://tsetmc.com/ShareChange" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="MarketActivityLast">
      <soap:operation soapAction="http://tsetmc.com/MarketActivityLast" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SectorState">
      <soap:operation soapAction="http://tsetmc.com/SectorState" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="NSCStart">
      <soap:operation soapAction="http://tsetmc.com/NSCStart" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="PowerInstrument">
      <soap:operation soapAction="http://tsetmc.com/PowerInstrument" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="PowerInstrumentHistory">
      <soap:operation soapAction="http://tsetmc.com/PowerInstrumentHistory" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:binding name="TsePublicV2Soap12" type="tns:TsePublicV2Soap">
    <soap12:binding transport="http://schemas.xmlsoap.org/soap/http" />
    <wsdl:operation name="ClientType">
      <soap12:operation soapAction="http://tsetmc.com/ClientType" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Auction">
      <soap12:operation soapAction="http://tsetmc.com/Auction" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Board">
      <soap12:operation soapAction="http://tsetmc.com/Board" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Sector">
      <soap12:operation soapAction="http://tsetmc.com/Sector" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SubSector">
      <soap12:operation soapAction="http://tsetmc.com/SubSector" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="TradeLastDay">
      <soap12:operation soapAction="http://tsetmc.com/TradeLastDay" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="TradeOneDay">
      <soap12:operation soapAction="http://tsetmc.com/TradeOneDay" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="TradeOneDayAll">
      <soap12:operation soapAction="http://tsetmc.com/TradeOneDayAll" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="InstTrade">
      <soap12:operation soapAction="http://tsetmc.com/InstTrade" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="MarketActivityDaily">
      <soap12:operation soapAction="http://tsetmc.com/MarketActivityDaily" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="BestLimitsAllIns">
      <soap12:operation soapAction="http://tsetmc.com/BestLimitsAllIns" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="BestLimitOneIns">
      <soap12:operation soapAction="http://tsetmc.com/BestLimitOneIns" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="InstWithBestLimit">
      <soap12:operation soapAction="http://tsetmc.com/InstWithBestLimit" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Instrument">
      <soap12:operation soapAction="http://tsetmc.com/Instrument" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="InstrumentFilterByDate">
      <soap12:operation soapAction="http://tsetmc.com/InstrumentFilterByDate" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Company">
      <soap12:operation soapAction="http://tsetmc.com/Company" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="InstrumentsState">
      <soap12:operation soapAction="http://tsetmc.com/InstrumentsState" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="IndexB1LastDayLastData">
      <soap12:operation soapAction="http://tsetmc.com/IndexB1LastDayLastData" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="IndexB1LastDayOneInst">
      <soap12:operation soapAction="http://tsetmc.com/IndexB1LastDayOneInst" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="IndexB2">
      <soap12:operation soapAction="http://tsetmc.com/IndexB2" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="IndexInstrument">
      <soap12:operation soapAction="http://tsetmc.com/IndexInstrument" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="InstAffect">
      <soap12:operation soapAction="http://tsetmc.com/InstAffect" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="TOP">
      <soap12:operation soapAction="http://tsetmc.com/TOP" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Msg">
      <soap12:operation soapAction="http://tsetmc.com/Msg" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="StaticThresholds">
      <soap12:operation soapAction="http://tsetmc.com/StaticThresholds" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="MarketValue">
      <soap12:operation soapAction="http://tsetmc.com/MarketValue" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="MarketValueByDate">
      <soap12:operation soapAction="http://tsetmc.com/MarketValueByDate" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="FutureInformation">
      <soap12:operation soapAction="http://tsetmc.com/FutureInformation" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="AdjPrice">
      <soap12:operation soapAction="http://tsetmc.com/AdjPrice" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="ShareChange">
      <soap12:operation soapAction="http://tsetmc.com/ShareChange" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="MarketActivityLast">
      <soap12:operation soapAction="http://tsetmc.com/MarketActivityLast" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SectorState">
      <soap12:operation soapAction="http://tsetmc.com/SectorState" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="NSCStart">
      <soap12:operation soapAction="http://tsetmc.com/NSCStart" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="PowerInstrument">
      <soap12:operation soapAction="http://tsetmc.com/PowerInstrument" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="PowerInstrumentHistory">
      <soap12:operation soapAction="http://tsetmc.com/PowerInstrumentHistory" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:service name="TsePublicV2">
    <wsdl:port name="TsePublicV2Soap" binding="tns:TsePublicV2Soap">
      <soap:address location="http://service.tsetmc.com/WebService/TsePublicV2.asmx" />
    </wsdl:port>
    <wsdl:port name="TsePublicV2Soap12" binding="tns:TsePublicV2Soap12">
      <soap12:address location="http://service.tsetmc.com/WebService/TsePublicV2.asmx" />
    </wsdl:port>
  </wsdl:service>
</wsdl:definitions>