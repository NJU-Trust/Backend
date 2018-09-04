package nju.trust.service.target;

import nju.trust.entity.user.UserMonthStatistics;
import nju.trust.util.SimpleExponentialSmoothing;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: J.D. Liao
 * Date: 2018/9/3
 * Description:
 */
public class UserMonthlyDataHelper {

    private List<UserMonthStatistics> statistics;

    public UserMonthlyDataHelper(List<UserMonthStatistics> statistics) {
        this.statistics = statistics;
    }

    public double getTotalSurplus() {
        return statistics.stream().mapToDouble(UserMonthStatistics::getSurplus).sum();
    }

    public double getTotalDisc() {
        return statistics.stream().mapToDouble(UserMonthStatistics::getDisc).sum();
    }

    public double forecastSurplus() {
        List<Double> surplusData = statistics.stream()
                .map(UserMonthStatistics::getSurplus).collect(Collectors.toList());
        return SimpleExponentialSmoothing.forecast(surplusData);
    }

    public double forecastDisc() {
        List<Double> discData = statistics.stream().map(UserMonthStatistics::getDisc).collect(Collectors.toList());
        return SimpleExponentialSmoothing.forecast(discData);
    }

    double getTotalDebt() {
        return statistics.stream().mapToDouble(UserMonthStatistics::getDebt).sum();
    }
}
