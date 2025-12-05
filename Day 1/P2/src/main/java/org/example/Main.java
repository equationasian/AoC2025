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
                int rotation = Integer.parseInt(line.substring(1));

                numZeroes += rotation / 100;
                rotation %= 100;

                int numAfterRotation = rotateNum(direction, currentNum, rotation);
                boolean passedZeroLeft = currentNum != 0 && direction == 'L' && numAfterRotation > currentNum;
                boolean passedZeroRight = currentNum != 0 && direction == 'R' && numAfterRotation < currentNum;

                if (numAfterRotation == 0) {
                    ++numZeroes;
                }
                else if (passedZeroLeft || passedZeroRight) {
                    ++numZeroes;
                }

                currentNum = numAfterRotation;

                line = reader.readLine();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(numZeroes);
    }

    public static int rotateNum(char direction, int currentNum, int rotation) {
        int rotate = direction == 'L' ? currentNum - rotation : currentNum + rotation;
        int result;

        if (rotate < 0) {
            result = 100 - Math.abs(rotate);
        }
        else if (rotate > 99) {
            result = rotate - 100;
        }
        else {
            result = rotate;
        }

        return result;
    }
}