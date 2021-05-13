package unittests;

import Primitives.Ray;
import Primitives.Vector;
import Primitives.point3D;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {
    @Test
    void findClosestPoint() {

        Ray ray = new Ray(point3D.zero, new Vector(0,0,1));
        List<point3D> point3DList = new LinkedList<>();
        point3D p1 = new point3D(0,0,2);
        point3D p2 = new point3D(0,0,3);
        point3D p3 = new point3D(0,0,4);

        // ============ Equivalence Partitions Tests ==============

        // TC01: The point in the middle of the list is the closest to the beginning of the ray
        point3DList.add(p2);
        point3DList.add(p1);
        point3DList.add(p3);
        assertEquals(point3DList.get(1),ray.findClosestPoint(point3DList));


        // =============== Boundary Values Tests ====================

        // TC10: Empty list (method should return null value)
        assertNull(ray.findClosestPoint(null), "supposed to be null");

        // TC11: The first point is closest to the beginning of the foundation
        point3DList = new LinkedList<>();
        point3DList.add(p1);
        point3DList.add(p2);
        point3DList.add(p3);
        assertEquals(point3DList.get(0),ray.findClosestPoint(point3DList));

        // TC12: The last point is closest to the beginning of the foundation
        point3DList = new LinkedList<>();
        point3DList.add(p3);
        point3DList.add(p2);
        point3DList.add(p1);
        assertEquals(point3DList.get(2),ray.findClosestPoint(point3DList));

    }
}