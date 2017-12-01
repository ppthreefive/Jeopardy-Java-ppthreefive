/* Author: Phillip Pham
 * Date: January 24, 2017
 * Course: CSC240, Section: 18070
 * 
 * Program Title: Jeopardy Game
 * Program Description: This program will simulate a jeopardy game, with questions that are input from a formatted text file.
 */

package com.phillippham.jeopardy_game;

public class AlreadyAttemptedException extends Exception
{
	public AlreadyAttemptedException()
	{
		super("Sorry. You have already attempted to answer this question.");
	}
	
	public AlreadyAttemptedException(String message)
	{
		super(message);
	}
}