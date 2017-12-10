package loan.model;

import java.math.BigDecimal;

/**
 * @author A.Bogoslov
 */
public class MonthSchedule {

    private int monthIndex;
    private BigDecimal loanBalance;
    private BigDecimal primaryPayment;
    private BigDecimal percents;

    public int setMonthIndex() {
        return monthIndex;
    }

    public int getMonthIndex() {
        return monthIndex;
    }

    public BigDecimal getLoanBalance() {
        return loanBalance;
    }

    public BigDecimal getPrimaryPayment() {
        return primaryPayment;
    }

    public BigDecimal getPercents() {
        return percents;
    }

    public void setMonthIndex(int monthIndex) {
        this.monthIndex = monthIndex;
    }

    public void setLoanBalance(BigDecimal loanBalance) {
        this.loanBalance = loanBalance;
    }

    public void setPrimaryPayment(BigDecimal primaryPayment) {
        this.primaryPayment = primaryPayment;
    }

    public void setPercents(BigDecimal percents) {
        this.percents = percents;
    }
}
