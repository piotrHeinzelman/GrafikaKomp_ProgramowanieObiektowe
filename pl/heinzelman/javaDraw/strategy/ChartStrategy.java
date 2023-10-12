package pl.heinzelman.javaDraw.strategy;

import pl.heinzelman.javaDraw.model.Edge;
import pl.heinzelman.javaDraw.model.Model;
import pl.heinzelman.javaDraw.model.Pixel;
import pl.heinzelman.javaDraw.model.Point;

import java.util.ArrayList;
import java.util.List;

public class ChartStrategy implements ProjectionStrategy {
    private final Model model;

    public ChartStrategy( Model model ) {
        this.model = model;
    }

    public List<Pixel> getPixels_of_ProjectedPoints(List<Point> points ){
        Pixel ViewTL = model.getViewTopLeft();
        Pixel ViewBR = model.getViewBottomRight();
        int DeltaX = (ViewBR.getX()-ViewTL.getX())/2;
        int DeltaY = (ViewBR.getY()-ViewTL.getY())/2;
        int maxX = ViewBR.getX();
        int maxY = ViewBR.getY();
        Double mull = model.getD();

        List<Pixel> pixels = new ArrayList<>();
            for ( Point p : points ){
                Double x=p.getX();
                Double y=p.getY();
                int ix = DeltaX+(int) (x*mull);
                int iy = DeltaY-(int) (y*mull);
                if ( true || ix > 0 && ix < maxX && iy > 0 && iy < maxY ) {
                    pixels.add( new Pixel( ix, iy ) ); // LeftTop = 00
                }
            }
        return pixels;
    }


    public List<Edge> getEdgesOfPixels( List<Pixel> pixels ){
        // Join one-two two-three ...
        Pixel tmp = null;
        List<Edge> edges = new ArrayList<>();
        for ( Pixel pix : pixels ){
            if ( tmp==null ){ tmp=pix; continue; }
            edges.add( new Edge( tmp , pix ) ); tmp=pix;
        }
        return edges;
    }
}

/*

    public void createPixelFromPoints(){
        for ( Point p : points ){
            int x = XtoPixX( p.getX() );
            int y = YtoPixY( p.getY() );

            if ( x<minX || x>maxX ) continue;
            if ( y<minY || y>maxY ) continue;

            Pixel pix = new Pixel( x, y );
            pixels.add( pix );
        }

    }

    // * * * * *
    // *
    // *

    public int XtoPixX( Double x ){
        Double lx = deltaX+x*factor;
        return lx.intValue();
    }

    public int YtoPixY( Double y ){
        Double ly = deltaY+y*factor;
        return ly.intValue();
    }


    // * * * * * * * * *

 */