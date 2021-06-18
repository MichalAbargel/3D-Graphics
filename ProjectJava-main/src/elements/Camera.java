package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.awt.color.ICC_ColorSpace;
import java.util.ArrayList;
import java.util.List;

import static primitives.Util.isZero;


/* class present camera in 3D cartesian coordinate */

public class Camera {

    public   int SuperSamplingNumber=3; // Default

    public  Camera setSuperSamplingNumber(int superSamplingNumber) {
        this.SuperSamplingNumber = superSamplingNumber;
        return this;
    }

    Point3D _p0;
    Vector _vTo;
    Vector _vUp;
    Vector _vRight;

    /* physical dimensions of the "screen" */
    private double _width;
    private double _height;
    private double _distance;       // distance between camera and the view plane

    /* Getters */

    private Point3D getP0() {
        return _p0;
    }

    private Vector getvTo() {
        return _vTo;
    }

    private Vector getvUp() {
        return _vUp;
    }

    private Vector getvRight() {
        return _vRight;
    }

    private double getWidth() {
        return _width;
    }

    private double getHeight() {
        return _height;
    }

    private double getDistance() {
        return _distance;
    }

    /**
     * @param p0  eye of camera
     * @param vTo vector opposite to Z axis
     * @param vUp vector pointing towards Y axis
     */
    public Camera(Point3D p0, Vector vTo, Vector vUp) {

        if (!isZero(vTo.dotProduct(vUp))) {
            throw new IllegalArgumentException(" vto and vup are not orthogonal");
        }

        _p0 = new Point3D(p0.getX(), p0.getY(), p0.getZ());

        _vTo = vTo.normalized();
        _vUp = vUp.normalized();

        _vRight = _vTo.crossProduct(_vUp).normalize();
    }

    /**
     * @param width
     * @param height
     * @return
     */
    public Camera setViewPlaneSize(double width, double height) {
        _width = width;
        _height = height;
        return this;
    }
    /*  function distance */

    public Camera setDistance(double distance) {
        try {
            if (isZero(distance)) {
                throw new IllegalArgumentException("can not be 0");
            }
            _distance = distance;
        }
        catch (Exception e) {
            System.out.println(" distance can not be 0");
        }
        return this;
    }
    /*  function that create ray with point */

    public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {

        Point3D pc = _p0.add(_vTo.scale(_distance));

        if (isZero(nX) || isZero(nY)) {
            throw new IllegalArgumentException("can not be 0");
        }

        double ry = _height / nY;
        double rx = _width / nX;



        double yi = -(((i - ((nY - 1) / 2d)))*ry);
        double xj = ((j - ((nX - 1) / 2d)))*rx;

        Point3D Pij = pc ;


        if (!isZero(xj)) {
            Pij=Pij.add(_vRight.scale(xj));
        }


        if (!isZero(yi)) {
            Pij=Pij.add(_vUp.scale(yi));
        }


        Vector v = Pij.subtract(_p0);


        return new Ray(_p0, v.normalize());

    }

    public List<Ray> constructRayThroughPixelSuperSampling(int nX, int nY, int x, int y){
        List<Ray> rays=new ArrayList<Ray>();

        for (int i=0; i<SuperSamplingNumber;i++){
            for (int j=0; j<SuperSamplingNumber;j++){

                rays.add(this.constructRayThroughPixelGrid(nX,nY,x,y,i,j));
            }
        }
        return rays;
    }
    public Ray constructRayThroughPixelGrid(int nX, int nY, int x, int y, int i, int j){

        Point3D pc= this.getCenterOfPixel(nX,nY,y,x);

        double Rx=_width/nX/SuperSamplingNumber;
        double Ry=_height/nY/SuperSamplingNumber;
        Point3D p=this.getCenterOfPixelP(pc,Rx, Ry,i, j);
        return new Ray(_p0,p.subtract(_p0).normalize());
    }


    public Point3D getCenterOfPixel(int nX, int nY, int j, int i){
        Point3D pc = _p0.add(_vTo.scale(_distance));

        if (isZero(nX) || isZero(nY)) {
            throw new IllegalArgumentException("can not be 0");
        }

        double ry = _height / nY;
        double rx = _width / nX;



        double yi = -(((i - ((nY - 1) / 2d)))*ry);
        double xj = ((j - ((nX - 1) / 2d)))*rx;

        Point3D Pij = pc ;


        if (!isZero(xj)) {
            Pij=Pij.add(_vRight.scale(xj));
        }


        if (!isZero(yi)) {
            Pij=Pij.add(_vUp.scale(yi));
        }
        return Pij;
    }

    public Point3D getCenterOfPixelP(Point3D pc, double Rx, double Ry, int i, int j){

        double yi = (i - (SuperSamplingNumber / 2.0))*Ry+Ry/2.0;
        double xj = (j - (SuperSamplingNumber / 2.0))*Rx+Rx/2.0;

        Point3D Pij = pc ;


        if (!isZero(xj)) {
            Pij=Pij.add(_vRight.scale(xj));
        }


        if (!isZero(yi)) {
            Pij=Pij.add(_vUp.scale(-yi));
        }
        return Pij;
    }
}
