/**
 * @author Yigit Sezer
 * @since March 3, 2019
 * Dependencies: StdDraw.java
 * Description: Circle class used to store x, y, r values for circle objects that can be drawn on screen
 */
public class Circle {
    private double x; //x coordinate for circle
    private double y; //y coordinate for circle
    private double r; //radius value for circle

    /**
     * Constructor for circle class with parameters double x, y, r.
     * @param x x coordinate for the circle
     * @param y y coordinate for the circle
     * @param r radius value for the circle
     */
    public Circle(double x, double y, double r) {
        /* Assign the input x, y, r to the property variable of this circle's x, y, r. */
        this.x = x;
        this.y = y;
        this.r = r;
    }

    /**
     * Method to draw this circle
     */
    public void draw() {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.circle(x, y, r);
    }

    /**
     * Method to calculate the distance between this circle and the input circle
     * @param inputCircle
     * @return distance between this circle and the input circle
     */
    public double distance(Circle inputCircle) {
        /* Representation of distance between two points in a two dimensional space */
        return Math.pow((x - inputCircle.getX())*(x - inputCircle.getX()) + (y - inputCircle.getY()) * (y - inputCircle.getY()), 0.5);
    }

    /**
     * @return x coordinate of this circle
     */
    public double getX() {
        return x;
    }

    /**
     * @return y coordinate of this circle
     */
    public double getY() {
        return y;
    }
}
