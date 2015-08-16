package stocks.twitter

class GroupController {

    def index() {}

    def build() {
        def group = params.id ? [:] : null
        [
                group: group
        ]
    }

    def save(){
        println params
    }
}
