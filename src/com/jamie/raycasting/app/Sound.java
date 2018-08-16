package com.jamie.raycasting.app;

import javax.sound.sampled.*;
import java.io.File;

public class Sound
{
    public static Sound clickUp = loadSound("sfx/clickUp.wav");
    public static Sound clickDown = loadSound("sfx/clickDown.wav");
    public static Sound clickAction = loadSound("sfx/clickAction.wav");
    public static Sound hit = loadSound("sfx/hit.wav");
    public static Sound pain = loadSound("sfx/pain.wav");
    public static Sound die = loadSound("sfx/die.wav");
    public static Sound smash = loadSound("sfx/smash.wav");
    public static Sound pickUp = loadSound("sfx/pickUp.wav");
    public static Sound slideUp = loadSound("sfx/slideUp.wav");
    public static Sound slideDown = loadSound("sfx/slideDown.wav");
    public static Sound switchLevel = loadSound("sfx/switchLevel.wav");

    private Clip clip;


    private static Sound loadSound(String fileName) {
        Sound sound = new Sound();
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

    public void play() {
        if (App.soundEnabled) {
            try {
                if (clip != null) {
                    new Thread(() -> {
                        synchronized (clip) {
                            clip.stop();
                            clip.setFramePosition(0);
                            clip.start();
                        }
                    }).start(); // multithreading sound on linux causes the system to run out of sound handles
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
