package com.jamie.raycasting.app;

public class Console {

    private Client client;


    public Console(Client client) {
        this.client = client;
    }

    public static void run(String command) {
        System.out.println(command); ///////////////////
        String[] keys = {
                "coc",
                "addItem",
                "setBlock",
        };

        String match = "default";
        for (int i = 0; i < keys.length; i++) {
            if (command.contains(keys[i])) {
                match = keys[i];
                break;
            }
        }

        switch (match) {
            case "coc":
                System.out.println("do coc");
                break;
            case "addItem":
                System.out.println("do addItem");
                break;
            default:
                System.out.println("No command found for: " + command);
        }
    }
}
