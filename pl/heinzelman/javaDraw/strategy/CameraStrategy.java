package pl.heinzelman.javaDraw.strategy;

import pl.heinzelman.javaDraw.model.*;

import java.util.ArrayList;
import java.util.List;

public class CameraStrategy implements ProjectionStrategy {
    private final Model model;

    public CameraStrategy( Model model ) {
        this.model = model;
    }

    public List<Pixel> getPixels_of_ProjectedPoints( List<Point> points ){
        int deltaX=560; // screenWidth=1200;
        int deltaY=360; // screenHeight=800;
        List<Pixel> pixels = new ArrayList<>();

        Matrix screenMatrix = new Matrix(); /* set d */ Double[][] sm=screenMatrix.getM();sm[2][3]=(1/model.getD()); sm[3][3]=0.0;
        Double d = model.getD();
        for ( Point p : points ){
            Point3D p3 = (Point3D) p;
            Double x=p3.getX();
            Double y=p3.getY();
            Double z=p3.getZ();
            Double w=p3.getW();

            Point3D proj = screenMatrix.mul( p3 );
            Pixel pix = new Pixel( deltaX+proj.getX().intValue(), deltaY-proj.getY().intValue() );
            pixels.add( pix );
        }
        return pixels;
    }


    public List<Edge> getEdgesOfPixels( List<Pixel> pixels ){
        Pixel corners[] = new Pixel[9];
        int i=0;
        List<Edge> edges = new ArrayList<>();
        for ( Pixel pix : pixels ){
            corners[i] = pix;
            i++;
            if (i==8) {
                edges.add(new Edge(corners[0], corners[1]));
                edges.add(new Edge(corners[1], corners[2]));
                edges.add(new Edge(corners[2], corners[3]));
                edges.add(new Edge(corners[3], corners[0]));

                edges.add(new Edge(corners[0], corners[4]));
                edges.add(new Edge(corners[1], corners[5]));
                edges.add(new Edge(corners[2], corners[6]));
                edges.add(new Edge(corners[3], corners[7]));

                edges.add(new Edge(corners[4], corners[5]));
                edges.add(new Edge(corners[5], corners[6]));
                edges.add(new Edge(corners[6], corners[7]));
                edges.add(new Edge(corners[7], corners[4]));
                i = 0;
            }
        }
        return edges;
    }



    public List<Wall> getWallsOfPixels( List<Pixel> pixels ){
        Pixel corners[] = new Pixel[9];
        int i=0;
        List<Wall> walls = new ArrayList<>();
        for ( Pixel pix : pixels ){
            corners[i] = pix;
            i++;
            if (i==8) {
                walls.add( new Wall( corners[0], corners[1], corners[2], corners[3] ));
                walls.add( new Wall( corners[4], corners[5], corners[6], corners[7] ));
                walls.add( new Wall( corners[0], corners[1], corners[5], corners[4] ));
                walls.add( new Wall( corners[3], corners[2], corners[6], corners[7] ));
                walls.add( new Wall( corners[0], corners[3], corners[7], corners[4] ));
                walls.add( new Wall( corners[1], corners[2], corners[6], corners[5] ));
                i = 0;
            }
        }
        return SortWall( walls );
    }










    public List<Point> translatePoints( List<Point> points , Translate translate ) {
        List<Point> translated = new ArrayList<>();
        Matrix translatedMatrix = new Matrix();
        Double phi=30*(3.14/360);//0.0314;
        Double step=10.0;

        switch (translate){
            case LEFT  -> { translatedMatrix = Matrix.getMoveMatrix( -step,0.0,0.0 ); }
            case RIGHT -> { translatedMatrix = Matrix.getMoveMatrix(  step,0.0,0.0 ); }
            case UP    -> { translatedMatrix = Matrix.getMoveMatrix(   0.0, step,0.0 ); }
            case DOWN  -> { translatedMatrix = Matrix.getMoveMatrix(   0.0,-step,0.0 ); }
            case IN    -> {  model.setD( model.getD()*1.1 ); }
            case OUT   -> {  model.setD( model.getD()*(1.0/1.1) ); }
            case ROT_CCW -> { translatedMatrix = Matrix.getRotateZMatrix( -phi ); }
            case ROT_CW  -> { translatedMatrix = Matrix.getRotateZMatrix(  phi ); }
            case ROT_DOWN ->{ translatedMatrix = Matrix.getRotateXMatrix(  phi ); }
            case ROT_UP  -> { translatedMatrix = Matrix.getRotateXMatrix( -phi ); }
            case ROT_LEFT ->{ translatedMatrix = Matrix.getRotateYMatrix( -phi ); }
            case ROT_RIGHT->{ translatedMatrix = Matrix.getRotateYMatrix(  phi ); }
        }

        for ( Point p : points ){
            translated.add( translatedMatrix.mul( (Point3D)p ));
        }
        return translated;
    }


    public List<Wall> SortWall( List<Wall> walls ){
        List<Wall> sortedWall = new ArrayList<>();
        // TODO Sort wall !
        // must translated from Points, PIXELS is Flat :- (
    return sortedWall;
    }
}
