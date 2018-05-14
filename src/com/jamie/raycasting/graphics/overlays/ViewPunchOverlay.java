package com.jamie.raycasting.graphics.overlays;

import java.util.Random;

public class ViewPunchOverlay extends Overlay
{
    private Random random = new Random();

    public ViewPunchOverlay(int width, int height) {
        super(width, height);
    }

    public void update(double magnitude, String type) {
        int colour0 = 0, colour1 = 0;
        switch (type) {
            case "blunt":
                colour0 = 0x505020;
                colour1 = 0x707070;
                break;
            case "poison":
                colour0 = 0x205020;
                colour1 = 0x007000;
                break;
            case "fire":
                colour0 = 0xFF2000;
                colour1 = 0xFF7000;
                break;
            default:
                colour0 = 0x201010;
                colour1 = 0x801010;
        }

        for (int i = 0; i < pixels.length; i++) {
            double xp = ((i % width) - width / 2.0) / width * 2;
            double yp = ((i / width) - height / 2.0) / height * 2;
            if (random.nextDouble() < magnitude * Math.sqrt(xp * xp + yp * yp)) {
                if (random.nextBoolean()) {
                    pixels[i] = colour0;
                } else {
                    pixels[i] = colour1;
                }
            } else {
                pixels[i] = 0;
            }
        }
    }
}
