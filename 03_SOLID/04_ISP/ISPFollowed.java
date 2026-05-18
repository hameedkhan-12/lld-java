interface TwoDimensionalShape {
    double area();
}

interface ThreeDimensionalShape {
    double area();
    double volume();
}

class Square implements TwoDimensionalShape{
    private double side;
    public Square(double s){
        this.side = s;
    }

    @Override
    public double area(){
        return side * side;
    }
}

class Rectangle implements TwoDimensionalShape {
    private double length, width;
    public Rectangle(double l, double w){
        this.length = l;
        this.width = w;
    }

    @Override
    public double area(){
        return length * width;
    }
}

class Cube implements ThreeDimensionalShape {
    private double side;

    public Cube(double s){
        this.side = s;
    }

    @Override
    public double area(){
        return 6 * side * side;
    }


    @Override
    public double volume(){
        return side * side * side;
    }
}

public class ISPFollowed {
    public static void main(String[] args){
        TwoDimensionalShape square = new Square(5);
        TwoDimensionalShape rectangle = new Rectangle(4,6);
        ThreeDimensionalShape cube = new Cube(3);

        System.out.println("Area of square: "+square.area());
        System.out.println("Area of rectangle: "+rectangle.area());
        System.out.println("Area of cube: "+cube.area());
        System.out.println("Volume of cube: "+cube.volume());
    }
}