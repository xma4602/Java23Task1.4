package com.xma.task3;

import java.util.Arrays;
import java.util.Comparator;

/**
 * поставщик топлива
 */
public class ProductSupplier {

    /**
     * поряднывый номер поставщика в системе
     */
    private final int supplierNumber;
    /**
     * стоимость за единицу топлива, поставляемого данным поставщиком
     */
    private final double productCost;
    private final Delivery[] deliveries;
    private int availableVolume;

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

    public static Comparator<ProductSupplier> comparatorByCost() {
        return Comparator.comparing(ProductSupplier::getProductCost);
    }

    public boolean isAvailableVolumeNotEmpty() {
        return availableVolume != 0;
    }

    public static Shipment makePurchase(ProductSupplier supplier, Delivery delivery) {
        Station station = delivery.getStation();
        Shipment shipment = new Shipment(supplier, station);

        int minVolume = Math.min(station.getRequiredVolume(), supplier.getAvailableVolume());
        shipment.setVolume(minVolume);
        supplier.reduceAvailableFuelVolume(minVolume);
        station.reduceRequiredVolume(minVolume);

        return shipment;
    }

    private void reduceAvailableFuelVolume(int fuelVolume) {
        this.availableVolume -= fuelVolume;
    }

    public Delivery[] getDeliveries() {
        return deliveries;
    }

    /**
     * доставка поставщика к станции
     */
    public static class Delivery implements Comparable<Delivery> {
        private final Station station;
        private final int deliveryCost;

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
