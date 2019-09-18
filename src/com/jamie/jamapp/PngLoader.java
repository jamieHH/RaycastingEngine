package com.jamie.jamapp;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class PngLoader
{
    public static Map<String, Render> cache = new HashMap<String, Render>();
    public static boolean enableCaching = false;


    public static Render loadBitmap(String fileName) {
        if (enableCaching) {
            if (cache.containsKey(fileName)) {
                return cache.get(fileName);
            }
        }

        try {
            BufferedImage img = ImageIO.read(new FileInputStream("res/" + fileName));
            int width = img.getWidth();
            int height = img.getHeight();
            Render result = new Render(width, height);
            img.getRGB(0, 0, width, height, result.pixels, 0, width);

            for (int i = 0; i < result.pixels.length; i++) {
                if (result.pixels[i] == 0xFFFF00FF) {
                    result.pixels[i] = Render.INVISIBLE;
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

    public static Render setInvisible() {
        Render render = new Render(16, 16);
        for (int i = 0; i < render.pixels.length; i++) {
            render.pixels[i] = Render.INVISIBLE;
        }

        return render;
    }

    public static Render mergeBitmap(Render tex0, Render tex1) {
        Render texture = new Render(tex0.width, tex0.height);
        for (int i = 0; i < tex1.pixels.length; i++) {
            if (tex1.pixels[i] != Render.INVISIBLE) {
                texture.pixels[i] = tex1.pixels[i];
            } else {
                texture.pixels[i] = tex0.pixels[i];
            }
        }

        return texture;
    }
}
