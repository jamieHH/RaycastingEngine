package com.jamie.raycasting.graphics.overlays.menus;

import com.jamie.raycasting.app.Game;
import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.overlays.Overlay;
import com.jamie.raycasting.items.consumables.Consumable;

public class InventoryOverlay extends Overlay
{
    private Game game;
    private int itemIndex = 0;
    private String[] itemCategories = {
        "Items", "Weapons", "Consumables"
    };
    private int itemCategoryIndex = 0;

    public InventoryOverlay(int width, int height, Game game) {
        super(width, height);
        this.game = game;
        if (game.player.getRightHandItem() != null) {
            itemIndex = game.player.getRightHandItemIndex();
        } else {
            itemIndex = 0;
        }
    }

    public void tick(Game game) {
        if (game.userInput.forward) {
            game.userInput.setKeyGroupState("forward", false);
            if ((itemIndex > 0)) {
                itemIndex--;
            }
        }
        if (game.userInput.back) {
            game.userInput.setKeyGroupState("back", false);

            if ((itemIndex < game.player.inventory.getItems().size() - 1)) {
                itemIndex++;
            }
        }

        if (game.userInput.left || game.userInput.rotLeft) {
            game.userInput.setKeyGroupState("left", false);
            game.userInput.setKeyGroupState("rotLeft", false);

            if ((itemCategoryIndex > 0)) {
                itemCategoryIndex--;
            }
        }
        if (game.userInput.right || game.userInput.rotRight) {
            game.userInput.setKeyGroupState("right", false);
            game.userInput.setKeyGroupState("rotRight", false);

            if ((itemCategoryIndex < itemCategories.length - 1)) {
                itemCategoryIndex++;
            }
        }

        if (game.player.inventory.getItems().size() > 0) {
            if (game.userInput.action) {
                game.userInput.setKeyGroupState("action", false);
                if (!(game.player.inventory.getItems().get(itemIndex) instanceof Consumable)) {
                    if (game.player.getRightHandItem() != game.player.inventory.getItem(itemIndex)) {
                        game.player.setRightHandItemIndex(itemIndex);
                    } else {
                        game.player.unequipRightHand();
                    }
                } else {
                    game.player.inventory.getItems().get(itemIndex).use();
                }
            }
        }

        if (itemIndex >= game.player.inventory.getItems().size()) {
            itemIndex--; // fix from changing to -1 from other empty mobs
        }
    }

    public void update() {
        fill(0, 0, width, height, 0x202020);

        String catString = itemCategories[itemCategoryIndex];
        if (itemCategoryIndex == 0) {
            draw("< ", borderPadding, borderPadding, 0x404040);
        } else {
            draw("< ", borderPadding, borderPadding, 0xF0F0F0);
        }
        draw(itemCategories[itemCategoryIndex], borderPadding + 12, borderPadding, 0xF0F0F0);
        if (itemCategoryIndex == itemCategories.length - 1) {
            draw(" >", borderPadding + 12 + (catString.length() * 6), borderPadding, 0x404040);
        } else {
            draw(" >", borderPadding + 12 + (catString.length() * 6), borderPadding, 0xF0F0F0);
        }

        for (int i = 0; i < itemCategories.length; i++) {
            Render blip = new Render(2, 2);
            if (i == itemCategoryIndex) {
                blip.fill(0, 0, blip.width, blip.height, 0xF0F0F0);
            } else {
                blip.fill(0, 0, blip.width, blip.height, 0x404040);
            }
            draw(blip, (width - borderPadding - 4) - ((itemCategories.length - i - 1) * 6), borderPadding + 3);
        }



        fill(borderPadding, borderPadding + 10, width - borderPadding, height - borderPadding, 0x101010);

        if (game.player.inventory.getItems().size() > 0) {
            fill(borderPadding, borderPadding + 10 + (itemIndex * 12), width - borderPadding, borderPadding + 10 + ((itemIndex + 1) * 12), 0x404040);
            for (int i = 0; i < game.player.inventory.getItems().size(); i++) {
                String string;
                String string2 = game.player.inventory.getItemsInfo().get(i).get("damage") + " ";
                int colour;

                if (game.player.getRightHandItemIndex() == i && !game.player.rightHandEmpty) {
                    string = "-> " + game.player.inventory.getItemsInfo().get(i).get("name");
                    colour = 0xF0F070;
                } else {
                    string = " " + game.player.inventory.getItemsInfo().get(i).get("name");
                    colour = 0x707070;
                }

                if (itemIndex == i) {
                    colour = 0xF0F0F0;
                }
                draw(string, borderPadding, borderPadding + 10 + (i * 12) + 2, colour);
                draw(string2, width - borderPadding - (6 * string2.length()), borderPadding + 10 + (i * 12) + 2, colour);
            }
        }
    }
}
