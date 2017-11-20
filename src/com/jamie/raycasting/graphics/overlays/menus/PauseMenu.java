package com.jamie.raycasting.graphics.overlays.menus;

import com.jamie.raycasting.app.App;
import com.jamie.raycasting.app.Game;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.entities.mobs.Player;
import com.jamie.raycasting.graphics.Screen;

public class PauseMenu extends Menu
{
    public PauseMenu() {
        options.add("Resume");
        options.add("Main Menu");
    }

    public void tick(Mob mob) {
        super.tick(mob);

        if (mob.input.pause) {
            mob.input.setKeyGroupState("pause", false);
            mob.setActiveOverlay(null);
        }

        if (mob.input.forward) {
            mob.input.setKeyGroupState("forward", false);
            if ((optionIndex > 0)) {
                optionIndex--;
            }
        }
        if (mob.input.back) {
            mob.input.setKeyGroupState("back", false);

            if ((optionIndex < options.size() - 1)) {
                optionIndex++;
            }
        }

        if (mob.input.action) {
            mob.input.setKeyGroupState("action", false);
            if (options.get(optionIndex) == "Resume") {
                mob.setActiveOverlay(null);
            } else if (options.get(optionIndex) == "Main Menu") {
                App.setGameActiveOverlay(new MainMenu());
                // TODO: this should trigger something to remove the the client from the game.
            }
        }
    }

    public void render(Screen screen) {
        screen.draw(screen.menuBackground, 0, 0);
        screen.draw("Paused", textMarginX + 6, (int) ((screen.height * 0.2) + 8), 0xF0F0F0);
        for (int i = 0; i < options.size(); i++) {
            if (optionIndex == i) {
                screen.draw("-> " + options.get(i), selectedTextMarginX, 10 + (int) ((screen.height * 0.2) + 8) + (i * 10), 0xD0D0D0);
            } else {
                screen.draw(options.get(i), textMarginX, 10 + (int) ((screen.height * 0.2) + 8) + (i * 10), 0x707070);
            }
        }
    }
}
