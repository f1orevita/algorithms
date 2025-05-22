import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class SorterTest {
    static final int[] SIZES = {1024, 2048, 4096, 8192, 16384, 32768};
    static final String[] TYPES = {"random", "sorted", "reversed", "same"};

    public static void main(String[] args) throws IOException {
        Sorter.DEBUG = false;
        FileWriter writer = new FileWriter("results.txt");

        Sorter.SortStrategy[] algorithms = {
            new Sorter.BubbleSort(),
            new Sorter.SelectionSort(),
            new Sorter.MergeSort(),
            new Sorter.QuickSort()
        };
        String[] names = {"BubbleSort", "SelectionSort", "MergeSort", "QuickSort"};

        for (int i = 0; i < algorithms.length; i++) {
            for (String type : TYPES) {
                for (int size : SIZES) {
                    int[] arr = generateArray(size, type);
                    int[] copy = Arrays.copyOf(arr, arr.length);

                    long start = System.nanoTime();
                    algorithms[i].sort(copy);
                    long end = System.nanoTime();

                    writer.write(names[i] + " | " + type + " | " + size + " | " + (end - start) / 1_000_000.0 + " ms\n");
                }
            }
        }

        writer.close();
    }

    static int[] generateArray(int size, String type) {
        int[] array = new int[size];
        Random rand = new Random();
        switch (type) {
            case "random":
                for (int i = 0; i < size; i++) array[i] = rand.nextInt(size * 10);
                break;
            case "sorted":
                for (int i = 0; i < size; i++) array[i] = i;
                break;
            case "reversed":
                for (int i = 0; i < size; i++) array[i] = size - i;
                break;
            case "same":
                int val = rand.nextInt(1000);
                Arrays.fill(array, val);
                break;
        }
        return array;
    }
}