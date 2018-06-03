import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class duplicates {
	private static String original;
	private static String[] positionHist;
	private static String[] LETTERS;
	private static int[] COUNTS;
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String file = "inputFile.txt";
		Scanner reader = new Scanner(new File(file));
		while(reader.hasNextLine()) {
			String input = reader.nextLine();
			if(input.indexOf("RESET") != -1) {
				String str = input.substring(6);
				reset(str);
			}
			if(input.indexOf("REPORT") != -1) {
				int numb = Integer.parseInt(input.substring(7));
				System.out.println(report(numb));
			}
			if(input.indexOf("ADD") != -1) {
				String str2 = input.substring(4);
				add(str2);
			}
			if(input.indexOf("DELETE") != -1) {
				String str3 = input.substring(7);
				delete(str3);
			}
		}

	}
	public static String sorting(String str) {
		char[] chars = str.toCharArray();
        Arrays.sort(chars);
        String sorted = new String(chars);
        return sorted;
	}
	public static void reset(String original) {
		original = original.toUpperCase();
		original = original.replaceAll("[^a-zA-Z]", "");
		int length = original.length();
		positionHist = new String[length];
		char[] characters = original.toCharArray();
		int length2 = characters.length;
		for (int i = 0; i < length; i++) {
			for (int j = i + 1; j < length; j++) {
				if (characters[i] == characters[j]) {
					int temp = j;
					for (int k = temp; k < length - 1; k++) {
						characters[k] = characters[k + 1];
					}
					j--;
					length--;
				}
			}
		}
		String unsort= new String(characters);
		unsort = unsort.substring(0, length);
		String newstr = "";
		for(int i = 0; i < unsort.length(); i++) {
			positionHist[i] = "";
			char letter = unsort.charAt(i);
			if(newstr == "") {
				newstr = ""+letter;
				positionHist[i] = positionHist[i] + letter;
			}
			else {
				if(newstr.indexOf(letter) >= 0) {
					newstr = newstr;
				}
				else {
					newstr = sorting(newstr + letter);
					for(int x = 0; x < newstr.length(); x++) {
						if(positionHist[x].length() != 0 && positionHist[x].charAt(positionHist[x].length() - 1) == newstr.charAt(x)) {
							positionHist[x] = positionHist[x];
						}
						else {
							positionHist[x] = positionHist[x] + newstr.charAt(x);
						}
					}
				}
			}
		}
		LETTERS = newstr.split("");
		COUNTS = new int[LETTERS.length];
		for(int i = 0; i < unsort.length(); i++) {
			COUNTS[i] = original.length() - original.replace(LETTERS[i], "").length();
		}
	}

	public static String report(int index) {
		return positionHist[index - 1];
	}

	public static void add(String word) {
		word = word.toUpperCase();
		word = word.replaceAll("[^a-zA-Z]", "");
		for(int i = 0; i < word.length(); i++) {
			String letter = word.charAt(i)+"";
			if(Arrays.asList(LETTERS).contains(letter)) {
				int index = Arrays.asList(LETTERS).indexOf(letter);
				COUNTS[index]++;
			}
			else{
				//System.out.println(letter);
				//add cells in array
				String[] Letters2 = new String[LETTERS.length + 1];
				int[] count2 = new int[LETTERS.length + 1];
				for (int x = 0; x < LETTERS.length; x++)
				{
					Letters2[x] = LETTERS[x];
					count2[x] = COUNTS[x];
				}
				LETTERS = Letters2;
				COUNTS = count2;
				for(int x = 0; x < LETTERS.length; x++) {
					//if letter needs to be placed in the middle
					if(LETTERS[x] != null && letter.charAt(0) < LETTERS[x].charAt(0)){
						//shifts letters
						for(int y=LETTERS.length-1; y > x; y--){
							LETTERS[y] = LETTERS[y-1];
							COUNTS[y] = COUNTS[y-1];
						}
						LETTERS[x] = letter;
						COUNTS[x] = 1;
						for(int j = x; j < LETTERS.length; j++) {
							if(positionHist[j] != null) {
								positionHist[j] = positionHist[j] + LETTERS[j];
							}
							else {
								positionHist[j] = LETTERS[j];
							}

						}
						x = LETTERS.length;
					}
					else {
						LETTERS[LETTERS.length - 1] = letter;
						COUNTS[LETTERS.length - 1] = 1;
					}
				}
			}
			//System.out.println(COUNTS[0]);
		}
	}
	public static void delete(String word) {
		int length = LETTERS.length;
		word = word.toUpperCase();
		word = word.replaceAll("[^a-zA-Z]", "");
		for(int i = 0; i < word.length(); i++) {
			String letter = word.charAt(i)+"";
			if(Arrays.asList(LETTERS).contains(letter)) {
				int index = Arrays.asList(LETTERS).indexOf(letter);
				COUNTS[index]--;
				if(COUNTS[index] == 0) {
					for (int x = index; x < LETTERS.length-1; x++) {
						LETTERS[x] = LETTERS[x+1];
						COUNTS[x] = COUNTS[x+1];
					}
					String[] letter2 = new String[LETTERS.length - 1];
					int[] count2 = new int[COUNTS.length - 1];
					for(int y = 0; y < COUNTS.length - 1; y++) {
						letter2[y] = LETTERS[y];
						count2[y] = COUNTS[y];
					}
					
					LETTERS = letter2;
					COUNTS = count2;
					String lastLetter = positionHist[index].substring(positionHist[index].length() - 1);
					for(int y = index; y < LETTERS.length; y++) {
						if(LETTERS[y] == lastLetter) {
							positionHist[y] = positionHist[y];
						}
						else {
							positionHist[y] = positionHist[y] + LETTERS[y];
						}
					}

				}
			}
		}
	}

}
