package Primitives;

import java.util.List;
import java.util.Objects;

import static Primitives.Utile.isZero;

public class Ray {
    point3D p0;
    Vector dir;

    public Ray(point3D p0, Vector dir) {
        dir.normalize();
        this.p0 = p0;
        this.dir = dir;
    }

    public point3D getP0() {
        return p0;
    }

    public Vector getDir() {
        return dir;
    }

    @Override
    public String toString() {
        return "Ray{" +
                this.p0.toString()+
                ", dir="+ this.dir.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return Objects.equals(p0, ray.p0) && Objects.equals(dir, ray.dir);
    }
    public point3D findClosestPoint(List<point3D> point3DCollection){
        point3D minP = null;

        if(point3DCollection == null){
            return null;
        }

        double distance = Double.POSITIVE_INFINITY;

        for (point3D point:point3DCollection) {
            double temp = point.distance(this.p0);
            if(temp<distance){
                distance=temp;
                minP=point;
            }
        }
        return minP;
    }
    public point3D getPoint(double t){
        return isZero(t) ? p0 : (p0).add(dir.scale(t));
    }


}
