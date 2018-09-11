package ru.stqa.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    hello("world");
    hello("user");
    hello("Max");

    Square s = new Square(5);
    System.out.println("Plodhadj kvadrata so storonoj " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(4, 6);
    System.out.println("Ploshadj treugolnika so storonami " + r.a + " i " + r.b + " = " + r.area());

    Point p1 = new Point(2, 4);
    Point p2 = new Point(1, 3);
    double dist = p1.distance(p2);
    System.out.println("Koordinatq tochek na dvumernoj ploskosti:" + " x1 = " + p1.x + " y1 = " + p1.y + "; x2 = " + p2.x + " y2 = " + p2.y + " | " + "Rasstojanie mezhdu to4kami = " + dist);
  }

  public static void hello(String somebody) {
    System.out.println("Hello, " + somebody + "!");
  }
}