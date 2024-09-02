package com.example.springexample;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LongestCommonPrefix {
    public static void main(String[] args){
        testLongestCommonPrefix(new String[] {"flower","flow","flight"}, "fl");
        testLongestCommonPrefix(new String[] {"dog","racecar","car"}, "");
        testLongestCommonPrefix(new String[] {"a"}, "a");
        testLongestCommonPrefix(new String[] {"a", "a", "a"}, "a");
        testLongestCommonPrefix(new String[] {"", "", ""}, "");

    }
    public static void testLongestCommonPrefix(String[] strs, String expected) {
        String result = longestCommonPrefix(strs);
        if (result.equals(expected)) {
            System.out.println("Test passed: Input " + Arrays.toString(strs) + ", output: " + result);
        } else {
            System.out.println("Test failed: Input " + Arrays.toString(strs) + ", output: " + result + ". Expected: " + expected);
        }
    }
    public static String longestCommonPrefix(String[] strs) {

        if(strs!=null && strs.length!=0){
            String prefix=strs[0];
            for(int i=1;i<strs.length;i++){
                while(strs[i].indexOf(prefix)!=0){
                    prefix=prefix.substring(0,prefix.length()-1);
                    if(prefix.isEmpty()){
                        return "";
                    }

                }
            }
            return prefix;
        }else{
            return "";
        }
    }

}
