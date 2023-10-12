package pl.heinzelman.javaDraw.view;

import pl.heinzelman.javaDraw.model.Model;
import pl.heinzelman.javaDraw.model.Pixel;
import pl.heinzelman.javaDraw.model.Swatch;

import java.awt.*;

public class View extends Canvas {

    private final Model model;
    private Window parent;
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

        g2d.setColor( new Color(0xf0,0xf0,0xf0) );
        g2d.fillRect(-2000,-2000,3000,3000);


        if ( isAxis ) drawAxis( g2d );

        g2d.setColor( color );
        g2d.setStroke( stroke );

        for ( Pixel pix : model.getPixels() ){
            g2d.drawLine( pix.getX(), pix.getY(), pix.getX(), pix.getY() );
        }
    }


    public void setColor( Color color ) { this.color = color; }
    public void setStroke( int stroke ) {
        this.stroke = new BasicStroke( stroke );
    }
    private void drawAxis( Graphics2D g2 ){
        g2.setStroke(new BasicStroke( 1 ));
        g2.setColor( new Color(0xC0,0xC0,0xC0) );
        g2.drawLine(   model.getMinX().intValue() , model.YtoPixY(0.0), model.getMaxX().intValue(), model.YtoPixY(0.0) );
        g2.drawLine(   model.XtoPixX(0.0), model.getMinY().intValue() , model.XtoPixX(0.0), model.getMaxY().intValue() );
    }
}
