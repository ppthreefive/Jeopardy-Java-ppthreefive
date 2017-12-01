package com.phillippham.jeopardy_game;

/* Author: Phillip Pham
 * Date: January 24, 2017
 * Course: CSC240, Section: 18070
 * 
 * Program Title: Jeopardy Game
 * Program Description: This program will simulate a jeopardy game, 
 * 			with questions that are input from a formatted text file.
 */

/* Algorithm:
 * This class will contain methods that handle most of the game logic, such as populate(),
 * 			 displayQuestion(), display(), and answer().
 * 
 * populate() will check for a file named "Jeopardy.txt" containing 25 lines 
 * 			with Questions, Answers, Categories, and Points split by tabs.
 * 			This will used BufferredReader to scan the file line-by-line, and put the lines
 * 			into an ArrayList. This ArrayList will have each of its' Strings split 
 * 			by detected tabs into another ArrayList with String arrays, each with 4 Strings.
 * 			Finally, this will be fed into the Question object's constructor for each cell 
 * 			in the 2D array, in order.
 * 
 * After the 2D array has been populated, you can use the other methods with ease. 
 * Otherwise, every attribute in every question will be set to null.
 * 
 * answer() will check the String input that is fed into the method against whatever the answer is
 * 			for its' respective cell (2D array coordinates are also fed into the method).
 * 			This will be mostly achieved by using both toLowerCase() and the equals() method 
 * 			built into Strings.
 * 
 * displayQuestion() will check if the 2D array coordinates fed into the method match with a 
 * 					Question object inside the respective cell that already has been answered.
 * 					If it hasn't, then it will display the question to the user, or give an error
 * 					if it already has.
 * 
 * display() will display all of the relative information required for the user experience of the game.
 * 			The text is formatted to simulate a jeopardy game board.
 * 
 */

