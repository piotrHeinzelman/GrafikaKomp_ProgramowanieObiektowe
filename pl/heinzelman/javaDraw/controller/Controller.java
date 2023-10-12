package pl.heinzelman.javaDraw.controller;

import pl.heinzelman.javaDraw.model.Model;
import pl.heinzelman.javaDraw.tools.FileTool;
import pl.heinzelman.javaDraw.view.View;

import javax.swing.*;
import java.io.File;

public class Controller {
    public static final JFileChooser CH = new JFileChooser();
    public final Model model;
    public final View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }


    public void clearPoint(){
        model.clearPoint();
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


/*
a_LEFT
a_RIGHT
a_UP
a_DOWN
a_IN
a_OUT
r_LEFT
r_RIGHT
r_UP
r_DOWN
*/


    // ** load from file
    public void loadPointsFromFile(){
        JFileChooser ch = new JFileChooser();
                     ch.showOpenDialog( null );
        File selectedFile = ch.getSelectedFile();
        if (selectedFile!=null) {
            loadPointsFromFile(selectedFile.getAbsolutePath());
        }
    }

    public void loadPointsFromFile( String fileName ){
        clearPixels();
        view.repaint();
        FileTool ft = new FileTool();
        for ( String s : ft.getListOfString( fileName )){
            model.addPoint( s.split(","));
        }
        createPixelFromPoints();
        view.repaint();
    }
}
