interface Shape {
    double area();
    double volume();
}

class Square implements Shape {
    private double side;
    public Square(double s){
        this.side = s;
    }

    @Override 
    public double area(){
        return side*side;
    }

    @Override
    public double volume(){
        throw new UnsupportedOperationException("Volume not applicable for Square!");
    }
}

class Rectangle implements Shape {
    private double length, width;

    public Rectangle(double l, double w){
        this.length = l;
        this.width = w;
    }

    @Override
    public double area(){
        return length*width;
    }

    @Override
    public double volume(){
        throw new UnsupportedOperationException("Volume not applicable for Rectangle!");
    }
}

class Cube implements Shape {
    private double side;
    public Cube(double s){
        this.side = s;
    }

    @Override
    public double area(){
        throw new UnsupportedOperationException("Area not applicable for Cube!");
    }

    @Override
    public double volume(){
        return side*side*side;
    }
}

public class ISPViolated {
    public static void main(String[] args){
        Shape square = new Square(5);
        Shape rectangle = new Rectangle(4,6);
        Shape cube = new Cube(3);

        System.out.println("Area of square: "+square.area());
        System.out.println("Area of rectangle: "+rectangle.area());
        System.out.println("Area of cube: "+cube.area());

        try{
            System.out.println("Volume of square: "+square.volume());
            System.out.println("Volume of rectangle: "+rectangle.volume());
            System.out.println("Volume of cube: "+cube.volume());
        }catch(UnsupportedOperationException e){
            System.out.println(e.getMessage());
        }
    }
}