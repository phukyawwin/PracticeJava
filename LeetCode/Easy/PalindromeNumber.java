package com.example.springexample;

public class PalindromeNumber {
    public static void main(String[] args){
        int x1 = 121;
        boolean result1 = isPalindrome(x1);
        System.out.println("Is " + x1 + " a palindrome? " + result1); // Expected: true

        // Test case 2: Negative number
        int x2 = -121;
        boolean result2 = isPalindrome(x2);
        System.out.println("Is " + x2 + " a palindrome? " + result2); // Expected: false

        // Test case 3: Non-palindrome
        int x3 = 10;
        boolean result3 = isPalindrome(x3);
        System.out.println("Is " + x3 + " a palindrome? " + result3); // Expected: false

        // Test case 4: Single digit
        int x4 = 5;
        boolean result4 = isPalindrome(x4);
        System.out.println("Is " + x4 + " a palindrome? " + result4); // Expected: true

        // Test case 5: Large palindrome
        int x5 = 12321;
        boolean result5 = isPalindrome(x5);
        System.out.println("Is " + x5 + " a palindrome? " + result5); // Expected: true

    }
    public static boolean isPalindrome(int x) {
        if(x>=0){
            int revNum=0;
            int orgNum=x;
            while(x!=0){
                int pop=x%10;
                x/=10;
                revNum=revNum*10+pop;
            }
            return orgNum==revNum;
        }else{
            return false;
        }
    }
}
