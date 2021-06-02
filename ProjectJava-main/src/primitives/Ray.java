package primitives;

import geometries.Intersectable.*;

import java.util.Objects;
import java.util.List;

import static primitives.Point3D.ZERO;

/**
 * this class Represent Ray in the euclidean field.
 *
 */
public class Ray {

    final Point3D _p0;
    final Vector _dir;

    private static final double DELTA = 0.1;


    /**
     * Ray Vector must saved as normalize vector
     * Vector 0 will throw exception
     *
     * @param head  Point3D (x,y,z)
     * @param direction Vector (x,y,z)
     */
    public Ray(Point3D head, Vector direction, Vector normal){
        this._dir = direction.normalize();
        if (direction.equals(ZERO)) {
            throw new IllegalArgumentException("Vector dir can't be point(0,0,0)");
        }
        if(direction.dotProduct(normal)<0)
            this._p0=head.add(normal.scale(-DELTA));
        else
            this._p0=head.add(normal.scale(DELTA));

    }

    public Ray(Point3D head, Vector direction){
        if (direction.equals(ZERO)) {
            throw new IllegalArgumentException("Vector dir can't be point(0,0,0)");
        }
        this._p0=head;
        this._dir = direction.normalize();

    }
    /**
     * getter.
     *
     * @return _p0
     */
    public Point3D getP0() {
        return _p0;
    }

    /**
     * getter.
     *
     * @return _dir
     */
    public Vector getDir() {
        return _dir;
    }

    /**
     * @param pointsList Point3D list to check which of them closes to ray origin
     * @return the closes point to ray P0
     */
    public Point3D findClosestPoint(java.util.List<Point3D> pointsList) {
        Point3D result=null;
        double closestDistance = Double.MAX_VALUE; // the biggest value

        /**
         * chack if pointsList empty
         */
        if (pointsList == null) {
            return null;
        }

        for (Point3D p : pointsList) {
            double temp = p.distance(_p0);
            if (temp < closestDistance) {
                closestDistance = temp;
                result = p;
            }
        }
        return result;
    }

    /**
     *TODO - notes
     * @param list
     * @return
     */

    public GeoPoint getClosestGeoPoint(List<GeoPoint> list){
        GeoPoint result=null;
        double closestDistance = Double.MAX_VALUE; // the biggest value

        /**
         * chack if list empty
         */
        if (list == null) {
            return null;
        }

        for (GeoPoint p : list) {
            double temp = p.point.distance(_p0);
            if (temp < closestDistance) {
                closestDistance = temp;
                result = p;
            }
        }
        return result;

    }

    /**
     * @param o
     * @return true when equals Ray to object argument.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return _p0.equals(ray._p0) && _dir.equals(ray._dir);
    }

    /**
     * Print Ray object.
     *
     * @return Ray toString
     */
    @Override

    public String toString() {
        return _p0.toString() +
                ", dir:" + _dir.toString();
    }

    /**
     * this function is very usefull in the Geometries packegae to calculate
     * intersections. for refactoring we put this here (Class Ray). this function
     * return point on the ray according to this formula (start ray point + t *
     * dirction ray's )
     *
     * @param t real number
     * @return point on the ray
     */
    public Point3D getPoint(double t) {
        try {
            if (Util.isZero(t))
                return _p0;
            return _p0.add(_dir.scale(t));
        } catch (IllegalArgumentException e) {
            return _p0;
        }

    }
}
