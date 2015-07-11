package stocks

/**
 * Created by farzin on 29/12/2014.
 */
public class RateHelper {

    public static final OILS = [
            'WTI-Crude-Oil-Nymex': [
                    name: FarsiNormalizationFilter.apply('نفت خام WTI (بازار NYMEX)'),
                    source: 'CL1:COM'
            ],
            'Brent-Crude-ICE': [
                    name: FarsiNormalizationFilter.apply('نفت برنت (ICE)'),
                    source: 'CO1:COM'
            ],
            'Crude-Oil-Tokyo': [
                    name: FarsiNormalizationFilter.apply('نفت خام (توکیو)'),
                    source: 'CP1:COM'
            ],
            'Natural-Gas-Nymex': [
                    name: FarsiNormalizationFilter.apply('گاز طبیعی (بازار NYMEX)'),
                    source: 'NG1:COM'
            ]
    ]

    public static final COINS = [
            'n-coin' : [
                    name  : FarsiNormalizationFilter.apply('سکه بهار آزادی'),
                    source: 'sekeb'
            ],
            'o-coin' : [
                    name  : FarsiNormalizationFilter.apply('سکه امامی (قدیم)'),
                    source: 'sekee'
            ],
            'h-coin' : [
                    name  : FarsiNormalizationFilter.apply('نیم سکه'),
                    source: 'nim'
            ],
            'q-coin' : [
                    name  : FarsiNormalizationFilter.apply('ربع سکه'),
                    source: 'rob'
            ],
            'g-coin' : [
                    name  : FarsiNormalizationFilter.apply('سکه گرمی'),
                    source: 'gerami'
            ],
            'mesghal': [
                    name  : FarsiNormalizationFilter.apply('مثقال طلا'),
                    source: 'mesghal'
            ],
            'ons'    : [
                    name  : FarsiNormalizationFilter.apply('انس طلا'),
                    source: 'ons'
            ],
            'geram18': [
                    name  : FarsiNormalizationFilter.apply('گرم طلای 18'),
                    source: 'geram18'
            ],
            'geram24': [
                    name  : FarsiNormalizationFilter.apply('گرم طلای 24'),
                    source: 'geram24'
            ]
    ]

