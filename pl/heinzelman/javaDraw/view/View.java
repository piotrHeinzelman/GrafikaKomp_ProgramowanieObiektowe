package pl.heinzelman.javaDraw.view;

import pl.heinzelman.javaDraw.model.Model;
import pl.heinzelman.javaDraw.model.Pixel;
import pl.heinzelman.javaDraw.model.Swatch;
import pl.heinzelman.javaDraw.trash.Rys1;

import java.awt.*;

public class View extends Canvas {

    private final Model model;
    private Color color = Swatch.R.getJColor(Swatch.R);
    BasicStroke stroke = new BasicStroke( 3 );
    private Boolean isAxis=true;

    public View( Model model ) {
        this.model = model;
        setSize( 800, 600);
        setVisible(true);
    }


    public void paint( Graphics g )
    {
        Graphics2D g2d = (Graphics2D)g;
        super.paint(g);

        if ( isAxis ) drawAxis( g , g2d );

        g.setColor( color );
        g2d.setStroke( stroke );

        for ( Pixel pix : model.getPixels() ){
            g.drawLine( pix.getX(), pix.getY(), pix.getX(), pix.getY() );
        }



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
}
