package Primitives;

import java.util.Objects;

import static Primitives.point3D.zero;

public class Vector {
    point3D head;

    public Vector(double x, double y, double z)  {

        if (new point3D(x,y,z).equals(zero))
            throw new IllegalArgumentException("vector head can not be (0,0,0)");
        this.head = new point3D(x, y, z);
    }

    public Vector(Coordinate x, Coordinate y, Coordinate z) {
        point3D p=new point3D(x, y, z);
        if(p.equals(zero)) {
            throw new IllegalArgumentException("vector head can not be (0,0,0)");
        }
        this.head = p;

    }

    public Vector(point3D head) {
        if (head.equals(zero)) {
            throw new IllegalArgumentException("vector head can not be (0,0,0)");
        }
        this.head = head;
    }

    public point3D getHead() {
        return head;
    }

    @Override
    public String toString() {
        return "Vector{" +
                "head=" + head +
                '}';
    }


    public double dotProduct(Vector vector) {

        return vector.head.getX()*(this.head.getX()) +
                vector.head.getY()*(this.head.getY()) + vector.head.getZ()*(this.head.getZ());


    }

    public Vector crossProduct(Vector vector) {

        return new Vector(this.head.getY()*(vector.head.getZ()) - this.head.getZ()*(vector.head.getY()),
                this.head.getZ()*(vector.head.getX()) - this.head.getX()*(vector.head.getZ()),
                this.head.getX()*vector.head.getY() - this.head.getY()*(vector.head.getX()));


    }

    public Vector subtruct(Vector vector) throws IllegalAccessException {
        if (vector.equals(this)) {
            throw new IllegalAccessException("illagal vector (0,0,0)");
        }
        double u1 = head.X.coord;
        double u2 = head.X.coord;
        double u3 = head.X.coord;
        double v1 = vector.head.X.coord;
        double v2 = vector.head.X.coord;
        double v3 = vector.head.X.coord;
        return new Vector(new point3D(u1 - v1, u2 - v2, u3 - v3));
    }
    //Add two vectors and return new vector of the sum.
    public Vector add(Vector vector)  {

        Vector v=new Vector(this.head);
        v.scale(-1);
        if (vector.equals(v))
            throw new IllegalArgumentException("illegal vector (0,0,0)");
        double u1 = head.X.coord;
        double u2 = head.X.coord;
        double u3 = head.X.coord;
        double v1 = vector.head.X.coord;
        double v2 = vector.head.X.coord;
        double v3 = vector.head.X.coord;
        return new Vector(new point3D(u1 + v1, u2 + v2, u3 + v3));
    }
    //Scale the vector whit scalar and  return new vector of the multiplication.
    public Vector scale(double scalar) {
        return new Vector(head.getX() * scalar,head.getY() * scalar,head.getZ() * scalar);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return this.head.equals(vector.head);
    }

    @Override
    public int hashCode() {
        return Objects.hash(head);
    }
    //return the lenght of the vector in power.
    public double lengthSquared() {
        return  this.head.getX()*(this.head.getX()) +
                this.head.getY()*(this.head.getY()) +
                this.head.getZ()*(this.head.getZ());
    }
    //return the lenght of the vector.
    public double length() {
        return Math.sqrt(this.lengthSquared());
    }
    //Normalize the vector and change the head
    public Vector normalize() {
        this.head = new point3D(this.head.getX()/(this.length()),
                this.head.getY()/(this.length()),
                this.head.getZ()/(this.length()));
        return this;
    }
    //Return new Normalized vector.
    public Vector normalized()  {
        return new Vector(this.head.getX(), this.head.getY(), this.head.getZ()).normalize();
    }

}

