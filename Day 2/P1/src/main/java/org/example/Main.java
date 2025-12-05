package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        long total = 0;
        List<String> ranges = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String line = reader.readLine();
            ranges = new ArrayList<>(Arrays.asList(line.split(",")));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        for (String range : ranges) {
            String[] splitID = range.split("-");
            String firstID = splitID[0];
            String lastID = splitID[1];

            while (Long.parseLong(firstID) <= Long.parseLong(lastID)) {
                if (firstID.length() % 2 != 0) {
                    String addDigit = "1" + "0".repeat(firstID.length() / 2);
                    firstID = addDigit.repeat(2);
                }
                else {
                    int midIndex = firstID.length() == 2 ? 1 : firstID.length() / 2;
                    if (firstID.substring(0, midIndex).equals(firstID.substring(midIndex))) {
                        total += Long.parseLong(firstID);
                    }

                    firstID = String.valueOf(Long.parseLong(firstID) + 1);
                }
            }
        }

        System.out.println(total);
    }
}