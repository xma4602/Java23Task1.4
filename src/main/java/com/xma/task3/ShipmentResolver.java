package com.xma.task3;

/**
 * представляет алгоритм вычисления необходимых закупок
 */
public interface ShipmentResolver {
    /**
     * вычисляет закупки
     * @param suppliers доступные поставщики
     * @param stations АЗС для поставок
     * @return вычисленные закупки
     */
    Shipment[] resolve(ProductSupplier[] suppliers, Station[] stations);
}
