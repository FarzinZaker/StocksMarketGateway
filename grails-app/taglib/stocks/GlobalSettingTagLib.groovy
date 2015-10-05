package stocks

class GlobalSettingTagLib {

    static namespace = "gs"

    def value = {attrs, body ->
        out << GlobalSetting.findByName(attrs.name as String).value
    }
}
