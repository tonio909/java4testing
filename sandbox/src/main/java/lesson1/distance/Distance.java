package lesson1.distance;

public class Distance {
    double x;
    double y;

    public Distance(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static double distance(Distance a, Distance b) {

        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));

    }

}