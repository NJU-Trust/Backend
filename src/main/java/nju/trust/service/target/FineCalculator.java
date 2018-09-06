package nju.trust.service.target;

import nju.trust.entity.record.RepaymentRecord;
import nju.trust.exception.ResourceNotFoundException;

import java.time.LocalDate;
import java.util.List;

/**
 * Author: J.D. Liao
 * Date: 2018/9/6
 * Description:
 */
class FineCalculator {

    private static final int FIRST_FINE_PHASE = 30;

    private static final int SECOND_FINE_PHASE = 60;

    private static final double FIRST_FINE_RATE = 0.05e-2;

    private static final double SECOND_FINE_RATE = 0.08e-2;

    private static final double THIRD_FINE_RATE = 0.1e-2;

    private static final int PREPAYMENT_PHASE_1 = 4;

    private static final int PREPAYMENT_PHASE_2 = 8;

    private static final double PREPAYMENT_PENALTY_RATE_1 = 1.5e-2;

    private static final double PREPAYMENT_PENALTY_RATE_2 = 1e-2;

    private static final double PREPAYMENT_PENALTY_RATE_3 = 0.5e-2;

    static double getOverdueFine(double sum, long overdueDays) {
        double fine = 0.;
        if (overdueDays <= FIRST_FINE_PHASE)
            fine = sum * overdueDays * FIRST_FINE_RATE;
        else if (overdueDays <= 60)
            fine = sum * (FIRST_FINE_RATE * FIRST_FINE_RATE + (overdueDays - FIRST_FINE_PHASE) * SECOND_FINE_RATE);
        else
            fine = sum * (FIRST_FINE_RATE * FIRST_FINE_RATE + SECOND_FINE_PHASE * FIRST_FINE_PHASE
                    + (overdueDays - SECOND_FINE_PHASE) * THIRD_FINE_RATE);
        return fine;
    }

    static double getPrepaymentFine(List<RepaymentRecord> records, int period) {
        // Calculate remaining principals
        // Get latest repaymentRecord
        double remainingPrincipal = getRemainingPrincipal(records);
        double fine = 0.;
        if (period <= PREPAYMENT_PHASE_1) {
            fine = remainingPrincipal * PREPAYMENT_PENALTY_RATE_1;
        } else if (period <= PREPAYMENT_PHASE_2) {
            fine = remainingPrincipal * PREPAYMENT_PENALTY_RATE_2;
        } else {
            fine = remainingPrincipal * PREPAYMENT_PENALTY_RATE_3;
        }

        return fine;
    }

    private static double getRemainingPrincipal(List<RepaymentRecord> records) {
        return records.stream().filter(r -> !r.isPayOff() && !r.isAtSettlementDay())
                .mapToDouble(RepaymentRecord::getPrincipal).sum();
    }
}