    public static final CURRENCIES = [
            'us-dollar'             : [
                    name  : FarsiNormalizationFilter.apply('دلار امریکا'),
                    source: 'price_dollar'
            ],
            'euro'                  : [
                    name  : FarsiNormalizationFilter.apply('یورو'),
                    source: 'price_eur'
            ],
            'gbp'                   : [
                    name  : FarsiNormalizationFilter.apply('پوند انگلستان'),
                    source: 'price_gbp'
            ],
            'cad'                   : [
                    name  : FarsiNormalizationFilter.apply('دلار کانادا'),
                    source: 'price_cad'
            ],
            'australian-dollar'     : [
                    name  : FarsiNormalizationFilter.apply('دلار استرالیا'),
                    source: 'price_aud'
            ],
            'swiss-franc'           : [
                    name  : FarsiNormalizationFilter.apply('فرانک سوئیس'),
                    source: 'price_chf'
            ],
            'yen'                   : [
                    name  : FarsiNormalizationFilter.apply('ین ژاپن'),
                    source: 'price_jpy'
            ],
            'sek'                   : [
                    name  : FarsiNormalizationFilter.apply('کرون سوئد'),
                    source: 'price_sek'
            ],
            'danish-krone'          : [
                    name  : FarsiNormalizationFilter.apply('کرون دانمارک'),
                    source: 'price_dkk'
            ],
            'nok'                   : [
                    name  : FarsiNormalizationFilter.apply('کرون نروژ'),
                    source: 'price_ nok'
            ],
            'aed'                   : [
                    name  : FarsiNormalizationFilter.apply('درهم امارات'),
                    source: 'price_aed'
            ],
            'lear-turkey'           : [
                    name  : FarsiNormalizationFilter.apply('لیر ترکیه'),
                    source: 'price_try'
            ],
            'rm-malaysia'           : [
                    name  : FarsiNormalizationFilter.apply('رینگت مالزی'),
                    source: 'price_myr'
            ],
            'chinese-yuan'          : [
                    name  : FarsiNormalizationFilter.apply('یوان چین'),
                    source: 'price_ncy'
            ],
            'bet-thailand'          : [
                    name  : FarsiNormalizationFilter.apply('بت تایلند'),
                    source: 'price_thb'
            ],
            'indian-rupee'          : [
                    name  : FarsiNormalizationFilter.apply('روپیه هند'),
                    source: 'price_inr'
            ],
            'saudi-riyal'           : [
                    name  : FarsiNormalizationFilter.apply('ریال عربستان'),
                    source: 'price_sar'
            ],
            'iraq-dinar'            : [
                    name  : FarsiNormalizationFilter.apply('دینار عراق'),
                    source: 'price_iqd'
            ],
            'iraqi-dinar'           : [
                    name  : FarsiNormalizationFilter.apply('روبل روسیه'),
                    source: 'price_rub'
            ],
            'azerbaijani-manat'     : [
                    name  : FarsiNormalizationFilter.apply('منات آذربایجان'),
                    source: 'price_azn'
            ],
            'armenian-dram'         : [
                    name  : FarsiNormalizationFilter.apply('درام ارمنستان'),
                    source: 'price_amd'
            ],
            'georgia-lari'          : [
                    name  : FarsiNormalizationFilter.apply('لاری گرجستان'),
                    source: 'price_gel'
            ],
            'kuwaiti-dinar'         : [
                    name  : FarsiNormalizationFilter.apply('دینار کویت'),
                    source: 'price_kwd'
            ],
            'bahraini-dinar'        : [
                    name  : FarsiNormalizationFilter.apply('دینار بحرین'),
                    source: 'price_bhd'
            ],
            'omani-rial'            : [
                    name  : FarsiNormalizationFilter.apply('ریال عمان'),
                    source: 'price_omr'
            ],
            'qatari-rial'           : [
                    name  : FarsiNormalizationFilter.apply('ریال قطر'),
                    source: 'price_qar'
            ],
            'singapore-dollar'      : [
                    name  : FarsiNormalizationFilter.apply('دلار سنگاپور'),
                    source: 'price_sgd'
            ],
            'new-zealand-dollar'    : [
                    name  : FarsiNormalizationFilter.apply('دلار نیوزلند'),
                    source: 'price_nzd'
            ],
            'hong-kong-dollar'      : [
                    name  : FarsiNormalizationFilter.apply('دلار هنگ کنگ'),
                    source: 'price_hkd'
            ],
            'pakistani-rupee'       : [
                    name  : FarsiNormalizationFilter.apply('روپیه پاکستان'),
                    source: 'price_pkr'
            ],
            'afghan'                : [
                    name  : FarsiNormalizationFilter.apply('افغانی'),
                    source: 'price_afn'
            ],
            'syrian-pound'          : [
                    name  : FarsiNormalizationFilter.apply('پوند سوریه'),
                    source: 'price_syp'
            ],

            //bank prices
            'us-dollar-bank'        : [
                    name  : FarsiNormalizationFilter.apply('دلار امریکا (بانکی)'),
                    source: 'bank_dollar'
            ],
            'euro-bank'             : [
                    name  : FarsiNormalizationFilter.apply('یورو (بانکی)'),
                    source: 'bank_eur'
            ],
            'gbp-bank'              : [
                    name  : FarsiNormalizationFilter.apply('پوند انگلستان (بانکی)'),
                    source: 'bank_gbp'
            ],
            'cad-bank'              : [
                    name  : FarsiNormalizationFilter.apply('دلار کانادا (بانکی)'),
                    source: 'bank_cad'
            ],
            'australian-dollar-bank': [
                    name  : FarsiNormalizationFilter.apply('دلار استرالیا (بانکی)'),
                    source: 'bank_aud'
            ],
            'swiss-franc-bank'      : [
                    name  : FarsiNormalizationFilter.apply('فرانک سوئیس (بانکی)'),
                    source: 'bank_chf'
            ],
            'yen-bank'              : [
                    name  : FarsiNormalizationFilter.apply('ین ژاپن (بانکی)'),
                    source: 'bank_jpy'
            ],
            'sek-bank'              : [
                    name  : FarsiNormalizationFilter.apply('کرون سوئد (بانکی)'),
                    source: 'bank_sek'
            ],
            'danish-krone-bank'     : [
                    name  : FarsiNormalizationFilter.apply('کرون دانمارک (بانکی)'),
                    source: 'bank_dkk'
            ],
            'nok-bank'              : [
                    name  : FarsiNormalizationFilter.apply('کرون نروژ (بانکی)'),
                    source: 'bank_ nok'
            ],
            'aed-bank'              : [
                    name  : FarsiNormalizationFilter.apply('درهم امارات (بانکی)'),
                    source: 'bank_aed'
            ],
            'lear-turkey-bank'      : [
                    name  : FarsiNormalizationFilter.apply('لیر ترکیه (بانکی)'),
                    source: 'bank_try'
            ],
            'rm-malaysia-bank'      : [
                    name  : FarsiNormalizationFilter.apply('رینگت مالزی (بانکی)'),
                    source: 'bank_myr'
            ],
            'chinese-yuan-bank'     : [
                    name  : FarsiNormalizationFilter.apply('یوان چین (بانکی)'),
                    source: 'bank_ncy'
            ],
            'bet-thailand-bank'     : [
                    name  : FarsiNormalizationFilter.apply('بت تایلند (بانکی)'),
                    source: 'bank_thb'
            ],
            'indian-rupee-bank'     : [
                    name  : FarsiNormalizationFilter.apply('روپیه هند (بانکی)'),
                    source: 'bank_inr'
            ],
            'saudi-riyal-bank'      : [
                    name  : FarsiNormalizationFilter.apply('ریال عربستان (بانکی)'),
                    source: 'bank_sar'
            ],
            'iraq-dinar-bank'       : [
                    name  : FarsiNormalizationFilter.apply('دینار عراق (بانکی)'),
                    source: 'bank_iqd'
            ],
            'iraqi-dinar-bank'      : [
                    name  : FarsiNormalizationFilter.apply('روبل روسیه (بانکی)'),
                    source: 'bank_rub'
            ],
            'azerbaijani-manat-bank': [
                    name  : FarsiNormalizationFilter.apply('منات آذربایجان (بانکی)'),
                    source: 'bank_azn'
            ],
            'armenian-dram-bank'    : [
                    name  : FarsiNormalizationFilter.apply('درام ارمنستان (بانکی)'),
                    source: 'bank_amd'
            ],
            'kuwaiti-dinar-bank'    : [
                    name  : FarsiNormalizationFilter.apply('دینار کویت (بانکی)'),
                    source: 'bank_kwd'
            ],
            'bahraini-dinar-bank'   : [
                    name  : FarsiNormalizationFilter.apply('دینار بحرین (بانکی)'),
                    source: 'bank_bhd'
            ],
            'omani-rial-bank'       : [
                    name  : FarsiNormalizationFilter.apply('ریال عمان (بانکی)'),
                    source: 'bank_omr'
            ],
            'qatari-rial-bank'      : [
                    name  : FarsiNormalizationFilter.apply('ریال قطر (بانکی)'),
                    source: 'bank_qar'
            ],
            'singapore-dollar-bank' : [
                    name  : FarsiNormalizationFilter.apply('دلار سنگاپور (بانکی)'),
                    source: 'bank_sgd'
            ],
            'hong-kong-dollar-bank' : [
                    name  : FarsiNormalizationFilter.apply('دلار هنگ کنگ (بانکی)'),
                    source: 'bank_hkd'
            ],
            'pakistani-rupee-bank'  : [
                    name  : FarsiNormalizationFilter.apply('روپیه پاکستان (بانکی)'),
                    source: 'bank_pkr'
            ],
            'afghan-bank'           : [
                    name  : FarsiNormalizationFilter.apply('افغانی (بانکی)'),
                    source: 'bank_afn'
            ],
            'syrian-pound-bank'     : [
                    name  : FarsiNormalizationFilter.apply('پوند سوریه (بانکی)'),
                    source: 'bank_syp'
            ]
    ]

    public static final METALS = [
            'silver'   : [
                    name  : FarsiNormalizationFilter.apply('انس نقره'),
                    source: 'silver'
            ],
            'platinum' : [
                    name  : FarsiNormalizationFilter.apply('انس پلاتین'),
                    source: 'platinum'
            ],
            'palladium': [
                    name  : FarsiNormalizationFilter.apply('انس پالادیوم'),
                    source: 'palladium'
            ],
            'copper'   : [
                    name  : FarsiNormalizationFilter.apply('مس'),
                    source: 'copper'
            ],
            'aluminium': [
                    name  : FarsiNormalizationFilter.apply('آلومینیوم'),
                    source: 'aluminium'
            ],
            'nickel'   : [
                    name  : FarsiNormalizationFilter.apply('نیکل'),
                    source: 'nickel'
            ],
            'tin'      : [
                    name  : FarsiNormalizationFilter.apply('قلع'),
                    source: 'nickel'
            ],
            'zinc'     : [
                    name  : FarsiNormalizationFilter.apply('روی'),
                    source: 'zinc'
            ]
    ]
}
