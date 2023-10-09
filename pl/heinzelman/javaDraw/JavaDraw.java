package pl.heinzelman.javaDraw;

import pl.heinzelman.javaDraw.actions.LoadFileAction;
import pl.heinzelman.javaDraw.tools.FileTool;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class JavaDraw{
	
	public static void main(String[] args) {


		if ( args.length > 0  )  {
			List<String> lines = FileTool.getListOfString(args[0]);
			for ( String s : lines ){
				System.out.println( s );
			}
		}

		//JFrame.setDefaultLookAndFeelDecorated(true);

		try
		{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
		JFrame.setDefaultLookAndFeelDecorated(true);





		JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout()); //setLayout(new GridLayout(3, 2));
		frame.setSize(300, 400); // Change width and height as needed
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		LoadFileAction loadFileAction = new LoadFileAction();


		JMenuBar menuBar = new JMenuBar();
		menuBar.add( new JMenuItem( loadFileAction ));
		//open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));

		frame.add( new JButton( loadFileAction ));

		frame.setJMenuBar( menuBar );

		//MyComponent component = new MyComponent();
	//	frame.add(component);
		frame.setVisible(true);


		// OPEN
		JFileChooser jFileChooser = new JFileChooser();
		jFileChooser.showDialog( frame , "Open data file *.txt");

		// SaveDialog
		JFileChooser jFileChooser1 = new JFileChooser();
		jFileChooser1.showDialog( frame , "SaveFile *.bmp" );

		Canvas canvas = new Rys1();
		canvas.setBackground( new Color( 0,0,255 ) );

		frame.add(  canvas );



	}
	}