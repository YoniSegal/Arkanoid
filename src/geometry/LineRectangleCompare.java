package geometry;

import java.util.ArrayList;
import java.util.List;

public class LineRectangleCompare extends LineCompare {

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the
     * line.
     *
     * @param rect geometry.Rectangle.
     * @return geometry.Point of intersection.
     */
    public Point closestIntersectionToStartOfLine(Line line, Rectangle rect) {
        //get intersection points.
        List<Point> intersections = intersectionPoints(line, rect);
        //If there are no intersections.
        if (intersections.size() == 0) {
            return null;
            //if there is one intersection.
        } else if (intersections.size() == 1) {
            return intersections.get(0);
            //If there is more than 1 intersection.
        } else {
            if (intersections.get(0).distance(line.getStart()) < intersections.get(1).distance(line.getStart())) {
                return intersections.get(0);
            } else {
                return intersections.get(1);
            }
        }
    }

    /**
     * Method returns a (possibly empty) List of intersection points with the specified line.
     *
     * @param line geometry.Line.
     * @return List of points.
     */
    public List<Point> intersectionPoints(Line line, Rectangle rectangle) {
        List<Point> points = new ArrayList<>();

        //Check if array hits top of rectangle.
        if (isIntersecting(line, rectangle.getTop())) {
            points.add(intersectionWith(line, rectangle.getTop()));
        }
        //Check if array hits bottom of rectangle.
        if (isIntersecting(line, rectangle.getBottom())) {
            points.add(intersectionWith(line, rectangle.getBottom()));
        }
        //Check if array hits right of rectangle.
        if (isIntersecting(line, rectangle.getRight())) {
            points.add(intersectionWith(line, rectangle.getRight()));
        }
        //Check if array hits left of rectangle.
        if (isIntersecting(line, rectangle.getLeft())) {
            points.add(intersectionWith(line, rectangle.getLeft()));
        }
        return points;
    }
}
