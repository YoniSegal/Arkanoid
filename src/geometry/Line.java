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
//    private LineCompare lineCompare = new LineCompare();

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
     * Calculates length of a line.
     *
     * @return distance between 2 points.
     */
    // Return the length of the line
    public double length() {
        return this.start.distance(this.end);
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

//    /**
//     * If this line does not intersect with the rectangle, return null.
//     * Otherwise, return the closest intersection point to the start of the
//     * line.
//     *
//     * @param rect geometry.Rectangle.
//     * @return geometry.Point of intersection.
//     */
//    public Point closestIntersectionToStartOfLine(Rectangle rect) {
//        LineRectangleCompare lineRectangleCompare = new LineRectangleCompare();
//        return lineRectangleCompare.closestIntersectionToStartOfLine(this, rect);
////        //get intersection points.
////        List<Point> intersections = rect.intersectionPoints(this);
////        //If there are no intersections.
////        if (intersections.size() == 0) {
////            return null;
////            //if there is one intersection.
////        } else if (intersections.size() == 1) {
////            return intersections.get(0);
////            //If there is more than 1 intersection.
////        } else {
////            if (intersections.get(0).distance(this.start) < intersections.get(1).distance(this.start)) {
////                return intersections.get(0);
////            } else {
////                return intersections.get(1);
////            }
////        }
//    }
//
//
//    /**
//     * Returns true if the lines intersect, false otherwise.
//     *
//     * @param other (geometry.Line)
//     * @return boolean. true if lines intersect. Otherwise false.
//     */
//    public boolean isIntersecting(Line other) {
//        //Return true if 2 lines intersect.
////        return this.intersectionWith(other) != null;
//        return lineCompare.intersectionWith(this, other) != null;
//    }
//
//    /**
//     * Returns the intersection point if the lines intersect and null otherwise.
//     *
//     * @param other (geometry.Line)
//     * @return Intersection point or null.
//     */
//    public Point intersectionWith(Line other) {
////        return lineCompare.intersectionWith(this, other);
//        Point p;
//        double grad1 = this.getEquation().getGradient();
//        double grad2 = other.getEquation().getGradient();
//        boolean isGrad1Infinity = (grad1 == Double.POSITIVE_INFINITY);
//        boolean isGrad2Infinity = (grad2 == Double.POSITIVE_INFINITY);
//        //Check if either 2 lines are vertical.
//        if (isGrad1Infinity || isGrad2Infinity) {
//            //Call 'vertical' function to determine any intersection point.
//            return vertical(this, other);
//            //Check if lines are parallel.
//        } else if (sameGradient(grad1, grad2)) {
//            //null -> lines overlap or are parallel.
//            //!null -> lines touch at edge.
//            p = parallel(this, other);
//        } else {
//            /*
//            If lines aren't parallel, and neither line is vertical find
//            intersection.
//            */
//            p = regularIntersection(this, other);
//        }
//        //Return final point of intersection (or null if there is none).
//        if (p != null) {
//            System.out.println(p);
//        }
//        return p;
//    }
//
//    /**
//     * Returns true is the lines are equal, false otherwise.
//     *
//     * @param other (line)
//     * @return boolean. True if lines are equal. False otherwise.
//     */
//    public boolean equals(Line other) {
//        boolean sameStart = this.start.equals(other.start);
//        boolean sameEnd = this.end.equals(other.end);
//        boolean oppStart = this.start.equals(other.end);
//        boolean oppEnd = this.end.equals(other.start);
//        //Return true if 2 lines end and start points align.
//        return (sameStart && sameEnd || oppStart && oppEnd);
//    }
//
//    /**
//     * Calculates gradient of a line.
//     *
//     * @param line The line.
//     * @return gradient of a line.
//     */
//    public double gradient(Line line) {
//        return line.getEquation().getGradient();
////        double grad;
////        double inf = Double.POSITIVE_INFINITY;
////        //If line is vertical, assign infinite gradient.
////        if (Math.abs(line.start.getX() - line.end.getX()) < EPSILON) {
////            return inf;
////        }
////        //Calculate change in y axis.
////        double dy = line.end.getY() - line.start.getY();
////        //Calculate change in x axis.
////        double dx = line.end.getX() - line.start.getX();
////        //Return gradient of line.
////        return (dy / dx);
//    }
//
//
//    /**
//     * Calculates the y-intercept of a line.
//     *
//     * @param line geometry.Line
//     * @param grad gradient of given line.
//     * @return y intercept of given line.
//     */
//    public double yIntercept(Line line, double grad) {
//        //Return y intercept of line.
//        return (line.start.getY() - grad * line.start.getX());
//    }
//
//    /**
//     * Checks if 2 gradients are equal.
//     *
//     * @param grad1 gradient of a line.
//     * @param grad2 gradient of another line.
//     * @return boolean. True if gradients are equal. False otherwise.
//     */
//    public boolean sameGradient(double grad1, double grad2) {
//        //Return true if 2 lines have the same gradient.
//        return Math.abs(grad1 - grad2) < EPSILON;
//    }
//
//    /**
//     * Checks if 2 lines overlap over more than one point.
//     *
//     * @param line1 first line
//     * @param line2 second line
//     * @return boolean. True if lines overlap at at more than one point.
//     * False otherwise.
//     */
//    public boolean isOverlap(Line line1, Line line2) {
//        //Calculate overlap between lines.
//        double overlap1 = line2.start.getX() - line1.end.getX();
//        double overlap2 = line1.start.getX() - line2.end.getX();
//        //Calculate sum of both lines lengths minus their overlap.
//        double sum1 = line2.length() + line1.length() - overlap1;
//        double sum2 = line1.length() + line2.length() - overlap2;
//        //Calculate sum of both of line's lengths.
//        double finalLength = line1.length() + line2.length();
//        /*
//        If the sum of both line's lengths don't equal their sum without their
//        overlap: lines overlap -> return true.
//        */
//        return !(Math.abs(sum1 - finalLength) < EPSILON || Math.abs(sum2 - finalLength) < EPSILON);
//    }
//
//    /**
//     * Calculates point of intersection between 2 lines.
//     *
//     * @param line1 first line
//     * @param line2 second line
//     * @return geometry.Point of intersection or null.
//     */
//    public Point regularIntersection(Line line1, Line line2) {
//        double x, y;
//        double grad1 = line1.getEquation().getGradient();
//        double yInt1 = line1.getEquation().getyIntercept();
//        double grad2 = line2.getEquation().getGradient();
//        double yInt2 = line2.getEquation().getyIntercept();
//        /*
//        Calculate different between y intercepts & divide by difference
//        between gradients.
//        */
//        x = (yInt2 - yInt1) / (grad1 - grad2);
//        //substitute x into equation of one of the lines.
//        y = grad1 * x + yInt1;
//        //Define point with intersection point.
//        Point p = new Point(x, y);
//        //Return null if intersection point isn't within domain of both lines.
//        if (!inDomain(p, line1, line2)) {
//            p = null;
//        }
//        //Return point of intersection.
//        return p;
//    }
//
//    /**
//     * Method called if 2 lines are parallel. If the lines overlap at more than
//     * one point, or they never intersect as infinite lines, return null.
//     * Otherwise, return their point of intersection.
//     *
//     * @param line1 first line
//     * @param line2 second line
//     * @return geometry.Point of intersection or null.
//     */
//    public Point parallel(Line line1, Line line2) {
//        double grad1 = line1.getEquation().getGradient();
//        double yInt1 = line1.getEquation().getyIntercept();
//        double grad2 = line2.getEquation().getGradient();
//        double yInt2 = line2.getEquation().getyIntercept();
//        //Return null if lines overlap.
//        if (isOverlap(line1, line2)) {
//            return null;
//            //Return null if lines have different y intercepts.
//        } else if (Math.abs(yInt1 - yInt2) > EPSILON) {
//            return null;
//            //If lines touch at their start or end points, return that point.
//        } else {
//            return onEdge(line1, line2);
//        }
//    }
//
//    /**
//     * Method checks if 2 lines intersect at their start or end points only. If
//     * so, return that point. Otherwise return null.
//     *
//     * @param line1 first line
//     * @param line2 second line
//     * @return geometry.Point of intersection or null.
//     */
//    public Point onEdge(Line line1, Line line2) {
//        boolean start1Start2 = line1.start.equals(line2.start);
//        boolean end2Start1 = line2.end.equals(line1.start);
//        boolean end1End2 = line1.end.equals(line2.end);
//        boolean end1Start2 = line2.start.equals(line1.end);
//        //If 2 lines have the same start point or line 2 ends at line 1's start.
//        if (start1Start2 || end2Start1) {
//            return line1.start;
//            /* If 2 lines have the same end point or line 2 starts at line 1's
//            end. */
//        } else if (end1End2 || end1Start2) {
//            return line1.end;
//        } else {
//            //If line's don't touch, return null.
//            return null;
//        }
//    }
//
//    /**
//     * Method receives 2 theoretically infinite lines and their point of
//     * intersection. Checks if their point of intersection is within the domain
//     * of both finite lines.
//     *
//     * @param intersect (geometry.Point of intersection)
//     * @param line1     first line
//     * @param line2     second line
//     * @return boolean. True if they intersect. False otherwise.
//     */
//    public boolean inDomain(Point intersect, Line line1, Line line2) {
//        double xInt = intersect.getX();
//        boolean isInDom1 = minDigit(line1) <= xInt + EPSILON && maxDigit(line1) >= xInt - EPSILON;
//        boolean isInDom2 = minDigit(line2) <= xInt + EPSILON && maxDigit(line2) >= xInt - EPSILON;
//        //Return true if intersection point is withing the domain of both lines.
//        return (isInDom1 && isInDom2);
//    }
//
//    /**
//     * Method calculates the smallest x value of a given line.
//     *
//     * @param line given line
//     * @return smallest x value of a given line
//     */
//    public double minDigit(Line line) {
//        if (line.end.getX() > line.start.getX()) {
//            //If start point is smaller, return it.
//            return line.start.getX();
//        } else {
//            //Return line's end point.
//            return line.end.getX();
//        }
//    }
//
//    /**
//     * Method returns lowest point on line.
//     *
//     * @param line geometry.Line.
//     * @return Double - lowest y value.
//     */
//    public double smallestY(Line line) {
//        //If end point is smaller, return it.
//        if (line.start.getY() > line.end.getY()) {
//            return line.end.getY();
//        } else {
//            //Return line's start point.
//            return line.start.getY();
//        }
//    }
//
//    /**
//     * Method returns highest point on line.
//     *
//     * @param line geometry.Line.
//     * @return Double - highest y value.
//     */
//    public double largestY(Line line) {
//        if (line.start.getY() > line.end.getY()) {
//            //If start point if greater, return it.
//            return line.start.getY();
//        } else {
//            //Return line's end point.
//            return line.end.getY();
//        }
//    }
//
//    /**
//     * Method calculates the largest x value of a given line.
//     *
//     * @param line given line
//     * @return largest x value of a given line
//     */
//    public double maxDigit(Line line) {
//        if (line.end.getX() > line.start.getX()) {
//            //If end point if greater, return it.
//            return line.end.getX();
//        } else {
//            //Return line's start point.
//            return line.start.getX();
//        }
//    }
//
//    /**
//     * Method called if at least one of the lines is vertical and finds they're
//     * point of intersection and returns null if there is none.
//     *
//     * @param line1 first line
//     * @param line2 second line
//     * @return geometry.Point of intersection ro null.
//     */
//    public Point vertical(Line line1, Line line2) {
//        double grad1 = line1.getEquation().getGradient();
//        double grad2 = line2.getEquation().getGradient();
//        boolean isGrad1Infinity = (grad1 == Double.POSITIVE_INFINITY);
//        boolean isGrad2Infinity = (grad2 == Double.POSITIVE_INFINITY);
//        Point perpendicular = perpendicular(line1, line2);
//        //If both lines are vertical.
//        if (isGrad1Infinity && isGrad2Infinity) {
//            //Return intersection point if edges touch and null otherwise.
//            return onEdge(line1, line2);
//            //If lines are perpendicular.
//        } else if (perpendicular != null) {
//            /*
//            Return point of intersection if either edge lies on the other
//            line and null otherwise.
//            */
//            return perpendicular;
//        } else {
//            //Return point of intersection.
//            return liesOnLine(line1, line2);
//        }
//    }
//
//    /**
//     * Method determines theoretical point of intersection on infinite line.
//     * Returns point of intersection if there is one and null otherwise.
//     *
//     * @param line1 first line
//     * @param line2 second line
//     * @return geometry.Point (point of intersection or null)
//     */
//    public Point liesOnLine(Line line1, Line line2) {
//        double grad1 = line1.getEquation().getGradient();
//        double grad2 = line2.getEquation().getGradient();
//        double yint1 = line1.getEquation().getyIntercept();
//        double yint2 = line2.getEquation().getyIntercept();
//        double x, y;
//        boolean isInDom1 = minDigit(line1) <= line2.start.getX() && maxDigit(line1) >= line2.start.getX();
//        boolean isInDom2 = minDigit(line2) <= line1.start.getX() && maxDigit(line2) >= line1.start.getX();
//        boolean isInRan2 = smallestY(line1) <= line2.start.getY() && largestY(line1) >= line2.end.getY();
//        boolean isInRan1 = smallestY(line2) <= line1.start.getY() && largestY(line2) >= line1.end.getY();
//        //Return point of intersection.
//        if (isInDom1 && isInRan2 && grad2 == Double.POSITIVE_INFINITY) {
//            x = line2.start.getX();
//            y = grad1 * x + yint1;
//            return new Point(x, y);
//        } else if (isInDom2 && isInRan1 && grad1 == Double.POSITIVE_INFINITY) {
//            x = line1.start.getX();
//            y = grad2 * x + yint2;
//            return new Point(x, y);
//        }
//        return null;
//    }
//
//    /**
//     * Method called if both lines are perpendicular. If they are both within
//     * the domain of the other line, return their point of intersection.
//     * Return null otherwise.
//     *
//     * @param line1 first line
//     * @param line2 second line
//     * @return geometry.Point of intersection or null.
//     */
//    public Point perpendicular(Line line1, Line line2) {
//        double grad1 = line1.getEquation().getGradient();
//        double grad2 = line2.getEquation().getGradient();
//        boolean isGrad1Infinity = (grad1 == Double.POSITIVE_INFINITY);
//        boolean isGrad2Infinity = (grad2 == Double.POSITIVE_INFINITY);
//        double test1 = line1.start.getX();
//        double test2 = line2.start.getX();
//        double x, y;
//        double startY1 = line1.start.getY();
//        double startY2 = line2.start.getY();
//        //Test if lines intersect within their respective domains.
//        boolean isInDom1 = minDigit(line1) <= test2 + EPSILON && maxDigit(line1) >= test2 - EPSILON;
//        boolean isInDom2 = minDigit(line2) <= test1 + EPSILON && maxDigit(line2) >= test1 - EPSILON;
//        //Test if lines intersect within their respective ranges.
//        boolean isInRan2 = smallestY(line2) <= startY1 + EPSILON && startY1 <= largestY(line2) - EPSILON;
//        boolean isInRan1 = smallestY(line1) <= startY2 + EPSILON && startY2 <= largestY(line1) - EPSILON;
//        //If starting point of line 2 is within the domain of line 1.
//        if (isInDom1 && isInRan2 && isGrad2Infinity && grad1 < EPSILON) {
//            x = test2;
//            //Substitute x value into equation.
//            y = line1.start.getY();
//            //If starting point of line 1 is within the domain of line 2.
//        } else if (isInDom2 && isInRan1 && isGrad1Infinity && grad2 < EPSILON) {
//            x = test1;
//            //Substitute x value into equation.
//            y = line2.start.getY();
//        } else {
//            //If x values of intersection aren't within either line's domain.
//            return null;
//        }
//        //Return point of intersection.
//        return new Point(x, y);
//    }

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
        if (Math.abs(point.getY() - (this.getEquation().getGradient() * point.getX() + this.getEquation().getyIntercept())) < EPSILON) {
            return true;
        }
        return false;
    }
}
