package unittests;

import Primitives.Ray;
import Primitives.Vector;
import Primitives.point3D;
import geometries.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest {

    @Test
    void findIntsersections() {
        Geometries geometries = new Geometries();

        // =============== Boundary Values Tests ==================

        assertNull(geometries.findIntsersections(new Ray(new point3D(0,1,0), new Vector(1,0,5))),
                "empty geometries collections");

        geometries.add(new plane(new point3D(1,1,0), new Vector(0,0,1)));
        try {
            geometries.add(new Triangle(new point3D(1,0,0), new point3D(0,1,0), new point3D(0,0,1)));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        geometries.add((Intersectable) new Sphere(1d, new point3D(1, 0, 0)));


        assertNull(geometries.findIntsersections(new Ray(new point3D(0,0,2), new Vector(0,-1,0))),
                "each geometry doesn't have intersections points");

        assertEquals( 1, geometries.findIntsersections(new Ray(new point3D(0,5,-1), new Vector(0,0,1))).size(),
                "just one geometry has intersections point");

        // ============ Equivalence Partitions Tests ==============
        assertEquals( 2, geometries.findIntsersections(new Ray(new point3D(1,0,-1), new Vector(0,0,1))).size(),
                "part of the geometries has intersections points");

    }
}