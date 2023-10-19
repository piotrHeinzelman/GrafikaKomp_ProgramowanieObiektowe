package pl.heinzelman.javaDraw.model;

/**
 *  Klasa płaszczyzna 3D w rozumieniu matematycznym
 *
 *  @author Piotr Heinzelman
 */

public class Plane {
    // Ax+By+Cz+D=0  Vnorm(a,b,c)
    //
    // przechodzaca przez punkt p0 rownolegla do 2 odcinkow
    //  x = x0 + tx1 +sx2
    //  y = y0 + ty1 +sy2
    //  z = z0 + tz1 +sz2


    private Double A;
    private Double B;
    private Double C;
    private Double D;

    public Plane( Double a, Double b, Double c, Double d ) {
        this.A = a;
        this.B = b;
        this.C = c;
        this.D = d;
    }

    public Plane( Vector3D normal , Point3D p0 ) {
        this.A = normal.getX();
        this.B = normal.getY();
        this.C = normal.getZ();
        //PI: A(x − x0) + B(y − y0) + C(z − z0) = 0 -> Ax -Ax0 +By -By0 +Cz -Cz0 =0 -> Ax+By+Cz + ( -Ax0 -By0 -Cz0 ) = 0 => ( -Ax0 -By0 -Cz0 ) = D
        this.D = ( -A*p0.getX() -B*p0.getY() -C*p0.getZ());
    }


    public Plane ( Point3D one, Point3D two, Point3D three ){
        Vector3D twoOne = new Vector3D( two, one );
        Vector3D twoThree = new Vector3D( two, three );
        Vector3D normal = Vector3D.getNormal( twoOne , twoThree );
        Plane P = new Plane ( normal , two );
        this.A = P.A;
        this.B = P.B;
        this.C = P.C;
        this.D = P.D;
    }

    public Point3D getPointXYOnPlane( Double x,Double y ){
        Point3D p = new Point3D( x, y, 0.0);
        //A*x + B*y +C*z + D = 0;
         Double z = (-A*x -B*y - D)/C;
         p.setZ( z );
         return p;
    }

    public Point3D getPointXZOnPlane( Double x,Double z ){
        Point3D p = new Point3D( x, 0.0, z);
        //A*x + B*y +C*z + D = 0;
        Double y = (-A*x -C*z - D)/B;
        p.setY( y );
        return p;
    }

    /**
     * Sprawdzenie czy punkt leży nad płaszczyzną
     * @param p2 punkt
     * @return kierunek wektora wiodącego
     */
    public int checkSideIsAtRightSide( Point3D p2 ){
        /* P2 o
              |  l= x=x0+ta / y=y0+tb / z=zo+tc  po - punkt prostej  wektor wzd. v=(a,b,c)
        ------|------
           P1 o        P1 nalezy do prostej, spełnia   P1x = P2x +tA / P1y = P2y +tB / P1z = P2z +tC
                       P1 nalezy do płaszczyzny spełnia Ax+By+Cz+D=0
                       zatem A(x2+tA) +B(y2+tB) +C(z2+tC) +D =0  nie znamy tylko t więc...
                       t*(A²+B²+C²)=D-Ax2-By2-Cy2
                       t= D-Ax2-By2-Cy2 / (A²+B²+C²);
     */

        Double ABC=A*p2.getX() +B*p2.getY() +C*p2.getZ();
        Double D_ABC = (D-ABC)/(A*A+B*B+C*C);
        //System.out.println( "ABC: "+ABC+", D: "+D+", D-ABC: "+(D-ABC)+ ", wynik: "+ D_ABC );
        if ( D_ABC  >  0.000001 ) return +1;  // ponad plaszczyzna
        if ( D_ABC  < -0.000001 ) return -1;  // pod plaszczyzna
        return 0;  // na tej samej plaszczyznie, uwzgledniajac bledy notacji zmiennoprzecinkowej
        // dokladnosc do 0.1
     //   if ((  A*p2.getX() +B*p2.getY() +C*p2.getZ() ) < D ) { return -1; } else { return +1; }

    }

    /**
     * pobiera punkt z odcinka leżący na płaszczyźnie
     * @param p0 - początek odcinka
     * @param p1 - koniec odcinka
     * @return - punkt wspólny odcinka i płaszczycny
     */
    public Point3D getPointOfPlane( Point3D p0, Point3D p1  ) {
        Double dx=p1.getX()-p0.getX();
        Double dy=p1.getY()-p0.getY();
        Double dz=p1.getZ()-p0.getZ();

        Double t= ( -A*p0.getX() -B*p0.getY() -C* p0.getZ() -D )/( A*dx+B*dy+C*dz );
        return new Point3D( p0.getX()+t*dx, p0.getY()+t*dy,p0.getZ()+t*dz );
    }



    @Override
    public String toString() {
        return "Plane{A=" + A + ", B=" + B +  ", C=" + C + ", D=" + D +  '}';
    }
}
