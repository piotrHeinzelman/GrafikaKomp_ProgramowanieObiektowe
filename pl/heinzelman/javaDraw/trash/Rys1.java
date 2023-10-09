package pl.heinzelman.javaDraw.trash;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;

public class Rys1 extends Canvas
{

    //http://www.java2s.com/Code/Java/2D-Graphics-GUI/DrawapointuseadrawLinemethod.htm
    public Rys1()
    {
        setSize(280, 200);
        setVisible(true);
    }
    public void paint( Graphics g )
    {
        super.paint(g);
     //   Graphics2D g2 = (Graphics2D) g;

        //Create Point2D.Double
        Integer xd = 50;
        Integer yd = 54;
        Point2D.Double point = new Point2D.Double(xd, yd);

        g.setColor( new Color (1,1,1));
        g.translate(xd,yd);
        g.drawLine(xd,yd,0,0);
        //g2.draw(new Line2D.Double(x1, y1, x2, y2));

        //setLocation(double x, double y) – To set the location of the point- defining coordinates as double values.
        //setLocation(Point2D point)


         //https://docs.oracle.com/javase/tutorial/2d/geometry/primitives.html


        /*
        g.drawLine(10, 10, 50, 160);
        g.drawLine(10, 10, 50, 160);
        g.setColor(Color.ORANGE);
        g.drawRect(60, 10, 40, 150);
        g.setColor(Color.RED);
        g.fillRect(60+1, 10+1, 40-1, 150-1);
        g.setColor(Color.BLUE);
        g.drawOval(110, 10, 40, 150);
        g.setColor(Color.CYAN);
        g.fillOval(110+1, 10+1, 40-1, 150-1);
        g.setColor(Color.GREEN);
        g.setFont(new Font("Serif", Font.BOLD, 16));
        g.drawString("Serif BOLD 16", 160, 70);
        g.setColor(Color.MAGENTA);
        g.setFont(new Font("SansSerif", Font.ITALIC, 11));
        g.drawString("SansSerif ITALIC 11", 160, 110);
         */
    }
}