package unittests;

import Primitives.Color;
import org.junit.Test;
import renderer.ImageWriter;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

public class ImageWriterTest{
    @Test

    public void writeToImageTest(){
        int nx=800;
        int ny=500;
        int gapX=nx/16;
        int gapY=ny/10;
        ImageWriter image=new ImageWriter("ImageTest",800,500);
            for (int i = 0; i < image.getNx(); i++) {
                for (int j = 0; j < image.getNy(); j++) {
                    if (i % 50 == 0 || j % 50 == 0)
                        image.writePixel(i, j, new Color(255,165,0));
                    else
                        image.writePixel(i, j,new Color(0, 0, 205) );

                }
            }
        image.writeToImage();

    }

}

