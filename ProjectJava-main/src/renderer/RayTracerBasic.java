package renderer;
import elements.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.alignZero;



public class RayTracerBasic extends RayTracerBase {

    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final int MAX_CALC_COLOR_LEVEL = 10;

    /**
     * receive scene and use the father class - abstract RayTracerBase
     * @param scene to receive
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    private GeoPoint findClosestIntersection(Ray ray){
        java.util.List<GeoPoint> intersections = _scene._geometries.findGeoIntersections(ray);
        if (intersections != null) {
            GeoPoint closestPoint = ray.getClosestGeoPoint(intersections);
            return closestPoint;
        }
        return null;
    }
    /**
     * search intersections between the ray and the 3D dimension of the scene
     *
     * @param ray to find intersections
     * @return Color - return from the closestPoint
     */
    @Override
    public Color traceRay(Ray ray) {
        GeoPoint closestPoint = findClosestIntersection(ray);
        return closestPoint == null ? _scene._background : calcColor(closestPoint, ray);
    }


    /**
     * This function and calcLocalEffects function and calcSpecular and calcDiffusive function
     * calculates the color of point with all of kinds of lights. We
     * use Phong's model of light Together To got the precise color on the
     * intersection point. We combine The Diffuse light and Specular Light and
     * emission light
     * @param geopoint receive point3D and return color
     * @param ray the ray from viewPlane
     * @return in this part of the project this return only ambient Light light of the scene
     */

//    private Color calcColor(GeoPoint point, Ray ray) {
//        return _scene._ambientLight.getIntensity()
//                .add(point.geometry.getEmission()
//                        .add(calcLocalEffects(point, ray)));
//       // return point.geometry.getEmission().add(calcLocalEffects(point, ray));
//    }
    private Color calcColor(GeoPoint geopoint, Ray ray) {
        return calcColor(geopoint, ray, MAX_CALC_COLOR_LEVEL, 0.1)
                .add(_scene._ambientLight.getIntensity());
    }

    private Color calcColor(GeoPoint intersection, Ray ray, int level, double k) {
        Color color = intersection.geometry.getEmission();
        color = color.add(calcLocalEffects(intersection, ray, k));
        return 1 == level|| k<MIN_CALC_COLOR_K ? color : color.add(calcGlobalEffects(intersection, ray.getDir(),level, k));
    }


    /**
     * this fucntion calculate Local Effects
     * @param intersection get the point to calculate the effect next to her
     * @param ray get the Ray and Calculate , like we study in the course
     * @return Color
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray, double k) {
        Color color = intersection.geometry.getEmission();
        Vector v = ray.getDir();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) {
            return Color.BLACK;
        }
        Material material = intersection.geometry.getMaterial();
        int nShininess = material._nShininess;
        double kd = material._kD;
        double ks = material._kS;
        for (LightSource lightSource : _scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                double ktr = transparency(lightSource, l, n, intersection);
                if (ktr * k > MIN_CALC_COLOR_K) {
                    Color lightIntensity = lightSource.getIntensity(intersection.point).scale(ktr);
                    color = color.add(calcDiffusive(material._kD, l, n, lightIntensity),
                            calcSpecular(material._kS, l, n, v, nShininess, lightIntensity));
                }
            }

        }
        return color;

    }


    private Color calcGlobalEffects(GeoPoint gp, Vector v, int level, double k) {
        Color color = Color.BLACK; Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();
        double kkr = k * material.kR;
        if (kkr > MIN_CALC_COLOR_K)
            color = calcGlobalEffect(constructReflectedRay(gp, v, n), level, material.kR, kkr);
        double kkt = k * material.kT;
        if (kkt > MIN_CALC_COLOR_K)
            color = color.add(
                    calcGlobalEffect(constructRefractedRay(gp, v, n), level, material.kT, kkt));
        return color;
    }
    private Color calcGlobalEffect(Ray ray, int level, double kx, double kkx) {
        GeoPoint gp = findClosestIntersection (ray);
        return (gp == null ? _scene._background : calcColor(gp, ray, level - 1, kkx)
        ).scale(kx);
    }


    private Ray constructRefractedRay(GeoPoint point, Vector v, Vector n) {
        return new Ray(point.point,v,n);
    }

    private Ray constructReflectedRay(GeoPoint point, Vector v, Vector n) {
        Vector r = v.subtract(n.scale(2*v.dotProduct(n))._head);
        ///
        return new Ray(point.point,r,n);
    }


    /**
     * This function calculate the specular light according to Phong's model
     * @param ks Coefficient for specual normally be 0<=ks<=1
     * @param l Vector from light Source to the Point
     * @param n The Normal to the Point
     * @param v The Vector from Camera to the Point
     * @param nShininess The exponent
     * @param lightIntensity The Light intensity
     * @return The specular color of the point
     */
    private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
        // - this is  r = l-r*-2(L*n)*n
        Vector r= l.subtract(n.scale(l.dotProduct(n)*2).getHead());

        double Minus_vr=v.scale(-1).dotProduct(r);

        return lightIntensity.scale(ks*Math.pow(Minus_vr,nShininess)) ;
    }


    /**
     * This function calculate the diffusive light according to Phong's model
     *
     * @param kd   Coefficient for diffusive normally be 0<=kd<=1
     * @param l  Vector from light Source to the Point
     * @param n The Normal to the Point
     * @param lightIntensity  The Light intensity
     * @return Color
     */
    private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
        double factor =kd* Math.abs(l.dotProduct(n));
        return lightIntensity.scale(factor);

    }
    private double transparency(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geopoint.point, lightDirection, n);
        double lightDistance = light.getDistance(geopoint.point);
        var intersections = _scene._geometries.findGeoIntersections(lightRay);
        if (intersections == null) return 1.0;
        double ktr = 1.0;
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(geopoint.point) - lightDistance) <= 0) {
                ktr *= gp.geometry.getMaterial().getkT();
                if (ktr < MIN_CALC_COLOR_K) return 0.0;
            }
        }
        return ktr;
    }

}

