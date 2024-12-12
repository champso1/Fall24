// Name: Casey Hampson
// Class: CS 3305/W01
// Term: Fall 2024
// Instructor: Sharon Perry
// Assignment: 05-Part-2-Miles

public class P2 {
    public static float MilesToKilometers(float input) {
        return (input / 1.6f);
    }

    // helper function to print our array
    // not general at all but suits the purpose of this program
    public static void PrintArray(float[] arr) {
        for (int i=0; i<10; i++) System.out.printf("%.3f ", arr[i]);
        System.out.printf("\n");
    }

    public static void main(String[] args) {
        float[] input = {0, 10, 20, 30, 40, 50, 60, 70, 80, 90};
        System.out.printf("Before conversion:\n");
        PrintArray(input);

        for (int i=0; i<10; i++) input[i] = MilesToKilometers(input[i]);
        System.out.printf("After conversion:\n");
        PrintArray(input);
    }
}