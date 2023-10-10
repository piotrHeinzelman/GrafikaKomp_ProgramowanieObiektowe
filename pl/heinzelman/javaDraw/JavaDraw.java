package pl.heinzelman.javaDraw;

import pl.heinzelman.javaDraw.controller.Controller;
import pl.heinzelman.javaDraw.model.Model;

public class JavaDraw{
	
	public static void main(String[] args) {
		Model model = new Model();
		Controller controller = new Controller( model );

		if ( args.length > 0  )  { controller.loadPointsFromFile( args[0] ); } /* ShortCut !*/ else  controller.loadPointsFromFile( "G:\\JavaDraw\\dataWykres.txt" );


		controller.clearPixels();
		controller.createPixelFromPoints();



	}
	}