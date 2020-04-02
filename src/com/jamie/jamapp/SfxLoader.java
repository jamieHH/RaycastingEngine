package com.jamie.jamapp;

import javax.sound.sampled.*;
import java.util.HashMap;
import java.util.Map;

public class SfxLoader
{
    public static Map<String, Sfx> cache = new HashMap<>();
    public static boolean enableCaching = false;


    public static Sfx loadSound(String fileName) {
        if (enableCaching) {
            if (cache.containsKey(fileName)) {
                return cache.get(fileName);
            }
        }

        try {
            Sfx sound = new Sfx();
            AudioInputStream ais = AudioSystem.getAudioInputStream(SfxLoader.class.getClassLoader().getResource(fileName));
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            sound.clip = clip;

            if (enableCaching) {
                cache.put(fileName, sound);
            }
            return sound;
        } catch (Exception e) {
            System.out.println("Could not read sound from file name: " + fileName);
            throw new RuntimeException(e);
        }
    }
}
