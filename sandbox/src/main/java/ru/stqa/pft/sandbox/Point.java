package ru.stqa.pft.sandbox;

public class Point {
  public double x;
  public double y;
  public Point p1;
  public Point p2;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public static double distance(Point p1, Point p2) {
    double px = p2.x - p1.x;
    double py = p2.y - p1.y;
    return Math.sqrt(px * px + py * py);
  }
}
