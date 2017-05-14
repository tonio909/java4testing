package lesson1.point;

import org.testng.Assert;
import org.testng.annotations.Test;
import static lesson1.point.Point.distance;

public class PointTests {

    @Test
    public void testCoordinates()

    {
        Coordinates co = new Coordinates(4, 2, 6, 3);
        Assert.assertEquals(distance(co), 2.23606797749979);
    }

    @Test
    public void testCoordinates1()

    {
        Coordinates co = new Coordinates(-1, 2, 3, -4);
        Assert.assertEquals(distance(co), 7.211102550927978);
    }

    @Test
    public void testCoordinates2()

    {
        Coordinates co = new Coordinates(0, 0, 0, 0);
        Assert.assertEquals(distance(co), 0.0);
    }

}
