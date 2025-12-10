package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<Long, Long> ranges = new HashMap<>();
        int fresh = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            // ranges
            String line = reader.readLine();
            while (!line.isEmpty()) {
                String[] range = line.split("-");

                if (ranges.containsKey(Long.parseLong(range[0]))) {
                    if (ranges.get(Long.parseLong(range[0])) < Long.parseLong(range[1])) {
                        ranges.put(Long.parseLong(range[0]), Long.parseLong(range[1]));
                    }
                }
                else {
                    ranges.put(Long.parseLong(range[0]), Long.parseLong(range[1]));
                }

                line = reader.readLine();
            }

            // IDs
            line = reader.readLine();
            while (line != null) {
                long id = Long.parseLong(line);
                for (Map.Entry<Long, Long> range : ranges.entrySet()) {
                    if (id >= range.getKey() && id <= range.getValue()) {
                        ++fresh;
                        break;
                    }
                }

                line = reader.readLine();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(fresh);
    }
}