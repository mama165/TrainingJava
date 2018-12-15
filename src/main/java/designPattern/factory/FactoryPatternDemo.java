package designPattern.factory;

public class FactoryPatternDemo {

    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();

        //get an object of Circle and call its draw extractLines.
        Shape shape1 = shapeFactory.getShape("CIRCLE");

        //call draw extractLines of Circle
        shape1.draw();

        //get an object of Rectangle and call its draw extractLines.
        Shape shape2 = shapeFactory.getShape("RECTANGLE");

        //call draw extractLines of Rectangle
        shape2.draw();

        //get an object of Square and call its draw extractLines.
        Shape shape3 = shapeFactory.getShape("SQUARE");

        //call draw extractLines of square
        shape3.draw();
    }
}