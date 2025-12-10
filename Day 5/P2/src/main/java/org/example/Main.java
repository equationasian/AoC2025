package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        Map<Long, Long> ranges = new TreeMap<>();
        long fresh = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
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
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        Map<Long, Long> updatedRanges = mergeRanges(ranges, -1);
        for (Map.Entry<Long, Long> range : updatedRanges.entrySet()) {
            long ids = range.getValue() - range.getKey() + 1;
            fresh += ids;
        }

        System.out.println(fresh);
    }

    public static Map<Long, Long> mergeRanges(Map<Long, Long> ranges, int changes) {
        if (changes == 0 || ranges.size() == 1) {
            return ranges;
        }

        Map<Long, Long> updatedRanges = new TreeMap<>();
        int merges = 0;

        Iterator<Map.Entry<Long, Long>> it = ranges.entrySet().iterator();
        Map.Entry<Long, Long> previousRange = it.next();
        while (it.hasNext()) {
            updatedRanges.put(previousRange.getKey(), previousRange.getValue());

            Map.Entry<Long, Long> currentRange = it.next();
            if (currentRange.getKey() <= previousRange.getValue()) {
                if (currentRange.getValue() > previousRange.getValue()) {
                    updatedRanges.put(previousRange.getKey(), currentRange.getValue());
                    previousRange = Map.entry(previousRange.getKey(), currentRange.getValue());
                }

                it.remove();
                ++merges;
            }
            else {
                updatedRanges.put(currentRange.getKey(), currentRange.getValue());
                previousRange = currentRange;
            }
        }

        return mergeRanges(updatedRanges, merges);
    }
}