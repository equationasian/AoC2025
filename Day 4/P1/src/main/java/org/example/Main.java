package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<List<String>> grid = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String line = reader.readLine();
            while (line != null) {
                grid.add(List.of(line.split("")));
                line = reader.readLine();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

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
                    }
                }
            }
        }

        System.out.println(accessible);
    }
}