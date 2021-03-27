package com.jamie.raycasting.graphics.overlays.menus;

import com.jamie.jamapp.App;
import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.app.Client;
import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.input.Controls;

import java.util.Arrays;
import java.util.List;

public class PauseMenu extends Menu
{
    private boolean showControls = false;

    public String[] getOptions() {
        return new String[] {
                "Resume",
                "Controls",
                "Options",
                "Main Menu"
        };
    }


    public PauseMenu() {
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
            switch (getOption(optionIndex)) {
                case "Resume":
                    Client.setActiveOverlay(null);
                    break;
                case "Controls":
                    showControls = !showControls;
                    break;
                case "Options":
                    Client.setActiveOverlay(Client.optionsMenu);
                    break;
                case "Main Menu":
                    Client.stopGame();
                    break;
            }
        }
    }

    public void update() {
        fill(0x202020);
        draw("  Paused", bp, bp, 0xF0F0F0);
        for (int i = 0; i < getOptions().length; i++) {
            if (optionIndex == i) {
                draw("-> " + getOption(i), bp, bp + lineHeight() + (i * lineHeight()), 0xD0D0D0);
            } else {
                draw(" " + getOption(i), bp, bp + lineHeight() + (i * lineHeight()), 0x707070);
            }
        }

        if (showControls) {
            List<String> lines = Arrays.asList(
                    "Action:         Space   Click",
                    "Look / Rotate:  Arrows  Mouse",
                    "Move / Strafe:  W,S,A,D      ",
                    "Inventory:      E            ",
                    "Pause:          Esc          "
            );
            Bitmap textBox = textBoxSpacedTrimmed(lines, width, 0xF0F070, 0x303030);
            Bitmap tWindow = new Bitmap(textBox.width + 4, textBox.height + 4);
            tWindow.fill(0x101010);
            draw(drawCenter(tWindow, textBox), halfWidth() - tWindow.halfWidth(), halfHeight() - tWindow.halfHeight());
        }
    }

    public void resetSelection() {
        super.resetSelection();
        showControls = false;
    }
}
