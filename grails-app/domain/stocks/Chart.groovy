package stocks

class Chart extends Image {

    String options

    static mapping={
        options sqlType: 'ntext'
    }
    static constraints = {
        options(maxSize: 20000000)
    }
}
