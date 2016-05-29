package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;

/**
 * Created by Admin on 5/29/2016.
 */
public class PointTest {

  Point p1 = new Point(1, 6);
  Point p2 = new Point(4, 2);

  @Test
  public void testDistance1(){
    Assert.assertEquals(p1.distance(p2), 4.0);
  }

  @Test
  public void testDistance2(){
    Assert.assertEquals(p1.distance(p2), p2.distance(p1));
  }

  @Test
  public void testDistance3(){
    Assert.assertTrue(Math.abs(p1.distance(p2) - 5) < 0.01);
  }

}
