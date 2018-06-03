package walk;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class walk {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String file = "inputFile.txt";
		Scanner reader = new Scanner(new File(file));
		String string1 = reader.nextLine();
		while(reader.hasNextLine()) {
			int[][] a = makeArray(string1);
			String input = reader.nextLine();
			walking(input, a);
		}
	}

	public static int[][] makeArray(String input) {
		String bin = "";
		String[] array = input.split(", ");
		int[][] array2 = new int[8][8];
		for(int i = 0; i < array.length; i++) {
			int x = Integer.parseInt(array[i], 16);
			bin = Integer.toBinaryString(x);
			while(bin.length() < 8) {
				bin = "0" + bin;
			}
			for (int j = 0; j < bin.length(); j++)
			{
				array2[i][j] = bin.charAt(j) - '0';
			}
		}
		return array2;
	}
	public static void walking(String input, int[][] a) {
		String[] inputList = input.split(", ");
		int Xpos = Integer.parseInt(inputList[0]);
		int Ypos = Integer.parseInt(inputList[1]);
		String direction = inputList[2];
		int moves = Integer.parseInt(inputList[3]);
		int x = 0;
		while(x < moves) {
			if(direction.equals("L")) {
				String[] info = left(a, Xpos, Ypos, moves, x);
				Xpos = Integer.parseInt(info[0]);
				Ypos = Integer.parseInt(info[1]);
				direction = info[2];
				x++;
			}
			else if(direction.equals("R")) {
				String[] info = right(a, Xpos, Ypos, moves, x);
				Xpos = Integer.parseInt(info[0]);
				Ypos = Integer.parseInt(info[1]);
				direction = info[2];
				x++;
			}

			else if(direction.equals("A")) {
				String[] info = above(a, Xpos, Ypos, moves, x);
				Xpos = Integer.parseInt(info[0]);
				Ypos = Integer.parseInt(info[1]);
				direction = info[2]; 
				x++;
			}
			else if(direction.equals("B")) {
				String[] info = below(a, Xpos, Ypos, moves, x);
				Xpos = Integer.parseInt(info[0]);
				Ypos = Integer.parseInt(info[1]);
				direction = info[2];
				x++;
			}

		}
		System.out.println(Xpos + "," + Ypos);
	}

	public static String[] left(int[][] a, int xValue, int yValue, int moves, int x) {
		xValue = xValue - 1;
		yValue = yValue - 1;
		int boxVal = a[xValue][yValue];
		int newX = 0;
		int newY = 0;
		String direction = "";
		if(boxVal == 0) {
			newX = xValue;
			newY = yValue + 1;
			if(newY > 7) {
				newY = 0;
			}
			x++;
			direction = "L";
		}
		else if (boxVal % 4 == 1) {
			newX = xValue - 1;
			newY = yValue;
			x++;
			if(newX < 0) {
				newX = 7;
			}
			a[xValue][yValue] = 2;
			direction = "B";
		}
		else if (boxVal % 4 == 2) {
			newX = xValue;
			newY = yValue + 1;
			x++;
			if(newY > 7) {
				newY = 0;
			}
			a[xValue][yValue] = 3;
			direction = "L";
		}
		else if (boxVal % 4 == 3) {
			newX = xValue + 1;
			newY = yValue;
			x++;
			if(newX > 7) {
				newX = 0;
			}
			a[xValue][yValue] = 4;
			direction = "A";
		}
		else if (boxVal % 4 == 0) {
			newX = xValue;
			newY = yValue - 1;
			x++;
			if(newY < 0) {
				newY = 7;
			}
			a[xValue][yValue] = boxVal++;
			direction = "R";
		}
		String [] infoOutput = {Integer.toString(newX + 1), Integer.toString(newY  + 1), direction};
		return infoOutput;
	}

	public static String[] right(int[][] a, int xValue, int yValue, int moves, int x) {
		xValue = xValue - 1;
		yValue = yValue - 1;
		int boxVal = a[xValue][yValue];
		int newX = 0;
		int newY = 0;
		String direction = "";
		if(boxVal == 0) {
			newX = xValue;
			newY = yValue - 1;
			x++;
			if(newY < 0) {
				newY = 7;
			}
			direction = "R";
		}
		else if (boxVal % 4 == 1) {
			newX = xValue + 1;
			newY = yValue;
			x++;
			if(newX > 7) {
				newX = 0;
			}
			a[xValue][yValue] = 2;
			direction = "A";
		}
		else if (boxVal % 4 == 2) {
			newX = xValue;
			newY = yValue - 1;
			x++;
			if(newY < 0) {
				newY = 7;
			}
			a[xValue][yValue] = 3;
			direction = "R";
		}
		else if (boxVal % 4 == 3) {
			newX = xValue - 1;
			newY = yValue;
			x++;
			if(newX < 0) {
				newX = 7;
			}
			a[xValue][yValue] = 4;
			direction = "B";
		}
		else if (boxVal % 4 == 0) {
			newX = xValue;
			newY = yValue + 1;
			x++;
			if(newY > 7) {
				newY = 0;
			}
			a[xValue][yValue] = boxVal++;
			direction = "L";
		}
		String [] infoOutput = {Integer.toString(newX + 1), Integer.toString(newY  + 1), direction};
		return infoOutput;
	}

	public static String[] above(int[][] a, int xValue, int yValue, int moves, int x) {
		xValue = xValue - 1;
		yValue = yValue - 1;
		int boxVal = a[xValue][yValue];
		int newX = 0;
		int newY = 0;
		String direction = "";
		if(boxVal == 0) {
			newX = xValue + 1;
			newY = yValue;
			x++;
			if(newX > 7) {
				newX = 0;
			}
			direction = "A";
		}
		else if (boxVal % 4 == 1) {
			newX = xValue;
			newY = yValue + 1;
			x++;
			if(newY > 7) {
				newY = 0;
			}
			a[xValue][yValue] = 2;
			direction = "L";
		}
		else if (boxVal % 4 == 2) {
			newX = xValue + 1;
			newY = yValue;
			x++;
			if(newX > 7) {
				newX = 0;
			}
			a[xValue][yValue] = 3;
			direction = "A";
		}
		else if (boxVal % 4 == 3) {
			newX = xValue;
			newY = yValue - 1;
			x++;
			if(newY < 0) {
				newY = 7;
			}
			a[xValue][yValue] = 4;
			direction = "R";
		}
		else if (boxVal % 4 == 0) {
			newX = xValue - 1;
			newY = yValue;
			x++;
			if(newX < 0) {
				newX = 7;
			}
			a[xValue][yValue] = boxVal++;
			direction = "B";
		}
		String [] infoOutput = {Integer.toString(newX + 1), Integer.toString(newY  + 1), direction};
		return infoOutput;
	}

	public static String[] below(int[][] a, int xValue, int yValue, int moves, int x) {
		xValue = xValue - 1;
		yValue = yValue - 1;
		int boxVal = a[xValue][yValue];
		int newX = 0;
		int newY = 0;
		String direction = "";
		if(boxVal == 0) {
			newX = xValue - 1;
			newY = yValue;
			x++;
			if(newX < 0) {
				newX = 7;
			}
			direction = "B";
		}
		else if (boxVal % 4 == 1) {
			newX = xValue;
			newY = yValue - 1;
			x++;
			if(newY < 0) {
				newY = 7;
			}
			a[xValue][yValue] = 2;
			direction = "R";
		}
		else if (boxVal % 4 == 2) {
			newX = xValue - 1;
			newY = yValue;
			x++;
			if(newX < 0) {
				newX = 7;
			}
			a[xValue][yValue] = 3;
			direction = "B";
		}
		else if (boxVal % 4 == 3) {
			newX = xValue;
			newY = yValue + 1;
			x++;
			if(newY > 7) {
				newY = 0;
			}
			a[xValue][yValue] = 4;
			direction = "L";
		}
		else if (boxVal % 4 == 0) {
			newX = xValue + 1;
			newY = yValue;
			x++;
			if(newX > 7) {
				newX = 0;
			}
			a[xValue][yValue] = boxVal++;
			direction = "A";
		}
		String [] infoOutput = {Integer.toString(newX + 1), Integer.toString(newY  + 1), direction};
		return infoOutput;
	}

}


