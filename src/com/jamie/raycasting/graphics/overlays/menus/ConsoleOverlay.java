package com.jamie.raycasting.graphics.overlays.menus;

import com.jamie.jamapp.App;
import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.app.Client;
import com.jamie.raycasting.app.Console;
import com.jamie.raycasting.graphics.overlays.Overlay;
import com.jamie.raycasting.input.Controls;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class ConsoleOverlay extends Overlay
{
    private Bitmap historyPane;
    private String command = "";
    private List<String> commandHistory = new ArrayList<String>();
    private int reverseHistoryIndex = 0;

    public ConsoleOverlay() {
        super((int) (App.getDisplayWidth() * 0.8), (int) (App.getDisplayHeight() * 0.6));
    }

    public void resizeOverlay() {
        super.setSize((int) (App.getDisplayWidth() * 0.8), (int) (App.getDisplayHeight() * 0.6));
    }

    public void setSize(int width, int height) {
        super.setSize(width, height);
        historyPane = new Bitmap(width, height - (bp + lineHeight() + bp));
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
                    ke.getKeyCode() != KeyEvent.VK_UP &&
                    ke.getKeyCode() != KeyEvent.VK_DOWN &&
                    ke.getKeyCode() != KeyEvent.VK_LEFT &&
                    ke.getKeyCode() != KeyEvent.VK_RIGHT &&
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
            commandHistory.add(command);
            reverseHistoryIndex = 0;
            command = "";
        }
        if (Client.input.check(Controls.UP)) {
            Client.input.stopInput(Controls.UP);
            if (reverseHistoryIndex < commandHistory.size()) {
                reverseHistoryIndex++;
                command = commandHistory.get(commandHistory.size() - reverseHistoryIndex);
            }
        }
        if (Client.input.check(Controls.DOWN)) {
            Client.input.stopInput(Controls.DOWN);
            if (reverseHistoryIndex > 1) {
                reverseHistoryIndex--;
                command = commandHistory.get(commandHistory.size() - reverseHistoryIndex);
            } else if (reverseHistoryIndex > 0) {
                reverseHistoryIndex--;
                command = "";
            }
        }
    }

    public void update() {
        super.update();

        fill(0x202020);

        historyPane.setSize(width, (Console.getLines().size() * lineHeight() + bp));
        historyPane.fill(0x101010);

        for (int i = 0; i < Console.getLines().size(); i++) {
            historyPane.draw(Console.getLines().get(i), bp, (lineHeight() * (i)) + bp, 0x707070);
        }
        draw(historyPane, 0, (height - bp - bp - lineHeight()) - historyPane.height);

        draw(command + "_", bp, height - bp - lineHeight() + bp, 0xD0D0D0);
    }
}
