package org.example;

import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        int currentNum = 50;
        int numZeroes = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String line = reader.readLine();
            while (line != null) {
                char direction = line.charAt(0);
                int rotation = Integer.parseInt(line.substring(1)) % 100;

                currentNum = direction == 'L' ? currentNum - rotation : currentNum + rotation;

                if (currentNum == 0 || Math.abs(currentNum) % 100 == 0) {
                    ++numZeroes;
                    currentNum = 0;
                }

                line = reader.readLine();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(numZeroes);
    }
}