package ru.stqa.geometry.figures;

public record Square(double side) {
    public Square {
        if (side < 0) {
            throw new IllegalArgumentException("Square side should be non-negative");
        }
    }


    public static void printSquareArea(Square s) {
        //System.out.println("Площадь квадрата со стороной " + side + " = " + squareArea(side));
        String text = String.format("Площадь квадрата со стороной %f = %f", s.side, s.area());
        System.out.println(text);
    }

    public static void printSquarePerimeter(Square s) {
        String text = String.format("Периметр квадрата со стороной %f = %f", s.side, s.perimeter());
        System.out.println(text);
    }

    public double area() {
        return this.side * this.side;
    }

    public double perimeter() {
        return this.side * 4;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Square) obj;
        return Double.doubleToLongBits(this.side) == Double.doubleToLongBits(that.side);
    }

    @Override
    public String toString() {
        return "Square[" +
                "side=" + side + ']';
    }

}
