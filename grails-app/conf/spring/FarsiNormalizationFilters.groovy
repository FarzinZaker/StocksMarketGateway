import org.springframework.web.multipart.commons.CommonsMultipartFile
import stocks.FarsiNormalizationFilter

class FarsiNormalizationFilters {

    def filters = {
        all(controller: '*', action: '*') {
            before = {
                params.findAll {
                    it.value instanceof String
                }.each { param ->
                    if (params."${param.key}" instanceof String[]) {
                        def list = params."${param.key}".collect {
                            FarsiNormalizationFilter.apply(it.value as String)
                        }
                        def array = new String[list.size()]
                        params."${param.key}" = list.toArray(array)
                    } else if (params."${param.key}" instanceof String)
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
