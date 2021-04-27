package geometries;

import Primitives.Ray;
import Primitives.Vector;
import Primitives.point3D;

import java.util.List;

public class Tube implements Geometry {
    final Ray AxysRay;
    final double Radius;

    public Tube(Ray axysRay, double radius) {
        AxysRay = axysRay;
        Radius = radius;
    }


    public Ray getAxysRay() {
        return AxysRay;
    }

    public double getRadius() {
        return Radius;
    }

    @Override
    public String toString() {
        return "Tube{" +
                "AxysRay=" + AxysRay +
                ", Radius=" + Radius +
                '}';
    }

    @Override
    public Vector getNormal(point3D point) {
        Vector p1 = null;
        try {
            p1 = point.subtract(this.AxysRay.getP0());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        double v = this.AxysRay.getDir().dotProduct(p1);
        point3D O = this.AxysRay.getP0().add(p1.scale(v));
        Vector N = null;
        try {
            N = point.subtract(O);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return N.normalize();
    }

    @Override
    public List<point3D> findIntsersections(Ray ray) {
        return null;
    }
}
