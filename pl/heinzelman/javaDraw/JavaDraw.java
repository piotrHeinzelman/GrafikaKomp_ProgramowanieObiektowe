package pl.heinzelman.javaDraw;

import pl.heinzelman.javaDraw.actions.LoadFileAction;
import pl.heinzelman.javaDraw.tools.FileTool;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.util.List;

public class JavaDraw{
	
	public static void main(String[] args) {


		if ( args.length > 0  )  {
			List<String> lines = FileTool.getListOfString(args[0]);
			for ( String s : lines ){
				System.out.println( s );
			}
		}

		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout()); //setLayout(new GridLayout(3, 2));
		frame.setSize(300, 400); // Change width and height as needed
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		LoadFileAction loadFileAction = new LoadFileAction();


		JMenuBar menuBar = new JMenuBar();
		menuBar.add( new JMenuItem( loadFileAction ));

		frame.add( new JButton( loadFileAction ));

		frame.setJMenuBar( menuBar );

		//MyComponent component = new MyComponent();
	//	frame.add(component);
		frame.setVisible(true);
		}
	}