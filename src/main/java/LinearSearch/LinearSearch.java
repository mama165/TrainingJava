package LinearSearch;

public class LinearSearch {

    public static void  main(String [] args){
        int [] array = {1, 2, 5, 9, 8};

        System.out.println(search(array, 8));
    }

    public static int search(int[] array, int value) {

        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
