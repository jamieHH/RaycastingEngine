package com.jamie.jamapp;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sfx
{
    public Clip clip;

    public void play() {
        play(1f);
    }


    public void play(double volume) {
        if (App.getSoundEnabled()) {
            if (volume > 0f) {
                try {
                    if (clip != null) {
                        new Thread(() -> {
                            synchronized (clip) {
                                clip.stop();
                                clip.setFramePosition(0);

                                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                                gainControl.setValue(20f * (float) Math.log10(volume));

                                clip.start();
                            }
                        }).start();
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else if (volume > 1f) {
                throw new IllegalArgumentException("Volume not valid: " + volume);
            }
        }
    }
}
