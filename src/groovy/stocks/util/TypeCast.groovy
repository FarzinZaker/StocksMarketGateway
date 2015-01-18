package stocks.util

/**
 * Created by farzin on 18/01/2015.
 */
public class TypeCast {

    public static double[] toDoubleArray(List list){
        def result = new double[list.size()]
        list.eachWithIndex {item, index ->
            result[index] = list[index] as double
        }
        result
    }

    public static int toInt(value){
        value as int
    }
}
