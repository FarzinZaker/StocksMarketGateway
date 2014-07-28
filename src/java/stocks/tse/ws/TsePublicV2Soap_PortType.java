/**
 * TsePublicV2Soap_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package stocks.tse.ws;

public interface TsePublicV2Soap_PortType extends java.rmi.Remote {
    public stocks.tse.ws.AuctionResponseAuctionResult auction(java.lang.String userName, java.lang.String password, long from) throws java.rmi.RemoteException;
    public stocks.tse.ws.BoardResponseBoardResult board(java.lang.String userName, java.lang.String password) throws java.rmi.RemoteException;
    public stocks.tse.ws.SectorResponseSectorResult sector(java.lang.String userName, java.lang.String password) throws java.rmi.RemoteException;
    public stocks.tse.ws.SubSectorResponseSubSectorResult subSector(java.lang.String userName, java.lang.String password) throws java.rmi.RemoteException;
    public stocks.tse.ws.TradeLastDayResponseTradeLastDayResult tradeLastDay(java.lang.String userName, java.lang.String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException;
    public stocks.tse.ws.TradeOneDayResponseTradeOneDayResult tradeOneDay(java.lang.String userName, java.lang.String password, int selDate, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException;
    public stocks.tse.ws.TradeOneDayAllResponseTradeOneDayAllResult tradeOneDayAll(java.lang.String userName, java.lang.String password, int selDate, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException;
    public stocks.tse.ws.InstTradeResponseInstTradeResult instTrade(java.lang.String userName, java.lang.String password, long inscode, int dateFrom, int dateTo) throws java.rmi.RemoteException;
    public stocks.tse.ws.MarketActivityDailyResponseMarketActivityDailyResult marketActivityDaily(java.lang.String userName, java.lang.String password, int dateFrom, int dateTo) throws java.rmi.RemoteException;
    public stocks.tse.ws.BestLimitsAllInsResponseBestLimitsAllInsResult bestLimitsAllIns(java.lang.String userName, java.lang.String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException;
    public stocks.tse.ws.BestLimitOneInsResponseBestLimitOneInsResult bestLimitOneIns(java.lang.String userName, java.lang.String password, long insCode) throws java.rmi.RemoteException;
    public stocks.tse.ws.InstWithBestLimitResponseInstWithBestLimitResult instWithBestLimit(java.lang.String userName, java.lang.String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException;
    public stocks.tse.ws.InstrumentResponseInstrumentResult instrument(java.lang.String userName, java.lang.String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException;
    public stocks.tse.ws.InstrumentFilterByDateResponseInstrumentFilterByDateResult instrumentFilterByDate(java.lang.String userName, java.lang.String password, int DEven, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException;
    public stocks.tse.ws.CompanyResponseCompanyResult company(java.lang.String userName, java.lang.String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException;
    public stocks.tse.ws.InstrumentsStateResponseInstrumentsStateResult instrumentsState(java.lang.String userName, java.lang.String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException;
    public stocks.tse.ws.IndexB1LastDayLastDataResponseIndexB1LastDayLastDataResult indexB1LastDayLastData(java.lang.String userName, java.lang.String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException;
    public stocks.tse.ws.IndexB1LastDayOneInstResponseIndexB1LastDayOneInstResult indexB1LastDayOneInst(java.lang.String userName, java.lang.String password, long idxCode, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException;
    public stocks.tse.ws.IndexB2ResponseIndexB2Result indexB2(java.lang.String userName, java.lang.String password, int DEven) throws java.rmi.RemoteException;
    public stocks.tse.ws.IndexInstrumentResponseIndexInstrumentResult indexInstrument(java.lang.String userName, java.lang.String password, long idxCode, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException;
    public stocks.tse.ws.InstAffectResponseInstAffectResult instAffect(java.lang.String userName, java.lang.String password) throws java.rmi.RemoteException;
    public stocks.tse.ws.TOPResponseTOPResult TOP(java.lang.String userName, java.lang.String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException;
    public stocks.tse.ws.MsgResponseMsgResult msg(java.lang.String userName, java.lang.String password) throws java.rmi.RemoteException;
    public stocks.tse.ws.StaticThresholdsResponseStaticThresholdsResult staticThresholds(java.lang.String userName, java.lang.String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException;
    public java.math.BigDecimal marketValue(java.lang.String userName, java.lang.String password) throws java.rmi.RemoteException;
    public java.math.BigDecimal marketValueByDate(java.lang.String userName, java.lang.String password, int DEven) throws java.rmi.RemoteException;
    public stocks.tse.ws.FutureInformationResponseFutureInformationResult futureInformation(java.lang.String userName, java.lang.String password, int DEven) throws java.rmi.RemoteException;
    public stocks.tse.ws.AdjPriceResponseAdjPriceResult adjPrice(java.lang.String userName, java.lang.String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException;
    public stocks.tse.ws.ShareChangeResponseShareChangeResult shareChange(java.lang.String userName, java.lang.String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException;
    public stocks.tse.ws.MarketActivityLastResponseMarketActivityLastResult marketActivityLast(java.lang.String userName, java.lang.String password, org.apache.axis.types.UnsignedByte flow) throws java.rmi.RemoteException;
    public stocks.tse.ws.SectorStateResponseSectorStateResult sectorState(java.lang.String userName, java.lang.String password, int DEven) throws java.rmi.RemoteException;
    public stocks.tse.ws.NSCStartResponseNSCStartResult NSCStart(java.lang.String userName, java.lang.String password) throws java.rmi.RemoteException;
    public stocks.tse.ws.PowerInstrumentResponsePowerInstrumentResult powerInstrument(java.lang.String userName, java.lang.String password) throws java.rmi.RemoteException;
    public stocks.tse.ws.PowerInstrumentHistoryResponsePowerInstrumentHistoryResult powerInstrumentHistory(java.lang.String userName, java.lang.String password, long from) throws java.rmi.RemoteException;
}
