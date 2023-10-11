package pl.heinzelman.javaDraw.actions;

import pl.heinzelman.javaDraw.controller.Controller;
import pl.heinzelman.javaDraw.model.Model;
import pl.heinzelman.javaDraw.model.Window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class myBar extends JMenuBar {

    JMenu menuFile    = new JMenu("File");
    JMenu menuControl = new JMenu("Control");
    JMenu menuHelp    = new JMenu("Help");

    public Action actLeft = null;

    public myBar( Window win, Model model, Controller controller ) {
        super();
        actLeft = new myAction( "Left", controller, "a_LEFT" , KeyStroke.getKeyStroke( KeyEvent.VK_O, ActionEvent.CTRL_MASK ));

        menuControl.add( actLeft );

        add( menuFile );
        add( menuControl );
        add( menuHelp );
    }
}

