package pl.heinzelman.javaDraw;

import pl.heinzelman.javaDraw.actions.myBar;
import pl.heinzelman.javaDraw.controller.Controller;
import pl.heinzelman.javaDraw.model.Model;
import pl.heinzelman.javaDraw.view.View;
import pl.heinzelman.javaDraw.view.Window;

import javax.swing.*;

/**
 *  JavaDraw - program rysujący wykres, pobierający
 *  punkty z pliku tekstowego
 *
 *  dodatkowo program może wykorzystując inną strategię
 *  rzutowania punktów narysować sześciany w przestrzeni 3D
 *
 *  @author Piotr Heinzelman 146703
 *  Politechnika Warszawska, Okno . 19.X.2023
 *
 */
public class JavaDraw{

	/**
	 *  główny program,
	 *  przygotowuje obiekty wzorca MVC
	 *  model, widok, kontroller
	 *  oraz okno głowne programu win.
	 *
	 *  ponadto przygotowuje belkę menu górnego
	 *  i pokazuje główne okno.
	 *
	 *
	 * @param args
	 * 	jeśli args[0] zawiera nazwę istniejęcego pliku
	 *  plik zostanie wczytany.
	 */

	public static void main(String[] args) {

		Window win   = new Window();
		Model  model = new Model();
		View   view  = new View( model , win );
		Controller controller = new Controller( model, view );

		JMenuBar menuBar = new myBar( win, model, controller );
		win.setJMenuBar( menuBar );
		win.add(view);



		/*  setup first screen  */
		if ( args.length > 0  )  { controller.loadPointsFromFile( args[0] ); } /* ShortCut !*/ else  controller.loadPointsFromFile( "G:\\JavaDraw\\dataWykres.txt" );
		//if ( args.length > 0  )  { controller.loadPointsFromFile( args[0] ); } /* ShortCut !*/ else { controller.setCameraStrategy(); controller.loadPointsFromFile( "G:\\JavaDraw\\dataCam.txt" ); }

		//TestClass test = new TestClass( win, model, controller , view);
		win.setVisible(true);

	}
	}