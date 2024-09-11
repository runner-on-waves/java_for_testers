package ru.stqa.geometry.figures;

public record Square(double side) {


    public static void printSquareArea(Square s) {
        //System.out.println("Площадь квадрата со стороной " + side + " = " + squareArea(side));
        String text = String.format("Площадь квадрата со стороной %f = %f", s.side, s.area());
        System.out.println(text);
    }

    public double area() {
        return this.side * this.side;
    }

    public double perimeter() {
        return this.side * 4;
    }
}
