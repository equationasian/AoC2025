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
                String[] split = line.split("");
                diagram.add(new ArrayList<>(Arrays.asList(split)));
                line = reader.readLine();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        Set<Integer> beamColumns = new HashSet<>();
        beamColumns.add(findStart(diagram.get(0)));
        int splits = 0;

        for (int row = 1; row < diagram.size(); ++row) {
            Set<Integer> updateColumns = new HashSet<>(beamColumns);

            for (int column : beamColumns) {
                if (diagram.get(row).get(column).equals("^")) {
                    if (column == 0) {
                        updateColumns.add(column + 1);
                    }
                    else if (column == diagram.get(row).size() - 1) {
                        updateColumns.add(column - 1);
                    }
                    else {
                        updateColumns.add(column + 1);
                        updateColumns.add(column - 1);
                    }

                    updateColumns.remove(column);
                    ++splits;
                }
            }

            beamColumns = updateColumns;
        }

        System.out.println(splits);
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