package pl.heinzelman.javaDraw.model;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Wall  { /* WALL in projection ! 2D quadrangle ! */
    private Pixel one;
    private Pixel two;
    private Pixel three;
    private Pixel four;
    private Color color;                                                                                                               /* G&S */ public Color getColor() { return color; } public void setColor(Color color) { this.color = color; }

    public Wall( Pixel one, Pixel two, Pixel three, Pixel four ) {
        // sort by x & y
        this.one = one;
        this.two = two;
        this.three = three;
        this.four = four;
    }

    public Pixel getOne() { return one; }
    public Pixel getTwo() { return two; }
    public Pixel getThree() { return three; }
    public Pixel getFour() { return four; }

}
