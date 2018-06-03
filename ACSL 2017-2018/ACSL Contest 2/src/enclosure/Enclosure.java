package enclosure;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Enclosure {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String file = "inputFile.txt";
		Scanner reader = new Scanner(new File(file));
		while(reader.hasNextLine()) {
			String input1 = reader.nextLine();
			findMissing(input1);
		}
	}
	public static void findMissing(String input) {
		String locations = ""; 
		int countP = 0;
		int countB = 0;
		for(int i = 0; i < input.length(); i++) {
			if(input.charAt(i) == '(' || input.charAt(i) == ')') {
				countP++;
			}
			else if (input.charAt(i) == '[' || input.charAt(i) == ']') {
				countB++;
			}
		}
		///////CASE 1: BRACKETS PRESENT, 1 PARENTHESIS MISSING
		if (countP != 2 && countB == 2) {
			int start = input.indexOf('[');
			int end = input.indexOf(']', start);
			String section = input.substring(start, end+1);
			///CASE 1A: OPEN PARENTHESIS MISSING 
			if(input.indexOf(')') != -1) {
				int x;
				for(int i = start; i < input.indexOf(')') - 1; i++) {
					char character = input.charAt(i);
					char nextChar = input.charAt(i+1);
					char prevChar;
					if(i!= 0) {
						prevChar = input.charAt(i-1);
						if(Character.isDigit(character) == true && Character.isDigit(prevChar)== true) {
							i++;
			
						}
						else if(Character.isDigit(character) == true && (Character.isDigit(nextChar)== false || Character.isDigit(prevChar)== false)) {
							x = i;
							locations = locations + (x+1) + ", ";
						}
							
					}
				}
			}
			///CASE 1B: CLOSE PARENTHESIS MISSING
			if(input.indexOf('(') != -1) {
				int x;
				for(int i = input.indexOf('(') + 1; i < end+1; i++) {
					char character = input.charAt(i);
					char nextChar = input.charAt(i+1);
					char prevChar;
					if(i!= 0) {
						prevChar = input.charAt(i-1);
						if(Character.isDigit(character) == true && Character.isDigit(prevChar)== true) {
							i++;
			
						}
						if(i >= 3 && (input.charAt(i - 2) == '(' || input.charAt(i - 3) == '(')) {
							i++;
						}
						else if(Character.isDigit(character) == false && (Character.isDigit(nextChar)== true || Character.isDigit(prevChar)== true)) {
							x = i;
							locations = locations + (x+1) + ", ";
						}
							
					}
				}
			}
			if(input.charAt(input.length()-1) == ']') {
				locations = locations + (input.length()) + ", ";
			}

		}
		///////CASE 2: PARENTHESIS PRESENT, 1 BRACKET MISSING
		if(countB != 2 && countP == 2) {
			///CASE 2A: CLOSE BRACKET MISSING
			if(input.indexOf('[') != -1) {
				int x;
				int closeParen = input.indexOf(')');
				for(int i = closeParen; i < input.length(); i++) {
					char character = input.charAt(i);
					if(character == ')') {
						x = i+1;
						locations = locations + (x+1) + ", ";
					}
					else if (Character.isDigit(character) == true) {
						if(i != input.length()-1 && Character.isDigit(input.charAt(i+1)) == true ) {
							i=i;
						}
						else {
							x = i+1;
							locations = locations + (x+1) + ", ";
						}
						
					}
				}
			}
			///CASE 2B: OPEN BRACKET MISSING
			if(input.indexOf(']') != -1) {
				int x;
				int openParen = input.indexOf('(');
				for(int i = 0; i <= openParen; i++) {
					char character = input.charAt(i);
					if(character == '(') {
						x = i;
						locations = locations + (x+1) + ", ";
					}
					else if (Character.isDigit(character) == true) {
						if(i != 0 && Character.isDigit(input.charAt(i-1)) == true ) {
							i=i;
						}
						else {
							x = i;
							locations = locations + (x+1) + ", ";
						}
					}
					
				}
			}
		}
		///////CASE 3: ONLY BRACKET PRESENT OR ONLY PARENTHESIS PRESENT
		if((countB != 2 && countP == 0)||(countP != 2 && countB == 0)) {
			///CASE 3A: CLOSE BRACKET/PARENTHESIS MISSING
			if(input.indexOf('[') != -1 || input.indexOf('(') != -1) {
				int index = 0;
				if(input.indexOf('[') != -1) {
					index = input.indexOf('[');
				}
				else if(input.indexOf('(') != -1) {
					index = input.indexOf('(');
				}
				int x; 
				for(int i = index+1; i < input.length(); i++) {
					char prevChar = input.charAt(i-1);
					char character = input.charAt(i);
					if(Character.isDigit(character) == true && Character.isDigit(prevChar)== true) {
						i=i;
		
					}
					if(i >= 3 && ((input.charAt(i - 2) == '[' || input.charAt(i - 3) == '[' ) || (input.charAt(i - 2) == '(' || input.charAt(i - 3) == '(' ))) {
						i=i;
					}
					else if(Character.isDigit(character) == false && Character.isDigit(prevChar) == true) {
						x = i-1;
						locations = locations + (x+2) + ", ";
					}
				}
				locations = locations + (input.length()+1)+ ", ";
			}
			///CASE 3B: OPEN BRACKET/PARENTHESIS MISSING
			if(input.indexOf(']') != -1 || input.indexOf(')') != -1) {
				locations = locations + 1 + ", ";
				int index = 0;
				if(input.indexOf(']') != -1) {
					index = input.indexOf(']');
				}
				else if(input.indexOf(')') != -1) {
					index = input.indexOf(')');
				}
				int x; 
				for(int i = 0; i < index; i++) {
					char character = input.charAt(i);
					char nextChar = input.charAt(i+1);
					if(i != 0) {
						char prevChar = input.charAt(i-1);
						if(Character.isDigit(character) == true && Character.isDigit(prevChar)== true) {
							i=i;
			
						}
						if(i <= input.length()-3 && ((input.charAt(i + 2) == ']' || input.charAt(i + 3) == ']' ) || (input.charAt(i + 2) == ')' || input.charAt(i + 3) == ')' ))) {
							i=i;
						}
						else if(Character.isDigit(character) == false && Character.isDigit(nextChar) == true) {
							x = i+1;
							locations = locations + (x+1) + ", ";
						}
					}
					
				}
			}
			
		}
		String output = locations.substring(0, locations.length() - 2);
		System.out.println(output);
	}

}
