package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static java.lang.Double.NaN;

public class TriangleTests {
    //Блок тестов на проверку корректности условий создания треугольника в конструкторе

    // Тест на проверку отрицательной стороны a
    @Test
    void cannotCreateTriangleWithNegativeSideA() {
        try {
            new Triangle(-5, 5, 9);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            // OK
        }
    }

    // Тест на проверку отрицательной стороны b
    @Test
    void cannotCreateTriangleWithNegativeSideB() {
        try {
            new Triangle(5, -5, 9);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            // OK
        }
    }

    // Тест на проверку отрицательной стороны c
    @Test
    void cannotCreateTriangleWithNegativeSideC() {
        try {
            new Triangle(5, 5, -9);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            // OK
        }
    }

    // Тест на проверку суммы сторон треугольника a+b<c
    @Test
    void cannotCreateTriangleWithSidesAplusBLessC() {
        try {
            new Triangle(5, 5, 15);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            // OK
        }
    }

    // Тест на проверку суммы сторон треугольника a+с<b
    @Test
    void cannotCreateTriangleWithSidesAplusCLessB() {
        try {
            new Triangle(5, 15, 5);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            // OK
        }
    }

    // Тест на проверку суммы сторон треугольника b+с<a
    @Test
    void cannotCreateTriangleWithSidesBplusCLessA() {
        try {
            new Triangle(5, 15, 5);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            // OK
        }
    }

    //Блок тестов на проверку корректности работы методов класса
    // Тест корректности расчета площади
    @Test
    void canCalculateArea() {
        var triangle1 = new Triangle(6, 8, 10);
        var triangle2 = new Triangle(5, 5, 5);
        var triangle3 = new Triangle(5, 5, 9);
        Assertions.assertEquals(24.000000, triangle1.triangleArea());
        Assertions.assertEquals(10.825317547305483, triangle2.triangleArea());
        Assertions.assertEquals(9.807522622966516, triangle3.triangleArea());
    }

    // Тест метода расчета полупериметра
    @Test
    void canCalculateHalfPerimeter() {
        var triangle1 = new Triangle(6, 8, 10);
        Assertions.assertEquals(12, triangle1.triangleHalfPerimeter());
    }
}