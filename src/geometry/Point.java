package geometry;

/**
 * Class creates a point with an  x and y coordinate. Changes its toString
 * method, calculates distance between points and determines if 2 points are
 * equal.
 *
 * @author Yonatan Segal
 * @version 1
 */

public class Point {
    private static final double EPSILON = 0.0001;
    //Declare object parameters.
    private double x;
    private double y;

    /**
     * Changes print method of a point.
     *
     * @return geometry.Point string in the form of a  point.
     */
    public String toString() {
        //Print point in the form (x, y).
        return "(" + x + ", " + y + ")";
    }

    /**
     * Constructor assigns x and y coordinate to point.
     *
     * @param x x-coordinate of point.
     * @param y y-coordinate of point.
     */
    public Point(double x, double y) {
        //Assign point values.
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates distance between 2 points.
     *
     * @param other geometry.Point 'other' is sent for comparison to current point.
     * @return distance between 2 points.
     */
    public double distance(Point other) {
        //Distance between x values.
        double dx = this.x - other.getX();
        //Distance between y values.
        double dy = this.y - other.getY();
        //Return square root of the sum of the difference's squares.
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * Determines if 2 point have the same coordinates.
     *
     * @param other geometry.Point 'other' is sent for comparison to current point.
     * @return boolean. True if Points are equal. False otherwise.
     */
    public boolean equals(Point other) {
        //Return true if 2 point have the same x and y values.
        return (Math.abs(this.x - other.x) < EPSILON && Math.abs(this.y - other.y) < EPSILON);
    }

    /**
     * Assigns x-coordinate to point.
     *
     * @return x-coordinate of point.
     */
    public double getX() {
        //Assign x value to point.
        return this.x;
    }

    /**
     * Assigns y-coordinate to point.
     *
     * @return y-coordinate of point.
     */
    public double getY() {
        //Assign y value to point.
        return this.y;
    }

    /**
     * Method sets point's x value.
     *
     * @param newX Double.
     * @return X value.
     */
    public double setX(double newX) {
        return newX;
    }

    /**
     * Method sets point's x value.
     *
     * @param newY Double.
     * @return Y value.
     */
    public double setY(double newY) {
        return newY;
    }
}