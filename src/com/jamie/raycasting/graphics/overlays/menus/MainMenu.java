package com.jamie.raycasting.graphics.overlays.menus;

import com.jamie.jamapp.App;
import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.app.Client;
import com.jamie.raycasting.input.Controls;

import java.util.Arrays;
import java.util.List;

public class MainMenu extends Menu
{
    private boolean showControls = false;

    public String[] getOptions() {
        return new String[] {
                "New Game",
                "Controls",
                "Options",
                "Quit",
        };
    }


    public MainMenu() {
        super(App.getDisplayWidth(), (int) (App.getDisplayHeight() * 0.6));
    }

    public void tick() {
        if (!showControls) {
            if (Client.input.check(Controls.FORWARD) || Client.input.check(Controls.UP)) {
                Client.input.stopInput(Controls.FORWARD);
                Client.input.stopInput(Controls.UP);
                if ((optionIndex > 0)) {
                    optionIndex--;
                    Sound.clickUp.play();
                }
            }
            if (Client.input.check(Controls.BACK)|| Client.input.check(Controls.DOWN)) {
                Client.input.stopInput(Controls.BACK);
                Client.input.stopInput(Controls.DOWN);
                if ((optionIndex < getOptions().length - 1)) {
                    optionIndex++;
                    Sound.clickDown.play();
                }
            }
        }

        if (Client.input.check(Controls.ACTION) || Client.input.check(Controls.ENTER)) {
            Client.input.stopInput(Controls.ACTION);
            Client.input.stopInput(Controls.ENTER);
            Sound.clickAction.play();
            if (!showControls) {
                if (getOption(optionIndex).equals("New Game")) {
                    Client.newGame("prison");
                } else if (getOption(optionIndex).equals("Controls")) {
                    showControls = !showControls;
                } else if (getOption(optionIndex).equals("Options")) {
                    Client.setActiveOverlay(Client.optionsMenu);
                } else if (getOption(optionIndex).equals("Quit")) {
                    System.exit(0);
                }
            } else {
                showControls = false;
            }
        }
    }

    public void update() {
        fill(0x202020);

        draw("  Dungeon Raycaster", bp, bp, 0xF0F0F0);
        for (int i = 0; i < getOptions().length; i++) {
            if (optionIndex == i) {
                draw("-> " + getOption(i), bp, bp + lineHeight() + (i * lineHeight()), 0xD0D0D0);
            } else {
                draw(" " + getOption(i), bp, bp + lineHeight() + (i * lineHeight()), 0x707070);
            }
        }

        if (showControls) {
            List<String> lines = Arrays.asList(
                    "[Arrows,Scroll]: Navigate",
                    "[Space,Click]:     Action",
                    "[W,S,A,D]:  Move / Strafe",
                    "[Arrows]:   Move / Rotate",
                    "[E]:            Inventory",
                    "[Esc]:              Pause");

            Bitmap textBox = textBoxTrimmed(lines, width, 0xF0F070, 0x303030);
            Bitmap tWindow = new Bitmap(textBox.width + 4, textBox.height + 4);
            tWindow.fill(0x101010);
            draw(drawCenter(tWindow, textBox), halfWidth() - tWindow.halfWidth(), halfHeight() - tWindow.halfHeight());
        }
    }
}
