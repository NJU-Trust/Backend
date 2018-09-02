package nju.trust.entity.user;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Author: J.D. Liao
 * Date: 2018/8/31
 * Description:
 */
@Entity
@DiscriminatorValue("EQUAL_INSTALLMENT_OF_PRINCIPAL_AND_INTEREST")
public class EIPIRepayment extends Repayment {
    @Override
    public double getThisMonthAmount() {
        return 0;
    }
}
