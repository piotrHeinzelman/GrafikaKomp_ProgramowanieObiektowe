package pl.heinzelman.javaDraw.model;


public class Point3D extends Point {
    protected Double z;

    public Point3D(Double x, Double y, Double z ) {
        super(x, y);
        this.setZ(z);
    }

    public Double getZ() { return z; }
    public void setZ(Double y) { this.z = z; }

}
