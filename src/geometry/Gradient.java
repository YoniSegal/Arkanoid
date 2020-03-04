package geometry;

public class Gradient {
    private double gradient;
    private static final double EPSILON = 0.0001;

    public Gradient(Line line) {
        //If line is vertical, assign infinite gradient.
        if (Math.abs(line.getStart().getX() - line.getEnd().getX()) < EPSILON) {
            this.gradient = Double.POSITIVE_INFINITY;
        }
        //Calculate change in y axis.
        double dy = line.getEnd().getY() - line.getStart().getY();
        //Calculate change in x axis.
        double dx = line.getEnd().getX() - line.getStart().getX();
        //Return gradient of line.
        this.gradient = (dy / dx);
    }

    public double getGradientValue() {
        return gradient;
    }
}
