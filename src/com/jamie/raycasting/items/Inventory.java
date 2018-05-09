package com.jamie.raycasting.items;

import com.jamie.raycasting.items.consumables.Consumable;
import com.jamie.raycasting.items.weapons.Weapon;

import java.util.ArrayList;
import java.util.List;

public class Inventory
{
    List<Item> items = new ArrayList<Item>();


    public void addItem(Item item) {
        items.add(item);
    }

    public Item getItem(int i) {
        return items.get(i);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public int countItems() {
        return getItems().size();
    }

    public List<Weapon> getWeapons() {
        List<Weapon> list = new ArrayList<Weapon>();

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) instanceof Weapon) {
                list.add((Weapon) items.get(i));
            }
        }

        return list;
    }

    public int countWeapons() {
        return getWeapons().size();
    }

    public List<Consumable> getConsumables() {
        List<Consumable> list = new ArrayList<Consumable>();

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) instanceof Consumable) {
                list.add((Consumable) items.get(i));
            }
        }

        return list;
    }

    public int countConsumables() {
        return getConsumables().size();
    }
}
