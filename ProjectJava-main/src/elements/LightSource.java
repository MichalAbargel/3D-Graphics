package elements;
/**
 *
 *
 */

import primitives.*;

/**
 * LightSource interface to implement kinds of light
 */

public interface LightSource {
    /**
     *Get Intensity this func returns the correct intensity at specific point
     * @param p Point that we want to know the intensity
     * @return Color
     */
    public Color getIntensity(Point3D p);

    /**
     * this function is used to get the direction vector from the Point (p) to the Light Source
     * @param p The Point you want to get Direction
     * @return the vector of the direction of the light
     */
    public Vector getL(Point3D p);

    double getDistance(Point3D point);
}
