public class MyFirstProgram {

  public static void main(String[] args) {
    System.out.println("Hello, world!");

    Point p1 = new Point(2.5, 4.5);
    Point p2 = new Point(10, 10);

    System.out.println("Pасстояние между двумя точками: " + "A(" + p1.x + "; " + p1.y + ")" + " и " + "B(" + p2.x + "; " + p2.y + ")" + " = " + distance(p1, p2));

  }

  public static double distance(Point p1, Point p2) {
    return Math.sqrt(Math.pow((p2.x - p1.x), 2) + Math.pow((p2.y - p1.y), 2));
  }

}