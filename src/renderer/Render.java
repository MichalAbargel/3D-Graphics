package renderer;

import Primitives.Color;
import Primitives.Ray;
import elements.Camera;
import geometries.Intersectable;
import secens.Scene;

import java.util.List;
import java.util.MissingResourceException;

public class Render {
    ImageWriter imageWriter;
    Scene scene;
    Camera camera;
    RayTracerBase rayTracerBase;

    public Render setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    public Render setScene(Scene scene) {
        this.scene = scene;
        return this;
    }

    public Render setCamera(Camera camera) {
        this.camera = camera;
        return this;
    }

    public Render setRayTracerBase(RayTracerBase rayTracerBase) {
        this.rayTracerBase = rayTracerBase;
        return this;
    }
    public void renderImage(){
        if(this.camera==null)
            throw new MissingResourceException("","","");
        if(this.imageWriter==null)
            throw new MissingResourceException("","","");
        if(this.scene==null)
            throw new MissingResourceException("","","");
        if(this.rayTracerBase==null)
            throw new MissingResourceException("","","");
        //rendering the image
        int nX = this.imageWriter.getNx();
        int nY = this.imageWriter.getNy();
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                Ray ray = this.camera.constructRayThroughPixel(nX, nY, j, i);
                Color pixelColor = this.rayTracerBase.traceRay(ray);
                this.imageWriter.writePixel(j, i, pixelColor);
            }
        }

    }

    public void printGrid(int interval, Color color){
        if(this.imageWriter==null)
            throw new MissingResourceException("","","");
        int nX = this.imageWriter.getNx();
        int nY = this.imageWriter.getNy();
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                if (i % interval == 0 || j % interval == 0) {
                    this.imageWriter.writePixel(j, i, color);
                }
            }
        }
    }

    public void writeToImage(){
        if(this.imageWriter==null)
            throw new MissingResourceException("","","");
        this.imageWriter.writeToImage();
    }
}
