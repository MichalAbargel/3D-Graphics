package elements;

import primitives.Color;
/**
 * /**
 * This Class represent an Ambient Light inherit From Light which is constant
 * light in the background of thhe image
 *
 */

/**
 * Ambient Light Color
 */
public class AmbientLight extends Light {

    /**
     * Construct The Ambient Light using the super ctor
     * @param IA Intensity of Ambient Light
     * @param Ka Coefficient (Mekadem)
     */
    public AmbientLight(Color IA, Double Ka) { super(IA.scale(Ka)); }

    /**
     * Construct The Ambient Light using the super ctor
     */
    public AmbientLight (){
        super(Color.BLACK);
    }

}
