/**
 * @author Yigit Sezer
 * @since March 3, 2019
 * Dependencies: StdDraw.java
 * Description: Draws 'n' random circles on the screen and shows the furthest two circles. 
 */

public class RemoteCircles {
    public static void main(String[] args) {
        //Data field
        int n = 100; //Number of circles
        double x = 0; //x coordinate for circles
        double y = 0; //y coordinate for circles
        double r = 0; //radius for circles
        double xScale = 1; //x scaling value of the window
        double yScale = 1; //y scaling value of the window
        final double MINIMUM_RADIUS = xScale / 50; //minimum radius for circles
        double mostDistance = 0; //distance between furthest circles
        double distance = 0; //distance between circles
        int distantCircle1 = 0; //index of one of the circles those have the most distance in between
        int distantCircle2 = 0; //index of the other circle that have the most distance in between
        Circle[] circles = new Circle[n]; //array of circles

        /* Enable double buffering to display all the shapes
        that will be displayed on the screen at the same time after
        all the computations are done in the background.
         */
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(500, 500); //Set size of the window that will display the shapes
        StdDraw.setXscale(0, xScale); //Set the x scale for coordinates of the shapes
        StdDraw.setYscale(0, yScale); //Set the y scale for coordinates of the shapes
        StdDraw.setPenRadius(0.002); //Thickness of the pen that will be used to draw shapes on the screen

        /* Loop that will generate somewhat random x, y, r values for circle objects,
         will ensure that they are inside the canvas, create the circle object and put it
         inside the circles array. */
        for (int i = 0; i < n; i++) {
            x = (Math.random() * xScale); //generate an x coordinate for the circle based on the x scale of the canvas.
            y = (Math.random() * yScale); //generate an y coordinate for the circle based on the x scale of the canvas.
            r = (Math.random() * xScale/30 + MINIMUM_RADIUS); //generate a radius value for the circle based on the x scale of the canvas and minimum radius for a circle.

            /* Condition to check if the assigned x, y, r values ensure that the circles are inside the canvas
            otherwise run this iteration of the loop again. */
            if (x + r > xScale || x - r < 0 || y + r > yScale || y - r < 0){
                i--;
                continue;
            }
            circles[i] = new Circle(x, y, r); //create a new circle object and add it into the circles array.
            circles[i].draw(); //draw the recently created circle object.
        }

        /* Loop to check the distance between each circle in the array and find the circles that have
        the most distance in between. */
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distance = circles[i].distance(circles[j]); //check the distance between circles[i] and circles[j]

                /* Condition to compare distance between currently known most distance */
                if (distance > mostDistance) {
                    mostDistance = distance; //mostDistance is the latest distance calculated if it is greater than last known most distance
                    distantCircle1 = i; //save the index of one of the circles those have the most distance in between
                    distantCircle2 = j; //save the index of the other circle that have the most distance in between
                }
            }
        }
        StdDraw.setPenRadius(0.007); //make the pen thicker just for visualization
        circles[distantCircle1].draw(); //draw one of the circles those are furthest away from each other
        circles[distantCircle2].draw(); //draw the other circle
        StdDraw.setPenColor(StdDraw.RED); //make the color of the pen red for visualization
        StdDraw.setPenRadius(0.004);
        /* Draw a line between the circles that have the most distance in between */
        StdDraw.line(circles[distantCircle1].getX(), circles[distantCircle1].getY(), circles[distantCircle2].getX(), circles[distantCircle2].getY());
        StdDraw.show(); //Show every shape that has been calculated in the background at the same time
    }
}
