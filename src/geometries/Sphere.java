package geometries;

import Primitives.Ray;
import Primitives.Vector;
import Primitives.point3D;
import jdk.jfr.ValueDescriptor;

import java.util.List;

import static Primitives.Utile.alignZero;

public class Sphere implements Geometry {
    point3D Center;
    final double Radius;

    public Sphere(double radius, point3D center) {
        this.Center=center;
        Radius = radius;
    }

    public point3D getCenter() {

        return Center;
    }

    public double getRadius() {
        return Radius;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "Center=" + Center +
                ", Radius=" + Radius +
                '}';
    }
    public Vector getNormal(point3D point) {
        Vector n= null;
        try {
            n = point.subtract(this.Center);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return n.normalize();
    }

    @Override
    public List<point3D> findIntsersections(Ray ray) {
        point3D p0 = ray.getP0();
        Vector v = ray.getDir();
        Vector u;
        // 𝑢 = 𝑂 − 𝑃0 distance from the center and the p0
        try {
            u = this.Center.subtract(p0);
        } catch (IllegalArgumentException e) {
            return List.of(ray.getPoint(this.Radius));
        }
        //tm=v*u the distance between p0 and the point with makes 90 degrees with the center
        double tm = alignZero(v.dotProduct(u));
        //d=u^2+tm^2 distance between the center and the point that  makes 90 degrees with the center
        double dsquared;
        if (tm == 0)
            dsquared = u.lengthSquared();
        else {
            dsquared = u.lengthSquared() - tm * tm;
        }
        double thsquared = alignZero(this.Radius * this.getRadius() - dsquared);

        if (thsquared <= 0) return null;
        //th=radius*radius-d*d between p1 and center
        double th = alignZero(Math.sqrt(thsquared));
        if (th == 0) return null;

        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);
        if (t1 <= 0 && t2 <= 0) return null;

        if (t1 > 0 && t2 > 0)
            return List.of(ray.getPoint(t1), ray.getPoint(t2)); //P1 , P2
        if (t1 > 0)
            return List.of(ray.getPoint(t1)); //just one point
        if (t2 > 0)
            return List.of(ray.getPoint(t2));
        return null;
    }
}
