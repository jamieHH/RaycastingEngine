package com.jamie.jamapp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class XmlLoader
{
    public static String[] getKeys(String path, String[] keys) {
        String[] array = new String[keys.length];
        Properties properties = new Properties();
        try {
            FileInputStream read = new FileInputStream(path);
            properties.loadFromXML(read);

            for (int i = 0; i < keys.length; i++) {
                array[i] = properties.getProperty(keys[i]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not find path: " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return array;
    }
}
