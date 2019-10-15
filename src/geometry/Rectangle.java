package geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * geometry.Rectangle has corner points and lines connecting them.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    private Point upperRight;
    private Point lowerLeft;
    private Point lowerRight;

    private Line left;
    private Line right;
    private Line top;
    private Line bottom;

    /**
     * Constructor creates a new rectangle with location and width/height.
     *
     * @param upperLeft geometry.Point.
     * @param width     Width of rectangle.
     * @param height    Height of rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this(upperLeft, new Point(upperLeft.getX() + width, upperLeft.getY()),
                new Point(upperLeft.getX(), upperLeft.getY() + height),
                new Point(upperLeft.getX() + width, upperLeft.getY() + height));
        this.height = height;
        this.width = width;
    }

    /**
     * Constructor creates a new rectangle with location and width/height.
     *
     * @param upperLeft  upper left point.
     * @param upperRight upper right point.
     * @param lowerLeft  lower left point.
     * @param lowerRight lower right point.
     */
    private Rectangle(Point upperLeft, Point upperRight, Point lowerLeft, Point lowerRight) {
        this.upperLeft = upperLeft;
        this.upperRight = upperRight;
        this.lowerLeft = lowerLeft;
        this.lowerRight = lowerRight;
        //Set top line.
        this.top = new Line(upperLeft, upperRight);
        //Set bottom line.
        this.bottom = new Line(lowerRight, lowerLeft);
        //Set left line.
        this.left = new Line(lowerLeft, upperLeft);
        //Set right line.
        this.right = new Line(lowerRight, upperRight);
    }

    /**
     * Method returns a (possibly empty) List of intersection points with the specified line.
     *
     * @param line geometry.Line.
     * @return List of points.
     */
    public List intersectionPoints(Line line) {
        List<Point> points = new ArrayList<>();
        //Check if array hits top of rectangle.
        if (line.isIntersecting(this.top)) {
            points.add(line.intersectionWith(this.top));
        }
        //Check if array hits bottom of rectangle.
        if (line.isIntersecting(this.bottom)) {
            points.add(line.intersectionWith(this.bottom));
        }
        //Check if array hits right of rectangle.
        if (line.isIntersecting(this.right)) {
            points.add(line.intersectionWith(this.right));
        }
        //Check if array hits left of rectangle.
        if (line.isIntersecting(this.left)) {
            points.add(line.intersectionWith(this.left));
        }
        return points;
    }

    /**
     * Method returns the width and height of the rectangle.
     *
     * @return width of rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Method sets width of rectangle.
     *
     * @param w width of rectangle.
     */
    public void setWidth(double w) {
        this.width = w;
    }

    /**
     * Method gets rectangle's height.
     *
     * @return height of rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Method sets rectangles height.
     *
     * @param h Height of rectangle.
     */
    public void setHeight(double h) {
        this.height = h;
    }

    /**
     * Method returns the upper-left point of the rectangle.
     *
     * @return upperleft point.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Method sets the upper-left point of the rectangle.
     *
     * @param topLeft top left corner.
     */
    public void setUpperLeft(Point topLeft) {
        this.upperLeft = topLeft;
    }

    /**
     * Method returns the lower-left point of the rectangle.
     *
     * @return lowerl eft point.
     */
    public Point getLowerLeft() {
        return this.lowerLeft;
    }

    /**
     * Method returns the lower-right point of the rectangle.
     *
     * @return lower right point.
     */
    public Point getLowerRight() {
        return this.lowerRight;
    }

    /**
     * Method returns the upper-right point of the rectangle.
     *
     * @return upper right point.
     */
    public Point getUpperRight() {
        return this.upperRight;
    }

    /**
     * Method returns the bottom line of the rectangle.
     *
     * @return geometry.Line.
     */
    public Line getBottom() {
        return this.bottom;
    }

    /**
     * Method returns the top line of the rectangle.
     *
     * @return geometry.Line.
     */
    public Line getTop() {
        return this.top;
    }

    /**
     * Method returns the top line of the rectangle.
     *
     * @return geometry.Line.
     */
    public Line getRight() {
        return this.right;
    }

    /**
     * Method returns the left line of the rectangle.
     *
     * @return geometry.Line.
     */
    public Line getLeft() {
        return this.left;
    }
}
