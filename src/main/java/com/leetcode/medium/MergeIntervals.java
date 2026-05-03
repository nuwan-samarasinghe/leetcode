package com.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> mergedIntervals = new ArrayList<>();

        for (int i = 1; i < intervals.length; i++) {
            int[] range1 = intervals[i - 1];
            int[] range2 = intervals[i];

            if (range1[1] >= range2[0]) {
                if (range1[1] <= range2[1]) {
                    intervals[i][0] = range1[0];
                    intervals[i][1] = range2[1];
                    intervals[i - 1][0] = -1;
                    intervals[i - 1][1] = -1;
                } else {
                    intervals[i][0] = range1[0];
                    intervals[i][1] = range1[1];
                    intervals[i - 1][0] = -1;
                    intervals[i - 1][1] = -1;
                }
            }
        }

        for (int[] interval : intervals) {
            if (interval[0] != -1) {
                mergedIntervals.add(interval);
            }
        }

        return mergedIntervals.toArray(new int[mergedIntervals.size()][]);
    }

    public static void main(String[] args) {
        MergeIntervals solution = new MergeIntervals();
        int[][] intervals = new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}, {16, 20}};
        int[][] output = solution.merge(intervals);
        for (int[] interval : output) {
            System.out.println("[" + interval[0] + ", " + interval[1] + "]");
        }

    }

}
