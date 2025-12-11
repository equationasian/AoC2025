package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> worksheet = new ArrayList<>();
        long total = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String line = reader.readLine();
            while (line != null) {
                worksheet.add(line);
                line = reader.readLine();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        String operationsStr = worksheet.remove(worksheet.size() - 1);
        Deque<String> operations = new ArrayDeque<>(Arrays.asList(operationsStr.trim().split("\s+")));
        List<Long> columnTotals = new ArrayList<>();

        for (int column = worksheet.get(0).length() - 1; column >= 0; --column) {
            StringBuilder sb = new StringBuilder();

            for (String row : worksheet) {
                if (row.charAt(column) != ' ') {
                    sb.append(row.charAt(column));

                }
            }

            if (sb.isEmpty() || column == 0) {
                if (column == 0) {
                    String num = sb.toString();
                    columnTotals.add(Long.parseLong(num));
                }

                String operation = operations.removeLast();
                long columnTotal = operation.equals("+") ? 0 : 1;

                for (long num : columnTotals) {
                    if (operation.equals("+")) {
                        columnTotal += num;
                    }
                    else {
                        columnTotal *= num;
                    }
                }

                total += columnTotal;
                columnTotals.clear();
            }
            else {
                String num = sb.toString();
                columnTotals.add(Long.parseLong(num));
            }
        }

        System.out.println(total);
    }
}