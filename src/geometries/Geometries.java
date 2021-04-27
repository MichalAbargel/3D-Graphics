package geometries;

import Primitives.Ray;
import Primitives.point3D;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable {

    List<Intersectable> intersectables;

    public Geometries(List<Intersectable> geometries) {
        this.intersectables = geometries;
    }
    public Geometries() {
        this.intersectables = new ArrayList<Intersectable>();
    }
    public void add(Intersectable... geometries){
        for (var geometry :geometries) {
            this.intersectables.add(geometry);
        }
    }

    @Override
    public List<point3D> findIntsersections(Ray ray) {
        List<point3D> result = null;
        for(Intersectable element: this.intersectables)
        {
            List<point3D>elemList = element.findIntsersections(ray);
            if(elemList != null){
                if(result == null)
                {
                    result = new ArrayList<>();
                }

                result.addAll(elemList);
            }
        }

        return result;
    }
}
