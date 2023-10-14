package pl.heinzelman.javaDraw.model;


import pl.heinzelman.javaDraw.strategy.Matrix;

public class Point3D extends Point {
    protected Double z;
    protected Double d;

    public Point3D(Double x, Double y, Double z ) {
        super(x, y);
        this.setZ(z);
    }

    public Point3D( Double x, Double y, Double z, Double d){
        super(x,y);
        this.z=z;
        this.d=d;
    }



    public Double getZ() { return z; }
    public void setZ(Double y) { this.z = z; }
    public Double getD() { return d; }
    public void setD(Double d) { this.d = d; }


    public Point mul( Matrix matrix ){
        Double[][] m = matrix.getM();

        Double _x = x*m[0][0] + y*m[0][1] + z*m[0][2] + d*m[0][3];
        Double _y = x*m[1][0] + y*m[1][1] + z*m[1][2] + d*m[1][3];
        Double _z = x*m[2][0] + y*m[2][1] + z*m[2][2] + d*m[2][3];
        Double _d = x*m[3][0] + y*m[3][1] + z*m[3][2] + d*m[3][3];

        return new Point3D( _x/_d, _y/_d, _z/_d, 1.0);
    }




}
