package unittests;

import geometries.Plane;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    Point3D p1  = new Point3D(-1,2,1);
    Point3D p2  = new Point3D(0,-3,2);
    Point3D p3  = new Point3D(1,1,-4);

    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Plane pl = new Plane(new Point3D(-1,2,1), new Point3D(0,-3,2), new Point3D(1,1,-4));
        Vector v= new Vector(26,7,9);

        if (v.scale(-1).equals(pl.getNormal(null))||v.equals(pl.getNormal(null)))
            assertEquals(new Vector(26,7,9).normalize(),
                    pl.getNormal(null),
                    "Bad normal to trinagle");

        // ============ Equivalence Partitions Tests ==============
        // TC02: There is a simple single test here for to pointD3 ecouls
        try {
            assertThrows( IllegalArgumentException.class,
                    () -> new Plane(new Point3D(-1,2,1),
                            new Point3D(-1,2,1), new Point3D(1,1,-4)),
                    "failure text TC02 problem dopnt indepit vector 0");
        }
        catch (Exception a) {


        }
        // TC03: There is a simple single test here for to pointD3 ecouls
        try {
            assertThrows( IllegalArgumentException.class,
                    () -> new Plane(new Point3D(0,0,1),
                            new Point3D(0,0,2), new Point3D(0,0,5)),
                    "failure text TC03 problem dopnt indepit vector 0");      }
        catch (Exception e) {
        }

    }
    @Test
    public void findIntsersections() {

        Plane plane = new Plane(new Vector(0,0,1), new Point3D(1,1,0));

        // ============ Equivalence Partitions Tests ==============

        assertEquals( List.of(new Point3D(1,0,0)),
                plane.findIntersections(new Ray(new Point3D(0,0,-1), new Vector(1,0,1))),"ray intersects the plane");
        assertNull( plane.findIntersections(new Ray(new Point3D(0,0,-1), new Vector(1,0,-1))),"ray does'nt intersect the plane");

        assertNull( plane.findIntersections(new Ray(new Point3D(1,1,1), new Vector(0,1,0)))
        ,"the ray is parallel and not included in the plane");
        assertEquals(List.of(new Point3D(1,1,0)),
                plane.findIntersections(new Ray(new Point3D(1,1,-1), new Vector(0,0,1))),"the ray is orthogonal and before the plane");

        assertNull(
                plane.findIntersections(new Ray(new Point3D(1,1,0), new Vector(0,0,1)))
        ,"the ray is orthogonal and in the plane");

        assertEquals(List.of(new Point3D(1,1,0)),
                plane.findIntersections(new Ray(new Point3D(1,1,1), new Vector(0,0,-1))),"the ray is orthogonal and after the plane");
        assertNull(
                plane.findIntersections(new Ray(new Point3D(2,2,0), new Vector(1,1,1)))
                ,"the ray is neither orthogonal nor parallel to and begins at the plane");

        assertNull( plane.findIntersections(new Ray(new Point3D(1,1,0), new Vector(1,1,1))),
                "the ray is neither orthogonal nor parallel to the plane and begins in " +
                        "the same point which appears as reference point in the plane");
    }
}


