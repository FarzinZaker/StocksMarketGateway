package stocks

class Image {

    String name
    byte[] content

    static mapping={
        content sqlType: 'ntext'
    }
    static constraints = {
        content(maxSize: 2000000000)
    }
}
