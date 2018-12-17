package fr.kelkoo.interview;

public class Foo {

    public  String[] removeElementAtIndice(String[] array, int indice) {
        if (array == null || indice > array.length | indice <= 0) return null;

        String[] arrayToReturn = new String[array.length - 1];
        int j = 0;

        for (int i = 0; i < array.length; i++) {
            if (indice == i + 1) continue;
            arrayToReturn[j] = array[i];
            j++;
        }
        return arrayToReturn;
    }
}
