public class Geometry {
    public static void main(String[] args) {
       printSquareArea(8.0);
       printSquareArea(5.0);
       printSquareArea(4.0);
       printRectangleArea(3.0, 5.0);
       printRectangleArea(7.0, 9.0);
        /*Лекция 1.3 Переменные
        var side = 7.0;
        System.out.println("Площадь квадрата со стороной " + side + " = " + (side * side));
        side = 5.0;
        System.out.println("Площадь квадрата со стороной " + side + " = " + (side * side));
        side = 5;
        var a = 2;
        //a = 2.0; // не получится преобразование
        var b = "2";
        b ="3";
        b = "" + 3; // изменить числовой тип на строковый
        */

    }

    private static void printRectangleArea(double a, double b) {
        System.out.println("Площадь прямоугольника " + a + " и " + b + " = " + rectangleArea(a,b));
    }

    private static Double rectangleArea(double a, double b) {
        return a * b;
    }

    static void printSquareArea(double a) {
        System.out.println("Площадь квадрата со стороной " + a + " = " + squareArea(a));
    }

    private static double squareArea(double a) {
        return a * a;
    }

}
