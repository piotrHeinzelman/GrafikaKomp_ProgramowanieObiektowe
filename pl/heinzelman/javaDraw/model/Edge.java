package pl.heinzelman.javaDraw.model;


public class Edge {
    private Pixel start;
    private Pixel end;

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
