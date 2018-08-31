package nju.trust.entity;

/**
 * 信用评级
 */
public enum CreditRating {
    AA(7, 5000),
    A(6, 4000),
    B(4, 3000),
    C(3, 2000),
    D(1, 1000);

    public static CreditRating of(double score) {
        double[] scoreSlice = new double[]{90., 75., 60., 40.};
        CreditRating[] ratings = CreditRating.values();
        for (int i = 0; i < scoreSlice.length; i++) {
            if (score >= scoreSlice[i]) {
                return ratings[i];
            }
        }
        return ratings[ratings.length - 1];
    }

    private int level;

    private double borrowingAmount;

    CreditRating(int level, double borrowingAmount) {
        this.level = level;
        this.borrowingAmount = borrowingAmount;
    }

    public int getLevel() {
        return level;
    }

    public double getBorrowingAmount() {
        return borrowingAmount;
    }
}
