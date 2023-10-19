package pl.heinzelman.javaDraw.model;
/**
 *  Klasa wektora 3D
 *  @author Piotr Heinzelman
 */
public class Vector3D {
    private Double x;
    private Double y;
    private Double z;


    public Vector3D( Double x, Double y, Double z ) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3D( Point3D start, Point3D end ) {
        x=end.getX()-start.getX();
        y=end.getY()-start.getY();
        z=end.getZ()-start.getZ();
    }


    /**
     * Obliczanie normalnej do płaszczyzny
     * @param a wektor leżący na płaszczyźnie
     * @param b wektor leżący na płaszczyźnie
     * @return zwraca obiekt wektor ormalny do płaszczyzny
     */
    public static Vector3D getNormal( Vector3D a, Vector3D b ){
        return new Vector3D( a.y*b.z - a.z* b.y , a.z* b.x - a.x* b.z , a.x*b.y - a.y*b.x  );
    }




    @Override public String toString() { return "{x=" + x + ", y=" + y + ", z=" + z + '}'; }
    public Double getX() { return x; } public Double getY() { return y; } public Double getZ() { return z; }
}
