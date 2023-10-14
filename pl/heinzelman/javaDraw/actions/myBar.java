package pl.heinzelman.javaDraw.actions;

import pl.heinzelman.javaDraw.controller.Controller;
import pl.heinzelman.javaDraw.model.Model;
import pl.heinzelman.javaDraw.model.Translate;
import pl.heinzelman.javaDraw.view.Window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class myBar extends JMenuBar {

    JMenu menuFile    = new JMenu("File");
    JMenu menuConfig = new JMenu("Config");
    JMenu menuControl = new JMenu("Control");
    JMenu menuHelp    = new JMenu("Help");

    public myBar( Window win, Model model, Controller controller ) {
        super();
        Action actOpen = new myAction( "Open", controller, "loadPointsFromFile",KeyStroke.getKeyStroke( KeyEvent.VK_O, ActionEvent.CTRL_MASK ), null );
   //     Action actConfig = new myAction( "Config", controller, "callConfig"     , KeyStroke.getKeyStroke( KeyEvent.VK_G, ActionEvent.CTRL_MASK ), null );
        Action actQuit = new myAction( "Quit", win, "quit"             , KeyStroke.getKeyStroke( KeyEvent.VK_Q, ActionEvent.CTRL_MASK ), null );

        Action actLeft  = new myAction( "Left",  controller, "callDoTranslatePoint"  , KeyStroke.getKeyStroke( KeyEvent.VK_LEFT , 0 ) , Translate.LEFT  );
        Action actRight = new myAction( "Right", controller, "callDoTranslatePoint"  , KeyStroke.getKeyStroke( KeyEvent.VK_RIGHT, 0 ) , Translate.RIGHT );
        Action actUp    = new myAction( "Up",    controller, "callDoTranslatePoint"  , KeyStroke.getKeyStroke( KeyEvent.VK_UP   , 0 ) , Translate.UP    );
        Action actDown  = new myAction( "Down",  controller, "callDoTranslatePoint"  , KeyStroke.getKeyStroke( KeyEvent.VK_DOWN , 0 ) , Translate.DOWN  );
        Action actIn    = new myAction( "Zoom In",  controller, "callDoTranslatePoint",KeyStroke.getKeyStroke( KeyEvent.VK_UP   , 0 ) , Translate.IN    );
        Action actOut   = new myAction( "Zoom Out", controller, "callDoTranslatePoint",KeyStroke.getKeyStroke( KeyEvent.VK_DOWN , 0 ) , Translate.OUT   );

        Action rotLeft  = new myAction( "Rotate Left",  controller, "callDoTranslatePoint" , KeyStroke.getKeyStroke( KeyEvent.VK_LEFT , ActionEvent.CTRL_MASK ) ,Translate.ROT_LEFT );
        Action rotRight = new myAction( "Rotate Right", controller, "callDoTranslatePoint" , KeyStroke.getKeyStroke( KeyEvent.VK_RIGHT, ActionEvent.CTRL_MASK ) ,Translate.ROT_RIGHT );
        Action rotUp    = new myAction( "Rotate Up",    controller, "callDoTranslatePoint" , KeyStroke.getKeyStroke( KeyEvent.VK_UP   , ActionEvent.CTRL_MASK ) ,Translate.ROT_UP );
        Action rotDown  = new myAction( "Rotate Down",  controller, "callDoTranslatePoint" , KeyStroke.getKeyStroke( KeyEvent.VK_DOWN , ActionEvent.CTRL_MASK ) ,Translate.ROT_DOWN );

        Action rotCW  = new myAction( "Rotate CW",    controller, "callDoTranslatePoint"   , KeyStroke.getKeyStroke( KeyEvent.VK_LEFT  , ActionEvent.ALT_MASK ) , Translate.ROT_CW );
        Action rotCCW  = new myAction( "Rotate CCW",  controller, "callDoTranslatePoint"   , KeyStroke.getKeyStroke( KeyEvent.VK_RIGHT , ActionEvent.ALT_MASK ) , Translate.ROT_CCW );

        menuFile.add( actOpen );
        menuFile.add( new JSeparator() );
        menuFile.add( actQuit );

        menuControl.add( actLeft );
        menuControl.add( actRight );
        menuControl.add( actUp );
        menuControl.add( actDown );
        menuControl.add( actIn );
        menuControl.add( actOut );
        menuControl.add( new JSeparator() );
        menuControl.add( rotLeft );
        menuControl.add( rotRight );
        menuControl.add( rotUp );
        menuControl.add( rotDown );
        menuControl.add( new JSeparator() );
        menuControl.add( rotCW );
        menuControl.add( rotCCW );

        add( menuFile );
        add( menuConfig );
        add( menuControl );
        add( menuHelp );
    }
}

