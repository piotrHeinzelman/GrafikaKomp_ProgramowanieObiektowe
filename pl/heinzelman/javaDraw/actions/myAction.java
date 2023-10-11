package pl.heinzelman.javaDraw.actions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class myAction extends AbstractAction  {

    private Object obj;
    private String methodName;

    public myAction( String name, Object obj, String methodName, KeyStroke keyStroke ) {
        super(name);
        this.obj = obj;
        this.methodName = methodName;
        putValue(ACCELERATOR_KEY, keyStroke );
    }

    @Override
    public void actionPerformed( ActionEvent e ) {
        try {
            Method method2Call = obj.getClass().getMethod( methodName );
            method2Call.invoke( obj , null );
        }
        catch (NoSuchMethodException ex)     { throw new RuntimeException(ex); }
        catch (InvocationTargetException ex) { throw new RuntimeException(ex); }
        catch (IllegalAccessException ex)    { throw new RuntimeException(ex); }
    }
}
