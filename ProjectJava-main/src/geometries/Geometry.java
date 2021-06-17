package geometries;

import primitives.*;


/**
 * Geometry interface for Geometries package.
 *
 */


public abstract class Geometry implements Intersectable {
    /**
     * TODO - Notes
     *
     */
    protected Color _emission= Color.BLACK;
    private Material _material= new Material();

    /**
     * Get normal function.
     * @param point of geometry shape.
     * @return the normal of each geometry shape.
     */
    public abstract Vector getNormal(Point3D point);

    /**
     * TODO - notes
     * @return
     */
    public Color getEmission() {
        return _emission;
    }

    /**
     * Get the Emmssion
     * @return primitives.Color
     */
    public Geometry setEmission(Color emission) {
        this._emission = emission;
        return this;
    }


    /**
     * Default Ctor: init _emision to Black and init Material with default value
     */

    public Geometry() {
        this(Color.BLACK);
    }

    /**
     * Ctor with emission parameter set and init Material with default value
     *
     * @param emission
     */
    public Geometry(Color emission) {
        this(emission, new Material());
    }

    /**
     * ctor the get material and Color
     *
     * @param emission
     * @param material
     */
    public Geometry(Color emission, Material material) {
        _emission = emission;
        _material = material;
    }

    /**
     * chaining method  for material
     * @param material
     * @return Geometry
     */
    public Geometry setMaterial(Material material) {
        _material = material;
        return this;
    }

    /**
     *
     * @return _material
     */
    public Material getMaterial() {
        return _material;
    }
}
