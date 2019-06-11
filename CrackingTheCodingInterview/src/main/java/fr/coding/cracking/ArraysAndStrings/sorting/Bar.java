package fr.coding.cracking.ArraysAndStrings.sorting;

public class Bar {

    public int[] insertSort(int[] array) {
        for (int i = 1; i < array.length; ++i) {
            int key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }

        int[] result = array.clone();
        return result;
    }

    public int[] mergeSort(int[] array) {
        int[] helper = new int[array.length];
        return mergeSort(array, helper, 0, array.length - 1);
    }

    private int[] mergeSort(int[] array, int[] helper, int low, int high) {
        if (low < high) {
            int middle = (low + high) / 2;
            mergeSort(array, helper, low, middle);
            mergeSort(array, helper, middle + 1, high);
            merge(array, helper, low, middle, high);
        }
        return array.clone();
    }

    private void merge(int[] array, int[] helper, int low, int middle, int high) {
        /* Copy both halves into a helper array */
        for (int i = low; i <= high; i++) {
            helper[i] = array[i];
        }

        int helperLeft = low;
        int helperRight = middle + 1;
        int current = low;

        /* Iterate through helper array. Compare the left and right half, copying back
            the smaller element from the two halves into the orginal array
         */
        while (helperLeft <= middle && helperRight <= high) {
            if (helper[helperLeft] <= helper[helperRight]) {
                array[current] = helper[helperLeft];
                helperLeft++;
            } else { // If right element is smaller than left element
                array[current] = helper[helperRight];
                helperRight++;
            }
            current++;
        }

        /* Copy the rest of the left side of the array into the target array */
        int remaining = middle - helperLeft;
        for (int i = 0; i <= remaining; i++) {
            array[current + i] = helper[helperLeft + i];
        }
    }
}