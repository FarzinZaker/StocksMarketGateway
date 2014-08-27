import stocks.FarsiNormalizationFilter

class FarsiNormalizationFilters {

    def filters = {
        all(controller: '*', action: '*') {
            before = {
                params.each { param ->
                    if (params."${param.key}" instanceof String[]) {
                        def list = params."${param.key}".collect {
                            FarsiNormalizationFilter.apply(it.value as String)
                        }
                        def array = new String[list.size()]
                        params."${param.key}" = list.toArray(array)
                    }
                    else
                        params."${param.key}" = FarsiNormalizationFilter.apply(param.value as String)
                }
            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
    }
}
