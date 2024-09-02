package com.example.springexample;

import java.util.HashMap;
import java.util.Map;

public class RomanToInt {
    public static void main(String[] args){
        testRomanToInt("I", 1);
        testRomanToInt("IV", 4);
        testRomanToInt("MCMXC", 1990);
        testRomanToInt("MMMDCCCLXXXVIII", 3888);
        testRomanToInt("I", 1);
        testRomanToInt("MMMCMXCIX", 3999);

    }
    public static void testRomanToInt(String roman, int expected) {

        int result =romanToInt(roman);
        if (result == expected) {
            System.out.println("Test passed: Roman numeral " + roman + " equals " + result);
        } else {
            System.out.println("Test failed: Roman numeral " + roman + " equals " + result + ". Expected: " + expected);
        }
    }
    public static int romanToInt(String s) {

        Map<Character,Integer> romanMap=new HashMap<>();
        romanMap.put('I',1);
        romanMap.put('V',5);
        romanMap.put('X',10);
        romanMap.put('L',50);
        romanMap.put('C',100);
        romanMap.put('D',500);
        romanMap.put('M',1000);

        int result=0;
        for(int i=0;i<s.length()-1;i++){
            char curChar=s.charAt(i);
            char nextChar=s.charAt(i+1);
            if(romanMap.get(curChar)<romanMap.get(nextChar)){
                result-=romanMap.get(curChar);
            }else{
                result+=romanMap.get(curChar);
            }
        }
        result+=romanMap.get(s.charAt(s.length()-1));
        return result;
    }

}
