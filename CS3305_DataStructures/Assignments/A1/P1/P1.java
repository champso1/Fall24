/*
 * Name: Casey Hampson
 * Class: CS3305/W01
 * Term: Fall24
 * Instructor: Sharon Perry
 * Assignment: A1 Factorial
 */


import java.util.Scanner;


public class P1 {

    public static long Factorial(int x) {
        // base case; once x gets to be 1 (or less), return just x
        if (x <= 1) return x;
        // otherwise, continue with the factorial
        else return x * Factorial(x-1);

    }

    public static void main(String[] args) {
        // use a try-with-resources for the scanner in case of error when creating it
        // also automatically closes it and frees memory
        try(Scanner sc = new Scanner(System.in);) {
            // grab the user's **non-negative** input
            System.out.printf("Enter a nonnegative integer: ");
            int x = Integer.parseInt(sc.nextLine());
            while (x <= 0) {
                System.out.printf("Please enter a valid input: ");
                x = Integer.parseInt(sc.nextLine());
            }

            // compute the factorial using our recursive algorithm and print the result out
            long fact = Factorial(x);
            System.out.printf("The factorial of %d (%d!) is: %d\n", x, x, fact);
        }
    }
}