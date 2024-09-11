package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static java.lang.Double.NaN;

public class TriangleTests {
    // Проверка расчета площади
    @Test
    void canCalculateArea() {
        var triangle1 = new Triangle(6, 8, 10);
        var triangle2 = new Triangle(5, 5, 5);
        var triangle3 = new Triangle(5, 5, 9);
        var triangle4 = new Triangle(1, 2, 15);
        Assertions.assertEquals(24.000000, triangle1.triangleArea());
        Assertions.assertEquals(10.825317547305483, triangle2.triangleArea());
        Assertions.assertEquals(9.807522622966516, triangle3.triangleArea());
        Assertions.assertEquals(NaN, triangle4.triangleArea());
    }

    // Проверка метода существования треугольника
    @Test
    void canMakeTriangle() {
        var triangle1 = new Triangle(6, 8, 10);
        Assertions.assertTrue(triangle1.isTriangle());
        var triangle2 = new Triangle(0, 8, 10);
        Assertions.assertFalse(triangle2.isTriangle());
    }

    // Проверка метода расчета полупериметра
    @Test
    void canCalculateHalfPerimeter() {
        var triangle1 = new Triangle(6, 8, 10);
        Assertions.assertEquals(12, triangle1.triangleHalfPerimeter());
    }
}