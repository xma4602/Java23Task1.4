package com.xma.task3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * представляет простой алгоритм вычисления необходимых закупок
 */
public class SimpleShipmentResolver implements ShipmentResolver {
    private Station[] stations;
    private List<Shipment> shipments;

    public SimpleShipmentResolver() {
    }

    @Override
    public Shipment[] resolve(ProductSupplier[] suppliers, Station[] stations) {
        this.stations = stations;

        Arrays.sort(suppliers, ProductSupplier.comparatorByCost());
        shipments = new ArrayList<>();
        //для каждого поставщика вычислить поставки, которые он может реализовать
        for (ProductSupplier supplier : suppliers) {
            boolean stationsEmpty = resolverForSupplier(supplier);
            //проверить наличие запроса станций
            if (stationsEmpty) break;
        }
        return shipments.toArray(new Shipment[0]);
    }

    private boolean resolverForSupplier(ProductSupplier supplier) {
        //флаг наличия хотя бы одной станции с запросом поставки
        boolean stationsNotEmpty = true;
        //флаг наличия у поставщика свободного для поставки товара
        boolean supplierNotEmpty = true;

        while (supplierNotEmpty && stationsNotEmpty) {
            for (ProductSupplier.Delivery delivery : supplier.getDeliveries()) {
                //если на данную станцию нужно делать поставку
                if (delivery.getStation().requiredVolumeNotEmpty()) {
                    //сформировать поставку и сохранить ее
                    Shipment shipment = ProductSupplier.makePurchase(supplier, delivery);
                    shipments.add(shipment);
                    //проверить станции на заполненность
                    stationsNotEmpty = isStationsRequiredVolumeNotEmpty();
                    if (!stationsNotEmpty) break;
                    //проверить поставщика на возможност ьдальше поставлять
                    supplierNotEmpty = supplier.isAvailableVolumeNotEmpty();
                    if (!supplierNotEmpty) break;
                }
            }
        }
        //возвращает факт заколненности станций
        return !stationsNotEmpty;
    }

    private boolean isStationsRequiredVolumeNotEmpty() {
        for (Station station : stations) {
            if (station.requiredVolumeNotEmpty()) return true;
        }
        return false;

    }
}

