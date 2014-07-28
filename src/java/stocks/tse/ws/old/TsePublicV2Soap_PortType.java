/**
 * TsePublicV2Soap_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package stocks.tse.ws.old;

public interface TsePublicV2Soap_PortType extends java.rmi.Remote {
    public BoardResponseBoardResult board(String userName, String password) throws java.rmi.RemoteException;
    public SectorResponseSectorResult sector(String userName, String password) throws java.rmi.RemoteException;
    public SubSectorResponseSubSectorResult subSector(String userName, String password) throws java.rmi.RemoteException;
    public TradeLastDayResponseTradeLastDayResult tradeLastDay(String userName, String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException;
    public TradeOneDayResponseTradeOneDayResult tradeOneDay(String userName, String password, int selDate, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException;
    public TradeOneDayAllResponseTradeOneDayAllResult tradeOneDayAll(String userName, String password, int selDate, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException;
    public InstTradeResponseInstTradeResult instTrade(String userName, String password, long inscode, int dateFrom, int dateTo) throws java.rmi.RemoteException;
    public MarketActivityDailyResponseMarketActivityDailyResult marketActivityDaily(String userName, String password, int dateFrom, int dateTo) throws java.rmi.RemoteException;
    public BestLimitsAllInsResponseBestLimitsAllInsResult bestLimitsAllIns(String userName, String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException;
    public BestLimitOneInsResponseBestLimitOneInsResult bestLimitOneIns(String userName, String password, long insCode) throws java.rmi.RemoteException;
    public InstWithBestLimitResponseInstWithBestLimitResult instWithBestLimit(String userName, String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException;
    public InstrumentResponseInstrumentResult instrument(String userName, String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException;
    public InstrumentFilterByDateResponseInstrumentFilterByDateResult instrumentFilterByDate(String userName, String password, int DEven, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException;
    public CompanyResponseCompanyResult company(String userName, String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException;
    public InstrumentDetailOneInsResponseInstrumentDetailOneInsResult instrumentDetailOneIns(String userName, String password, String CIsin, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException;
    public InstrumentDetailAllInsResponseInstrumentDetailAllInsResult instrumentDetailAllIns(String userName, String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException;
    public StateTypeResponseStateTypeResult stateType(String userName, String password) throws java.rmi.RemoteException;
    public InstrumentStateDescOneInsResponseInstrumentStateDescOneInsResult instrumentStateDescOneIns(String userName, String password, long insCode, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException;
    public InstrumentsStateDescAllInsResponseInstrumentsStateDescAllInsResult instrumentsStateDescAllIns(String userName, String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException;
    public InstrumentsStateResponseInstrumentsStateResult instrumentsState(String userName, String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException;
    public IndexB1LastDayLastDataResponseIndexB1LastDayLastDataResult indexB1LastDayLastData(String userName, String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException;
    public IndexB1LastDayOneInstResponseIndexB1LastDayOneInstResult indexB1LastDayOneInst(String userName, String password, long idxCode, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException;
    public IndexB2ResponseIndexB2Result indexB2(String userName, String password, int DEven) throws java.rmi.RemoteException;
    public IndexInstrumentResponseIndexInstrumentResult indexInstrument(String userName, String password, long idxCode, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException;
    public InstAffectResponseInstAffectResult instAffect(String userName, String password) throws java.rmi.RemoteException;
    public TOPResponseTOPResult TOP(String userName, String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException;
    public MsgResponseMsgResult msg(String userName, String password) throws java.rmi.RemoteException;
    public StaticThresholdsResponseStaticThresholdsResult staticThresholds(String userName, String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException;
    public java.math.BigDecimal marketValue(String userName, String password) throws java.rmi.RemoteException;
    public java.math.BigDecimal marketValueByDate(String userName, String password, int DEven) throws java.rmi.RemoteException;
    public FutureInformationResponseFutureInformationResult futureInformation(String userName, String password, int DEven) throws java.rmi.RemoteException;
    public AdjPriceResponseAdjPriceResult adjPrice(String userName, String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException;
    public ShareChangeResponseShareChangeResult shareChange(String userName, String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException;
    public MarketActivityLastResponseMarketActivityLastResult marketActivityLast(String userName, String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException;
    public SectorStateResponseSectorStateResult sectorState(String userName, String password, int DEven) throws java.rmi.RemoteException;
    public NSCStartResponseNSCStartResult NSCStart(String userName, String password) throws java.rmi.RemoteException;
}
