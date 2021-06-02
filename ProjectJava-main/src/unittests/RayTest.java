package unittests;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    @Test
    void testFindClosestPoint() {
        Ray ray = new Ray(new Point3D(0,0,10),new Vector(1,10,-100));

        java.util.List <Point3D> list= new LinkedList<>();

        list.add(new Point3D(1,1,-100));
        list.add(new Point3D(-1,1,-90));
        list.add(new Point3D(0,2,-10));
        list.add(new Point3D(0.5,0,-99));


        assertEquals(list.get(2),ray.findClosestPoint(list), "findClosestPoint not worked ");
    }


    @Test
    void testFindClosestPoint2() {
        Ray ray = new Ray(new Point3D(0,0,10),new Vector(1,10,-100));

        java.util.List <Point3D> list= new LinkedList<>();

        list.add(new Point3D(0,2,-10));
        list.add(new Point3D(1,1,-100));
        list.add(new Point3D(-1,1,-90));
        list.add(new Point3D(0.5,0,-99));


        assertEquals(list.get(0),ray.findClosestPoint(list), "findClosestPoint not worked ");
    }



    @Test
    void testFindClosestPoint3() {
        Ray ray = new Ray(new Point3D(0,0,10),new Vector(1,10,-100));

        java.util.List <Point3D> list= new LinkedList<>();

        list.add(new Point3D(1,1,-100));
        list.add(new Point3D(-1,1,-90));
        list.add(new Point3D(0.5,0,-99));
        list.add(new Point3D(0,2,-10));

        assertEquals(list.get(3),ray.findClosestPoint(list), "findClosestPoint not worked ");
    }




    @Test
    void testFindClosestPoint4() {
        Ray ray = new Ray(new Point3D(0,0,10),new Vector(1,10,-100));

        java.util.List <Point3D> list= null;


        assertNull(list, "findClosestPoint not worked. its need to be null ");
    }

}