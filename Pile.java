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
public class Pile {
	private int counter;//The number of in ONE pile
	
	public Pile(int size){
		
		counter = size;
		
	} //Initial constructor with the starting counter size
	
	
	
	public void remove(int x){
		
		counter = counter - x;
		
	} //Number of counters to remove from the pile, is this number allowed?
	
	
	public boolean isEmpty(){
		
		if(counter>0){
			
			return false;
			
		} 
		
		else {
			
			return true;
			
		}
	} //Is the pile empty?
	
	
	
	public void displayCounter(){
		
		System.out.print("      ");
		
		System.out.print(counter);
		
	} //Print the current number of counters in this pile
	
	
	public int getSize(){
		
		return counter;
		
	} //Return the number of counters in this pile
}
