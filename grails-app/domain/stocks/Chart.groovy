package stocks

class Chart extends Image {

    String options

    static constraints = {
        options(maxSize: 20000000)
    }
}
