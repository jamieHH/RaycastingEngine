package com.jamie.raycasting.graphics.overlays;

import com.jamie.jamapp.App;
import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.app.Client;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.graphics.Texture;
import com.jamie.raycasting.input.Controls;
import com.jamie.raycasting.items.Inventory;
import com.jamie.raycasting.items.Item;

import java.util.*;

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
            new ItemList("Weapons", Item.TYPE_WEAPON),
            new ItemList("Consumables", Item.TYPE_CONSUMABLE),
            new ItemList("Misc", Item.TYPE_MISC)
    };


    private class ItemList
    {
        public String category;
        public String itemType;
        public List<ItemListItem> listedItems = new ArrayList<>();
        public int listIndex = 0;
        public int listYShift = 0;

        public ItemList(String category, String itemType) {
            this.category = category;
            this.itemType = itemType;
        }
    }

    private static class ItemListItem
    {
        public String title;
        public Boolean selected = false;
        public List<Item> items;

        public ItemListItem(Item item) {
            this.title = item.name;
            this.items = new ArrayList<>(Arrays.asList(
                    item
            ));
        }

        public String getDisplayString() {
            if (selected) {
                return "-> " + title + getQuantityString();
            } else {
                return " " + title + getQuantityString();
            }
        }

        public String getQuantityString() {
            if (items.size() > 1) {
                return " (" + items.size() + ")";
            } else {
                return "";
            }
        };

        public Item nextItem() {
            return items.get(0);
        };
    }


    private int getListItemHeight() {
        return (lineHeight() + 2);
    }


    public InventoryOverlay() {
        super((int) (App.getDisplayWidth() * 0.8), (int) (App.getDisplayHeight() * 0.6));
    }

    public void resizeOverlay() {
        super.setSize((int) (App.getDisplayWidth() * 0.8), (int) (App.getDisplayHeight() * 0.6));
    }

    public void setSize(int width, int height) {
        super.setSize(width, height);
        itemDetailsPane = new Bitmap(48, height - (bp + lineHeight() + bp));
        itemListBitmap = new Bitmap(width - bp - bp - itemDetailsPane.width, height - (bp + lineHeight() + bp));
    }

    public void addItem(List<ItemListItem> itemList, Item item) {
        boolean added = false;
        for (ItemListItem ili : itemList) {
            for (Item it : ili.items) {
                if (it.equalTo(item)) {
                    ili.items.add(item);
                    added = true;
                    break;
                }
            }
        }
        if (!added) {
            itemList.add(new ItemListItem(item));
        }
    }

    public void tick() {
        this.mob = Client.getPlayer();
        this.inventory = Client.getPlayer().inventory;

        for (int i = 0; i < categoryItemLists.length; i++) {
            List<Item> items;
            if (categoryItemLists[i].itemType != null) {
                items = inventory.getItemsByType(categoryItemLists[i].itemType);
            } else {
                items = inventory.getItems();
            }


            List<ItemListItem> listedItems = new ArrayList<>();
            for (Item item : items) {
                addItem(listedItems, item);
            }
            listedItems.sort(Comparator.comparing(ItemListItem::getDisplayString));
            categoryItemLists[i].listedItems = listedItems;
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
            if ((getItemListIndex() > 0)) {
                Sound.clickUp.play();
                getItemList().listIndex--;
            }
        }
        if (Client.input.check(Controls.BACK) || Client.input.check(Controls.DOWN)) {
            Client.input.stopInput(Controls.BACK);
            Client.input.stopInput(Controls.DOWN);
            if ((getItemListIndex() < getItemListItems().size() - 1)) {
                Sound.clickDown.play();
                getItemList().listIndex++;
            }
        }

        if (getItemListItems().size() > 0) {
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

        if (getItemListIndex() >= getItemListItems().size() && getItemListIndex() != 0) {
            getItemList().listIndex = getItemListItems().size() - 1;
        }

        if (getItemListItems().size() != 0) {
            inventoryItemIndex = inventory.getIndexOf(getItemListItems().get(getItemListIndex()).nextItem());
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
        draw(getItemList().category, bp + (fontWidth() * 2), bp, 0xF0F0F0);
        if (categoryIndex == categoryItemLists.length - 1) {
            draw(" >", bp + (fontWidth() * 2) + (catString.length() * fontWidth()), bp, 0x404040);
        } else {
            draw(" >", bp + (fontWidth() * 2) + (catString.length() * fontWidth()), bp, 0xF0F0F0);
        }
    }

    private void updateList() {

        int down = getItemListIndex() * getListItemHeight();
        if (down < -getItemList().listYShift) {
            getItemList().listYShift = -(down % -getItemList().listYShift);
        }

        if (down + (fontHeight()) >= itemListBitmap.height - getItemList().listYShift) {
            getItemList().listYShift = -(down - itemListBitmap.height + getListItemHeight());
        }

        itemListBitmap.fill(0x101010);
        if (getItemListItems().size() > 0) {
            itemListBitmap.fill(0, getItemList().listYShift + (getItemListIndex() * getListItemHeight()), itemListBitmap.width, getItemList().listYShift + ((getItemListIndex() + 1) * getListItemHeight()), 0x404040);
            for (int i = 0; i < getItemListItems().size(); i++) {
                int colour;
                if (mob.getRightHandItemIndex() == inventory.getIndexOf(getItemListItems().get(i).nextItem()) && !mob.rightHandEmpty) {
                    getItemListItems().get(i).selected = true;
                    colour = 0xF0F070;
                } else {
                    getItemListItems().get(i).selected = false;
                    colour = 0x707070;
                }
                if (getItemListIndex() == i) {
                    colour = 0xF0F0F0;
                }
                itemListBitmap.draw(getItemListItems().get(i).getDisplayString(), bp, getItemList().listYShift + (i * getListItemHeight()) + 2, colour);
            }
        }
        draw(itemListBitmap, bp, bp + fontHeight() + bp);
    }

    private void updateDetailsPane() {
        itemDetailsPane.fill(0x303030);
        if (getItemListItems().size() > 0 && getItemListItems().size() != getItemListIndex()) {
            Item item = getItemListItems().get(getItemListIndex()).nextItem();
            Bitmap icon = item.icon;
            Bitmap bGround = new Bitmap(18, 18);
            bGround.fill(0x202020);

            itemDetailsPane.draw(bGround, itemDetailsPane.halfWidth() - bGround.halfWidth(), bp);
            itemDetailsPane.draw(icon, itemDetailsPane.halfWidth() - icon.halfWidth(), bp + 1);

            int rowY = bp + bGround.height + bp;
            if (item.type.equals(Item.TYPE_WEAPON)) {
                itemDetailsPane.draw(Texture.damageIcon, bp, rowY + 1);
                itemDetailsPane.draw(Integer.toString(item.getDamage()), bp + (fontWidth() * 2), rowY, 0xF0F0F0);
                rowY += 10;
                itemDetailsPane.draw(Texture.rangeIcon, bp, rowY + 1);
                itemDetailsPane.draw(Integer.toString(item.getReach()), bp + (fontWidth() * 2), rowY, 0xF0F0F0);
            } else if (item.type.equals(Item.TYPE_CONSUMABLE)) {
                itemDetailsPane.draw(Texture.magnitudeIcon, bp, rowY + 1);
                itemDetailsPane.draw(Integer.toString(item.getMagnitude()), bp + (fontWidth() * 2), rowY, 0xF0F0F0);
                rowY += 10;
                itemDetailsPane.draw(Texture.durationIcon, bp, rowY + 1);
                itemDetailsPane.draw(Integer.toString(item.getDuration()) + "s", bp + (fontWidth() * 2), rowY, 0xF0F0F0);
            }
        }
        draw(itemDetailsPane, bp + itemListBitmap.width,bp + (lineHeight()));
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

    private int getItemListIndex() {
        return getItemList().listIndex;
    }
    
    private List<ItemListItem> getItemListItems() {
        return getItemList().listedItems;
    }
}
