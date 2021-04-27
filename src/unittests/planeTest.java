package unittests;
//import org.junit.jupiter.api.Test;
import Primitives.Ray;
import Primitives.Vector;
import Primitives.point3D;
import geometries.plane;
import org.junit.Test;


import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

import static org.junit.Assert.*;

public class planeTest {


    @Test
    public void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        plane pl = new plane(new point3D(-1,2,1), new point3D(0,-3,2), new point3D(1,1,-4));
        Vector v= new Vector(26,7,9);

        if (v.scale(-1).equals(pl.getNormal(null))||v.equals(pl.getNormal(null)))
            assertEquals(new Vector(26,7,9).normalize(),
                    pl.getNormal(null),
                    "Bad normal to trinagle");

        // ============ Equivalence Partitions Tests ==============
        // TC02: There is a simple single test here for to pointD3 ecouls
        try {
            assertThrows( IllegalArgumentException.class,
                    () -> new plane(new point3D(-1,2,1),
                            new point3D(-1,2,1), new point3D(1,1,-4)),
                    "failure text TC02 problem dopnt indepit vector 0");
        }
        catch (Exception a) {


        }
        // TC03: There is a simple single test here for to pointD3 ecouls
        try {
            assertThrows( IllegalArgumentException.class,
                    () -> new plane(new point3D(0,0,1),
                            new point3D(0,0,2), new point3D(0,0,5)),
                    "failure text TC03 problem dopnt indepit vector 0");      }
        catch (Exception e) {
        }

    }

    @Test
    public void findIntsersections() {

        plane plane = new plane(new point3D(1, 1, 0), new Vector(0, 0, 1));

        // ============ Equivalence Partitions Tests ==============

        assertEquals("ray intersects the plane", List.of(new point3D(1, 0, 0)),
                plane.findIntsersections(new Ray(new point3D(0, 0, -1), new Vector(1, 0, 1))));
        assertNull("ray doesn't intersect the plane", plane.findIntsersections(new Ray(new point3D(0, 0, -1), new Vector(1, 0, -1))));

        assertNull("the ray is parallel and not included in the plane", plane.findIntsersections(new Ray(new point3D(1, 1, 1), new Vector(0, 1, 0))));
        assertEquals("the ray is orthogonal and before the plane", List.of(new point3D(1, 1, 0)),
                plane.findIntsersections(new Ray(new point3D(1, 1, -1), new Vector(0, 0, 1))));

        assertNull("the ray is orthogonal and in the plane",
                plane.findIntsersections(new Ray(new point3D(1, 1, 0), new Vector(0, 0, 1))));

        assertEquals("the ray is orthogonal and after the plane", List.of(new point3D(1, 1, 0)),
                plane.findIntsersections(new Ray(new point3D(1, 1, 1), new Vector(0, 0, -1))));
        assertNull("the ray is neither orthogonal nor parallel to and begins at the plane",
                plane.findIntsersections(new Ray(new point3D(2, 2, 0), new Vector(1, 1, 1))));

        assertNull("the ray is neither orthogonal nor parallel to the plane and begins in " +
                "the same point which appears as reference point in the plane", plane.findIntsersections(new Ray(new point3D(1, 1, 0), new Vector(1, 1, 1))));
    }


}
