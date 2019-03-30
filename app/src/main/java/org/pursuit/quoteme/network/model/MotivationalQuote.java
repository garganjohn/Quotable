package org.pursuit.quoteme.network.model;

public class MotivationalQuote {
    private String title;
    private String content;
    private int ID;
//TODO use gson to convert to convert json -> sharedprefs -> json shared prefs cant take objects
    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getID() {
        return ID;
    }
}
