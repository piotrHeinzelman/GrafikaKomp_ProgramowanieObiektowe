package pl.heinzelman.javaDraw.model;

import pl.heinzelman.javaDraw.strategy.ProjectionStrategy;
import pl.heinzelman.javaDraw.strategy.Translate;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private List<Point> points = new ArrayList<>();
    private List<Pixel> pixels = new ArrayList<>();
    private List<Edge>  edges  = new ArrayList<>();
    private List<Wall>  walls  = new ArrayList<>();

    private ProjectionStrategy strategy = null;                                                                                       /* G&S */   public void setStrategy( ProjectionStrategy strategy ) { this.strategy = strategy; }
    private Double d = 100.0;                                                                                                         /* G&S */   public Double getD() { return d; } public void setD(Double d) { this.d = d; }
    private Pixel viewTopLeft;                                                                                                        /* G&S */   public void setViewTopLeft    (Pixel viewTopLeft)     { this.viewTopLeft = viewTopLeft; }         public Pixel getViewTopLeft() { return viewTopLeft; }
    private Pixel viewBottomRight;                                                                                                    /* G&S */   public void setViewBottomRight(Pixel viewBottomRight) { this.viewBottomRight = viewBottomRight; } public Pixel getViewBottomRight() { return viewBottomRight; }
    private Point Pmax = null;
    private Point Pmin = null;                                                                                                        /* G&S */   public Point getPmax() { return Pmax; } public void setPmax(Point pmax) { Pmax = pmax; } public Point getPmin() { return Pmin; } public void setPmin(Point pmin) { Pmin = pmin; }


    private List<Point> axisPoints = new ArrayList<>();

    // --
    public void clearAll(){
        clearPixels();
        clearPoints();
    };

    public void refreshPixels(){
        pixels = strategy.getPixels_of_ProjectedPoints( points );
         edges = strategy.getEdgesOfPixels( pixels );
    }

    public void translatePoints( Translate translate ){
        points = strategy.translatePoints( points , translate );
        axisPoints = strategy.translatePoints( axisPoints, translate );
    }



    // Points
    private void         clearPoints() { points = new ArrayList<>(); }
    private List<Point>  getPoints()   { return points;              }
    public void addPoint(String[] split ){
        Point p = Point.PointFromFile( split );
        if (p!=null) {
            points.add( p );
        }
    }

    // Pixels
    public List<Pixel> getPixels()                   { return pixels;        }
    public void        setPixels(List<Pixel> pixels) { this.pixels = pixels; }
    public void        clearPixels()                 { pixels = new ArrayList<>(); }
    public List<Pixel> getPixels_of_ProjectedPoints( List<Point> points )   {
        return strategy.getPixels_of_ProjectedPoints( points );
    }

    // Edges
    public List<Edge> getEdges()   { return edges;              }
    public void       clearEdges() { edges = new ArrayList<>(); }
    public void setEdges(List<Edge> edges) { this.edges = edges; }
    public List<Edge> getEdgesOfPixels(List<Pixel> pixels )   {
        return strategy.getEdgesOfPixels( pixels );
    }




    // Walls
    public List<Wall> getWalls()                 { return walls;              }
    public void       clearWalls()               { walls = new ArrayList<>(); }
    public void       setWalls(List<Wall> walls) { this.walls = walls;        }


    public void calculateModelScale(){
        getRangeOfPoint();
    }


    private void getRangeOfPoint(){
        Double minX=null, maxX=null;
        Double minY=null, maxY=null;
        for ( Point p : points ){
            if ( minX ==null) { minX=p.getX(); maxX=p.getX(); minY=p.getY(); maxY=p.getY() ;  continue; }
            if ( p.getX()<minX ){ minX=p.getX(); }
            if ( p.getX()>maxX ){ maxX=p.getX(); }
            if ( p.getY()<minY ){ minY=p.getY(); }
            if ( p.getY()>maxY ){ maxY=p.getY(); }
        }

        Double max = minX*minX;
        Double tmp = maxX*maxX; if ( tmp>max ) { max=tmp; }
               tmp = minY*minY; if ( tmp>max ) { max=tmp; }
               tmp = maxY*maxY; if ( tmp>max ) { max=tmp; }
        Double maxValue = Math.pow( max,0.5 );
        Pmin = new Point ( minX, minY );
        Pmax = new Point ( maxX, maxY );
    }

    public void createAxisEdge() {
        axisPoints = new ArrayList<>();

        axisPoints.add(new Point(Pmin.getX() * 1.2, 0.0));
        axisPoints.add(new Point(Pmax.getX() * 1.2, 0.0));
        axisPoints.add(new Point(0.0, 0.0));
        axisPoints.add(new Point(0.0, Pmin.getY() * 1.2));
        axisPoints.add(new Point(0.0, Pmax.getY() * 1.2));
        axisPoints.add(new Point(0.0, 0.0));
        axisPoints.add(new Point(1.0, 0.0));
        axisPoints.add(new Point(1.0, -0.1));
        axisPoints.add(new Point(1.0, 0.0));
        axisPoints.add(new Point(2.0, 0.0));
        axisPoints.add(new Point(2.0, -0.1));
        axisPoints.add(new Point(2.0, 0.0));
        axisPoints.add(new Point(3.0, 0.0));
        axisPoints.add(new Point(3.0, -0.1));
        axisPoints.add(new Point(3.0, 0.0));
        axisPoints.add(new Point(0.0, 0.0));
        axisPoints.add(new Point(0.0, 1.0));
        axisPoints.add(new Point(-0.1, 1.0));
        axisPoints.add(new Point(0.0, 1.0));
        axisPoints.add(new Point(0.0, -1.0));
        axisPoints.add(new Point(-0.1, -1.0));
        axisPoints.add(new Point(0.0, -1.0));
        axisPoints.add(new Point(0.0, 0.0));
        axisPoints.add(new Point(-1.0, -0.0));
        axisPoints.add(new Point(-1.0, -0.1));
        axisPoints.add(new Point(-1.0, -0.0));
    }

    public List<Edge> getAxisEdge() {
        List<Pixel> axisPixel = getPixels_of_ProjectedPoints( axisPoints );
        List<Edge>  listEdges = getEdgesOfPixels( axisPixel );
        return listEdges;
    }


}
