package com.jamie.raycasting.graphics.overlays;

import com.jamie.raycasting.app.Game;
import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.graphics.Render;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.items.Inventory;
import com.jamie.raycasting.items.Item;
import com.jamie.raycasting.items.MiscItem;
import com.jamie.raycasting.items.consumables.Consumable;

import java.util.ArrayList;
import java.util.List;

public class InventoryOverlay extends Overlay
{
    private Mob mob;
    private Inventory inventory;

    private List<Item> listedItems = new ArrayList<Item>();
    private int listItemIndex = 0;
    private int inventoryItemIndex = 0;

    private String[] itemCategories = {
        "Items", "Weapons", "Consumables", "Misc"
    };
    private int itemCatIndex = 0;

    private Render itemDetailsPane = new Render(48, height - (bp + 10 + 8 + bp));
    private Render itemListRender = new Render(width - bp - bp - itemDetailsPane.width, height - (bp + 10 + 8 + bp));
    private int itemListYShift = 0;

    public InventoryOverlay(int width, int height) {
        super(width, height);
    }

    public void tick(Game game) {
        this.mob = game.player;
        this.inventory = game.player.inventory;

        if (itemCategories[itemCatIndex].equals("Items")) {
            listedItems = inventory.getItems();
        } else if (itemCategories[itemCatIndex].equals("Weapons")) {
            listedItems = inventory.getItemsByType("weapon");
        } else if (itemCategories[itemCatIndex].equals("Consumables")) {
            listedItems = inventory.getItemsByType("consumable");
        } else if (itemCategories[itemCatIndex].equals("Misc")) {
            listedItems = inventory.getItemsByType("misc");
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
                Sound.clickUp.play();
                listItemIndex--;
            }
            int down = listItemIndex * 12;
            if (down < -itemListYShift) {
                itemListYShift = -(down % -itemListYShift);
            }
        }
        if (game.userInput.back) {
            game.userInput.setKeyGroupState("back", false);
            if ((listItemIndex < listedItems.size() - 1)) {
                Sound.clickDown.play();
                listItemIndex++;
            }
            int down = listItemIndex * 12;
            if (down + 12 >= itemListRender.height - itemListYShift) {
                itemListYShift = -(down - itemListRender.height + 12);
            }
        }

        if (listedItems.size() > 0) {
            if (game.userInput.action) {
                game.userInput.setKeyGroupState("action", false);
                Sound.clickAction.play();
                if (inventory.getItems().get(inventoryItemIndex) instanceof Consumable) {
                    inventory.getItem(inventoryItemIndex).use();
                } else if (inventory.getItems().get(inventoryItemIndex) instanceof MiscItem) {
                    // do nothing
                } else {
                    if (mob.getRightHandItem() != inventory.getItem(inventoryItemIndex)) {
                        int inx = inventory.getIndexOf(inventory.getItem(inventoryItemIndex));
                        mob.setRightHandItemIndex(inx);
                    } else {
                        mob.unequipRightHand();
                    }
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
    }

    public void updateCatHeadings() {
        String catString = itemCategories[itemCatIndex];
        if (itemCatIndex == 0) {
            draw("< ", bp, bp, 0x404040);
        } else {
            draw("< ", bp, bp, 0xF0F0F0);
        }
        draw(itemCategories[itemCatIndex], bp + 12, bp, 0xF0F0F0);
        if (itemCatIndex == itemCategories.length - 1) {
            draw(" >", bp + 12 + (catString.length() * 6), bp, 0x404040);
        } else {
            draw(" >", bp + 12 + (catString.length() * 6), bp, 0xF0F0F0);
        }
        for (int i = 0; i < itemCategories.length; i++) {
            Render blip = new Render(2, 2);
            if (i == itemCatIndex) {
                blip.fill(0xF0F0F0);
            } else {
                blip.fill(0x404040);
            }
            draw(blip, (width - bp - 4) - ((itemCategories.length - i - 1) * 6), bp + 3);
        }
        draw(Texture.nameIcon, bp * 2, bp + 10);
    }

    public void updateList() {
        itemListRender.fill(0x101010);
        if (listedItems.size() > 0) {
            itemListRender.fill(0, itemListYShift + (listItemIndex * 12), itemListRender.width, itemListYShift + ((listItemIndex + 1) * 12), 0x404040);
            for (int i = 0; i < listedItems.size(); i++) {
                int colour;
                String itemName;
                if (mob.getRightHandItemIndex() == inventory.getIndexOf(listedItems.get(i)) && !mob.rightHandEmpty) {
                    itemName = "-> " + listedItems.get(i).name;
                    colour = 0xF0F070;
                } else {
                    itemName = " " + listedItems.get(i).name;
                    colour = 0x707070;
                }
                if (listItemIndex == i) {
                    colour = 0xF0F0F0;
                }
                itemListRender.draw(itemName, bp, itemListYShift + (i * 12) + 2, colour);
            }
        }
        draw(itemListRender, bp, bp + 10 + 8);
    }

    public void updateDetailsPain() {
        itemDetailsPane.fill(0x303030);
        if (listedItems.size() > 0) {
            Item item = listedItems.get(listItemIndex);
            Render icon = item.icon;
            Render bground = new Render(18, 18);
            bground.fill(0x202020);

            itemDetailsPane.draw(bground, itemDetailsPane.width / 2 - bground.width / 2, bp);
            itemDetailsPane.draw(icon, itemDetailsPane.width / 2 - icon.width / 2, bp + 1);

            int rowX = bp + bground.height + bp + 12;
            if (item.type.equals("weapon")) {
                itemDetailsPane.draw(Texture.damageIcon, bp, rowX + 1);
                itemDetailsPane.draw(item.getInfo().get("damage"), bp + 12, rowX, 0xF0F0F0);
                rowX += 10;
                itemDetailsPane.draw(Texture.rangeIcon, bp, rowX + 1);
                itemDetailsPane.draw(item.getInfo().get("reach"), bp + 12, rowX, 0xF0F0F0);
            } else if (item.type.equals("consumable")) {
                itemDetailsPane.draw(Texture.magnitudeIcon, bp, rowX + 1);
                itemDetailsPane.draw(item.getInfo().get("magnitude"), bp + 12, rowX, 0xF0F0F0);
                rowX += 10;
                itemDetailsPane.draw(Texture.durationIcon, bp, rowX + 1);
                itemDetailsPane.draw(item.getInfo().get("duration"), bp + 12, rowX, 0xF0F0F0);
            }
        }
        draw(itemDetailsPane, bp + itemListRender.width, bp + 10 + 8);
    }

    public void update() {
        fill(0x202020);
        updateCatHeadings();
        updateList();
        updateDetailsPain();
    }
}
