package unittests;

import Primitives.Ray;
import Primitives.Vector;
import Primitives.point3D;
import geometries.*;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SphereTest {

    @Test
    public void getNormal() {
        Sphere sp = new Sphere(1.0, new point3D(0, 0, 1));
        assertEquals(new Vector(0, 0, 1), sp.getNormal(new point3D(0, 0, 2)));
    }

    /**
     * Test method for {@link geometries.Sphere#findIntsersections(Primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        // ============ Equivalence Partitions Tests ==============

        //TC01 no intersections ray is outside of the sphere
        Ray ray = new Ray(new point3D(0, 0, 0), new Vector(0, 0, -1));
        Sphere sphere =
                new Sphere(1, new point3D(0, 2, -4));
        List<point3D> intersectionsList = sphere.findIntsersections(ray);
        assertNull(intersectionsList, "must be empty");

        //TC02 ray start before and cross the sphere twice: 2 intersections
        //(si ca ne marche pas penser a changer l'ordre des points)
        ray = new Ray(new point3D(-3, 1, 0), new Vector(6, 0, 0));
        sphere = new Sphere(2, new point3D(0, 0, 0));
        intersectionsList = sphere.findIntsersections(ray);
        assertNotNull(intersectionsList, "must not be empty");
        assertEquals(2, intersectionsList.size(), "must be equal to 2");
        if (intersectionsList.get(0).getX() > intersectionsList.get(1).getX()) {
            intersectionsList = List.of(intersectionsList.get(1), intersectionsList.get(0));
        }
        assertEquals(new point3D(-1.732050807569, 1, 0), intersectionsList.get(0), "must be equal");
        assertEquals(new point3D(1.732050807569, 1, 0), intersectionsList.get(1), "must be equal");


        //TC03 ray start inside sphere:1 intersection
        ray = new Ray(new point3D(0, 0, 0), new Vector(0, 0, -1));
        sphere = new Sphere(5, new point3D(0, -1, -1));
        intersectionsList = sphere.findIntsersections(ray);
        assertNotNull(intersectionsList, "must be not empty");
        assertEquals(1, intersectionsList.size(), "must be equal to one");
        assertEquals(new point3D(0, 0, -5.898979485566), intersectionsList.get(0), "must be equal");

        //TC04 ray starts outside sphere after it, due to direction- no intersection
        ray = new Ray(new point3D(0, 0, 0), new Vector(0, 0, -1));
        sphere = new Sphere(2, new point3D(0, 1, 4));
        intersectionsList = sphere.findIntsersections(ray);
        assertNull(intersectionsList, "must be empty");

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside
        ray = new Ray(new point3D(-2, 0, 0), new Vector(2, 0, 2));
        sphere = new Sphere(2, new point3D(0, 0, 0));
        intersectionsList = sphere.findIntsersections(ray);
        assertNotNull(intersectionsList, "must be not empty");
        assertEquals(1, intersectionsList.size(), "must be equal to one");
        assertEquals(new point3D(0, 0, 2), intersectionsList.get(0), "must be equal");

        // TC12; Ray starts at sphere and goes outside
        ray = new Ray(new point3D(2, 0, 0), new Vector(2.29, 4.67, 0));
        sphere = new Sphere(2, new point3D(0, 0, 0));
        intersectionsList = sphere.findIntsersections(ray);
        assertNull(intersectionsList, "must be empty");

// **** Group: Ray's line goes through the center
        //TC13 ray start at the center of the sphere
        ray = new Ray(new point3D(0, 0, 0), new Vector(0, 0, -1));
        sphere = new Sphere(5, new point3D(0, 0, 0));
        intersectionsList = sphere.findIntsersections(ray);
        assertNotNull(intersectionsList, "must be not empty");
        assertEquals(1, intersectionsList.size(), "must be equal to one");
        assertEquals(new point3D(0, 0, -5), intersectionsList.get(0), "must be equal");

        //TC14 ray starts on sphere surface, to the outside:
        //no intersection because we don't take in account the ray head
        ray = new Ray(new point3D(0, 0, 3), new Vector(0, 0, 5));
        sphere = new Sphere(2, new point3D(0, 0, 1));
        intersectionsList = sphere.findIntsersections(ray);
        assertNull(intersectionsList, "must be empty");
        /*assertNotNull("must be not empty", intersectionsList);
        assertEquals("must be equal to one", 1, intersectionsList.size());
        assertEquals("must be equal", new Point3D(0, 0, 3), intersectionsList.get(0));*/

        //TC15 ray starts on sphere surface, to the inside:
        //1 intersection because we don't take in account the ray head
        ray = new Ray(new point3D(0, 0, 4), new Vector(0, 0, 1));
        sphere = new Sphere(2, new point3D(0, 0, 6));
        intersectionsList = sphere.findIntsersections(ray);
        assertNotNull(intersectionsList, "must be not empty");
        assertEquals(1, intersectionsList.size(), "must be equal to one");
        assertEquals(new point3D(0, 0, 8), intersectionsList.get(0), "must be equal");


        //TC16 ray starts outside of the sphere to the outside but on a line aligned with the center: no intersections
        ray = new Ray(new point3D(0, 0, 0), new Vector(0, 0, -1));
        sphere = new Sphere(5, new point3D(0, 0, 6));
        intersectionsList = sphere.findIntsersections(ray);
        assertNull(intersectionsList, "must be empty");

        //TC17 ray starts outside of the sphere to the inside cross twice the sphere and pass by center: 2 intersections
        ray = new Ray(new point3D(0, 0, 0), new Vector(0, 0, -1));
        sphere = new Sphere(5, new point3D(0, 0, -6));
        intersectionsList = sphere.findIntsersections(ray);
        assertNotNull(intersectionsList, "must be not empty");
        assertEquals(2, intersectionsList.size(), "must be equal to one");
        assertEquals(new point3D(0, 0, -1), intersectionsList.get(0), "must be equal");
        assertEquals(new point3D(0, 0, -11), intersectionsList.get(1), "must be equal");

        //TC18 ray starts on sphere surface, to the outside on the same line that the center: 1 intersection
        ray = new Ray(new point3D(0, 0, 1), new Vector(0, 0, 1));
        sphere = new Sphere(5, new point3D(0, 0, 0));
        intersectionsList = sphere.findIntsersections(ray);
        assertNotNull(intersectionsList, "must be not empty");
        assertEquals(1, intersectionsList.size(), "must be equal to one");
        assertEquals(new point3D(0, 0, 5), intersectionsList.get(0), "must be equal");

        // **** Group: Special cases
        //TC19 ray is on a line that vertical to radius- ray starts on the radius line
        ray = new Ray(new point3D(0, 0, 0), new Vector(0, 0, -1));
        sphere = new Sphere(1, new point3D(0, 2, 0));
        intersectionsList = sphere.findIntsersections(ray);
        assertNull(intersectionsList, "must be empty");

        // **** Group: Ray's line is tangent to the sphere
        //TC16 ray is on the tangent line- ray starts before intersection: 1 intersection
        ray = new Ray(new point3D(0, 0, 0), new Vector(0, 0, -1));
        sphere = new Sphere(1, new point3D(0, 1, -1));
        intersectionsList = sphere.findIntsersections(ray);
        assertNull(intersectionsList, "must be empty");


//TC17 ray is on the tangent line- ray starts on intersection: 1 intersection
        ray = new Ray(new point3D(0, 0, 0), new Vector(0, 0, -1));
        sphere = new Sphere(1, new point3D(0, 1, 0));
        intersectionsList = sphere.findIntsersections(ray);
        assertNull(intersectionsList, "must be empty");

        //TC18 ray is on the tangent line- ray starts after intersection: no intersections
        ray = new Ray(new point3D(0, 0, 0), new Vector(0, 0, -1));
        sphere = new Sphere(1, new point3D(0, 1, 1));
        intersectionsList = sphere.findIntsersections(ray);
        assertNull(intersectionsList, "must be empty");
    }

}


