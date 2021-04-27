package geometries;

import Primitives.*;

public interface Geometry extends Intersectable {

    public Vector getNormal(point3D point);
}