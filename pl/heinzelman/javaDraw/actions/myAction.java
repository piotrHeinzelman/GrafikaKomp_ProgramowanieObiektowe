package pl.heinzelman.javaDraw.actions;

import pl.heinzelman.javaDraw.strategy.Translate;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class myAction extends AbstractAction  {

    private Object obj;
    private String methodName;
    private Translate translate;

    public myAction(String name, Object obj, String methodName, KeyStroke keyStroke , Translate translate ) {
        super(name);
        this.obj = obj;
        this.methodName = methodName;
        putValue(ACCELERATOR_KEY, keyStroke );
        this.translate=translate;
    }

    @Override
    public void actionPerformed( ActionEvent e ) {
        try {
            if ( translate==null) {
                Method method2Call = obj.getClass().getMethod( methodName );
                method2Call.invoke( obj , null );
            }
            else {
                Method method2Call = obj.getClass().getMethod( methodName, Translate.class );
                method2Call.invoke( obj , translate );
            }
        }
        catch (NoSuchMethodException ex)     { throw new RuntimeException(ex); }
        catch (InvocationTargetException ex) { throw new RuntimeException(ex); }
        catch (IllegalAccessException ex)    { throw new RuntimeException(ex); }
    }
}
