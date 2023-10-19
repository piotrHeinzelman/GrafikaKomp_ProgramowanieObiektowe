package pl.heinzelman.javaDraw.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

/**
 *  Prosta implementacja obiektu JFrame
 *
 *  @author Piotr Heinzelman
 */

public class Window extends JFrame {
    /**
     * bezargumentowy konstruktor
     *
     * @throws HeadlessException
     */
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

        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    public void quit(){
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        // Bye...
    }
}
