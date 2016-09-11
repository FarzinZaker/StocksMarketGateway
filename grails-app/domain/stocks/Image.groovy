package stocks

class Image {

    static auditable = true

    String name
    byte[] content

    static mapping = {
        table 'images'
    }
    static constraints = {
    }
}
