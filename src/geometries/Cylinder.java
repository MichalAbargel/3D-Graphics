package geometries;

import Primitives.Ray;
import Primitives.point3D;

public class Cylinder extends Tube {
    double height;

    public Cylinder(Ray axysRay, double radius, double height) {
        super(axysRay, radius);
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                super.toString()+
                '}';
    }

}
