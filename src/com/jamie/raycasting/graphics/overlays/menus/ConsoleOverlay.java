package com.jamie.raycasting.graphics.overlays.menus;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.app.Client;
import com.jamie.raycasting.app.Console;
import com.jamie.raycasting.graphics.overlays.Overlay;

public class ConsoleOverlay extends Overlay
{
    public ConsoleOverlay(int width, int height) {
        super(width, height);
        opacity = 75;
    }

    public void tick() {
        super.tick();

        if (!Client.input.getIsTyping()) {
            String command = Client.input.grabTypedString();
            Console.run(command);
        }
    }

    public void update() {
        super.update();
        fill(0x101010);
        fill(0, height - bp - Bitmap.getFontHeight() - bp, width, height, 0x202020);
        for (int i = 0; i < Console.getLines().size(); i++) {
            draw(Console.getLines().get(i), bp, bp + Bitmap.getFontHeight() * (i - 1), 0x707070);
        }
        draw(Client.input.getTypedString() + "_", bp, height - bp - Bitmap.getFontHeight(), 0xD0D0D0);
    }
}
