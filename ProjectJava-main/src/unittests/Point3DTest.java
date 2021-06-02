package unittests;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class Point3DTest {
    Point3D p1 = new Point3D(0.0d, 0.5d, 1.0d);
    Point3D p2 = new Point3D(0.000000000000000001d, 0.499999999999999999999d, 1.0d);

    @Test
    void testEquals() {
        assertEquals(p1,p2);
    }

    @Test
    void testToString() {
        System.out.println(p1);
    }

    @Test
    void distance(){
        assertEquals(0,p1.distance(p2));

    }

    @Test
    void subtract() {
        Point3D p1 = new Point3D(1, 2, 3);
        Vector result = new Point3D(2,3,4).subtract(p1);
        assertEquals(new Vector(1,1,1),result,"ERROR: Point - Point does not work correctly");
        }

  /* add Point3D to vector
  return Point3D
  */

    @Test
    void add() {
        Vector p1 = new Vector(1, 2, 3);
        Point3D result = new Point3D(2,3,4).add(p1);
        assertEquals(new Point3D(3,5,7),result,"ERROR: Point - Point does not work correctly");

    }
}