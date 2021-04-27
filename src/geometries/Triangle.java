package geometries;
import Primitives.*;

import java.util.List;

import static Primitives.Utile.isZero;

public class Triangle extends Polygon {

    public Triangle(point3D p1,point3D p2,point3D p3) throws IllegalAccessException {
        super(p1,p2,p3);
    }

    @Override
    public String toString() {
        return "Triangle{" +
                super.toString()+
                '}';
    }

    @Override
    public List<point3D> findIntsersections(Ray ray) {
        List<point3D> intersections = this.plane.findIntsersections(ray);
        if (intersections == null)
            return null;

        point3D p0 = ray.getP0();
        Vector v = ray.getDir();
        //we creat the three vector
        Vector v1 = null;
        Vector v2 = null;
        Vector v3 = null;
        try {
            v1 = this.vertices.get(0).subtract(p0);
            v2 = this.vertices.get(1).subtract(p0);
            v3 = this.vertices.get(2).subtract(p0);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }


        double side1 = v.dotProduct(v1.crossProduct(v2));
        if (isZero(side1))
            return null;
        double side2 = v.dotProduct(v2.crossProduct(v3));
        if (isZero(side2))
            return null;
        double side3 = v.dotProduct(v3.crossProduct(v1));
        if (isZero(side3))
            return null;

        if ((side1 > 0 && side2 > 0 && side3 > 0) || (side1 < 0 && side2 < 0 && side3 < 0))
            return intersections;
        return null;
    }
}
