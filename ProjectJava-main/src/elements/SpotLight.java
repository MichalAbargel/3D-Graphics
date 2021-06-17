package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;
/**

 */


public class SpotLight extends PointLight{

    private Vector _direction;

    /**
     *
     * @param intensity Intensity of spot light
     * @param position  Position of spot light
     * @param direction Direction of spot light
     *                  attenuation.
     */


    public SpotLight(Color intensity, Point3D position,Vector direction/*, double kc, double kl, double kq*/ ) {
        super(intensity, position/*, kc, kl, kq*/);
        this._direction=direction.normalized();
    }



    /**
     * @param p Point that we want to know the intensity
     * @return Color
     */

    @Override
    public Color getIntensity(Point3D p) {
        Vector l = getL(p);
        double d = l.dotProduct(this._direction);
        return super.getIntensity(p).scale(Math.max(0,d));
    }


}
