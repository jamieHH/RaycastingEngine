package com.jamie.raycasting.graphics.overlays.menus;

import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.app.Client;
import com.jamie.raycasting.app.Sound;
import com.jamie.raycasting.entities.mobs.Mob;
import com.jamie.raycasting.graphics.overlays.Overlay;
import com.jamie.raycasting.input.Controls;
import com.jamie.raycasting.items.Inventory;

import java.util.ArrayList;
import java.util.List;

public class ItemsOverlay extends Overlay
{
    private Mob mob;
    private Inventory inventory;
    private List<ItemTile> itemTiles = new ArrayList<ItemTile>();
    private static final int ICON_SIZE = 16;
    private int itemIndex = 0;


    public ItemsOverlay(int width, int height) {
        super(width, height);
    }

    private class ItemTile
    {
        String title;
        Bitmap icon;

        public ItemTile(String title, Bitmap icon) {
            this.title = title;
            this.icon = icon;
        }
    }

    public void tick() {
        this.mob = Client.getPlayer();
        this.inventory = Client.getPlayer().inventory;

        List<ItemTile> il = new ArrayList<ItemTile>();
        for (int i = 0; i < inventory.getItems().size(); i++) {
            il.add(new ItemTile(inventory.getItem(i).name, inventory.getItem(i).icon));
        }
        itemTiles = il;

        if (itemTiles.size() > 0 && !(itemTiles.size() < itemIndex + 1)) {
            if (Client.input.check(Controls.FORWARD) || Client.input.check(Controls.UP)) {
                Client.input.stopInput(Controls.FORWARD);
                Client.input.stopInput(Controls.UP);
                if ((itemIndex > 0)) {
                    Sound.clickUp.play();
                    itemIndex--;
                }
            }
            if (Client.input.check(Controls.BACK) || Client.input.check(Controls.DOWN)) {
                Client.input.stopInput(Controls.BACK);
                Client.input.stopInput(Controls.DOWN);
                if ((itemIndex < inventory.getItems().size() - 1)) {
                    Sound.clickDown.play();
                    itemIndex++;
                }
            }

            if (Client.input.check(Controls.HOT1)) {
                Client.input.stopInput(Controls.HOT1);
                Sound.clickAction.play();
                if (mob.getHotkey(1) == null || mob.getHotkey(1) != itemIndex) {
                    mob.setHotkey(1, itemIndex);
                } else {
                    mob.setHotkey(1, null);
                }
            }
            if (Client.input.check(Controls.HOT2)) {
                Client.input.stopInput(Controls.HOT2);
                Sound.clickAction.play();
                if (mob.getHotkey(2) == null || mob.getHotkey(2) != itemIndex) {
                    mob.setHotkey(2, itemIndex);
                } else {
                    mob.setHotkey(2, null);
                }
            }
            if (Client.input.check(Controls.HOT3)) {
                Client.input.stopInput(Controls.HOT3);
                Sound.clickAction.play();
                if (mob.getHotkey(3) == null || mob.getHotkey(3) != itemIndex) {
                    mob.setHotkey(3, itemIndex);
                } else {
                    mob.setHotkey(3, null);
                }
            }

            if (Client.input.check(Controls.ACTION) || Client.input.check(Controls.ENTER)) {
                Client.input.stopInput(Controls.ACTION);
                Client.input.stopInput(Controls.ENTER);
                Sound.clickAction.play();
                mob.useItemIndex(inventory.getIndexOf(inventory.getItem(itemIndex)));
            }
        }
    }

    public void update() {
        fill(0x404040);
        Bitmap itemLine = new Bitmap(itemTiles.size() * ICON_SIZE, ICON_SIZE);
        itemLine.fill(0x404040);
        for (int i = 0; i < itemTiles.size(); i++) {
            itemLine.draw(itemTiles.get(i).icon, i * ICON_SIZE, 0);
        }
        draw(itemLine, halfWidth() - (ICON_SIZE * itemIndex) - (ICON_SIZE / 2), halfHeight() - itemLine.halfHeight());

        if (itemTiles.size() > 0) {
            if (!(itemTiles.size() < itemIndex + 1)) {
                Bitmap itemTitle = textBox(itemTiles.get(itemIndex).title, 0xF0F0F0, 0);
                draw(itemTitle, halfWidth() - itemTitle.halfWidth(), height - itemTitle.height);

                Bitmap itemHighlight = square(ICON_SIZE + 4, ICON_SIZE + 4, 0xF0F0F0);
                draw(itemHighlight, halfWidth() - itemHighlight.halfWidth(), halfHeight() - itemHighlight.halfHeight());
            } else {
                itemIndex--;
            }
        } else {
            Bitmap emptyMessage = textBox("Empty", 0xF0F0F0, 0);
            draw(emptyMessage, halfWidth() - emptyMessage.halfWidth(), halfHeight() - emptyMessage.halfHeight());
        }

    }
}
