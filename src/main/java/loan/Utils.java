package loan;

import loan.model.MonthSchedule;
import loan.model.PayInfo;
import loan.model.Request;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.math.BigDecimal.ROUND_HALF_UP;

/**
 * @author A.Bogoslov
 */
public class Utils {

    // % / 12
    private static final double INTEREST_RATE_RATIO = 0.11 / 12.0;

    public static BigDecimal calcMonthlyCharge(Request request) {

        return request.getSum().multiply(
                    BigDecimal.valueOf(INTEREST_RATE_RATIO + INTEREST_RATE_RATIO /
                            (Math.pow(1 + INTEREST_RATE_RATIO, request.getDuration()) - 1)))
                    .setScale(2, ROUND_HALF_UP);
    }

    public static List<MonthSchedule> calcSchedule(int duration, BigDecimal sum, BigDecimal monthlyCharge) {

        List<MonthSchedule> scheduleList = new ArrayList<>(duration);
        MonthSchedule schedule;

        for (int i = 0; i < duration; i++) {
            schedule = new MonthSchedule();
            schedule.setMonthIndex(i);

            if (i > 0) {
                schedule.setLoanBalance(
                        scheduleList.get(i - 1).getLoanBalance()
                                .subtract(scheduleList.get(i - 1).getPrimaryPayment())
                                .setScale(2, ROUND_HALF_UP)
                );
            } else {
                schedule.setLoanBalance(sum);
            }

            schedule.setPercents(
                    schedule.getLoanBalance()
                            .multiply(BigDecimal.valueOf(INTEREST_RATE_RATIO))
                            .setScale(2, ROUND_HALF_UP)
            );

            schedule.setPrimaryPayment(monthlyCharge
                    .subtract(schedule.getPercents())
                    .setScale(2, ROUND_HALF_UP));

            scheduleList.add(schedule);
        }

        return scheduleList;
    }

    public static PayInfo calcInfo(List<MonthSchedule> schedule, BigDecimal sum, int duration) {
        PayInfo info = new PayInfo();
        schedule.forEach(s -> info.calcInfo(s, duration));
        info.setAmount(info.getPercents().add(sum));
        return info;
    }
}
