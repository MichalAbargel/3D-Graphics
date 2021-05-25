package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/* class plane present a plane in 3D cartesian coordinate system */

public class Plane extends Geometry{
    protected final Vector _noraml;
    protected final Point3D _q0;

    /* Constructors */

    public Plane(Point3D p1, Point3D p2, Point3D p3) {
        _q0 = p1;

        Vector u = p2.subtract(p1);
        Vector v = p3.subtract(p1);

        Vector n = u.crossProduct(v);

        _noraml = n.normalize();


    }
    /* constructor build a plane from a single point and a vector that will be normalized */
    public Plane(Vector noraml, Point3D q0) {
        _noraml = noraml.normalized();
        _q0 = q0;
    }
    /*
    @Deprecated ues{ @link plane#getNurmal(point3D) }wite null value for parmeter
    get the normal vector of a plane R3
    *  */
    @Deprecated
    public Vector getNormal() {
        return _noraml;
    }
    public Vector getNormal(Point3D point) {
        return _noraml;
    }
    @Override
    public String toString() {
        return "Plane{" +
                "_noraml=" + _noraml +
                ", _q0=" + _q0 +
                '}';
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        Vector p0Q;
        try {
            p0Q = _q0.subtract(ray.getP0());
        } catch (IllegalArgumentException e) {
            return null; // ray starts from point Q - no intersections
        }

        double nv = _noraml.dotProduct(ray.getDir());
        if (isZero(nv)) // ray is parallel to the plane - no intersections
            return null;

        double t = alignZero(_noraml.dotProduct(p0Q) / nv);
        Point3D _POO=ray.getP0();
        Vector _direction=ray.getDir();

        if (isZero(t)) {
            Point3D X = _POO.add(_direction.scale(t));
            return t <= 0 ? null:  List.of(X);
        }
       else
            return t <= 0 ? null:  List.of(_POO);

    }

    /**
     *
     * @param ray
     * @return
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {

        /**
         * we define variables  P0 and V to calculate ray that Intersection Sphere
         */
        Point3D P0 = ray.getP0();
        Vector v = ray.getDir();

        try {
            v=_q0.subtract(ray.getP0());
        }
        catch (IllegalArgumentException ex)
        {  return  null; } // if ray start from the point Q - there is no intersections

        double Nv= _noraml.dotProduct(ray.getDir());
        if (isZero(Nv)) // if ray parallel to the plane - there is no intersections
            return null;

        double t =alignZero(_noraml.dotProduct(v) /Nv);
        if (t<=0)
        {
            return null;
        }

        // TODO - cheack if this is correcnt
        Point3D P=ray.getPoint(t);
        return List.of(new GeoPoint(this,P));


    }
}

