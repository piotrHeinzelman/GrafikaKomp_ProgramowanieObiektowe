package pl.heinzelman.javaDraw.view;

import pl.heinzelman.javaDraw.model.Model;
import pl.heinzelman.javaDraw.model.Pixel;
import pl.heinzelman.javaDraw.model.Swatch;

import java.awt.*;

public class View extends Canvas {

    private final Model model;
    private Window parent;
    private Graphics g;
    private Graphics2D g2d;
    private Color color = null;
    private BasicStroke stroke = null;
    private Boolean isAxis=true;


    public View( Model model , Window parent) {
        this.model = model;
        this.parent = parent;

        setColor( Swatch.R.getJColor(Swatch.R) );
        setStroke(3);
        setSize(800, 600);
        setVisible(true);
    }


    public void paint( Graphics g )
    {
        super.paint(g);
        Graphics2D g2d = ( Graphics2D ) g;

        if ( isAxis ) drawAxis( g , g2d );

        g.setColor( color );
        g2d.setStroke( stroke );

        for ( Pixel pix : model.getPixels() ){
            g.drawLine( pix.getX(), pix.getY(), pix.getX(), pix.getY() );
        }
        this.g = g;
    }


    public void setColor( Color color ) { this.color = color; }
    public void setStroke( int stroke ) {
        this.stroke = new BasicStroke( stroke );
    }
    private void drawAxis( Graphics g , Graphics2D g2 ){
        g2.setStroke(new BasicStroke( 1 ));
        g.setColor( new Color(0xC0,0xC0,0xC0) );
        g.drawLine(   model.getMinX().intValue() , model.YtoPixY(0.0), model.getMaxX().intValue(), model.YtoPixY(0.0) );
        g.drawLine(   model.XtoPixX(0.0), model.getMinY().intValue() , model.XtoPixX(0.0), model.getMaxY().intValue() );
    }

    public void clear(){
        System.out.println( this.g );
        System.out.println( g );z

        Rectangle bounds = parent.getBounds();
            Color tmp = color;
            if (g!=null) {
                    g.setColor(getBackground());
                    g.clearRect(0, 0, bounds.width, bounds.height);
            }
         /*





        Graphics g = getGraphics();
        if ( g!=null ) {
            g.clearRect(bounds.x, bounds.y, bounds.width, bounds.height);
            Color tmp = color;
            g.setColor( new Color(0xC0,0xC0,0xC0) );
            g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
            color=tmp;
        }*/
    }

    public void refresh(){
     //   if (getGraphics()!=null) {
     //       paint( getGraphics() );
     //   }
    }
}
