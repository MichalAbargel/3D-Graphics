package unittests;

import Primitives.point3D;
import geometries.Triangle;
import org.junit.Test;

import static org.junit.Assert.*;

public class TriangleTest {

    @Test

    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        //TC01:with two same points and so it's not a triangle it's a vector
        try {
            new Triangle(new point3D(0, 0, 0), new point3D(0, 0, 0), new point3D(1, 2, 3));
            fail("Constructed a vector not a triangle ");
        } catch (IllegalArgumentException e) {

        } catch (IllegalAccessException e) {
            e.printStackTrace();

        }
    }
}