package renderer;

import elements.Camera;
import geometries.Intersectable;
import primitives.Color;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

import java.util.MissingResourceException;

/**
 * this class job its to create from the scene the matrix of colors of the picture
 * @author Salay Shalom Shuker 311600605 salayshuker@gmail.com
 * @author Shimon Mizrahi 203375563 shimonshimi4@gmail.com
 */

public class Render {
    public ImageWriter _imageWriter = null;
    public Camera _camera = null;
    public RayTracerBase _rayTracerBase = null;

    /**
     * chain method
     *
     * @param imageWriter imageWriter object to define
     * @return Render
     */
    public Render setImageWriter(ImageWriter imageWriter) {
        _imageWriter = imageWriter;
        return this;
    }


    /**
     * chain method
     *
     * @param camera camera object to define
     * @return Render
     */


    public Render setCamera(Camera camera) {
        _camera = camera;
        return this;
    }


    /**
     * chain method
     *
     * @param rayTracerBase rayTracerBase object to define
     * @return Render
     */


    public Render setRayTracer(RayTracerBase rayTracerBase) {
        _rayTracerBase = rayTracerBase;
        return this;

    }

    /**
     * This function renders image's pixel color map from the scene included with
     * the Renderer object
     */


    public void renderImage() {
        //rendering the image
        try {
            if (_imageWriter == null) {
                throw new MissingResourceException("Missing resource ", ImageWriter.class.getName(), "");
            }
            if (_camera == null) {
                throw new MissingResourceException("Missing resource ", Camera.class.getName(), "");
            }
            if (_rayTracerBase == null) {
                throw new MissingResourceException("Missing resource ", RayTracerBase.class.getName(), "");
            }
            /**
             * loop on all the pixels of the view plane
             * for any pixel this will build ray and for any ray this receive color from the color scanner
             * we will put the color in the right pixel of writePixel
             */
            int nX = _imageWriter.getNx();
            int nY = _imageWriter.getNy();
            for (int i = 0; i < nY; i++) {
                for (int j = 0; j < nX; j++) {
                    Ray ray = _camera.constructRayThroughPixel(nX, nY, j, i);
                    // get color of pixel through the ray pixel
                    Color pixelColor = _rayTracerBase.traceRay(ray);
                    //_imageWriter will paint the right color of the pixel
                    _imageWriter.writePixel(j, i, pixelColor);

                }
            }
        } catch (MissingResourceException e) {
            throw new UnsupportedOperationException("Not implement yed" + e.getClassName());
        }
    }

    /**
     * This function writings grid for the image
     *
     * @param interval Determines the density of the grid
     * @param color    the grid color.
     */
    public void printGrid(int interval, Color color) {

//print only grid

        int nX = _imageWriter.getNx();
        int nY = _imageWriter.getNy();

        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                if (i % interval == 0 || j % interval == 0) {
                    _imageWriter.writePixel(i, j, color);
                }

            }

        }
        _imageWriter.writeToImage();
    }

    /**
     * This function is Writing the image the in the imageBuffer
     */

    public void writeToImage() {
        _imageWriter.writeToImage();
    }

}