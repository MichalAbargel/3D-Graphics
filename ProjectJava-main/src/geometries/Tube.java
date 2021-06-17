package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * class Tube, this class Represent Tube in the euclidean field.
 */


public class Tube extends RadialGeometry {
    protected Ray _axisRay;


    /**
     *C-tor of Tube need ( Ray , double)
     * @param axisRay Ray (Point3D, Vector)
     * @param radius double
     */
    public Tube(double radius, Ray axisRay) {
        super(radius);
        this._axisRay = axisRay;
    }

    /**
     *
     * @return _axisRay Ray
     */
    public Ray getAxisRay() {
        return _axisRay;
    }

    /**
     *
     * @return _radius double
     */
    public double getRadius() {
        return _radius;
    }

    /**
     *
     * @return toString of Tube
     */
    @Override
    public String toString() {
        return "axisRay: " + _axisRay.toString() +
                ", radius=" + _radius;
    }

    /**
     *  getNormal of class Tube.
     * @param point  point that we calculate the normal for.
     * @return normalize vector (the vector itself has been changed )
     */
    @Override
    public Vector getNormal(Point3D point) {
        Point3D po = _axisRay.getP0();
        Vector v=_axisRay.getDir();

        Vector P0P =point.subtract(po);

        double t = alignZero(v.dotProduct(P0P));
        if (isZero(t)) {
            return P0P;
        }

        Point3D o = po.add(v.scale((t)));

        if(o.equals(point)){
            throw new IllegalArgumentException("point cannot be the reference point  of the tube");
        }

        return point.subtract(o).normalize();
    }

    /**
     *
     * @param ray the famous  Ray pointing to.
     * @return return null. I don't implement this bonus
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return null;
    }

    @Override
    public List<Intersectable.GeoPoint> findGeoIntersections(Ray ray) {
        return null;
    }
}
