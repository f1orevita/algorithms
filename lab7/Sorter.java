public class Sorter {
    public static boolean DEBUG = false;

    public interface SortStrategy {
        void sort(int[] array);
    }

    public static class BubbleSort implements SortStrategy {
        public void sort(int[] array) {
            int comparisons = 0, swaps = 0;
            for (int i = 0; i < array.length - 1; i++) {
                for (int j = 0; j < array.length - i - 1; j++) {
                    comparisons++;
                    if (array[j] > array[j + 1]) {
                        int temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                        swaps++;
                    }
                }
            }
            if (DEBUG) {
                System.out.println("Bubble Sort - Comparisons: " + comparisons + ", Swaps: " + swaps);
            }
        }
    }

    public static class SelectionSort implements SortStrategy {
        public void sort(int[] array) {
            int comparisons = 0, swaps = 0;
            for (int i = 0; i < array.length - 1; i++) {
                int minIndex = i;
                for (int j = i + 1; j < array.length; j++) {
                    comparisons++;
                    if (array[j] < array[minIndex]) {
                        minIndex = j;
                    }
                }
                if (minIndex != i) {
                    int temp = array[minIndex];
                    array[minIndex] = array[i];
                    array[i] = temp;
                    swaps++;
                }
            }
            if (DEBUG) {
                System.out.println("Selection Sort - Comparisons: " + comparisons + ", Swaps: " + swaps);
            }
        }
    }

    public static class MergeSort implements SortStrategy {
        public void sort(int[] array) {
            mergeSort(array, 0, array.length - 1);
        }

        private void mergeSort(int[] arr, int l, int r) {
            if (l < r) {
                int m = (l + r) / 2;
                mergeSort(arr, l, m);
                mergeSort(arr, m + 1, r);
                merge(arr, l, m, r);
            }
        }

        private void merge(int[] arr, int l, int m, int r) {
            int n1 = m - l + 1;
            int n2 = r - m;
            int[] L = new int[n1];
            int[] R = new int[n2];
            System.arraycopy(arr, l, L, 0, n1);
            System.arraycopy(arr, m + 1, R, 0, n2);
            int i = 0, j = 0, k = l;
            while (i < n1 && j < n2) {
                arr[k++] = (L[i] <= R[j]) ? L[i++] : R[j++];
            }
            while (i < n1) arr[k++] = L[i++];
            while (j < n2) arr[k++] = R[j++];
        }
    }

    public static class QuickSort implements SortStrategy {
        public void sort(int[] array) {
            quickSort(array, 0, array.length - 1);
        }

        private void quickSort(int[] arr, int low, int high) {
            if (low < high) {
                int pi = partition(arr, low, high);
                quickSort(arr, low, pi - 1);
                quickSort(arr, pi + 1, high);
            }
        }

        private int partition(int[] arr, int low, int high) {
            int pivot = arr[high];
            int i = (low - 1);
            for (int j = low; j < high; j++) {
                if (arr[j] <= pivot) {
                    i++;
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
            int temp = arr[i + 1];
            arr[i + 1] = arr[high];
            arr[high] = temp;
            return i + 1;
        }
    }
}