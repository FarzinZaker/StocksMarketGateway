package stocks.feed

class ExternalAnalysis {

    static searchable = true

    String title
    String identifier
    Date date
    String link
    String category
    String source
    Integer clickCount = 0
    String imageUrl

    public transient String getDay(){
        if (date) {
            Calendar calendar = Calendar.instance
            calendar.setTime(date)
            "${calendar.get(Calendar.YEAR)}${(calendar.get(Calendar.MONTH) + 1).toString().padLeft(2, '0')}${calendar.get(Calendar.DAY_OF_MONTH).toString().padLeft(2, '0')}"
        }
        else{
            ''
        }
    }

    static mapping = {
        table 'feed_analysis'
        date column: 'dat'
        identifier unique: true
    }

    static constraints = {
        category inList: ExternalNewsService.categoryList
        imageUrl nullable: true
    }
}
