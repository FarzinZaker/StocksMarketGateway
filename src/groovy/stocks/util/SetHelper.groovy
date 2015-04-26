package stocks.util

/**
 * Created by farzin on 25/04/2015.
 */
public class SetHelper {

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
}
