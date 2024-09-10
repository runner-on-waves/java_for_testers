package ru.stqa.geometry.figures;

public class Rectangle {
   public static void printRectangleArea(double a, double b) {
       // System.out.println("Площадь прямоугольника со сторонами" + a + " и " + b + " = " + rectangleArea(a,b));
       var text = String.format("Площадь прямоугольника со сторонами %f и % f = % f", a, b,rectangleArea(a,b));
       System.out.println(text);
    }

    private static Double rectangleArea(double a, double b) {
        return a * b;
    }
}
