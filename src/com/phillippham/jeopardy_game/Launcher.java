/* Author: Phillip Pham
 * Date: January 24, 2017
 * Course: CSC240, Section: 18070
 * 
 * Program Title: Jeopardy Game
 * Program Description: This program will simulate a jeopardy game, with questions that are input from a formatted text file.
 */

package com.phillippham.jeopardy_game;

import java.util.Scanner;

public class Launcher
{
	public static void main(String []args)
	{
		// Initialize variables
		Scanner inputNum = new Scanner(System.in);
		Scanner inputAns = new Scanner(System.in);
		String correctAnswers = "";
		String wrongAnswers = "";
		Jeopardy jep = new Jeopardy();
		boolean hasExit = false;
		
		// Print messages to user with basic instructions on how to play
		System.out.println("Welcome to Jeopardy! This version of jeopardy was designed and created by Phillip Pham.\n");
		System.out.println("\tTo answer a question, please input the correct integers (0 through 4) of the column,\n"
				+ "followed by row of the question you would like to answer.\n");
		System.out.println("\tFor example: To answer the first question in the first category, you must enter: 0, and then 0 again.\n");
		System.out.println("\tTo exit the game, you must enter \"exit\". Enjoy!\n");
		
		// Creating the game board
		jep.populate();
		
		// This will initiate the game loop, which will only exit if the user enters "exit" or finishes the game.
		while(!hasExit)
		{	
			// If the user has finished the game by answering all of the questions, then the loop will end.
			// This will also show statistics after the game is finished.
			if(jep.getNumCorrect() + jep.getNumIncorrect() == 25)
			{
				System.out.println("\nThat's all of the questions! The game is now over!\n");
				System.out.println("Total Score: " + jep.getTotalScore());
				System.out.println("Category 0 Score: " + jep.getCatScore1());
				System.out.println("Category 1 Score: " + jep.getCatScore2());
				System.out.println("Category 2 Score: " + jep.getCatScore3());
				System.out.println("Category 3 Score: " + jep.getCatScore4());
				System.out.println("Category 4 Score: " + jep.getCatScore5());
				System.out.println("Total Correct: " + jep.getNumCorrect());
				System.out.println("Total Incorrect: " + jep.getNumIncorrect());
				System.out.println("Questions Correct: " + correctAnswers);
				System.out.println("Questions incorrect: " + wrongAnswers);
				hasExit = true;
			}
			else
			{
				// Initializing variables
				int row = 0;
				int column = 0;
				String selectColumn = "";
				String selectRow = "";
				String answer;



				// Displaying the game board			
				jep.display();
				System.out.println(jep + "\n");
				System.out.println("Questions correct: " + correctAnswers);
				System.out.println("Questions incorrect: " + wrongAnswers + "\n");

				// This loop will ensure that the user enters an integer ranged 0-4
				// This will ask the user to select the column (category) they would like to choose
				do
				{
					System.out.println("Please enter the number of the category you would like to choose: ");
					selectColumn = inputNum.nextLine();

					// This will check if the user has typed "exit" and will terminate the program if so
					if(selectColumn.toLowerCase().contains("exit"))
					{
						System.out.println("Exiting...");
						inputNum.close();
						inputAns.close();
						System.exit(0);
					}
					else
					{
						// User input is fed into a method that will clean the string of all characters besides integers 0-4
						column = cleanInput(selectColumn);
					}

					// If the user did not enter an integer 0-4, this message will display and the loop starts over
					if(column < 0)
					{
						System.out.println("You must input an integer 0 through 4!");
					}
				}
				while(column < 0);

				// This loop will ensure that the user enters an integer ranged 0-4
				// This will ask the user to select the row (points) they would like to choose
				do
				{
					System.out.println("Please enter the number of the points you would like to attempt: ");
					selectRow = inputNum.nextLine();

					// This will check if the user has typed "exit" and will terminate the program if so
					if(selectRow.toLowerCase().contains("exit"))
					{
						System.out.println("Exiting...");
						inputNum.close();
						inputAns.close();
						System.exit(0);;
					}
					else
					{
						// User input is fed into a method that will clean the string of all characters besides integers 0-4
						row = cleanInput(selectRow);
					}

					// If the user did not enter an integer 0-4, this message will display and the loop starts over
					if(row < 0)
					{
						System.out.println("You must input an integer 0 through 4!");
					}
				}
				while(row < 0);

				// This will display the question that the user requested
				jep.displayQuestion(column, row);

				// This will check if the question has already been attempted, and then proceed to take the answer if not
				if(jep.getBoard()[column][row].hasAttempted() == false)
				{
					System.out.println("What is your answer?");
					answer = inputAns.nextLine();
					
					// This will input the answer into the answer method in Jeopardy, and return a boolean if it's true or false
					boolean isCorrect = jep.answer(answer.toLowerCase(), column, row);

					// This will display a message and add the question to the already attempted list so that the user knows
					if(!isCorrect)
					{
						System.out.println("You were incorrect!");
						System.out.println("The correct answer was: " + jep.getBoard()[column][row].getAnswer() + "\n");
						wrongAnswers += "(" + column + "," + row + ")";
					}
					else
					{
						System.out.println("That is the correct answer! Great job!\n");
						correctAnswers += "(" + column + "," + row + ")";
					}
				}
			}
		}
		
		// Exit gracefully
		inputNum.close();
		inputAns.close();
		
		System.exit(0);
	}
	
	// This method will scan the String that the user input and take only Integers 0-4. If not detected, then it will give a negative return.
	private static int cleanInput(String input)
	{
		// Keep only the first integer
		for (int i = 0; i < input.length(); i++)
		{	
			int result = 0;
			
			if (input.charAt(i) >= '0' && input.charAt(i) <= '4')
			{	
				String temp = "";
				temp += input.charAt(i);
				result = Integer.parseInt(temp);
				return result;
			}
		}
		
		// The negative return helps ensure that if numbers 0-4 are not found in the String, it is possible to keep
		// the question selection loops going until the user enters the correct integers.
		return -1;
	}
}