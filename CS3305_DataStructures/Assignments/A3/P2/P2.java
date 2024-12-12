// Name: Casey Hampson
// Class: CS 3305/W01
// Term: Fall 2024
// Instructor: Sharon Perry
// Assignment: 3-Part-2-Palindromes

import java.util.Scanner;
import java.util.Stack;

public class P2 {
    // declare our scanner and the three stacks (for characters)
    static Scanner input_scanner;
    static Stack<Character> stack1, stack2, stack3;


    static boolean IsPalindrome(String str) {
        // first, push all the characters to stack 1 and stack 2
        for (char c: str.toCharArray()) {
            stack1.add(c);
            stack2.add(c);
        }
        // then, we pop everything off of stack 1 and push them to stack 3
        // this effectively reverses the string
        while (!stack1.isEmpty()) {
            stack3.add(stack1.pop());
        }
        // now just stack2 and 3 for equality
        return stack2.equals(stack3);
    }


    public static void main(String[] args) {
        // instantiate our scanner and three stakcs
        input_scanner = new Scanner(System.in);
        stack1 = new Stack<>();
        stack2 = new Stack<>();
        stack3 = new Stack<>();

        System.out.printf("Please enter a string to test if it is a palindrome: ");
        String input = input_scanner.nextLine();
        input = input.toLowerCase(); // make it  lowercase just to be safe
        System.out.printf(
            "\"%s\" is%s a palindrome.\n",
            input, IsPalindrome(input) ? "" : " not"
        );


        input_scanner.close();
    }
}