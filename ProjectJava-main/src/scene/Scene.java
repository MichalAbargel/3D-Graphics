package scene;
import elements.*;
import geometries.Geometries;
import primitives.Color;
import java.util.LinkedList;
import java.util.List;


/**
 * This Class represent Scene - A complete set of objects that build image
 */

public class Scene {
    public final String _name;
    public Color _background = Color.BLACK; // default value
    public AmbientLight _ambientLight = new AmbientLight();
    public Geometries _geometries;
    public List<LightSource> lights;

    /**
     * receive name and build new Geometries
     * @param _sceneName name of the scene
     */
    public Scene(String _sceneName){
        this._name=_sceneName;
        _geometries=new Geometries();
        lights = new LinkedList<>();
    }

    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }
    /**
     *
     * @param ambientLight receive ambit light
     * @return Scene
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        _ambientLight = ambientLight;
        return this;
    }

    /**
     *
     * @param background receive background
     * @return Scene
     */

    public Scene setBackground(Color background) {
        _background = background;
        return this;
    }

    /**
     *	 * add list of Intractable to the list
     * @param geometries receive  geometries
     * @return Scene
     */

    public Scene setGeometries(Geometries geometries) {
        _geometries = geometries;
        return this;
    }

}