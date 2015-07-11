package stocks.tse

/**
 * Created with IntelliJ IDEA.
 * User: Farzin
 * Date: 7/8/15
 * Time: 6:41 PM
 */
public class EnergyMarketHelper {


    private static Map<String, Integer> marketMap = [
            'برق'   : 1,
            'فیزیکی': 2
    ]

    public static Set<String> marketNames() {
        marketMap.keySet()
    }

    public static Collection<Integer> marketIdentifiers() {
        marketMap.values()
    }

    public static String marketName(Integer marketIdentifier) {
        marketMap.find { it.value == marketIdentifier }.key
    }

    public static Integer marketIdentifier(String marketName) {
        marketMap[marketName]
    }
}
