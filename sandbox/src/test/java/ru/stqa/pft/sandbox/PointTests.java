package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
  @Test
  public void testArea() {
    Point p1 = new Point(2, 4);
    Point p2 = new Point(1, 3);
    double px = p2.x - p1.x;
    double py = p2.y - p1.y;
    Assert.assertEquals(Math.sqrt(px * px + py * py), 1.4142135623730951);
  }
}
