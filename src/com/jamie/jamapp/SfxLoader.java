package com.jamie.jamapp;

import javax.sound.sampled.*;
import java.io.File;

public class SfxLoader
{
    public static Sfx loadSound(String fileName) {
        Sfx sound = new Sfx();
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File("res/" + fileName));
            Clip clip = AudioSystem.getClip();
            clip.open(ais); // find a way to close for multi thread
            sound.clip = clip;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sound;
    }
}
