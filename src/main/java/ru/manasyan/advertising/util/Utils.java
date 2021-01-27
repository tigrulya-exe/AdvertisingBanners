package ru.manasyan.advertising.util;

public class Utils {
    static public String toSearchTemplate(String str) {
        return "%" + str.toLowerCase() + "%";
    }
}
