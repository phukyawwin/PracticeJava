package com.example.springexample;

import java.util.Arrays;

public class TwoSums {
    public static void main(String[] args){
        testTwoSum(new int[] {2, 7, 11, 15}, 9, new int[] {0, 1});
        testTwoSum(new int[] {3, 2, 4}, 6, new int[] {1, 2});
        testTwoSum(new int[] {3, 3}, 6, new int[] {0, 1});
        testTwoSum(new int[] {1, 2, 3, 4, 5}, 7, new int[] {1, 4});
        testTwoSum(new int[] {1, 1}, 2, new int[] {0, 1});
    }

    public static void testTwoSum(int[] nums, int target, int[] expected) {
        int[] result = twoSum(nums, target);
        if (Arrays.equals(result, expected)) {
            System.out.println("Test passed: " + Arrays.toString(nums) + ", target=" + target);
        } else {
            System.out.println("Test failed: " + Arrays.toString(nums) + ", target=" + target + ". Expected: " + Arrays.toString(expected) + ", but got: " + Arrays.toString(result));
        }
    }

    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] { i, j };
                }
            }
        }
        throw new IllegalArgumentException("No solution found");
    }
}
