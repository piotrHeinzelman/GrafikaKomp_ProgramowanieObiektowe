package pl.heinzelman.javaDraw.controller;

import pl.heinzelman.javaDraw.model.Model;
import pl.heinzelman.javaDraw.model.Point;
import pl.heinzelman.javaDraw.strategy.ChartStrategy;
import pl.heinzelman.javaDraw.tools.FileTool;
import pl.heinzelman.javaDraw.view.View;

import javax.swing.*;
import java.io.File;

public class Controller {
    public static final JFileChooser CH = new JFileChooser();
    public final Model model;
    public final View view;

    public Controller( Model model, View view ) {
        this.model = model;
        this.view = view;
        model.setStrategy( new ChartStrategy( model ) );
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
        model.clearPixels();
        model.clearPoints();
        FileTool ft = new FileTool();
        for ( String s : ft.getListOfString( fileName )){
            model.addPoint( s.split(","));
        }
        model.setModel();
        model.setPixels( model.getPixels_of_ProjectedPoints( model.getPoints() ));
        model.setEdges ( model.getEdgesOfPixels( model.getPixels() ));
        view.repaint();
    }





}
