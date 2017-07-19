/******************************************************************
* 
* 
*Assignment 3 - Wythoff Game
*CST8110 Intro to Computer Programming
*Section 302
*Instructor Howard Rosenblum
*
*Program created by Nicholas Lockhart
*Submitted July 22nd 2017
*
*
*****************************************************************/
public class Assign3 {
	
	public static void main (String[] args) {
		Wythoff game = new Wythoff();
		
		System.out.println("Welcome to Wythoff's game");
		System.out.println("You and the computer will take turns removing counters from one or both piles;");
		System.out.println("when removing counters from both piles, the number of counters removed from each");
		System.out.println("pile must be equal.");
		System.out.println("The game ends when the last person removes the last counter or counters, thus winning.");
		
		do{
			game.printPiles();
				
			if(!game.playerMove()){
				
				System.out.println("Congrats, you win!");
				continue;
				
			}
					
			if(!game.computerMove()){
				
				System.out.println("Sorry, I win!");
				
			}
				
		}while(!game.gameDone());

	}
}
