package pl.heinzelman.javaDraw.actions;

import pl.heinzelman.javaDraw.strategy.Translate;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *  Implementacja klasy AbstractAction
 *  przygotowuje akcje do działania w modelu MVC.
 *
 *  @author Piotr Heinzelman
 */
public class myAction extends AbstractAction  {

    private Object obj;
    private String methodName;
    private Translate translate;

    /**
     * Konstruktor
     * @param name - nazwa w menu
     * @param obj  - obiekt którego metodę wykona akcja
     * @param methodName - metoda obiektu wołana przez akcję
     * @param keyStroke  - ewentualny skrót klawiszowy uruchamiający akcje.
     * @param translate  - ewentualny kierunek tranclacji.
     */
    public myAction(String name, Object obj, String methodName, KeyStroke keyStroke , Translate translate ) {
        super(name);
        this.obj = obj;
        this.methodName = methodName;
        putValue(ACCELERATOR_KEY, keyStroke );
        this.translate=translate;
    }

    /**
     * Uruchomienie działania akcji
     *
     * @param e the event to be processed
     *          zdarzenie wywułane po uruchomieniu akcji.
     *          obj.call.Method()...
     */
    @Override
    public void actionPerformed( ActionEvent e ) {
        try {
            if ( translate==null) {
                Method method2Call = obj.getClass().getMethod( methodName );
                method2Call.invoke( obj );
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
