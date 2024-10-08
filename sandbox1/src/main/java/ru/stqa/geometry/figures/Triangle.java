package ru.stqa.geometry.figures;

import java.util.Objects;

public record Triangle(double a, double b, double c) {

    public Triangle {
        if (a < 0 || b < 0 || c < 0) {
            throw new IllegalArgumentException("Triangle side should be non-negative");
        }
        if (a + b < c || a + c < b || b + c < a) {
            throw new IllegalArgumentException("Two triangle sides sum should always be more then third");
        }
    }

    //Метод вывода на печать площади - вынести в отдельный класс Print
    public static void printTriangleArea(Triangle t) {
        String text = String.format("Площадь треугольника со сторонами %f, %f, %f = ", t.a, t.b, t.c) + t.triangleArea();
        System.out.println(text);
    }

    /* Метод расчета площади треугольника по формуле Герона
    формула Герона: S = √(p * (p - a) * (p - b) * (p - c)),
    где a, b и c - длины сторон треугольника;
    p - полупериметр треугольника: p = (a + b + c) / 2
 */
    public double triangleArea() {
        double halfP = triangleHalfPerimeter();
        return Math.sqrt(halfP * (halfP - this.a) * (halfP - this.b) * (halfP - this.c));
    }

    //Метод расчета полупериметра треугольника
    public double triangleHalfPerimeter() {
        return (this.a + this.b + this.c) / 2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return (Double.compare(this.a, triangle.a) == 0 && Double.compare(this.b, triangle.b) == 0 && Double.compare(this.c, triangle.c) == 0)
                || (Double.compare(this.a, triangle.a) == 0 && Double.compare(this.b, triangle.c) == 0 && Double.compare(this.c, triangle.b) == 0)
                || (Double.compare(this.a, triangle.b) == 0 && Double.compare(this.b, triangle.c) == 0 && Double.compare(this.c, triangle.a) == 0)
                || (Double.compare(this.a, triangle.b) == 0 && Double.compare(this.b, triangle.a) == 0 && Double.compare(this.c, triangle.c) == 0)
                || (Double.compare(this.a, triangle.c) == 0 && Double.compare(this.b, triangle.b) == 0 && Double.compare(this.c, triangle.a) == 0)
                || (Double.compare(this.a, triangle.c) == 0 && Double.compare(this.b, triangle.a) == 0 && Double.compare(this.c, triangle.b) == 0);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
