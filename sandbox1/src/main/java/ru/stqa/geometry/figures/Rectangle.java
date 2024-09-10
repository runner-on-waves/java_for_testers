package ru.stqa.geometry.figures;

public class Rectangle {
   public static void printRectangleArea(double a, double b) {
        System.out.println("Площадь прямоугольника " + a + " и " + b + " = " + rectangleArea(a,b));
    }

    private static Double rectangleArea(double a, double b) {
        return a * b;
    }
}
