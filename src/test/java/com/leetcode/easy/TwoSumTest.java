package com.leetcode.easy;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class TwoSumTest {
    private final TwoSum solution = new TwoSum();

    @Test
    public void testTwoSum_BasicCase() {
        int[] result = solution.twoSum(new int[]{2, 7, 11, 15}, 9);
        assertThat(result).containsExactly(0, 1);
    }

    @Test
    public void testTwoSum_MultipleValidPairs() {
        int[] result = solution.twoSum(new int[]{3, 2, 4}, 6);
        assertThat(result).containsExactly(1, 2);
    }

    @Test
    public void testTwoSum_NegativeNumbers() {
        int[] result = solution.twoSum(new int[]{-1, -2, -3, 5, 10}, 7);
        assertThat(result).containsExactly(3, 4);
    }

    @Test
    public void testTwoSum_DuplicateNumbers() {
        int[] result = solution.twoSum(new int[]{1, 1, 1, 1}, 2);
        assertThat(result).contains(0, 1);
    }
}
