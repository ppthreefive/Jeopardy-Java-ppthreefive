/* Author: Phillip Pham
 * Date: January 24, 2017
 * Course: CSC240, Section: 18070
 * 
 * Program Title: Jeopardy Game
 * Program Description: This program will simulate a jeopardy game, with questions that are input from a formatted text file.
 */

/* UML Details:
 * 
 * Attributes:
 * - category: String
 * - question: String
 * - answer: String
 * - points: int
 * - attempted: boolean
 * 
 * Behaviors:
 * + getCategory(): String
 * + getQuestion(): String
 * + getAnswer(): String
 * + getPoints(): int
 * + hasAttempted(): boolean
 * + setCategory(): void
 * + setQuestion(): void
 * + setAnswer(): void
 * + setPoints(): void
 * + setAttempted: void
 * + toString(): String
 */

package com.phillippham.jeopardy_game;

public class Question
{
	
	// Attributes
	private String category, question, answer;
	private int points;
	private boolean attempted;

	// Constructors
	public Question()
	{
		this.category = null;
		this.question = null;
		this.answer = null;
		this.points = 0;
		this.attempted = false;
	}

	public Question(String question, String answer, String category, int points)
	{
		this.category = category;
		this.question = question;
		this.answer = answer;
		this.points = points;
		this.attempted = false;
	}

	// Accessors
	public String getCategory()
	{
		return category;
	}

	public String getQuestion()
	{
		return question;
	}

	public String getAnswer()
	{
		return answer;
	}

	public int getPoints()
	{
		return points;
	}
	
	public boolean hasAttempted()
	{
		return this.attempted;
	}

	// Mutators
	public void setCategory(String category)
	{
		this.category = category;
	}

	public void setQuestion(String question)
	{
		this.question = question;
	}

	public void setAnswer(String answer)
	{
		this.answer = answer;
	}

	public void setPoints(int points)
	{
		this.points = points;
	}
	
	public void setAttempted(boolean attempted)
	{
		this.attempted = attempted;
	}

	// Methods
	public String toString()
	{
		return "Category: " + category + ", Question: " + question;
	}
}