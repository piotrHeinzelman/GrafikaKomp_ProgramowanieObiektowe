package pl.heinzelman.javaDraw.view;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    public Window() throws HeadlessException {

        try
        {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
        JFrame.setDefaultLookAndFeelDecorated(true);

        setLayout(new FlowLayout()); //setLayout(new GridLayout(3, 2));
        setSize(800, 600); // Change width and height as needed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
    }
}
