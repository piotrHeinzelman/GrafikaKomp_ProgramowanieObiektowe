package pl.heinzelman.javaDraw;

import pl.heinzelman.javaDraw.actions.myBar;
import pl.heinzelman.javaDraw.controller.Controller;
import pl.heinzelman.javaDraw.model.Model;
import pl.heinzelman.javaDraw.model.Point;
import pl.heinzelman.javaDraw.model.Point3D;
import pl.heinzelman.javaDraw.strategy.Matrix;
import pl.heinzelman.javaDraw.view.View;
import pl.heinzelman.javaDraw.view.Window;

import javax.swing.*;

public class JavaDraw{
	
	public static void main(String[] args) {

		/* test
		Point p = new Point3D(1.0, 2.0, 3.0, 4.0);

		Matrix one =new Matrix();
		System.out.println( one );

		Matrix move = Matrix.getMoveMatrix( 1.0,2.1,3.2 );
		System.out.println( move );

		Matrix rotX = Matrix.getRotateYMatrix( 3.14 ); // podstawa 3.14
		System.out.println( rotX );

		Matrix my = move.mul( move ).mul( rotX );

		System.out.println( p.mul( my ) );
		if ( true ) return;
		*/

		Window win   = new Window();
		Model  model = new Model();
		View   view  = new View( model , win );
		Controller controller = new Controller( model, view );

		JMenuBar menuBar = new myBar( win, model, controller );
		win.setJMenuBar( menuBar );
		win.add(view);

		/*  setup first screen  */
		//if ( args.length > 0  )  { controller.loadPointsFromFile( args[0] ); } /* ShortCut !*/ else  controller.loadPointsFromFile( "G:\\JavaDraw\\dataWykres.txt" );
		if ( args.length > 0  )  { controller.loadPointsFromFile( args[0] ); } /* ShortCut !*/ else { controller.setCameraStrategy(); controller.loadPointsFromFile( "G:\\JavaDraw\\dataCam.txt" ); }

		win.setVisible(true);

	}
	}