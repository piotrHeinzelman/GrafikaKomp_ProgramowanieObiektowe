package pl.heinzelman.javaDraw.strategy;

import pl.heinzelman.javaDraw.model.Edge;
import pl.heinzelman.javaDraw.model.Pixel;
import pl.heinzelman.javaDraw.model.Point;
import pl.heinzelman.javaDraw.model.Wall;

import java.util.List;

public interface ProjectionStrategy {
    public List<Pixel> getPixels_of_ProjectedPoints(List<Point> points );
    public List<Edge>  getEdgesOfPixels( List<Pixel> pixels );
    public List<Wall>  getWallsOfPixels(List<Pixel> pixels);
    public List<Point> translatePoints( List<Point> points , Translate translate );
}
