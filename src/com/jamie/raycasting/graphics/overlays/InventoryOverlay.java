package com.jamie.raycasting.graphics.overlays;

import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.app.Client;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.input.Controls;
import com.jamie.raycasting.items.Inventory;
import com.jamie.raycasting.items.Item;

import java.util.ArrayList;
import java.util.List;

public class InventoryOverlay extends Overlay
{
    private Mob mob;
    private Inventory inventory;
    private int inventoryItemIndex = 0;

    private Bitmap itemDetailsPane;
    private Bitmap itemListBitmap;

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

    private int getListItemHeight() {
        return (getFontHeight() + 4);
    }


    public InventoryOverlay(int width, int height) {
        super(width, height);
        setSize(width, height);
    }

    public void setSize(int width, int height) {
        super.setSize(width, height);
        itemDetailsPane = new Bitmap(48, height - (bp + getFontHeight() + 2 + bp));
        itemListBitmap = new Bitmap(width - bp - bp - itemDetailsPane.width, height - (bp + getFontHeight() + 2 + bp));
    }

    public void tick() {
        this.mob = Client.getPlayer();
        this.inventory = Client.getPlayer().inventory;

        for (int i = 0; i < categoryItemLists.length; i++) {
            if (categoryItemLists[i].itemType != null) {
                categoryItemLists[i].listedItems = inventory.getItemsByType(categoryItemLists[i].itemType);
            } else {
                categoryItemLists[i].listedItems = inventory.getItems();
            }

            int down = getItemList().listIndex * getListItemHeight();
            if (down < -getItemList().listYShift) {
                getItemList().listYShift = -(down % -getItemList().listYShift);
            }

            if (down + (getFontHeight()) >= itemListBitmap.height - getItemList().listYShift) {
                getItemList().listYShift = -(down - itemListBitmap.height + getListItemHeight());
            }
        }

        if (Client.input.check(Controls.LEFT) || Client.input.check(Controls.ROTLEFT)) {
            Client.input.stopInput(Controls.LEFT);
            Client.input.stopInput(Controls.ROTLEFT);
            if ((categoryIndex > 0)) {
                categoryIndex--;
            }
        }
        if (Client.input.check(Controls.RIGHT) || Client.input.check(Controls.ROTRIGHT)) {
            Client.input.stopInput(Controls.RIGHT);
            Client.input.stopInput(Controls.ROTRIGHT);
            if ((categoryIndex < categoryItemLists.length - 1)) {
                categoryIndex++;
            }
        }

        if (Client.input.check(Controls.FORWARD) || Client.input.check(Controls.UP)) {
            Client.input.stopInput(Controls.FORWARD);
            Client.input.stopInput(Controls.UP);
            if ((getItemList().listIndex > 0)) {
                Sound.clickUp.play();
                getItemList().listIndex--;
            }
        }
        if (Client.input.check(Controls.BACK) || Client.input.check(Controls.DOWN)) {
            Client.input.stopInput(Controls.BACK);
            Client.input.stopInput(Controls.DOWN);
            if ((getItemList().listIndex < getItemList().listedItems.size() - 1)) {
                Sound.clickDown.play();
                getItemList().listIndex++;
            }
        }

        if (getItemList().listedItems.size() > 0) {
            if (Client.input.check(Controls.ACTION) || Client.input.check(Controls.ENTER)) {
                Client.input.stopInput(Controls.ACTION);
                Client.input.stopInput(Controls.ENTER);
                Sound.clickAction.play();
                mob.useItemIndex(inventory.getIndexOf(inventory.getItem(inventoryItemIndex)));
            }

            if (Client.input.check(Controls.HOT1)) {
                Client.input.stopInput(Controls.HOT1);
                Sound.clickAction.play();
                if (mob.getHotkey(1) == null || mob.getHotkey(1) != inventoryItemIndex) {
                    mob.setHotkey(1, inventoryItemIndex);
                } else {
                    mob.setHotkey(1, null);
                }
            }
            if (Client.input.check(Controls.HOT2)) {
                Client.input.stopInput(Controls.HOT2);
                Sound.clickAction.play();
                if (mob.getHotkey(2) == null || mob.getHotkey(2) != inventoryItemIndex) {
                    mob.setHotkey(2, inventoryItemIndex);
                } else {
                    mob.setHotkey(2, null);
                }
            }
            if (Client.input.check(Controls.HOT3)) {
                Client.input.stopInput(Controls.HOT3);
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
        draw(getItemList().category, bp + (getFontWidth() * 2), bp, 0xF0F0F0);
        if (categoryIndex == categoryItemLists.length - 1) {
            draw(" >", bp + (getFontWidth() * 2) + (catString.length() * getFontWidth()), bp, 0x404040);
        } else {
            draw(" >", bp + (getFontWidth() * 2) + (catString.length() * getFontWidth()), bp, 0xF0F0F0);
        }
    }

    private void updateList() {
        itemListBitmap.fill(0x101010);
        if (getItemList().listedItems.size() > 0) {
            itemListBitmap.fill(0, getItemList().listYShift + (getItemList().listIndex * getListItemHeight()), itemListBitmap.width, getItemList().listYShift + ((getItemList().listIndex + 1) * getListItemHeight()), 0x404040);
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
                itemListBitmap.draw(itemName, bp, getItemList().listYShift + (i * getListItemHeight()) + 2, colour);
            }
        }
        draw(itemListBitmap, bp, bp + getFontHeight() + bp);
    }

    private void updateDetailsPane() {
        itemDetailsPane.fill(0x303030);
        if (getItemList().listedItems.size() > 0 && getItemList().listedItems.size() != getItemList().listIndex) {
            Item item = getItemList().listedItems.get(getItemList().listIndex);
            Bitmap icon = item.icon;
            Bitmap bGround = new Bitmap(18, 18);
            bGround.fill(0x202020);

            itemDetailsPane.draw(bGround, itemDetailsPane.width / 2 - bGround.width / 2, bp);
            itemDetailsPane.draw(icon, itemDetailsPane.width / 2 - icon.width / 2, bp + 1);

            int rowX = bp + bGround.height + bp;
            if (item.type.equals("weapon")) {
                itemDetailsPane.draw(Texture.damageIcon, bp, rowX + 1);
                itemDetailsPane.draw(item.getInfo().get("damage"), bp + (getFontWidth() * 2), rowX, 0xF0F0F0);
                rowX += 10;
                itemDetailsPane.draw(Texture.rangeIcon, bp, rowX + 1);
                itemDetailsPane.draw(item.getInfo().get("reach"), bp + (getFontWidth() * 2), rowX, 0xF0F0F0);
            } else if (item.type.equals("consumable")) {
                itemDetailsPane.draw(Texture.magnitudeIcon, bp, rowX + 1);
                itemDetailsPane.draw(item.getInfo().get("magnitude"), bp + (getFontWidth() * 2), rowX, 0xF0F0F0);
                rowX += 10;
                itemDetailsPane.draw(Texture.durationIcon, bp, rowX + 1);
                itemDetailsPane.draw(item.getInfo().get("duration"), bp + (getFontWidth() * 2), rowX, 0xF0F0F0);
            }
        }
        draw(itemDetailsPane, bp + itemListBitmap.width,bp + (getFontHeight() + 2));
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
