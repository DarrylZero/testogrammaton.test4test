package com.steammachine.testmachine.infrastructure.tests.factory;

//import java.util.stream.IntStream;
//
//import static com.steammachine.common.utils.commonutils.CommonUtils.check;

public class TriangleSquare {

    public static double triangleSquare(double a, double b, double c) {
        double[] sides = {a, b, c, a, b};
//        check(() -> IntStream.range(0, 2).allMatch(i -> sides[i] < 100_000_000.00),
//              IllegalArgumentException::new);
//        check(() -> IntStream.range(0, 2).allMatch(i -> sides[i] > 0),
//              IllegalArgumentException::new);
//        check(() -> IntStream.range(0, 2).allMatch(i -> sides[i] + sides[i + 1] > sides[i + 2]),
//              IllegalArgumentException::new);
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
}
