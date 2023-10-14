package pl.heinzelman.javaDraw.view;

import pl.heinzelman.javaDraw.model.Edge;
import pl.heinzelman.javaDraw.model.Model;
import pl.heinzelman.javaDraw.model.Pixel;

import java.awt.*;
import java.util.List;

public class View extends Canvas {

    private final Model model;
    private final Window parent;

    private Color bgcolor    = new Color( 0x20,0x20,0x20 );
    private Color axisColor  = new Color( 0x00,0x50,0x00 );
    private Color color = new Color( 0x00,0xa0,0xf0 );
    private Graphics2D g2d   = null;
    private int strokeWidth = 1;

    private BasicStroke stroke = new BasicStroke( 2 );
    private Boolean isAxis=true;                                                                                                    /* G&S */ public void turnOffAxis() { isAxis = false; } public void turnOnAxis()  { isAxis = true; }

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
            g2d.setStroke(new BasicStroke( strokeWidth +1 ));
            g2d.setColor( color );
            drawListOfPixel( model.getPixels() );
        }

        // edge ? !
        if ( true ) {
            g2d.setStroke( new BasicStroke(strokeWidth) );
            g2d.setColor( color );
            drawListOfEdge( model.getEdges() );
        }

        // walls ?
        if ( false ){
            g2d.setStroke( new BasicStroke(strokeWidth) );
            g2d.setColor( color );
            // TODO draw walls
        }

    }


    private void drawAxis( Graphics2D g2 ){
        g2.setStroke(new BasicStroke( 1 ));
        g2.setColor( axisColor );
        drawListOfEdge( model.getAxisEdge() );
    }


    public void drawPixel( Pixel p ){
        g2d.drawLine( p.getX(), p.getY(), p.getX(), p.getY());
    }
    public void drawListOfPixel( List<Pixel> list ){
        for ( Pixel pix : list ){
            drawPixel( pix );
        }
    }



    public void drawLine( Pixel p1, Pixel p2 ){
        g2d.drawLine( p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }
    public void drawListOfEdge( List<Edge> list ){
        for ( Edge e : list ){
            drawLine( e.getStart(), e.getEnd() );
        }
    }




    // getters & setters
    public void setColor( Color color ) { this.color = color; }
    private void setStroke( int stroke ) { this.stroke = new BasicStroke( stroke ); }
    public  void incStroke() { this.strokeWidth++; }
    public  void decStroke() { this.strokeWidth--; }
}
