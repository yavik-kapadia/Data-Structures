import java.util.Scanner;

/**
 * A Class to test the geometric object class  which extends the circle, rectangle, and triangle class
 *
 * @author Yavik Kapadia
 * @version 09-08-2020
 */
public class GeometricTester {
    public static void main(String[] args) {
        boolean invalidTriangle = true;
        double[] side = new double[3];
        Scanner in = new Scanner(System.in);

        GeometricObject geoObject1 = new Circle(5);
        geoObject1.setFilled(false);
        geoObject1.setColor("Lilac");

        GeometricObject geoObject2 = new Rectangle(5, 3);
        geoObject2.setFilled(true);
        geoObject2.setColor("Blue");

        while (invalidTriangle) {
            System.out.println("Please enter a length for each side of this triangle: ");
            System.out.print("Enter the first side of the triangle: ");
            side[0] = in.nextDouble();
            System.out.print("Enter the second side of the triangle: ");
            side[1] = in.nextDouble();
            System.out.print("Enter the third side of the triangle: ");
            side[2] = in.nextDouble();

            if ((side[0] + side[1] > side[2]) && (side[1] + side[2] > side[0]) && (side[0] + side[2] > side[1])) {
                invalidTriangle = false;

            } else {
                System.out.println("These sides do not make a triangle.");
                System.out.println("The sum of two sides have to be greater than the third side.");
            }
        }
        GeometricObject geoObject3 = new Triangle(side[0], side[1], side[2]);

        System.out.println("What color is this triangle? ");
        geoObject3.setColor(in.next());
        System.out.println("Is the triangle filled? ");
        geoObject3.setFilled(in.nextBoolean());

        System.out.println("Triangle:");
        System.out.printf("Perimeter: %.2f " + "%n", geoObject3.getPerimeter());
        System.out.printf("Area: %.2f  " + "%n", geoObject3.getArea());
        System.out.println("Color: " + geoObject3.getColor() + ".");
        System.out.println("Filled: " + geoObject3.isFilled());
        System.out.println("\n");

        System.out.println("Circle:");
        System.out.println("Creation Date: " + geoObject1.getDateCreated());
        System.out.printf("Perimeter: %.2f " + "%n", geoObject1.getPerimeter());
        System.out.printf("Area: %.2f  " + "%n", geoObject1.getArea());
        System.out.println("Color: " + geoObject1.getColor() + ".");
        System.out.println("Filled: " + geoObject1.isFilled());
        System.out.println("\n");

        System.out.println("Rectangle:");
        System.out.println("Creation Date: " + geoObject2.getDateCreated());
        System.out.printf("Perimeter: %.2f " + "%n", geoObject2.getPerimeter());
        System.out.printf("Area: %.2f  " + "%n", geoObject2.getArea());
        System.out.println("Color: " + geoObject2.getColor() + ".");
        System.out.println("Filled: " + geoObject2.isFilled());
        System.out.println("\n");

    }
}

abstract class GeometricObject {
    private String color = "white";
    private boolean filled;
    private java.util.Date dateCreated;

    /**
     * Construct a default geometric object
     */
    protected GeometricObject() {
        dateCreated = new java.util.Date();
    }

    /**
     * Construct a geometric object with color and filled value
     */
    protected GeometricObject(String color, boolean filled) {
        dateCreated = new java.util.Date();
        this.color = color;
        this.filled = filled;
    }

    /**
     * Return color
     */
    public String getColor() {
        return color;
    }

    /**
     * Set a new color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Return filled. Since filled is boolean,
     * the get method is named isFilled
     */
    public boolean isFilled() {
        return filled;
    }

    /**
     * Set a new filled
     */
    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    /**
     * Get dateCreated
     */
    public java.util.Date getDateCreated() {
        return dateCreated;
    }

    /**
     * Return a string representation of this object
     */
    public String toString() {
        return "created on " + dateCreated + "\ncolor: " + color +
                " and filled: " + filled;
    }

    /**
     * Abstract method getArea
     */
    public abstract double getArea();

    /**
     * Abstract method getPerimeter
     */
    public abstract double getPerimeter();
}

/**
 * a class to model a Circle
 */
class Circle extends GeometricObject {
    private double radius;

    public Circle() {
    }

    public Circle(double radius) {
        this.radius = radius;
    }

    /**
     * Return radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Set a new radius
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    /** Return area */
    public double getArea() {
        return radius * radius * Math.PI;
    }

    /**
     * Return diameter
     */
    public double getDiameter() {
        return 2 * radius;
    }

    @Override
    /** Return perimeter */
    public double getPerimeter() {
        return 2 * radius * Math.PI;
    }

    /* Print the circle info */
    public void printCircle() {
        System.out.println("The circle is created " + getDateCreated() +
                " and the radius is " + radius);
    }
}

class Triangle extends GeometricObject {

    private double side_1;
    private double side_2;
    private double side_3;

    public Triangle() {
    }

    public Triangle(double side_1, double side_2, double side_3) {
        this.side_1 = side_1;
        this.side_2 = side_2;
        this.side_3 = side_3;

    }

    @Override
    /**
     * Return area using Heron's formula
     */
    public double getArea() {
        double p = getPerimeter() / 2; // p = (side_1 + side_2 + side_3)/2
        double area = Math.sqrt((p) * (p - side_1) * (p - side_2) * (p - side_3));
        return area;
    }

    @Override
    /**
     *return perimeter
     * @return perimeter of this triangle
     **/
    public double getPerimeter() {
        return side_1 + side_2 + side_3;
    }

}
class Rectangle extends GeometricObject {
    private double width;
    private double height;

    public Rectangle() {
    }

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Return width
     */
    public double getWidth() {

        return width;
    }

    /**
     * Set a new width
     */
    public void setWidth(double width) {

        this.width = width;
    }

    /**
     * Return height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Set a new height
     */
    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    /** Return area */
    public double getArea() {
        return width * height;
    }

    @Override
    /** Return perimeter */
    public double getPerimeter() {
        return 2 * (width + height);
    }
    
}

