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



    public void buildTreeFromListWall3D( List<Wall3D> unsorted ){
        Plane plane=null;
        for ( Wall3D w : unsorted ){
            if ( plane==null ) { getCenterWall().add(w); plane = new Plane( w.getOne(), w.getTwo(), w.getThree() ); }
            else {
                Integer det=null;
                det=( 2+plane.checkSideIsAtRightSide( w.getOne()  )) *1000+
                    ( 2+plane.checkSideIsAtRightSide( w.getTwo()  )) *100 +
                    ( 2+plane.checkSideIsAtRightSide( w.getThree())) *10  +
                    ( 2+plane.checkSideIsAtRightSide( w.getFour() )) *1 ;
                //System.out.println( det );
                switch ( det ){

                    case 3333: case 2233: case 3322: case 3223: case 2332:// over
                            getRightList() .add( w ); break; // over

                    case 2222:  // the same
                            getCenterWall().add( w ); break;

                    case 1111: case 2112: case 2211: case 1221: case 1122: // under
                            getLeftList()  .add( w ); break;

                    case 3113: case 3311: case 1133: case 1331:   // split
                            { // split wall3D
                            Wall3D[] walls=w.splitByPlane( plane );
                            for (int i=0;i<walls.length;i++){
                                Wall3D fragment =walls[i];
                                int tempDet=plane.checkSideIsAtRightSide( fragment.getOne() )*1000+plane.checkSideIsAtRightSide( fragment.getTwo() )*100+plane.checkSideIsAtRightSide( fragment.getThree() )*10+plane.checkSideIsAtRightSide( fragment.getFour() );
                                //System.out.println( "tempDet"+tempDet );
                                if ( tempDet>0 ) { getLeftList().add( fragment ); } else { getRightList().add( fragment ); }
                            }
                    };
                         break;
                    default:
                        System.out.println( "Wall3D::buildTreeFromListWall3D: nie obsługiwany CASE: "+det );
                }
            }
        }

        if ( leftList.size()>0 ){ // recurrention built left tree
            if ( left==null ) { left = new Tree(); }
            left.buildTreeFromListWall3D( leftList );
        }
        if ( rightList.size()>0 ){ // recurrention built right tree
            if ( right==null ) { right = new Tree(); }
            right.buildTreeFromListWall3D( rightList );
        }
    }


    public int size(){
        return centerWall.size() + ( left==null ? 0 : left.size() ) + ( right==null ? 0 : right.size()) ;
    }

    public List<Wall3D> getInOrder(){
        System.out.println( "inOrder" );
        List<Wall3D> list=new ArrayList<>();
        addListoListReverse( list , getPostOrder() );
        return list;
    }

    public List<Wall3D> getPostOrder(){
        //System.out.println( "postOrder" );
        List<Wall3D> list=new ArrayList<>();
        // RIGHT
        if (right!=null) {
            addListoList( list , right.getPostOrder() );
        }
        //CENTER
        addListoList( list , centerWall );
        //LEFT
        if (left!=null) {
            addListoList( list , left.getPostOrder() );
        }
        return list;
    }

    private void addListoList(List<Wall3D> target , List<Wall3D> source ){
        if (source==null) return;
        for ( Wall3D wall : source ){
            target.add( wall );
        }
    }

    private void addListoListReverse(List<Wall3D> target , List<Wall3D> source ){
        if (source==null) return;
        for ( int i=source.size()-1; i>-1; i--){
            target.add ( source.get(i) );
        }
    }

}





