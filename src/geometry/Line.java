package geometry;

/**
 * geometry.Line class assigns a starting point an ending point to each line. Can
 * calculate length of a line, it's midpoint, whether or not a line intersects
 * another line and if so, at which point. It can also determine whether or
 * not 2 lines are equal.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class Line {
    private static final double EPSILON = 0.0001;
    //Declare object parameters.
    private Point start;
    private Point end;
    private LinearEquation equation;

    /**
     * Constructors assign a start and end point to a line.
     *
     * @param start starting point.
     * @param end   end point.
     */
    public Line(Point start, Point end) {
        //Assign line's start and end values.
        this.start = start;
        this.end = end;
        this.equation = new LinearEquation(this);
    }

    public LinearEquation getEquation() {
        return equation;
    }

    /**
     * Constructors assign an x and y coordinate to both the start and end
     * point of a line.
     *
     * @param x1 x coordinate of start point.
     * @param y1 y coordinate of start point.
     * @param x2 x coordinate of end point.
     * @param y2 y coordinate of end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        //Assign line's coordinates of start and end values.
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }


    /**
     * Calculates the midpoint of a line.
     *
     * @return midpoint of a line.
     */
    public Point middle() {
        //Average x value of start and end points.
        double aveX = (start.getX() + end.getX()) / 2;
        //Average y value of start and end points.
        double aveY = (start.getY() + end.getY()) / 2;
        //Return midpoint of line.
        return new Point(aveX, aveY);
    }

    /**
     * Assigns starting point to a line.
     *
     * @return geometry.Point (starting point).
     */
    public Point getStart() {
        //Return start point of a line.
        return this.start;
    }

    /**
     * Assigns end point to a line.
     *
     * @return geometry.Point (end point).
     */
    public Point getEnd() {
        //Return end point of a line.
        return this.end;
    }

    /**
     * Method returns true if a point lies on a given line.
     *
     * @param point geometry.Point.
     * @return Boolean - true if point lies on line.
     */
    public boolean pointOnLine(Point point) {
        //If line is vertical.
        if (this.getEquation().getGradient() == Double.POSITIVE_INFINITY) {
            if (Math.abs(this.start.getX() - point.getX()) < EPSILON) {
                return true;
            }
        }
        return Math.abs(point.getY() - (this.getEquation().getGradient() * point.getX() + this.getEquation().getyIntercept())) < EPSILON;
    }
}
