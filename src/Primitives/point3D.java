package Primitives;
import java.awt.*;
import java.util.Objects;



public class point3D {
    final Coordinate X;
    final Coordinate Y;
    final Coordinate Z;

    public point3D(Coordinate x, Coordinate y, Coordinate z) {
        X = new Coordinate(x.coord);
        Y = new Coordinate(y.coord);
        Z = new Coordinate(z.coord);
    }

    public point3D(double x, double y, double z) {
        X=new Coordinate(x);
        Y=new Coordinate(y);
        Z=new Coordinate(z);
    }

    public Double getX() {
        return X.coord;
    }

    public double getY() {
        return Y.coord;
    }

    public double getZ() {
        return Z.coord;
    }
    public static point3D zero=new point3D(0d,0d,0d);

    @Override
    public String toString() {
        return  "X=" + X + ", Y=" + Y + ", Z=" + Z;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        point3D point3D = (point3D) o;
        return X.equals(point3D.X) && Y.equals(point3D.Y) && Z.equals(point3D.Z);
    }

    @Override
    public int hashCode() {
        return Objects.hash(X, Y, Z);

    }
    public Vector subtract(point3D point)  {
        if(point.equals(this))
            throw new IllegalArgumentException("cannot create Vector to Point(0,0,0)");
        Coordinate x= new Coordinate(this.getX()-(point.getX())) ;
        Coordinate y= new Coordinate(this.getY()-(point.getY())) ;
        Coordinate z= new Coordinate(this.getZ()-(point.getZ())) ;
        return new Vector(new point3D(x, y, z));
    }
    public point3D add(Vector vector){
        return new point3D
                (this.X.coord+vector.getHead().getX(),this.Y.coord+vector.getHead().getY(),
                        this.Z.coord+vector.getHead().getZ());


    }
    public double distanceSquared(point3D point){
        double x=this.getX()+(point.getX());
        double y=this.getY()+(point.getY());
        double z=this.getZ()+(point.getZ());
        return x*x+y*y+z*z;
    }
    public double distance(point3D point){

        return Math.sqrt(this.distanceSquared(point));
    }


}

