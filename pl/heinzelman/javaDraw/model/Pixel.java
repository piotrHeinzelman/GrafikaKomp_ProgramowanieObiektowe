package pl.heinzelman.javaDraw.model;
/**
 * Realizacja płaskiego Pixela punktu na ekranie
 *
 *  @author Piotr Heinzelman
 */
public class Pixel {
    private int x;
    private int y;

    public Pixel(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

    @Override
    public String toString() {
        return "Pixel{ x=" + x + ", y=" + y + '}';
    }
}
