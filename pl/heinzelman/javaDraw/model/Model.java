package pl.heinzelman.javaDraw.model;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private List<Point> points = new ArrayList<>();
    private List<Pixel> pixels = new ArrayList<>();
    private Double factor=100.0;
    private Long minX= 0L;
    private Long maxX=800L;
    private Long minY= 0L;
    private Long maxY=600L;
    private Long deltaX=400L;
    private Long deltaY=300L;




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

    public void setScreenRange( Long minX, Long maxX, Long minY, Long maxY ){
        this.minX = minX; this.maxX=maxX; this.minY=minY; this.maxY=maxY;
    }



    public int XtoPixX( Double x ){
        Double lx = deltaX+x*factor;
        return lx.intValue();
    }

    public int YtoPixY( Double y ){
        Double ly = deltaY+y*factor;
        return ly.intValue();
    }

    public void createPixelFromPoints(){

        for ( Point p : points ){
              int x = XtoPixX( p.getX() );
              int y = YtoPixY( p.getY() );

              if ( x<minX || x>maxX ) continue;
              if ( y<minY || y>maxY ) continue;

              Pixel pix = new Pixel( x, y );
            //System.out.println( pix );
            pixels.add( pix );
        }

    }

    public Double getFactor() { return factor; }
    public void setFactor(Double factor) { this.factor = factor; }

    public List<Pixel> getPixels() { return pixels; }


    public Long getMinX() {return minX;}
    public Long getMaxX() {return maxX;}
    public Long getMinY() {return minY;}
    public Long getMaxY() {return maxY;}
}
