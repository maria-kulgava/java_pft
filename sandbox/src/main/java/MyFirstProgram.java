public class MyFirstProgram {

  public static void main(String[] args) {
    System.out.println("Hello, world!");

    /*  Вычисление расстояния между двумя точками №1 (с помощью функции) */
    Point p1 = new Point(2.5, 4.5);
    Point p2 = new Point(10, 10);

    System.out.println("\n" + "Вычисление расстояния между двумя точками №1 (с помощью статической функции)");
    System.out.println("Pасстояние между двумя точками: " + "A(" + p1.x1 + "; " + p1.y1 + ")" + " и " + "B(" + p2.x1 + "; " + p2.y1 + ")" + " = " + distance(p1, p2));

    /*  Вычисление расстояния между двумя точками №2  (с помощью метода) */
    Point p = new Point(2.5, 4.5, 10, 10);

    System.out.println("\n" + "Вычисление расстояния между двумя точками №2 (с помощью метода)");
    System.out.println("Pасстояние между двумя точками: " + "A(" + p.x1 + "; " + p.y1 + ")" + " и " + "B(" + p.x2 + "; " + p.y2 + ")" + " = " + p.distance());

  }

  public static double distance(Point p1, Point p2) {
    return Math.sqrt(Math.pow((p2.x1 - p1.x1), 2) + Math.pow((p2.y1 - p1.y1), 2));
  }

}