package pl.heinzelman.javaDraw;

import pl.heinzelman.javaDraw.trash.LoadFileAction;
import pl.heinzelman.javaDraw.tools.FileTool;
import pl.heinzelman.javaDraw.trash.MyJFrame;
import pl.heinzelman.javaDraw.trash.Rys1;

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


		//MyJFrame rootFrame = new MyJFrame();

	}
	}