package pl.heinzelman.javaDraw;

import pl.heinzelman.javaDraw.controller.Controller;
import pl.heinzelman.javaDraw.model.Model;
import pl.heinzelman.javaDraw.model.Swatch;
import pl.heinzelman.javaDraw.model.Window;
import pl.heinzelman.javaDraw.view.MyMenuBar;
import pl.heinzelman.javaDraw.view.View;

import javax.swing.*;
import java.awt.*;

public class JavaDraw{
	
	public static void main(String[] args) {

		Window win = new Window();

		Model model = new Model();
		Controller controller = new Controller( model );
		View view = new View( model );

		if ( args.length > 0  )  { controller.loadPointsFromFile( args[0] ); } /* ShortCut !*/ else  controller.loadPointsFromFile( "G:\\JavaDraw\\dataWykres.txt" );


		controller.clearPixels();
		controller.setScreenRange( 0L,800L,0L,600L );
		controller.createPixelFromPoints();

		view.setColor( Swatch.R.getJColor(Swatch.R) );
		view.setStroke(3);


		win.setJMenuBar( new MyMenuBar() );
		win.add(view);
		win.setVisible(true);

	}
	}