package Distance;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by IEUser on 7/23/2016.
 */
public class DistanceTests {

    public Point p1;
    public Point p2;

    public DistanceTests() {
        p1 = new Point(5, 5);
        p2 = new Point(5, 10);
    }



    @Test
    public void testArea () {
        assert Point.distance(p1, p2) == 5;

    }

    @Test
    public void testArea2() {
        Assert.assertEquals(Point.distance(p1, p2), 5.0);
    }
}
