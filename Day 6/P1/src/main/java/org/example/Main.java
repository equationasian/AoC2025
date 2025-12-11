package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<List<String>> worksheet = new ArrayList<>();
        long total = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String line = reader.readLine();
            while (line != null) {
                String[] split = line.trim().split("\s+");
                worksheet.add(new ArrayList<>(List.of(split)));
                line = reader.readLine();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        List<String> operations = worksheet.remove(worksheet.size() - 1);;

        for (int column = 0; column < worksheet.get(0).size(); ++column) {
            long columnTotal = operations.get(column).equals("+") ? 0 : 1;

            for (List<String> row : worksheet) {
                if (operations.get(column).equals("+")) {
                    columnTotal += Long.parseLong(row.get(column));
                }
                else {
                    columnTotal *= Long.parseLong(row.get(column));
                }
            }

            total += columnTotal;
        }

        System.out.println(total);
    }
}