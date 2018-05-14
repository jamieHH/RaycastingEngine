package com.jamie.raycasting.items;

import com.jamie.raycasting.items.consumables.Consumable;
import com.jamie.raycasting.items.weapons.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        for (int i = 0; i < countItems(); i++) {
            if (getItem(i).getInfo().get("type") == type) {
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

    public int countItems() {
        return getItems().size();
    }

    public List<Map<String, String>> getItemsInfo() {
        List<Map<String, String>> info = new ArrayList<Map<String, String>>();
        for (int i = 0; i < countItems(); i++) {
            info.add(getItem(i).getInfo());
        }

        return info;
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

    public int countWeapons() {
        return getWeapons().size();
    }

    public List<Map<String, String>> getWeaponsInfo() {
        List<Map<String, String>> info = new ArrayList<Map<String, String>>();
        for (int i = 0; i < countWeapons(); i++) {
            info.add(getWeapon(i).getInfo());
        }

        return info;
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

    public int countConsumables() {
        return getConsumables().size();
    }

    public List<Map<String, String>> getConsumablesInfo() {
        List<Map<String, String>> info = new ArrayList<Map<String, String>>();
        for (int i = 0; i < countConsumables(); i++) {
            info.add(getConsumable(i).getInfo());
        }

        return info;
    }
}
