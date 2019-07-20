package com.jamie.jamapp;

import javax.sound.sampled.Clip;

public class Sfx
{
    public Clip clip;

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
