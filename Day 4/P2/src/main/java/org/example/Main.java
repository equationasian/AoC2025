package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<List<String>> grid = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String line = reader.readLine();
            while (line != null) {
                grid.add(Arrays.asList(line.split("")));
                line = reader.readLine();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        int totalRemoved = removeRolls(grid, 0, -1);

        System.out.println(totalRemoved);
    }

    public static int removeRolls(List<List<String>> grid, int total, int removed) {
        if (removed == 0) {
            return total;
        }

        Map<Integer, List<Integer>> replaceIndexes = new HashMap<>();
        int accessible = 0;
        for (int row = 0; row < grid.size(); ++row) {
            for (int column = 0; column < grid.get(row).size(); ++column) {
                int adjacentRolls = 0;

                if (grid.get(row).get(column).equals("@")) {
                    // up
                    if (row - 1 >= 0 && grid.get(row - 1).get(column).equals("@")) {
                        ++adjacentRolls;
                    }

                    // NE
                    if (row - 1 >= 0 && column + 1 < grid.get(row).size() && grid.get(row - 1).get(column + 1).equals("@")) {
                        ++adjacentRolls;
                    }

                    // right
                    if (column + 1 < grid.get(row).size() && grid.get(row).get(column + 1).equals("@")) {
                        ++adjacentRolls;
                    }

                    // SE
                    if (row + 1 < grid.size() && column + 1 < grid.get(row).size() && grid.get(row + 1).get(column + 1).equals("@")) {
                        ++adjacentRolls;
                    }

                    // down
                    if (row + 1 < grid.size() && grid.get(row + 1).get(column).equals("@")) {
                        ++adjacentRolls;
                    }

                    // SW
                    if (row + 1 < grid.size() && column - 1 >= 0 && grid.get(row + 1).get(column - 1).equals("@")) {
                        ++adjacentRolls;
                    }

                    // left
                    if (column - 1 >= 0 && grid.get(row).get(column - 1).equals("@")) {
                        ++adjacentRolls;
                    }

                    // NW
                    if (row - 1 >= 0 && column - 1 >= 0 && grid.get(row - 1).get(column - 1).equals("@")) {
                        ++adjacentRolls;
                    }

                    if (adjacentRolls < 4) {
                        ++accessible;

                        if (!replaceIndexes.containsKey(row)) {
                            replaceIndexes.put(row, new ArrayList<>(List.of(column)));
                        }
                        else {
                            List<Integer> columnIndexes = replaceIndexes.get(row);
                            columnIndexes.add(column);
                            replaceIndexes.put(row, columnIndexes);
                        }
                    }
                }
            }
        }

        for (Map.Entry<Integer, List<Integer>> coordinate : replaceIndexes.entrySet()) {
            for (int column : coordinate.getValue()) {
                grid.get(coordinate.getKey()).set(column, ".");
            }
        }

        return removeRolls(grid, total + accessible, accessible);
    }
}