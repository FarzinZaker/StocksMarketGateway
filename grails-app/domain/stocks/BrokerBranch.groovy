package stocks


class BrokerBranch {

    static auditable = true

    Broker broker
    City city
    String type
    String responsiblePerson
    Integer personnelCount
    String phoneNumber
    Date registrationDate
    Integer registrationNumber
    City registrationCity
    String address
    Boolean stockExchangeMarket
    Boolean otcMarket
    Boolean energyMarket
    Boolean commodityFuturesMarket
    Boolean exchangeFuturesMarket
    Boolean investmentFund

    Boolean deleted = false
    Date dateCreated
    Date lastUpdated

    static belongsTo = [Broker]

    static mapping = {
        address type: 'clob'
    }

    static constraints = {
        type inList: [
                'administrative',
                'energy_market',
                'exclusive_exchange_hall',
                'main_hall',
                'under_construction_hall',
                'administrative_office',
                'central_office',
                'exchange_office',
                'market_hall_branch',
                'other_hall_branch',
                'agent',
                'overseas_agent'
        ]
    }
}
