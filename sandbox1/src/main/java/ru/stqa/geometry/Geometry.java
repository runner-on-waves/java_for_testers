package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Square;
import ru.stqa.geometry.figures.Triangle;

public class Geometry {
    public static void main(String[] args) {
        Square.printSquareArea(new Square(8.0));
        Square.printSquareArea(new Square(5.0));
        Square.printSquareArea(new Square(4.0));
        Rectangle.printRectangleArea(3.0, 5.0);
        Rectangle.printRectangleArea(7.0, 9.0);
        Triangle.printTriangleArea(new Triangle(1, 2, 15));
        Triangle.printTriangleArea(new Triangle(5, 5, 9));
        Triangle.printTriangleArea(new Triangle(6, 8, 10));
        Triangle.printTriangleArea(new Triangle(5, 5, 5));
        Triangle.printTriangleArea(new Triangle(-5, 5, 5));
        Triangle.printTriangleArea(new Triangle(0, 5, 5));
        Triangle.printTriangleArea(new Triangle(1, 2, 3));

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

}
