package stocks

import grails.converters.JSON
import grails.plugins.springsecurity.Secured

@Secured([RoleHelper.ROLE_ADMIN])
class BrokerController {

    def build() {
        [brokerInstance: params.id ? Broker.get(params.id) : null]
    }

    def save() {
        def broker
        if (params.id) {
            broker = Broker.get(params.id)
            broker.properties = params
        } else {
            broker = new Broker(params)
        }
        if (broker.save())
            redirect(action: 'list')
        else if (broker.save())  //retry
            redirect(action: 'list')
    }

    def list() {

    }

    def jsonList() {

        def value = [:]
        def parameters = [offset: params.skip, max: params.pageSize, sort: params["sort[0][field]"] ?: "lastUpdated", order: params["sort[0][dir]"] ?: "desc"]

        def list = Broker.findAllByDeleted(false, parameters)
        value.total = Broker.countByDeleted(false)

        value.data = list.collect {
            [
                    id  : it.id,
                    name: it.name,
                    englishName: it.englishName
            ]
        }

        render value as JSON
    }

    def delete() {

        def broker = Broker.get(params.id)
        broker.deleted = true
        render(broker.save() ? '1' : '0')
    }


    def userList() {
        [broker: Broker.get(params.id)]
    }

    def userJsonList() {

        def broker = Broker.get(params.id)

        def value = [:]
        def parameters = [offset: params.skip, max: params.pageSize, sort: params["sort[0][field]"] ?: "username", order: params["sort[0][dir]"] ?: "asc"]

        def list
        if (params.search && params.search != '') {
            def searchResult = User.search(params.search?.toString()).results.collect { it.id }
            list = searchResult?.size() > 0 ? User.findAllByBrokerAndIdInList(broker, searchResult, parameters) : []
            value.total = searchResult?.size() > 0 ? User.countByBrokerAndIdInList(broker, searchResult) : 0
        } else {
            list = User.findAllByBroker(broker, parameters)
            value.total = User.countByBroker(broker)
        }

        value.data = list.collect {
            [
                    id          : it.id,
                    firstName   : it.firstName,
                    lastName    : it.lastName,
                    username    : it.username,
                    sex         : it.sex,
                    mobile      : it.mobile,
                    nationalCode: it.nationalCode,
                    city        : it.city?.name,
                    enabled     : it.enabled,
                    roles       : it.authorities.collect { message(code: "userInfo.roles.${it.authority}") }.join(',')
            ]
        }

        render value as JSON
    }

    def user() {
        def user = User.get(params.id)

        [
                user          : user ?: new User(),
                roles         : user ? UserRole.findAllByUser(user).collect {
                    it.role.authority
                } : [RoleHelper.ROLE_BROKER_USER],
                availableRoles: RoleHelper.BROKER_ROLES
        ]
    }

    def userSave() {

        def user
        if (params.id) {
            user = User.get(params.id)
            def oldPassword = user.password
            user.properties = params
            if (oldPassword != user.password)
                user.password = springSecurityService.encodePassword(user.password)
        } else {

            if(User.findByEmail(params.email))
            {
                flash.validationError = message(code:'user.save.error.repetitiveEmail')
                redirect(action: 'user')
                return
            }

            user = new User(params)
        }

        user.broker = Broker.get(params.brokerId)

        user.city = City.get(params.cityId)
        user.username = user.email

        if (user.validate() && user.save()) {

            UserRole.findAllByUser(user).each { it.delete() }
            RoleHelper.ROLES.each { role ->
                if (params."roles_${role}")
                    UserRole.create(user, Role.findByAuthority(role))
            }

            redirect(action: 'userList', id: user.broker?.id)
        } else {
            flash.validationError = user.errors.toString()
            redirect(action: 'user', params: params)
        }
    }
}
