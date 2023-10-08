import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Project{
	
	public static void main(String[] args) {

		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout()); //setLayout(new GridLayout(3, 2));
		frame.setSize(300, 400); // Change width and height as needed
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenu menuFile = new JMenu("File");
		JMenu menuFileOpen = new JMenu("Open" );
		    //  menuFileOpen.

		menuFile.add( menuFileOpen );

		JMenuBar menuBar = new JMenuBar();
		menuBar.add( menuFile );

		frame.setJMenuBar( menuBar );

		MyComponent component = new MyComponent();
		frame.add(component);
		frame.setVisible(true);
		}
	}