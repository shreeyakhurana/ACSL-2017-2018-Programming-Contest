package nintynine;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class NinetyNine {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		String file = "inputFile.txt";
		Scanner reader = new Scanner(new File(file));
		//takes input lines from file 
		while(reader.hasNextLine()) {
			String input1 = reader.nextLine();
			String[] array = makeArray(input1);
			int[] newArray = conversions(array);
			play(newArray);
		}
	}
	
	//Changes string input to an array
	public static String[] makeArray(String input) {
		String[] array = input.split(", ");
		return array;
	}
	
	//converts string array to an integer array and converts "letter" cards to numbers
	public static int[] conversions(String[] array) {
		int[] newArray = new int[array.length];
		for(int i = 0; i < array.length; i++) {
			//T to 10
			if (array[i].equals("T")) {
				newArray[i] = 10;
			}
			//J to 11
			else if (array[i].equals("J")) {
				newArray[i] = 11;
			}
			//Q to 12
			else if (array[i].equals("Q")) {
				newArray[i] = 12;
			}
			//K to 13
			else if (array[i].equals("K")) {
				newArray[i] = 13;
			}
			//A to 14
			else if (array[i].equals("A")) {
				newArray[i] = 14;
			}
			//anything else to an integer
			else {
				newArray[i] = Integer.parseInt(array[i]);
			}
		}
		return newArray;
	}
	
	//emulates actual game
	public static void play(int[] array) {
		//assigns initial total
		int total = array[0];
		//assigns initial player cards
		int[] card = new int[3];
		for(int i = 1; i < 4; i++) {
			card[i - 1] = array[i];
		}
		//initializes flag to tell whether current player is player or dealer
		int flag = 0;
		int counter = 4;
		
		//iterates until total is greater than 99 and game is over
		while(total <= 99) {
			
			//finds largest card for player to throw
			int min = card[0];
			int maxCard = card[0];
			for (int i = 0; i < card.length; i++) {
				if(card[i] < min) {
					min = card[i];
				}
				else if (card[i] > maxCard) {
					maxCard = card[i];
				}
			}
			
			//checks if the card is a special action card (T, A, or 9)
			if(maxCard == 10) {
				total -= 10;
			}
			else if((total + 14 > 99) && maxCard == 14  ) {
				total += 1;
			}
			else if(maxCard == 9) {
				total = total;
			}
			//if not any special card, simply adds value to total
			else {
				total += maxCard;
			}
			//player just threw a card
			flag = 1;
			
			//finds index of the card just thrown
			int index = 0;
			for (int i = 0; i < card.length; i++) {
				if(card[i] == maxCard) {
					index = i;
					i = card.length;
				}
			}
			//replaces card thrown with new card from pile
			card[index] = array[counter];
			counter++;
			
			//checks if the total is still under 99 and continues playing
			if(total <= 99) {
				//gets dealers card
				int dealerCard = array[counter];
				//checks is card is a special action card (T, A, or 9)
				if(dealerCard == 10) {
					total -= 10;
				}
				else if(dealerCard == 14 && (total + 14 > 99)) {
					total += 1;
				}
				else if(dealerCard == 9) {
					total = total;
				}
				//if not, simply adds to value to total
				else {
					total += dealerCard;
				}
				//dealer just threw a card
				flag = 0;
			}
			counter++;
		}
		String output = "";
		//checks if a certain player caused the total to go over 99 - the winner is that player's opponent
		if(flag == 1) {
			output = "dealer";
		}
		else {
			output = "player";
		}
		//output
		System.out.println(total + ", " + output);
	}
}
