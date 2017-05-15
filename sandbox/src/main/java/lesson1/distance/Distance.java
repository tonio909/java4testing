package lesson1.distance;

public class Distance {
    double x;
    double y;

    public Distance(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static double distance(Distance a, Distance b) {
        double coordinatesX = a.x - b.x;
        double coordinatesY = a.y - b.y;

        return Math.sqrt( Math.pow(coordinatesX, 2) + Math.pow(coordinatesY, 2) );
    }

}