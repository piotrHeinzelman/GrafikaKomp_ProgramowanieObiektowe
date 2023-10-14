package pl.heinzelman.javaDraw.strategy;

public class Matrix {
    private Double x1, x2, x3, x4;
    private Double y1, y2, y3 ,y4;
    private Double z1, z2, z3 ,z4;
    private Double d1, d2, d3, d4;

    public Matrix() {

    }

    public void setX(Double x1, Double x2, Double x3, Double x4) {
        this.x1 = x1; this.x2 = x2; this.x3 = x3; this.x4 = x4;
    }

    public void setY(Double y1, Double y2, Double y3, Double y4) {
        this.y1 = y1; this.y2 = y2; this.y3 = y3; this.y4 =y4;
    }

    public void setZ(Double z1, Double z2, Double z3, Double z4) {
        this.z1 = z1; this.z2 = z2; this.z3 = z3; this.z4 =z4;
    }


    public void setD(Double d1, Double d2, Double d3, Double d4) {
        this.d1 = d1; this.d2 = d2; this.d3 = d3; this.d4 =d4;
    }

}
