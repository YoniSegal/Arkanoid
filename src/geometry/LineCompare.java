package geometry;

public class LineCompare {
    private static final double EPSILON = 0.0001;

    public LineCompare() {
    }

    public boolean isIntersecting(Line line1, Line line2) {
        //Return true if 2 lines intersect.
        return this.intersectionWith(line1, line2) != null;
    }

    public Point intersectionWith(Line line1, Line line2) {
        // Parallel
        if (line1.getEquation().getGradient() == line2.getEquation().getGradient()) {
            return null;
        }
        // 1 vertical
        if (line1.getEquation().getGradient() == Double.POSITIVE_INFINITY) {
            Point p = verticalIntersection(line1, line2);
            if (inRange(p, line1, line2)) {
                return p;
            } else {
                return null;
            }
        } else if (line2.getEquation().getGradient() == Double.POSITIVE_INFINITY) {
            Point p = verticalIntersection(line2, line1);
            if (inRange(p, line1, line2)) {
                return p;
            } else {
                return null;
            }
        }

        double x = (line1.getEquation().getyIntercept() - line2.getEquation().getyIntercept()) / (line2.getEquation().getGradient() - line1.getEquation().getGradient());
        double y = line1.getEquation().getGradient() * x + line1.getEquation().getyIntercept();
        Point p = new Point(x, y);
        if (inRange(p, line1, line2)) {
            return p;
        } else {
            return null;
        }
    }

    private boolean inRange(Point p, Line line1, Line line2) {
        double x = p.getX();
        double y = p.getY();
        boolean betweenLine1x = x >= Math.min(line1.getStart().getX(), line1.getEnd().getX()) && x <= Math.max(line1.getStart().getX(), line1.getEnd().getX());
        boolean betweenLine2x = x >= Math.min(line2.getStart().getX(), line2.getEnd().getX()) && x <= Math.max(line2.getStart().getX(), line2.getEnd().getX());
        boolean betweenLine1y = y >= Math.min(line1.getStart().getY(), line1.getEnd().getY()) && y <= Math.max(line1.getStart().getY(), line1.getEnd().getY());
        boolean betweenLine2y = y >= Math.min(line2.getStart().getY(), line2.getEnd().getY()) && y <= Math.max(line2.getStart().getY(), line2.getEnd().getY());
        return betweenLine1x && betweenLine2x && betweenLine1y && betweenLine2y;
    }

    private Point verticalIntersection(Line vertical, Line regular) {
        double x = vertical.getStart().getX();
        double y = regular.getEquation().getGradient() * x + regular.getEquation().getyIntercept();
        return new Point(x, y);
    }


}
