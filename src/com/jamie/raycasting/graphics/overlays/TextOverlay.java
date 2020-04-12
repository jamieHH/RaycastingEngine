package com.jamie.raycasting.graphics.overlays;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.app.Client;
import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.input.Controls;

public class TextOverlay extends Overlay
{
    private int showTime = 60;
    private String message;

    private int maxWidth = 100;
    private int textCol = 0xD0D0D0;
    private int borderCol = 0x202020;
    private int bgCol = 0x202020;

    public TextOverlay(int width, int height, String message) {
        super(width, height);
        this.message = message;
    }

    public void tick() {
        if (showTime > 0) {
            showTime--;
            return;
        }

        if (Client.input.check(Controls.ACTION) || Client.input.check(Controls.ENTER)) {
            Client.input.stopInput(Controls.ACTION);
            Client.input.stopInput(Controls.ENTER);
            Sound.clickAction.play();
            Client.setActiveOverlay(null);
        }

        textCol = 0x707070;
    }

    public void update() {
        Bitmap wText = textBoxTrimmed(message, maxWidth, textCol, bgCol);
        Bitmap tWindow = new Bitmap(wText.width + 4, wText.height + 4);
        tWindow.fill(borderCol);
        draw(drawCenter(tWindow, wText), this.halfWidth() - tWindow.halfWidth(), this.halfHeight() - tWindow.halfHeight());
    }
}
