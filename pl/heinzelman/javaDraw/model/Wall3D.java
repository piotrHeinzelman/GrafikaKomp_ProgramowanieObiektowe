package pl.heinzelman.javaDraw.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Wall3D {
    Point3D one;
    Point3D two;
    Point3D three;
    Point3D four;
    Color color;

    public Wall3D(Point3D one, Point3D two, Point3D three, Point3D four, Color color) {
        this.one = one;
        this.two = two;
        this.three = three;
        this.four = four;
        this.color = color;
    }

    public List<Point> getPoints() {
        List<Point> asList = new ArrayList<>();
        asList.add(one);
        asList.add(two);
        asList.add(three);
        asList.add(four);
        return asList;
    }

    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }

    @Override  public String toString() { return "{one=" + one + ", two=" + two + ", three=" + three + ", four=" + four + "}"; }

    public Point3D getOne()   { return one;   }
    public Point3D getTwo()   { return two;   }
    public Point3D getThree() { return three; }
    public Point3D getFour()  { return four;  }
}
