package stocks


class GroupMembershipPurgeJob {

    static startDelay = 60000
    static timeout = 60000l
    static concurrent = false

    def groupGraphService

    def execute() {
        groupGraphService.membershipPurge()
    }
}
