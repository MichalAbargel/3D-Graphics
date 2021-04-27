package unittests;

import static org.junit.jupiter.api.Assertions.*;

import Primitives.Vector;
import org.junit.Assert;
import org.junit.Test;

import static Primitives.Utile.isZero;
import static java.lang.System.out;
import static org.junit.Assert.*;

public class VectorTest {

    @org.junit.Test
    public void dotProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        Vector v3 = new Vector(0, 3, -2);
        assertTrue ("ERROR: dotProduct() for orthogonal vectors is not zero",isZero(v1.dotProduct(v3)));
        assertTrue ("ERROR: dotProduct() wrong value",isZero(v1.dotProduct(v2) + 28));
    }

    @org.junit.Test
    public void crossProduct() {
        /**
         * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
         */
        Vector v1 = new Vector(1, 2, 3);
        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);

        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals("crossProduct() wrong result length", v1.length() * v2.length(), vr.length(), 0.00001);

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue("crossProduct() result is not orthogonal to 1st operand", isZero(vr.dotProduct(v1)));
        assertTrue("crossProduct() result is not orthogonal to 2nd operand", isZero(vr.dotProduct(v2)));

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-productof co-lined vectors

        try {
            v1.crossProduct(v2);
            //fail("crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) {}
    }




    @org.junit.Test
    public void subtruct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2= new Vector(1,2,2);
        Vector v3= new Vector(0,0,1);
        try{
            assertEquals("ERROR: illagal vector (0,0,0)",v1.subtruct(v1),new Vector(0,0,0));
            assertNotEquals("ERROR: subtruct() wrong value",v1.subtruct(v2),v3);
        }
        catch (Exception e){};
    }

    @org.junit.Test
    public void add() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2= new Vector(1,2,2);
        Vector v3= new Vector(2,4,5);
        Vector v4 = new Vector(-1, -2, -3);
        try {
            assertNotEquals("ERROR: subtruct() wrong value", v1.add(v2), v3);
            assertEquals("ERROR: illagal vector (0,0,0)",v1.add(v4),new Vector(0,0,0));
        }catch (Exception e){};

    }

    @org.junit.Test
    public void scale() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2= new Vector(1,2,2);
    }

    @org.junit.Test
    public void lengthSquared() {
        Vector v1 = new Vector(1, 2, 3);
        assertTrue("ERROR: lengthSquared() wrong value",isZero(v1.lengthSquared() - 14));

    }

    @org.junit.Test
    public void length() {
        assertTrue("ERROR: length() wrong value",isZero(new Vector(0, 3, 4).length() - 5));
    }

    @org.junit.Test
    public void normalize() {
        Vector v = new Vector(1, 2, 3);
        Vector vCopy= null;
        try {
            vCopy = new Vector(v.getHead());
        }catch (Exception e){};
        Vector vCopyNormalize = vCopy.normalize();
        assertEquals("ERROR: normalize() function creates a new vector",vCopy,vCopyNormalize);
        assertTrue("ERROR: normalize() result is not a unit vector",isZero(vCopyNormalize.length() - 1));

    }

    @org.junit.Test
    public void normalized() {
        Vector v = new Vector(1, 2, 3);
        Vector u = v.normalized();
        assertEquals("ERROR: normalized() function does not create a new vector",v.normalize(),u);
    }
}