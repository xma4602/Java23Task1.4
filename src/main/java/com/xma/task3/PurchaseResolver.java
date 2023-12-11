package com.xma.task3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PurchaseResolver {
    private ProductSupplier[] suppliers;
    private Station[] stations;

    public PurchaseResolver(ProductSupplier[] suppliers, Station[] stations) {
        this.suppliers = suppliers;
        this.stations = stations;
    }

    public Purchase[] resolve() {
        Arrays.sort(suppliers, ProductSupplier.comparatorByCost());
        List<Purchase> purchases = new ArrayList<>();
        for (ProductSupplier supplier : suppliers) {
            boolean stationsEmpty = resolverForSupplier(supplier, purchases);
            if (stationsEmpty) break;
        }
        return purchases.toArray(new Purchase[0]);
    }

    private boolean resolverForSupplier(ProductSupplier supplier, List<Purchase> purchases) {
        boolean stationsNotEmpty = true;
        boolean supplierNotEmpty = true;

        while (supplierNotEmpty && stationsNotEmpty) {
            for (ProductSupplier.Delivery delivery : supplier.getDeliveries()) {
                if (delivery.getStation().requiredVolumeNotEmpty()) {
                    Purchase purchase = ProductSupplier.makePurchase(supplier, delivery);
                    purchases.add(purchase);

                    stationsNotEmpty = isStationsRequiredVolumeNotEmpty(stations);
                    if (!stationsNotEmpty) break;
                    supplierNotEmpty = supplier.availableVolumeNotEmpty();
                    if (!supplierNotEmpty) break;
                }
            }
        }
        return !stationsNotEmpty;
    }

    private boolean isStationsRequiredVolumeNotEmpty(Station[] stations) {
        for (Station station : stations) {
            if (station.requiredVolumeNotEmpty()) return true;
        }
        return false;

    }
}

