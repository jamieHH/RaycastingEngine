package com.jamie.raycasting.items;

import com.jamie.raycasting.items.consumables.Consumable;
import com.jamie.raycasting.items.weapons.Weapon;

import java.util.ArrayList;
import java.util.List;

public class Inventory
{
    private List<Item> items = new ArrayList<Item>();


    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public List<Item> getItemsByType(String type) {
        List<Item> items = new ArrayList<Item>();
        for (int i = 0; i < getItems().size(); i++) {
            if (getItem(i).getType().equals(type)) {
                items.add(getItem(i));
            }
        }
        return items;
    }

    public int getIndexOf(Item item) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) == item) {
                return i;
            }
        }

        return -1;
    }

    // All Items
    public List<Item> getItems() {
        return items;
    }

    public Item getItem(int i) {
        return items.get(i);
    }

    // Weapons
    public List<Weapon> getWeapons() {
        List<Weapon> list = new ArrayList<Weapon>();

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) instanceof Weapon) {
                list.add((Weapon) items.get(i));
            }
        }

        return list;
    }

    public Weapon getWeapon(int i) {
        return getWeapons().get(i);
    }

    // Consumables
    public List<Consumable> getConsumables() {
        List<Consumable> list = new ArrayList<Consumable>();

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) instanceof Consumable) {
                list.add((Consumable) items.get(i));
            }
        }

        return list;
    }

    public Consumable getConsumable(int i) {
        return getConsumables().get(i);
    }

    // Keys
    public List<MiscItem> getMiscItems() {
        List<MiscItem> list = new ArrayList<MiscItem>();

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) instanceof MiscItem) {
                list.add((MiscItem) items.get(i));
            }
        }

        return list;
    }

    public MiscItem getMiscItem(int i) {
        return getMiscItems().get(i);
    }
}
