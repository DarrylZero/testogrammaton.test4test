package com.steammachine.testmachine.infrastructure.tests;

import com.steammachine.common.utils.commonutils.CommonUtils;
import com.steammachine.testmachine.infrastructure.tests.factory.Infrastructure;
import com.steammachine.testmachine.infrastructure.tests.factory.InfrastructureFactory;
import lombok.Value;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static com.steammachine.common.utils.commonutils.CommonUtils.measureTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

@Tag("infrastructure_test")
class InfrastructureTest {

    private final Infrastructure infra = InfrastructureFactory.instance(Infrastructure.class);

    @Test
    @DisplayName("Triangle square must be calculated well")
    void triangleSquareIsCalculatedCorrect() {
        Assertions.assertEquals(6.0, infra.triangleSquare(3.0, 4.0, 5.0));
    }

    @Value
    static class Triplet {
        String name;
        double a;
        double b;
        double c;
        Double result;
        boolean correct;
    }

    private static final List<Triplet> TRIPLETS =
            Arrays.asList(
                    new Triplet("All zeroes", 0, 0, 0, null, false),
                    new Triplet("Difference is less or equal than zero 1", 1, 2, 3, null, false),
                    new Triplet("Difference is less or equal than zero 2", 2, 3, 1, null, false),
                    new Triplet("Difference is less or equal than zero 3", 3, 2, 1, null, false),
                    new Triplet("One side is zero", 3, 3, 0, null, false),
                    new Triplet("One side is less than zero", 3, 3, -1, null, false),
                    new Triplet("All sides is less than zero", -3, -3, -1, null, false),
                    new Triplet("Correct calculation", 3, 4, 5, 6.0, true)
            );

    private static final List<Triplet> SUM_TRIPLETS =
            Arrays.asList(
                    new Triplet("Difference is less or equal than zero 1", 1, 2, 3, null, false),
                    new Triplet("Difference is less or equal than zero 2", 1, 3, 2, null, false),
                    new Triplet("Difference is less or equal than zero 3", 2, 1, 3, null, false),
                    new Triplet("Difference is less or equal than zero 4", 2, 3, 1, null, false),
                    new Triplet("Difference is less or equal than zero 5", 3, 1, 2, null, false),
                    new Triplet("Difference is less or equal than zero 6", 3, 1, 1, null, false));




    @TestFactory
    Stream<DynamicTest> testWrongDataAllCombinations() {
        return SUM_TRIPLETS
                .stream()
                .map(trip -> dynamicTest(
                        trip.getName(),
                        () -> assertThrows(IllegalArgumentException.class,
                                           () -> infra.triangleSquare(trip.getA(), trip.getB(), trip.getC()))));
    }

    @TestFactory
    Stream<DynamicTest> testWrongData() {
        return TRIPLETS
                .stream()
                .filter(trip -> !trip.isCorrect())
                .map(trip -> dynamicTest(
                        trip.getName(),
                        () -> assertThrows(IllegalArgumentException.class,
                                           () -> infra.triangleSquare(trip.getA(), trip.getB(), trip.getC()))));
    }

    @TestFactory
    Stream<DynamicTest> testCorrectCalculation() {
        return TRIPLETS
                .stream()
                .filter(Triplet::isCorrect)
                .map(trip -> dynamicTest(
                        trip.getName(),
                        () -> assertEquals(trip.result, infra.triangleSquare(trip.getA(), trip.getB(), trip.getC()))));
    }

    @Test
    @DisplayName("Million times must be executed within 500 millis")
    void testTimeMeasure() {
        Assertions.assertTimeout(Duration.of(1000, ChronoUnit.MILLIS),
                                 () -> measureTime(() -> infra
                                         .triangleSquare(3100.0, 3100.0, 3100.0), 1000_000));
    }


}
