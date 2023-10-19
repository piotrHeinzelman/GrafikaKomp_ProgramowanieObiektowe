package pl.heinzelman.javaDraw.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *  Klasa wczytuje pliki tekstowe z lista punktów
 *  2D i 3D zależnie od wybranej strategii
 *  (strategia Kamera - CameraStrategy, strategia Wykres - ChartStrategy)
 *
 *  @author Piotr Heinzelman
 */
public class FileTool {

    /**
     * Klasa wczytuje plik
     * model.point.
     * @param fileName - nazwa pliku do wczytania
     * @return zwraca listę linii wczytanych z pliku.
     * metoda rzuca wyjątek FileNotFoundException w sytuacji braku pliku
     */
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
