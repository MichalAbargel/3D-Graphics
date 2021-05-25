package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

/**
 * Test writing a image
 *
 */


class ImageWriterTest {

    @Test
    void testWriteToImage() {
        // create new image for test
        ImageWriter imageWriter= new ImageWriter("testTube",800,500);
        for (int i = 0; i <800 ; i++) {
            for (int j = 0; j < 500; j++) {
                //800 /16 = 50
                if (i%50==0) {
                    imageWriter.writePixel(i,j, Color.BLACK);
                }
                //500 /10 = 50
                else if (j%50==0){
                    imageWriter.writePixel(i,j, Color.BLACK);
                }
                else {
                    imageWriter.writePixel(i,j,Color.BLUE); //new Color(0d,0d,255d) =BLUE
                }

            }
        }
        imageWriter.writeToImage();

    }
}