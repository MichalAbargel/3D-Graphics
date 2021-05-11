package elements;

import Primitives.Color;

public class AmbientLight {
    private Color intensity;

    public Color getIntensity() {
        return intensity;
    }

    public AmbientLight(Color IA, double KA ) {
        this.intensity = new Color(IA.scale(KA));
    }
}
