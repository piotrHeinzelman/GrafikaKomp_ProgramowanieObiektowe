package pl.heinzelman.javaDraw.trash;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class LoadFileAction extends AbstractAction {

    public LoadFileAction() {

        super("Load");
       // putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_L));
         putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK) );
       // putValue(SELECTED_KEY, new Integer(KeyEvent.VK_L));
    }

    public void actionPerformed(ActionEvent e ) {
        System.out.println("CALL Action loadFileAction" + e);
    }
}




