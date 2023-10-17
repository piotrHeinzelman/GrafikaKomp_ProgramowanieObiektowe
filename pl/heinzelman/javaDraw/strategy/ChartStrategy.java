package pl.heinzelman.javaDraw.strategy;

import pl.heinzelman.javaDraw.model.*;

import java.util.ArrayList;
import java.util.List;

public class ChartStrategy implements ProjectionStrategy {
    private final Model model;

    public ChartStrategy( Model model ) {
        this.model = model;
    }

    public List<Pixel> getPixels_of_ProjectedPoints(List<Point> points ){
        int deltaX=560; // screenWidth=1200;
        int deltaY=360; // screenHeight=800;

        Double minX = model.getPmin().getX();
        Double maxX = model.getPmax().getX();
        Double minY = model.getPmin().getY();
        Double maxY = model.getPmax().getY();
        Double aX = (deltaX+deltaX)/(maxX-minX);
        Double aY = (deltaY+deltaY)/(maxY-minY);
        Double mull = aX<aY ? aX : aY;

        List<Pixel> pixels = new ArrayList<>();
            for ( Point p : points ){

                //  min,max
                //        min--x--max
                //     0-------------------------1200
                //     (.)--(x-min)--(max-min)
                //        (x-min) * 1200/(max-min)

                // System.out.println( minX+" : "+maxX+" - "+mull+" ? "+(p.getX()-minX) + " = " + (p.getX()-minX)*mull );

                int ix = (int) ( 20+(p.getX()-minX) *mull );
                int iy =(int) (  20+(p.getY()-minY) *mull );
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

    @Override  public List<Wall3D> getWallsOfPixels(List<Pixel> pixels   ){ return null; }
    @Override  public List<Wall> SortAndFlatWall3D(List<Wall3D> unsorted ){ return null; }


    public List<Point> translatePoints( List<Point> points , Translate translate ){
    List<Point> translated = new ArrayList<>();
        Point vector = new Point( 0.0 ,0.0 );
        Double mul = 1.0;

        Boolean mustMul=false;
        Boolean mustMove=false;

        switch (translate){
            case LEFT  -> { vector.setX(  0.1 ); mustMove=true; }
            case RIGHT -> { vector.setX( -0.1 ); mustMove=true; }
            case UP    -> { vector.setY(  0.1 ); mustMove=true; }
            case DOWN  -> { vector.setY( -0.1 ); mustMove=true; }
            case IN    -> { mul = 1.1;           mustMul =true;  }
            case OUT   -> { mul = 0.9;           mustMul =true; }
        }

        for ( Point p : points ){
            new Point( p.getX(),p.getY() );
            if ( mustMove ) p=p.move( vector );
            if ( mustMul  ) p=p.scale( mul );
            translated.add( p );
        }

    return translated;
    }
}
