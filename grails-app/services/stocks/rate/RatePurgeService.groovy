package stocks.rate

class RatePurgeService {

    def lowLevelDataService

    def purgeCoin() {
        lowLevelDataService.executeStoredProcedure('rate_purge', [name: 'coin'])
    }

    def purgeCurrency() {
        lowLevelDataService.executeStoredProcedure('rate_purge', [name: 'currency'])
    }

    def purgeMetal() {
        lowLevelDataService.executeStoredProcedure('rate_purge', [name: 'metal'])
    }
}
