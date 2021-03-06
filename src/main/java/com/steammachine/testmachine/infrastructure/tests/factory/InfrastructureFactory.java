package com.steammachine.testmachine.infrastructure.tests.factory;

import com.steammachine.testmachine.sdk.ContextHook;
import com.steammachine.testmachine.sdk.TestedObjectFactory;

import java.util.stream.IntStream;

import static com.steammachine.common.utils.commonutils.CommonUtils.check;

/**
 * {@link InfrastructureFactory}
 */
public class InfrastructureFactory implements ContextHook, Infrastructure {

    private static TestedObjectFactory factory;

    public static Infrastructure instance(Class<Infrastructure> clazz) {
        return factory == null ? new InfrastructureFactory() : factory.get(clazz);
    }

    @Override
    public void install(TestedObjectFactory f) {
        factory = f;
    }

    @Override
    public double triangleSquare(double a, double b, double c) {
        double[] sides = {a, b, c, a, b};
        check(() -> IntStream.rangeClosed(0, 2).allMatch(i -> sides[i] < 100_000_000.00),
              IllegalArgumentException::new);
        check(() -> IntStream.rangeClosed(0, 2).allMatch(i -> sides[i] > 0),
              IllegalArgumentException::new);
        check(() -> IntStream.rangeClosed(0, 2).allMatch(i -> sides[i] + sides[i + 1] > sides[i + 2]),
              IllegalArgumentException::new);

        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
}
