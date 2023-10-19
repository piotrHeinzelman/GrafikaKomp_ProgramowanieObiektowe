package pl.heinzelman.javaDraw.model;

import pl.heinzelman.javaDraw.strategy.Matrix;

/**
 * Implementacja punktu 3D
 *
 *  @author Piotr Heinzelman
 */
public class Point3D extends Point {
    protected Double z;
    protected Double w;

    public Point3D() {
        super();
    }

    public Point3D(Double x, Double y, Double z ) {
        super(x, y);
        this.z=z;
        this.w=1.0;
    }

    public Point3D( Double x, Double y, Double z, Double w){
        super(x,y);
        this.z=z;
        if (w==null) w=1.0;
        this.w=w;
    }



    public Double getZ() { return z; }
    public void setZ(Double y) { this.z = z; }
    public Double getW() { return w; }
    public void setW(Double w) { this.w = w; }

    /**
     * mnożenie punktu przez macierz
     *
     * @param matrix - macierz translacji
     * @return - zwraca przesunięty punkt.
     */
    public Point3D mul( Matrix matrix ){
        Double[][] m = matrix.getM();

        Double _x = x*m[0][0] + y*m[0][1] + z*m[0][2] + w *m[0][3];
        Double _y = x*m[1][0] + y*m[1][1] + z*m[1][2] + w *m[1][3];
        Double _z = x*m[2][0] + y*m[2][1] + z*m[2][2] + w *m[2][3];
        Double _w = x*m[3][0] + y*m[3][1] + z*m[3][2] + w *m[3][3];
        //System.out.println( _w );
        return new Point3D( _x, _y, _z, _w );
    }


    @Override
    public String toString() {
        return "{x=" + x + ", y=" + y + ", z=" +z+", w=" + w + '}';
    }
}
