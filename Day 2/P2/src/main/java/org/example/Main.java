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
                if (firstID.length() == 1) {
                    firstID = String.valueOf(Long.parseLong(firstID) + 1);
                    continue;
                }

                List<Integer> factors = findFactors(firstID.length());

                for (int factor : factors) {
                    String group = firstID.substring(0, factor);
                    boolean isRepeating = true;

                    for (int i = factor; i < firstID.length(); i += factor) {
                        if (!firstID.startsWith(group, i)) {
                            isRepeating = false;
                            break;
                        }
                    }

                    if (isRepeating) {
                        total += Long.parseLong(firstID);
                        break;
                    }
                }

                firstID = String.valueOf(Long.parseLong(firstID) + 1);
            }
        }

        System.out.println(total);
    }

    public static List<Integer> findFactors(int num) {
        List<Integer> factors = new ArrayList<>();
        factors.add(1);
        int maxFactor = num / 2;

        for (int i = 2; i <= maxFactor; ++i) {
            if (num % i == 0) {
                factors.add(i);
            }
        }

        return factors;
    }
}