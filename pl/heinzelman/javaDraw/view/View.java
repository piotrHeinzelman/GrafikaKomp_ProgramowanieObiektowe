package pl.heinzelman.javaDraw.view;

import pl.heinzelman.javaDraw.model.Edge;
import pl.heinzelman.javaDraw.model.Model;
import pl.heinzelman.javaDraw.model.Pixel;
import pl.heinzelman.javaDraw.model.Wall;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.List;

public class View extends Canvas {

    private final Model model;
    private final Window parent;

    private Color bgcolor    = new Color( 0x20,0x20,0x20 );
    private Color axisColor  = new Color( 0x00,0x50,0x00 );
    private Color color      = new Color( 0x00,0xa0,0xf0 );
    private Color wallColor = new Color( 0xA0,0x00, 0x20 );
    private Graphics2D g2d   = null;
    private int strokeWidth = 1;

    private BasicStroke stroke = new BasicStroke( 2 );
    private Boolean isAxis=true;                                                                                                    /* G&S */ public void turnOffAxis() { isAxis = false; } public void turnOnAxis()  { isAxis = true; }
    private Boolean isPoint=true;
    private Boolean isEdge=true;
    private Boolean isWall=true;


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
        if ( isPoint ) {
            g2d.setStroke(new BasicStroke( strokeWidth +2 ));
            g2d.setColor( color );
            drawListOfPixel( model.getPixels() );
        }

        // edge ? !
        if ( isEdge ) {
            g2d.setStroke( new BasicStroke(strokeWidth) );
            g2d.setColor( color );
            drawListOfEdge( model.getEdges() );
        }

        // walls ?
        if ( isWall ){
            g2d.setStroke( new BasicStroke( strokeWidth ) );
            g2d.setColor( color );
            drawListOfWall( model.getWalls() );
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

    public void drawWall( Wall w ){
        GeneralPath polyline = new GeneralPath(GeneralPath.WIND_EVEN_ODD, 4);
        polyline.moveTo( w.getOne().getX(), w.getOne().getY() );
        polyline.lineTo( w.getTwo().getX(), w.getTwo().getY() );
        polyline.lineTo( w.getThree().getX(), w.getThree().getY() );
        polyline.lineTo( w.getFour().getX(), w.getFour().getY() );
        polyline.closePath();
        g2d.fill( polyline );
    }

    public void drawListOfWall( List<Wall> list ){
        Color tmp = color;
        for ( Wall w : list ){
            if ( w.getColor()!=null ) { g2d.setColor( w.getColor() ); } else { g2d.setColor( new Color(255,0,255) ); }
            try { Thread.sleep(1); } catch (InterruptedException e) { throw new RuntimeException(e); }
            drawWall( w );
        }
      color=tmp;
    }



    // getters & setters
    public void setColor( Color color ) { this.color = color; }
    private void setStroke( int stroke ) { this.stroke = new BasicStroke( stroke ); }
    public  void incStroke() { this.strokeWidth++; }
    public  void decStroke() { this.strokeWidth--; }
    public void togglePoint(){ isPoint=!isPoint; }
    public void toggleEdge() { isEdge=!isEdge;   }
    public void toggleWall() { isWall=!isWall; }
}
