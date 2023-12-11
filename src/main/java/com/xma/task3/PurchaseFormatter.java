package com.xma.task3;

import java.util.*;

public class PurchaseFormatter {

    Map<ProductSupplier, PurchaseInfo> data;

    public PurchaseFormatter(Purchase... purchases) {
        data = collect(purchases);
    }

    public String formatFor(ProductSupplier[] suppliers, Station[] stations) {
        String[][] records = new String[suppliers.length][];
        for (int i = 0; i < suppliers.length; i++) {
            records[i] = toStrings(data.getOrDefault(suppliers[i], new PurchaseInfo(suppliers[i])), stations);
        }
        StringBuilder builder = formatTable(records, stations);
        builder.append("ИТОГО: ").append("%.2f".formatted(PurchaseInfo.totalAmount(data.values())));
        return builder.toString();
    }

    private StringBuilder formatTable(String[][] records, Station[] stations) {
        StringBuilder builder = new StringBuilder();
        makeTitle(stations, builder);
        for (var rec : records) {
            addRecord(rec, builder);
        }
        builder.append("-".repeat(80)).append("\n");
        return builder;
    }

    private void addRecord(String[] rec, StringBuilder builder) {
        builder.append("|%10s|".formatted(rec[0]));
        for (int i = 1; i < rec.length - 2; i++) {
            builder.append("%7s|".formatted(rec[i]));
        }
        builder.append("%17s|".formatted(rec[rec.length - 2]));
        builder.append("%17s|\n".formatted(rec[rec.length - 1]));
    }


    private static void makeTitle(Station[] stations, StringBuilder builder) {
        builder.append("-".repeat(80)).append("\n");
        builder.append("|Поставщики|");
        for (var station : stations) {
            builder.append(" АЗС %s |".formatted(station.getTitle()));
        }
        builder.append("Стоимость закупки|");
        builder.append("С учетом доставки|\n");
        builder.append("-".repeat(80)).append("\n");
    }

    private String[] toStrings(PurchaseInfo purchaseInfo, Station[] stations) {
        List<String> records = new ArrayList<>();

        records.add(String.valueOf(purchaseInfo.supplier.getSupplierNumber()));
        for (Station station : stations) {
            records.add(String.valueOf(purchaseInfo.getVolumeFor(station)));
        }
        double productAmount = purchaseInfo.calcProductAmount();
        records.add("%.2f".formatted(productAmount));
        double deliveryAmount = purchaseInfo.calDeliveryAmount();
        records.add("%.2f".formatted(productAmount + deliveryAmount));

        return records.toArray(new String[0]);
    }


    private Map<ProductSupplier, PurchaseInfo> collect(Purchase[] purchases) {
        Map<ProductSupplier, PurchaseInfo> map = new HashMap<>();
        for (var purchase : purchases) {
            if (map.containsKey(purchase.getSupplier())) {
                map.get(purchase.getSupplier()).setPurchaseData(purchase.getStation(), purchase.getVolume());
            } else {
                map.put(purchase.getSupplier(), PurchaseInfo.createInfo(purchase));
            }
        }
        return map;
    }


    private static class PurchaseInfo {
        ProductSupplier supplier;
        Map<Station, Integer> stationsVolumes;

        public PurchaseInfo(ProductSupplier supplier) {
            this.supplier = supplier;
            stationsVolumes = new HashMap<>();
        }

        public static double totalAmount(Collection<PurchaseInfo> values) {
            double sum = 0;
            for (PurchaseInfo info : values) {
                sum += info.getPurchaseAmount();
            }
            return sum;
        }

        public int getVolumeFor(Station station) {
            return stationsVolumes.getOrDefault(station, 0);
        }

        public void setPurchaseData(Station station, int volume) {
            stationsVolumes.put(station, volume);
        }

        public static PurchaseInfo createInfo(Purchase purchase) {
            PurchaseInfo info = new PurchaseInfo(purchase.getSupplier());
            info.setPurchaseData(purchase.getStation(), purchase.getVolume());
            return info;
        }

        public double getPurchaseAmount() {
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
