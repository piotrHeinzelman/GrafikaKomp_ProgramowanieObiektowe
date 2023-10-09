package pl.heinzelman.javaDraw;

import java.awt.*;
import java.awt.event.*;
public class Rys1 extends Canvas
{
    public Rys1()
    {
        setSize(280, 200);
        setVisible(true);
    }
    public void paint(Graphics g)
    {
        super.paint(g);


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
    }
}