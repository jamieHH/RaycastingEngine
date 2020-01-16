package com.jamie.raycasting.graphics.overlays.menus;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.app.Client;
import com.jamie.raycasting.app.Console;
import com.jamie.raycasting.graphics.overlays.Overlay;
import com.jamie.raycasting.input.Controls;

import java.awt.event.KeyEvent;

public class ConsoleOverlay extends Overlay
{
    private Bitmap historyPane;
    private String command = "";

    public ConsoleOverlay(int width, int height) {
        super(width, height);
        setSize(width, height);
        opacity = 75;
    }

    public void setSize(int width, int height) {
        super.setSize(width, height);
        historyPane = new Bitmap(width - bp - bp, height - (bp + getLineHeight() + bp));
    }

    public void tick() {
        super.tick();

        KeyEvent ke = Client.input.grabLastKeyEvent();
        if (ke != null) {
            if (ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                if (command.length() > 0) {
                    command = command.substring(0, command.length() - 1);
                }
            } else if (
                    ke.getKeyCode() != KeyEvent.VK_ENTER &&
                    ke.getKeyCode() != KeyEvent.VK_SHIFT &&
                    ke.getKeyCode() != KeyEvent.VK_BACK_QUOTE &&
                    ke.getKeyCode() != KeyEvent.VK_ESCAPE
            ) {
                command += ke.getKeyChar();
            }
        }

        if (Client.input.check(Controls.ENTER)) {
            Client.input.stopInput(Controls.ENTER);
            Console.run(command);
            command = "";
        }
    }

    public void update() {
        super.update();

        fill(0x202020);

        historyPane.setSize(width, (Console.getLines().size() * getLineHeight()));
        historyPane.fill(0x101010);

        for (int i = 0; i < Console.getLines().size(); i++) {
            historyPane.draw(Console.getLines().get(i), bp, (getLineHeight() * (i)), 0x707070);
        }
        draw(historyPane, 0, (height - bp - bp - getLineHeight()) - historyPane.height);

        draw(command + "_", bp, height - bp - getLineHeight() + 2, 0xD0D0D0);
    }
}
