import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

public class MyComponent extends JComponent
{
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        // Your drawing instructions go here
        g.drawRect(1,1,10,10);
    }
}