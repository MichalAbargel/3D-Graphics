package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import geometries.Intersectable.GeoPoint;

/**
 * Composite class for all Intersectable objects
 */
public class Geometries implements Intersectable {
    private List<Intersectable> _intersectables = null;

    public Geometries(){
        _intersectables = new LinkedList<>();
    }

    public Geometries(Intersectable... intersectables){
        _intersectables = new LinkedList<>();
        add(intersectables);
    }

    public void add(Intersectable... intersectables) {
        for (Intersectable item: intersectables     ) {
            _intersectables.add(item);
        }
        _intersectables.addAll(Arrays.asList(intersectables));

        // TODO - remove if its not neececcery
//        Collection.addAll(_intersectables,intersectables);
    }



    /**
     * Getter
     * @param ray
     * @return list of ntersections
     */
    @Override
    //TODO check correctness
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        List<GeoPoint> intersections = null;


        for (Intersectable geometry : _intersectables) {
            List<GeoPoint> geoIntersections = geometry.findGeoIntersections(ray);

            //if there are elements in geoIntersections – add them to intersections
            if (geoIntersections!=null){
                if (intersections==null) {
                    intersections = new LinkedList<GeoPoint>();
                }
                intersections.addAll(geoIntersections);
            }

        }

        return intersections;

    }
}
