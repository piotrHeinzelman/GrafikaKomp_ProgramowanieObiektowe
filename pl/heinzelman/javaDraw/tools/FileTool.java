package pl.heinzelman.javaDraw.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileTool {

    public static List<String> getListOfString( String fileName ){
        if (fileName==null) return null;
        List<String> lines = new ArrayList<>(20);
        try{
            File file = new File ( fileName );
            if ( file.exists() && file.canRead() && !file.isDirectory() ){
                Scanner scanner = new Scanner(file);
                if ( scanner.hasNextLine() ) scanner.nextLine();  // skip first line
                while ( scanner.hasNextLine() ){
                    lines.add( ( scanner.nextLine().toString()) );
                }
                scanner.close();
            }
        } catch( FileNotFoundException e ){
            System.out.println( "FileNotFoundException"+e ); }
        return lines;
    }


}
