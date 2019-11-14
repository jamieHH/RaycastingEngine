package com.jamie.raycasting.graphics.overlays.menus;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.app.Client;
import com.jamie.raycasting.app.Console;
import com.jamie.raycasting.graphics.overlays.Overlay;

public class ConsoleOverlay extends Overlay
{
    private Bitmap historyPane;

    public ConsoleOverlay(int width, int height) {
        super(width, height);
        setSize(width, height);
        opacity = 75;
    }

    public void setSize(int width, int height) {
        super.setSize(width, height);
        historyPane = new Bitmap(width - bp - bp, height - (bp + getFontHeight() + 2 + bp));
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

        fill(0x202020);

        historyPane.setSize(width, (Console.getLines().size() * Bitmap.getFontHeight()));
        historyPane.fill(0x101010);

        for (int i = 0; i < Console.getLines().size(); i++) {
            historyPane.draw(Console.getLines().get(i), bp, (Bitmap.getFontHeight() * (i)), 0x707070);
        }
        draw(historyPane, 0, (height - bp - bp - Bitmap.getFontHeight()) - historyPane.height);

        draw(Client.input.getTypedString() + "_", bp, height - bp - Bitmap.getFontHeight(), 0xD0D0D0);
    }
}
