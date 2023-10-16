package pl.heinzelman.javaDraw.tools;

import pl.heinzelman.javaDraw.controller.Controller;
import pl.heinzelman.javaDraw.model.*;
import pl.heinzelman.javaDraw.strategy.Matrix;
import pl.heinzelman.javaDraw.view.Window;

import java.util.List;

public class TestClass {
	// obliczenia, szkice

	private final Window win;
	private final Model model;
	private final Controller controller;

	public TestClass(Window win, Model model, Controller controller) {
		this.win = win;
		this.model = model;
		this.controller = controller;

		//One();
		Two();

	}

	public void Two(){

		List<Point> points = model.TEST_ONLY_getPoints();
		Point3D p0 = (Point3D) points.get(4);
		Point3D p1 = (Point3D) points.get(5);
		Point3D p2 = (Point3D) points.get(6);



		//model.clearAll();
		//points.add(  new Point3D( 12.0,12.0,100.0 ) );
		//points.add(  new Point3D( 30.0,10.0,100.0 ) );
		//points.add(  new Point3D( 30.0,10.0,80.0 ) );



		Plane Pi = new Plane (p0, p1, p2);
		Vector3D.getNormal(new Vector3D(p1, p0), new Vector3D(p1, p2))

		System.out.println( Pi );



		for (int x=-10;x<50;x+=5){
			for ( int z=70;z<110;z+=5){
				points.add(   Pi.getPointXZOnPlane( 1.0*x, 1.0*z ) );
				points.add(   Pi.getPointXZOnPlane( 1.0*x, 1.0*z ) );
				points.add(   Pi.getPointXZOnPlane( 1.0*x, 1.0*z ) );
				points.add(   Pi.getPointXZOnPlane( 1.0*x, 1.0*z ) );
				points.add(   Pi.getPointXZOnPlane( 1.0*x, 1.0*z ) );
				points.add(   Pi.getPointXZOnPlane( 1.0*x, 1.0*z ) );
			}
		}

		System.out.println( points );
/*
		Vector3D v1 = new Vector3D( p1,p0 );
		Vector3D v2 = new Vector3D( p2,p0 );
		Vector3D vNormal = Vector3D.getNormal(v1, v2);
		System.out.println( vNormal );
 */
		model.refreshPixels();
		controller.getView().repaint();

	}








	public void One(){
		// test MxP
		Point p = new Point3D(1.0, 2.0, 3.0, 4.0);

		Matrix one =new Matrix();
		System.out.println( one );

		Matrix move = Matrix.getMoveMatrix( 1.0,2.1,3.2 );
		System.out.println( move );

		Matrix rotX = Matrix.getRotateYMatrix( 3.14 ); // podstawa 3.14
		System.out.println( rotX );

		Matrix my = move.mul( move ).mul( rotX );

		System.out.println( p.mul( my ) );
		win.quit();
	}



}
