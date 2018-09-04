package nju.trust.service.target;

import nju.trust.entity.user.UserMonthStatistics;
import nju.trust.exception.InternalException;
import nju.trust.exception.ResourceNotFoundException;
import nju.trust.util.SimpleExponentialSmoothing;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: J.D. Liao
 * Date: 2018/9/3
 * Description:
 */
class UserMonthlyDataHelper {

    private List<UserMonthStatistics> statistics;

    UserMonthlyDataHelper(List<UserMonthStatistics> statistics) {
        this.statistics = statistics;
    }

    double getTotalSurplus() {
        return statistics.stream().mapToDouble(UserMonthStatistics::getSurplus).sum();
    }

    double getTotalDisc() {
        return statistics.stream().mapToDouble(UserMonthStatistics::getDisc).sum();
    }

    double forecastSurplus() {
        List<Double> surplusData = statistics.stream()
                .map(UserMonthStatistics::getSurplus).collect(Collectors.toList());
        return SimpleExponentialSmoothing.forecast(surplusData);
    }

    double forecastDisc() {
        List<Double> discData = statistics.stream().map(UserMonthStatistics::getDisc).collect(Collectors.toList());
        return SimpleExponentialSmoothing.forecast(discData);
    }

    double getCurrentDebt() {
        if (statistics.isEmpty())
            throw new ResourceNotFoundException("User statistics not found");
        return statistics.get(statistics.size() - 1).getDebt();
    }

    double getAvgMonthlyIncomeLevel() {
        double avgMonthlyIncome = statistics.stream()
                .mapToDouble(UserMonthStatistics::getIncome).average()
                .orElseThrow(() -> new InternalException("Something is wrong"));

        double[] slices = {1000., 2000., 3000., 4000., 5000., 10000.};
        int i = 1;
        for (double slice : slices) {
            if (avgMonthlyIncome < slice)
                return i;
            i++;
        }

        return i;
    }
}
