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

    public Wall3D[] splitByPlane(Plane plane ){
        int[] signs=new int[4];
        signs[0]= plane.checkSideIsAtRightSide(one);
        signs[1]= plane.checkSideIsAtRightSide(two);
        signs[2]= plane.checkSideIsAtRightSide(three);
        signs[3]= plane.checkSideIsAtRightSide(four);
        //System.out.println( "signs: "+signs[0]+","+signs[1]+","+signs[2]+","+signs[3] );

        Wall3D over = new Wall3D( this.one,this.two,this.three,this.four,this.color );
        Wall3D under = new Wall3D( this.one,this.two,this.three,this.four,this.color );

        Point3D one_two=null;
        Point3D two_three=null;
        Point3D three_four=null;
        Point3D four_one=null;
        if ( signs[0]!=signs[1] ){  one_two =  plane.getPointOfPlane( one  , two   ); }
        if ( signs[1]!=signs[2] ){  two_three =  plane.getPointOfPlane( two  , three ); }
        if ( signs[2]!=signs[3] ){  three_four =  plane.getPointOfPlane( three, four  ); }
        if ( signs[3]!=signs[1] ){  four_one =  plane.getPointOfPlane( four , one   ); }

        Wall3D up=null; Wall3D down=null;
        if ( one_two!=null && three_four!=null ){
                if ( signs[0]==1 ) { up=over; down=under;  } else { up=under; down=over; }
                up.two=one_two; down.one=one_two;
                up.three=three_four; down.four=three_four;
                return new Wall3D[]{ up , down };
        }
        if ( two_three!=null && four_one!=null ){
            if ( signs[0]==1 ) { up=over; down=under;  } else { up=under; down=over; }
            up.three=two_three; down.two=two_three;
            up.four=four_one;   down.one=four_one;
            return new Wall3D[]{ up , down };
        }
        return null;
    }



    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }

    @Override  public String toString() { return "{one=" + one + ", two=" + two + ", three=" + three + ", four=" + four + "}"; }

    public Point3D getOne()   { return one;   }
    public Point3D getTwo()   { return two;   }
    public Point3D getThree() { return three; }
    public Point3D getFour()  { return four;  }
}
