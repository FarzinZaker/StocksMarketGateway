/**
 * TsePublicV2Soap12Stub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package stocks.tse.ws.old;

public class TsePublicV2Soap12Stub extends org.apache.axis.client.Stub implements TsePublicV2Soap_PortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[36];
        _initOperationDesc1();
        _initOperationDesc2();
        _initOperationDesc3();
        _initOperationDesc4();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Board");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>BoardResponse>BoardResult"));
        oper.setReturnClass(BoardResponseBoardResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "BoardResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Sector");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>SectorResponse>SectorResult"));
        oper.setReturnClass(SectorResponseSectorResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "SectorResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("SubSector");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>SubSectorResponse>SubSectorResult"));
        oper.setReturnClass(SubSectorResponseSubSectorResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "SubSectorResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("TradeLastDay");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Flow"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>TradeLastDayResponse>TradeLastDayResult"));
        oper.setReturnClass(TradeLastDayResponseTradeLastDayResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "TradeLastDayResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("TradeOneDay");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "SelDate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Flow"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>TradeOneDayResponse>TradeOneDayResult"));
        oper.setReturnClass(TradeOneDayResponseTradeOneDayResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "TradeOneDayResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("TradeOneDayAll");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "SelDate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Flow"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>TradeOneDayAllResponse>TradeOneDayAllResult"));
        oper.setReturnClass(TradeOneDayAllResponseTradeOneDayAllResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "TradeOneDayAllResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("InstTrade");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Inscode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), long.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "DateFrom"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "DateTo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>InstTradeResponse>InstTradeResult"));
        oper.setReturnClass(InstTradeResponseInstTradeResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "InstTradeResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("MarketActivityDaily");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "DateFrom"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "DateTo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>MarketActivityDailyResponse>MarketActivityDailyResult"));
        oper.setReturnClass(MarketActivityDailyResponseMarketActivityDailyResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "MarketActivityDailyResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("BestLimitsAllIns");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Flow"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>BestLimitsAllInsResponse>BestLimitsAllInsResult"));
        oper.setReturnClass(BestLimitsAllInsResponseBestLimitsAllInsResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "BestLimitsAllInsResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("BestLimitOneIns");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "InsCode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), long.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>BestLimitOneInsResponse>BestLimitOneInsResult"));
        oper.setReturnClass(BestLimitOneInsResponseBestLimitOneInsResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "BestLimitOneInsResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("InstWithBestLimit");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Flow"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>InstWithBestLimitResponse>InstWithBestLimitResult"));
        oper.setReturnClass(InstWithBestLimitResponseInstWithBestLimitResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "InstWithBestLimitResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Instrument");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Flow"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>InstrumentResponse>InstrumentResult"));
        oper.setReturnClass(InstrumentResponseInstrumentResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "InstrumentResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("InstrumentFilterByDate");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "DEven"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Flow"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>InstrumentFilterByDateResponse>InstrumentFilterByDateResult"));
        oper.setReturnClass(InstrumentFilterByDateResponseInstrumentFilterByDateResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "InstrumentFilterByDateResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Company");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Flow"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>CompanyResponse>CompanyResult"));
        oper.setReturnClass(CompanyResponseCompanyResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "CompanyResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[13] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("InstrumentDetailOneIns");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "CIsin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Flow"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>InstrumentDetailOneInsResponse>InstrumentDetailOneInsResult"));
        oper.setReturnClass(InstrumentDetailOneInsResponseInstrumentDetailOneInsResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "InstrumentDetailOneInsResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[14] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("InstrumentDetailAllIns");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Flow"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>InstrumentDetailAllInsResponse>InstrumentDetailAllInsResult"));
        oper.setReturnClass(InstrumentDetailAllInsResponseInstrumentDetailAllInsResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "InstrumentDetailAllInsResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[15] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("StateType");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>StateTypeResponse>StateTypeResult"));
        oper.setReturnClass(StateTypeResponseStateTypeResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "StateTypeResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[16] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("InstrumentStateDescOneIns");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "InsCode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), long.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Flow"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>InstrumentStateDescOneInsResponse>InstrumentStateDescOneInsResult"));
        oper.setReturnClass(InstrumentStateDescOneInsResponseInstrumentStateDescOneInsResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "InstrumentStateDescOneInsResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[17] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("InstrumentsStateDescAllIns");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Flow"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>InstrumentsStateDescAllInsResponse>InstrumentsStateDescAllInsResult"));
        oper.setReturnClass(InstrumentsStateDescAllInsResponseInstrumentsStateDescAllInsResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "InstrumentsStateDescAllInsResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[18] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("InstrumentsState");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Flow"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>InstrumentsStateResponse>InstrumentsStateResult"));
        oper.setReturnClass(InstrumentsStateResponseInstrumentsStateResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "InstrumentsStateResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[19] = oper;

    }

    private static void _initOperationDesc3(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("IndexB1LastDayLastData");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Flow"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>IndexB1LastDayLastDataResponse>IndexB1LastDayLastDataResult"));
        oper.setReturnClass(IndexB1LastDayLastDataResponseIndexB1LastDayLastDataResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "IndexB1LastDayLastDataResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[20] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("IndexB1LastDayOneInst");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "IdxCode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), long.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Flow"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>IndexB1LastDayOneInstResponse>IndexB1LastDayOneInstResult"));
        oper.setReturnClass(IndexB1LastDayOneInstResponseIndexB1LastDayOneInstResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "IndexB1LastDayOneInstResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[21] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("IndexB2");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "DEven"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>IndexB2Response>IndexB2Result"));
        oper.setReturnClass(IndexB2ResponseIndexB2Result.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "IndexB2Result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[22] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("IndexInstrument");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "IdxCode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), long.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Flow"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>IndexInstrumentResponse>IndexInstrumentResult"));
        oper.setReturnClass(IndexInstrumentResponseIndexInstrumentResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "IndexInstrumentResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[23] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("InstAffect");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>InstAffectResponse>InstAffectResult"));
        oper.setReturnClass(InstAffectResponseInstAffectResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "InstAffectResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[24] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("TOP");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Flow"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>TOPResponse>TOPResult"));
        oper.setReturnClass(TOPResponseTOPResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "TOPResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[25] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Msg");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>MsgResponse>MsgResult"));
        oper.setReturnClass(MsgResponseMsgResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "MsgResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[26] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("StaticThresholds");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Flow"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>StaticThresholdsResponse>StaticThresholdsResult"));
        oper.setReturnClass(StaticThresholdsResponseStaticThresholdsResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "StaticThresholdsResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[27] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("MarketValue");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        oper.setReturnClass(java.math.BigDecimal.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "MarketValueResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[28] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("MarketValueByDate");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "DEven"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        oper.setReturnClass(java.math.BigDecimal.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "MarketValueByDateResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[29] = oper;

    }

    private static void _initOperationDesc4(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("FutureInformation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "DEven"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>FutureInformationResponse>FutureInformationResult"));
        oper.setReturnClass(FutureInformationResponseFutureInformationResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "FutureInformationResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[30] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("AdjPrice");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Flow"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>AdjPriceResponse>AdjPriceResult"));
        oper.setReturnClass(AdjPriceResponseAdjPriceResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "AdjPriceResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[31] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ShareChange");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Flow"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>ShareChangeResponse>ShareChangeResult"));
        oper.setReturnClass(ShareChangeResponseShareChangeResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "ShareChangeResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[32] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("MarketActivityLast");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Flow"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"), org.apache.axis.types.UnsignedByte.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>MarketActivityLastResponse>MarketActivityLastResult"));
        oper.setReturnClass(MarketActivityLastResponseMarketActivityLastResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "MarketActivityLastResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[33] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("SectorState");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "DEven"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>SectorStateResponse>SectorStateResult"));
        oper.setReturnClass(SectorStateResponseSectorStateResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "SectorStateResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[34] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("NSCStart");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "UserName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tsetmc.com/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tsetmc.com/", ">>NSCStartResponse>NSCStartResult"));
        oper.setReturnClass(NSCStartResponseNSCStartResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tsetmc.com/", "NSCStartResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[35] = oper;

    }

    public TsePublicV2Soap12Stub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public TsePublicV2Soap12Stub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public TsePublicV2Soap12Stub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
        addBindings0();
        addBindings1();
    }

    private void addBindings0() {
            Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>AdjPriceResponse>AdjPriceResult");
            cachedSerQNames.add(qName);
            cls = AdjPriceResponseAdjPriceResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>BestLimitOneInsResponse>BestLimitOneInsResult");
            cachedSerQNames.add(qName);
            cls = BestLimitOneInsResponseBestLimitOneInsResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>BestLimitsAllInsResponse>BestLimitsAllInsResult");
            cachedSerQNames.add(qName);
            cls = BestLimitsAllInsResponseBestLimitsAllInsResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>BoardResponse>BoardResult");
            cachedSerQNames.add(qName);
            cls = BoardResponseBoardResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>CompanyResponse>CompanyResult");
            cachedSerQNames.add(qName);
            cls = CompanyResponseCompanyResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>FutureInformationResponse>FutureInformationResult");
            cachedSerQNames.add(qName);
            cls = FutureInformationResponseFutureInformationResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>IndexB1LastDayLastDataResponse>IndexB1LastDayLastDataResult");
            cachedSerQNames.add(qName);
            cls = IndexB1LastDayLastDataResponseIndexB1LastDayLastDataResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>IndexB1LastDayOneInstResponse>IndexB1LastDayOneInstResult");
            cachedSerQNames.add(qName);
            cls = IndexB1LastDayOneInstResponseIndexB1LastDayOneInstResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>IndexB2Response>IndexB2Result");
            cachedSerQNames.add(qName);
            cls = IndexB2ResponseIndexB2Result.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>IndexInstrumentResponse>IndexInstrumentResult");
            cachedSerQNames.add(qName);
            cls = IndexInstrumentResponseIndexInstrumentResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>InstAffectResponse>InstAffectResult");
            cachedSerQNames.add(qName);
            cls = InstAffectResponseInstAffectResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>InstrumentDetailAllInsResponse>InstrumentDetailAllInsResult");
            cachedSerQNames.add(qName);
            cls = InstrumentDetailAllInsResponseInstrumentDetailAllInsResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>InstrumentDetailOneInsResponse>InstrumentDetailOneInsResult");
            cachedSerQNames.add(qName);
            cls = InstrumentDetailOneInsResponseInstrumentDetailOneInsResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>InstrumentFilterByDateResponse>InstrumentFilterByDateResult");
            cachedSerQNames.add(qName);
            cls = InstrumentFilterByDateResponseInstrumentFilterByDateResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>InstrumentResponse>InstrumentResult");
            cachedSerQNames.add(qName);
            cls = InstrumentResponseInstrumentResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>InstrumentsStateDescAllInsResponse>InstrumentsStateDescAllInsResult");
            cachedSerQNames.add(qName);
            cls = InstrumentsStateDescAllInsResponseInstrumentsStateDescAllInsResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>InstrumentsStateResponse>InstrumentsStateResult");
            cachedSerQNames.add(qName);
            cls = InstrumentsStateResponseInstrumentsStateResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>InstrumentStateDescOneInsResponse>InstrumentStateDescOneInsResult");
            cachedSerQNames.add(qName);
            cls = InstrumentStateDescOneInsResponseInstrumentStateDescOneInsResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>InstTradeResponse>InstTradeResult");
            cachedSerQNames.add(qName);
            cls = InstTradeResponseInstTradeResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>InstWithBestLimitResponse>InstWithBestLimitResult");
            cachedSerQNames.add(qName);
            cls = InstWithBestLimitResponseInstWithBestLimitResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>MarketActivityDailyResponse>MarketActivityDailyResult");
            cachedSerQNames.add(qName);
            cls = MarketActivityDailyResponseMarketActivityDailyResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>MarketActivityLastResponse>MarketActivityLastResult");
            cachedSerQNames.add(qName);
            cls = MarketActivityLastResponseMarketActivityLastResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>MsgResponse>MsgResult");
            cachedSerQNames.add(qName);
            cls = MsgResponseMsgResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>NSCStartResponse>NSCStartResult");
            cachedSerQNames.add(qName);
            cls = NSCStartResponseNSCStartResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>SectorResponse>SectorResult");
            cachedSerQNames.add(qName);
            cls = SectorResponseSectorResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>SectorStateResponse>SectorStateResult");
            cachedSerQNames.add(qName);
            cls = SectorStateResponseSectorStateResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>ShareChangeResponse>ShareChangeResult");
            cachedSerQNames.add(qName);
            cls = ShareChangeResponseShareChangeResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>StateTypeResponse>StateTypeResult");
            cachedSerQNames.add(qName);
            cls = StateTypeResponseStateTypeResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>StaticThresholdsResponse>StaticThresholdsResult");
            cachedSerQNames.add(qName);
            cls = StaticThresholdsResponseStaticThresholdsResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>SubSectorResponse>SubSectorResult");
            cachedSerQNames.add(qName);
            cls = SubSectorResponseSubSectorResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>TOPResponse>TOPResult");
            cachedSerQNames.add(qName);
            cls = TOPResponseTOPResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>TradeLastDayResponse>TradeLastDayResult");
            cachedSerQNames.add(qName);
            cls = TradeLastDayResponseTradeLastDayResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>TradeOneDayAllResponse>TradeOneDayAllResult");
            cachedSerQNames.add(qName);
            cls = TradeOneDayAllResponseTradeOneDayAllResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">>TradeOneDayResponse>TradeOneDayResult");
            cachedSerQNames.add(qName);
            cls = TradeOneDayResponseTradeOneDayResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">AdjPrice");
            cachedSerQNames.add(qName);
            cls = AdjPrice.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">AdjPriceResponse");
            cachedSerQNames.add(qName);
            cls = AdjPriceResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">BestLimitOneIns");
            cachedSerQNames.add(qName);
            cls = BestLimitOneIns.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">BestLimitOneInsResponse");
            cachedSerQNames.add(qName);
            cls = BestLimitOneInsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">BestLimitsAllIns");
            cachedSerQNames.add(qName);
            cls = BestLimitsAllIns.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">BestLimitsAllInsResponse");
            cachedSerQNames.add(qName);
            cls = BestLimitsAllInsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">Company");
            cachedSerQNames.add(qName);
            cls = Company.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">CompanyResponse");
            cachedSerQNames.add(qName);
            cls = CompanyResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">FutureInformation");
            cachedSerQNames.add(qName);
            cls = FutureInformation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">FutureInformationResponse");
            cachedSerQNames.add(qName);
            cls = FutureInformationResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">IndexB1LastDayLastData");
            cachedSerQNames.add(qName);
            cls = IndexB1LastDayLastData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">IndexB1LastDayLastDataResponse");
            cachedSerQNames.add(qName);
            cls = IndexB1LastDayLastDataResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">IndexB1LastDayOneInst");
            cachedSerQNames.add(qName);
            cls = IndexB1LastDayOneInst.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">IndexB1LastDayOneInstResponse");
            cachedSerQNames.add(qName);
            cls = IndexB1LastDayOneInstResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">IndexB2");
            cachedSerQNames.add(qName);
            cls = IndexB2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">IndexB2Response");
            cachedSerQNames.add(qName);
            cls = IndexB2Response.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">IndexInstrument");
            cachedSerQNames.add(qName);
            cls = IndexInstrument.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">IndexInstrumentResponse");
            cachedSerQNames.add(qName);
            cls = IndexInstrumentResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">InstAffect");
            cachedSerQNames.add(qName);
            cls = InstAffect.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">InstAffectResponse");
            cachedSerQNames.add(qName);
            cls = InstAffectResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">Instrument");
            cachedSerQNames.add(qName);
            cls = Instrument.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">InstrumentDetailAllIns");
            cachedSerQNames.add(qName);
            cls = InstrumentDetailAllIns.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">InstrumentDetailAllInsResponse");
            cachedSerQNames.add(qName);
            cls = InstrumentDetailAllInsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">InstrumentDetailOneIns");
            cachedSerQNames.add(qName);
            cls = InstrumentDetailOneIns.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">InstrumentDetailOneInsResponse");
            cachedSerQNames.add(qName);
            cls = InstrumentDetailOneInsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">InstrumentFilterByDate");
            cachedSerQNames.add(qName);
            cls = InstrumentFilterByDate.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">InstrumentFilterByDateResponse");
            cachedSerQNames.add(qName);
            cls = InstrumentFilterByDateResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">InstrumentResponse");
            cachedSerQNames.add(qName);
            cls = InstrumentResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">InstrumentsState");
            cachedSerQNames.add(qName);
            cls = InstrumentsState.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">InstrumentsStateDescAllIns");
            cachedSerQNames.add(qName);
            cls = InstrumentsStateDescAllIns.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">InstrumentsStateDescAllInsResponse");
            cachedSerQNames.add(qName);
            cls = InstrumentsStateDescAllInsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">InstrumentsStateResponse");
            cachedSerQNames.add(qName);
            cls = InstrumentsStateResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">InstrumentStateDescOneIns");
            cachedSerQNames.add(qName);
            cls = InstrumentStateDescOneIns.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">InstrumentStateDescOneInsResponse");
            cachedSerQNames.add(qName);
            cls = InstrumentStateDescOneInsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">InstTrade");
            cachedSerQNames.add(qName);
            cls = InstTrade.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">InstTradeResponse");
            cachedSerQNames.add(qName);
            cls = InstTradeResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">InstWithBestLimit");
            cachedSerQNames.add(qName);
            cls = InstWithBestLimit.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">InstWithBestLimitResponse");
            cachedSerQNames.add(qName);
            cls = InstWithBestLimitResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">MarketActivityDaily");
            cachedSerQNames.add(qName);
            cls = MarketActivityDaily.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">MarketActivityDailyResponse");
            cachedSerQNames.add(qName);
            cls = MarketActivityDailyResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">MarketActivityLast");
            cachedSerQNames.add(qName);
            cls = MarketActivityLast.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">MarketActivityLastResponse");
            cachedSerQNames.add(qName);
            cls = MarketActivityLastResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">MarketValue");
            cachedSerQNames.add(qName);
            cls = MarketValue.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">MarketValueByDate");
            cachedSerQNames.add(qName);
            cls = MarketValueByDate.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">MarketValueByDateResponse");
            cachedSerQNames.add(qName);
            cls = MarketValueByDateResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">MarketValueResponse");
            cachedSerQNames.add(qName);
            cls = MarketValueResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">Msg");
            cachedSerQNames.add(qName);
            cls = Msg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">MsgResponse");
            cachedSerQNames.add(qName);
            cls = MsgResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">NSCStart");
            cachedSerQNames.add(qName);
            cls = NSCStart.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">NSCStartResponse");
            cachedSerQNames.add(qName);
            cls = NSCStartResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">Sector");
            cachedSerQNames.add(qName);
            cls = Sector.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">SectorResponse");
            cachedSerQNames.add(qName);
            cls = SectorResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">SectorState");
            cachedSerQNames.add(qName);
            cls = SectorState.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">SectorStateResponse");
            cachedSerQNames.add(qName);
            cls = SectorStateResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">ShareChange");
            cachedSerQNames.add(qName);
            cls = ShareChange.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">ShareChangeResponse");
            cachedSerQNames.add(qName);
            cls = ShareChangeResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">StateType");
            cachedSerQNames.add(qName);
            cls = StateType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">StateTypeResponse");
            cachedSerQNames.add(qName);
            cls = StateTypeResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">StaticThresholds");
            cachedSerQNames.add(qName);
            cls = StaticThresholds.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">StaticThresholdsResponse");
            cachedSerQNames.add(qName);
            cls = StaticThresholdsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">SubSector");
            cachedSerQNames.add(qName);
            cls = SubSector.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">SubSectorResponse");
            cachedSerQNames.add(qName);
            cls = SubSectorResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">TOP");
            cachedSerQNames.add(qName);
            cls = TOP.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">TOPResponse");
            cachedSerQNames.add(qName);
            cls = TOPResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">TradeLastDay");
            cachedSerQNames.add(qName);
            cls = TradeLastDay.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">TradeLastDayResponse");
            cachedSerQNames.add(qName);
            cls = TradeLastDayResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }
    private void addBindings1() {
            Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">TradeOneDay");
            cachedSerQNames.add(qName);
            cls = TradeOneDay.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">TradeOneDayAll");
            cachedSerQNames.add(qName);
            cls = TradeOneDayAll.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">TradeOneDayAllResponse");
            cachedSerQNames.add(qName);
            cls = TradeOneDayAllResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tsetmc.com/", ">TradeOneDayResponse");
            cachedSerQNames.add(qName);
            cls = TradeOneDayResponse.class;
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
                String key = (String) keys.nextElement();
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
                        Class cls = (Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            Class sf = (Class)
                                 cachedSerFactories.get(i);
                            Class df = (Class)
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
        catch (Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public BoardResponseBoardResult board(String userName, String password) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/Board");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "Board"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {userName, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (BoardResponseBoardResult) _resp;
            } catch (Exception _exception) {
                return (BoardResponseBoardResult) org.apache.axis.utils.JavaUtils.convert(_resp, BoardResponseBoardResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public SectorResponseSectorResult sector(String userName, String password) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/Sector");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "Sector"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {userName, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (SectorResponseSectorResult) _resp;
            } catch (Exception _exception) {
                return (SectorResponseSectorResult) org.apache.axis.utils.JavaUtils.convert(_resp, SectorResponseSectorResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public SubSectorResponseSubSectorResult subSector(String userName, String password) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/SubSector");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "SubSector"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {userName, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (SubSectorResponseSubSectorResult) _resp;
            } catch (Exception _exception) {
                return (SubSectorResponseSubSectorResult) org.apache.axis.utils.JavaUtils.convert(_resp, SubSectorResponseSubSectorResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public TradeLastDayResponseTradeLastDayResult tradeLastDay(String userName, String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/TradeLastDay");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "TradeLastDay"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {userName, password, flow});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (TradeLastDayResponseTradeLastDayResult) _resp;
            } catch (Exception _exception) {
                return (TradeLastDayResponseTradeLastDayResult) org.apache.axis.utils.JavaUtils.convert(_resp, TradeLastDayResponseTradeLastDayResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public TradeOneDayResponseTradeOneDayResult tradeOneDay(String userName, String password, int selDate, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/TradeOneDay");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "TradeOneDay"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {userName, password, new Integer(selDate), flow});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (TradeOneDayResponseTradeOneDayResult) _resp;
            } catch (Exception _exception) {
                return (TradeOneDayResponseTradeOneDayResult) org.apache.axis.utils.JavaUtils.convert(_resp, TradeOneDayResponseTradeOneDayResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public TradeOneDayAllResponseTradeOneDayAllResult tradeOneDayAll(String userName, String password, int selDate, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/TradeOneDayAll");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "TradeOneDayAll"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {userName, password, new Integer(selDate), flow});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (TradeOneDayAllResponseTradeOneDayAllResult) _resp;
            } catch (Exception _exception) {
                return (TradeOneDayAllResponseTradeOneDayAllResult) org.apache.axis.utils.JavaUtils.convert(_resp, TradeOneDayAllResponseTradeOneDayAllResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public InstTradeResponseInstTradeResult instTrade(String userName, String password, long inscode, int dateFrom, int dateTo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/InstTrade");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "InstTrade"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {userName, password, new Long(inscode), new Integer(dateFrom), new Integer(dateTo)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (InstTradeResponseInstTradeResult) _resp;
            } catch (Exception _exception) {
                return (InstTradeResponseInstTradeResult) org.apache.axis.utils.JavaUtils.convert(_resp, InstTradeResponseInstTradeResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public MarketActivityDailyResponseMarketActivityDailyResult marketActivityDaily(String userName, String password, int dateFrom, int dateTo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/MarketActivityDaily");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "MarketActivityDaily"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {userName, password, new Integer(dateFrom), new Integer(dateTo)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (MarketActivityDailyResponseMarketActivityDailyResult) _resp;
            } catch (Exception _exception) {
                return (MarketActivityDailyResponseMarketActivityDailyResult) org.apache.axis.utils.JavaUtils.convert(_resp, MarketActivityDailyResponseMarketActivityDailyResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public BestLimitsAllInsResponseBestLimitsAllInsResult bestLimitsAllIns(String userName, String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/BestLimitsAllIns");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "BestLimitsAllIns"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {userName, password, flow});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (BestLimitsAllInsResponseBestLimitsAllInsResult) _resp;
            } catch (Exception _exception) {
                return (BestLimitsAllInsResponseBestLimitsAllInsResult) org.apache.axis.utils.JavaUtils.convert(_resp, BestLimitsAllInsResponseBestLimitsAllInsResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public BestLimitOneInsResponseBestLimitOneInsResult bestLimitOneIns(String userName, String password, long insCode) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/BestLimitOneIns");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "BestLimitOneIns"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {userName, password, new Long(insCode)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (BestLimitOneInsResponseBestLimitOneInsResult) _resp;
            } catch (Exception _exception) {
                return (BestLimitOneInsResponseBestLimitOneInsResult) org.apache.axis.utils.JavaUtils.convert(_resp, BestLimitOneInsResponseBestLimitOneInsResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public InstWithBestLimitResponseInstWithBestLimitResult instWithBestLimit(String userName, String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/InstWithBestLimit");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "InstWithBestLimit"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {userName, password, flow});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (InstWithBestLimitResponseInstWithBestLimitResult) _resp;
            } catch (Exception _exception) {
                return (InstWithBestLimitResponseInstWithBestLimitResult) org.apache.axis.utils.JavaUtils.convert(_resp, InstWithBestLimitResponseInstWithBestLimitResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public InstrumentResponseInstrumentResult instrument(String userName, String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/Instrument");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "Instrument"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {userName, password, flow});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (InstrumentResponseInstrumentResult) _resp;
            } catch (Exception _exception) {
                return (InstrumentResponseInstrumentResult) org.apache.axis.utils.JavaUtils.convert(_resp, InstrumentResponseInstrumentResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public InstrumentFilterByDateResponseInstrumentFilterByDateResult instrumentFilterByDate(String userName, String password, int DEven, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/InstrumentFilterByDate");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "InstrumentFilterByDate"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {userName, password, new Integer(DEven), flow});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (InstrumentFilterByDateResponseInstrumentFilterByDateResult) _resp;
            } catch (Exception _exception) {
                return (InstrumentFilterByDateResponseInstrumentFilterByDateResult) org.apache.axis.utils.JavaUtils.convert(_resp, InstrumentFilterByDateResponseInstrumentFilterByDateResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public CompanyResponseCompanyResult company(String userName, String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/Company");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "Company"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {userName, password, flow});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (CompanyResponseCompanyResult) _resp;
            } catch (Exception _exception) {
                return (CompanyResponseCompanyResult) org.apache.axis.utils.JavaUtils.convert(_resp, CompanyResponseCompanyResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public InstrumentDetailOneInsResponseInstrumentDetailOneInsResult instrumentDetailOneIns(String userName, String password, String CIsin, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[14]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/InstrumentDetailOneIns");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "InstrumentDetailOneIns"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {userName, password, CIsin, flow});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (InstrumentDetailOneInsResponseInstrumentDetailOneInsResult) _resp;
            } catch (Exception _exception) {
                return (InstrumentDetailOneInsResponseInstrumentDetailOneInsResult) org.apache.axis.utils.JavaUtils.convert(_resp, InstrumentDetailOneInsResponseInstrumentDetailOneInsResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public InstrumentDetailAllInsResponseInstrumentDetailAllInsResult instrumentDetailAllIns(String userName, String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[15]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/InstrumentDetailAllIns");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "InstrumentDetailAllIns"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {userName, password, flow});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (InstrumentDetailAllInsResponseInstrumentDetailAllInsResult) _resp;
            } catch (Exception _exception) {
                return (InstrumentDetailAllInsResponseInstrumentDetailAllInsResult) org.apache.axis.utils.JavaUtils.convert(_resp, InstrumentDetailAllInsResponseInstrumentDetailAllInsResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public StateTypeResponseStateTypeResult stateType(String userName, String password) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[16]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/StateType");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "StateType"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {userName, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (StateTypeResponseStateTypeResult) _resp;
            } catch (Exception _exception) {
                return (StateTypeResponseStateTypeResult) org.apache.axis.utils.JavaUtils.convert(_resp, StateTypeResponseStateTypeResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public InstrumentStateDescOneInsResponseInstrumentStateDescOneInsResult instrumentStateDescOneIns(String userName, String password, long insCode, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[17]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/InstrumentStateDescOneIns");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "InstrumentStateDescOneIns"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {userName, password, new Long(insCode), flow});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (InstrumentStateDescOneInsResponseInstrumentStateDescOneInsResult) _resp;
            } catch (Exception _exception) {
                return (InstrumentStateDescOneInsResponseInstrumentStateDescOneInsResult) org.apache.axis.utils.JavaUtils.convert(_resp, InstrumentStateDescOneInsResponseInstrumentStateDescOneInsResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public InstrumentsStateDescAllInsResponseInstrumentsStateDescAllInsResult instrumentsStateDescAllIns(String userName, String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[18]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/InstrumentsStateDescAllIns");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "InstrumentsStateDescAllIns"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {userName, password, flow});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (InstrumentsStateDescAllInsResponseInstrumentsStateDescAllInsResult) _resp;
            } catch (Exception _exception) {
                return (InstrumentsStateDescAllInsResponseInstrumentsStateDescAllInsResult) org.apache.axis.utils.JavaUtils.convert(_resp, InstrumentsStateDescAllInsResponseInstrumentsStateDescAllInsResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public InstrumentsStateResponseInstrumentsStateResult instrumentsState(String userName, String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[19]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/InstrumentsState");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "InstrumentsState"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {userName, password, flow});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (InstrumentsStateResponseInstrumentsStateResult) _resp;
            } catch (Exception _exception) {
                return (InstrumentsStateResponseInstrumentsStateResult) org.apache.axis.utils.JavaUtils.convert(_resp, InstrumentsStateResponseInstrumentsStateResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public IndexB1LastDayLastDataResponseIndexB1LastDayLastDataResult indexB1LastDayLastData(String userName, String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[20]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/IndexB1LastDayLastData");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "IndexB1LastDayLastData"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {userName, password, flow});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (IndexB1LastDayLastDataResponseIndexB1LastDayLastDataResult) _resp;
            } catch (Exception _exception) {
                return (IndexB1LastDayLastDataResponseIndexB1LastDayLastDataResult) org.apache.axis.utils.JavaUtils.convert(_resp, IndexB1LastDayLastDataResponseIndexB1LastDayLastDataResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public IndexB1LastDayOneInstResponseIndexB1LastDayOneInstResult indexB1LastDayOneInst(String userName, String password, long idxCode, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[21]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/IndexB1LastDayOneInst");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "IndexB1LastDayOneInst"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {userName, password, new Long(idxCode), flow});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (IndexB1LastDayOneInstResponseIndexB1LastDayOneInstResult) _resp;
            } catch (Exception _exception) {
                return (IndexB1LastDayOneInstResponseIndexB1LastDayOneInstResult) org.apache.axis.utils.JavaUtils.convert(_resp, IndexB1LastDayOneInstResponseIndexB1LastDayOneInstResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public IndexB2ResponseIndexB2Result indexB2(String userName, String password, int DEven) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[22]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/IndexB2");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "IndexB2"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {userName, password, new Integer(DEven)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (IndexB2ResponseIndexB2Result) _resp;
            } catch (Exception _exception) {
                return (IndexB2ResponseIndexB2Result) org.apache.axis.utils.JavaUtils.convert(_resp, IndexB2ResponseIndexB2Result.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public IndexInstrumentResponseIndexInstrumentResult indexInstrument(String userName, String password, long idxCode, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[23]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/IndexInstrument");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "IndexInstrument"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {userName, password, new Long(idxCode), flow});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (IndexInstrumentResponseIndexInstrumentResult) _resp;
            } catch (Exception _exception) {
                return (IndexInstrumentResponseIndexInstrumentResult) org.apache.axis.utils.JavaUtils.convert(_resp, IndexInstrumentResponseIndexInstrumentResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public InstAffectResponseInstAffectResult instAffect(String userName, String password) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[24]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/InstAffect");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "InstAffect"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {userName, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (InstAffectResponseInstAffectResult) _resp;
            } catch (Exception _exception) {
                return (InstAffectResponseInstAffectResult) org.apache.axis.utils.JavaUtils.convert(_resp, InstAffectResponseInstAffectResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public TOPResponseTOPResult TOP(String userName, String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[25]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/TOP");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "TOP"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {userName, password, flow});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (TOPResponseTOPResult) _resp;
            } catch (Exception _exception) {
                return (TOPResponseTOPResult) org.apache.axis.utils.JavaUtils.convert(_resp, TOPResponseTOPResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public MsgResponseMsgResult msg(String userName, String password) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[26]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/Msg");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "Msg"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {userName, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (MsgResponseMsgResult) _resp;
            } catch (Exception _exception) {
                return (MsgResponseMsgResult) org.apache.axis.utils.JavaUtils.convert(_resp, MsgResponseMsgResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public StaticThresholdsResponseStaticThresholdsResult staticThresholds(String userName, String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[27]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/StaticThresholds");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "StaticThresholds"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {userName, password, flow});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (StaticThresholdsResponseStaticThresholdsResult) _resp;
            } catch (Exception _exception) {
                return (StaticThresholdsResponseStaticThresholdsResult) org.apache.axis.utils.JavaUtils.convert(_resp, StaticThresholdsResponseStaticThresholdsResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.math.BigDecimal marketValue(String userName, String password) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[28]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/MarketValue");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "MarketValue"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {userName, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.math.BigDecimal) _resp;
            } catch (Exception _exception) {
                return (java.math.BigDecimal) org.apache.axis.utils.JavaUtils.convert(_resp, java.math.BigDecimal.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.math.BigDecimal marketValueByDate(String userName, String password, int DEven) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[29]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/MarketValueByDate");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "MarketValueByDate"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {userName, password, new Integer(DEven)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.math.BigDecimal) _resp;
            } catch (Exception _exception) {
                return (java.math.BigDecimal) org.apache.axis.utils.JavaUtils.convert(_resp, java.math.BigDecimal.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public FutureInformationResponseFutureInformationResult futureInformation(String userName, String password, int DEven) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[30]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/FutureInformation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "FutureInformation"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {userName, password, new Integer(DEven)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (FutureInformationResponseFutureInformationResult) _resp;
            } catch (Exception _exception) {
                return (FutureInformationResponseFutureInformationResult) org.apache.axis.utils.JavaUtils.convert(_resp, FutureInformationResponseFutureInformationResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public AdjPriceResponseAdjPriceResult adjPrice(String userName, String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[31]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/AdjPrice");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "AdjPrice"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {userName, password, flow});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (AdjPriceResponseAdjPriceResult) _resp;
            } catch (Exception _exception) {
                return (AdjPriceResponseAdjPriceResult) org.apache.axis.utils.JavaUtils.convert(_resp, AdjPriceResponseAdjPriceResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public ShareChangeResponseShareChangeResult shareChange(String userName, String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[32]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/ShareChange");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "ShareChange"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {userName, password, flow});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (ShareChangeResponseShareChangeResult) _resp;
            } catch (Exception _exception) {
                return (ShareChangeResponseShareChangeResult) org.apache.axis.utils.JavaUtils.convert(_resp, ShareChangeResponseShareChangeResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public MarketActivityLastResponseMarketActivityLastResult marketActivityLast(String userName, String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[33]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/MarketActivityLast");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "MarketActivityLast"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {userName, password, flow});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (MarketActivityLastResponseMarketActivityLastResult) _resp;
            } catch (Exception _exception) {
                return (MarketActivityLastResponseMarketActivityLastResult) org.apache.axis.utils.JavaUtils.convert(_resp, MarketActivityLastResponseMarketActivityLastResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public SectorStateResponseSectorStateResult sectorState(String userName, String password, int DEven) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[34]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/SectorState");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "SectorState"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {userName, password, new Integer(DEven)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (SectorStateResponseSectorStateResult) _resp;
            } catch (Exception _exception) {
                return (SectorStateResponseSectorStateResult) org.apache.axis.utils.JavaUtils.convert(_resp, SectorStateResponseSectorStateResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public NSCStartResponseNSCStartResult NSCStart(String userName, String password) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[35]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tsetmc.com/NSCStart");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tsetmc.com/", "NSCStart"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {userName, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (NSCStartResponseNSCStartResult) _resp;
            } catch (Exception _exception) {
                return (NSCStartResponseNSCStartResult) org.apache.axis.utils.JavaUtils.convert(_resp, NSCStartResponseNSCStartResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
