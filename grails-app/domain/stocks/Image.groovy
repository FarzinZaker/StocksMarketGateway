package stocks

class Image {

    static auditable = true

    String name
    byte[] content

    static mapping = {
        table 'images'
        content type: 'clob'
    }
    static constraints = {
//        content(maxSize: 2000000000)
    }
}
