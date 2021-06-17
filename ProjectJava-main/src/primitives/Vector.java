package primitives;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

import java.util.Objects;

/*  this class represent a vector for 3 dimensions X, Y and Z */

public class Vector {

    public Point3D _head;

    public Point3D getHead() {
        return _head;
    }


    /**
     * Constructor
     * @param head Point3D head of the vector
     */
    public Vector(Point3D head) {
        if (head.equals(Point3D.ZERO)) {
            throw new IllegalArgumentException("vector head can not be (0,0,0)");
        }
        _head = head;
    }


    /**
     * Constractor
     * @param x vector with Coordinate x
     * @param y vector with Coordinate y
     * @param z vector with Coordinate z
     */
    public Vector(Coordinate x, Coordinate y, Coordinate z) {
        _head = new Point3D(x.coord, y.coord, z.coord);
        if (_head.equals(Point3D.ZERO)) {
            throw new IllegalArgumentException("vector head can not be (0,0,0)");
        }
    }
    /* Constractor */

    /**
     * Constractor
     * @param x vector with double -x
     * @param y vector with double -y
     * @param z  vector with double -z
     */
    public Vector(double x, double y, double z) {
        _head = new Point3D(x, y, z);
        if (_head.equals(Point3D.ZERO)) {
            throw new IllegalArgumentException("vector head can not be (0,0,0)");
        }
    }
    /*copy cot-r*/
    /*public Vector(Vector v) {
        this(v._head);
    }*/

    /* dotProduct
     * return
     *  */

    /**
     * dotProduct
     * @param v Vector to do with him dotProduct
     * @return ((u1 * v1) + (u2 * v2) + (u3 * v3))
     */
    public double dotProduct(Vector v) {
        double u1 = _head._x.coord;
        double u2 = _head._y.coord;
        double u3 = _head._z.coord;
        double v1 = v._head._x.coord;
        double v2 = v._head._y.coord;
        double v3 = v._head._z.coord;

        return ((u1 * v1) + (u2 * v2) + (u3 * v3));
    }



    /**
     * crossProduct
     * @param v Vector to do with him crossProduct
     * @return new Vector from 2 vectors
     */
    public Vector crossProduct(Vector v) {

        double u1 = _head._x.coord;
        double u2 = _head._y.coord;
        double u3 = _head._z.coord;
        double v1 = v._head._x.coord;
        double v2 = v._head._y.coord;
        double v3 = v._head._z.coord;

        return (new Vector(new Point3D(
                u2 * v3 - u3 * v2,
                u3 * v1 - u1 * v3,
                u1 * v2 - u2 * v1
        )));
    }
    /* Add
     * return add 2 vectors to new vector
     *  */

    /**
     * Add
     * @param v  Vector to add
     * @return add 2 vectors to new vector
     */
    public Vector add(Vector v) {
        double u1 = _head._x.coord;
        double u2 = _head._y.coord;
        double u3 = _head._z.coord;
        double v1 = v._head._x.coord;
        double v2 = v._head._y.coord;
        double v3 = v._head._z.coord;

        return (new Vector(
                u1 + v1,
                u2 + v2,
                u3 + v3
        ));
    }

    /**
     * subtract
     * @param v
     * @return subtract one vector from second vector to new vector
     */
    public Vector subtract(Point3D v) {
        double u1 = _head._x.get();
        double u2 = _head._y.get();
        double u3 = _head._z.get();
        double v1 = v._x.get();
        double v2 = v._y.get();
        double v3 = v._z.get();
        if(isZero(u1-v1)&&(isZero(u2-v2)&&(isZero(u3-v3))))
            throw new IllegalArgumentException("vector head can not be (0,0,0)");
        return (new Vector(
                u1 - v1,
                u2 - v2,
                u3 - v3
        ));
    }

    /**
     * scale
     * @param t multiplication vector with number
     * @return  ector with new parameters
     */
    public Vector scale(double t) {
        double u1 = _head._x.coord;
        double u2 = _head._y.coord;
        double u3 = _head._z.coord;

        return (new Vector(u1 * t, u2 * t, u3 * t));

    }


    /**
     * lengthSquared
     * @return the length of vector squared
     */
    public double lengthSquared() {
        double u1 = _head._x.coord;
        double u2 = _head._y.coord;
        double u3 = _head._z.coord;

        return ((u1 * u1) + (u2 * u2) + (u3 * u3));
    }


    /**
     * length
     * @return  the length of vector
     */
    public double length() {
        return (Math.sqrt(lengthSquared()));
    }

    /**
     * normalize
     * @return vector normalize
     */
    public Vector normalize() {
        double u1 = _head._x.coord;
        double u2 = _head._y.coord;
        double u3 = _head._z.coord;
        double length = Math.sqrt(u1 * u1 + u2 * u2 + u3 * u3);
        {
            _head = new Point3D(
                    new Coordinate(u1 / length),
                    new Coordinate(u2 / length),
                    new Coordinate(u3 / length)
            );
            return this;
        }
    }



    /**
     * normalized
     * @return vector normalized
     */
    public Vector normalized() {
        return new Vector(this._head).normalize();
    }

    /* this function compare if the 2 vectors are equal. */

    /**
     * this function compare if the 2 vectors are equal
     * @param o
     * @return equal Vector
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return _head.equals(vector._head);
    }

    @Override
    public String toString() {
        return "Vector{" +
                "_head=" + _head +
                '}';
    }

}


