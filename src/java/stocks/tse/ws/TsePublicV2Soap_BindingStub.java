/**
 * TsePublicV2Soap_BindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package stocks.tse.ws;

public class TsePublicV2Soap_BindingStub extends org.apache.axis.client.Stub implements stocks.tse.ws.TsePublicV2Soap_PortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[35];
        _initOperationDesc1();
        _initOperationDesc2();
        _initOperationDesc3();
        _initOperationDesc4();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ClientType");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>ClientTypeResponse>ClientTypeResult"));
        oper.setReturnClass(stocks.tse.ws.ClientTypeResponseClientTypeResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "ClientTypeResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Auction");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "from"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), long.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>AuctionResponse>AuctionResult"));
        oper.setReturnClass(stocks.tse.ws.AuctionResponseAuctionResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "AuctionResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Board");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>BoardResponse>BoardResult"));
        oper.setReturnClass(stocks.tse.ws.BoardResponseBoardResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "BoardResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Sector");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>SectorResponse>SectorResult"));
        oper.setReturnClass(stocks.tse.ws.SectorResponseSectorResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "SectorResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("SubSector");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>SubSectorResponse>SubSectorResult"));
        oper.setReturnClass(stocks.tse.ws.SubSectorResponseSubSectorResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "SubSectorResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("TradeLastDay");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Flow"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>TradeLastDayResponse>TradeLastDayResult"));
        oper.setReturnClass(stocks.tse.ws.TradeLastDayResponseTradeLastDayResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "TradeLastDayResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("TradeOneDay");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "SelDate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Flow"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>TradeOneDayResponse>TradeOneDayResult"));
        oper.setReturnClass(stocks.tse.ws.TradeOneDayResponseTradeOneDayResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "TradeOneDayResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("TradeOneDayAll");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "SelDate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Flow"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>TradeOneDayAllResponse>TradeOneDayAllResult"));
        oper.setReturnClass(stocks.tse.ws.TradeOneDayAllResponseTradeOneDayAllResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "TradeOneDayAllResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("InstTrade");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Inscode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), long.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "DateFrom"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "DateTo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>InstTradeResponse>InstTradeResult"));
        oper.setReturnClass(stocks.tse.ws.InstTradeResponseInstTradeResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "InstTradeResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("MarketActivityDaily");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "DateFrom"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "DateTo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>MarketActivityDailyResponse>MarketActivityDailyResult"));
        oper.setReturnClass(stocks.tse.ws.MarketActivityDailyResponseMarketActivityDailyResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "MarketActivityDailyResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("BestLimitsAllIns");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Flow"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>BestLimitsAllInsResponse>BestLimitsAllInsResult"));
        oper.setReturnClass(stocks.tse.ws.BestLimitsAllInsResponseBestLimitsAllInsResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "BestLimitsAllInsResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("BestLimitOneIns");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "InsCode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), long.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>BestLimitOneInsResponse>BestLimitOneInsResult"));
        oper.setReturnClass(stocks.tse.ws.BestLimitOneInsResponseBestLimitOneInsResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "BestLimitOneInsResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("InstWithBestLimit");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Flow"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>InstWithBestLimitResponse>InstWithBestLimitResult"));
        oper.setReturnClass(stocks.tse.ws.InstWithBestLimitResponseInstWithBestLimitResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "InstWithBestLimitResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Instrument");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Flow"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>InstrumentResponse>InstrumentResult"));
        oper.setReturnClass(stocks.tse.ws.InstrumentResponseInstrumentResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "InstrumentResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[13] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("InstrumentFilterByDate");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "DEven"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Flow"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>InstrumentFilterByDateResponse>InstrumentFilterByDateResult"));
        oper.setReturnClass(stocks.tse.ws.InstrumentFilterByDateResponseInstrumentFilterByDateResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "InstrumentFilterByDateResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[14] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Company");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Flow"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>CompanyResponse>CompanyResult"));
        oper.setReturnClass(stocks.tse.ws.CompanyResponseCompanyResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "CompanyResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[15] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("InstrumentsState");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Flow"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>InstrumentsStateResponse>InstrumentsStateResult"));
        oper.setReturnClass(stocks.tse.ws.InstrumentsStateResponseInstrumentsStateResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "InstrumentsStateResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[16] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("IndexB1LastDayLastData");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Flow"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>IndexB1LastDayLastDataResponse>IndexB1LastDayLastDataResult"));
        oper.setReturnClass(stocks.tse.ws.IndexB1LastDayLastDataResponseIndexB1LastDayLastDataResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "IndexB1LastDayLastDataResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[17] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("IndexB1LastDayOneInst");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "IdxCode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), long.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Flow"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>IndexB1LastDayOneInstResponse>IndexB1LastDayOneInstResult"));
        oper.setReturnClass(stocks.tse.ws.IndexB1LastDayOneInstResponseIndexB1LastDayOneInstResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "IndexB1LastDayOneInstResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[18] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("IndexB2");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "DEven"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>IndexB2Response>IndexB2Result"));
        oper.setReturnClass(stocks.tse.ws.IndexB2ResponseIndexB2Result.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "IndexB2Result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[19] = oper;

    }

    private static void _initOperationDesc3(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("IndexInstrument");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "IdxCode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), long.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Flow"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>IndexInstrumentResponse>IndexInstrumentResult"));
        oper.setReturnClass(stocks.tse.ws.IndexInstrumentResponseIndexInstrumentResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "IndexInstrumentResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[20] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("InstAffect");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>InstAffectResponse>InstAffectResult"));
        oper.setReturnClass(stocks.tse.ws.InstAffectResponseInstAffectResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "InstAffectResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[21] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("TOP");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Flow"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>TOPResponse>TOPResult"));
        oper.setReturnClass(stocks.tse.ws.TOPResponseTOPResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "TOPResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[22] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Msg");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>MsgResponse>MsgResult"));
        oper.setReturnClass(stocks.tse.ws.MsgResponseMsgResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "MsgResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[23] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("StaticThresholds");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Flow"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>StaticThresholdsResponse>StaticThresholdsResult"));
        oper.setReturnClass(stocks.tse.ws.StaticThresholdsResponseStaticThresholdsResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "StaticThresholdsResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[24] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("MarketValue");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        oper.setReturnClass(java.math.BigDecimal.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "MarketValueResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[25] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("MarketValueByDate");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "DEven"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        oper.setReturnClass(java.math.BigDecimal.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "MarketValueByDateResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[26] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("FutureInformation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "DEven"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>FutureInformationResponse>FutureInformationResult"));
        oper.setReturnClass(stocks.tse.ws.FutureInformationResponseFutureInformationResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "FutureInformationResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[27] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("AdjPrice");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Flow"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>AdjPriceResponse>AdjPriceResult"));
        oper.setReturnClass(stocks.tse.ws.AdjPriceResponseAdjPriceResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "AdjPriceResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[28] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ShareChange");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Flow"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>ShareChangeResponse>ShareChangeResult"));
        oper.setReturnClass(stocks.tse.ws.ShareChangeResponseShareChangeResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "ShareChangeResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[29] = oper;

    }

    private static void _initOperationDesc4(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("MarketActivityLast");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Flow"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>MarketActivityLastResponse>MarketActivityLastResult"));
        oper.setReturnClass(stocks.tse.ws.MarketActivityLastResponseMarketActivityLastResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "MarketActivityLastResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[30] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("SectorState");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "DEven"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>SectorStateResponse>SectorStateResult"));
        oper.setReturnClass(stocks.tse.ws.SectorStateResponseSectorStateResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "SectorStateResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[31] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("NSCStart");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>NSCStartResponse>NSCStartResult"));
        oper.setReturnClass(stocks.tse.ws.NSCStartResponseNSCStartResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "NSCStartResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[32] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("PowerInstrument");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>PowerInstrumentResponse>PowerInstrumentResult"));
        oper.setReturnClass(stocks.tse.ws.PowerInstrumentResponsePowerInstrumentResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "PowerInstrumentResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[33] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("PowerInstrumentHistory");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "From"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), long.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>PowerInstrumentHistoryResponse>PowerInstrumentHistoryResult"));
        oper.setReturnClass(stocks.tse.ws.PowerInstrumentHistoryResponsePowerInstrumentHistoryResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "PowerInstrumentHistoryResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[34] = oper;

    }

    public TsePublicV2Soap_BindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public TsePublicV2Soap_BindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public TsePublicV2Soap_BindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
        addBindings0();
        addBindings1();
    }

    private void addBindings0() {
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>AdjPriceResponse>AdjPriceResult");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.AdjPriceResponseAdjPriceResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>AuctionResponse>AuctionResult");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.AuctionResponseAuctionResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>BestLimitOneInsResponse>BestLimitOneInsResult");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.BestLimitOneInsResponseBestLimitOneInsResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>BestLimitsAllInsResponse>BestLimitsAllInsResult");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.BestLimitsAllInsResponseBestLimitsAllInsResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>BoardResponse>BoardResult");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.BoardResponseBoardResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>ClientTypeResponse>ClientTypeResult");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.ClientTypeResponseClientTypeResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>CompanyResponse>CompanyResult");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.CompanyResponseCompanyResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>FutureInformationResponse>FutureInformationResult");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.FutureInformationResponseFutureInformationResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>IndexB1LastDayLastDataResponse>IndexB1LastDayLastDataResult");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.IndexB1LastDayLastDataResponseIndexB1LastDayLastDataResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>IndexB1LastDayOneInstResponse>IndexB1LastDayOneInstResult");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.IndexB1LastDayOneInstResponseIndexB1LastDayOneInstResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>IndexB2Response>IndexB2Result");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.IndexB2ResponseIndexB2Result.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>IndexInstrumentResponse>IndexInstrumentResult");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.IndexInstrumentResponseIndexInstrumentResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>InstAffectResponse>InstAffectResult");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.InstAffectResponseInstAffectResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>InstrumentFilterByDateResponse>InstrumentFilterByDateResult");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.InstrumentFilterByDateResponseInstrumentFilterByDateResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>InstrumentResponse>InstrumentResult");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.InstrumentResponseInstrumentResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>InstrumentsStateResponse>InstrumentsStateResult");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.InstrumentsStateResponseInstrumentsStateResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>InstTradeResponse>InstTradeResult");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.InstTradeResponseInstTradeResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>InstWithBestLimitResponse>InstWithBestLimitResult");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.InstWithBestLimitResponseInstWithBestLimitResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>MarketActivityDailyResponse>MarketActivityDailyResult");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.MarketActivityDailyResponseMarketActivityDailyResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>MarketActivityLastResponse>MarketActivityLastResult");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.MarketActivityLastResponseMarketActivityLastResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>MsgResponse>MsgResult");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.MsgResponseMsgResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>NSCStartResponse>NSCStartResult");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.NSCStartResponseNSCStartResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>PowerInstrumentHistoryResponse>PowerInstrumentHistoryResult");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.PowerInstrumentHistoryResponsePowerInstrumentHistoryResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>PowerInstrumentResponse>PowerInstrumentResult");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.PowerInstrumentResponsePowerInstrumentResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>SectorResponse>SectorResult");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.SectorResponseSectorResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>SectorStateResponse>SectorStateResult");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.SectorStateResponseSectorStateResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>ShareChangeResponse>ShareChangeResult");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.ShareChangeResponseShareChangeResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>StaticThresholdsResponse>StaticThresholdsResult");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.StaticThresholdsResponseStaticThresholdsResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>SubSectorResponse>SubSectorResult");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.SubSectorResponseSubSectorResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>TOPResponse>TOPResult");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.TOPResponseTOPResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>TradeLastDayResponse>TradeLastDayResult");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.TradeLastDayResponseTradeLastDayResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>TradeOneDayAllResponse>TradeOneDayAllResult");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.TradeOneDayAllResponseTradeOneDayAllResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>TradeOneDayResponse>TradeOneDayResult");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.TradeOneDayResponseTradeOneDayResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">AdjPrice");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.AdjPrice.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">AdjPriceResponse");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.AdjPriceResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">Auction");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.Auction.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">AuctionResponse");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.AuctionResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">BestLimitOneIns");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.BestLimitOneIns.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">BestLimitOneInsResponse");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.BestLimitOneInsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">BestLimitsAllIns");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.BestLimitsAllIns.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">BestLimitsAllInsResponse");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.BestLimitsAllInsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">Board");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.Board.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">BoardResponse");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.BoardResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">Company");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.Company.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">CompanyResponse");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.CompanyResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">FutureInformation");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.FutureInformation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">FutureInformationResponse");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.FutureInformationResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">IndexB1LastDayLastData");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.IndexB1LastDayLastData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">IndexB1LastDayLastDataResponse");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.IndexB1LastDayLastDataResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">IndexB1LastDayOneInst");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.IndexB1LastDayOneInst.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">IndexB1LastDayOneInstResponse");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.IndexB1LastDayOneInstResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">IndexB2");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.IndexB2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">IndexB2Response");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.IndexB2Response.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">IndexInstrument");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.IndexInstrument.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">IndexInstrumentResponse");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.IndexInstrumentResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">InstAffect");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.InstAffect.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">InstAffectResponse");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.InstAffectResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">Instrument");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.Instrument.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">InstrumentFilterByDate");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.InstrumentFilterByDate.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">InstrumentFilterByDateResponse");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.InstrumentFilterByDateResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">InstrumentResponse");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.InstrumentResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">InstrumentsState");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.InstrumentsState.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">InstrumentsStateResponse");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.InstrumentsStateResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">InstTrade");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.InstTrade.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">InstTradeResponse");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.InstTradeResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">InstWithBestLimit");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.InstWithBestLimit.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">InstWithBestLimitResponse");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.InstWithBestLimitResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">MarketActivityDaily");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.MarketActivityDaily.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">MarketActivityDailyResponse");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.MarketActivityDailyResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">MarketActivityLast");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.MarketActivityLast.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">MarketActivityLastResponse");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.MarketActivityLastResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">MarketValue");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.MarketValue.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">MarketValueByDate");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.MarketValueByDate.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">MarketValueByDateResponse");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.MarketValueByDateResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">MarketValueResponse");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.MarketValueResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">Msg");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.Msg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">MsgResponse");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.MsgResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">NSCStart");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.NSCStart.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">NSCStartResponse");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.NSCStartResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">PowerInstrument");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.PowerInstrument.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">PowerInstrumentHistory");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.PowerInstrumentHistory.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">PowerInstrumentHistoryResponse");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.PowerInstrumentHistoryResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">PowerInstrumentResponse");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.PowerInstrumentResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">Sector");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.Sector.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">SectorResponse");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.SectorResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">SectorState");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.SectorState.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">SectorStateResponse");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.SectorStateResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">ShareChange");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.ShareChange.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">ShareChangeResponse");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.ShareChangeResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">StaticThresholds");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.StaticThresholds.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">StaticThresholdsResponse");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.StaticThresholdsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">SubSector");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.SubSector.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">SubSectorResponse");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.SubSectorResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">TOP");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.TOP.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">TOPResponse");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.TOPResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">TradeLastDay");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.TradeLastDay.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">TradeLastDayResponse");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.TradeLastDayResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">TradeOneDay");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.TradeOneDay.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">TradeOneDayAll");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.TradeOneDayAll.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">TradeOneDayAllResponse");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.TradeOneDayAllResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }
    private void addBindings1() {
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">TradeOneDayResponse");
            cachedSerQNames.add(qName);
            cls = stocks.tse.ws.TradeOneDayResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public stocks.tse.ws.ClientTypeResponseClientTypeResult clientType(java.lang.String userName, java.lang.String password) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/ClientType");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "ClientType"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userName, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (stocks.tse.ws.ClientTypeResponseClientTypeResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (stocks.tse.ws.ClientTypeResponseClientTypeResult) org.apache.axis.utils.JavaUtils.convert(_resp, stocks.tse.ws.ClientTypeResponseClientTypeResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public stocks.tse.ws.AuctionResponseAuctionResult auction(java.lang.String userName, java.lang.String password, long from) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/Auction");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "Auction"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userName, password, new java.lang.Long(from)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (stocks.tse.ws.AuctionResponseAuctionResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (stocks.tse.ws.AuctionResponseAuctionResult) org.apache.axis.utils.JavaUtils.convert(_resp, stocks.tse.ws.AuctionResponseAuctionResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public stocks.tse.ws.BoardResponseBoardResult board(java.lang.String userName, java.lang.String password) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/Board");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "Board"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userName, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (stocks.tse.ws.BoardResponseBoardResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (stocks.tse.ws.BoardResponseBoardResult) org.apache.axis.utils.JavaUtils.convert(_resp, stocks.tse.ws.BoardResponseBoardResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public stocks.tse.ws.SectorResponseSectorResult sector(java.lang.String userName, java.lang.String password) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/Sector");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "Sector"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userName, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (stocks.tse.ws.SectorResponseSectorResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (stocks.tse.ws.SectorResponseSectorResult) org.apache.axis.utils.JavaUtils.convert(_resp, stocks.tse.ws.SectorResponseSectorResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public stocks.tse.ws.SubSectorResponseSubSectorResult subSector(java.lang.String userName, java.lang.String password) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/SubSector");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "SubSector"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userName, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (stocks.tse.ws.SubSectorResponseSubSectorResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (stocks.tse.ws.SubSectorResponseSubSectorResult) org.apache.axis.utils.JavaUtils.convert(_resp, stocks.tse.ws.SubSectorResponseSubSectorResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public stocks.tse.ws.TradeLastDayResponseTradeLastDayResult tradeLastDay(java.lang.String userName, java.lang.String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/TradeLastDay");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "TradeLastDay"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userName, password, flow});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (stocks.tse.ws.TradeLastDayResponseTradeLastDayResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (stocks.tse.ws.TradeLastDayResponseTradeLastDayResult) org.apache.axis.utils.JavaUtils.convert(_resp, stocks.tse.ws.TradeLastDayResponseTradeLastDayResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public stocks.tse.ws.TradeOneDayResponseTradeOneDayResult tradeOneDay(java.lang.String userName, java.lang.String password, int selDate, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/TradeOneDay");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "TradeOneDay"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userName, password, new java.lang.Integer(selDate), flow});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (stocks.tse.ws.TradeOneDayResponseTradeOneDayResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (stocks.tse.ws.TradeOneDayResponseTradeOneDayResult) org.apache.axis.utils.JavaUtils.convert(_resp, stocks.tse.ws.TradeOneDayResponseTradeOneDayResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public stocks.tse.ws.TradeOneDayAllResponseTradeOneDayAllResult tradeOneDayAll(java.lang.String userName, java.lang.String password, int selDate, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/TradeOneDayAll");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "TradeOneDayAll"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userName, password, new java.lang.Integer(selDate), flow});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (stocks.tse.ws.TradeOneDayAllResponseTradeOneDayAllResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (stocks.tse.ws.TradeOneDayAllResponseTradeOneDayAllResult) org.apache.axis.utils.JavaUtils.convert(_resp, stocks.tse.ws.TradeOneDayAllResponseTradeOneDayAllResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public stocks.tse.ws.InstTradeResponseInstTradeResult instTrade(java.lang.String userName, java.lang.String password, long inscode, int dateFrom, int dateTo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/InstTrade");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "InstTrade"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userName, password, new java.lang.Long(inscode), new java.lang.Integer(dateFrom), new java.lang.Integer(dateTo)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (stocks.tse.ws.InstTradeResponseInstTradeResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (stocks.tse.ws.InstTradeResponseInstTradeResult) org.apache.axis.utils.JavaUtils.convert(_resp, stocks.tse.ws.InstTradeResponseInstTradeResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public stocks.tse.ws.MarketActivityDailyResponseMarketActivityDailyResult marketActivityDaily(java.lang.String userName, java.lang.String password, int dateFrom, int dateTo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/MarketActivityDaily");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "MarketActivityDaily"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userName, password, new java.lang.Integer(dateFrom), new java.lang.Integer(dateTo)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (stocks.tse.ws.MarketActivityDailyResponseMarketActivityDailyResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (stocks.tse.ws.MarketActivityDailyResponseMarketActivityDailyResult) org.apache.axis.utils.JavaUtils.convert(_resp, stocks.tse.ws.MarketActivityDailyResponseMarketActivityDailyResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public stocks.tse.ws.BestLimitsAllInsResponseBestLimitsAllInsResult bestLimitsAllIns(java.lang.String userName, java.lang.String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/BestLimitsAllIns");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "BestLimitsAllIns"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userName, password, flow});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (stocks.tse.ws.BestLimitsAllInsResponseBestLimitsAllInsResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (stocks.tse.ws.BestLimitsAllInsResponseBestLimitsAllInsResult) org.apache.axis.utils.JavaUtils.convert(_resp, stocks.tse.ws.BestLimitsAllInsResponseBestLimitsAllInsResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public stocks.tse.ws.BestLimitOneInsResponseBestLimitOneInsResult bestLimitOneIns(java.lang.String userName, java.lang.String password, long insCode) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/BestLimitOneIns");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "BestLimitOneIns"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userName, password, new java.lang.Long(insCode)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (stocks.tse.ws.BestLimitOneInsResponseBestLimitOneInsResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (stocks.tse.ws.BestLimitOneInsResponseBestLimitOneInsResult) org.apache.axis.utils.JavaUtils.convert(_resp, stocks.tse.ws.BestLimitOneInsResponseBestLimitOneInsResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public stocks.tse.ws.InstWithBestLimitResponseInstWithBestLimitResult instWithBestLimit(java.lang.String userName, java.lang.String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/InstWithBestLimit");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "InstWithBestLimit"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userName, password, flow});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (stocks.tse.ws.InstWithBestLimitResponseInstWithBestLimitResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (stocks.tse.ws.InstWithBestLimitResponseInstWithBestLimitResult) org.apache.axis.utils.JavaUtils.convert(_resp, stocks.tse.ws.InstWithBestLimitResponseInstWithBestLimitResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public stocks.tse.ws.InstrumentResponseInstrumentResult instrument(java.lang.String userName, java.lang.String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/Instrument");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "Instrument"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userName, password, flow});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (stocks.tse.ws.InstrumentResponseInstrumentResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (stocks.tse.ws.InstrumentResponseInstrumentResult) org.apache.axis.utils.JavaUtils.convert(_resp, stocks.tse.ws.InstrumentResponseInstrumentResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public stocks.tse.ws.InstrumentFilterByDateResponseInstrumentFilterByDateResult instrumentFilterByDate(java.lang.String userName, java.lang.String password, int DEven, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[14]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/InstrumentFilterByDate");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "InstrumentFilterByDate"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userName, password, new java.lang.Integer(DEven), flow});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (stocks.tse.ws.InstrumentFilterByDateResponseInstrumentFilterByDateResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (stocks.tse.ws.InstrumentFilterByDateResponseInstrumentFilterByDateResult) org.apache.axis.utils.JavaUtils.convert(_resp, stocks.tse.ws.InstrumentFilterByDateResponseInstrumentFilterByDateResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public stocks.tse.ws.CompanyResponseCompanyResult company(java.lang.String userName, java.lang.String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[15]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/Company");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "Company"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userName, password, flow});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (stocks.tse.ws.CompanyResponseCompanyResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (stocks.tse.ws.CompanyResponseCompanyResult) org.apache.axis.utils.JavaUtils.convert(_resp, stocks.tse.ws.CompanyResponseCompanyResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public stocks.tse.ws.InstrumentsStateResponseInstrumentsStateResult instrumentsState(java.lang.String userName, java.lang.String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[16]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/InstrumentsState");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "InstrumentsState"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userName, password, flow});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (stocks.tse.ws.InstrumentsStateResponseInstrumentsStateResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (stocks.tse.ws.InstrumentsStateResponseInstrumentsStateResult) org.apache.axis.utils.JavaUtils.convert(_resp, stocks.tse.ws.InstrumentsStateResponseInstrumentsStateResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public stocks.tse.ws.IndexB1LastDayLastDataResponseIndexB1LastDayLastDataResult indexB1LastDayLastData(java.lang.String userName, java.lang.String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[17]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/IndexB1LastDayLastData");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "IndexB1LastDayLastData"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userName, password, flow});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (stocks.tse.ws.IndexB1LastDayLastDataResponseIndexB1LastDayLastDataResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (stocks.tse.ws.IndexB1LastDayLastDataResponseIndexB1LastDayLastDataResult) org.apache.axis.utils.JavaUtils.convert(_resp, stocks.tse.ws.IndexB1LastDayLastDataResponseIndexB1LastDayLastDataResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public stocks.tse.ws.IndexB1LastDayOneInstResponseIndexB1LastDayOneInstResult indexB1LastDayOneInst(java.lang.String userName, java.lang.String password, long idxCode, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[18]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/IndexB1LastDayOneInst");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "IndexB1LastDayOneInst"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userName, password, new java.lang.Long(idxCode), flow});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (stocks.tse.ws.IndexB1LastDayOneInstResponseIndexB1LastDayOneInstResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (stocks.tse.ws.IndexB1LastDayOneInstResponseIndexB1LastDayOneInstResult) org.apache.axis.utils.JavaUtils.convert(_resp, stocks.tse.ws.IndexB1LastDayOneInstResponseIndexB1LastDayOneInstResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public stocks.tse.ws.IndexB2ResponseIndexB2Result indexB2(java.lang.String userName, java.lang.String password, int DEven) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[19]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/IndexB2");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "IndexB2"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userName, password, new java.lang.Integer(DEven)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (stocks.tse.ws.IndexB2ResponseIndexB2Result) _resp;
            } catch (java.lang.Exception _exception) {
                return (stocks.tse.ws.IndexB2ResponseIndexB2Result) org.apache.axis.utils.JavaUtils.convert(_resp, stocks.tse.ws.IndexB2ResponseIndexB2Result.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public stocks.tse.ws.IndexInstrumentResponseIndexInstrumentResult indexInstrument(java.lang.String userName, java.lang.String password, long idxCode, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[20]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/IndexInstrument");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "IndexInstrument"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userName, password, new java.lang.Long(idxCode), flow});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (stocks.tse.ws.IndexInstrumentResponseIndexInstrumentResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (stocks.tse.ws.IndexInstrumentResponseIndexInstrumentResult) org.apache.axis.utils.JavaUtils.convert(_resp, stocks.tse.ws.IndexInstrumentResponseIndexInstrumentResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public stocks.tse.ws.InstAffectResponseInstAffectResult instAffect(java.lang.String userName, java.lang.String password) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[21]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/InstAffect");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "InstAffect"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userName, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (stocks.tse.ws.InstAffectResponseInstAffectResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (stocks.tse.ws.InstAffectResponseInstAffectResult) org.apache.axis.utils.JavaUtils.convert(_resp, stocks.tse.ws.InstAffectResponseInstAffectResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public stocks.tse.ws.TOPResponseTOPResult TOP(java.lang.String userName, java.lang.String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[22]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/TOP");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "TOP"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userName, password, flow});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (stocks.tse.ws.TOPResponseTOPResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (stocks.tse.ws.TOPResponseTOPResult) org.apache.axis.utils.JavaUtils.convert(_resp, stocks.tse.ws.TOPResponseTOPResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public stocks.tse.ws.MsgResponseMsgResult msg(java.lang.String userName, java.lang.String password) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[23]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/Msg");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "Msg"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userName, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (stocks.tse.ws.MsgResponseMsgResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (stocks.tse.ws.MsgResponseMsgResult) org.apache.axis.utils.JavaUtils.convert(_resp, stocks.tse.ws.MsgResponseMsgResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public stocks.tse.ws.StaticThresholdsResponseStaticThresholdsResult staticThresholds(java.lang.String userName, java.lang.String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[24]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/StaticThresholds");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "StaticThresholds"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userName, password, flow});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (stocks.tse.ws.StaticThresholdsResponseStaticThresholdsResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (stocks.tse.ws.StaticThresholdsResponseStaticThresholdsResult) org.apache.axis.utils.JavaUtils.convert(_resp, stocks.tse.ws.StaticThresholdsResponseStaticThresholdsResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.math.BigDecimal marketValue(java.lang.String userName, java.lang.String password) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[25]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/MarketValue");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "MarketValue"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userName, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.math.BigDecimal) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.math.BigDecimal) org.apache.axis.utils.JavaUtils.convert(_resp, java.math.BigDecimal.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.math.BigDecimal marketValueByDate(java.lang.String userName, java.lang.String password, int DEven) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[26]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/MarketValueByDate");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "MarketValueByDate"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userName, password, new java.lang.Integer(DEven)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.math.BigDecimal) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.math.BigDecimal) org.apache.axis.utils.JavaUtils.convert(_resp, java.math.BigDecimal.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public stocks.tse.ws.FutureInformationResponseFutureInformationResult futureInformation(java.lang.String userName, java.lang.String password, int DEven) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[27]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/FutureInformation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "FutureInformation"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userName, password, new java.lang.Integer(DEven)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (stocks.tse.ws.FutureInformationResponseFutureInformationResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (stocks.tse.ws.FutureInformationResponseFutureInformationResult) org.apache.axis.utils.JavaUtils.convert(_resp, stocks.tse.ws.FutureInformationResponseFutureInformationResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public stocks.tse.ws.AdjPriceResponseAdjPriceResult adjPrice(java.lang.String userName, java.lang.String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[28]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/AdjPrice");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "AdjPrice"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userName, password, flow});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (stocks.tse.ws.AdjPriceResponseAdjPriceResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (stocks.tse.ws.AdjPriceResponseAdjPriceResult) org.apache.axis.utils.JavaUtils.convert(_resp, stocks.tse.ws.AdjPriceResponseAdjPriceResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public stocks.tse.ws.ShareChangeResponseShareChangeResult shareChange(java.lang.String userName, java.lang.String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[29]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/ShareChange");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "ShareChange"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userName, password, flow});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (stocks.tse.ws.ShareChangeResponseShareChangeResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (stocks.tse.ws.ShareChangeResponseShareChangeResult) org.apache.axis.utils.JavaUtils.convert(_resp, stocks.tse.ws.ShareChangeResponseShareChangeResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public stocks.tse.ws.MarketActivityLastResponseMarketActivityLastResult marketActivityLast(java.lang.String userName, java.lang.String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[30]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/MarketActivityLast");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "MarketActivityLast"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userName, password, flow});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (stocks.tse.ws.MarketActivityLastResponseMarketActivityLastResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (stocks.tse.ws.MarketActivityLastResponseMarketActivityLastResult) org.apache.axis.utils.JavaUtils.convert(_resp, stocks.tse.ws.MarketActivityLastResponseMarketActivityLastResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public stocks.tse.ws.SectorStateResponseSectorStateResult sectorState(java.lang.String userName, java.lang.String password, int DEven) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[31]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/SectorState");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "SectorState"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userName, password, new java.lang.Integer(DEven)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (stocks.tse.ws.SectorStateResponseSectorStateResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (stocks.tse.ws.SectorStateResponseSectorStateResult) org.apache.axis.utils.JavaUtils.convert(_resp, stocks.tse.ws.SectorStateResponseSectorStateResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public stocks.tse.ws.NSCStartResponseNSCStartResult NSCStart(java.lang.String userName, java.lang.String password) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[32]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/NSCStart");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "NSCStart"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userName, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (stocks.tse.ws.NSCStartResponseNSCStartResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (stocks.tse.ws.NSCStartResponseNSCStartResult) org.apache.axis.utils.JavaUtils.convert(_resp, stocks.tse.ws.NSCStartResponseNSCStartResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public stocks.tse.ws.PowerInstrumentResponsePowerInstrumentResult powerInstrument(java.lang.String userName, java.lang.String password) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[33]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/PowerInstrument");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "PowerInstrument"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userName, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (stocks.tse.ws.PowerInstrumentResponsePowerInstrumentResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (stocks.tse.ws.PowerInstrumentResponsePowerInstrumentResult) org.apache.axis.utils.JavaUtils.convert(_resp, stocks.tse.ws.PowerInstrumentResponsePowerInstrumentResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public stocks.tse.ws.PowerInstrumentHistoryResponsePowerInstrumentHistoryResult powerInstrumentHistory(java.lang.String userName, java.lang.String password, long from) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[34]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/PowerInstrumentHistory");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "PowerInstrumentHistory"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userName, password, new java.lang.Long(from)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (stocks.tse.ws.PowerInstrumentHistoryResponsePowerInstrumentHistoryResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (stocks.tse.ws.PowerInstrumentHistoryResponsePowerInstrumentHistoryResult) org.apache.axis.utils.JavaUtils.convert(_resp, stocks.tse.ws.PowerInstrumentHistoryResponsePowerInstrumentHistoryResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
