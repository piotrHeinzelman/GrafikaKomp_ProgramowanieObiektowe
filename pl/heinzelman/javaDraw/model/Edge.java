package pl.heinzelman.javaDraw.model;


import java.awt.*;

public class Edge {
    private Pixel start;
    private Pixel end;
    private Color color=null;                                                                         /* G&S */public Color getColor() { return color; } public void setColor(Color color) { this.color = color; }

    public Edge( Pixel start, Pixel end ) {
        this.start = start;
        this.end = end;
    }

    public Pixel getStart() { return start; }
    public Pixel getEnd()   { return end;   }

    @Override
    public String toString() {
        return "{ "+start.toString() + ", " + end.toString() + '}';
    }


}
