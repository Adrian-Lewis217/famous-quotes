package com.pluralsight;

import java.util.Random;
import java.util.Scanner;

public class QuotesApp {
    public static void main(String[] args) {
        String[] quotes = {
          "Life has no remote. Get up and change it yourself",
          "You may encounter many defeats, but you must not be defeated.",
          "It's not what happens to you, but how you react to it that matters.",
          "When we strive to become better than we are, everything around us becomes better too.",
          "The secret of getting ahead is getting started.",
          "You can never cross the ocean until you have the courage to lose sight of the shore.",
          "Do the best you can until you know better. Then when you know better, do better.",
          "You are never too old to set another goal or to dream a new dream.",
          "Your time is limited, so don't waste it living someone else's life.",
          "The two most important days in your life are the day you are born and the day you find out why."
        };

        Scanner userInput = new Scanner(System.in);

        try {
            System.out.println("Enter a number between 1-10 to display a specific quote.");
            System.out.print("Your choice: ");

            // nextInt throws InputMismatchException for text like "abc"
            int indexFromUser = userInput.nextInt();

            // - 1 turns the user's 1 to 10 into the array's 0 to 9;
            // 0 or 11 throws ArrayIndexOutOfBoundsException
            System.out.println(quotes[indexFromUser - 1]);

        } catch (Exception exception) {
            // Exception catches both problems above (bad number, out of range)
            System.out.println("Invalid input. Please enter a number between 1 and 10.");
            userInput.nextLine();  // Clear the buffer.
        }

        // BONUS version: keep going until the user answers N, and R picks a random quote.
        // To try it, comment out the try/catch above and remove the comment markers here.
        boolean continueLoop = true;
        // One Random object for the whole program, reused for every random quote
        Random random = new Random();

        while (continueLoop) {
            // Everything that can throw sits inside try, so one bad answer
            // does not end the program: we jump to catch and loop again
            try {
                System.out.println("1. Enter a number between 1-10 to display a specific quote.");
                System.out.println("2. Enter 'R' to display a random quote.");
                System.out.print("Your choice: ");

                // Read the answer as text, because it can be a number OR the letter R
                String input = userInput.next();

                if (input.equalsIgnoreCase("R")) {
                    // nextInt(10) gives 0 to 9, exactly the valid positions.
                    // What is the formula if I want a different range of numbers?
                    //   random.nextInt(max - min + 1) + min
                    //   example, 1 to 10:  random.nextInt(10 - 1 + 1) + 1
                    //   example, 5 to 20:  random.nextInt(20 - 5 + 1) + 5
                    int randomIndex = random.nextInt(quotes.length);
                    System.out.println(quotes[randomIndex]);
                } else {
                    // parseInt throws NumberFormatException for text like "abc"
                    int index = Integer.parseInt(input);
                    // - 1 turns the user's 1 to 10 into the array's 0 to 9;
                    // 0 or 11 throws ArrayIndexOutOfBoundsException
                    System.out.println(quotes[index - 1]);
                }

                System.out.print("Do you want to see another quote? (Y/N): ");
                String answer = userInput.next();

                // Any answer other than N keeps the loop going
                if (answer.equalsIgnoreCase("N")) {
                    continueLoop = false;
                }
            } catch (Exception exception) {
                // Exception catches both problems above (bad number, out of range)
                System.out.println("Invalid input. Please enter a number between 1 and 10 or 'R' for a random quote.");
                userInput.nextLine();  // Clear the buffer.
            }
        }
    }
}
