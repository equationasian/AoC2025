package org.example;

import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        long total = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String line = reader.readLine();
            while (line != null) {
                char[] bank = line.toCharArray();
                StringBuilder sb = new StringBuilder();
                int previousIndex = -1;

                for (int i = 11; i >= 0; --i) {
                    int index = findMaxIndex(bank, previousIndex, bank.length - i);
                    sb.append(bank[index]);
                    previousIndex = index;
                }

                total += Long.parseLong(sb.toString());
                line = reader.readLine();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(total);
    }

    public static int findMaxIndex(char[] bank, int previousIndex, int bankLength) {
        char max = bank[previousIndex + 1];
        int index = previousIndex + 1;

        for (int i = index; i < bankLength; ++i) {
            if (bank[i] > max) {
                max = bank[i];
                index = i;
            }
        }

        return index;
    }
}