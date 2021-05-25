package elements;

import primitives.Color;

/**
 *  * class to present Light for scene
 *This abstract Class represent a Light. All types of lights inherit from it
 *and they needs to implement the "getIntesity" method.
 *
 *
 *
 */

abstract class Light {
    protected Color _intensity;

    /**
     * Ctor of Light
     *
     * @param intensity  Color Intensity of The light
     */

    protected Light(Color intensity) {
        this._intensity = intensity;
    }

    /**
     * Get the Light intensity
     *
     * @return Color
     */

    public Color getIntensity() {
        return _intensity;
    }


}
