package pl.heinzelman.javaDraw.strategy;

import pl.heinzelman.javaDraw.model.Point3D;

import java.util.Arrays;



public class Matrix {
    private Double m[][] = new Double[4][4];  // [x][y]

    public Matrix() {
        for (int i=0;i<4;i++){
            for (int j=0;j<4;j++){
             m[i][j]= (i==j)? 1.0 : 0.0;
            }
        }
    }

    public Double[][] getM() { return m; }
    public Double getValue(int i,int j){
        if (i>0 && i<4 && j>0 && j<4) {
            return m[i][j];
        }
        return null;
    }

    public void setValue(int i,int j, Double valueDouble) {
        if (i>0 && i<4 && j>0 && j<4) {
            m[i][j]=valueDouble;
        }
    }


    @Override
    public String toString() {
        StringBuffer out = new StringBuffer();
        out.append( "| "+ m[0][0] + " : " + m[1][0] + " : " + m[2][0] + " : " + m[3][0] + " |\n");
        out.append( "| "+ m[0][1] + " : " + m[1][1] + " : " + m[2][1] + " : " + m[3][1] + " |\n");
        out.append( "| "+ m[0][2] + " : " + m[1][2] + " : " + m[2][2] + " : " + m[3][2] + " |\n");;
        out.append( "| "+ m[0][3] + " : " + m[1][3] + " : " + m[2][3] + " : " + m[3][3] + " |\n");

        return out.toString();
    }


    public static Matrix getMoveMatrix( Double x, Double y, Double z ){
        Matrix matrix = new Matrix();
        Double[][] m = matrix.getM();
        m[3][0]=x;
        m[3][1]=y;
        m[3][2]=z;
        return matrix;
    }

    public static Matrix getRotateXMatrix( Double phi ){
        Matrix matrix = new Matrix();
        Double[][] m = matrix.getM();
        m[1][1]= Math.cos(phi);    m[2][1]=-Math.sin(phi);
        m[1][2]= Math.sin(phi);    m[2][2]= Math.cos(phi);
        return matrix;
    }

    public static Matrix getRotateYMatrix( Double phi ){
        Matrix matrix = new Matrix();
        Double[][] m = matrix.getM();
        m[0][0]= Math.cos(phi);    m[2][0]= Math.sin(phi);
        m[0][2]=-Math.sin(phi);    m[2][2]= Math.cos(phi);
        return matrix;
    }

    public static Matrix getRotateZMatrix( Double phi ){
        Matrix matrix = new Matrix();
        Double[][] m = matrix.getM();
        m[0][0]= Math.cos(phi);    m[1][0]=-Math.sin(phi);
        m[0][1]= Math.sin(phi);    m[1][1]= Math.cos(phi);
        return matrix;
    }

    public Matrix mul( Matrix smatrix ){
        Matrix outMatrix = new Matrix();
        Double[][] out = outMatrix.getM();

        Double[][] m1 = this.getM();
        Double[][] m2 = smatrix.getM();

        for ( int j=0;j<4;j++) {
            for ( int i=0;i<4;i++) {
                out[i][j] = m1[0][j] * m2[i][0] + m1[1][j] * m2[i][1] + m1[2][j] * m2[i][2] + m1[3][j] * m2[i][3];
            }
        }
        return outMatrix;
    }


    public Point3D mul( Point3D point ){
        Point3D out = new Point3D();

        Double[][] m = this.getM();
        Double x = point.getX();
        Double y = point.getY();
        Double z = point.getZ();
        Double w = point.getW();

        Double _x = x*m[0][0] + y*m[1][0] + z*m[2][0] + w*m[3][0];
        Double _y = x*m[0][1] + y*m[1][1] + z*m[2][1] + w*m[3][1];
        Double _z = x*m[0][2] + y*m[1][2] + z*m[2][2] + w*m[3][2];
        Double _w = x*m[0][3] + y*m[1][3] + z*m[2][3] + w*m[3][3];

        return new Point3D( _x/_w , _y/_w , _z/_w , 1.0  );
    }

}
