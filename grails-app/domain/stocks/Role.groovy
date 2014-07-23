package stocks

class Role {

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
