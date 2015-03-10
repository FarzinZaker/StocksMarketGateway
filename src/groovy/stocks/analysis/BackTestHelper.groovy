package stocks.analysis

/**
 * Created by farzin on 02/03/2015.
 */
class BackTestHelper {
    public static final String STATUS_WAITING = "status_waiting"
    public static final String STATUS_IN_PROGRESS = "status_in_progress"
    public static final String STATUS_FINISHED = "status_finished"

    public static final String[] STATUS_LIST = [STATUS_WAITING, STATUS_IN_PROGRESS, STATUS_FINISHED]

    public static final String REASON_TIME_LIMIT = "reason_time_limit"
    public static final String REASON_PROFIT_LIMIT = "reason_profit_limit"
    public static final String REASON_LOSS_LIMIT = "reason_loss_limit"
    public static final String REASON_RULES_MATCHED = "reason_rules_matched"

    public static
    final String[] REASON_LIST = [REASON_TIME_LIMIT, REASON_PROFIT_LIMIT, REASON_LOSS_LIMIT, REASON_RULES_MATCHED]
}
