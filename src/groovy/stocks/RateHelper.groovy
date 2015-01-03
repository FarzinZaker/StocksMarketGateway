package stocks

/**
 * Created by farzin on 29/12/2014.
 */
public class RateHelper {

    public static final COINS = [
            'n-coin': [
                    name: FarsiNormalizationFilter.apply('سکه بهار آزادی'),
                    source: 'sekeb'
            ],
            'o-coin': [
                    name: FarsiNormalizationFilter.apply('سکه امامی (قدیم)'),
                    source: 'sekee'
            ],
            'h-coin': [
                    name: FarsiNormalizationFilter.apply('نیم سکه'),
                    source: 'nim'
            ],
            'q-coin': [
                    name: FarsiNormalizationFilter.apply('ربع سکه'),
                    source: 'rob'
            ],
            'g-coin': [
                    name: FarsiNormalizationFilter.apply('سکه گرمی'),
                    source: 'gerami'
            ]
    ]

    public static final CURRENCIES = [
            'us-dollar': [
                    name: FarsiNormalizationFilter.apply('دلار امریکا'),
                    source: 103
            ],
            'euro': [
                    name: FarsiNormalizationFilter.apply('یورو'),
                    source: 105
            ],
            'gbp': [
                    name: FarsiNormalizationFilter.apply('پوند انگلستان'),
                    source: 209
            ],
            'cad': [
                    name: FarsiNormalizationFilter.apply('دلار کانادا'),
                    source: 210
            ],
            'australian-dollar': [
                    name: FarsiNormalizationFilter.apply('دلار استرالیا'),
                    source: 211
            ],
            'swiss-franc': [
                    name: FarsiNormalizationFilter.apply('فرانک سوئیس'),
                    source: 212
            ],
            'yen': [
                    name: FarsiNormalizationFilter.apply('ین ژاپن'),
                    source: 213
            ],
            'sek': [
                    name: FarsiNormalizationFilter.apply('کرون سوئد'),
                    source: 214
            ],
            'danish-krone': [
                    name: FarsiNormalizationFilter.apply('کرون دانمارک'),
                    source: 215
            ],
            'nok': [
                    name: FarsiNormalizationFilter.apply('کرون نروژ'),
                    source: 216
            ],
            'aed': [
                    name: FarsiNormalizationFilter.apply('درهم امارات'),
                    source: 217
            ],
            'lear-urkey': [
                    name: FarsiNormalizationFilter.apply('لیر ترکیه'),
                    source: 218
            ],
            'rm-malaysia': [
                    name: FarsiNormalizationFilter.apply('رینگت مالزی'),
                    source: 219
            ],
            'chinese-yuan': [
                    name: FarsiNormalizationFilter.apply('یوان چین'),
                    source: 220
            ],
            'bet-thailand': [
                    name: FarsiNormalizationFilter.apply('بت تایلند'),
                    source: 221
            ],
            'indian-rupee'         : [
                    name: FarsiNormalizationFilter.apply('روپیه هند'),
                    source: 222
            ],
            'saudi-riyal'         : [
                    name: FarsiNormalizationFilter.apply('ریال عریستان'),
                    source: 223
            ],
            ''         : [
                    name: FarsiNormalizationFilter.apply('دینار عراق'),
                    source: 224
            ],
            'iraqi-dinar'         : [
                    name: FarsiNormalizationFilter.apply('روبل روسیه'),
                    source: 225
            ],
            'azerbaijani-manat'         : [
                    name: FarsiNormalizationFilter.apply('منات آذربایجان'),
                    source: 226
            ],
            'armenian-dram'         : [
                    name: FarsiNormalizationFilter.apply('درام ارمنستان'),
                    source: 227
            ],
            'georgia-lari'         : [
                    name: FarsiNormalizationFilter.apply('لاری گرجستان'),
                    source: 228
            ],
            'kuwaiti-dinar'         : [
                    name: FarsiNormalizationFilter.apply('دینار کویت'),
                    source: 229
            ],
            'bahraini-dinar'         : [
                    name: FarsiNormalizationFilter.apply('دینار بحرین'),
                    source: 230
            ],
            'omani-rial'         : [
                    name: FarsiNormalizationFilter.apply('ریال عمان'),
                    source: 231
            ],
            'qatari-rial'         : [
                    name: FarsiNormalizationFilter.apply('ریال قطر'),
                    source: 232
            ],
            'lear-russia'         : [
                    name: FarsiNormalizationFilter.apply('لیر روسیه'),
                    source: 233
            ],
            'singapore-dollar'         : [
                    name: FarsiNormalizationFilter.apply('دلار سنگاپور'),
                    source: 234
            ],
            'new-zealand-dollar'         : [
                    name: FarsiNormalizationFilter.apply('دلار نیوزلند'),
                    source: 235
            ],
            'hong-kong-dollar'         : [
                    name: FarsiNormalizationFilter.apply('دلار هنگ کنگ'),
                    source: 236
            ],
            'pakistani-rupee'         : [
                    name: FarsiNormalizationFilter.apply('روپیه پاکستان'),
                    source: 237
            ],
            'afghan'         : [
                    name: FarsiNormalizationFilter.apply('افغانی'),
                    source: 238
            ]
    ]

    public static final METALS = [
            'mesghal'  : [
                    name: FarsiNormalizationFilter.apply('مثقال طلا'),
                    source: 'mesghal'
            ],
            'ons'  : [
                    name: FarsiNormalizationFilter.apply('انس طلا'),
                    source: 'ons'
            ],
            'geram18'  : [
                    name: FarsiNormalizationFilter.apply('گرم طلای 18'),
                    source: 'geram18'
            ],
            'geram24'  : [
                    name: FarsiNormalizationFilter.apply('گرم طلای 24'),
                    source: 'geram24'
            ],
            'silver'   : [
                    name: FarsiNormalizationFilter.apply('انس نقره'),
                    source: 'silver'
            ],
            'platinum' : [
                    name: FarsiNormalizationFilter.apply('انس پلاتین'),
                    source: 'platinum'
            ],
            'palladium': [
                    name: FarsiNormalizationFilter.apply('انس پالادیوم'),
                    source: 'palladium'
            ]
    ]
}
