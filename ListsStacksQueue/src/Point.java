import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * A Class to model a point on the coordinate plane
 *
 * @author Yavik Kapadia
 * @version 10-12-2020
 */
public class Point implements Comparable<Point> {
    //private fields
    private double x;
    private double y;

    /**
     * Main method
     */
    public static void main(String[] args) {
        Point[] points = new Point[100];
        // Randomly generated 100 points
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point(Math.random() * 5, Math.random() * 5);
        }
        //Display the points in increasing order of their x-coordinates
        Arrays.sort(points);
        List<Point> x_coords = Arrays.asList(points);
        System.out.println("Points sorted in increasing order of their x-coordinates: ");
        System.out.println(x_coords);

        //Display the points in increasing order of their y-coordinates
        Arrays.sort(points, new CompareY());
        List<Point> y_coords = Arrays.asList(points);
        System.out.println("Points sorted in increasing order of their x-coordinates: ");
        System.out.println(y_coords);


    }

    /**
     * constructor for creating a point
     *
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;

    }

    /**
     * returns x-coordinate value
     *
     * @return x-coordinate value
     */
    public double getX() {
        return x;
    }

    /**
     * Sets x-coordinate
     *
     * @param x x-coordinate to change or to set to
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * retuns y-coordinate value
     *
     * @return y-coordinate value
     */
    public double getY() {
        return y;
    }

    /**
     * Sets y-coordinate value
     *
     * @param y y-coordinate to change or to set to
     */
    public void setY(double y) {
        this.y = y;
    }


    /**
     * compares x-coordinates of the point object; if first x is greater than second return 1
     * if same, compare Y coordinates; else return negative -1
     *
     * @param p2 second point that is being compared
     * @return 1 if p1 > p2, -1 if p1 < p2, compares y-coords if p1 == p2
     */
    public int compareTo(Point p2) {
        int result;
        if (getX() == p2.getX()) {
            result = Double.compare(getY(), p2.getY());
        } else if (getX() > p2.getX()) {
            result = 1;
        } else {
            result = -1;
        }

        return result;
    }

    @Override
    public String toString() {
        return String.format("(%.2f, %.2f)", getX(), getY());
    }
}

/**
 * Class to compare two y-coordinates between two sets of points
 */
class CompareY implements Comparator<Point> {

    /**
     * compares two point's y coordinates
     *
     * @param p1 first point being compared
     * @param p2 second point being compared
     * @return 1 if p1 > p2, -1 if p1 < p2, compares x-coords if p1 == p2
     */
    public int compare(Point p1, Point p2) {
        int result;
        double x1 = p1.getX();
        double y1 = p1.getY();
        double x2 = p2.getX();
        double y2 = p2.getY();
        if (y1 == y2) {
            result = Double.compare(x1, x2);
        } else if (y1 > y2) {
            result = 1;
        } else {
            result = -1;
        }
        return result;
    }
}

