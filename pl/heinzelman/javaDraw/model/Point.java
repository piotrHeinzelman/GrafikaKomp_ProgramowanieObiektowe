package pl.heinzelman.javaDraw.model;


import pl.heinzelman.javaDraw.strategy.Matrix;

public class Point {
    protected Double x;
    protected Double y;


    public Point() {}
    public Point(Double x, Double y ) {
        this.setX(x);
        this.setY(y);
    }


    public static Point PointFromFile( String[] split ) {
        Double tmp=null;
        Double x=null;
        Double y=null;
        Double z=null;
        try {
            if (split.length>0) { tmp = Double.parseDouble( split[0] ); if (tmp!=null) { x=tmp; }}
            if (split.length>1) { tmp = Double.parseDouble( split[1] ); if (tmp!=null) { y=tmp; }
               if ( split.length==2 ){ return new Point(x,y); } // Point
            }
            if (split.length>2) {
                tmp = Double.parseDouble( split[2] );
                if (tmp!=null) {  return new Point3D( x,y,tmp ); }
            }

        }
        catch( Throwable e ){}
        return null;
    }


    public Double getX() { return x; }
    public Double getY() { return y; }
    public void setY(Double y) { this.y = y; }
    public void setX(Double x) { this.x = x; }

    public Point move ( Point vector ){ return new Point( x+vector.getX() , y+vector.getY() ); }
    public Point scale ( Double mul ){
        return new Point( x*mul , y*mul );
    }
    public Point mul( Matrix matrix ){ return null; }

    @Override
    public String toString() { return "{x=" + x + ", y=" + y + '}'; }
}
