package com.jamie.raycasting.graphics.overlays;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.app.Client;
import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.input.Controls;

public class TextOverlay extends Overlay
{
    public TextOverlay(int width, int height, String message) {
        super(width, height);
        int maxWidth = 100;
        int textCol = 0xD0D0D0;
        int borderCol = 0x202020;
//        int borderCol = 0;
        int bgCol = 0x202020;
//        int bgCol = 0;

        Bitmap wText = textBoxTrimmed(message, maxWidth, textCol, bgCol);
        Bitmap tWindow = new Bitmap(wText.width + 4, wText.height + 4);
        tWindow.fill(borderCol);
        tWindow.draw(wText, tWindow.halfWidth() - wText.halfWidth(), tWindow.halfHeight() - wText.halfHeight());

        draw(tWindow, this.halfWidth() - tWindow.halfWidth(), this.halfHeight() - tWindow.halfHeight());
    }

    public void tick() {
        if (Client.input.check(Controls.ACTION) || Client.input.check(Controls.ENTER)) {
            Client.input.stopInput(Controls.ACTION);
            Client.input.stopInput(Controls.ENTER);
            Sound.clickAction.play();
            Client.setActiveOverlay(null);
        }
    }
}
