package com.example.springexample;

import java.util.Arrays;
import java.util.Stack;

public class ValidParentheses {
    public static void main(String[] args){
        testValidParentheses("()", true);
        testValidParentheses("()[]{}", true);
        testValidParentheses("(]", false);
        testValidParentheses("([)]", false);
        testValidParentheses("{[]}", true);

    }

    public static void testValidParentheses(String s, boolean expected) {
        boolean result = isValid(s);
        if (result == expected) {
            System.out.println("Test passed: Input \"" + s + "\", output: " + result);
        } else {
            System.out.println("Test failed: Input \"" + s + "\", output: " + result + ". Expected: " + expected);
        }
    }
    public static boolean isValid(String s) {
        Stack<Character> stack=new Stack<>();
        for(char c:s.toCharArray()){
            if(c=='('){
                stack.push(')');
            }else if(c=='{'){
                stack.push('}');
            }else if(c=='['){
                stack.push(']');
            }else if(stack.isEmpty()|| stack.pop()!=c){
                return false;
            }
        }
        return stack.isEmpty();
    }

}
