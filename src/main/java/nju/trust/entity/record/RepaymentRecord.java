package nju.trust.entity.record;

import nju.trust.entity.target.BaseTarget;
import nju.trust.entity.user.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class RepaymentRecord extends BaseRecord {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_id")
    private BaseTarget target;

    private Double sum;

    private Double principal;

    private Double interest;

    private Double remainingPrincipal;

    private boolean payOff;

    private LocalDate returnDate;

    public RepaymentRecord(Double money, Long targetId) {
        super();
        payOff = false;
    }

    public RepaymentRecord(User user, BaseTarget target, Double sum, Double principal,
                           Double interest, Double remainingPrincipal, LocalDate returnDate) {
        this.target = target;
        this.sum = sum;
        this.principal = principal;
        this.interest = interest;
        this.remainingPrincipal = remainingPrincipal;
        this.payOff = false;
        this.returnDate = returnDate;
    }

    public boolean isPayOff() {
        return payOff;
    }

    public void setPayOff(boolean payOff) {
        this.payOff = payOff;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BaseTarget getTarget() {
        return target;
    }

    public void setTarget(BaseTarget target) {
        this.target = target;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Double getPrincipal() {
        return principal;
    }

    public void setPrincipal(Double principal) {
        this.principal = principal;
    }

    public Double getInterest() {
        return interest;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    public Double getRemainingPrincipal() {
        return remainingPrincipal;
    }

    public void setRemainingPrincipal(Double remainingPrincipal) {
        this.remainingPrincipal = remainingPrincipal;
    }
}
