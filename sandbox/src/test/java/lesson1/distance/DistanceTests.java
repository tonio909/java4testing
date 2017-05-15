package lesson1.distance;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DistanceTests {

    @Test
    public void testDistancePositiveNumbers()

    {
        Distance p1 = new Distance(1, 2);
        Distance p2 = new Distance(3, 4);
        Assert.assertEquals(Distance.distance(p1, p2), 2.8284271247461903);
    }

    @Test
    public void testDistanceNegativeNumbers()

    {
        Distance p1 = new Distance(-1, 2);
        Distance p2 = new Distance(3, -4);
        Assert.assertEquals(Distance.distance(p1, p2), 7.211102550927978);
    }

    @Test
    public void testDistanceZero()

    {
        Distance p1 = new Distance(0, 0);
        Distance p2 = new Distance(0, 0);
        Assert.assertEquals(Distance.distance(p1, p2), 0.0);
    }

}