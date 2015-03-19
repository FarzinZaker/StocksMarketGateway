package stocks.util

/**
 * Created by farzin on 18/03/2015.
 */
class StringHelper {

    public static String underlineToCamel(String input) {
        def parts = input.split('_');
        def result = parts.first().toLowerCase()
        for (def i = 1; i < parts.size(); i++)
            result += parts[i].substring(0, 1).toUpperCase() + parts[i].substring(1).toLowerCase();
        result
    }
}
