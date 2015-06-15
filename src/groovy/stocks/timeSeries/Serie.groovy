package stocks.timeSeries

import com.jniwrapper.Bool

/**
 * Created by farzin on 05/06/2015.
 */
public class Serie {

    private String databaseNameString

    public String getDatabaseName() {
        databaseNameString
    }

    public Serie databaseName(String databaseName) {
        databaseNameString = databaseName
        this
    }

    private String retentionPolicyString

    public String getRetentionPolicy() {
        retentionPolicyString
    }

    public Serie retentionPolicy(String databaseName) {
        retentionPolicyString = databaseName
        this
    }

    private Map tagsMap = [:];

    public Serie tags(Map value) {
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

    public Serie time(Date date) {
        timeLong = date.time * 1000000
        this
    }

    public Date getTime() {
        new Date((timeLong / 1000000l) as Long)
    }

    public Boolean hasTime() {
        timeLong > 0
    }

    private List<Point> pointList = []

    public List<Point> getPoints() {
        pointList
    }

    public List<Point> setPoints(List<Point> value) {
        pointList = value
    }

    public Serie addPoint(Point point) {
        if (point.value != null)
            pointList << point
        this
    }

    public Map toJSON() {
        def json = [:]
        json.database = databaseNameString
        json.retentionPolicy = retentionPolicyString
        if (hasTags()) {
            json.tags = tagsMap
        }
        if (hasTime()) {
            json.time = timeLong
            json.precision = 'n'
        }
        json.points = pointList.collect { it.toJSON() }
        json
    }

    public String toJSONString() {
        toJSON()
    }

    public List<String> toPagedCSV(Integer pageSize) {
        if (pointList.size() <= pageSize)
            return [toCSV()]

        def result = []
        def startIndex = 0
        while(startIndex * pageSize < pointList.size()) {
            result << toCSV(startIndex++, pageSize)
        }
        result
    }

    public String toCSV(Integer page = 1, Integer pageSize = Integer.MAX_VALUE) {
        try {
            def resultList = pointList[(page - 1) * pageSize..[page * pageSize, pointList.size()].min() - 1]
            resultList.collect { it.toCSV() }.join('\n')
        }
        catch(ex){
            throw ex
        }
    }

}
