import java.text.Format;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;


/**
 * A Class to model a point on the coordinate plane
 *
 * @author Yavik Kapadia
 * @version 10-28-2020
 */
class Point implements Comparable<Point> {
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
            points[i] = new Point(Math.random() * 100, Math.random() * 100);
        }
        //Display the points in increasing order of their x-coordinates
        Arrays.sort(points);
        System.out.println("List of coordinate from which we are looking for the closest pair: ");
        for (Point o: points) {
            System.out.println(o);
        }
        System.out.print("The closest pair of points are: ");
        System.out.print(Pair.getClosestPair(points));
        System.out.println();
        System.out.print("The distance between " + Pair.getClosestPair(points).getP1()+ " and " + Pair.getClosestPair(points).getP2() + ": ");
        System.out.printf("%.2f",(Pair.getClosestPair(points).getDistance()));

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
     * returns y-coordinate value
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

/**
 * A class to find closest pair
 */
class Pair {
    //private variables
    public Point p1;
    public Point p2;

    /**
     * Constructs Pair object
     *
     * @param p1 first point
     * @param p2 second point
     */
    public Pair(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    /**
     * Compute the distance between two points p1 and p2
     *
     * @param p1 first point
     * @param p2 second point
     * @return distance between p1 and p2
     */
    public static double distance(Point p1, Point p2) {
        double x1 = p1.getX();
        double y1 = p1.getY();
        double x2 = p2.getX();
        double y2 = p2.getY();

        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    }

    /**
     * Return the distance of the closest pair of points
     *
     * @param points array of coordinates from which we are trying to find closest pair
     * @return closest pair
     */
    public static Pair getClosestPair(double[][] points) {
        Point[] p = new Point[points.length];

        for (int i = 0; i < points.length; i++) {
            p[i] = new Point(points[i][0], points[i][1]);
        }

        return getClosestPair(p);
    }

    /**
     * Return the distance of the closest pair of points
     *
     * @param points array of points
     * @return closest pair
     */
    public static Pair getClosestPair(Point[] points) {
        Arrays.sort(points);
        Point[] sortedY = new Point[points.length];
        System.arraycopy(points, 0, sortedY, 0, sortedY.length);
        Arrays.sort(sortedY, new CompareY());

        return distance(points, 0, points.length - 1, sortedY);
    }


    /**
     * @param pointsOrderedOnX array of points ordered by X coordinates
     * @param low              low index
     * @param high             high index
     * @param pointsOrderedOnY array of points ordered by Y coordinates
     * @return closest pair of points
     */

    public static Pair distance(Point[] pointsOrderedOnX, int low, int high, Point[] pointsOrderedOnY) {
        if (high - low <= 3) {

            Point p1 = pointsOrderedOnX[low];
            Point p2 = pointsOrderedOnX[low + 1];
            for (int i = low; i <= high; i++) {
                for (int j = i + 1; j <= high; j++) {
                    if (distance(pointsOrderedOnX[i], pointsOrderedOnX[j]) < distance(p1, p2)) {
                        p1 = pointsOrderedOnX[i];
                        p2 = pointsOrderedOnX[j];
                    }
                }
            }
            return new Pair(p1, p2);

        }
        int mid = (low + high) / 2;

        Pair p1 = distance(pointsOrderedOnX, low, mid, pointsOrderedOnY);
        Pair p2 = distance(pointsOrderedOnX, mid + 1, high, pointsOrderedOnY);

        double d = Math.min(p1.getDistance(), p2.getDistance());

        List<Point> stripL = new ArrayList<>();
        List<Point> stripR = new ArrayList<>();
        double midX = pointsOrderedOnX[mid].getX();
        for (Point p : pointsOrderedOnY) {
            if (p.getX() <= midX && midX - p.getX() <= d) {
                stripL.add(p);
            } else if (p.getX() > midX && p.getX() - midX <= d) {
                stripR.add(p);
            }
        }

        Pair p3 = null;
        int r = 0; // stripR current index
        for (Point p : stripL) {
            // Starting at the bottom of stripR, seek the first index within distance
            // d of current stripL point
            while (r < stripR.size() && stripR.get(r).getY() <= p.getY() - d) {
                r++;
            }

            int r1 = r; // copy current index for use in next loop
            while (r1 < stripR.size() && r1 - r <= 7 &&
                    Math.abs(stripR.get(r1).getY() - p.getY()) <= d) {
                if (distance(p, stripR.get(r1)) < d) {
                    d = distance(p, stripR.get(r1));
                    p3 = new Pair(p, stripR.get(r1));
                }
                r1++;
            }
        }

        return Objects.requireNonNullElseGet(p3, () -> min(p1, p2));

    }

    /**
     * Find minimum distance between points
     *
     * @param p1 first point whose distance is being calculated
     * @param p2 second point whose distance is being calculated
     * @return closer of two points
     */
    public static Pair min(Pair p1, Pair p2) {
        if (p1.getDistance() < p2.getDistance())
            return p1;
        else
            return p2;
    }

    /**
     * gets first point
     *
     * @return x and y coords for point 1
     */
    public Point getP1() {
        return p1;
    }

    /**
     * gets second point
     *
     * @return x and y coords for point 2
     */
    public Point getP2() {
        return p2;
    }

    /**
     * Get distance between two points
     *
     * @return distance between to points
     */
    public double getDistance() {
        return distance(p1, p2);
    }

    @Override
    public String toString() {

        return p1 + ", " + p2;
    }
}

