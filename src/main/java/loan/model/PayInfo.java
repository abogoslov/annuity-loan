package loan.model;

import java.math.BigDecimal;

/**
 * @author A.Bogoslov
 */
public class PayInfo {

    private BigDecimal amount;
    private BigDecimal percents;
    private BigDecimal averagePay;

    public PayInfo() {

        amount = new BigDecimal(0);
        percents = new BigDecimal(0);
        averagePay = new BigDecimal(0);
    }

    public void calcInfo(MonthSchedule schedule, int duration) {

        percents = percents.add(schedule.getPercents());

        averagePay = averagePay
                .add(schedule.getPrimaryPayment().divide(BigDecimal.valueOf(duration), BigDecimal.ROUND_HALF_UP));
    }


    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPercents() {
        return percents;
    }

    public void setPercents(BigDecimal percents) {
        this.percents = percents;
    }

    public BigDecimal getAveragePay() {
        return averagePay;
    }

    public void setAveragePay(BigDecimal averagePay) {
        this.averagePay = averagePay;
    }
}
