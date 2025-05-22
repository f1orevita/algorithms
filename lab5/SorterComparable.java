public class SorterComparable {
    public static int comparisons = 0;
    public static int swaps = 0;

    public static void resetStats() {
        comparisons = 0;
        swaps = 0;
    }

    public static <T extends Comparable<T>> void bubbleSort(T[] arr) {
        resetStats();
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                comparisons++;
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    swaps++;
                    T temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static <T extends Comparable<T>> void selectionSort(T[] arr) {
        resetStats();
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                comparisons++;
                if (arr[j].compareTo(arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                swaps++;
                T temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }
}
