/**
 * Future_Market_Data.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package stocks.rate.ws;

public class Future_Market_Data  implements java.io.Serializable {
    private java.util.Calendar lastTradingDate;

    private java.lang.String persianLastTradingDate;

    private java.lang.String contractCode;

    private java.lang.String contractDescription;

    private double contractSize;

    private java.lang.String contractSizeUnitFaDesc;

    private java.lang.String contractSizeUnitEnDesc;

    private java.lang.String contractCurrencyFaDesc;

    private java.lang.String contractCurrencyEnDesc;

    private int contractCurrencyDecimalPlaces;

    private java.math.BigDecimal lastSettlementPrice;

    private java.util.Calendar lastSettlementPriceDate;

    private java.lang.String persianLastSettlementPriceDate;

    private int yesterdayOpenInterests;

    private int bidTotalVolume;

    private int bidVolume1;

    private java.math.BigDecimal bidPrice1;

    private int bidVolume2;

    private java.math.BigDecimal bidPrice2;

    private int bidVolume3;

    private java.math.BigDecimal bidPrice3;

    private int bidVolume4;

    private java.math.BigDecimal bidPrice4;

    private int bidVolume5;

    private java.math.BigDecimal bidPrice5;

    private int askTotalVolume;

    private int askVolume1;

    private double askPrice1;

    private int askVolume2;

    private double askPrice2;

    private int askVolume3;

    private double askPrice3;

    private int askVolume4;

    private double askPrice4;

    private int askVolume5;

    private double askPrice5;

    private java.util.Calendar ordersDateTime;

    private java.lang.String persianOrdersDateTime;

    private double firstTradedPrice;

    private java.util.Calendar firstTradedPriceTime;

    private java.lang.String persianFirstTradedPriceTime;

    private double c_FirstTradedPriceChanges;

    private double c_FirstTradedPriceChangesPercent;

    private double lastTradedPrice;

    private java.util.Calendar lastTradedPriceTime;

    private java.lang.String persianLastTradedPriceTime;

    private double c_LastTradedPriceChanges;

    private double c_LastTradedPriceChangesPercent;

    private double highTradedPrice;

    private double c_HighTradedPriceChanges;

    private double c_HighTradedPriceChangesPercent;

    private double lowTradedPrice;

    private double c_LowTradedPriceChanges;

    private double c_LowTradedPriceChangesPercent;

    private double averageTradedPrice;

    private double openingPrice;

    private double closingPrice;

    private int tradesCount;

    private int tradesVolume;

    private double tradesValue;

    private java.lang.String tradesValueCurrencyFaDesc;

    private java.lang.String tradesValueCurrencyEnDesc;

    private int openInterests;

    private double c_OpenInterestsChanges;

    private double openInterestsChangesPercent;

    private java.util.Calendar lastUpdate;

    private java.lang.String persianLastUpdate;

    private boolean expired;

    public Future_Market_Data() {
    }

    public Future_Market_Data(
           java.util.Calendar lastTradingDate,
           java.lang.String persianLastTradingDate,
           java.lang.String contractCode,
           java.lang.String contractDescription,
           double contractSize,
           java.lang.String contractSizeUnitFaDesc,
           java.lang.String contractSizeUnitEnDesc,
           java.lang.String contractCurrencyFaDesc,
           java.lang.String contractCurrencyEnDesc,
           int contractCurrencyDecimalPlaces,
           java.math.BigDecimal lastSettlementPrice,
           java.util.Calendar lastSettlementPriceDate,
           java.lang.String persianLastSettlementPriceDate,
           int yesterdayOpenInterests,
           int bidTotalVolume,
           int bidVolume1,
           java.math.BigDecimal bidPrice1,
           int bidVolume2,
           java.math.BigDecimal bidPrice2,
           int bidVolume3,
           java.math.BigDecimal bidPrice3,
           int bidVolume4,
           java.math.BigDecimal bidPrice4,
           int bidVolume5,
           java.math.BigDecimal bidPrice5,
           int askTotalVolume,
           int askVolume1,
           double askPrice1,
           int askVolume2,
           double askPrice2,
           int askVolume3,
           double askPrice3,
           int askVolume4,
           double askPrice4,
           int askVolume5,
           double askPrice5,
           java.util.Calendar ordersDateTime,
           java.lang.String persianOrdersDateTime,
           double firstTradedPrice,
           java.util.Calendar firstTradedPriceTime,
           java.lang.String persianFirstTradedPriceTime,
           double c_FirstTradedPriceChanges,
           double c_FirstTradedPriceChangesPercent,
           double lastTradedPrice,
           java.util.Calendar lastTradedPriceTime,
           java.lang.String persianLastTradedPriceTime,
           double c_LastTradedPriceChanges,
           double c_LastTradedPriceChangesPercent,
           double highTradedPrice,
           double c_HighTradedPriceChanges,
           double c_HighTradedPriceChangesPercent,
           double lowTradedPrice,
           double c_LowTradedPriceChanges,
           double c_LowTradedPriceChangesPercent,
           double averageTradedPrice,
           double openingPrice,
           double closingPrice,
           int tradesCount,
           int tradesVolume,
           double tradesValue,
           java.lang.String tradesValueCurrencyFaDesc,
           java.lang.String tradesValueCurrencyEnDesc,
           int openInterests,
           double c_OpenInterestsChanges,
           double openInterestsChangesPercent,
           java.util.Calendar lastUpdate,
           java.lang.String persianLastUpdate,
           boolean expired) {
           this.lastTradingDate = lastTradingDate;
           this.persianLastTradingDate = persianLastTradingDate;
           this.contractCode = contractCode;
           this.contractDescription = contractDescription;
           this.contractSize = contractSize;
           this.contractSizeUnitFaDesc = contractSizeUnitFaDesc;
           this.contractSizeUnitEnDesc = contractSizeUnitEnDesc;
           this.contractCurrencyFaDesc = contractCurrencyFaDesc;
           this.contractCurrencyEnDesc = contractCurrencyEnDesc;
           this.contractCurrencyDecimalPlaces = contractCurrencyDecimalPlaces;
           this.lastSettlementPrice = lastSettlementPrice;
           this.lastSettlementPriceDate = lastSettlementPriceDate;
           this.persianLastSettlementPriceDate = persianLastSettlementPriceDate;
           this.yesterdayOpenInterests = yesterdayOpenInterests;
           this.bidTotalVolume = bidTotalVolume;
           this.bidVolume1 = bidVolume1;
           this.bidPrice1 = bidPrice1;
           this.bidVolume2 = bidVolume2;
           this.bidPrice2 = bidPrice2;
           this.bidVolume3 = bidVolume3;
           this.bidPrice3 = bidPrice3;
           this.bidVolume4 = bidVolume4;
           this.bidPrice4 = bidPrice4;
           this.bidVolume5 = bidVolume5;
           this.bidPrice5 = bidPrice5;
           this.askTotalVolume = askTotalVolume;
           this.askVolume1 = askVolume1;
           this.askPrice1 = askPrice1;
           this.askVolume2 = askVolume2;
           this.askPrice2 = askPrice2;
           this.askVolume3 = askVolume3;
           this.askPrice3 = askPrice3;
           this.askVolume4 = askVolume4;
           this.askPrice4 = askPrice4;
           this.askVolume5 = askVolume5;
           this.askPrice5 = askPrice5;
           this.ordersDateTime = ordersDateTime;
           this.persianOrdersDateTime = persianOrdersDateTime;
           this.firstTradedPrice = firstTradedPrice;
           this.firstTradedPriceTime = firstTradedPriceTime;
           this.persianFirstTradedPriceTime = persianFirstTradedPriceTime;
           this.c_FirstTradedPriceChanges = c_FirstTradedPriceChanges;
           this.c_FirstTradedPriceChangesPercent = c_FirstTradedPriceChangesPercent;
           this.lastTradedPrice = lastTradedPrice;
           this.lastTradedPriceTime = lastTradedPriceTime;
           this.persianLastTradedPriceTime = persianLastTradedPriceTime;
           this.c_LastTradedPriceChanges = c_LastTradedPriceChanges;
           this.c_LastTradedPriceChangesPercent = c_LastTradedPriceChangesPercent;
           this.highTradedPrice = highTradedPrice;
           this.c_HighTradedPriceChanges = c_HighTradedPriceChanges;
           this.c_HighTradedPriceChangesPercent = c_HighTradedPriceChangesPercent;
           this.lowTradedPrice = lowTradedPrice;
           this.c_LowTradedPriceChanges = c_LowTradedPriceChanges;
           this.c_LowTradedPriceChangesPercent = c_LowTradedPriceChangesPercent;
           this.averageTradedPrice = averageTradedPrice;
           this.openingPrice = openingPrice;
           this.closingPrice = closingPrice;
           this.tradesCount = tradesCount;
           this.tradesVolume = tradesVolume;
           this.tradesValue = tradesValue;
           this.tradesValueCurrencyFaDesc = tradesValueCurrencyFaDesc;
           this.tradesValueCurrencyEnDesc = tradesValueCurrencyEnDesc;
           this.openInterests = openInterests;
           this.c_OpenInterestsChanges = c_OpenInterestsChanges;
           this.openInterestsChangesPercent = openInterestsChangesPercent;
           this.lastUpdate = lastUpdate;
           this.persianLastUpdate = persianLastUpdate;
           this.expired = expired;
    }


    /**
     * Gets the lastTradingDate value for this Future_Market_Data.
     * 
     * @return lastTradingDate
     */
    public java.util.Calendar getLastTradingDate() {
        return lastTradingDate;
    }


    /**
     * Sets the lastTradingDate value for this Future_Market_Data.
     * 
     * @param lastTradingDate
     */
    public void setLastTradingDate(java.util.Calendar lastTradingDate) {
        this.lastTradingDate = lastTradingDate;
    }


    /**
     * Gets the persianLastTradingDate value for this Future_Market_Data.
     * 
     * @return persianLastTradingDate
     */
    public java.lang.String getPersianLastTradingDate() {
        return persianLastTradingDate;
    }


    /**
     * Sets the persianLastTradingDate value for this Future_Market_Data.
     * 
     * @param persianLastTradingDate
     */
    public void setPersianLastTradingDate(java.lang.String persianLastTradingDate) {
        this.persianLastTradingDate = persianLastTradingDate;
    }


    /**
     * Gets the contractCode value for this Future_Market_Data.
     * 
     * @return contractCode
     */
    public java.lang.String getContractCode() {
        return contractCode;
    }


    /**
     * Sets the contractCode value for this Future_Market_Data.
     * 
     * @param contractCode
     */
    public void setContractCode(java.lang.String contractCode) {
        this.contractCode = contractCode;
    }


    /**
     * Gets the contractDescription value for this Future_Market_Data.
     * 
     * @return contractDescription
     */
    public java.lang.String getContractDescription() {
        return contractDescription;
    }


    /**
     * Sets the contractDescription value for this Future_Market_Data.
     * 
     * @param contractDescription
     */
    public void setContractDescription(java.lang.String contractDescription) {
        this.contractDescription = contractDescription;
    }


    /**
     * Gets the contractSize value for this Future_Market_Data.
     * 
     * @return contractSize
     */
    public double getContractSize() {
        return contractSize;
    }


    /**
     * Sets the contractSize value for this Future_Market_Data.
     * 
     * @param contractSize
     */
    public void setContractSize(double contractSize) {
        this.contractSize = contractSize;
    }


    /**
     * Gets the contractSizeUnitFaDesc value for this Future_Market_Data.
     * 
     * @return contractSizeUnitFaDesc
     */
    public java.lang.String getContractSizeUnitFaDesc() {
        return contractSizeUnitFaDesc;
    }


    /**
     * Sets the contractSizeUnitFaDesc value for this Future_Market_Data.
     * 
     * @param contractSizeUnitFaDesc
     */
    public void setContractSizeUnitFaDesc(java.lang.String contractSizeUnitFaDesc) {
        this.contractSizeUnitFaDesc = contractSizeUnitFaDesc;
    }


    /**
     * Gets the contractSizeUnitEnDesc value for this Future_Market_Data.
     * 
     * @return contractSizeUnitEnDesc
     */
    public java.lang.String getContractSizeUnitEnDesc() {
        return contractSizeUnitEnDesc;
    }


    /**
     * Sets the contractSizeUnitEnDesc value for this Future_Market_Data.
     * 
     * @param contractSizeUnitEnDesc
     */
    public void setContractSizeUnitEnDesc(java.lang.String contractSizeUnitEnDesc) {
        this.contractSizeUnitEnDesc = contractSizeUnitEnDesc;
    }


    /**
     * Gets the contractCurrencyFaDesc value for this Future_Market_Data.
     * 
     * @return contractCurrencyFaDesc
     */
    public java.lang.String getContractCurrencyFaDesc() {
        return contractCurrencyFaDesc;
    }


    /**
     * Sets the contractCurrencyFaDesc value for this Future_Market_Data.
     * 
     * @param contractCurrencyFaDesc
     */
    public void setContractCurrencyFaDesc(java.lang.String contractCurrencyFaDesc) {
        this.contractCurrencyFaDesc = contractCurrencyFaDesc;
    }


    /**
     * Gets the contractCurrencyEnDesc value for this Future_Market_Data.
     * 
     * @return contractCurrencyEnDesc
     */
    public java.lang.String getContractCurrencyEnDesc() {
        return contractCurrencyEnDesc;
    }


    /**
     * Sets the contractCurrencyEnDesc value for this Future_Market_Data.
     * 
     * @param contractCurrencyEnDesc
     */
    public void setContractCurrencyEnDesc(java.lang.String contractCurrencyEnDesc) {
        this.contractCurrencyEnDesc = contractCurrencyEnDesc;
    }


    /**
     * Gets the contractCurrencyDecimalPlaces value for this Future_Market_Data.
     * 
     * @return contractCurrencyDecimalPlaces
     */
    public int getContractCurrencyDecimalPlaces() {
        return contractCurrencyDecimalPlaces;
    }


    /**
     * Sets the contractCurrencyDecimalPlaces value for this Future_Market_Data.
     * 
     * @param contractCurrencyDecimalPlaces
     */
    public void setContractCurrencyDecimalPlaces(int contractCurrencyDecimalPlaces) {
        this.contractCurrencyDecimalPlaces = contractCurrencyDecimalPlaces;
    }


    /**
     * Gets the lastSettlementPrice value for this Future_Market_Data.
     * 
     * @return lastSettlementPrice
     */
    public java.math.BigDecimal getLastSettlementPrice() {
        return lastSettlementPrice;
    }


    /**
     * Sets the lastSettlementPrice value for this Future_Market_Data.
     * 
     * @param lastSettlementPrice
     */
    public void setLastSettlementPrice(java.math.BigDecimal lastSettlementPrice) {
        this.lastSettlementPrice = lastSettlementPrice;
    }


    /**
     * Gets the lastSettlementPriceDate value for this Future_Market_Data.
     * 
     * @return lastSettlementPriceDate
     */
    public java.util.Calendar getLastSettlementPriceDate() {
        return lastSettlementPriceDate;
    }


    /**
     * Sets the lastSettlementPriceDate value for this Future_Market_Data.
     * 
     * @param lastSettlementPriceDate
     */
    public void setLastSettlementPriceDate(java.util.Calendar lastSettlementPriceDate) {
        this.lastSettlementPriceDate = lastSettlementPriceDate;
    }


    /**
     * Gets the persianLastSettlementPriceDate value for this Future_Market_Data.
     * 
     * @return persianLastSettlementPriceDate
     */
    public java.lang.String getPersianLastSettlementPriceDate() {
        return persianLastSettlementPriceDate;
    }


    /**
     * Sets the persianLastSettlementPriceDate value for this Future_Market_Data.
     * 
     * @param persianLastSettlementPriceDate
     */
    public void setPersianLastSettlementPriceDate(java.lang.String persianLastSettlementPriceDate) {
        this.persianLastSettlementPriceDate = persianLastSettlementPriceDate;
    }


    /**
     * Gets the yesterdayOpenInterests value for this Future_Market_Data.
     * 
     * @return yesterdayOpenInterests
     */
    public int getYesterdayOpenInterests() {
        return yesterdayOpenInterests;
    }


    /**
     * Sets the yesterdayOpenInterests value for this Future_Market_Data.
     * 
     * @param yesterdayOpenInterests
     */
    public void setYesterdayOpenInterests(int yesterdayOpenInterests) {
        this.yesterdayOpenInterests = yesterdayOpenInterests;
    }


    /**
     * Gets the bidTotalVolume value for this Future_Market_Data.
     * 
     * @return bidTotalVolume
     */
    public int getBidTotalVolume() {
        return bidTotalVolume;
    }


    /**
     * Sets the bidTotalVolume value for this Future_Market_Data.
     * 
     * @param bidTotalVolume
     */
    public void setBidTotalVolume(int bidTotalVolume) {
        this.bidTotalVolume = bidTotalVolume;
    }


    /**
     * Gets the bidVolume1 value for this Future_Market_Data.
     * 
     * @return bidVolume1
     */
    public int getBidVolume1() {
        return bidVolume1;
    }


    /**
     * Sets the bidVolume1 value for this Future_Market_Data.
     * 
     * @param bidVolume1
     */
    public void setBidVolume1(int bidVolume1) {
        this.bidVolume1 = bidVolume1;
    }


    /**
     * Gets the bidPrice1 value for this Future_Market_Data.
     * 
     * @return bidPrice1
     */
    public java.math.BigDecimal getBidPrice1() {
        return bidPrice1;
    }


    /**
     * Sets the bidPrice1 value for this Future_Market_Data.
     * 
     * @param bidPrice1
     */
    public void setBidPrice1(java.math.BigDecimal bidPrice1) {
        this.bidPrice1 = bidPrice1;
    }


    /**
     * Gets the bidVolume2 value for this Future_Market_Data.
     * 
     * @return bidVolume2
     */
    public int getBidVolume2() {
        return bidVolume2;
    }


    /**
     * Sets the bidVolume2 value for this Future_Market_Data.
     * 
     * @param bidVolume2
     */
    public void setBidVolume2(int bidVolume2) {
        this.bidVolume2 = bidVolume2;
    }


    /**
     * Gets the bidPrice2 value for this Future_Market_Data.
     * 
     * @return bidPrice2
     */
    public java.math.BigDecimal getBidPrice2() {
        return bidPrice2;
    }


    /**
     * Sets the bidPrice2 value for this Future_Market_Data.
     * 
     * @param bidPrice2
     */
    public void setBidPrice2(java.math.BigDecimal bidPrice2) {
        this.bidPrice2 = bidPrice2;
    }


    /**
     * Gets the bidVolume3 value for this Future_Market_Data.
     * 
     * @return bidVolume3
     */
    public int getBidVolume3() {
        return bidVolume3;
    }


    /**
     * Sets the bidVolume3 value for this Future_Market_Data.
     * 
     * @param bidVolume3
     */
    public void setBidVolume3(int bidVolume3) {
        this.bidVolume3 = bidVolume3;
    }


    /**
     * Gets the bidPrice3 value for this Future_Market_Data.
     * 
     * @return bidPrice3
     */
    public java.math.BigDecimal getBidPrice3() {
        return bidPrice3;
    }


    /**
     * Sets the bidPrice3 value for this Future_Market_Data.
     * 
     * @param bidPrice3
     */
    public void setBidPrice3(java.math.BigDecimal bidPrice3) {
        this.bidPrice3 = bidPrice3;
    }


    /**
     * Gets the bidVolume4 value for this Future_Market_Data.
     * 
     * @return bidVolume4
     */
    public int getBidVolume4() {
        return bidVolume4;
    }


    /**
     * Sets the bidVolume4 value for this Future_Market_Data.
     * 
     * @param bidVolume4
     */
    public void setBidVolume4(int bidVolume4) {
        this.bidVolume4 = bidVolume4;
    }


    /**
     * Gets the bidPrice4 value for this Future_Market_Data.
     * 
     * @return bidPrice4
     */
    public java.math.BigDecimal getBidPrice4() {
        return bidPrice4;
    }


    /**
     * Sets the bidPrice4 value for this Future_Market_Data.
     * 
     * @param bidPrice4
     */
    public void setBidPrice4(java.math.BigDecimal bidPrice4) {
        this.bidPrice4 = bidPrice4;
    }


    /**
     * Gets the bidVolume5 value for this Future_Market_Data.
     * 
     * @return bidVolume5
     */
    public int getBidVolume5() {
        return bidVolume5;
    }


    /**
     * Sets the bidVolume5 value for this Future_Market_Data.
     * 
     * @param bidVolume5
     */
    public void setBidVolume5(int bidVolume5) {
        this.bidVolume5 = bidVolume5;
    }


    /**
     * Gets the bidPrice5 value for this Future_Market_Data.
     * 
     * @return bidPrice5
     */
    public java.math.BigDecimal getBidPrice5() {
        return bidPrice5;
    }


    /**
     * Sets the bidPrice5 value for this Future_Market_Data.
     * 
     * @param bidPrice5
     */
    public void setBidPrice5(java.math.BigDecimal bidPrice5) {
        this.bidPrice5 = bidPrice5;
    }


    /**
     * Gets the askTotalVolume value for this Future_Market_Data.
     * 
     * @return askTotalVolume
     */
    public int getAskTotalVolume() {
        return askTotalVolume;
    }


    /**
     * Sets the askTotalVolume value for this Future_Market_Data.
     * 
     * @param askTotalVolume
     */
    public void setAskTotalVolume(int askTotalVolume) {
        this.askTotalVolume = askTotalVolume;
    }


    /**
     * Gets the askVolume1 value for this Future_Market_Data.
     * 
     * @return askVolume1
     */
    public int getAskVolume1() {
        return askVolume1;
    }


    /**
     * Sets the askVolume1 value for this Future_Market_Data.
     * 
     * @param askVolume1
     */
    public void setAskVolume1(int askVolume1) {
        this.askVolume1 = askVolume1;
    }


    /**
     * Gets the askPrice1 value for this Future_Market_Data.
     * 
     * @return askPrice1
     */
    public double getAskPrice1() {
        return askPrice1;
    }


    /**
     * Sets the askPrice1 value for this Future_Market_Data.
     * 
     * @param askPrice1
     */
    public void setAskPrice1(double askPrice1) {
        this.askPrice1 = askPrice1;
    }


    /**
     * Gets the askVolume2 value for this Future_Market_Data.
     * 
     * @return askVolume2
     */
    public int getAskVolume2() {
        return askVolume2;
    }


    /**
     * Sets the askVolume2 value for this Future_Market_Data.
     * 
     * @param askVolume2
     */
    public void setAskVolume2(int askVolume2) {
        this.askVolume2 = askVolume2;
    }


    /**
     * Gets the askPrice2 value for this Future_Market_Data.
     * 
     * @return askPrice2
     */
    public double getAskPrice2() {
        return askPrice2;
    }


    /**
     * Sets the askPrice2 value for this Future_Market_Data.
     * 
     * @param askPrice2
     */
    public void setAskPrice2(double askPrice2) {
        this.askPrice2 = askPrice2;
    }


    /**
     * Gets the askVolume3 value for this Future_Market_Data.
     * 
     * @return askVolume3
     */
    public int getAskVolume3() {
        return askVolume3;
    }


    /**
     * Sets the askVolume3 value for this Future_Market_Data.
     * 
     * @param askVolume3
     */
    public void setAskVolume3(int askVolume3) {
        this.askVolume3 = askVolume3;
    }


    /**
     * Gets the askPrice3 value for this Future_Market_Data.
     * 
     * @return askPrice3
     */
    public double getAskPrice3() {
        return askPrice3;
    }


    /**
     * Sets the askPrice3 value for this Future_Market_Data.
     * 
     * @param askPrice3
     */
    public void setAskPrice3(double askPrice3) {
        this.askPrice3 = askPrice3;
    }


    /**
     * Gets the askVolume4 value for this Future_Market_Data.
     * 
     * @return askVolume4
     */
    public int getAskVolume4() {
        return askVolume4;
    }


    /**
     * Sets the askVolume4 value for this Future_Market_Data.
     * 
     * @param askVolume4
     */
    public void setAskVolume4(int askVolume4) {
        this.askVolume4 = askVolume4;
    }


    /**
     * Gets the askPrice4 value for this Future_Market_Data.
     * 
     * @return askPrice4
     */
    public double getAskPrice4() {
        return askPrice4;
    }


    /**
     * Sets the askPrice4 value for this Future_Market_Data.
     * 
     * @param askPrice4
     */
    public void setAskPrice4(double askPrice4) {
        this.askPrice4 = askPrice4;
    }


    /**
     * Gets the askVolume5 value for this Future_Market_Data.
     * 
     * @return askVolume5
     */
    public int getAskVolume5() {
        return askVolume5;
    }


    /**
     * Sets the askVolume5 value for this Future_Market_Data.
     * 
     * @param askVolume5
     */
    public void setAskVolume5(int askVolume5) {
        this.askVolume5 = askVolume5;
    }


    /**
     * Gets the askPrice5 value for this Future_Market_Data.
     * 
     * @return askPrice5
     */
    public double getAskPrice5() {
        return askPrice5;
    }


    /**
     * Sets the askPrice5 value for this Future_Market_Data.
     * 
     * @param askPrice5
     */
    public void setAskPrice5(double askPrice5) {
        this.askPrice5 = askPrice5;
    }


    /**
     * Gets the ordersDateTime value for this Future_Market_Data.
     * 
     * @return ordersDateTime
     */
    public java.util.Calendar getOrdersDateTime() {
        return ordersDateTime;
    }


    /**
     * Sets the ordersDateTime value for this Future_Market_Data.
     * 
     * @param ordersDateTime
     */
    public void setOrdersDateTime(java.util.Calendar ordersDateTime) {
        this.ordersDateTime = ordersDateTime;
    }


    /**
     * Gets the persianOrdersDateTime value for this Future_Market_Data.
     * 
     * @return persianOrdersDateTime
     */
    public java.lang.String getPersianOrdersDateTime() {
        return persianOrdersDateTime;
    }


    /**
     * Sets the persianOrdersDateTime value for this Future_Market_Data.
     * 
     * @param persianOrdersDateTime
     */
    public void setPersianOrdersDateTime(java.lang.String persianOrdersDateTime) {
        this.persianOrdersDateTime = persianOrdersDateTime;
    }


    /**
     * Gets the firstTradedPrice value for this Future_Market_Data.
     * 
     * @return firstTradedPrice
     */
    public double getFirstTradedPrice() {
        return firstTradedPrice;
    }


    /**
     * Sets the firstTradedPrice value for this Future_Market_Data.
     * 
     * @param firstTradedPrice
     */
    public void setFirstTradedPrice(double firstTradedPrice) {
        this.firstTradedPrice = firstTradedPrice;
    }


    /**
     * Gets the firstTradedPriceTime value for this Future_Market_Data.
     * 
     * @return firstTradedPriceTime
     */
    public java.util.Calendar getFirstTradedPriceTime() {
        return firstTradedPriceTime;
    }


    /**
     * Sets the firstTradedPriceTime value for this Future_Market_Data.
     * 
     * @param firstTradedPriceTime
     */
    public void setFirstTradedPriceTime(java.util.Calendar firstTradedPriceTime) {
        this.firstTradedPriceTime = firstTradedPriceTime;
    }


    /**
     * Gets the persianFirstTradedPriceTime value for this Future_Market_Data.
     * 
     * @return persianFirstTradedPriceTime
     */
    public java.lang.String getPersianFirstTradedPriceTime() {
        return persianFirstTradedPriceTime;
    }


    /**
     * Sets the persianFirstTradedPriceTime value for this Future_Market_Data.
     * 
     * @param persianFirstTradedPriceTime
     */
    public void setPersianFirstTradedPriceTime(java.lang.String persianFirstTradedPriceTime) {
        this.persianFirstTradedPriceTime = persianFirstTradedPriceTime;
    }


    /**
     * Gets the c_FirstTradedPriceChanges value for this Future_Market_Data.
     * 
     * @return c_FirstTradedPriceChanges
     */
    public double getC_FirstTradedPriceChanges() {
        return c_FirstTradedPriceChanges;
    }


    /**
     * Sets the c_FirstTradedPriceChanges value for this Future_Market_Data.
     * 
     * @param c_FirstTradedPriceChanges
     */
    public void setC_FirstTradedPriceChanges(double c_FirstTradedPriceChanges) {
        this.c_FirstTradedPriceChanges = c_FirstTradedPriceChanges;
    }


    /**
     * Gets the c_FirstTradedPriceChangesPercent value for this Future_Market_Data.
     * 
     * @return c_FirstTradedPriceChangesPercent
     */
    public double getC_FirstTradedPriceChangesPercent() {
        return c_FirstTradedPriceChangesPercent;
    }


    /**
     * Sets the c_FirstTradedPriceChangesPercent value for this Future_Market_Data.
     * 
     * @param c_FirstTradedPriceChangesPercent
     */
    public void setC_FirstTradedPriceChangesPercent(double c_FirstTradedPriceChangesPercent) {
        this.c_FirstTradedPriceChangesPercent = c_FirstTradedPriceChangesPercent;
    }


    /**
     * Gets the lastTradedPrice value for this Future_Market_Data.
     * 
     * @return lastTradedPrice
     */
    public double getLastTradedPrice() {
        return lastTradedPrice;
    }


    /**
     * Sets the lastTradedPrice value for this Future_Market_Data.
     * 
     * @param lastTradedPrice
     */
    public void setLastTradedPrice(double lastTradedPrice) {
        this.lastTradedPrice = lastTradedPrice;
    }


    /**
     * Gets the lastTradedPriceTime value for this Future_Market_Data.
     * 
     * @return lastTradedPriceTime
     */
    public java.util.Calendar getLastTradedPriceTime() {
        return lastTradedPriceTime;
    }


    /**
     * Sets the lastTradedPriceTime value for this Future_Market_Data.
     * 
     * @param lastTradedPriceTime
     */
    public void setLastTradedPriceTime(java.util.Calendar lastTradedPriceTime) {
        this.lastTradedPriceTime = lastTradedPriceTime;
    }


    /**
     * Gets the persianLastTradedPriceTime value for this Future_Market_Data.
     * 
     * @return persianLastTradedPriceTime
     */
    public java.lang.String getPersianLastTradedPriceTime() {
        return persianLastTradedPriceTime;
    }


    /**
     * Sets the persianLastTradedPriceTime value for this Future_Market_Data.
     * 
     * @param persianLastTradedPriceTime
     */
    public void setPersianLastTradedPriceTime(java.lang.String persianLastTradedPriceTime) {
        this.persianLastTradedPriceTime = persianLastTradedPriceTime;
    }


    /**
     * Gets the c_LastTradedPriceChanges value for this Future_Market_Data.
     * 
     * @return c_LastTradedPriceChanges
     */
    public double getC_LastTradedPriceChanges() {
        return c_LastTradedPriceChanges;
    }


    /**
     * Sets the c_LastTradedPriceChanges value for this Future_Market_Data.
     * 
     * @param c_LastTradedPriceChanges
     */
    public void setC_LastTradedPriceChanges(double c_LastTradedPriceChanges) {
        this.c_LastTradedPriceChanges = c_LastTradedPriceChanges;
    }


    /**
     * Gets the c_LastTradedPriceChangesPercent value for this Future_Market_Data.
     * 
     * @return c_LastTradedPriceChangesPercent
     */
    public double getC_LastTradedPriceChangesPercent() {
        return c_LastTradedPriceChangesPercent;
    }


    /**
     * Sets the c_LastTradedPriceChangesPercent value for this Future_Market_Data.
     * 
     * @param c_LastTradedPriceChangesPercent
     */
    public void setC_LastTradedPriceChangesPercent(double c_LastTradedPriceChangesPercent) {
        this.c_LastTradedPriceChangesPercent = c_LastTradedPriceChangesPercent;
    }


    /**
     * Gets the highTradedPrice value for this Future_Market_Data.
     * 
     * @return highTradedPrice
     */
    public double getHighTradedPrice() {
        return highTradedPrice;
    }


    /**
     * Sets the highTradedPrice value for this Future_Market_Data.
     * 
     * @param highTradedPrice
     */
    public void setHighTradedPrice(double highTradedPrice) {
        this.highTradedPrice = highTradedPrice;
    }


    /**
     * Gets the c_HighTradedPriceChanges value for this Future_Market_Data.
     * 
     * @return c_HighTradedPriceChanges
     */
    public double getC_HighTradedPriceChanges() {
        return c_HighTradedPriceChanges;
    }


    /**
     * Sets the c_HighTradedPriceChanges value for this Future_Market_Data.
     * 
     * @param c_HighTradedPriceChanges
     */
    public void setC_HighTradedPriceChanges(double c_HighTradedPriceChanges) {
        this.c_HighTradedPriceChanges = c_HighTradedPriceChanges;
    }


    /**
     * Gets the c_HighTradedPriceChangesPercent value for this Future_Market_Data.
     * 
     * @return c_HighTradedPriceChangesPercent
     */
    public double getC_HighTradedPriceChangesPercent() {
        return c_HighTradedPriceChangesPercent;
    }


    /**
     * Sets the c_HighTradedPriceChangesPercent value for this Future_Market_Data.
     * 
     * @param c_HighTradedPriceChangesPercent
     */
    public void setC_HighTradedPriceChangesPercent(double c_HighTradedPriceChangesPercent) {
        this.c_HighTradedPriceChangesPercent = c_HighTradedPriceChangesPercent;
    }


    /**
     * Gets the lowTradedPrice value for this Future_Market_Data.
     * 
     * @return lowTradedPrice
     */
    public double getLowTradedPrice() {
        return lowTradedPrice;
    }


    /**
     * Sets the lowTradedPrice value for this Future_Market_Data.
     * 
     * @param lowTradedPrice
     */
    public void setLowTradedPrice(double lowTradedPrice) {
        this.lowTradedPrice = lowTradedPrice;
    }


    /**
     * Gets the c_LowTradedPriceChanges value for this Future_Market_Data.
     * 
     * @return c_LowTradedPriceChanges
     */
    public double getC_LowTradedPriceChanges() {
        return c_LowTradedPriceChanges;
    }


    /**
     * Sets the c_LowTradedPriceChanges value for this Future_Market_Data.
     * 
     * @param c_LowTradedPriceChanges
     */
    public void setC_LowTradedPriceChanges(double c_LowTradedPriceChanges) {
        this.c_LowTradedPriceChanges = c_LowTradedPriceChanges;
    }


    /**
     * Gets the c_LowTradedPriceChangesPercent value for this Future_Market_Data.
     * 
     * @return c_LowTradedPriceChangesPercent
     */
    public double getC_LowTradedPriceChangesPercent() {
        return c_LowTradedPriceChangesPercent;
    }


    /**
     * Sets the c_LowTradedPriceChangesPercent value for this Future_Market_Data.
     * 
     * @param c_LowTradedPriceChangesPercent
     */
    public void setC_LowTradedPriceChangesPercent(double c_LowTradedPriceChangesPercent) {
        this.c_LowTradedPriceChangesPercent = c_LowTradedPriceChangesPercent;
    }


    /**
     * Gets the averageTradedPrice value for this Future_Market_Data.
     * 
     * @return averageTradedPrice
     */
    public double getAverageTradedPrice() {
        return averageTradedPrice;
    }


    /**
     * Sets the averageTradedPrice value for this Future_Market_Data.
     * 
     * @param averageTradedPrice
     */
    public void setAverageTradedPrice(double averageTradedPrice) {
        this.averageTradedPrice = averageTradedPrice;
    }


    /**
     * Gets the openingPrice value for this Future_Market_Data.
     * 
     * @return openingPrice
     */
    public double getOpeningPrice() {
        return openingPrice;
    }


    /**
     * Sets the openingPrice value for this Future_Market_Data.
     * 
     * @param openingPrice
     */
    public void setOpeningPrice(double openingPrice) {
        this.openingPrice = openingPrice;
    }


    /**
     * Gets the closingPrice value for this Future_Market_Data.
     * 
     * @return closingPrice
     */
    public double getClosingPrice() {
        return closingPrice;
    }


    /**
     * Sets the closingPrice value for this Future_Market_Data.
     * 
     * @param closingPrice
     */
    public void setClosingPrice(double closingPrice) {
        this.closingPrice = closingPrice;
    }


    /**
     * Gets the tradesCount value for this Future_Market_Data.
     * 
     * @return tradesCount
     */
    public int getTradesCount() {
        return tradesCount;
    }


    /**
     * Sets the tradesCount value for this Future_Market_Data.
     * 
     * @param tradesCount
     */
    public void setTradesCount(int tradesCount) {
        this.tradesCount = tradesCount;
    }


    /**
     * Gets the tradesVolume value for this Future_Market_Data.
     * 
     * @return tradesVolume
     */
    public int getTradesVolume() {
        return tradesVolume;
    }


    /**
     * Sets the tradesVolume value for this Future_Market_Data.
     * 
     * @param tradesVolume
     */
    public void setTradesVolume(int tradesVolume) {
        this.tradesVolume = tradesVolume;
    }


    /**
     * Gets the tradesValue value for this Future_Market_Data.
     * 
     * @return tradesValue
     */
    public double getTradesValue() {
        return tradesValue;
    }


    /**
     * Sets the tradesValue value for this Future_Market_Data.
     * 
     * @param tradesValue
     */
    public void setTradesValue(double tradesValue) {
        this.tradesValue = tradesValue;
    }


    /**
     * Gets the tradesValueCurrencyFaDesc value for this Future_Market_Data.
     * 
     * @return tradesValueCurrencyFaDesc
     */
    public java.lang.String getTradesValueCurrencyFaDesc() {
        return tradesValueCurrencyFaDesc;
    }


    /**
     * Sets the tradesValueCurrencyFaDesc value for this Future_Market_Data.
     * 
     * @param tradesValueCurrencyFaDesc
     */
    public void setTradesValueCurrencyFaDesc(java.lang.String tradesValueCurrencyFaDesc) {
        this.tradesValueCurrencyFaDesc = tradesValueCurrencyFaDesc;
    }


    /**
     * Gets the tradesValueCurrencyEnDesc value for this Future_Market_Data.
     * 
     * @return tradesValueCurrencyEnDesc
     */
    public java.lang.String getTradesValueCurrencyEnDesc() {
        return tradesValueCurrencyEnDesc;
    }


    /**
     * Sets the tradesValueCurrencyEnDesc value for this Future_Market_Data.
     * 
     * @param tradesValueCurrencyEnDesc
     */
    public void setTradesValueCurrencyEnDesc(java.lang.String tradesValueCurrencyEnDesc) {
        this.tradesValueCurrencyEnDesc = tradesValueCurrencyEnDesc;
    }


    /**
     * Gets the openInterests value for this Future_Market_Data.
     * 
     * @return openInterests
     */
    public int getOpenInterests() {
        return openInterests;
    }


    /**
     * Sets the openInterests value for this Future_Market_Data.
     * 
     * @param openInterests
     */
    public void setOpenInterests(int openInterests) {
        this.openInterests = openInterests;
    }


    /**
     * Gets the c_OpenInterestsChanges value for this Future_Market_Data.
     * 
     * @return c_OpenInterestsChanges
     */
    public double getC_OpenInterestsChanges() {
        return c_OpenInterestsChanges;
    }


    /**
     * Sets the c_OpenInterestsChanges value for this Future_Market_Data.
     * 
     * @param c_OpenInterestsChanges
     */
    public void setC_OpenInterestsChanges(double c_OpenInterestsChanges) {
        this.c_OpenInterestsChanges = c_OpenInterestsChanges;
    }


    /**
     * Gets the openInterestsChangesPercent value for this Future_Market_Data.
     * 
     * @return openInterestsChangesPercent
     */
    public double getOpenInterestsChangesPercent() {
        return openInterestsChangesPercent;
    }


    /**
     * Sets the openInterestsChangesPercent value for this Future_Market_Data.
     * 
     * @param openInterestsChangesPercent
     */
    public void setOpenInterestsChangesPercent(double openInterestsChangesPercent) {
        this.openInterestsChangesPercent = openInterestsChangesPercent;
    }


    /**
     * Gets the lastUpdate value for this Future_Market_Data.
     * 
     * @return lastUpdate
     */
    public java.util.Calendar getLastUpdate() {
        return lastUpdate;
    }


    /**
     * Sets the lastUpdate value for this Future_Market_Data.
     * 
     * @param lastUpdate
     */
    public void setLastUpdate(java.util.Calendar lastUpdate) {
        this.lastUpdate = lastUpdate;
    }


    /**
     * Gets the persianLastUpdate value for this Future_Market_Data.
     * 
     * @return persianLastUpdate
     */
    public java.lang.String getPersianLastUpdate() {
        return persianLastUpdate;
    }


    /**
     * Sets the persianLastUpdate value for this Future_Market_Data.
     * 
     * @param persianLastUpdate
     */
    public void setPersianLastUpdate(java.lang.String persianLastUpdate) {
        this.persianLastUpdate = persianLastUpdate;
    }


    /**
     * Gets the expired value for this Future_Market_Data.
     * 
     * @return expired
     */
    public boolean isExpired() {
        return expired;
    }


    /**
     * Sets the expired value for this Future_Market_Data.
     * 
     * @param expired
     */
    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Future_Market_Data)) return false;
        Future_Market_Data other = (Future_Market_Data) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.lastTradingDate==null && other.getLastTradingDate()==null) || 
             (this.lastTradingDate!=null &&
              this.lastTradingDate.equals(other.getLastTradingDate()))) &&
            ((this.persianLastTradingDate==null && other.getPersianLastTradingDate()==null) || 
             (this.persianLastTradingDate!=null &&
              this.persianLastTradingDate.equals(other.getPersianLastTradingDate()))) &&
            ((this.contractCode==null && other.getContractCode()==null) || 
             (this.contractCode!=null &&
              this.contractCode.equals(other.getContractCode()))) &&
            ((this.contractDescription==null && other.getContractDescription()==null) || 
             (this.contractDescription!=null &&
              this.contractDescription.equals(other.getContractDescription()))) &&
            this.contractSize == other.getContractSize() &&
            ((this.contractSizeUnitFaDesc==null && other.getContractSizeUnitFaDesc()==null) || 
             (this.contractSizeUnitFaDesc!=null &&
              this.contractSizeUnitFaDesc.equals(other.getContractSizeUnitFaDesc()))) &&
            ((this.contractSizeUnitEnDesc==null && other.getContractSizeUnitEnDesc()==null) || 
             (this.contractSizeUnitEnDesc!=null &&
              this.contractSizeUnitEnDesc.equals(other.getContractSizeUnitEnDesc()))) &&
            ((this.contractCurrencyFaDesc==null && other.getContractCurrencyFaDesc()==null) || 
             (this.contractCurrencyFaDesc!=null &&
              this.contractCurrencyFaDesc.equals(other.getContractCurrencyFaDesc()))) &&
            ((this.contractCurrencyEnDesc==null && other.getContractCurrencyEnDesc()==null) || 
             (this.contractCurrencyEnDesc!=null &&
              this.contractCurrencyEnDesc.equals(other.getContractCurrencyEnDesc()))) &&
            this.contractCurrencyDecimalPlaces == other.getContractCurrencyDecimalPlaces() &&
            ((this.lastSettlementPrice==null && other.getLastSettlementPrice()==null) || 
             (this.lastSettlementPrice!=null &&
              this.lastSettlementPrice.equals(other.getLastSettlementPrice()))) &&
            ((this.lastSettlementPriceDate==null && other.getLastSettlementPriceDate()==null) || 
             (this.lastSettlementPriceDate!=null &&
              this.lastSettlementPriceDate.equals(other.getLastSettlementPriceDate()))) &&
            ((this.persianLastSettlementPriceDate==null && other.getPersianLastSettlementPriceDate()==null) || 
             (this.persianLastSettlementPriceDate!=null &&
              this.persianLastSettlementPriceDate.equals(other.getPersianLastSettlementPriceDate()))) &&
            this.yesterdayOpenInterests == other.getYesterdayOpenInterests() &&
            this.bidTotalVolume == other.getBidTotalVolume() &&
            this.bidVolume1 == other.getBidVolume1() &&
            ((this.bidPrice1==null && other.getBidPrice1()==null) || 
             (this.bidPrice1!=null &&
              this.bidPrice1.equals(other.getBidPrice1()))) &&
            this.bidVolume2 == other.getBidVolume2() &&
            ((this.bidPrice2==null && other.getBidPrice2()==null) || 
             (this.bidPrice2!=null &&
              this.bidPrice2.equals(other.getBidPrice2()))) &&
            this.bidVolume3 == other.getBidVolume3() &&
            ((this.bidPrice3==null && other.getBidPrice3()==null) || 
             (this.bidPrice3!=null &&
              this.bidPrice3.equals(other.getBidPrice3()))) &&
            this.bidVolume4 == other.getBidVolume4() &&
            ((this.bidPrice4==null && other.getBidPrice4()==null) || 
             (this.bidPrice4!=null &&
              this.bidPrice4.equals(other.getBidPrice4()))) &&
            this.bidVolume5 == other.getBidVolume5() &&
            ((this.bidPrice5==null && other.getBidPrice5()==null) || 
             (this.bidPrice5!=null &&
              this.bidPrice5.equals(other.getBidPrice5()))) &&
            this.askTotalVolume == other.getAskTotalVolume() &&
            this.askVolume1 == other.getAskVolume1() &&
            this.askPrice1 == other.getAskPrice1() &&
            this.askVolume2 == other.getAskVolume2() &&
            this.askPrice2 == other.getAskPrice2() &&
            this.askVolume3 == other.getAskVolume3() &&
            this.askPrice3 == other.getAskPrice3() &&
            this.askVolume4 == other.getAskVolume4() &&
            this.askPrice4 == other.getAskPrice4() &&
            this.askVolume5 == other.getAskVolume5() &&
            this.askPrice5 == other.getAskPrice5() &&
            ((this.ordersDateTime==null && other.getOrdersDateTime()==null) || 
             (this.ordersDateTime!=null &&
              this.ordersDateTime.equals(other.getOrdersDateTime()))) &&
            ((this.persianOrdersDateTime==null && other.getPersianOrdersDateTime()==null) || 
             (this.persianOrdersDateTime!=null &&
              this.persianOrdersDateTime.equals(other.getPersianOrdersDateTime()))) &&
            this.firstTradedPrice == other.getFirstTradedPrice() &&
            ((this.firstTradedPriceTime==null && other.getFirstTradedPriceTime()==null) || 
             (this.firstTradedPriceTime!=null &&
              this.firstTradedPriceTime.equals(other.getFirstTradedPriceTime()))) &&
            ((this.persianFirstTradedPriceTime==null && other.getPersianFirstTradedPriceTime()==null) || 
             (this.persianFirstTradedPriceTime!=null &&
              this.persianFirstTradedPriceTime.equals(other.getPersianFirstTradedPriceTime()))) &&
            this.c_FirstTradedPriceChanges == other.getC_FirstTradedPriceChanges() &&
            this.c_FirstTradedPriceChangesPercent == other.getC_FirstTradedPriceChangesPercent() &&
            this.lastTradedPrice == other.getLastTradedPrice() &&
            ((this.lastTradedPriceTime==null && other.getLastTradedPriceTime()==null) || 
             (this.lastTradedPriceTime!=null &&
              this.lastTradedPriceTime.equals(other.getLastTradedPriceTime()))) &&
            ((this.persianLastTradedPriceTime==null && other.getPersianLastTradedPriceTime()==null) || 
             (this.persianLastTradedPriceTime!=null &&
              this.persianLastTradedPriceTime.equals(other.getPersianLastTradedPriceTime()))) &&
            this.c_LastTradedPriceChanges == other.getC_LastTradedPriceChanges() &&
            this.c_LastTradedPriceChangesPercent == other.getC_LastTradedPriceChangesPercent() &&
            this.highTradedPrice == other.getHighTradedPrice() &&
            this.c_HighTradedPriceChanges == other.getC_HighTradedPriceChanges() &&
            this.c_HighTradedPriceChangesPercent == other.getC_HighTradedPriceChangesPercent() &&
            this.lowTradedPrice == other.getLowTradedPrice() &&
            this.c_LowTradedPriceChanges == other.getC_LowTradedPriceChanges() &&
            this.c_LowTradedPriceChangesPercent == other.getC_LowTradedPriceChangesPercent() &&
            this.averageTradedPrice == other.getAverageTradedPrice() &&
            this.openingPrice == other.getOpeningPrice() &&
            this.closingPrice == other.getClosingPrice() &&
            this.tradesCount == other.getTradesCount() &&
            this.tradesVolume == other.getTradesVolume() &&
            this.tradesValue == other.getTradesValue() &&
            ((this.tradesValueCurrencyFaDesc==null && other.getTradesValueCurrencyFaDesc()==null) || 
             (this.tradesValueCurrencyFaDesc!=null &&
              this.tradesValueCurrencyFaDesc.equals(other.getTradesValueCurrencyFaDesc()))) &&
            ((this.tradesValueCurrencyEnDesc==null && other.getTradesValueCurrencyEnDesc()==null) || 
             (this.tradesValueCurrencyEnDesc!=null &&
              this.tradesValueCurrencyEnDesc.equals(other.getTradesValueCurrencyEnDesc()))) &&
            this.openInterests == other.getOpenInterests() &&
            this.c_OpenInterestsChanges == other.getC_OpenInterestsChanges() &&
            this.openInterestsChangesPercent == other.getOpenInterestsChangesPercent() &&
            ((this.lastUpdate==null && other.getLastUpdate()==null) || 
             (this.lastUpdate!=null &&
              this.lastUpdate.equals(other.getLastUpdate()))) &&
            ((this.persianLastUpdate==null && other.getPersianLastUpdate()==null) || 
             (this.persianLastUpdate!=null &&
              this.persianLastUpdate.equals(other.getPersianLastUpdate()))) &&
            this.expired == other.isExpired();
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
        if (getLastTradingDate() != null) {
            _hashCode += getLastTradingDate().hashCode();
        }
        if (getPersianLastTradingDate() != null) {
            _hashCode += getPersianLastTradingDate().hashCode();
        }
        if (getContractCode() != null) {
            _hashCode += getContractCode().hashCode();
        }
        if (getContractDescription() != null) {
            _hashCode += getContractDescription().hashCode();
        }
        _hashCode += new Double(getContractSize()).hashCode();
        if (getContractSizeUnitFaDesc() != null) {
            _hashCode += getContractSizeUnitFaDesc().hashCode();
        }
        if (getContractSizeUnitEnDesc() != null) {
            _hashCode += getContractSizeUnitEnDesc().hashCode();
        }
        if (getContractCurrencyFaDesc() != null) {
            _hashCode += getContractCurrencyFaDesc().hashCode();
        }
        if (getContractCurrencyEnDesc() != null) {
            _hashCode += getContractCurrencyEnDesc().hashCode();
        }
        _hashCode += getContractCurrencyDecimalPlaces();
        if (getLastSettlementPrice() != null) {
            _hashCode += getLastSettlementPrice().hashCode();
        }
        if (getLastSettlementPriceDate() != null) {
            _hashCode += getLastSettlementPriceDate().hashCode();
        }
        if (getPersianLastSettlementPriceDate() != null) {
            _hashCode += getPersianLastSettlementPriceDate().hashCode();
        }
        _hashCode += getYesterdayOpenInterests();
        _hashCode += getBidTotalVolume();
        _hashCode += getBidVolume1();
        if (getBidPrice1() != null) {
            _hashCode += getBidPrice1().hashCode();
        }
        _hashCode += getBidVolume2();
        if (getBidPrice2() != null) {
            _hashCode += getBidPrice2().hashCode();
        }
        _hashCode += getBidVolume3();
        if (getBidPrice3() != null) {
            _hashCode += getBidPrice3().hashCode();
        }
        _hashCode += getBidVolume4();
        if (getBidPrice4() != null) {
            _hashCode += getBidPrice4().hashCode();
        }
        _hashCode += getBidVolume5();
        if (getBidPrice5() != null) {
            _hashCode += getBidPrice5().hashCode();
        }
        _hashCode += getAskTotalVolume();
        _hashCode += getAskVolume1();
        _hashCode += new Double(getAskPrice1()).hashCode();
        _hashCode += getAskVolume2();
        _hashCode += new Double(getAskPrice2()).hashCode();
        _hashCode += getAskVolume3();
        _hashCode += new Double(getAskPrice3()).hashCode();
        _hashCode += getAskVolume4();
        _hashCode += new Double(getAskPrice4()).hashCode();
        _hashCode += getAskVolume5();
        _hashCode += new Double(getAskPrice5()).hashCode();
        if (getOrdersDateTime() != null) {
            _hashCode += getOrdersDateTime().hashCode();
        }
        if (getPersianOrdersDateTime() != null) {
            _hashCode += getPersianOrdersDateTime().hashCode();
        }
        _hashCode += new Double(getFirstTradedPrice()).hashCode();
        if (getFirstTradedPriceTime() != null) {
            _hashCode += getFirstTradedPriceTime().hashCode();
        }
        if (getPersianFirstTradedPriceTime() != null) {
            _hashCode += getPersianFirstTradedPriceTime().hashCode();
        }
        _hashCode += new Double(getC_FirstTradedPriceChanges()).hashCode();
        _hashCode += new Double(getC_FirstTradedPriceChangesPercent()).hashCode();
        _hashCode += new Double(getLastTradedPrice()).hashCode();
        if (getLastTradedPriceTime() != null) {
            _hashCode += getLastTradedPriceTime().hashCode();
        }
        if (getPersianLastTradedPriceTime() != null) {
            _hashCode += getPersianLastTradedPriceTime().hashCode();
        }
        _hashCode += new Double(getC_LastTradedPriceChanges()).hashCode();
        _hashCode += new Double(getC_LastTradedPriceChangesPercent()).hashCode();
        _hashCode += new Double(getHighTradedPrice()).hashCode();
        _hashCode += new Double(getC_HighTradedPriceChanges()).hashCode();
        _hashCode += new Double(getC_HighTradedPriceChangesPercent()).hashCode();
        _hashCode += new Double(getLowTradedPrice()).hashCode();
        _hashCode += new Double(getC_LowTradedPriceChanges()).hashCode();
        _hashCode += new Double(getC_LowTradedPriceChangesPercent()).hashCode();
        _hashCode += new Double(getAverageTradedPrice()).hashCode();
        _hashCode += new Double(getOpeningPrice()).hashCode();
        _hashCode += new Double(getClosingPrice()).hashCode();
        _hashCode += getTradesCount();
        _hashCode += getTradesVolume();
        _hashCode += new Double(getTradesValue()).hashCode();
        if (getTradesValueCurrencyFaDesc() != null) {
            _hashCode += getTradesValueCurrencyFaDesc().hashCode();
        }
        if (getTradesValueCurrencyEnDesc() != null) {
            _hashCode += getTradesValueCurrencyEnDesc().hashCode();
        }
        _hashCode += getOpenInterests();
        _hashCode += new Double(getC_OpenInterestsChanges()).hashCode();
        _hashCode += new Double(getOpenInterestsChangesPercent()).hashCode();
        if (getLastUpdate() != null) {
            _hashCode += getLastUpdate().hashCode();
        }
        if (getPersianLastUpdate() != null) {
            _hashCode += getPersianLastUpdate().hashCode();
        }
        _hashCode += (isExpired() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Future_Market_Data.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "Future_Market_Data"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastTradingDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "LastTradingDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("persianLastTradingDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "PersianLastTradingDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contractCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ContractCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contractDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ContractDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contractSize");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ContractSize"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contractSizeUnitFaDesc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ContractSizeUnitFaDesc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contractSizeUnitEnDesc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ContractSizeUnitEnDesc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contractCurrencyFaDesc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ContractCurrencyFaDesc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contractCurrencyEnDesc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ContractCurrencyEnDesc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contractCurrencyDecimalPlaces");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ContractCurrencyDecimalPlaces"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastSettlementPrice");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "LastSettlementPrice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastSettlementPriceDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "LastSettlementPriceDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("persianLastSettlementPriceDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "PersianLastSettlementPriceDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("yesterdayOpenInterests");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "YesterdayOpenInterests"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bidTotalVolume");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "BidTotalVolume"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bidVolume1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "BidVolume1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bidPrice1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "BidPrice1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bidVolume2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "BidVolume2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bidPrice2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "BidPrice2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bidVolume3");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "BidVolume3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bidPrice3");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "BidPrice3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bidVolume4");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "BidVolume4"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bidPrice4");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "BidPrice4"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bidVolume5");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "BidVolume5"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bidPrice5");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "BidPrice5"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("askTotalVolume");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "AskTotalVolume"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("askVolume1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "AskVolume1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("askPrice1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "AskPrice1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("askVolume2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "AskVolume2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("askPrice2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "AskPrice2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("askVolume3");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "AskVolume3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("askPrice3");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "AskPrice3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("askVolume4");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "AskVolume4"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("askPrice4");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "AskPrice4"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("askVolume5");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "AskVolume5"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("askPrice5");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "AskPrice5"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ordersDateTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "OrdersDateTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("persianOrdersDateTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "PersianOrdersDateTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("firstTradedPrice");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FirstTradedPrice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("firstTradedPriceTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FirstTradedPriceTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("persianFirstTradedPriceTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "PersianFirstTradedPriceTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("c_FirstTradedPriceChanges");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "C_FirstTradedPriceChanges"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("c_FirstTradedPriceChangesPercent");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "C_FirstTradedPriceChangesPercent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastTradedPrice");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "LastTradedPrice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastTradedPriceTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "LastTradedPriceTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("persianLastTradedPriceTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "PersianLastTradedPriceTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("c_LastTradedPriceChanges");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "C_LastTradedPriceChanges"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("c_LastTradedPriceChangesPercent");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "C_LastTradedPriceChangesPercent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("highTradedPrice");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "HighTradedPrice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("c_HighTradedPriceChanges");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "C_HighTradedPriceChanges"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("c_HighTradedPriceChangesPercent");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "C_HighTradedPriceChangesPercent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lowTradedPrice");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "LowTradedPrice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("c_LowTradedPriceChanges");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "C_LowTradedPriceChanges"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("c_LowTradedPriceChangesPercent");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "C_LowTradedPriceChangesPercent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("averageTradedPrice");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "AverageTradedPrice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("openingPrice");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "OpeningPrice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("closingPrice");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ClosingPrice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tradesCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "TradesCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tradesVolume");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "TradesVolume"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tradesValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "TradesValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tradesValueCurrencyFaDesc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "TradesValueCurrencyFaDesc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tradesValueCurrencyEnDesc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "TradesValueCurrencyEnDesc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("openInterests");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "OpenInterests"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("c_OpenInterestsChanges");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "C_OpenInterestsChanges"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("openInterestsChangesPercent");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "OpenInterestsChangesPercent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastUpdate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "LastUpdate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("persianLastUpdate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "PersianLastUpdate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("expired");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Expired"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
