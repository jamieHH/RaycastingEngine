package com.jamie.raycasting.graphics.overlays.menus;

import com.jamie.raycasting.app.Client;
import com.jamie.raycasting.app.Console;
import com.jamie.raycasting.graphics.overlays.Overlay;
import com.jamie.raycasting.input.Controls;

import java.util.ArrayList;
import java.util.List;

public class ConsoleOverlay extends Overlay
{
    private List<String> commands = new ArrayList<String>();
    private String command;

    public ConsoleOverlay(int width, int height) {
        super(width, height);
    }

    public void tick() {
        super.tick();

        command = Client.input.getTypedString();

        if (Client.input.check(Controls.ENTER)) {
            Console.run(command);
            commands.add(command);
            command = "";
        }

//        if (!Client.input.getIsTyping()) {
//            closeOverlay();
//        }
    }

    public void closeOverlay() {
        Client.input.setIsTyping(false);
        Client.setActiveOverlay(null);
    }

    public void update() {
        fill(0x101010);

        super.update();
    }
}
