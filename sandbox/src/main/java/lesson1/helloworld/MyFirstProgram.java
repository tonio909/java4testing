package lesson1.helloworld;

public class MyFirstProgram {

    public static void main(String[] args) {

        Rectangle rect = new Rectangle(7,9);

        System.out.println("Площадь прямоугольника cо сторонами " + rect.a + " и " + rect.b + " = " + square(rect));
    }


    public static double square(Rectangle rect) {

        return rect.a * rect.b;
    }

}