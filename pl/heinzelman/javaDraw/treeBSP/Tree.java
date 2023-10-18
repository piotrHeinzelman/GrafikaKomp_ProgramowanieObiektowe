package pl.heinzelman.javaDraw.treeBSP;

import pl.heinzelman.javaDraw.model.Plane;
import pl.heinzelman.javaDraw.model.Wall3D;

import java.util.ArrayList;
import java.util.List;

public class Tree {
    private Tree left=null;
    private Tree right=null;
    private final List<Wall3D> unsortedWalls = new ArrayList<>();

    private final List<Wall3D> leftList   = new ArrayList<>();
    private final List<Wall3D> centerWall = new ArrayList<>();
    private final List<Wall3D> rightList  = new ArrayList<>();

    public List<Wall3D> getUnsortedWalls() { return unsortedWalls; }
    public List<Wall3D> getCenterWall()    { return centerWall; }
    public List<Wall3D> getLeftList() { return leftList; }
    public List<Wall3D> getRightList() { return rightList; }

    public void putOnTree( Wall3D w , Side side ){
        switch ( side ){
            case LEFT  -> { if (  left==null ) {   left=new Tree(); }}
            case RIGHT -> { if ( right==null ) {  right=new Tree(); }}
        }

        switch ( side ){
            case CENTER-> { centerWall.add( w ); }
            case LEFT  -> {  left.getUnsortedWalls().add( w ); }
            case RIGHT -> { right.getUnsortedWalls().add( w ); }
        }
        left.getUnsortedWalls().add( w );
    }



    public  Tree buildTreeFromListWall3D( List<Wall3D> unsorted ){
        Tree tree = new Tree();
        Plane plane=null;
        for ( Wall3D w : unsorted ){
            Integer det=null;
            if ( plane==null ) { tree.getCenterWall().add(w); plane = new Plane( w.getOne(), w.getTwo(), w.getThree() ); }
            else {
                det=( 2+plane.checkSideIsAtRightSide( w.getOne()  )) *1000+
                    ( 2+plane.checkSideIsAtRightSide( w.getTwo()  )) *100 +
                    ( 2+plane.checkSideIsAtRightSide( w.getThree())) *10  +
                    ( 2+plane.checkSideIsAtRightSide( w.getFour() )) *1 ;
            }
            switch ( det ){
                case 3333: tree.getRightList() .add( w ); break; // over
                case 2222: tree.getCenterWall().add( w ); break; // the same
                case 1111: tree.getLeftList()  .add( w ); break; // under
                default:
                    System.out.println( det );
            }

            if ( leftList.size()>0 ){ // recurrention built left tree
                if ( left==null ) { left = new Tree(); }
                left.buildTreeFromListWall3D( leftList );
            }
            if ( rightList.size()>0 ){ // recurrention built left tree
                if ( right==null ) { right = new Tree(); }
                right.buildTreeFromListWall3D( rightList );
            }
        }
        return tree;
    }



}





