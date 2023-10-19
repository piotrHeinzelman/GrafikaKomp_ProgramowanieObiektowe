package pl.heinzelman.javaDraw.strategy;

import pl.heinzelman.javaDraw.model.*;

import java.util.List;

/**
 *   Interfejs ProjectionStrategy
 *   realizujący przetwarzanie punktów 3D
 *   na pixele ekranu - punkty 2D
 */
public interface ProjectionStrategy {
    public List<Pixel> getPixels_of_ProjectedPoints(List<Point> points );
    public List<Edge>  getEdgesOfPixels( List<Pixel> pixels );
    public List<Wall3D> getWallsOfPoints3D(List<Point> points);
    public List<Point> translatePoints(  List<Point> points , Translate translate );
    public List<Wall> SortAndFlatWall3D( List<Wall3D> unsorted );
}
