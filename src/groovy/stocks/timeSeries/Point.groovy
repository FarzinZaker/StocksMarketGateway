package stocks.timeSeries

/**
 * Created by farzin on 01/06/2015.
 */
public class Point {

    public Point(String name) {
        this.name = name
    }

    private String name;

    public String getName() {
        name
    }

    private Map tagsMap = [:];

    public Point tags(Map value) {
        tagsMap = value
        this
    }

    public Map getTags() {
        tagsMap
    }

    public Boolean hasTags() {
        !tagsMap.isEmpty()
    }

    private Long timeLong = 0

    public Point time(Date date) {
        timeLong = date.time * 1000000
        this
    }

    public Date getTime() {
        new Date((timeLong / 1000000l) as Long)
    }

    public Boolean hasTime() {
        timeLong > 0
    }

    private Object valueLong = 0L;

    public Point value(Object value) {
        valueLong = value
        this
    }

    public Object getValue() {
        valueLong
    }

    public Map toJSON() {
        def json = [:]
        json.name = name
        if (hasTags()) {
            json.tags = tagsMap
        }
        if (hasTime()) {
            json.time = timeLong
            json.precision = 'n'
        }
        json.fields = [value: this.value as Long]
        json
    }

    public String toJSONString() {
        toJSON()
    }

    public String toCSV() {
        "${name}${tags.isEmpty() ? ' ' : ', '}${tags.collect { "${it.key}=${it.value}" }.join(', ')}${tags.isEmpty() ? '' : ' '}value=${value as Long} ${timeLong}"
    }
}
