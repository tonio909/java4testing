package lesson1.learning_process;

public class MyFirstProgram {

    public static void main(String[] args) {

        Rectangle r = new Rectangle(3,4);


        System.out.println("Площадь прямоугольника cо сторонами " + r.a + " и " + r.b + " = " + square(r));
    }


    public static double square(Rectangle r) {

        return r.a * r.b;
    }

}