package pl.heinzelman.javaDraw.model;

import java.util.ArrayList;
import java.util.List;

public class Model {
    List<Point> points = new ArrayList<>();
    List<Pixel> pixels = new ArrayList<>();
    Double factor=100.0;

    public void clearPoint(){
        points = new ArrayList<>();
    }

    public void addPoint( String[] split ){
        Point p = Point.PointFromFile( split );
        if (p!=null) {
            points.add( p );
        }
    }

    public void clearPixels(){
        pixels = new ArrayList<>();
    }

    public void createPixelFromPoints(){
        for ( Point p : points ){
              Double lx = p.getX()*factor;
              Double ly = p.getY()*factor;
              Pixel pix = new Pixel( lx.longValue(), ly.longValue() );
            System.out.println( pix );
            pixels.add( pix );
        }

    }

    public Double getFactor() { return factor; }
    public void setFactor(Double factor) { this.factor = factor; }

}
