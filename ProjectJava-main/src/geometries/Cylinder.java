package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;
import java.util.Objects;

/* class that present cylinder , it is a tube with limit */

public class Cylinder extends Tube {
    protected
    double height;

    /* Constructor */

    private Cylinder(double height, Ray r, double radius) {
        super(radius, r);
        this.height = height;
    }

    /* Getter */

    private double getHeight() {
        return height;
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return super.findIntersections(ray);
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + height +
                ", _axisRay=" + _axisRay +
                ", _radius=" + _radius +
                '}';
    }
}
