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

    private List<Item> listedItems = new ArrayList<Item>();
    private List<Map<String, String>> listedItemsInfo = new ArrayList<Map<String, String>>();
    private int listItemIndex;
    private int inventoryItemIndex = 0;

    private String[] itemCategories = {
        "Items", "Weapons", "Consumables"
    };
    private int itemCatIndex = 0;

    private Render itemListRender = new Render(width - borderPadding - borderPadding, height - (borderPadding + 10 + 8 + borderPadding));
    private int itemListYShift = 0;

    public InventoryOverlay(int width, int height, Game game) {
        super(width, height);
        this.game = game;
        this.inventory = game.player.inventory;

        if (game.player.getRightHandItem() != null) {
            listItemIndex = game.player.getRightHandItemIndex();
        } else {
            listItemIndex = 0;
        }
    }

    public void tick(Game game) {
        if (itemCategories[itemCatIndex].equals("Items")) {
            listedItems = inventory.getItems();
            listedItemsInfo = inventory.getItemsInfo();
        } else if (itemCategories[itemCatIndex].equals("Weapons")) {
            listedItems = inventory.getItemsByType("weapon");
            listedItemsInfo = inventory.getWeaponsInfo();
        } else if (itemCategories[itemCatIndex].equals("Consumables")) {
            listedItems = inventory.getItemsByType("consumable");
            listedItemsInfo = inventory.getConsumablesInfo();
        }

        if (game.userInput.left || game.userInput.rotLeft) {
            game.userInput.setKeyGroupState("left", false);
            game.userInput.setKeyGroupState("rotLeft", false);
            if ((itemCatIndex > 0)) {
                listItemIndex = 0;
                itemListYShift = 0;
                itemCatIndex--;
            }
        }
        if (game.userInput.right || game.userInput.rotRight) {
            game.userInput.setKeyGroupState("right", false);
            game.userInput.setKeyGroupState("rotRight", false);
            if ((itemCatIndex < itemCategories.length - 1)) {
                listItemIndex = 0;
                itemListYShift = 0;
                itemCatIndex++;
            }
        }

        if (game.userInput.forward) {
            game.userInput.setKeyGroupState("forward", false);
            if ((listItemIndex > 0)) {
                listItemIndex--;
            }
        }
        if (game.userInput.back) {
            game.userInput.setKeyGroupState("back", false);

            if ((listItemIndex < listedItems.size() - 1)) {
                listItemIndex++;
            }
        }

        if (listedItems.size() > 0) {
            if (game.userInput.action) {
                game.userInput.setKeyGroupState("action", false);
                if (!(inventory.getItems().get(inventoryItemIndex) instanceof Consumable)) {
                    if (game.player.getRightHandItem() != inventory.getItem(inventoryItemIndex)) {

                        int inx = inventory.getIndexOf(inventory.getItem(inventoryItemIndex));
                        game.player.setRightHandItemIndex(inx);
                    } else {
                        game.player.unequipRightHand();
                    }
                } else {
                    inventory.getItem(inventoryItemIndex).use();
                }
            }
        }

        if (listItemIndex >= listedItems.size() && listItemIndex != 0) {
            listItemIndex = listedItems.size() - 1;
        }

        if (listedItems.size() != 0) {
            inventoryItemIndex = inventory.getIndexOf(listedItems.get(listItemIndex));
        } else {
            inventoryItemIndex = -1;
        }

//        if (listItemIndex * 12 >= itemListRender.height) {
//            itemListYShift = -(listItemIndex * 12 % itemListRender.height + 12);
//        }
    }

    public void update() {
        List<Render> statIcons = new ArrayList<Render>();
        List<String> statNames = new ArrayList<String>();
        if (itemCategories[itemCatIndex].equals("Weapons")) {
            statIcons.add(Texture.damageIcon);
            statNames.add("damage");
            statIcons.add(Texture.rangeIcon);
            statNames.add("reach");
        } else if (itemCategories[itemCatIndex].equals("Consumables")) {
            statIcons.add(Texture.magnitudeIcon);
            statNames.add("magnitude");
            statIcons.add(Texture.durationIcon);
            statNames.add("duration");
        }

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
        draw(Texture.nameIcon, borderPadding + 6, borderPadding + 10);
        for (int i = 0; i < statIcons.size(); i++) {
            draw(statIcons.get(i), (width - borderPadding) - (i * 18) - 12, borderPadding + 10);
        }


        // listed items
        itemListRender.fill(0, 0, itemListRender.width, itemListRender.height, 0x101010);
        if (listedItems.size() > 0) {
            itemListRender.fill(0, itemListYShift + (listItemIndex * 12), itemListRender.width, itemListYShift + ((listItemIndex + 1) * 12), 0x404040);
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
                if (listItemIndex == i) {
                    colour = 0xF0F0F0;
                }
                itemListRender.draw(itemName, borderPadding, itemListYShift + (i * 12) + 2, colour);

                List<String> itemStats = new ArrayList<String>();
                for (int b = 0; b < statNames.size(); b++) {
                    itemStats.add(listedItemsInfo.get(i).get(statNames.get(b)) + " ");
                }
                for (int j = 0; j < itemStats.size(); j++) {
                    itemListRender.draw(itemStats.get(j), (itemListRender.width - borderPadding) - (j * 18) - (itemStats.get(j).length() * 6), itemListYShift + (i * 12) + 2, colour);
                }
            }
        }
        draw(itemListRender, borderPadding, borderPadding + 10 + 8);
    }
}
