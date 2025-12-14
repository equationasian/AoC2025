package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<List<String>> diagram = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String line = reader.readLine();
            while (line != null) {
                String replaceLine = line.replaceAll("\\.", "0");
                String[] split = replaceLine.split("");
                diagram.add(new ArrayList<>(Arrays.asList(split)));
                line = reader.readLine();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        Set<Integer> beamColumns = new HashSet<>();
        beamColumns.add(findStart(diagram.get(0)));

        List<String> previousRowSums = new ArrayList<>(diagram.get(0));
        previousRowSums.set(findStart(diagram.get(0)), "1");

        for (int row = 1; row < diagram.size(); ++row) {
            Set<Integer> updateColumns = new HashSet<>(beamColumns);
            List<String> currentRowSums = new ArrayList<>(diagram.get(row));

            for (int column : beamColumns) {
                if (diagram.get(row).get(column).equals("^")) {
                    if (column == 0) {
                        updateColumns.add(column + 1);

                        long sum = Long.parseLong(previousRowSums.get(column)) + Long.parseLong(currentRowSums.get(column + 1));
                        currentRowSums.set(column + 1, String.valueOf(sum));
                    }
                    else if (column == diagram.get(row).size() - 1) {
                        updateColumns.add(column - 1);

                        long sum = Long.parseLong(previousRowSums.get(column)) + Long.parseLong(currentRowSums.get(column - 1));
                        currentRowSums.set(column - 1, String.valueOf(sum));
                    }
                    else {
                        updateColumns.add(column + 1);
                        updateColumns.add(column - 1);

                        long sumRight = Long.parseLong(previousRowSums.get(column)) + Long.parseLong(currentRowSums.get(column + 1));
                        long sumLeft = Long.parseLong(previousRowSums.get(column)) + Long.parseLong(currentRowSums.get(column - 1));
                        currentRowSums.set(column + 1, String.valueOf(sumRight));
                        currentRowSums.set(column - 1, String.valueOf(sumLeft));
                    }

                    updateColumns.remove(column);
                }
                else {
                    long sum = Long.parseLong(previousRowSums.get(column)) + Long.parseLong(currentRowSums.get(column));
                    currentRowSums.set(column, String.valueOf(sum));
                }
            }

            previousRowSums = currentRowSums;
            beamColumns = updateColumns;
        }

        long timelines = previousRowSums.stream()
                .filter(e -> !e.equals("^"))
                .mapToLong(Long::parseLong)
                .sum();

        System.out.println(timelines);
    }

    public static int findStart(List<String> row) {
        int startingColumn = 0;

        for (int i = 0; i < row.size(); ++i) {
            if (row.get(i).equals("S")) {
                startingColumn = i;
                break;
            }
        }

        return startingColumn;
    }
}