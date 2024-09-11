package ru.stqa.geometry.figures;

public record Triangle(double a, double b, double c) {

    //вывод на печать площади, если трегольник существует и сообщения, что треугольник не существует (вынести в отдельный класс)
    public static void printTriangleArea(Triangle t) {
        String text = String.format("Площадь треугольника со сторонами %f, %f, %f = ", t.a, t.b, t.c) + t.triangleArea();

        if (t.isTriangle()) {
            System.out.println(text);
        } else {
            System.out.printf("Треугольник со сторонами %f, %f, %f  не существует%n", t.a, t.b, t.c);
        }
    }

    //проверка существования треугольника, длины>0, сумма длин двух сторон > 3
    public boolean isTriangle() {
        return (this.a > 0 && this.b > 0 && this.c > 0 && (this.a + this.b) > this.c && (this.a + this.c) > this.b && (this.c + this.b) > this.a);
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
}
