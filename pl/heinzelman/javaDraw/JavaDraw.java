package pl.heinzelman.javaDraw;

import pl.heinzelman.javaDraw.actions.myBar;
import pl.heinzelman.javaDraw.controller.Controller;
import pl.heinzelman.javaDraw.model.Model;
import pl.heinzelman.javaDraw.view.Window;
import pl.heinzelman.javaDraw.view.View;

import javax.swing.*;

public class JavaDraw{
	
	public static void main(String[] args) {

		Window win = new Window();
		Model model = new Model();
		View view = new View( model , win );
		Controller controller = new Controller( model, view );

		JMenuBar menuBar = new myBar( win, model, controller );
		win.setJMenuBar( menuBar );
		win.add(view);

		/*  setup first screen  */
		if ( args.length > 0  )  { controller.loadPointsFromFile( args[0] ); } /* ShortCut !*/ else  controller.loadPointsFromFile( "G:\\JavaDraw\\dataWykres.txt" );

		win.setVisible(true);

	}
	}