package geometry;

import geometry.Point;

public interface Equation {
    double getyIntercept();

    boolean isPointOnCurve(Point point);
}
