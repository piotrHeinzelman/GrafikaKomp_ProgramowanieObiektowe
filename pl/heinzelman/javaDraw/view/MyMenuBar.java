package pl.heinzelman.javaDraw.view;

import javax.swing.*;

public class MyMenuBar extends JMenuBar {

     JMenu menu1 = new JMenu("File");
     JMenu menu2 = new JMenu("Control");
     JMenu menu3 = new JMenu("Help");

    public MyMenuBar() {
        super();

        add(menu1);
        add(menu2);
        add(menu3);

        menu1.add(  new JMenuItem( "a 1" ) );
        menu1.add(  new JMenuItem( "a 2" ) );
        menu1.add(  new JMenuItem( "a 3" ) );

        menu2.add(  new JMenuItem( "b 1" ) );
        menu2.add(  new JMenuItem( "b 2" ) );
        menu2.add(  new JMenuItem( "b 3" ) );


    }

}
