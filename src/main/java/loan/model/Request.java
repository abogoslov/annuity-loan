package loan.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author A.Bogoslov
 */

@Entity
@Table(name = "requests")
public class Request {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "sum")
    private BigDecimal sum;

    @Column(name = "date")
    private Date date;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "monthly_charge")
    private BigDecimal monthlyCharge;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public BigDecimal getMonthlyCharge() {
        return monthlyCharge;
    }

    public void setMonthlyCharge(BigDecimal monthlyCharge) {
        this.monthlyCharge = monthlyCharge;
    }

    @Override
    public String toString() {
        return "ID: " + id + "; Сумма: " + sum + " руб.; Дата заявки: " + date + "; На срок " + duration + "мес.";
    }
}
