package stocks

import grails.plugin.cache.CacheEvict


class ClearNewsCacheJob {
    def timeout = 60000l // execute job once in 5 seconds

    @CacheEvict(value = ["newsCache"])
    def execute() {
        // execute task
    }
}
