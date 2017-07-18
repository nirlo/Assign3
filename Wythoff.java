/******************************************************************
* 
* 
*Assignment 3 - Wythoff Game
*CST8110 Intro to Computer Programming
*Section 300
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
		
		move.toLowerCase();
		
		//checks if move is valid
		while(!(move!="a"||move!="b"||move!="both")){
			
			System.out.print("\nPlease pick Pile A, B, or Both?");
			
			move = input.nextLine();
			
			move.toLowerCase();
		}
		
		
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
		
		/*
		//ensure that the chosen pile is not empty
		//the computer will then choose the pile that is not empty
		
		if((move==2&&(sizeA==0||sizeB==0))||(sizeA==0||sizeB==0)){
			
			if((move==0&&sizeA==0)||move==2&&sizeA==0){
				move = 1;
				
			} 
			
			else if((move==1&&sizeB==0)||move==2&&sizeB==0) {
				move = 0;
				
			}
		}
		//the chosen pile will now have an amount removed from it
		//if a or b, it will determine a way to put the piles in a cold position
		switch(move){
		
		
			case(0):{
				removed = this.removeStrategically(sizeA, sizeB);
				System.out.print("computer removed "+removed+" from Pile A\n");
				pileA.remove(removed);
			}
			
			
			break;
			
			
			case(1):{
				removed = this.removeStrategically(sizeB, sizeA);
				System.out.print("Computer removed "+removed+" from Pile B\n");
				pileB.remove(removed);
			}
			
			
			break;
			
			
			case(2):{
				
				if(sizeA < sizeB){
					
					removed = randomNumber.nextInt(sizeA)+1;
					
					System.out.print("Computer removed "+removed+" from both piles\n");
					
					pileA.remove(removed);
					
					pileB.remove(removed);
					
				} 
				
				else {
					
					removed = randomNumber.nextInt(sizeB)+1;
					
					System.out.print("Computer removed "+removed+" from both piles\n");
					
					pileA.remove(removed);
					
					pileB.remove(removed);
					
				}
			}
		}
		*/
		if(this.gameDone()){
			return false;
		}
		
		else {
			return true;
			
		}
	} //One move for the computer
	
	
	
	private int removeStrategically(int x, int y){


		if(x==y){

			return x;

		}
		
		if (x>10||y>10){
			
			return 10;
			
		}
		
		return x-1;
		
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
