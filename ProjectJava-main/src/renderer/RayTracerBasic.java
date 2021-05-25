package renderer;
import elements.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;
import static primitives.Util.alignZero;

/**
 * @author Salay Shalom Shuker 311600605 salayshuker@gmail.com
 * @author Shimon Mizrahi 203375563 shimonshimi4@gmail.com
 */

public class RayTracerBasic extends RayTracerBase {

    /**
     * receive scene and use the father class - abstract RayTracerBase
     * @param scene to receive
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * search intersections between the ray and the 3D dimension of the scene
     *
     * @param ray to find intersections
     * @return Color - return from the closestPoint
     */
    @Override
    public Color traceRay(Ray ray) {
        java.util.List<GeoPoint> intersections = _scene._geometries.findGeoIntersections(ray);
        if (intersections != null) {
            GeoPoint closestPoint = ray.getClosestGeoPoint(intersections);
            return calcColor(closestPoint,ray);
        }

        // the ray didn't intersect any geometrical object
        return _scene._background;
    }


    /**
     * This function and calcLocalEffects function and calcSpecular and calcDiffusive function
     * calculates the color of point with all of kinds of lights. We
     * use Phong's model of light Together To got the precise color on the
     * intersection point. We combine The Diffuse light and Specular Light and
     * emission light
     * @param point receive point3D and return color
     * @param ray the ray from viewPlane
     * @return in this part of the project this return only ambient Light light of the scene
     */

    private Color calcColor(GeoPoint point, Ray ray) {
        return _scene._ambientLight.getIntensity()
                .add(point.geometry.getEmission()
                        .add(calcLocalEffects(point, ray)));
    }


    /**
     * this fucntion calculate Local Effects
     * @param intersection get the point to calculate the effect next to her
     * @param ray get the Ray and Calculate , like we study in the course
     * @return Color
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
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
        Color color = Color.BLACK;
        for (LightSource lightSource : _scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Color lightIntensity = lightSource.getIntensity(intersection.point);
                color = color.add(calcDiffusive(kd, l, n, lightIntensity), calcSpecular(ks, l, n, v, nShininess, lightIntensity));
            }
        }
        return color;

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


}
