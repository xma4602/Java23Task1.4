package com.xma.task3;

public class Station {
    private int requiredVolume;
    private String title;

    public Station(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public int getRequiredVolume() {
        return requiredVolume;
    }

    public void setRequiredVolume(int requiredVolume) {
        this.requiredVolume = requiredVolume;
    }

    public boolean requiredVolumeNotEmpty() {
        return requiredVolume != 0;
    }

    public void reduceRequiredFuelVolume(int fuelVolume) {
        this.requiredVolume -= fuelVolume;
    }
}
