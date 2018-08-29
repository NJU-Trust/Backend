package nju.trust.entity;

/**
 * 信用评级
 */
public enum CreditRating {
    AA(7),
    A(6),
    B(4),
    C(3),
    D(1);

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

    CreditRating(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
