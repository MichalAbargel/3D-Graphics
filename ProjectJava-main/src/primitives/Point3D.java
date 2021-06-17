package primitives;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;



/**
 * his class represent a point for 3 dimensions X, Y and Z
 */
public class Point3D {
//3 coordinates to represent a point
    final Coordinate _x;
    final Coordinate _y;
    final Coordinate _z;

    public static Point3D ZERO = new Point3D(0d, 0d, 0d);
    public Point3D _head;

    /**
     *
     * @param x Coordinate of a point
     * @param y Coordinate of a point
     * @param z Coordinate of a point
     */
    public Point3D(Coordinate x, Coordinate y, Coordinate z) {
        this(x.coord, y.coord, z.coord);
    }


    /**
     * Constructor
     * @param x Constructor Point3D with double
     * @param y Constructor Point3D with double
     * @param z Constructor Point3D with double
     */
    public Point3D(double x, double y, double z) {
        _x = new Coordinate(alignZero(x));
        _y = new Coordinate(alignZero(y));
        _z = new Coordinate(alignZero(z));
    }



    /**
     * Getters
     * @return X or Y or Z
     */
    public Coordinate getX() {
        return _x;
    }

    public Coordinate getY() {
        return _y;
    }

    public Coordinate getZ() {
        return _z;
    }

    /**
     * this function compare between 2 Point3D, if they are equal the function
     * @param o the Object
     * @return 0(zero) and -1(minus 1) if they aren't equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point3D point3D = (Point3D) o;
        return _x.equals(point3D._x) &&
                _y.equals(point3D._y) &&
                _z.equals(point3D._z);
    }

    @Override
    public String toString() {
        return "(" + _x + " , " + _y + " ," + _z + " )";
    }

    /* distanceSquared
     * return the distance squared between 2 Point3D objects
     *  */

    public double distanceSquared(Point3D other) {
        final double x1 = _x.coord;
        final double y1 = _y.coord;
        final double z1 = _z.coord;
        final double x2 = other._x.coord;
        final double y2 = other._y.coord;
        final double z2 = other._z.coord;

        return ((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1) + (z2 - z1) * (z2 - z1));
    }

    /* distance
     * return the distance between 2 Point3D objects
     *  */

    public double distance(Point3D Point3D) {
        return Math.sqrt((distanceSquared(Point3D)));
    }


    /**
     * subtract
     * @param pt2  get 2 Point3D objects
     * @return new vector
     */
    public Vector subtract(Point3D pt2) {
        if (pt2.equals(this)) {
            throw new IllegalArgumentException("vector can not (0,0,0)");
        }
        return new Vector(new Point3D(
                _x.coord - pt2._x.coord,
                _y.coord - pt2._y.coord,
                _z.coord - pt2._z.coord
        ));
    }



    /**
     * add
     * @param vector add Point3D to vector
     * @return new Point3D
     */
    public Point3D add(Vector vector) {

        return (new Point3D(
                _x.coord + vector._head._x.coord,
                _y.coord + vector._head._y.coord,
                _z.coord + vector._head._z.coord
        ));

    }
}
