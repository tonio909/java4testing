package lesson1.helloworld;

public class MyFirstProgram {

    public static void main(String[] args) {

        Rectangle r = new Rectangle(7,9);

        System.out.println("Square of triangle is " + r.a + " и " + r.b + " = " + square(r));
    }


    public static double square(Rectangle r) {

        return r.a * r.b;
    }

}