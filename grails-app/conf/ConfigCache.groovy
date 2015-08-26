
grails.cache.config = {
    cache {
        name 'newsCache'
        maxElementsInMemory 10000
        eternal false
        timeToIdleSeconds 60
        timeToLiveSeconds 60
        overflowToDisk true
        maxElementsOnDisk 10000000
        diskPersistent false
        diskExpiryThreadIntervalSeconds 120
        memoryStoreEvictionPolicy 'LRU'
    }
    cache {
        name 'sparkLine'
        maxElementsInMemory 10000
        eternal false
        timeToIdleSeconds 600
        timeToLiveSeconds 600
        overflowToDisk true
        maxElementsOnDisk 10000000
        diskPersistent false
        diskExpiryThreadIntervalSeconds 120
        memoryStoreEvictionPolicy 'LRU'
    }
    cache {
        name 'marketViewCache'
        maxElementsInMemory 10000
        eternal false
        timeToIdleSeconds 60
        timeToLiveSeconds 60
        overflowToDisk true
        maxElementsOnDisk 10000000
        diskPersistent false
        diskExpiryThreadIntervalSeconds 120
        memoryStoreEvictionPolicy 'LRU'
    }
    cache {
        name 'announcementsCache'
        maxElementsInMemory 10000
        eternal false
        timeToIdleSeconds 60
        timeToLiveSeconds 60
        overflowToDisk true
        maxElementsOnDisk 10000000
        diskPersistent false
        diskExpiryThreadIntervalSeconds 120
        memoryStoreEvictionPolicy 'LRU'
    }
    cache {
        name 'ratesCache'
        maxElementsInMemory 10000
        eternal false
        timeToIdleSeconds 60
        timeToLiveSeconds 60
        overflowToDisk true
        maxElementsOnDisk 10000000
        diskPersistent false
        diskExpiryThreadIntervalSeconds 120
        memoryStoreEvictionPolicy 'LRU'
    }
}