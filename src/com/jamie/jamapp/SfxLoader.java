package com.jamie.jamapp;

import javax.sound.sampled.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SfxLoader
{
    public static Map<String, Sfx> cache = new HashMap<String, Sfx>();


    public static Sfx loadSound(String fileName) {
        if (cache.containsKey(fileName)) {
            return cache.get(fileName);
        }

        try {
            Sfx sound = new Sfx();
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File("res/" + fileName));
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            sound.clip = clip;

            cache.put(fileName, sound);
            return sound;
        } catch (Exception e) {
            System.out.println("Could not read sound from file name: " + fileName);
            throw new RuntimeException(e);
        }
    }
}
