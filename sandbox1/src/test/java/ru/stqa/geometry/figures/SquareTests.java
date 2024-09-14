package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquareTests {
    @Test
    void canCalculateArea() {
        var s = new Square(4);
        double result = s.area();
        Assertions.assertEquals(25.0, result);
       /* if (result != 25.0) {
            throw new AssertionError(String.format("Expected %f, actual %f", 25.00, result));
        }*/
    }

    @Test
    void canCalculatePerimeter() {
        Assertions.assertEquals(20.0, new Square(5).perimeter());
    }

    @Test
    void cannotCreateSquareWithNegativeSide() {
        try {
            new Square(-5);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            // OK
        }
    }

    @Test
    void testEquality() {
        var s1 = new Square(5);
        var s2 = new Square(5);
        Assertions.assertEquals(s1, s2);
    }

    @Test
    void testNonEquality() {
        var s1 = new Square(5);
        var s2 = new Square(4);
        Assertions.assertNotEquals(s1, s2);
    }

    @Test
    void testFail() {
        var s1 = new Square(5);
        var s2 = new Square(5);
        Assertions.assertTrue(s1.equals(s2));
    }
}
