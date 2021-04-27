package elements;

import Primitives.Ray;
import Primitives.Vector;
import Primitives.point3D;

import static Primitives.Utile.isZero;

public class Camera {
    point3D center;
    Vector to;
    Vector right;
    Vector up;
    private double _width;
    private double _height;
    private double _distance;

    public point3D getCenter() {
        return center;
    }

    public Vector getTo() {
        return to;
    }

    public Vector getRight() {
        return right;
    }

    public Vector getUp() {
        return up;
    }

    public double get_width() {
        return _width;
    }

    public double get_height() {
        return _height;
    }

    public double get_distance() {
        return _distance;
    }

    public Camera(point3D center, Vector to, Vector up) {
        if (!isZero(to.dotProduct(up))) {
            throw new IllegalArgumentException(" vto and vup are not orthogonal");
        }
        this.center = new point3D(center.getX(), center.getY(), center.getZ());
        this.to = to.normalize();
        this.up = up.normalize();
        this.right=this.to.crossProduct(this.up).normalize();
    }
    public Camera setViewPlaneSize(double width, double height){
        this._width=width;
        this._height=height;
        return  this;
    }
    public Camera setDistance(double distance){
        try {
            if (isZero(distance)) {
                throw new IllegalArgumentException("can not be 0");
            }
            _distance = distance;
        } catch (Exception e) {
            System.out.println(" distans can not be 0");
        }
        return  this;
    }
    public Ray constructRayThroughPixel(int nX, int nY, int j, int i){
        point3D pc = this.center.add(this.to.scale(_distance));

        if (isZero(nX) || isZero(nY)) {
            throw new IllegalArgumentException("can not be 0");
        }

        double ry = _height / nY;
        double rx = _width / nX;


        double yi = -(((i - ((nY - 1) / 2d)))*ry);
        double xj = ((j - ((nX - 1) / 2d)))*rx;

        point3D Pij = pc;


        if (!isZero(xj)) {
            Pij=Pij.add(this.right.scale(xj));
        }


        if (!isZero(yi)) {
            Pij=Pij.add(this.up.scale(yi));
        }


        Vector v = null;
        try {
            v = Pij.subtract(this.center);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }


        return new Ray(this.center, v);

    }



}
