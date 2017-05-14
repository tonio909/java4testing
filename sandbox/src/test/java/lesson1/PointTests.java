package lesson1;

import lesson1.point.Coordinates;
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

}
