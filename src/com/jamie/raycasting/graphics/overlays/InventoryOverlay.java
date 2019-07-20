package com.jamie.raycasting.graphics.overlays;

import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.app.Game;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.jamapp.Render;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.items.Inventory;
import com.jamie.raycasting.items.Item;

import java.util.ArrayList;
import java.util.List;

public class InventoryOverlay extends Overlay
{
    private Mob mob;
    private Inventory inventory;
    private int inventoryItemIndex = 0;

    private Render itemDetailsPane = new Render(48, height - (bp + 10 + 8 + bp));
    private Render itemListRender = new Render(width - bp - bp - itemDetailsPane.width, height - (bp + 10 + 8 + bp));

    private int categoryIndex = 0;
    private ItemList[] categoryItemLists = {
            new ItemList("Items", null),
            new ItemList("Weapons", "weapon"),
            new ItemList("Consumables", "consumable"),
            new ItemList("Misc", "misc")
    };


    private class ItemList
    {
        public String category;
        public String itemType;
        public List<Item> listedItems = new ArrayList<Item>();
        public int listIndex = 0;
        public int listYShift = 0;

        public ItemList(String category, String itemType) {
            this.category = category;
            this.itemType = itemType;
        }
    }


    public InventoryOverlay(int width, int height) {
        super(width, height);
    }

    public void tick(Game game) {
        this.mob = game.getPlayer();
        this.inventory = game.getPlayer().inventory;

        for (int i = 0; i < categoryItemLists.length; i++) {
            if (categoryItemLists[i].itemType != null) {
                categoryItemLists[i].listedItems = inventory.getItemsByType(categoryItemLists[i].itemType);
            } else {
                categoryItemLists[i].listedItems = inventory.getItems();
            }

            int down = getItemList().listIndex * 12;
            if (down < -getItemList().listYShift) {
                getItemList().listYShift = -(down % -getItemList().listYShift);
            }

            if (down + 12 >= itemListRender.height - getItemList().listYShift) {
                getItemList().listYShift = -(down - itemListRender.height + 12);
            }
        }

        if (game.userInput.left || game.userInput.rotLeft) {
            game.userInput.setInputState("left", false);
            game.userInput.setInputState("rotLeft", false);
            if ((categoryIndex > 0)) {
                categoryIndex--;
            }
        }
        if (game.userInput.right || game.userInput.rotRight) {
            game.userInput.setInputState("right", false);
            game.userInput.setInputState("rotRight", false);
            if ((categoryIndex < categoryItemLists.length - 1)) {
                categoryIndex++;
            }
        }

        if (game.userInput.forward) {
            game.userInput.setInputState("forward", false);
            if ((getItemList().listIndex > 0)) {
                Sound.clickUp.play();
                getItemList().listIndex--;
            }
        }
        if (game.userInput.back) {
            game.userInput.setInputState("back", false);
            if ((getItemList().listIndex < getItemList().listedItems.size() - 1)) {
                Sound.clickDown.play();
                getItemList().listIndex++;
            }
        }

        if (getItemList().listedItems.size() > 0) {
            if (game.userInput.action) {
                game.userInput.setInputState("action", false);
                Sound.clickAction.play();
                mob.useItemIndex(inventory.getIndexOf(inventory.getItem(inventoryItemIndex)));
            }

            if (game.userInput.hot1) {
                game.userInput.setInputState("hot1", false);
                Sound.clickAction.play();
                if (mob.getHotkey(1) == null || mob.getHotkey(1) != inventoryItemIndex) {
                    mob.setHotkey(1, inventoryItemIndex);
                } else {
                    mob.setHotkey(1, null);
                }
            }
            if (game.userInput.hot2) {
                game.userInput.setInputState("hot2", false);
                Sound.clickAction.play();
                if (mob.getHotkey(2) == null || mob.getHotkey(2) != inventoryItemIndex) {
                    mob.setHotkey(2, inventoryItemIndex);
                } else {
                    mob.setHotkey(2, null);
                }
            }
            if (game.userInput.hot3) {
                game.userInput.setInputState("hot3", false);
                Sound.clickAction.play();
                if (mob.getHotkey(3) == null || mob.getHotkey(3) != inventoryItemIndex) {
                    mob.setHotkey(3, inventoryItemIndex);
                } else {
                    mob.setHotkey(3, null);
                }
            }
        }

        if (getItemList().listIndex >= getItemList().listedItems.size() && getItemList().listIndex != 0) {
            getItemList().listIndex = getItemList().listedItems.size() - 1;
        }

        if (getItemList().listedItems.size() != 0) {
            inventoryItemIndex = inventory.getIndexOf(getItemList().listedItems.get(getItemList().listIndex));
        } else {
            inventoryItemIndex = -1;
        }
    }

    private void updateCatHeadings() {
        String catString = getItemList().category;
        if (categoryIndex == 0) {
            draw("< ", bp, bp, 0x404040);
        } else {
            draw("< ", bp, bp, 0xF0F0F0);
        }
        draw(getItemList().category, bp + 12, bp, 0xF0F0F0);
        if (categoryIndex == categoryItemLists.length - 1) {
            draw(" >", bp + 12 + (catString.length() * 6), bp, 0x404040);
        } else {
            draw(" >", bp + 12 + (catString.length() * 6), bp, 0xF0F0F0);
        }
        for (int i = 0; i < categoryItemLists.length; i++) {
            Render blip = new Render(2, 2);
            if (i == categoryIndex) {
                blip.fill(0xF0F0F0);
            } else {
                blip.fill(0x404040);
            }
            draw(blip, (width - bp - 4) - ((categoryItemLists.length - i - 1) * 6), bp + 3);
        }
        draw(Texture.nameIcon, bp * 2, bp + 10);
    }

    private void updateList() {
        itemListRender.fill(0x101010);
        if (getItemList().listedItems.size() > 0) {
            itemListRender.fill(0, getItemList().listYShift + (getItemList().listIndex * 12), itemListRender.width, getItemList().listYShift + ((getItemList().listIndex + 1) * 12), 0x404040);
            for (int i = 0; i < getItemList().listedItems.size(); i++) {
                int colour;
                String itemName;
                if (mob.getRightHandItemIndex() == inventory.getIndexOf(getItemList().listedItems.get(i)) && !mob.rightHandEmpty) {
                    itemName = "-> " + getItemList().listedItems.get(i).name;
                    colour = 0xF0F070;
                } else {
                    itemName = " " + getItemList().listedItems.get(i).name;
                    colour = 0x707070;
                }
                if (getItemList().listIndex == i) {
                    colour = 0xF0F0F0;
                }
                itemListRender.draw(itemName, bp, getItemList().listYShift + (i * 12) + 2, colour);
            }
        }
        draw(itemListRender, bp, bp + 10 + 8);
    }

    private void updateDetailsPane() {
        itemDetailsPane.fill(0x303030);
        if (getItemList().listedItems.size() > 0 && getItemList().listedItems.size() != getItemList().listIndex) {
            Item item = getItemList().listedItems.get(getItemList().listIndex);
            Render icon = item.icon;
            Render bGround = new Render(18, 18);
            bGround.fill(0x202020);

            itemDetailsPane.draw(bGround, itemDetailsPane.width / 2 - bGround.width / 2, bp);
            itemDetailsPane.draw(icon, itemDetailsPane.width / 2 - icon.width / 2, bp + 1);

            int rowX = bp + bGround.height + bp + 12;
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
        updateDetailsPane();
    }

    private ItemList getItemList() {
        return categoryItemLists[categoryIndex];
    }
}
