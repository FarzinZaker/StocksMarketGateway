package stocks

class BrokerTagLib {

    static namespace = "broker"

    def springSecurityService

    def logo = {attrs, body ->
        def broker = Broker.get((springSecurityService.currentUser as User).brokerId)
        out << """
            <img height="68px" src="${createLink(controller: 'image', id: Image.get(broker?.logoId)?.id)}"/>
"""
    }

    def title = {attrs, body ->
        def broker = Broker.get((springSecurityService.currentUser as User).brokerId)
        out << """
            ${broker.name}
"""
    }
}
