package com.sample.algorithms.sort;

public final class QuickSort {

    private QuickSort() {
        throw new AssertionError("Instantiating utility class");
    }

    public static void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private static void quickSort(int[] array, int left, int right) {
        int indexL = left, indexR = right;
        int marker = array[(left + right) / 2];

        while (indexL <= indexR) {
            while (array[indexL] < marker) indexL++;
            while (array[indexR] > marker) indexR--;

            if (indexL <= indexR) {
                swap(array, indexL, indexR);
                indexL++;
                indexR--;
            }
        }

        if (left < indexL - 1) quickSort(array, left, indexL - 1);
        if (indexL < right) quickSort(array, indexL, right);
    }

    private static void swap(int[] array, int indexL, int indexR) {
        int temp = array[indexL];
        array[indexL] = array[indexR];
        array[indexR] = temp;
    }
}
