package pl.heinzelman.javaDraw.controller;

import pl.heinzelman.javaDraw.model.Model;
import pl.heinzelman.javaDraw.strategy.Translate;
import pl.heinzelman.javaDraw.strategy.CameraStrategy;
import pl.heinzelman.javaDraw.strategy.ChartStrategy;
import pl.heinzelman.javaDraw.tools.FileTool;
import pl.heinzelman.javaDraw.view.View;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Controller {
    public static final JFileChooser CH = new JFileChooser();
    public final Model model;
    public final View  view;

    public Controller( Model model, View view ) {
        this.model = model;
        this.view = view;
        model.setStrategy( new ChartStrategy( model ) );
    }

    public void setChartStrategy() { view.turnOnAxis();  model.setStrategy( new ChartStrategy ( model ) ); }
    public void setCameraStrategy(){ view.turnOffAxis(); model.setStrategy( new CameraStrategy( model ) ); }



    // *****  ACTIONS CALL **********
    public void callDoTranslatePoint ( Translate translate ){
        //System.out.println( this.getClass().getName() +  ".translate;" + translate );
        model.translatePoints( translate );
        model.refreshPixels();
        view.repaint();
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
        model.clearAll();
        FileTool ft = new FileTool();
        for ( String s : ft.getListOfString( fileName )){
            model.addPoint( s.split(","));
        }
        model.calculateModelScale();
        model.createAxisEdge();
        model.refreshPixels();
        view.repaint();
    }

    public void actConfig(){
        System.out.println( "Config !" );
        // TODO change width & color
    }


    public void incWidth(){ view.incStroke(); view.repaint(); }
    public void decWidth(){ view.decStroke(); view.repaint(); }
    public void colSetRed()  { view.setColor( new Color(255,0,0)); view.repaint(); }
    public void colSetGreen(){ view.setColor( new Color(0,255,0)); view.repaint(); }
    public void colSetBlue() { view.setColor( new Color(0,0,255)); view.repaint(); }
}
