package com.jamie.raycasting.graphics.overlays.menus;

import com.jamie.raycasting.app.Game;
import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.graphics.overlays.Overlay;
import com.jamie.raycasting.items.Inventory;
import com.jamie.raycasting.items.Item;
import com.jamie.raycasting.items.consumables.Consumable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InventoryOverlay extends Overlay
{
    private Game game;
    private Inventory inventory;
    private int itemIndex = 0;
    private int totalItemIndex = 0;
    private String[] itemCategories = {
        "Items", "Weapons", "Consumables"
    };
    private int itemCatIndex = 0;

    private List<Item> listedItems = new ArrayList<Item>();
    private List<Map<String, String>> listedItemsInfo = new ArrayList<Map<String, String>>();

    public InventoryOverlay(int width, int height, Game game) {
        super(width, height);
        this.game = game;
        this.inventory = game.player.inventory;

        if (game.player.getRightHandItem() != null) {
            itemIndex = game.player.getRightHandItemIndex();
        } else {
            itemIndex = 0;
        }
    }

    public void tick(Game game) {
        if (itemCategories[itemCatIndex] == "Items") {
            listedItems = inventory.getItems();
            listedItemsInfo = inventory.getItemsInfo();
        } else if (itemCategories[itemCatIndex] == "Weapons") {
            listedItems = inventory.getItemsByType("weapon");
            listedItemsInfo = inventory.getWeaponsInfo();
        } else if (itemCategories[itemCatIndex] == "Consumables") {
            listedItems = inventory.getItemsByType("consumable");
            listedItemsInfo = inventory.getConsumablesInfo();
        }

        if (game.userInput.left || game.userInput.rotLeft) {
            game.userInput.setKeyGroupState("left", false);
            game.userInput.setKeyGroupState("rotLeft", false);
            if ((itemCatIndex > 0)) {
                itemIndex = 0;
                itemCatIndex--;
            }
        }
        if (game.userInput.right || game.userInput.rotRight) {
            game.userInput.setKeyGroupState("right", false);
            game.userInput.setKeyGroupState("rotRight", false);
            if ((itemCatIndex < itemCategories.length - 1)) {
                itemIndex = 0;
                itemCatIndex++;
            }
        }

        if (game.userInput.forward) {
            game.userInput.setKeyGroupState("forward", false);
            if ((itemIndex > 0)) {
                itemIndex--;
            }
        }
        if (game.userInput.back) {
            game.userInput.setKeyGroupState("back", false);

            if ((itemIndex < listedItems.size() - 1)) {
                itemIndex++;
            }
        }

        if (listedItems.size() > 0) {
            if (game.userInput.action) {
                game.userInput.setKeyGroupState("action", false);
                if (!(inventory.getItems().get(totalItemIndex) instanceof Consumable)) {
                    if (game.player.getRightHandItem() != inventory.getItem(totalItemIndex)) {

                        int inx = inventory.getIndexOf(inventory.getItem(totalItemIndex));
                        game.player.setRightHandItemIndex(inx);
                    } else {
                        game.player.unequipRightHand();
                    }
                } else {
                    inventory.getItem(totalItemIndex).use();
                }
            }
        }

        if (itemIndex >= listedItems.size() && itemIndex != 0) {
            itemIndex = listedItems.size() - 1;
        }

        if (listedItems.size() != 0) {
            totalItemIndex = inventory.getIndexOf(listedItems.get(itemIndex));
        } else {
            totalItemIndex = -1;
        }
    }

    public void update() {
        fill(0, 0, width, height, 0x202020);

        // cat headings
        String catString = itemCategories[itemCatIndex];
        if (itemCatIndex == 0) {
            draw("< ", borderPadding, borderPadding, 0x404040);
        } else {
            draw("< ", borderPadding, borderPadding, 0xF0F0F0);
        }
        draw(itemCategories[itemCatIndex], borderPadding + 12, borderPadding, 0xF0F0F0);
        if (itemCatIndex == itemCategories.length - 1) {
            draw(" >", borderPadding + 12 + (catString.length() * 6), borderPadding, 0x404040);
        } else {
            draw(" >", borderPadding + 12 + (catString.length() * 6), borderPadding, 0xF0F0F0);
        }
        for (int i = 0; i < itemCategories.length; i++) {
            Render blip = new Render(2, 2);
            if (i == itemCatIndex) {
                blip.fill(0, 0, blip.width, blip.height, 0xF0F0F0);
            } else {
                blip.fill(0, 0, blip.width, blip.height, 0x404040);
            }
            draw(blip, (width - borderPadding - 4) - ((itemCategories.length - i - 1) * 6), borderPadding + 3);
        }


        // column icons
        List<Render> columns = new ArrayList<Render>();
        List<String> columnInfo = new ArrayList<String>();
        if (itemCategories[itemCatIndex] == "Weapons") {
//            columns.add(Texture.healthIcon);
            columns.add(Texture.damageIcon);
            columnInfo.add("damage");
            columns.add(Texture.rangeIcon);
            columnInfo.add("reach");
        } else if (itemCategories[itemCatIndex] == "Consumables") {
            columns.add(Texture.magnitudeIcon);
            columnInfo.add("magnitude");
            columns.add(Texture.durationIcon);
            columnInfo.add("duration");
        }

        draw(Texture.nameIcon, borderPadding + 6, borderPadding + 10);
        for (int i = 0; i < columns.size(); i++) {
            draw(columns.get(i), (width - borderPadding) - (i * 18) - 12, borderPadding + 10);
        }


        // listed items
        int itemListStartY = borderPadding + 10 + 8;
        fill(borderPadding, itemListStartY, width - borderPadding, height - borderPadding, 0x101010);
        if (listedItems.size() > 0) {
            fill(borderPadding, itemListStartY + (itemIndex * 12), width - borderPadding, itemListStartY + ((itemIndex + 1) * 12), 0x404040);
            for (int i = 0; i < listedItems.size(); i++) {
                int colour;
                String itemName;
                if (game.player.getRightHandItemIndex() == inventory.getIndexOf(listedItems.get(i)) && !game.player.rightHandEmpty) {
                    itemName = "-> " + listedItemsInfo.get(i).get("name");
                    colour = 0xF0F070;
                } else {
                    itemName = " " + listedItemsInfo.get(i).get("name");
                    colour = 0x707070;
                }

                if (itemIndex == i) {
                    colour = 0xF0F0F0;
                }
                draw(itemName, borderPadding, itemListStartY + (i * 12) + 2, colour);

                List<String> itemInfoData = new ArrayList<String>();
                for (int b = 0; b < columnInfo.size(); b++) {
                    itemInfoData.add(listedItemsInfo.get(i).get(columnInfo.get(b)) + " ");
                }
                for (int j = 0; j < itemInfoData.size(); j++) {
                    draw(itemInfoData.get(j), (width - borderPadding) - (j * 18) - (itemInfoData.get(j).length() * 6), itemListStartY + (i * 12) + 2, colour); //////
                }
            }
        }
    }
}
