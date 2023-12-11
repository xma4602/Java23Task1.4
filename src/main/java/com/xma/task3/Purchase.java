package com.xma.task3;

public class Purchase {
    private ProductSupplier supplier;
    private Station station;
    private int volume;

    public Purchase(ProductSupplier supplier, Station station) {
        this.supplier = supplier;
        this.station = station;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public Station getStation() {
        return station;
    }

    public ProductSupplier getSupplier() {
        return supplier;
    }

    public int getVolume() {
        return volume;
    }
}
