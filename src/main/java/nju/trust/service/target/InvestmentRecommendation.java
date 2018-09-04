package nju.trust.service.target;

import nju.trust.entity.target.SmallTarget;
import nju.trust.payloads.investment.InvestmentStrategy;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.optim.linear.*;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: J.D. Liao
 * Date: 2018/9/3
 * Description:
 */
class InvestmentRecommendation {

    private static final double BANDWIDTH = 0.0274;

    private List<SmallTarget> targets;

    private double money;

    private double interestRate;

    InvestmentRecommendation(List<SmallTarget> targets, double money, double interestRate) {
        this.targets = targets;
        this.money = money;
        this.interestRate = interestRate;
    }

    List<InvestmentStrategy> recommend() {
        int matrixSize = targets.size();

        // Calculate distance matrix
        RealMatrix distanceMatrix = MatrixUtils.createRealMatrix(matrixSize, matrixSize);
        for (int i = 0; i < matrixSize; i++) {
            for (int j = i + 1; j < matrixSize; j++) {
                double iScore = targets.get(i).getTargetRatingScore();
                double jScore = targets.get(j).getTargetRatingScore();
                double distance = Math.abs(iScore - jScore);
                distanceMatrix.setEntry(i, j, distance);
                distanceMatrix.setEntry(j, i, distance);
            }
        }

        RealMatrix kernelValueMatrix = distanceMatrix.copy();
        for (int i = 0; i < matrixSize; i++) {
            for (int j = i; j < matrixSize; j++) {
                double ijDistance = kernelValueMatrix.getEntry(i, j);
                double kernelValue = gaussianKernel(ijDistance / BANDWIDTH);
                kernelValueMatrix.setEntry(i, j, kernelValue);
                kernelValueMatrix.setEntry(j, i, kernelValue);
            }
        }

        // Calculate weight for each distance
        RealMatrix weightMatrix = MatrixUtils.createRealMatrix(matrixSize, matrixSize);
        for (int i = 0; i < matrixSize; i++) {
            double rowSum = sum(kernelValueMatrix.getRow(i));
            for (int j = 0; j < matrixSize; j++) {
                double weight = kernelValueMatrix.getEntry(i, j) / rowSum;
                weightMatrix.setEntry(i, j, weight);
            }
        }

        // Calculate yield
        RealVector yieldVector = new ArrayRealVector(matrixSize);
        for (int i = 0; i < matrixSize; i++) {
            double yield = 0.;
            for (int j = 0; j < matrixSize; j++) {
                yield += weightMatrix.getEntry(i, j) * targets.get(j).getRepayment().getInterestRate();
            }
            yieldVector.setEntry(i, yield);
        }

        RealVector stdDeviation = new ArrayRealVector(matrixSize);
        for (int i = 0; i < matrixSize; i++) {
            double sigma = 0.;
            double yield = yieldVector.getEntry(i);
            for (int j = 0; j < matrixSize; j++) {
                sigma += weightMatrix.getEntry(i, j) *
                        Math.pow(targets.get(j).getRepayment().getInterestRate() - yield, 2);
            }
            stdDeviation.setEntry(i, Math.sqrt(sigma));
        }

        double[] result = doOptimization(matrixSize, yieldVector, stdDeviation);

        // Convert to InvestmentStrategy list
        List<InvestmentStrategy> strategies = new ArrayList<>();
        for (int i = 0; i < matrixSize; i++) {
            InvestmentStrategy strategy = new InvestmentStrategy(targets.get(i).getId(),
                    result[i], result[i] * money);
            strategies.add(strategy);
        }

        return strategies;
    }

    private double[] doOptimization(int matrixSize, RealVector yieldVector, RealVector stdDeviation) {
        LinearObjectiveFunction objectiveFunction = new LinearObjectiveFunction(stdDeviation, 0);
        List<LinearConstraint> constraints = new ArrayList<>();
        LinearConstraint constraint1 = new LinearConstraint(yieldVector, Relationship.EQ, interestRate);
        LinearConstraint constraint2 = new LinearConstraint(new ArrayRealVector(matrixSize, 1.),
                Relationship.EQ, 1.);
        constraints.add(constraint1);
        constraints.add(constraint2);
        for (SmallTarget target : targets) {
            double e = target.getMoney() - target.getCollectedMoney();
            constraints.add(new LinearConstraint(new double[]{money}, Relationship.LEQ, e));
        }
        // Do optimization
        SimplexSolver optimizer = new SimplexSolver();
        return optimizer.optimize(objectiveFunction,
                new LinearConstraintSet(constraints), GoalType.MINIMIZE).getPoint();
    }

    private double gaussianKernel(double t) {
        return (1 / Math.sqrt(2 * Math.PI)) * Math.exp(-1 / 2 * Math.pow(t, 2.));
    }

    private double sum(double[] array) {
        return Arrays.stream(array).sum();
    }
}
