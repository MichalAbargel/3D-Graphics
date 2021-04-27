package unittests;

import Primitives.Vector;
import Primitives.point3D;
import elements.Camera;
import geometries.Intersectable;
import geometries.Sphere;
import geometries.Triangle;
import geometries.plane;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IntersectionTest {
    Camera cam1 = new Camera(point3D.zero, new Vector(0, 0, 1), new Vector(0, -1, 0));
    Camera cam2 = new Camera(new point3D(0, 0, -0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));

    /**
     * the func finds amount of intersections between a geometry and a camera
     *
     * @param geometry the geometry to intersect in the test
     * @param expected expected amount of intersections
     * @param cam      the camera to generate the rays from
     */
    private void intersections(Intersectable geometry, int expected, Camera cam) {
        List<point3D> results = null;
        int count = 0;
        //w X h--- 3 X 3
        int Nx = 3;
        int Ny = 3;
        cam.setDistance(1);
        cam.setViewPlaneSize(3,3);
        for (int i = 0; i < Ny; ++i) {
            for (int j = 0; j < Nx; ++j) {
                results = geometry.findIntsersections(cam.constructRayThroughPixel(Nx, Ny, j, i));

                ;
                if (results != null)
                    count += results.size();
            }
        }
        assertEquals( expected, count,"not good");
    }

    /**
     * we test the intersection between camera and sphere
     */
    @Test
    public void constructRayThroughPixelWithSphere() {
        // TC01 test of intersection with sphere when all the sphere are out from the screen 3*3
        intersections(new Sphere( 1, new point3D(0, 0, 3)), 2, cam1);

        // TC02 test of intersection with sphere when the all screen are in sphere
        intersections(new Sphere( 2.5, new point3D(0, 0, 2.5)), 18, cam2);

        //TC03:part of screen are in the sphere
        intersections(new Sphere( 2, new point3D(0, 0, 2)), 10, cam2);

        //TC04:the sphere around the screen
        intersections(new Sphere( 4, new point3D(0, 0, 2)), 9, cam2);

        //TC05: the sphere is behind the screen
        intersections(new Sphere( 0.5, new point3D(0, 0, -1)), 0, cam2);
    }

    /**
     * we test all the intersection between camera and plane on screen 3*3
     */
    @Test
    public void constructRayThroughPixelWithPlane() {
        //TC01: Plane is parallel to view Plane (9 points)
        intersections(new plane(new point3D(0, 0, 3),new Vector(0, 0, 1)), 9, cam1);

        //TC02: Plane is not parallel to view Plane(9 points)
        intersections(new plane(new point3D(0.5, -1.5, 2), new point3D(0, 0, 2.5), new point3D(0, 1.5, 3)),
                9, cam1);

        //TC03: Plane is not parallel to view Plane (6 points)
        intersections(new plane(new point3D(0.5, -2, 2), new point3D(0, 0, 4), new point3D(0, 2, 6))
                , 6, cam1);
    }

    /**
     * we test all the intersection between camera and Triangle on screen 3*3
     */
    @Test
    public void constructRayThroughPixelWithTriangle() {
        //TC01: when triangle are smaller than the screen
        try {
            intersections(new Triangle( new point3D(0, -1, 2), new point3D(1, 1, 2), new point3D(-1, 1, 2)),
                    1, cam1);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        //TC02: when the triangle is bigger than the screen
        try {
            intersections(new Triangle( new point3D(0, -20, 2), new point3D(1, 1, 2), new point3D(-1, 1, 2)),
                    2, cam1);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

}