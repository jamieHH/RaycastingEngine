package com.jamie.raycasting.app;

import com.jamie.jamapp.App;
import com.jamie.jamapp.Bitmap;
import com.jamie.raycasting.items.Item;
import com.jamie.raycasting.world.blocks.TriggerableBlock;
import com.jamie.raycasting.world.levels.Level;

import java.util.ArrayList;
import java.util.List;

public class Console {

    private static List<String> lines = new ArrayList<String>();


    public Console(Client client) {

    }

    public static void log(String s) {
        lines.add(s);
    }

    public static Level getLevel() {
        return Client.getWorld().level;
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
                Client.setActiveOverlay(null);
                break;
            case "getPos": // get player pos
                log(Client.getPlayer().posX + ", " + Client.getPlayer().posZ);
                break;
            case "setPos":
                Client.getPlayer().setPosition(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
                break;
            case "getRot": // get player pos
                log(Double.toString(Client.getPlayer().getRotation()));
                break;
            case "dayNight": // enable day night cycle
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
            case "possessNextMob": // posses next mob
                Client.possessNextMob();
                break;
            case "switchPerspective": // switch perspective
                Client.switchPerspective();
                break;
            case "setRes": // set resolution
                if (args.length >= 3) {
                    App.setDisplayResolution(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
                    Client.resizeMenus();
                } else {
                    log("Specify new width and height");
                }
                break;
            case "smallFont": // set small font usage
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
            case "noclip":
                if (args.length >= 2) {
                    Client.getPlayer().isSolid = (Integer.parseInt(args[1]) < 1);
                } else {
                    log("Specify to set 0 or 1");
                }
                break;
            case "triggerb":
                if (args.length >= 2) {
                    if (getLevel() != null) {
                        if (getLevel().getBlockByReference(args[1]) != null) {
                            if (getLevel().getBlockByReference(args[1]) instanceof TriggerableBlock) {
                                ((TriggerableBlock) getLevel().getBlockByReference(args[1])).trigger();
                            } else {
                                log("This block is not triggerable");
                            }
                        } else {
                            log("Could not find a block by reference: " + args[1]);
                        }
                    } else {
                        log("Player must be inside a level to use this command");
                    }
                } else {
                    log("Provide a reference for a block in this level");
                }
                break;
            case "listEnts":
                if (args.length >= 1) {
                    if (getLevel() != null) {
                        for (int i = 0; i < getLevel().countEntities(); i++) {
                            log(getLevel().getEntity(i).hashCode() + " : " + getLevel().getEntity(i).getClass().getSimpleName());
                        }
                    } else {
                        log("Player must be inside a level to use this command");
                    }
                } else {
                    log("Provide a reference for a block in this level");
                }
                break;
            case "addItem": // add item
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
