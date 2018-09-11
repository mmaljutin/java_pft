package ru.stqa.pft.sandbox;

public class Point {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(double x, double y) {
        double px = this.x - x;
        double py = this.y - y;
        return Math.sqrt(px * px + py * py);
    }

    public double distance(Point p) {
        return distance(p.x, p.y);
    }
}
