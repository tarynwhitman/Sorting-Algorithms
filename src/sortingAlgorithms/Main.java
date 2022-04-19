package sortingAlgorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static ArrayList<String> makefileArray(){
		//Creates an Array List with all 270 different files 
		//Used for sorting later
		ArrayList<String> allFiles = new ArrayList<>();
			
		//runs through all files to make sure they all run through merge sort properly
		for(int i = 0; i < 270; i++) {
			for(int a = 1; a<= 30; a++) {
				allFiles.add(i, ("ReverseSorted" + a + "_" + 10000 + ".txt"));
				i++;
			}
			for(int a = 1; a<= 30; a++) {
				allFiles.add(i, ("ReverseSorted" + a + "_" + 100000 + ".txt"));
				i++;
			}
			for(int a = 1; a<= 30; a++) {
				allFiles.add(i, ("ReverseSorted" + a + "_" + 1000000 + ".txt"));
				i++;
			}
			for(int a = 1; a<= 30; a++) {
				allFiles.add(i, ("Unsorted" + a + "_" + 10000 + ".txt"));
				i++;
			}
			for(int a = 1; a<= 30; a++) {
				allFiles.add(i, ("Unsorted" + a + "_" + 100000 + ".txt"));
				i++;
			}
			for(int a = 1; a<= 30; a++) {
				allFiles.add(i, ("Unsorted" + a + "_" + 1000000 + ".txt"));
				i++;
			}
			for(int a = 1; a<= 30; a++) {
				allFiles.add(i, ("Sorted" + a + "_" + 10000 + ".txt"));
				i++;
			}
			for(int a = 1; a<= 30; a++) {
				allFiles.add(i, ("Sorted" + a + "_" + 100000 + ".txt"));
				i++;
			}
			for(int a = 1; a<= 30; a++) {
				allFiles.add(i, ("Sorted" + a + "_" + 1000000 + ".txt"));
				i++;
			}
				
		}
		return allFiles;
	}
	
	//read in file that was just created and stores the contents of file
	//into an array list for further use
	public static ArrayList<Integer> ReadFile(String fileName) throws FileNotFoundException{
		File file = new File("..\\algoHomeworkAssignment\\" + fileName); //grabs the file from specific location
		ArrayList<Integer> data = new ArrayList<>(); //used to store the data from the file
			
		//Scans in the data from the file
		Scanner reader = new Scanner(file);
			
		//stores the content from the file into the data array
		while (reader.hasNext()) {
			data.add(reader.nextInt());
		}
		reader.close(); //always close scanning
		return data; //return the contents of the file
	}

}
