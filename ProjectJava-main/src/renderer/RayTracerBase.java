package renderer;

import primitives.Color;
import primitives.Ray;
/**
 * @author Salay Shalom Shuker 311600605 salayshuker@gmail.com
 * @author Shimon Mizrahi 203375563 shimonshimi4@gmail.com
 */
import scene.Scene;

/**
 * abstract class RayTracerBase
 */
public abstract class RayTracerBase {

    protected Scene _scene;

    // C-Tor for the abstract class RayTracerBase
    public RayTracerBase(Scene scene) {
        _scene = scene;
    }

    /**
     *  abstract Method
     * @param ray receive ray and return color
     * @return Color
     */
    public abstract Color traceRay(Ray ray);


}
