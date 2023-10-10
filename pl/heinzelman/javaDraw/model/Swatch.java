package pl.heinzelman.javaDraw.model;

import java.awt.*;

public enum Swatch {
//colorCode=R White|grAy |Black|Red|Green|bLue|Cyan|Magenta|Yellow

    W, A, B, R, G, L, C, M, Y;

    private static Color black = new Color(0x00,0x00,0x00);
    private static Color gray  = new Color(0x7F,0x7F,0x7F);
    private static Color white = new Color(0xFF,0xFF,0xFF);
    private static Color yellow= new Color(0xFF,0xFF,0x00);
    private static Color red   = new Color(0xFF,0x00,0x00);
    private static Color magenta=new Color(0xFF,0x00,0xFF);
    private static Color blue  = new Color(0x00,0x00,0xFF);
    private static Color cyan  = new Color(0x00,0xFF,0xFF);
    private static Color green = new Color(0x00,0xFF,0x00);

    
    public Color getJColor(Swatch swatch ){
        switch ( swatch ){
            case B: return black;
            case W: return white;
            case Y: return yellow;
            case R: return red;
            case M: return magenta;
            case L: return blue;
            case C: return cyan;
            case G: return green;
            default: return gray;
        }
    }
}
