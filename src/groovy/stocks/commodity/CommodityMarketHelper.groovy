package stocks.commodity

/**
 * Created with IntelliJ IDEA.
 * User: Farzin
 * Date: 7/8/15
 * Time: 6:41 PM
 */
public class CommodityMarketHelper {


    private static Map<String, Integer> marketMap = [
            'کل بازار'                   : 0,
            'بازار فرعی'                 : 1,
            'پتروشیمی و فرآورده های نفتی': 2,
            'صنعتی'                      : 3,
            'کشاورزی'                    : 4
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
