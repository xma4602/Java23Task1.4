package com.xma.task3;

import java.util.*;

/**
 * форматирует список поставок для вывода
 */
public class ShipmentFormatter {

    private final Map<ProductSupplier, ShipmentInfo> data;

    public ShipmentFormatter(Shipment... shipments) {
        data = collect(shipments);
    }

    /**
     * преобразует поставки в формат для ввода
     *
     * @param shipments список поставок
     * @return сопоставление поставщиов и инф-ии о их поставках
     */
    private Map<ProductSupplier, ShipmentInfo> collect(Shipment[] shipments) {
        Map<ProductSupplier, ShipmentInfo> map = new HashMap<>();
        for (var shipment : shipments) {
            ProductSupplier supplier = shipment.getSupplier();
            if (map.containsKey(supplier)) {
                map.get(supplier).setShipmentData(shipment);
            } else {
                map.put(supplier, new ShipmentInfo(shipment));
            }
        }
        return map;
    }

    /**
     * форматирует в табличное представление
     *
     * @param suppliers поставщики, для которых форматировать данные
     * @param stations  станции, для которых форматировать данные
     * @return строкое представление таблицы поставок
     */
    public String formatFor(ProductSupplier[] suppliers, Station[] stations) {
        String[][] records = new String[suppliers.length][];

        for (int i = 0; i < suppliers.length; i++) {
            ShipmentInfo info = data.getOrDefault(suppliers[i], new ShipmentInfo(suppliers[i]));
            records[i] = shipmentInfoToStrings(info, stations);
        }
        StringBuilder builder = new StringBuilder();
        formatTitle(builder, stations);
        formatTable(builder, records);
        formatSummary(builder);
        return builder.toString();
    }

    private String[] shipmentInfoToStrings(ShipmentInfo shipmentInfo, Station[] stations) {
        List<String> records = new ArrayList<>();

        records.add(String.valueOf(shipmentInfo.supplier.getSupplierNumber()));
        for (Station station : stations) {
            records.add(String.valueOf(shipmentInfo.getVolumeFor(station)));
        }
        double productAmount = shipmentInfo.calcProductAmount();
        records.add("%.2f".formatted(productAmount));
        double deliveryAmount = shipmentInfo.calDeliveryAmount();
        records.add("%.2f".formatted(productAmount + deliveryAmount));

        return records.toArray(new String[0]);
    }

    private void formatTitle(StringBuilder builder, Station[] stations) {
        builder.append("-".repeat(80)).append("\n");
        builder.append("|Поставщики|");
        for (var station : stations) {
            builder.append(" АЗС %s |".formatted(station.getTitle()));
        }
        builder.append("Стоимость закупки|");
        builder.append("С учетом доставки|\n");
        builder.append("-".repeat(80)).append("\n");
    }

    private void formatTable(StringBuilder builder, String[][] records) {
        for (var rec : records) {
            addRecord(rec, builder);
        }
        builder.append("-".repeat(80)).append("\n");
    }

    private void addRecord(String[] rec, StringBuilder builder) {
        builder.append("|%10s|".formatted(rec[0]));
        for (int i = 1; i < rec.length - 2; i++) {
            builder.append("%7s|".formatted(rec[i]));
        }
        builder.append("%17s|".formatted(rec[rec.length - 2]));
        builder.append("%17s|\n".formatted(rec[rec.length - 1]));
    }

    private void formatSummary(StringBuilder builder) {
        builder.append("ИТОГО: ").append("%.2f".formatted(ShipmentInfo.totalAmount(data.values())));
    }

    private static class ShipmentInfo {
        ProductSupplier supplier;
        Map<Station, Integer> stationsVolumes;

        public ShipmentInfo(ProductSupplier supplier) {
            this.supplier = supplier;
            stationsVolumes = new HashMap<>();
        }

        public ShipmentInfo(Shipment shipment) {
            this(shipment.getSupplier());
            setShipmentData(shipment);
        }

        public static double totalAmount(Collection<ShipmentInfo> values) {
            double sum = 0;
            for (ShipmentInfo info : values) {
                sum += info.calcShipmentAmount();
            }
            return sum;
        }

        public int getVolumeFor(Station station) {
            return stationsVolumes.getOrDefault(station, 0);
        }

        public void setShipmentData(Shipment shipment) {
            stationsVolumes.put(shipment.getStation(), shipment.getVolume());
        }

        public double calcShipmentAmount() {
            return calcProductAmount() + calDeliveryAmount();
        }

        public double calcProductAmount() {
            double sum = 0;
            for (Integer volume : stationsVolumes.values()) {
                sum += supplier.getProductCost() * volume;
            }
            return Math.round(sum * 100) / 100.0;
        }

        private double calDeliveryAmount() {
            int sum = 0;
            for (var delivery : supplier.getDeliveries()) {
                if (stationsVolumes.containsKey(delivery.getStation())) {
                    sum += delivery.getDeliveryCost();
                }
            }
            return sum;
        }
    }
}
