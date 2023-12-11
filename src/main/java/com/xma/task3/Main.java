package com.xma.task3;

import java.util.Arrays;
import java.util.Scanner;

import static com.xma.task3.DataSource.*;

public class Main {
    static ProductSupplier[] suppliers;
    static Station[] stations;

    public static void main(String[] args) {
        init();
        //inputVolumes();
        setVolumes();

        PurchaseResolver resolver = new PurchaseResolver(Arrays.copyOf(suppliers, suppliers.length), stations);
        Purchase[] purchases = resolver.resolve();
        PurchaseFormatter formatter = new PurchaseFormatter(purchases);
        System.out.println(formatter.formatFor(suppliers, stations));
    }

    private static void setVolumes() {
        stations[0].setRequiredVolume(400);
        stations[1].setRequiredVolume(550);
        stations[2].setRequiredVolume(280);
        stations[3].setRequiredVolume(310);
    }

    private static void inputVolumes() {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < stations.length; i++) {
            boolean isIncorrect = true;
            do {
                System.out.printf("Введите необходимый объем поставки для АЗС %s: ", stations[i].getTitle());
                try {
                    String input = scanner.next();
                    int volume = Integer.parseInt(input);
                    if (volume <= 0) throw new IllegalArgumentException();
                    stations[i].setRequiredVolume(volume);
                    isIncorrect = false;
                } catch (IllegalArgumentException e) {
                    System.out.println("Введено некорректное знгачение. Попробуйте снова.");
                }
            }
            while (isIncorrect);
        }

    }

    private static void init() {
        stations = new Station[]{
                new Station("А"),
                new Station("Б"),
                new Station("В"),
                new Station("Г"),
        };
        suppliers = new ProductSupplier[]{
                new ProductSupplier(1, SUPPLIER_1_VOLUME, SUPPLIER_1_COST,
                        new ProductSupplier.Delivery(stations[0], SUPPLIER_1_STATION_A),
                        new ProductSupplier.Delivery(stations[1], SUPPLIER_1_STATION_B),
                        new ProductSupplier.Delivery(stations[2], SUPPLIER_1_STATION_C),
                        new ProductSupplier.Delivery(stations[3], SUPPLIER_1_STATION_D)
                ),
                new ProductSupplier(2, SUPPLIER_2_VOLUME, SUPPLIER_2_COST,
                        new ProductSupplier.Delivery(stations[0], SUPPLIER_2_STATION_A),
                        new ProductSupplier.Delivery(stations[1], SUPPLIER_2_STATION_B),
                        new ProductSupplier.Delivery(stations[2], SUPPLIER_2_STATION_C),
                        new ProductSupplier.Delivery(stations[3], SUPPLIER_2_STATION_D)
                ),
                new ProductSupplier(3, SUPPLIER_3_VOLUME, SUPPLIER_3_COST,
                        new ProductSupplier.Delivery(stations[0], SUPPLIER_3_STATION_A),
                        new ProductSupplier.Delivery(stations[1], SUPPLIER_3_STATION_B),
                        new ProductSupplier.Delivery(stations[2], SUPPLIER_3_STATION_C),
                        new ProductSupplier.Delivery(stations[3], SUPPLIER_3_STATION_D)
                ),
                new ProductSupplier(4, SUPPLIER_4_VOLUME, SUPPLIER_4_COST,
                        new ProductSupplier.Delivery(stations[0], SUPPLIER_4_STATION_A),
                        new ProductSupplier.Delivery(stations[1], SUPPLIER_4_STATION_B),
                        new ProductSupplier.Delivery(stations[2], SUPPLIER_4_STATION_C),
                        new ProductSupplier.Delivery(stations[3], SUPPLIER_4_STATION_D)
                ),
                new ProductSupplier(5, SUPPLIER_5_VOLUME, SUPPLIER_5_COST,
                        new ProductSupplier.Delivery(stations[0], SUPPLIER_5_STATION_A),
                        new ProductSupplier.Delivery(stations[1], SUPPLIER_5_STATION_B),
                        new ProductSupplier.Delivery(stations[2], SUPPLIER_5_STATION_C),
                        new ProductSupplier.Delivery(stations[3], SUPPLIER_5_STATION_D)
                ),
                new ProductSupplier(6, SUPPLIER_6_VOLUME, SUPPLIER_6_COST,
                        new ProductSupplier.Delivery(stations[0], SUPPLIER_6_STATION_A),
                        new ProductSupplier.Delivery(stations[1], SUPPLIER_6_STATION_B),
                        new ProductSupplier.Delivery(stations[2], SUPPLIER_6_STATION_C),
                        new ProductSupplier.Delivery(stations[3], SUPPLIER_6_STATION_D)
                )
        };
    }
}