package pl.heinzelman.javaDraw.controller;

import pl.heinzelman.javaDraw.model.Model;
import pl.heinzelman.javaDraw.tools.FileTool;

public class Controller {
    public final Model model;

    public Controller( Model model ) {
        this.model = model;
    }


    public void clearPoint(){
        model.clearPoint();
    }

    public void loadPointsFromFile( String fileName ){
        FileTool ft = new FileTool();
                for ( String s : ft.getListOfString( fileName )){
                    model.addPoint( s.split(","));
                }
    }

    public void clearPixels(){
        model.clearPixels();
    }
    public void createPixelFromPoints(){ model.createPixelFromPoints(); }
    public void setScreenRange( Long minX, Long maxX, Long minY, Long maxY ){
        model.setScreenRange(  minX,  maxX,  minY,  maxY );
    }


    // *****  ACTIONS CALL **********
    public void a_LEFT(){
        System.out.println( this.getClass().getName() +  ".a_LEFT();" );
    }

}
