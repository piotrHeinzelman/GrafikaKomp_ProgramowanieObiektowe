package pl.heinzelman.javaDraw.strategy;

import pl.heinzelman.javaDraw.model.*;
import pl.heinzelman.javaDraw.model.Point;
import pl.heinzelman.javaDraw.treeBSP.Tree;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CameraStrategy implements ProjectionStrategy {
    private final Model model;

    private final Color[] colorTab= {
        new Color(0x0077C7),
        new Color(0x004675),
        new Color(0x002E4D),
        new Color(0x6301EA),
        new Color(0x310075),
        new Color(0x20004D),
            null,
            null,

        new Color(0xC701EA),
        new Color(0x640075),
        new Color(0x41004D),
        new Color(0xEB0056),
        new Color(0x9E003A),
        new Color(0x570120),
            null,
            null,

        new Color(0xEB4E00),
        new Color(0x9E3500),
        new Color(0x571D00),
        new Color(0xEB9100),
        new Color(0x9E6100),
        new Color(0x573500),
            null,
            null,

        new Color(0xC4EB00),
        new Color(0x849E00),
        new Color(0x485600),
        new Color(0x00EB08),
        new Color(0x007304),
        new Color(0x005703),
            null,
            null,
    };



    public CameraStrategy( Model model ) {
        this.model = model;
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



    public List<Wall3D> getWallsOfPoints3D(List<Point> PS ){

        List<Wall3D> walls3D = new ArrayList<>();
        int i=0; int j=1;
        for (int k=0;k<PS.size()/8;k++){
/* FronT*/    walls3D.add( new Wall3D( (Point3D) PS.get(k*8+0), (Point3D) PS.get(k*8+1), (Point3D) PS.get(k*8+2), (Point3D) PS.get(k*8+3), colorTab[8*k]));
/* TOP */    walls3D.add( new Wall3D( (Point3D) PS.get(k*8+7), (Point3D) PS.get(k*8+3), (Point3D) PS.get(k*8+2), (Point3D) PS.get(k*8+6), colorTab[8*k+5]));//7326
/* Right*/    walls3D.add( new Wall3D( (Point3D) PS.get(k*8+2), (Point3D) PS.get(k*8+1), (Point3D) PS.get(k*8+5), (Point3D) PS.get(k*8+6), colorTab[8*k+1])); //2156
/* Left */    walls3D.add( new Wall3D( (Point3D) PS.get(k*8+4), (Point3D) PS.get(k*8+7), (Point3D) PS.get(k*8+3), (Point3D) PS.get(k*8+0), colorTab[8*k+3])); //0374
/*Bottom*/    walls3D.add( new Wall3D( (Point3D) PS.get(k*8+0), (Point3D) PS.get(k*8+4), (Point3D) PS.get(k*8+5), (Point3D) PS.get(k*8+1), colorTab[8*k+4]));
/* Back */    walls3D.add( new Wall3D( (Point3D) PS.get(k*8+7), (Point3D) PS.get(k*8+6), (Point3D) PS.get(k*8+5), (Point3D) PS.get(k*8+4), colorTab[8*k+2]));
        }
        return walls3D;
    }




    public void prepareTree( List<Wall3D> unsorted ) {
        Tree tree = new Tree();
        tree.buildTreeFromListWall3D( unsorted );
        model.setTree( tree );
    }

    public List<Wall> getTreeAsFlatWall(){
        Tree tree = model.getTree();
        if (tree==null) { model.refreshPixels();}
        Wall3D frontWall = tree.getCenterWall().get(0);
        Plane plane = new Plane( frontWall.getOne(), frontWall.getTwo(), frontWall.getThree());
        int i = plane.checkSideIsAtRightSide(new Point3D(0.0, 0.0, model.getD()));

        List<Wall3D> sorted;
        if ( i < 0.1 ) { sorted=tree.getInOrder(); System.out.println("InOrder" );}
        else     { sorted=tree.getPreOrder(); System.out.println("PreOrder" ); }

        // *** FLAT WALL3D *** -> Wall ( 4xPIXEL )
        List<Wall> listWall = new ArrayList<>();
        for ( Wall3D wall3D : sorted ){

            List<Pixel> corners = getPixels_of_ProjectedPoints(wall3D.getPoints());
            listWall.add( new Wall ( corners.get(0),corners.get(1),corners.get(2),corners.get(3), wall3D.getColor() ));
        }
        return listWall;
        //model.setWalls( listWall );
    }









}
