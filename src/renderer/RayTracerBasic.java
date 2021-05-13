package renderer;

import Primitives.Color;
import Primitives.Ray;
import Primitives.point3D;
import secens.Scene;

import java.util.List;

public class  RayTracerBasic extends RayTracerBase{
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        List<point3D> intersections = this.scene.geometries.findIntsersections(ray);
        if (intersections != null) {
            point3D closestPoint = ray.findClosestPoint(intersections);
            return calcColor(closestPoint);
        }
        //ray did not intersect any geometrical object
        return this.scene.background;
    }

    private Color calcColor(point3D point) {
       return this.scene.ambientLight.getIntensity();

    }

}
