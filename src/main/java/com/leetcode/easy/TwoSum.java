package com.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode Problem 1: Two Sum
 * https://leetcode.com/problems/two-sum/
 *
 * Given an array of integers nums and an integer target, return the indices of the two numbers
 * that add up to target. You may assume that each input has exactly one solution, and you may
 * not use the same element twice.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }
}
