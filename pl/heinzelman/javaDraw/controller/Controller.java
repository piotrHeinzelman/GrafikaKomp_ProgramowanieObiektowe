package pl.heinzelman.javaDraw.controller;

import pl.heinzelman.javaDraw.model.Model;
import pl.heinzelman.javaDraw.strategy.CameraStrategy;
import pl.heinzelman.javaDraw.strategy.ChartStrategy;
import pl.heinzelman.javaDraw.strategy.Translate;
import pl.heinzelman.javaDraw.tools.FileTool;
import pl.heinzelman.javaDraw.view.View;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 *  Kontroler z modelu MVC,
 *  manipuluje modelem i wywołuje metody obiekty widok
 *  by narysować obraz danych z modelu.
 *  ustawia strategię rysowania, oraz parametry rysowania
 *  czyli kolor linii i jej grubość
 *
 *  @author Piotr Heinzelman
 */
public class Controller {
    public static final JFileChooser CH = new JFileChooser();
    public final Model model;
    public final View  view;

    /**
     * Konstruktor
     * @param model - referencja do obiekt modelu MVC
     * @param view  - referencja do obiektu widoku
     */
    public Controller( Model model, View view ) {
        this.model = model;
        this.view = view;
        model.setStrategy( new ChartStrategy( model ) );
    }

    public void setChartStrategy() { view.turnOnAxis();  model.setStrategy( new ChartStrategy ( model ) ); view.repaint(); }
    public void setCameraStrategy(){ view.turnOffAxis(); model.setStrategy( new CameraStrategy( model ) ); view.repaint();}


    // *****  ACTIONS CALL **********
    public void callDoTranslatePoint ( Translate translate ){
        model.translatePoints( translate );
        model.refreshPixels();
        view.repaint();
    }


    /**
     * ładowanie pliku
     */
    public void loadPointsFromFile(){
        JFileChooser ch = new JFileChooser();
                     ch.showOpenDialog( null );
        File selectedFile = ch.getSelectedFile();
        if (selectedFile!=null) {
            loadPointsFromFile(selectedFile.getAbsolutePath());
        }
    }

    /**
     *  ładowanie pliku o podanej nazwie.
     *
     * @param fileName - nazwa pliku z danymi.
     */
    public void loadPointsFromFile( String fileName ){
        model.clearAll();
        Boolean chart=null;
        FileTool ft = new FileTool();
        for ( String s : ft.getListOfString( fileName )){
            model.addPoint( s.split(","));
        }
        if ( model.isChartStrategy() ) {
            model.calculateModelScale();
            model.createAxisEdge();
            view.turnOnAxis();
        } else {
            view.turnOffAxis();
        }
        model.refreshPixels();
        view.repaint();
    }


    public void incWidth(){ view.incStroke(); view.repaint(); }
    public void decWidth(){ view.decStroke(); view.repaint(); }
    public void colSetRed()  { view.setColor( new Color(255,0,0)); view.repaint(); }
    public void colSetGreen(){ view.setColor( new Color(0,255,0)); view.repaint(); }
    public void colSetBlue() { view.setColor( new Color(0,0,255)); view.repaint(); }
    public void togglePoint() { view.togglePoint(); view.repaint(); }
    public void toggleEdge() { view.toggleEdge(); view.repaint(); }
    public void toggleWall() { view.toggleWall(); view.repaint(); }


    @Deprecated  public View getView() { return view; }
}
