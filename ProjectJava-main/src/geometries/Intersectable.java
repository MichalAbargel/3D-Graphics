package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Interpretable interface, For scanning rays.
 */
public interface Intersectable {
    /**
     * find all intersections points from the ray.
     * @param ray the famous  Ray pointing to.
     * @return intersection points.
     */

    default List<Point3D> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream().map(gp -> gp.point).collect(Collectors.toList());
    }






    /**
     * GeoPoint ->
     *
     */
    public static class GeoPoint {
        public Geometry geometry;
        public Point3D point;

        /**
         * TODO
         * @param geometry geometry is reference to current geometry
         * @param point point is reference to current point on geometry
         */
        public GeoPoint(Geometry geometry, Point3D point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return geometry.equals(geoPoint.geometry) && point.equals(geoPoint.point);
        }



    }

    List<GeoPoint> findGeoIntersections(Ray ray);


}
