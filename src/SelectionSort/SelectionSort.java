package SelectionSort;

public class SelectionSort {

    public void sort(int[] array) {
        int n = array.length;

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (array[j] < array[min_idx])
                    min_idx = j;

            // Swap the found minimum element with the first
            // element
            int temp = array[min_idx];
            array[min_idx] = array[i];
            array[i] = temp;
        }
    }

    public void swap(int[] array, int a, int b) {
        int c;
        c = array[a];
        array[a] = array[b];
        array[b] = c;
    }

    public int min(int[] array) {
        int min = 0;

        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] <= array[i + 1]) {
                min = array[i];
            }
        }
        return min;
    }
}
