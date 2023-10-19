package pl.heinzelman.javaDraw.tools;

import pl.heinzelman.javaDraw.controller.Controller;
import pl.heinzelman.javaDraw.model.*;
import pl.heinzelman.javaDraw.model.Point;
import pl.heinzelman.javaDraw.strategy.CameraStrategy;
import pl.heinzelman.javaDraw.strategy.Matrix;
import pl.heinzelman.javaDraw.strategy.ProjectionStrategy;
import pl.heinzelman.javaDraw.view.View;
import pl.heinzelman.javaDraw.view.Window;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 *  Klasa testująca operacje matematyczne
 *  i współdziałanie obiektów
 *
 *  @author Piotr Heinzelman
 */

public class TestClass {
	// obliczenia, szkice

	private final Window win;
	private final Model model;
	private final Controller controller;
	private final View view;

	public TestClass(Window win, Model model, Controller controller, View view) {
		this.win = win;
		this.model = model;
		this.controller = controller;
		this.view = view;

		//One();
		//Two();
		Three();
	}



	public void Three() {

		Wall3D wall3D   = new Wall3D(new Point3D(-20.0, -20.0, 180.0), new Point3D(20.0, -20.0, 180.0), new Point3D(20.0, 20.0, 180.0), new Point3D(-20.0, 20.0, 180.0), new Color(0, 128, 255));
		//Wall3D wall3D = new Wall3D(new Point3D(-20.0, -0.01, 80.0), new Point3D( 20.0, -0.01, 80.0), new Point3D( 20.0, 20.0, 80.0 ), new Point3D(-20.0, 20.0, 80.0), new Color( 255, 0, 255 ));
		//Plane plane   = new Plane( new Point3D( 30.0,  3.0,  80.0),  new Point3D( 10.0, 3.0, 100.0 ),new Point3D( 10.0,  3.0,  80.0));
		Plane plane   = new Plane( new Point3D( 0.0,  10.0,  80.0), new Point3D( 0.0,  0.0,  80.0), new Point3D( 0.0, 0.0, 100.0 ));

		System.out.println( plane.checkSideIsAtRightSide( wall3D.getOne()   ) );
		System.out.println( plane.checkSideIsAtRightSide( wall3D.getTwo()   ) );
		System.out.println( plane.checkSideIsAtRightSide( wall3D.getThree() ) );
		System.out.println( plane.checkSideIsAtRightSide( wall3D.getFour()  ) );

		Wall3D[] splittedWallUp = wall3D.splitByPlane(plane);

		System.out.println( wall3D );
		System.out.println( "Split1"+splittedWallUp[0] );
		System.out.println( "Split2"+splittedWallUp[1] );

		model.getWalls3D().add(splittedWallUp[0]);
		model.getWalls3D().add(splittedWallUp[1]);
		List<Wall3D> listWall3D = new ArrayList<>();
		listWall3D.add(splittedWallUp[0]);
		listWall3D.add(splittedWallUp[1]);

		ProjectionStrategy str =new CameraStrategy( model );

		System.out.println( "listWall3D: "+listWall3D );
		List<Wall> walls = str.SortAndFlatWall3D(listWall3D);

		model.clearAll();
		model.TEST_ONLY_getPoints().add( splittedWallUp[0].getOne() );
		model.TEST_ONLY_getPoints().add( splittedWallUp[0].getTwo() );
		model.TEST_ONLY_getPoints().add( splittedWallUp[0].getThree() );
		model.TEST_ONLY_getPoints().add( splittedWallUp[0].getFour() );

		System.out.println( plane.checkSideIsAtRightSide( splittedWallUp[0].getOne()   ) );
		System.out.println( plane.checkSideIsAtRightSide( splittedWallUp[0].getTwo()   ) );
		System.out.println( plane.checkSideIsAtRightSide( splittedWallUp[0].getThree() ) );
		System.out.println( plane.checkSideIsAtRightSide( splittedWallUp[0].getFour()  ) );

		System.out.println( plane.checkSideIsAtRightSide( splittedWallUp[1].getOne()   ) );
		System.out.println( plane.checkSideIsAtRightSide( splittedWallUp[1].getTwo()   ) );
		System.out.println( plane.checkSideIsAtRightSide( splittedWallUp[1].getThree() ) );
		System.out.println( plane.checkSideIsAtRightSide( splittedWallUp[1].getFour()  ) );

		model.TEST_ONLY_getPoints().add( splittedWallUp[1].getOne() );
		model.TEST_ONLY_getPoints().add( splittedWallUp[1].getTwo() );
		model.TEST_ONLY_getPoints().add( splittedWallUp[1].getThree() );
		model.TEST_ONLY_getPoints().add( splittedWallUp[1].getFour() );

		model.refreshPixels();

		//System.out.println( "List<Wall>: "+walls );
		//view.drawListOfWall( walls );
		view.repaint();
		//win.setVisible(true);

	}

	public void Two(){

		List<Point> points = model.TEST_ONLY_getPoints();
		Point3D p0 = (Point3D) points.get(0);
		Point3D p1 = (Point3D) points.get(1);
		Point3D p2 = (Point3D) points.get(4);



		//model.clearAll();
		//points.add(  new Point3D( 12.0,12.0,100.0 ) );
		//points.add(  new Point3D( 30.0,10.0,100.0 ) );
		//points.add(  new Point3D( 30.0,10.0,80.0 ) );



		Plane Pi = new Plane (p0, p1, p2);
		Vector3D.getNormal(new Vector3D(p1, p0), new Vector3D(p1, p2));

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
