package com.xma.task3;

public class Shipment {
    private final ProductSupplier supplier;
    private final Station station;
    private int volume;

    public Shipment(ProductSupplier supplier, Station station) {
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
