package lesson1.helloworld;

import org.testng.Assert;
import org.testng.annotations.Test;
import static lesson1.helloworld.MyFirstProgram.square;

public class RectangleTests {

    @Test
    public void testArea() {

        Rectangle r = new Rectangle(5,5);
        Assert.assertEquals(square(r),25.0);
    }
}
