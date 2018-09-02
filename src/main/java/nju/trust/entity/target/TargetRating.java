package nju.trust.entity.target;

/**
 * 项目风险评级，A, B, C, D 风险依次上升
 */
public enum TargetRating {
    A,
    B,
    C,
    D;

    public static TargetRating of(double pValue) {
        if (pValue >= 0.9)
            return A;
        else if (pValue >= 0.8)
            return B;
        else if (pValue >= 0.6)
            return C;
        else
            return D;
    }
}
