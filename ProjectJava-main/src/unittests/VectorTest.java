package unittests;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class VectorTest {

    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(2, 0, 0);
    Vector v3 = new Vector(1, 3, -2);

    Point3D point = new Point3D(3,3,3);

    @Test
    void add() {
        Vector v = v1.add(v2);
        assertEquals(v,new Vector(3,2,3),"not success");

    }

     @Test
    void dotProduct() {
        if (isZero(v1.dotProduct(v3)))
           out.println("ERROR: dotProduct() for orthogonal vectors is not zero");
        assertEquals(1,v1.dotProduct(v3),"not success");

   }


    @Test
    void crossProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);


        // ============ Equivalence Partitions Tests ==============
        Vector v3 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v3);

        // Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
        assertEquals( v1.length() * v3.length(), vr.length(), 0.00001,"crossProduct() wrong result length");

        // Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)), "crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v3)), "crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // test zero vector from cross-productof co-lined vectors
        try {
            v1.crossProduct(v2);
            fail("crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) {}

    }

    @Test
    void subtract() {
        Point3D p=new Point3D(1d,1d,1d);
        Vector vsub =new Vector(p);
            Point3D p1=new Point3D(-1d,-1d,-1d);
            Vector vsub1 =new Vector(p1);
            Vector vsub3 =vsub.subtract(p1);
        assertEquals(vsub3._head,new Vector(2.0,2.0,2.0)._head,"not success");
    }


    @Test
    void lengthSquared() {
        double vlength = 14;
        assertEquals(v1.lengthSquared(),vlength,"not succes");
    }

    @Test
    void length() {
        if (!isZero(new Vector(0, 3, 4).length() - 5))
            out.println("ERROR: length() wrong value");
    }

    @Test
    void normalize() {

        Vector v = new Vector(1, 2, 3);
        Vector vCopy = new Vector(v.getHead());
        Vector vCopyNormalize = vCopy.normalize();
        if (vCopy != vCopyNormalize)
            out.println("ERROR: normalize() function creates a new vector");
        if (!isZero(vCopyNormalize.length() - 1))
            out.println("ERROR: normalize() result is not a unit vector");
        Vector u = v.normalized();
        if (u == v)
            out.println("ERROR: normalizated() function does not create a new vector");
    }

    @Test
    void normalized() {
        Vector v = new Vector(1, 2, 3);
        Vector vCopy = new Vector(1/Math.sqrt(14),2/Math.sqrt(14),3/Math.sqrt(14));
        Vector u = v.normalized();
        if (vCopy!=u)
            out.println("ERROR: normalizated() function does not create a new vector");
    }

    @Test
    void testEquals() {
        Vector v = new Vector(1, 2, 3);
        Vector vCopy = new Vector(1,2,3);
        if (!vCopy.equals(v))
            out.println("ERROR: normalizated() function does not create a new vector");
    }
}