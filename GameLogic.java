package com.blackjack;

import java.util.Scanner;

public class GameLogic {
	
	Game game = new Game();
	private String response;
	
	public void gameLogic(Scanner input) {
		//Assign 2 random numbers to player
		game.setFirstPlayerCard(randomNumberGenerator());
		game.setDisplayCard(numberToCardConverter(game.getFirstPlayerCard()));
		System.out.println("Your first card is: " + game.getDisplayCard());
		game.setPlayerTotal(game.getPlayerTotal() + game.getFirstPlayerCard());
		game.setSecondPlayerCard(randomNumberGenerator());
		game.setDisplayCard(numberToCardConverter(game.getSecondPlayerCard()));
		System.out.println("Your second card is: " + game.getDisplayCard());
		game.setPlayerTotal(game.getPlayerTotal() + game.getSecondPlayerCard());
		game.setPlayerTotal(elevenCheck(game.getPlayerTotal(), game.getFirstPlayerCard(), game.getSecondPlayerCard()));
		System.out.println("Your total is: " + game.getPlayerTotal());
		
		//Assign 2 random numbers to house
		game.setFirstHouseCard(randomNumberGenerator());
		game.setDisplayCard(numberToCardConverter(game.getFirstHouseCard()));
		System.out.println("The first card of the house is: " + game.getDisplayCard());
		game.setHouseTotal(game.getHouseTotal() + game.getFirstHouseCard());
		game.setSecondHouseCard(randomNumberGenerator());
		
		//player wins automatically if total is 21 in the beginning
		if(game.getPlayerTotal() == 21) {
			System.out.println("BlackJack! You win!");
		}
		
		do {
			System.out.println("Do you want to hit? (Y/N)");
			response = input.next();
			if(response.equalsIgnoreCase("y") || response.equalsIgnoreCase("n")) {
				if(response.equalsIgnoreCase("y")) {
					game.setPlayerCard(randomNumberGenerator());
					game.setDisplayCard(numberToCardConverter(game.getPlayerCard()));
					System.out.println("Your next card is: " + game.getDisplayCard());
					game.setPlayerTotal(game.getPlayerTotal() + game.getPlayerCard());
					game.setPlayerTotal(elevenCheck(game.getPlayerTotal(), game.getPlayerCard()));
					System.out.println("Your total is: " + game.getPlayerTotal());
				}
				else if(response.equalsIgnoreCase("n")) {
					System.out.println("Staying...");
					game.setDisplayCard(numberToCardConverter(game.getSecondHouseCard()));
					System.out.println("The second card of the house is: " + game.getDisplayCard());
					game.setHouseTotal(game.getHouseTotal() + game.getSecondHouseCard());
					game.setHouseTotal(elevenCheck(game.getHouseTotal(), game.getFirstHouseCard(), game.getSecondHouseCard()));
					System.out.println("The house total is: " + game.getHouseTotal());
					houseDeal();
				}
			} else {
				System.err.println("Invalid response! Try again");
			}
		} while(!checkWinner());	
	}
	
	private int randomNumberGenerator(){
		return 2 + (int)(Math.random()*((11-2)+1));
	}
	
	private boolean checkWinner() {
		if(game.getPlayerTotal() > 21) {
			System.out.println("Bust! You Lose");
			return true;
		} 
		else if(game.getHouseTotal() > 16 && game.getPlayerTotal() == game.getHouseTotal()) {
			System.out.println("Push! It's a tie!");
			return true;
		} 
		else if(game.getPlayerTotal() == 21 && game.getHouseTotal() < 21) {
			System.out.println("BlackJack! You win!");
			return true;
		} 
		else if(game.getPlayerTotal() < game.getHouseTotal() && game.getHouseTotal() < 22) {
			System.out.println("House wins! You Lose");
			return true;
		} 
		else if(game.getPlayerTotal() > game.getHouseTotal() && game.getHouseTotal() > 16) {
			System.out.println("You win!");
			return true;
		}
		else if(game.getPlayerTotal() < game.getHouseTotal() && game.getHouseTotal() > 21) {
			System.out.println("You win!");
			return true;
		}
		else 
			return false;		
	}
	
	private void houseDeal() {
		while(game.getHouseTotal() < 17) {
			game.setHouseCard(randomNumberGenerator());
			game.setDisplayCard(numberToCardConverter(game.getHouseCard()));
			System.out.println("The next card of the house is: " + game.getDisplayCard());
			game.setHouseTotal(game.getHouseTotal() + game.getHouseCard());
			game.setHouseTotal(elevenCheck(game.getHouseTotal(), game.getHouseCard()));
			System.out.println("The house total is: " + game.getHouseTotal());
		}
	}
	
	//converting number to a character for 
	private String numberToCardConverter(int number) {
		if(number == 10) {
			//assign a J,Q,K or 10 randomly
			int i = 1 + (int)(Math.random()*((4-1)+1));
			if(i == 1)
				return "J";
			else if(i == 2)
				return "Q"; 
			else if(i == 3)
				return "K";
			else
				return Integer.toString(number);
		} else if(number == 11) {
				return "A";
		} else {
			return Integer.toString(number);
		}
	}
	
	//check if number has to converted to 11 or not
	private int elevenCheck(int total, int first, int second) {
		if(total > 21 && (first == 11 || second == 11))
			total -= 10;
		return total;
	}
	
	private int elevenCheck(int total, int card) {
		if(total > 21 && card == 11)
			total -= 10;
		return total;
	}

}
