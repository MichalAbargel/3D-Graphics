package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Util;
import primitives.Vector;
/**
 * This Class represent a Point Light.
 * the lights up at 360 degrees - no direction.

 */



/*
 * @param kc        No attenuation with distance
 * @param kl        attenuation with distance
 * @param kq        attenuation with distance squared - The most influential attenuation
 */
public class PointLight extends Light implements LightSource {

    private Point3D _position;
    private double _kC=1;
    private double _kL=0;
    private double _kQ=0;


    /**
     * C_TOR- Gets 3 attenuations for get better image
     * @param intensity Intensity of Point Light
     * @param position  Position of Point Light
     *                  .
     */

    public PointLight(Color intensity, Point3D position
    ) {
        super(intensity);
        _position = position;
    }



    /**
     * @param p Point that we want to know the intensity
     * @return Intensity of point light
     */
    @Override
    public Color getIntensity(Point3D p) {
        double dSquared = p.distanceSquared(_position);
        double d = Math.sqrt(dSquared);

        return (getIntensity().reduce(_kC + d * _kL + _kQ * dSquared));

    }

    /**
     * @param p The Point you want to get Direction
     * @return Vector that represent  Direction
     */
    @Override
    public Vector getL(Point3D p) {
        try {
            return p.subtract(_position).normalized();
        } catch (IllegalArgumentException e) {
            return null;
        }

    }

    @Override
    public double getDistance(Point3D point) {
        return point.distance(this._position);
    }

    /**
     *set the Kc
     * @param kC
     * there is no No attenuation with distance for kc
     */
    public PointLight setKc(double kC) {
        _kC = kC;
        return this;
    }

    /**
     *  chaining method
     *set the kL
     * there is attenuation with distance
     * @param kL
     * @return PointLight
     */
    public PointLight setKl(double kL) {
        _kL = kL;
        return this;
    }

    /**
     * chaining method
     *set the kQ
     * kQ -> attenuation with distance squared - The most influential attenuation.
     *
     * @param kQ
     * @return PointLight
     */
    public PointLight setKq(double kQ) {
        _kQ = kQ;
        return this;
    }
}
