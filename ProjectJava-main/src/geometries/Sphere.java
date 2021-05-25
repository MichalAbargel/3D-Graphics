package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import javax.lang.model.type.NullType;
import java.util.List;
import java.util.Objects;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;


/* class present a sphere */

public class Sphere extends RadialGeometry  {

    final Point3D _center;

    /* Constructor */

    public Sphere( double radius , Point3D center) {
        super(radius);
        _center = center;

    }

    /* Getter */

    private Point3D getCenter() {
        return _center;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "_center=" + _center +
                ", _radius=" + _radius +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sphere sphere = (Sphere) o;
        return Double.compare(sphere._radius, _radius) == 0 && _center.equals(sphere._center);
    }

    /* normal vector of a Sphere */

    public Vector getNormal(Point3D point) {
        if (point.equals(_center)) {
            throw new IllegalArgumentException("point is can not to be 0");
        }
        Vector n = point.subtract(_center);
        return n.normalized();
    }

    /* Operations */
    /*list that get ray and find all the intersections with the sphere   */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> result = null;
        Point3D P0 = ray.getP0();
        Vector v = ray.getDir();

        if(_center.equals(P0)){
            throw new IllegalArgumentException("ray can not be sphere center");
        }
        Vector u = _center.subtract(P0);

        double tm = alignZero(u.dotProduct(v));
        double d = alignZero(Math.sqrt(u.lengthSquared()- tm * tm));

        if(alignZero(d) > _radius){
            return null;
        }

        double th = alignZero(Math.sqrt(_radius*_radius - d * d));

        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);

        if(t1 > 0 && t2 >0){
            Point3D P1 = P0.add(v.scale(t1));
            Point3D P2 = P0.add(v.scale(t2));

            return  List.of(P1,P2);
        }

        else if (t2 >= 0 ){
            Point3D P2 = P0.add(v.scale(t2));
            return List.of(P2);
        }

        return result;
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        /**
         * we define variables  P0 and V to calculate ray that Intersection Sphere
         */
        Point3D P0 = ray.getP0();
        Vector v = ray.getDir();


        if (_center.equals(P0)) {
            throw new IllegalArgumentException("ray origin cannot be at Sphere's center");
        }

        /**
         *to define U value like we study ->  U= O-P0
         */
        Vector u = _center.subtract(P0);

        /**
         * to define tm value like we study ->  tm= v dotProduct u
         * d =  sqrt of u^2 -tm^2
         */
        double tm = alignZero(u.dotProduct(v));
        double d = alignZero(Math.sqrt(u.lengthSquared() - tm * tm));

        /**
         * if d >= r
         * there are no intersections
         */
        if (d >= _radius) {
            return null;
        }
        /**
         *to define tm value like we study ->  th= sqrt of  r^2 -d^2
         */
        double th = alignZero(Math.sqrt(_radius * _radius - d * d));

        /**
         *to define t1,t2 value like we study ->  t1= tm-th
         *                                        t2= tm+th
         */
        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);

        /**
         *if one of the 't' >0 we want to calculate the follow variables :
         * for t1 -> P1= P0 + t1*v
         * for t2 -> P2= P0 + t2*v
         *
         * if t2 =0 then
         * P2= P0 + t2*v
         */
        if (t1 > 0 && t2 > 0) {
            Point3D P1 = P0.add(v.scale(t1));
            Point3D P2 = P0.add(v.scale(t2));

            return List.of(new GeoPoint(this,P1) ,new GeoPoint(this,P2) );
        } else if (t1 > 0) {
            Point3D P1 = P0.add(v.scale(t1));
            return List.of(new GeoPoint(this,P1));
        } else if (t2 > 0) {
            Point3D P2 = P0.add(v.scale(t2));
            return List.of(new GeoPoint(this,P2));
        }


        return null;
    }
}
