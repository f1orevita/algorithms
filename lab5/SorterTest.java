import java.util.*;

public class SorterTest {
    public static boolean DEBUG = true;

    public static void main(String[] args) {
        int[] manualArray = {5, 2, 8, 1, 3};
        testIntSorting("Manual array", manualArray);

        testRandomIntArray(20);
        testRandomIntArray(100);
        testRandomIntArray(1000);

        testPartiallySortedArray(30, 3);

        Integer[] compArray = {9, 4, 7, 2, 5};
        testComparableSorting("Comparable manual array", compArray);
    }

    private static void testIntSorting(String label, int[] original) {
        int[] arr1 = Arrays.copyOf(original, original.length);
        int[] arr2 = Arrays.copyOf(original, original.length);

        System.out.println("\n--- " + label + " ---");
        System.out.println("Before: " + Arrays.toString(original));

        Sorter.bubbleSort(arr1);
        System.out.println("Bubble sorted: " + Arrays.toString(arr1));
        if (DEBUG) printStats(Sorter.comparisons, Sorter.swaps);

        Sorter.selectionSort(arr2);
        System.out.println("Selection sorted: " + Arrays.toString(arr2));
        if (DEBUG) printStats(Sorter.comparisons, Sorter.swaps);
    }

    private static void testComparableSorting(String label, Integer[] original) {
        Integer[] arr1 = Arrays.copyOf(original, original.length);
        Integer[] arr2 = Arrays.copyOf(original, original.length);

        System.out.println("\n--- " + label + " ---");
        System.out.println("Before: " + Arrays.toString(original));

        SorterComparable.bubbleSort(arr1);
        System.out.println("Bubble sorted: " + Arrays.toString(arr1));
        if (DEBUG) printStats(SorterComparable.comparisons, SorterComparable.swaps);

        SorterComparable.selectionSort(arr2);
        System.out.println("Selection sorted: " + Arrays.toString(arr2));
        if (DEBUG) printStats(SorterComparable.comparisons, SorterComparable.swaps);
    }

    private static void testRandomIntArray(int length) {
        Random rand = new Random();
        int[] arr = rand.ints(length, 0, 1000).toArray();
        testIntSorting("Random array (" + length + ")", arr);
    }

    private static void testPartiallySortedArray(int length, int parts) {
        int[] arr = new int[length];
        int chunk = length / parts;
        Random rand = new Random();
        for (int i = 0; i < parts; i++) {
            int start = i * chunk;
            int end = (i + 1) * chunk;
            for (int j = start; j < end; j++) {
                arr[j] = rand.nextInt(length * 10) + i * length * 10;
            }
            Arrays.sort(arr, start, end);
        }
        testIntSorting("Partially sorted array (" + length + ", " + parts + " parts)", arr);
    }

    private static void printStats(int comparisons, int swaps) {
        System.out.println("Comparisons: " + comparisons + ", Swaps: " + swaps);
    }
}

