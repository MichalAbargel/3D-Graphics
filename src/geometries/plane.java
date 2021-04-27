package geometries;

import Primitives.Ray;
import Primitives.Vector;
import Primitives.point3D;

import java.util.List;

import static Primitives.Utile.alignZero;
import static Primitives.Utile.isZero;

public class plane implements Geometry {

    point3D q0;
    Vector normal;

    public plane(point3D q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal;
    }
    public plane(point3D p1, point3D p2,point3D p3) {
        q0 = p1;
        Vector U=new Vector(1,1,1) ;
        Vector V=new Vector(1,1,1) ;
        try {
            U = p2.subtract(p1);
            V = p3.subtract(p1);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        Vector N = U.crossProduct(V);

        N.normalize();
        //right hand rule
        normal = N;
    }

    @Override
    public String toString() {
        return "plane{" +
                "q0=" + q0 +
                ", normal=" + normal +
                '}';
    }

    public point3D getQ0() {
        return q0;
    }

    public Vector getNormal() {

        return this.normal;
    }

    @Override
    public Vector getNormal(point3D point) {

        return this.getNormal();
    }

    @Override
    public List<point3D> findIntsersections(Ray ray) {
        Vector q0;
        try {
            q0 = this.q0.subtract(ray.getP0());
        } catch (IllegalArgumentException e) {
            return null; // ray starts from point Q - no intersections
        }

        double nv = this.getNormal().dotProduct(ray.getDir());
        if (isZero(nv)) // ray is parallel to the plane - no intersections
            return null;

        double t = alignZero(this.getNormal().dotProduct(q0) / nv);

        return t <= 0 ? null : List.of(ray.getPoint(t));
    }
}


