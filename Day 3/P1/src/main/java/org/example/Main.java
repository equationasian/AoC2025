package org.example;

import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        int total = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String line = reader.readLine();
            while (line != null) {
                char[] bank = line.toCharArray();
                char first = bank[0];
                int firstIndex = 0;

                for (int i = 1; i < bank.length - 1; ++i) {
                    if (bank[i] > first) {
                        first = bank[i];
                        firstIndex = i;
                    }
                }

                char second = bank[firstIndex + 1];
                for (int i = firstIndex + 1; i < bank.length; ++i) {
                    if (bank[i] > second) {
                        second = bank[i];
                    }
                }

                String joltage = String.valueOf(first) + String.valueOf(second);
                total += Integer.parseInt(joltage);

                line = reader.readLine();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(total);
    }
}