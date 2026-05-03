package com.leetcode.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopKFrequentElement {

    public int[] topKFrequent(int[] nums, int k) {
        int[] output = new int[k];
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        List<Map.Entry<Integer, Integer>> topKEntries = frequencyMap.entrySet().stream().sorted((a, b) -> b.getValue() - a.getValue()).limit(k).toList();
        for (Map.Entry<Integer, Integer> entry : topKEntries) {
            output[output.length - k] = entry.getKey();
            k--;
        }
        return output;
    }

    public static void main(String[] args) {
        TopKFrequentElement solution = new TopKFrequentElement();
        int[] nums = new int[]{5, 5, 5, 2, 2, 3};
        int k = 2;
        System.out.println(Arrays.toString(solution.topKFrequent(nums, k)));
    }

}
