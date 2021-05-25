package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public abstract class RadialGeometry extends Geometry {
    protected final double _radius;

    /* constructor that receive a double and put him to be the value of _radius */

    public RadialGeometry(double radius) {
        if(radius <= 0){
            throw new IllegalArgumentException("radious can not be <= 0");
        }
        _radius = radius;
    }

    /* Getter */

    private double getRadius() {
        return _radius;
    }
    public abstract Vector getNormal(Point3D point);

    public abstract List<Point3D> findIntersections(Ray ray);

    public abstract List<Intersectable.GeoPoint> findGeoIntersections(Ray ray);
}
