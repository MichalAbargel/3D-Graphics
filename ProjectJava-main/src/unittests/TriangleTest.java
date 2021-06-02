package unittests;

import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


//*        Test method for {@link geometries.Triangle}.
        /*Triangle test class test triangle in 3D Cartesian coordinate*/

public class TriangleTest {
    /*
            * we test if the constructor find the problem in this case
            */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        //TC01:with two same points and so it's not a triangle it's a vector
        try {
            new Triangle( new Point3D(0, 0, 0), new Point3D(0, 0, 0), new Point3D(1, 2, 3));
            fail("Constructed a vector not a triangle ");
        } catch (IllegalArgumentException e) {

        }
    }


    /**
     * we test if when we have intersection witch ray and triangle we find intersection if exist amd if he found the right intersection
     */
    @Test
    public void findIntersections() {
        // ============ Equivalence Partitions Tests ==============
        //TC01 inside triangle
        Ray ray = new Ray(new Point3D(-4, 4, 3), new Vector(4, -2, -1));
        Triangle triangle = new Triangle(new Point3D(0, 0, 0), new Point3D(0, 6, 0), new Point3D(0, 0, 8));
        List<Point3D> intersectionsList = triangle.findIntersections(ray);
        assertNotNull( intersectionsList,"must be not empty");
        assertEquals( 1, intersectionsList.size(),"must be equal to 1");
        assertEquals( new Point3D(0, 2, 2), intersectionsList.get(0),"must be the same");

        //TC02 outside against edge
        ray = new Ray(new Point3D(5, 6, 0), new Vector(0, 0, -1));
        intersectionsList = triangle.findIntersections(ray);
        assertNull( intersectionsList,"must be empty");


        //TC02 outside against vertex
        ray = new Ray(new Point3D(-1, -1, 0), new Vector(0, 0, -1));
        intersectionsList = triangle.findIntersections(ray);
        assertNull( intersectionsList,"must be empty");


        // =============== Boundary Values Tests ==================

        //ray starts "before" the plane
        //TC10 on edge
        ray = new Ray(new Point3D(-2, 0, 5), new Vector(1, 0, -1));
        intersectionsList = triangle.findIntersections(ray);
        assertNull( intersectionsList,"must be empty");

        // TC12 in vertex
        ray = new Ray(new Point3D(6, 0, 1), new Vector(1, 0, -1));
        intersectionsList = triangle.findIntersections(ray);
        assertNull( intersectionsList,"must be not empty");

        // TC13 on edge continuation
        ray = new Ray(new Point3D(10, 0, 0), new Vector(0, 0, -1));
        intersectionsList = triangle.findIntersections(ray);
        assertNull( intersectionsList,"must be empty");
    }

}