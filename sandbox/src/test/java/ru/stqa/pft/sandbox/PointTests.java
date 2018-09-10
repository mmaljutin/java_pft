package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
  @Test
  public void testArea() {
    Point p1 = new Point(2, 4);
    Point p2 = new Point(1, 3);
    double dist = Point.distance(p1, p2);
    Assert.assertEquals(dist, 1.4142135623730951);
  }
}
