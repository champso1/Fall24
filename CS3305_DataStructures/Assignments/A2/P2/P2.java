// Name: Casey Hampson
// Class: CS 3305/W01
// Term: Fall 2024
// Instructor: Sharon Perry
// Assignment: 2 - Part 1 Iterator

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class P2 {
    final static String FILE_PATH = "./P2/res/Assignment-2-Part-2-Capital-Data.txt";
    final static short NUM_CAPITALS = 50;
    static Scanner input_scanner;

    // returns a hashmap with the keys being the states
    // and the values being the corresponding capitals
    public static HashMap<String, String> ParseFile(String file_path) {
        HashMap<String, String> capitals = new HashMap<>();
        try {
            Scanner file_scanner = new Scanner(new File(file_path));
        
            while (file_scanner.hasNextLine()) {
                String line = file_scanner.nextLine();
                // split the line by the comma
                String tokens[] = line.split(",");
                // add the state/capital pair into the hashmap
                capitals.put(tokens[0], tokens[1]);
            }
            
            file_scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.printf("Could not find the file.\n");
        }
        return capitals;
    }

    public static int DoGuesses(HashMap<String, String> states_map, int num_guesses) {
        Random random = new Random();

        // grab an iterable array of the keys so we can grab a random state
        String states_arr[] = states_map.keySet().toArray(new String[0]);
        ArrayList<String> used_states = new ArrayList<>();

        int num_correct = 0;
        for (int i=0; i<num_guesses; i++) {
            int random_state_idx = random.nextInt(0, NUM_CAPITALS);
            String random_state = states_arr[random_state_idx];

            // test to make sure we haven't used this particular capital already
            if (used_states.contains(random_state)) {
                i--;
                continue;
            }
            used_states.add(random_state);

            System.out.printf("Guess the capital of %s: ", random_state);
            String guess = input_scanner.nextLine().toLowerCase();
            if (guess.equals(states_map.get(random_state).toLowerCase())) {
                System.out.printf("Correct!\n");
                num_correct++;
            } else {
                System.out.printf("Incorrect... The correct answer is %s\n", states_map.get(random_state));
            }
        }
        return num_correct;
    }

    public static void main(String[] args) {
        input_scanner = new Scanner(System.in);

        System.out.printf("Enter the number of iterations you want: ");
        int num_guesses = Integer.parseInt(input_scanner.nextLine());
        while (num_guesses <= 0) {
            System.out.printf("Please enter a valid number: ");
            num_guesses = Integer.parseInt(input_scanner.nextLine());
        }
        
        HashMap<String, String> capitals = ParseFile(FILE_PATH);
        int num_correct = DoGuesses(capitals, num_guesses);
        System.out.printf(
            "You got %d out of %d: %.2f %% \n",
            num_correct, num_guesses, ((float)(num_correct)/(float)num_guesses)*100.0f
        );

        input_scanner.close();
    }
}