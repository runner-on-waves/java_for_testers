package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static java.lang.Double.NaN;

public class TriangleTests {
    //Блок тестов на проверку равенства треугольников
    @Test
    void testEquality1() {
        var t1 = new Triangle(5.0, 4.0, 8.0);
        var t2 = new Triangle(5.0, 4.0, 8.0);
        Assertions.assertEquals(t1, t2);
    }

    @Test
    void testEquality2() {
        var t1 = new Triangle(5.0, 4.0, 8.0);
        var t2 = new Triangle(5.0, 8.0, 4.0);
        Assertions.assertEquals(t1, t2);
    }

    @Test
    void testEquality3() {
        var t1 = new Triangle(5.0, 4.0, 8.0);
        var t2 = new Triangle(4.0, 8.0, 5.0);
        Assertions.assertEquals(t1, t2);
    }

    @Test
    void testEquality4() {
        var t1 = new Triangle(5.0, 4.0, 8.0);
        var t2 = new Triangle(4.0, 5.0, 8.0);
        Assertions.assertEquals(t1, t2);
    }

    @Test
    void testEquality5() {
        var t1 = new Triangle(5.0, 4.0, 8.0);
        var t2 = new Triangle(8.0, 5.0, 4.0);
        Assertions.assertEquals(t1, t2);
    }

    @Test
    void testEquality6() {
        var t1 = new Triangle(5.0, 4.0, 8.0);
        var t2 = new Triangle(8.0, 4.0, 5.0);
        Assertions.assertEquals(t1, t2);
    }
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