package geometries;

import Primitives.Ray;
import Primitives.point3D;

import java.util.List;

public interface Intersectable {
    List<point3D> findIntsersections(Ray ray);
}
