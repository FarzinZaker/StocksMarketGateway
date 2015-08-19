package stocks.util

/**
 * Created by farzin on 25/04/2015.
 */
public class CollectionHelper {

    public static ArrayList getConjunction(ArrayList<ArrayList> list) {
        if (list.size() < 1)
            return null
        if (list.size() < 2)
            return list.find()

        list[0].findAll { item ->
            !list.any { lst ->
                !lst.contains(item)
            }
        }
    }

    public static ArrayList getDisjunction(ArrayList<ArrayList> list) {
        if (list.size() < 1)
            return null
        if (list.size() < 2)
            return list.find()

        def result = []
        list.each {ls ->
            ls.each {item ->
                if(!result.contains(item))
                    result.add(item)
            }
        }
        result
    }

    public static List moveZeroesToFirst(List list) {
        if (!list.any { it != 0 })
            return list
        while (list[list.size() - 1] == 0) {
            for (def i = list.size() - 1; i > 0; i--)
                list[i] = list[i - 1]
            list[0] = 0
        }
        list
    }
}
