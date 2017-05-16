package lesson1.point;

import org.testng.Assert;
import org.testng.annotations.Test;

public class pointTests {

    @Test
    public void testDistancePositiveNumbers()

    {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(3, 4);
        Assert.assertEquals(p1.distance(p2), 2.8284271247461903);
    }


    @Test
    public void testDistanceNegativeNumbers()

    {
        Point p1 = new Point(-1, 2);
        Point p2 = new Point(3, -4);
        Assert.assertEquals(p1.distance(p2), 7.211102550927978);
    }

    @Test
    public void testDistanceZero()

    {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 0);
        Assert.assertEquals(p1.distance(p2), 0.0);
    }







}