package stocks

class Image {

    String name
    byte[] content

    static constraints = {
        content(maxSize: 2000000000)
    }
}
