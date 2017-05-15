package lesson1.distance;

public class Point {

    public static void main(String[] args) {

        Distance p1 = new Distance(234234, 272);
        Distance p2 = new Distance(5, 345);

        System.out.println("Расстояние между точками A (" + p1.x + ", " + p1.y + ") и B (" + p2.x + ", " + p2.y + ") составляет " + Distance.distance(p1, p2));
    }

}