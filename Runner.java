package com.blackjack;

import java.util.Scanner;

public class Runner {

	public static void main(String[] args) {
		boolean rerunCheck = true;
		Scanner input = new Scanner(System.in);
		String response;
		while(rerunCheck) {
			GameLogic gl = new GameLogic();
			System.out.println("Welcome to BlackJack!");
			gl.gameLogic(input);
			System.out.println("Thanks for playing!\n");			
			System.out.println("Would you like to play again? (Y/N)");
			response = input.next();
			System.out.println();
			if(response.equalsIgnoreCase("y") || response.equalsIgnoreCase("n")) {
				if(response.equalsIgnoreCase("y")) {
					rerunCheck = true;
				} else {
					rerunCheck = false;
					System.out.println("Thanks for playing!");
				}
			} else {
				rerunCheck = false;
				System.err.println("Invalid response! Ending the game.");
			}		
		}
		input.close();
	}

}
