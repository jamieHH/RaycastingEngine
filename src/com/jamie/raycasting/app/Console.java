package com.jamie.raycasting.app;

import com.jamie.jamapp.App;

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
                Client.setActiveOverlay(null);
                break;
            case "getpos": // get player pos
                log(Client.getPlayer().posX + ", " + Client.getPlayer().posZ);
                break;
            case "getrot": // get player pos
                log(Double.toString(Client.getPlayer().getRotation()));
                break;
            case "level": // move to level
                if (args.length >= 2) {
                    String levelName = args[1];
                    int id = 0;
                    if (args.length > 2) {
                        id = Integer.parseInt(args[2]);
                    }
                    try {
                        Client.getPlayer().level.world.switchLevel(Client.getPlayer(), levelName, id);
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
            case "setres":
                if (args.length >= 3) {
                    App.setDisplayResolution(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
                }
            case "additem": // add item
                log("[do add item]");
                break;
            default:
                log("Command: " + args[0] + " not recognised");
        }
    }

    public static List<String> getLines() {
        return lines;
    }
}
