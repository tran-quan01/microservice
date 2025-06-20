package quan.example.demo.data;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class CsvGenerator {
    private static final String FILE_DIRECTORY = "E:/30_Learn/Spring3/spring-batch-h2-example/src/main/resources/";
    private static final String FILE_NAME = FILE_DIRECTORY + "users.csv";
    private static final int NUM_RECORDS = 1000;
    private static final String[] FIRST_NAMES = {"Nguyen", "Tran", "Le", "Pham", "Hoang", "Huynh", "Phan", "Vu", "Dang", "Bui"};
    private static final String[] LAST_NAMES = {"Van A", "Thi B", "Minh C", "Thu D", "Quoc E", "Mai F", "Tuan G", "Thanh H", "Ngoc I", "Duy J"};

    public static void main(String[] args) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            // Write header
            writer.println("firstName,lastName");

            Random random = new Random();

            for (int i = 1; i <= NUM_RECORDS; i++) {
                // Generate random first name and last name
                String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)] + i; // Thêm số để đảm bảo tính duy nhất
                String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];

                // Write record to CSV
                writer.println(firstName + "," + lastName);
            }

            System.out.println("Successfully generated " + NUM_RECORDS + " records to " + FILE_NAME);

        } catch (IOException e) {
            System.err.println("An error occurred while writing to the CSV file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
