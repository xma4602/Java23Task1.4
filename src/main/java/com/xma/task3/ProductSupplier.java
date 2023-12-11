package com.xma.task3;

import java.util.Arrays;
import java.util.Comparator;

public class ProductSupplier {

    private int supplierNumber;
    private int availableVolume;
    private double productCost;
    private Delivery[] deliveries;

    public ProductSupplier(int supplierNumber, int fuelVolume, double productCost, Delivery... deliveries) {
        this.supplierNumber = supplierNumber;
        this.availableVolume = fuelVolume;
        this.productCost = productCost;
        this.deliveries = deliveries;
        Arrays.sort(this.deliveries);
    }


    public int getSupplierNumber() {
        return supplierNumber;
    }

    public double getProductCost() {
        return productCost;
    }

    public int getAvailableVolume() {
        return availableVolume;
    }

    public int getDeliveryCost(Station station) {
        for (var d : deliveries) {
            if (d.getStation().equals(station)) {
                return d.getDeliveryCost();
            }
        }
        return -1;
    }


    public static Comparator<ProductSupplier> comparatorByCost() {
        return Comparator.comparing(ProductSupplier::getProductCost);
    }

    public boolean availableVolumeNotEmpty() {
        return availableVolume != 0;
    }

    public static Purchase makePurchase(ProductSupplier supplier, Delivery delivery) {
        Station station = delivery.getStation();
        Purchase purchase = new Purchase(supplier, station);

        int minVolume = Math.min(station.getRequiredVolume(), supplier.getAvailableVolume());
        purchase.setVolume(minVolume);
        supplier.reduceAvailableFuelVolume(minVolume);
        station.reduceRequiredFuelVolume(minVolume);

        return purchase;
    }

    private void reduceAvailableFuelVolume(int fuelVolume) {
        this.availableVolume -= fuelVolume;
    }

    public Delivery[] getDeliveries() {
        return deliveries;
    }

    public static class Delivery implements Comparable<Delivery> {
        private Station station;
        private int deliveryCost;

        public Delivery(Station station, int deliveryCost) {
            this.station = station;
            this.deliveryCost = deliveryCost;
        }

        public Station getStation() {
            return station;
        }

        public int getDeliveryCost() {
            return deliveryCost;
        }

        @Override
        public int compareTo(Delivery o) {
            return this.deliveryCost - o.deliveryCost;
        }
    }
}
