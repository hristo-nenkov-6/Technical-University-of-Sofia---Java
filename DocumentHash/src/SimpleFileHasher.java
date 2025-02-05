import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class SimpleFileHasher {

    // Прост хеш алгоритъм за генериране на хеш на съдържанието на файл
    public static int generateFileHash(File file) throws IOException {
        int hashValue = 0;

        try (FileInputStream fis = new FileInputStream(file)) {
            int byteRead;
            while ((byteRead = fis.read()) != -1) {
                // Сумиране на байтовете и актуализиране на хеша
                hashValue += byteRead;
            }
        }

        // Използваме остатъка от делението по 256, за да получим хеш стойност в диапазона 0-255
        return hashValue % 256;
    }

    public static void main(String[] args) {
        // Пример за файл за хеширане
        File file = new File("src/text2");

        try {
            int fileHash = generateFileHash(file);
            System.out.println("Прост хеш на файла: " + fileHash);
        } catch (IOException e) {
            System.out.println("Грешка при генериране на хеш: " + e.getMessage());
        }
    }
}
