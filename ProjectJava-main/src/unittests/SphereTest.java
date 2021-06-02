package unittests;

import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    Sphere sphere = new Sphere(1d, new Point3D(1, 0, 0));

    @Test
    public void getNormalTest1() {
        Sphere sp = new Sphere(1.0, new Point3D(0, 0, 1));
        assertEquals(new Vector(0, 0, 1), sp.getNormal(new Point3D(0, 0, 2)));
    }


    /*@Test???
    void getNormal() {
        Sphere sp = new Sphere(1, new Point3D(0, 0, 1));
        var normal = sp.getNormal(new Point3D(1, 0, 1));
        assertEquals(new Vector(1, 0, 0), normal);
    }*/


    @Test
    public void getNormalTest2() {
        Sphere sp = new Sphere(1, new Point3D(0, 0, 1));
        assertNotEquals(new Vector(0, 0, 1), sp.getNormal(new Point3D(0, 1, 1)));
        System.out.println(sp.getNormal(new Point3D(0, 1, 1)));
    }

    /**
     * we test the intersection with ray and sphere
     */

    @Test

    public void findIntersections() {
        // ============ Equivalence Partitions Tests ==============

        //TC01 no intersections ray is outside of the sphere
        Ray ray = new Ray(new Point3D(0, 0, 0), new Vector(0, 0, -1));
        Sphere sphere =
                new Sphere(1, new Point3D(0, 2, -4));
        List<Point3D> intersectionsList = sphere.findIntersections(ray);
        assertNull(intersectionsList, "must be empty");

        //TC02 ray start before and cross the sphere twice: 2 intersections
        //(si ca ne marche pas penser a changer l'ordre des points)
        ray = new Ray(new Point3D(-3, 1, 0), new Vector(6, 0, 0));
        sphere = new Sphere(2, new Point3D(0, 0, 0));
        intersectionsList = sphere.findIntersections(ray);
        assertNotNull(intersectionsList, "must not be empty");
        assertEquals(2, intersectionsList.size(), "must be equal to 2");
        if (intersectionsList.get(0).getX().get() > intersectionsList.get(1).getX().get()) {
            intersectionsList = List.of(intersectionsList.get(1), intersectionsList.get(0));
        }
        assertEquals(new Point3D(-1.732050807569, 1, 0), intersectionsList.get(0), "must be equal");
        assertEquals(new Point3D(1.732050807569, 1, 0), intersectionsList.get(1), "must be equal");


        //TC03 ray start inside sphere:1 intersection
        ray = new Ray(new Point3D(0, 0, 0), new Vector(0, 0, -1));
        sphere = new Sphere(5, new Point3D(0, -1, -1));
        intersectionsList = sphere.findIntersections(ray);
        assertNotNull(intersectionsList, "must be not empty");
        assertEquals(1, intersectionsList.size(), "must be equal to one");
        assertEquals(new Point3D(0, 0, -5.898979485566), intersectionsList.get(0), "must be equal");

        //TC04 ray starts outside sphere after it, due to direction- no intersection
        ray = new Ray(new Point3D(0, 0, 0), new Vector(0, 0, -1));
        sphere = new Sphere(2, new Point3D(0, 1, 4));
        intersectionsList = sphere.findIntersections(ray);
        assertNull(intersectionsList, "must be empty");

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside
        ray = new Ray(new Point3D(-2, 0, 0), new Vector(2, 0, 2));
        sphere = new Sphere(2, new Point3D(0, 0, 0));
        intersectionsList = sphere.findIntersections(ray);
        assertNotNull(intersectionsList, "must be not empty");
        assertEquals(1, intersectionsList.size(), "must be equal to one");
        assertEquals(new Point3D(0, 0, 2), intersectionsList.get(0), "must be equal");

        // TC12; Ray starts at sphere and goes outside
        ray = new Ray(new Point3D(2, 0, 0), new Vector(2.29, 4.67, 0));
        sphere = new Sphere(2, new Point3D(0, 0, 0));
        intersectionsList = sphere.findIntersections(ray);
        assertNull(intersectionsList, "must be empty");

// **** Group: Ray's line goes through the center
        //TC13 ray start at the center of the sphere
        ray = new Ray(new Point3D(0, 0, 0), new Vector(0, 0, -1));
        sphere = new Sphere(5, new Point3D(0, 0, 0));
        intersectionsList = sphere.findIntersections(ray);
        assertNotNull(intersectionsList, "must be not empty");
        assertEquals(1, intersectionsList.size(), "must be equal to one");
        assertEquals(new Point3D(0, 0, -5), intersectionsList.get(0), "must be equal");

        //TC14 ray starts on sphere surface, to the outside:
        //no intersection because we don't take in account the ray head
        ray = new Ray(new Point3D(0, 0, 3), new Vector(0, 0, 5));
        sphere = new Sphere(2, new Point3D(0, 0, 1));
        intersectionsList = sphere.findIntersections(ray);
        assertNull(intersectionsList, "must be empty");

        assertNotNull(intersectionsList,"must be not empty");
        assertEquals( 1, intersectionsList.size(),"must be equal to one");
        assertEquals(new Point3D(0, 0, 3), intersectionsList.get(0),"must be equal");

        //TC15 ray starts on sphere surface, to the inside:
        //1 intersection because we don't take in account the ray head
        ray = new Ray(new Point3D(0, 0, 4), new Vector(0, 0, 1));
        sphere = new Sphere(2, new Point3D(0, 0, 6));
        intersectionsList = sphere.findIntersections(ray);
        assertNotNull(intersectionsList, "must be not empty");
        assertEquals(1, intersectionsList.size(), "must be equal to one");
        assertEquals(new Point3D(0, 0, 8), intersectionsList.get(0), "must be equal");


        //TC16 ray starts outside of the sphere to the outside but on a line aligned with the center: no intersections
        ray = new Ray(new Point3D(0, 0, 0), new Vector(0, 0, -1));
        sphere = new Sphere(5, new Point3D(0, 0, 6));
        intersectionsList = sphere.findIntersections(ray);
        assertNull(intersectionsList, "must be empty");

        //TC17 ray starts outside of the sphere to the inside cross twice the sphere and pass by center: 2 intersections
        ray = new Ray(new Point3D(0, 0, 0), new Vector(0, 0, -1));
        sphere = new Sphere(5, new Point3D(0, 0, -6));
        intersectionsList = sphere.findIntersections(ray);
        assertNotNull(intersectionsList, "must be not empty");
        assertEquals(2, intersectionsList.size(), "must be equal to one");
        assertEquals(new Point3D(0, 0, -1), intersectionsList.get(0), "must be equal");
        assertEquals(new Point3D(0, 0, -11), intersectionsList.get(1), "must be equal");

        //TC18 ray starts on sphere surface, to the outside on the same line that the center: 1 intersection
        ray = new Ray(new Point3D(0, 0, 1), new Vector(0, 0, 1));
        sphere = new Sphere(5, new Point3D(0, 0, 0));
        intersectionsList = sphere.findIntersections(ray);
        assertNotNull(intersectionsList, "must be not empty");
        assertEquals(1, intersectionsList.size(), "must be equal to one");
        assertEquals(new Point3D(0, 0, 5), intersectionsList.get(0), "must be equal");

        // **** Group: Special cases
        //TC19 ray is on a line that vertical to radius- ray starts on the radius line
        ray = new Ray(new Point3D(0, 0, 0), new Vector(0, 0, -1));
        sphere = new Sphere(1, new Point3D(0, 2, 0));
        intersectionsList = sphere.findIntersections(ray);
        assertNull(intersectionsList, "must be empty");

        // **** Group: Ray's line is tangent to the sphere
        //TC16 ray is on the tangent line- ray starts before intersection: 1 intersection
        ray = new Ray(new Point3D(0, 0, 0), new Vector(0, 0, -1));
        sphere = new Sphere(1, new Point3D(0, 1, -1));
        intersectionsList = sphere.findIntersections(ray);
        assertNull(intersectionsList, "must be empty");


//TC17 ray is on the tangent line- ray starts on intersection: 1 intersection
        ray = new Ray(new Point3D(0, 0, 0), new Vector(0, 0, -1));
        sphere = new Sphere(1, new Point3D(0, 1, 0));
        intersectionsList = sphere.findIntersections(ray);
        assertNull(intersectionsList, "must be empty");

        //TC18 ray is on the tangent line- ray starts after intersection: no intersections
        ray = new Ray(new Point3D(0, 0, 0), new Vector(0, 0, -1));
        sphere = new Sphere(1, new Point3D(0, 1, 1));
        intersectionsList = sphere.findIntersections(ray);
        assertNull(intersectionsList, "must be empty");
    }

}

