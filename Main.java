import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Main {

    public static void generateTestData(int numSets, int minSize, int maxSize) {
        Random random = new Random();
        for (int i = 0; i < numSets; i++) {
            int size = random.nextInt(maxSize - minSize + 1) + minSize;
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < size; j++) {
                char c = (char) (random.nextInt(26) + 'A'); // Генерируем случайные символы от 'A' до 'Z'
                sb.append(c);
            }
            String data = sb.toString();
            try {
                FileWriter writer = new FileWriter("test_data_" + i + ".txt");
                writer.write(data);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int numSets = 100;
        int minSize = 100;
        int maxSize = 10000;
        generateTestData(numSets, minSize, maxSize);
    }
}