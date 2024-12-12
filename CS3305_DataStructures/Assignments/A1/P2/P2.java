/*
 * Name: Casey Hampson
 * Class: CS3305/W01
 * Term: Fall24
 * Instructor: Sharon Perry
 * Assignment: A1 Factorial
 */


import java.util.Scanner;


public class P2 {

    private static void __recursion(int input, int current) {
        // We start from 1 and print out the first statement, 
        // then recursively call the function with incremented numbers,
        // which keeps printing increasing numbers all the way up to our initial input.
        // During these calls, the ``ALSO'' statements are added to a sort of ``stack'',
        // so once we reach the base case, the ``ALSO'' statements, since they are stacked,
        // will print in descending order,
        
        // base case is once we reach the initial input number
        if (current > input) return;

        System.out.printf("This was written by call number %d\n", current);
        __recursion(input, current+1);
        System.out.printf("This was ALSO written by call number %d\n", current);
    }

    // this is to make the calling from main much cleaner looking;
    // the user doesn't have to pass 1 randomly to the recursion function
    public static void Recursion(int input) {
        __recursion(input, 1);
    }

    public static void main(String[] args) {
        System.out.printf("Please enter a number: ");
        try (Scanner sc = new Scanner(System.in);) {
            Recursion(Integer.parseInt(sc.nextLine()));
        }
    }
}
