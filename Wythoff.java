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

import java.util.Scanner;
import java.util.Random;

public class Wythoff {
	private Pile pileA; //First pile
	private Pile pileB; //Second pile
	private Random randomNumber; //Random number generator
	private Scanner input; //Scanner for all inputs
	
	

	public Wythoff(){
		
		randomNumber = new Random();
		pileA = new Pile(randomNumber.nextInt(11)+10);
		pileB = new Pile(randomNumber.nextInt(11)+10);
		input = new Scanner(System.in);
		
		while(pileA.getSize()==pileB.getSize()){
			
			pileB = new Pile(randomNumber.nextInt(11)+10);
			
		}
		
	}; //Default constructor
	
	
	
	
	public boolean playerMove() {
		System.out.print("\nPile A, B, or Both?");
		
		String move = input.next();
		
		//checks if move is valid
		while(!move.equalsIgnoreCase("a")&&!move.equalsIgnoreCase("b")&&!move.equalsIgnoreCase("both")){
			
			System.out.print("Please pick Pile A, B, or Both?");
			
			move = input.nextLine();
			
		}
		
		move = move.toLowerCase();
		
		
		//variable for size use
		int sizeA = pileA.getSize();
		
		int sizeB = pileB.getSize();
		
		
		
		//ensures the user chooses a pile with an amount in it
		if((sizeA==0)||(sizeB==0)){
			
			if((move=="a"||move=="both")&&sizeA == 0){
				
				System.out.print("Pile A is empty, please pick another");
				move = input.nextLine();
				
			} 
			
			else if((move=="b"||move=="both")&&sizeB == 0){
				
				System.out.print("Pile B is empty, please pick another");
				move = input.nextLine();
				
			} 
			
			else {
				
				if(move.equals("a")){
					pileA.remove(this.promptNumber(pileA));
				} 
				
				else {
					pileB.remove(this.promptNumber(pileB));
				}
			}
		}
		
		
		//given the choice, prompt how much to be removed from chosen pile
		switch(move){
		
			case("both"):{
				
				int removed;
				
				if(sizeA<sizeB){
					removed = this.promptNumber(pileA);
				} 
				
				else {
					removed = this.promptNumber(pileB);
				}
				
				
				pileA.remove(removed);
				
				pileB.remove(removed);
				
				break;
			}
			
			
			case("a"): {
				
				int removed = this.promptNumber(pileA);
				
				pileA.remove(removed);
				
				break;
				
			}
			
			
			case("b"): {
				
				int removed = this.promptNumber(pileB);
				pileB.remove(removed);
				
				break;
				
			}
		}
		
		
		

		if(this.gameDone()){
			
			return false;
			
		}
		
		else {
			
			return true;
			
		}
	} //One move for the player, did the player select a valid move?
	
	
	
	
	
	private int promptNumber(Pile x){
		
		System.out.print("Pick a number between 1 and "+x.getSize()+"?");
		
		int move = input.nextInt();
		
		while(move>x.getSize()||move<=0){
			
			System.out.print("Please pick a number between 1 and "+x.getSize()+"?");
			
			move = input.nextInt();
			
		}
		
		return move;
	} //For a given pile, prompt for the number of counters to remove (with retry)
	
	
	
	
	
	
	
	public boolean computerMove(){
		
		int removed;
		int sizeA = pileA.getSize();
		int sizeB = pileB.getSize();
		
		if(sizeA==sizeB){
			
			removed = this.removeStrategically(sizeA, sizeB);
			pileA.remove(removed);
			pileB.remove(removed);

			System.out.print("computer removed "+removed+" from both Piles\n");
			
		}
		
		if(sizeA>sizeB){
			
			removed = this.removeStrategically(sizeA, sizeB);
			pileA.remove(removed);

			System.out.print("computer removed "+removed+" from Pile A\n");
			
		}
		
		if(sizeB>sizeA){
			
			removed = this.removeStrategically(sizeB, sizeA);
			pileB.remove(removed);
			
			System.out.print("computer removed "+removed+" from Pile B\n");
		}

		if(this.gameDone()){
			return false;
		}
		
		else {
			return true;
			
		}
	} //One move for the computer
	
	
	
	private int removeStrategically(int x, int y){


		//checks to see if there is an win move
		if(x==y||y==0){

			return x;

		}
		
		
		
		//these are the needed variables for the golden wythoff
		int other = x;

		double golden = 1.6180339;
		
		int removed = 0;
		
		/*this should work
		Cold postions in this game are ones that can only
		lead to the player that creates it to a win.
		
		
		ie) when the computer sets piles to a cold position, the player
		has a harder time winning against the computer.
		
		
		Wythoff proved that cold positions in the game
		are recusively based off multiples of the golden
		ratio. This loop checks each possible cold position.
		cold positions as follows:
		
		i:   1 | 2 | 3 | 4  | 5  | 6  | 7  | 8
		a:   1 | 3 | 4 | 6  | 8  | 9  | 11 | 12
		b:   2 | 5 | 7 | 10 | 13 | 15 | 18 | 20
		
		I only check as high as i:8 as 20 is the highest number
		the game can be set.
		
		
		*/
		
		for(int i = 1; i < 8; i++) {
			
			int a = (int) Math.floor(i*golden);
			
			int b = a+i;
			
			if(b==y){
				
				while(other!=a){
					other--;
					removed++;
				}
				
				return removed;
			}
			
			
			continue;
		}
		
		//if it can't make a normal cold move, it will attempt to force
		// i:1 cold move, the lowest one
		return x-2;
		
	} //BONUS, given current pile size and other pile size number that should be removed
	
	
	public void printPiles(){
		
		System.out.println("      A       B");
		
		pileA.displayCounter();
		
		pileB.displayCounter();
		
		
	} //Print all the piles
	public boolean gameDone(){
		
		if(pileA.isEmpty()&&pileB.isEmpty()){
			
			return true;
			
		}
		
		return false;
		
	} //Is the game done?
}
