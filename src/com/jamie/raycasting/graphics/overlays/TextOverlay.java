package com.jamie.raycasting.graphics.overlays;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.app.Client;
import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.input.Controls;

public class TextOverlay extends Overlay
{
    private int showTime = 60;

    public TextOverlay(int width, int height, String message) {
        super(width, height);
        int maxWidth = 100;
        int textCol = 0xD0D0D0;
        int borderCol = 0x202020;
        int bgCol = 0x202020;

        Bitmap wText = textBoxTrimmed(message, maxWidth, textCol, bgCol);
        Bitmap tWindow = new Bitmap(wText.width + 4, wText.height + 4);
        tWindow.fill(borderCol);
        draw(drawCenter(tWindow, wText), this.halfWidth() - tWindow.halfWidth(), this.halfHeight() - tWindow.halfHeight());
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
    }
}
