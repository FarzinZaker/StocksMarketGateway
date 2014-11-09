package stocks

class Role {

    static auditable = true

    String authority

    static mapping = {
        cache true
    }

    static constraints = {
        authority blank: false, unique: true
    }

    @Override
    String toString(){
        authority
    }
}
