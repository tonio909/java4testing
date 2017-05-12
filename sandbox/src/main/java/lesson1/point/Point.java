package lesson1.point;

public class Point {

    public static void main(String[] args) {

        Coordinates co = new Coordinates(4, 2, 6, 3);

        System.out.println("Расстояние между точками A (" + co.x1 + ", " + co.y1 + ") и B (" +co.x2 + ", " + co.y2 + ") составляет " + distance(co));

    }

    public static double distance(Coordinates co) {

        return Math.sqrt( Math.pow(co.x2 - co.x1, 2) + Math.pow(co.y2 - co.y1, 2) );

    }

}