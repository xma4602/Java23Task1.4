package com.xma.task3;

/**
 * Определяет станцию АЗС
 */
public class Station {
    /**
     * объем необходимого станции топлива
     */
    private int requiredVolume;
    /**
     * название/номер АЗС
     */
    private final String title;

    public Station(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public int getRequiredVolume() {
        return requiredVolume;
    }

    public void setRequiredVolume(int volume) {
        requiredVolume = volume;
    }

    public boolean requiredVolumeNotEmpty() {
        return requiredVolume != 0;
    }

    public void reduceRequiredVolume(int volume) {
        if (requiredVolume < volume) {
            throw new IllegalArgumentException("Нельзя уменьшить топливо (4%d) больше, чем его осталось (%d)".formatted(volume, requiredVolume));
        }
        requiredVolume -= volume;
    }
}
