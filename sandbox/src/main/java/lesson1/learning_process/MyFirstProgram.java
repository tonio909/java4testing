package lesson1.learning_process;

public class MyFirstProgram {

    public static void main(String[] args) {

        hello("world");

        double a = 3254.03;
        double b = 555.67;

        System.out.println("Площадь прямоугольника cо сторонами " + a + " и " + b + " = " + square(a, b));
    }

    public static void hello(String w) {
        System.out.println("Hello," + w + "!");
    }

    public static double square(double a, double b) {

        return a * b;
    }

}