package unittests;

import Primitives.Ray;
import Primitives.Vector;
import Primitives.point3D;
import geometries.Tube;
import org.junit.Test;

import static org.junit.Assert.*;

public class TubeTest {
    @Test
    public void getNormal() {
        Ray ray = new Ray(new point3D(0,1,0), new Vector(0,1,0));
        Tube tb = new Tube( ray,2);
        assertEquals(tb.getNormal(new point3D(0,0,0)) ,new Vector(0,-1,0));
    }
}
