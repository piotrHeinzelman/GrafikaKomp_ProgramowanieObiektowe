package pl.heinzelman.javaDraw.view;

import pl.heinzelman.javaDraw.model.Edge;
import pl.heinzelman.javaDraw.model.Model;
import pl.heinzelman.javaDraw.model.Pixel;
import pl.heinzelman.javaDraw.model.Point;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class View extends Canvas {

    private final Model model;
    private final Window parent;

    private Color bgcolor    = new Color( 0x20,0x20,0x20 );
    private Color axisColor  = new Color( 0x00,0x50,0x00 );
    private Color pointColor = new Color( 0x00,0xa0,0xf0 );
    private Color color      = new Color( 0x2f,0x2f,0x2f );
    private Graphics2D g2d   = null;
    private int width = 1;

    private BasicStroke stroke = new BasicStroke( 2 );
    private Boolean isAxis=true;




    private void setMySize(){
        setSize( 1200, 800 );
        parent.pack();
        model.setViewTopLeft( new Pixel (0,0) );
        model.setViewBottomRight( new Pixel ( getWidth() , getHeight()) );
    }


    public View( Model model , Window parent ) {
        this.model = model;
        this.parent = parent;
        setMySize();
        setVisible(true);
        model.setViewTopLeft( new Pixel (0,0) );
        model.setViewBottomRight( new Pixel ( getWidth() , getHeight()) );
    }


    public void paint( Graphics g )
    {
        super.paint(g);
        g2d = ( Graphics2D ) g;
        setMySize();

        // clean
        g2d.setColor( axisColor );
        g2d.fillRect( 0, 0, getWidth(), getHeight() );
        g2d.setColor( bgcolor );
        g2d.fillRect( 1, 1, getWidth()-2, getHeight()-2 );

        // axis ?
        if ( isAxis ) {
            g2d.setStroke( new BasicStroke( 1 ) );
            g2d.setColor( axisColor );
            drawAxis( g2d );
        }

        // points ? !
        if ( true ) {
            g2d.setStroke(new BasicStroke(3));
            g2d.setColor( pointColor );
            drawListOfPixel( model.getPixels() );
        }

        // edge ? !
        if ( true ) {
            g2d.setStroke( new BasicStroke(2) );
            g2d.setColor( pointColor );
            drawListOfEdge( model.getEdges() );
        }

        // walls ?
        if ( false ){
            g2d.setStroke( new BasicStroke(1) );
            g2d.setColor( color );
            // TODO draw walls
        }

    }


    private void drawAxis( Graphics2D g2 ){
        g2.setStroke(new BasicStroke( 1 ));
        g2.setColor( axisColor );
        Point pmin = model.getPmin();
        Point pmax = model.getPmax();

        List<Point> axisPoints = new ArrayList<>();

            axisPoints.add( new Point(  pmin.getX()*1.2 , 0.0 ));
            axisPoints.add( new Point(  pmax.getX()*1.2 , 0.0 ));
            axisPoints.add( new Point(  0.0,  0.0 ));
            axisPoints.add( new Point(  0.0, pmin.getY()*1.2  ));
            axisPoints.add( new Point(  0.0, pmax.getY()*1.2  ));
            axisPoints.add( new Point(  0.0,  0.0 ));
            axisPoints.add( new Point(  1.0,  0.0 ));
            axisPoints.add( new Point(  1.0, -0.1 ));
            axisPoints.add( new Point(  1.0,  0.0 ));
            axisPoints.add( new Point(  2.0,  0.0 ));
            axisPoints.add( new Point(  2.0,  -0.1 ));
            axisPoints.add( new Point(  2.0,  0.0 ));
            axisPoints.add( new Point(  3.0,  0.0 ));
            axisPoints.add( new Point(  3.0,  -0.1 ));
            axisPoints.add( new Point(  3.0,  0.0 ));
            axisPoints.add( new Point(  0.0,  0.0 ));
            axisPoints.add( new Point(  0.0,  1.0 ));
            axisPoints.add( new Point( -0.1,  1.0 ));
            axisPoints.add( new Point(  0.0,  1.0 ));
            axisPoints.add( new Point(  0.0, -1.0 ));
            axisPoints.add( new Point( -0.1, -1.0 ));
            axisPoints.add( new Point(  0.0, -1.0 ));
            axisPoints.add( new Point(  0.0,  0.0 ));
            axisPoints.add( new Point( -1.0, -0.0 ));
            axisPoints.add( new Point( -1.0, -0.1 ));
            axisPoints.add( new Point( -1.0, -0.0 ));

        List<Pixel> axisPixel = model.getPixels_of_ProjectedPoints( axisPoints );
        List<Edge>  listEdges = model.getEdgesOfPixels( axisPixel );
        drawListOfPixel( axisPixel );
        drawListOfEdge( listEdges  );
    }



    public void drawListOfPixel( List<Pixel> list ){
        for ( Pixel pix : list ){
            drawPixel( pix );
        }
    }


    public void drawPixel( Pixel p ){
        g2d.drawLine( p.getX(), p.getY(), p.getX(), p.getY());
    }


    public void drawListOfEdge( List<Edge> list ){
        for ( Edge e : list ){
            drawLine( e.getStart(), e.getEnd() );
        }
    }


    public void drawLine( Pixel p1, Pixel p2 ){
        g2d.drawLine( p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }


    // getters & setters
    public void setColor( Color color ) { this.color = color; }
    public void setStroke( int stroke ) { this.stroke = new BasicStroke( stroke ); }
}
