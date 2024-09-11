package ru.stqa.geometry.figures;

public class Rectangle {

    private double a;
    private double b;

    public Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public static void printRectangleArea(double a, double b) {
        // System.out.println("Площадь прямоугольника со сторонами" + a + " и " + b + " = " + rectangleArea(a,b));
        var text = String.format("Площадь прямоугольника со сторонами %f и % f = % f", a, b, rectangleArea(a, b));
        System.out.println(text);
    }

    private static Double rectangleArea(double a, double b) {
        return a * b;
    }
}
