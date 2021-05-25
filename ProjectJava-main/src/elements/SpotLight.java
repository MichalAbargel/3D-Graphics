package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;
/**
 * This class represents spot light.
 * @author Salay Shalom Shuker 311600605 salayshuker@gmail.com
 * @author Shimon Mizrahi 203375563 shimonshimi4@gmail.com
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
        double d = _direction.dotProduct(l);

        return super.getIntensity(p).scale(Math.max(0,d));
    }


}
