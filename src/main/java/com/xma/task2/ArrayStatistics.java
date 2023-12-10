package com.xma.task2;

import java.util.*;

public class ArrayStatistics {
    private final int[] items;

    public ArrayStatistics(int... items) {
        this.items = items;
    }

    public int[] mode(int count) {
        if (items.length == 0) {
            throw new IllegalStateException("Массив пустой");
        }
        Map<Integer, Integer> nums = new TreeMap<>();
        for (var item : items) {
            nums.merge(item, 1, (x, y) -> x + 1);
        }
        Map.Entry<Integer, Integer>[] arr = nums.entrySet().toArray(new Map.Entry[0]);
        Arrays.sort(arr, (x, y) -> y.getValue() - x.getValue());
        int[] result = new int[count];
        for (int i = 0; i < result.length; i++) {
            result[i] = arr[i].getKey();
        }
        return result;
    }

    public int median() {
        if (items.length < 2) {
            throw new IllegalStateException("В массиве %d элементов и не может быть найдена медиана".formatted(items.length));
        }
        Arrays.sort(items);
        if (items.length == 2) {
            return (items[0] + items[1]) / 2;
        }
        if (items.length == 3) {
            return items[1];
        }
        int index = items.length / 2;
        return (items[index] + items[index - 1]) / 2;
    }

    public double average() {
        if (items.length == 0) {
            throw new IllegalStateException("Массив пустой");
        }
        double sum = 0;
        for (var item : items) {
            sum += item;
        }
        return sum / items.length;
    }

    public double variance() {
        if (items.length == 0) {
            throw new IllegalStateException("Массив пустой");
        }
        double aver = average();
        double variance = 0;
        for (var item : items) {
            variance += (aver - item) * (aver - item);
        }
        return variance;
    }

    public double geometricMean() {
        if (items.length == 0) {
            throw new IllegalStateException("Массив пустой");
        }
        double sum = 1;
        for (var item : items) {
            sum *= item;
        }
        return Math.pow(sum, 1.0 / items.length);
    }

    public int[] shuffle() {
        if (items.length == 0) {
            return new int[0];
        }
        if (items.length == 1) {
            return new int[]{items[0]};
        }

        int[] result = new int[items.length];
        List<Integer> indexes = new ArrayList<>(items.length);
        for (int i = 0; i < items.length; i++) {
            indexes.add(i);
        }
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < result.length; i++) {
            int num = random.nextInt(indexes.size());
            int index = indexes.get(num);
            indexes.remove(num);
            result[i] = items[index];

        }

        return result;
    }

    public int[] sample(int size) {
        Random random = new Random(System.currentTimeMillis());
        int[] result = new int[size];
        for (int i = 0; i < result.length; i++) {
            result[i] = items[random.nextInt(items.length)];
        }
        return result;
    }
}
