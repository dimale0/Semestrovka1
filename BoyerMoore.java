import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BoyerMoore {

    private static int iterations; // Счетчик итераций

    private static int[] createBadCharacterTable(String pattern) {
        int[] badCharacterTable = new int[256];
        for (int i = 0; i < badCharacterTable.length; i++) {
            badCharacterTable[i] = -1;
        }
        for (int i = 0; i < pattern.length(); i++) {
            badCharacterTable[(int) pattern.charAt(i)] = i;
        }
        return badCharacterTable;
    }

    public static int search(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        int[] badCharacterTable = createBadCharacterTable(pattern);

        int shift = 0;
        iterations = 0; // Сбросим счетчик итераций при каждом поиске
        while (shift <= n - m) {
            int j = m - 1;
            while (j >= 0 && pattern.charAt(j) == text.charAt(shift + j)) {
                j--;
                iterations++; // Увеличиваем счетчик итераций
            }
            if (j < 0) {
                return shift;
            } else {
                shift += Math.max(1, j - badCharacterTable[text.charAt(shift + j)]);
                iterations++;
            }
        }
        return -1;
    }

    public static int getIterationsCount() {
        return iterations;
    }

    public static void main(String[] args) {
        try {
            File file = new File("test_data_0.txt");
            Scanner scanner = new Scanner(file);
            String text = scanner.nextLine();
            String pattern = "REL";

            long startTime = System.nanoTime(); // Засекаем начало времени
            int index = search(text, pattern);
            long endTime = System.nanoTime(); // Засекаем конец времени
            long duration = (endTime - startTime); // Вычисляем длительность выполнения

            int iterations = getIterationsCount(); // Получаем количество итераций

            if (index != -1) {
                System.out.println("Pattern found at index: " + index);
                System.out.println("Time taken: " + duration + " nanoseconds");
                System.out.println("Iterations: " + iterations);
            } else {
                System.out.println("Pattern not found in the text.");
                System.out.println("Time taken: " + duration + " nanoseconds");
                System.out.println("Iterations: " + iterations);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
