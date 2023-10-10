package pl.heinzelman.javaDraw.model;

public class Pixel {
    private Long x;
    private Long y;

    public Pixel(Long x, Long y) {
        this.x = x;
        this.y = y;
    }

    public Long getX() { return x; }
    public Long getY() { return y; }

    public void setX(Long x) { this.x = x; }
    public void setY(Long y) { this.y = y; }

    @Override
    public String toString() {
        return "Pixel{ x=" + x + ", y=" + y + '}';
    }
}
