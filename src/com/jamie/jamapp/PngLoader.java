package com.jamie.jamapp;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class PngLoader
{
    public static Map<String, Bitmap> cache = new HashMap<>();
    public static boolean enableCaching = true;


    public static Bitmap loadBitmap(String fileName) {
        if (enableCaching) {
            if (cache.containsKey(fileName)) {
                return cache.get(fileName);
            }
        }

        try {
            BufferedImage img = ImageIO.read(PngLoader.class.getClassLoader().getResource(fileName));
            int width = img.getWidth();
            int height = img.getHeight();
            Bitmap result = new Bitmap(width, height);
            img.getRGB(0, 0, width, height, result.pixels, 0, width);

            for (int i = 0; i < result.pixels.length; i++) {
                if (result.pixels[i] == 0xFFFF00FF) {
                    result.pixels[i] = Bitmap.INVISIBLE;
                }
            }

            if (enableCaching) {
                cache.put(fileName, result);
            }
            return result;
        } catch (Exception e) {
            System.out.println("Could not read image from file name: " + fileName);
            throw new RuntimeException(e);
        }
    }
}
