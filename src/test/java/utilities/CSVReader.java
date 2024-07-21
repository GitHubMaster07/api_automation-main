package utilities;

import java.io.*;
import java.util.*;

public class CSVReader {
    public List<String[]> readCSV(String filePath) {
        List<String[]> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    public static void main(String[] args) {
        CSVReader csvReader = new CSVReader();
        List<String[]> data = csvReader.readCSV("src/test/resources/usernames.csv");

        for (String[] row : data) {
            for (String field : row) {
                System.out.print(field + " ");
            }
            System.out.println();
        }
    }
}
