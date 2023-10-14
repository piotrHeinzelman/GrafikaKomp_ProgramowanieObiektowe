package pl.heinzelman.javaDraw.controller;

import pl.heinzelman.javaDraw.model.Model;
import pl.heinzelman.javaDraw.model.Translate;
import pl.heinzelman.javaDraw.strategy.CameraStrategy;
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

    public void setChartStrategy() { model.setStrategy( new ChartStrategy ( model ) ); }
    public void setCameraStrategy(){ model.setStrategy( new CameraStrategy( model ) ); }





    // *****  ACTIONS CALL **********
    public void callDoTranslatePoint(Translate translate ){
        System.out.println( this.getClass().getName() +  ".translate;" + translate );
    }




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
        model.resetModelScale();
        model.setPixels( model.getPixels_of_ProjectedPoints( model.getPoints() ));
        model.setEdges ( model.getEdgesOfPixels( model.getPixels() ));
        view.repaint();
    }

    public void actConfig(){
        System.out.println( "Config !" );
    }




}
