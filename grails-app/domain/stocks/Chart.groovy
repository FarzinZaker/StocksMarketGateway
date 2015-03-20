package stocks

class Chart extends Image {

    static auditable = true

    String options

    static mapping={
        options type: 'text'
    }
    static constraints = {
        options(maxSize: 20000000)
    }
}