/* UML Details:
 * Attributes:
 * - totalScore: int
 * - catScore0: int
 * - catScore1: int
 * - catScore2: int
 * - catScore3: int
 * - catScore4: int
 * - numCorrect: int
 * - numIncorrect: int
 * - board[][]: Question
 * 
 * Behaviors:
 * + getTotalScore(): int
 * + getCatScore1(): int
 * + getCatScore2(): int
 * + getCatScore3(): int
 * + getCatScore4(): int
 * + getCatScore5(): int
 * + getNumCorrect(): int
 * + getNumIncorrect(): int
 * + toString(): String
 * + displayQuestion(): void
 * + answer(): boolean
 * + display(): void
 * + populate(): boolean
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Jeopardy 
{
	// Attributes
	private int totalScore, catScore0, catScore1, catScore2, catScore3, catScore4, numCorrect, numIncorrect;
	static final int COLUMN = 5;
	static final int ROW = 5;
	private Question[][] board;
	
	// Constructor
	public Jeopardy()
	{
		this.board = new Question[COLUMN][ROW];
		this.totalScore = 0;
		this.catScore0 = 0;
		this.catScore1 = 0;
		this.catScore2 = 0;
		this.catScore3 = 0;
		this.catScore4 = 0;
		this.numCorrect = 0;
		this.numIncorrect = 0;
	}
	
	// Accessors 
	public int getTotalScore()
	{
		return totalScore;
	}

	public int getCatScore1()
	{
		return catScore0;
	}

	public int getCatScore2()
	{
		return catScore1;
	}

	public int getCatScore3()
	{
		return catScore2;
	}

	public int getCatScore4()
	{
		return catScore3;
	}
	
	public int getCatScore5()
	{
		return catScore4;
	}
	public int getNumCorrect()
	{
		return numCorrect;
	}

	public int getNumIncorrect()
	{
		return numIncorrect;
	}
	
	public Question[][] getBoard()
	{
		return board;
	}
	
	// toString() will display everything score related to the user
	public String toString()
	{
		return "Total Correct: " + numCorrect + ", Total Incorrect: " + numIncorrect + ", \nCategory 0 Score: " + catScore0 + ", Category 1 Score: " + catScore1
				+ ", Category 2 Score: " + catScore2 + ", Category 3 Score: " + catScore3 + ", Category 4 Score: " + catScore4 + "\nTotal Score: " + totalScore;
	}

	// This method will display the question selected by the user, and give an error if already has been chosen.
	public void displayQuestion(int x, int y)
	{
		// Checks if the hasAttempted boolean is true or false
		boolean attempted = this.board[x][y].hasAttempted();
		String output = "";
		try
		{
			if(!attempted)
			{
				output += this.board[x][y].getCategory() + " for " + this.board[x][y].getPoints() + " points:\n";
				output += this.board[x][y].getQuestion();
				System.out.println(output);
			}
			else
			{
				throw new AlreadyAttemptedException("Sorry, this question has already been attempted!");
			}
		}
		catch(AlreadyAttemptedException ex)
		{
			System.out.println("This question has already been attempted. Please choose another question.\n");
		}
	}
	
	// This method will accept the user's answer and compare to whatever was input from the text file.
	public boolean answer(String answer, int x, int y)
	{
		// If the answer is correct, the user will get points added to total and for the category. Also increments numCorrect.
		// This will set the attempted flag to true.
		if((answer.toLowerCase().equals((board[x][y].getAnswer().toLowerCase()))))
		{
			this.totalScore += board[x][y].getPoints();
			
			if(x == 0)
			{
				this.catScore0 += board[x][y].getPoints();
			}
			else if(x == 1)
			{
				this.catScore1 += board[x][y].getPoints();
			}
			else if(x == 2)
			{
				this.catScore2 += board[x][y].getPoints();
			}
			else if(x == 3)
			{
				this.catScore3 += board[x][y].getPoints();
			}
			else if(x == 4)
			{
				this.catScore4 += board[x][y].getPoints();
			}
			
			this.board[x][y].setAttempted(true);
			this.numCorrect++;
			
			// Returns that the user got the answer correct.
			return true;
		}
		else
		{	
			// This will increment the number of incorrect answers for the user and set the attempted flag to true.
			this.numIncorrect++;
			this.board[x][y].setAttempted(true);

			// Returns that the user got the answer incorrect.
			return false;
		}
	}
	
	// This will display all information of the game board and everything related to the game board.
	public void display()
	{
		String categories = this.board[0][0].getCategory() + " (0) \t" + this.board[1][0].getCategory()
				+ " (1) \t" + this.board[2][0].getCategory() + " (2) \t" + this.board[3][0].getCategory() + " (3) \t"
				+ this.board[4][0].getCategory() + " (4)     |";
		String underline = "_______________________________________________________________________________________ |";
		
		String output = "";
		
		for (int j = 0; j < 5; j++)
		{
			for (int i = 0; i < 5; i++)
			{
				if(i == 4)
				{
					if(j == 4)
					{
						output += "\t" + this.board[i][j].getPoints() + "\t\t" + "|" + "(" + j + ")";
					}
					else
					{
						output += "\t" + this.board[i][j].getPoints() + "\t\t" + "|" + "(" + j + ")" + "\n";
					}
				}
				else
				{
					output += this.board[i][j].getPoints() + "\t\t";
				}
			}
		}
		
		// Displays all of the Strings to form the game board.
		System.out.println(categories);
		System.out.println(underline);
		System.out.println(output);
		System.out.println(underline);
	}
	
	// This method only needs to be used once, and this will fill the 2D array with 25 Question objects that is loaded
	// Directly from the Jeopardy.txt file.
	public boolean populate()
	{
		boolean done = false;
		int row = 0;
		int column = 0;
		
		if(column < 5 && column >= 0)
		{
			try 
			{	
				// Initialize variables
				BufferedReader br = new BufferedReader(new FileReader("Jeopardy.txt"));
				String line = null;
				List<String> lines = new ArrayList<String>();
				List<String[]> output = new ArrayList<String[]>();
				
				// This will scan the text file line by line until there is nothing left to scan
				while((line = br.readLine()) != null)
				{
					lines.add(line);	
				}
				
				// This will split each element in the ArrayList every time it detects a tab, and then
				// it will add those separate arrays into another ArrayList, numbered 0-24. And each of the
				// elements inside the ArrayList now contains an array of 4 Strings:
				// [0] = Question, [1] = Answer, [2] = Category, [3] = Points
				for (int i = 0; i < 25; i++)
				{
					//output.add(importLines[i].split("\t"));
					output.add(lines.get(i).split("\t"));
				}
				
				// This will input each of those separate parts of the array elements that are inside the ArrayList, 
				// into each cell in our 2D array, in order.
				for (int i = 0; i < 25; i++)
				{
					this.board[column][row] = new Question(output.get(i)[0], output.get(i)[(1)], output.get(i)[(2)], 
								Integer.parseInt(output.get(i)[(3)]));
					
					if(row == 4)
					{
						column++;
						row = 0;
					}
					else
					{
						row++;
					}
				}
				
				// Closes the BufferredReader
				br.close();
			}
			catch(IOException ex)
			{
				// This will happen if there is an issue with the text file.
				System.out.println(ex.getMessage());
			}
			
			done = true;
		}

		// Will return true if the game board has successfully been populated, or failed.
		return done;
	}
}