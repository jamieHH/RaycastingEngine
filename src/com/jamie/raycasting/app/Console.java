package com.jamie.raycasting.app;

import com.jamie.jamapp.App;
import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Console {

    private static List<String> lines = new ArrayList<String>();


    public Console(Client client) {

    }

    public static void log(String s) {
        lines.add(s);
    }

    public static void run(String command) {
        log(command);
        String[] args = command.trim().split("\\s+");

        switch (args[0]) {
            case "": // blank
                break;
            case "clear": // clear all console lines
                Console.lines = new ArrayList<String>();
                break;
            case "x": // exit window
                Client.setGetActiveOverlay(null);
                break;
            case "getpos": // get player pos
                log(Client.getPlayer().posX + ", " + Client.getPlayer().posZ);
                break;
            case "setpos":
                Client.getPlayer().setPosition(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
                break;
            case "getrot": // get player pos
                log(Double.toString(Client.getPlayer().getRotation()));
                break;
            case "daynight": // enable day night cycle
                Client.getWorld().enableTimeCycle = (Integer.parseInt(args[1]) > 0);
                break;
            case "level": // move to level
                if (args.length >= 2) {
                    String levelName = args[1];
                    int id = 0;
                    if (args.length > 2) {
                        id = Integer.parseInt(args[2]);
                    }
                    try {
                        Client.getWorld().switchLevel(Client.getPlayer(), levelName, id);
                    } catch (Exception e) {
                        log("Cannot load level: " + levelName);
                    }
                } else {
                    log("Specify the level name");
                }
                break;
            case "possessnextmob": // posses next mob
                Client.possessNextMob();
                break;
            case "switchperspective": // switch perspective
                Client.switchPerspective();
                break;
            case "setres": // set resolution
                if (args.length >= 3) {
                    App.setDisplayResolution(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
                    Client.resizeMenus();
                } else {
                    log("specify new width and height");
                }
                break;
            case "smallfont": // set small font usage
                if (args.length >= 2) {
                    Bitmap.isSmallFont = (Integer.parseInt(args[1]) > 0);
                } else {
                    log("Specify to set 0 or 1");
                }
                break;
            case "indev":
                if (args.length >= 2) {
                    App.inDev = (Integer.parseInt(args[1]) > 0);
                } else {
                    log("Specify to set 0 or 1");
                }
                break;
            case "additem": // add item
                if (args.length >= 2) {
                    String itemName = args[1].substring(0, 1).toUpperCase() + args[1].substring(1);
                    try {
                        Item item = (Item) Class.forName("com.jamie.raycasting.items." + itemName).getDeclaredConstructor().newInstance();
                        Client.getPlayer().addItem(item);
                    } catch (Exception e) {
                        log("Cannot add item: " + itemName);
                    }
                } else {
                    log("Specify the item to add");
                }
                break;
            default:
                log("Command: " + args[0] + " not recognised");
        }
    }

    public static List<String> getLines() {
        return lines;
    }
}
