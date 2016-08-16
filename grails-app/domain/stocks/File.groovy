package stocks

class File {

    static auditable = true

    String name
    String contentType
    String extension
    byte[] content

    static mapping = {
        table 'files'
        content sqlType: 'blob'
    }
    static constraints = {
    }
}
