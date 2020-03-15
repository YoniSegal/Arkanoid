package geometry;

public class LinearEquation implements Equation {
    private double gradient;
    private double yIntercept;
    private static final double EPSILON = 0.0001;

    public double getGradient() {
        return this.gradient;
    }


    public LinearEquation(Line line) {
        this.gradient = calculateGradient(line);
        this.yIntercept = calculateYIntercept(line);
    }

    private double calculateYIntercept(Line line) {
        return line.getStart().getY() - this.gradient * line.getStart().getX();

    }

    @Override
    public double getyIntercept() {
        return this.yIntercept;
    }


    private double calculateGradient(Line line) {
        double inf = Double.POSITIVE_INFINITY;
        //If line is vertical, assign infinite gradient.
        if (Math.abs(line.getStart().getX() - line.getEnd().getX()) < EPSILON) {
            return inf;
        }
        //Calculate change in y axis.
        double dy = line.getEnd().getY() - line.getStart().getY();
        //Calculate change in x axis.
        double dx = line.getEnd().getX() - line.getStart().getX();
        //Return gradient of line.
        return dy / dx;
    }

    @Override
    public String toString() {
        return "y = " + this.gradient + " * x + " + this.yIntercept;
    }
}
