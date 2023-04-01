package com.example.sound2.Models;

public class Sound {
    private int id;
    private String name;
    private String sound;
    private String image;

    public Sound() {
    }

    public Sound(int id, String name, String sound, String image) {
        this.id = id;
        this.name = name;
        this.sound = sound;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
