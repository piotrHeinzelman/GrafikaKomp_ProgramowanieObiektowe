package pl.heinzelman.javaDraw.actions;

import pl.heinzelman.javaDraw.controller.Controller;
import pl.heinzelman.javaDraw.model.Model;
import pl.heinzelman.javaDraw.view.Window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class myBar extends JMenuBar {

    JMenu menuFile    = new JMenu("File");
    JMenu menuControl = new JMenu("Control");
    JMenu menuHelp    = new JMenu("Help");

    public Action actOpen = null;

    public Action actLeft = null;
    public Action actRight = null;
    public Action actUp = null;
    public Action actDown = null;
    public Action actIn = null;
    public Action actOut = null;

    public Action rotLeft = null;
    public Action rotRight = null;
    public Action rotUp = null;
    public Action rotDown = null;

    public myBar( Window win, Model model, Controller controller ) {
        super();
        actOpen = new myAction( "Open", controller, "loadPointsFromFile" , KeyStroke.getKeyStroke( KeyEvent.VK_O, ActionEvent.CTRL_MASK ));

        actLeft  = new myAction( "Left",  controller, "a_LEFT"  , KeyStroke.getKeyStroke( KeyEvent.VK_LEFT , 0 ));
        actRight = new myAction( "Right", controller, "a_RIGHT" , KeyStroke.getKeyStroke( KeyEvent.VK_RIGHT , 0 ));
        actUp    = new myAction( "Up",    controller, "a_UP"    , KeyStroke.getKeyStroke( KeyEvent.VK_UP , 0 ));
        actDown  = new myAction( "Down",  controller, "a_DOWN"  , KeyStroke.getKeyStroke( KeyEvent.VK_DOWN , 0 ));
        actIn    = new myAction( "Zoom In",  controller, "a_IN" , KeyStroke.getKeyStroke( KeyEvent.VK_UP , 0 ));
        actOut   = new myAction( "Zoom Out", controller, "a_OUT", KeyStroke.getKeyStroke( KeyEvent.VK_DOWN , 0 ));

        rotLeft  = new myAction( "Rotate Left",  controller, "r_LEFT"  , KeyStroke.getKeyStroke( KeyEvent.VK_LEFT , ActionEvent.CTRL_MASK ));
        rotRight = new myAction( "Rotate Right", controller, "r_RIGHT" , KeyStroke.getKeyStroke( KeyEvent.VK_RIGHT, ActionEvent.CTRL_MASK ));
        rotUp    = new myAction( "Rotate Up",    controller, "r_UP"    , KeyStroke.getKeyStroke( KeyEvent.VK_UP   , ActionEvent.CTRL_MASK ));
        rotDown  = new myAction( "Rotate Down",  controller, "r_DOWN"  , KeyStroke.getKeyStroke( KeyEvent.VK_DOWN , ActionEvent.CTRL_MASK ));


        menuFile.add( actOpen );

        menuControl.add( actLeft );
        menuControl.add( actRight );
        menuControl.add( actUp );
        menuControl.add( actDown );
        menuControl.add( actIn );
        menuControl.add( actOut );
        menuControl.add( "-" );
        menuControl.add( rotLeft );
        menuControl.add( rotRight );
        menuControl.add( rotUp );
        menuControl.add( rotDown );
        menuControl.add( "-" );

        add( menuFile );
        add( menuControl );
        add( menuHelp );
    }
}

