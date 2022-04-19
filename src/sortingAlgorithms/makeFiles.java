package sortingAlgorithms;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class makeFiles {
	private static File file;
	public static ArrayList<Integer> AllFiles = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		//Generates all files
		//Type 1: Unsorted
		//Type 2: Sorted
		//Type 3: Reverse Sorted
		makeSomeFiles(10000, 1); //Unsorted Small Files
		System.out.println("Unsorted 10,000 created!");
		makeSomeFiles(100000, 1); // Unsorted Medium Files
		System.out.println("Unsorted 100,000 created!");
		makeSomeFiles(1000000, 1); //Unsorted Large Files
		System.out.println("Unsorted 1,000,000 created!");
		
		makeSomeFiles(10000, 2); //Sorted Small Files
		System.out.println("Sorted 10,000 created!");
		makeSomeFiles(100000, 2); // Sorted Medium Files
		System.out.println("Sorted 100,000 created!");
		makeSomeFiles(1000000, 2); //Sorted Large Files
		System.out.println("Sorted 1,000,000 created!");
		
		makeSomeFiles(10000, 3); //ReversedSorted Small Files
		System.out.println("Reversed Sorted 10,000 created!");
		makeSomeFiles(100000, 3); //ReversedSorted Medium Files
		System.out.println("Reversed Sorted 100,000 created!");
		makeSomeFiles(1000000, 3); //ReversedSorted Large Files
		System.out.println("Reversed Sorted 1,000,000 created!");	
	}
	
	//Makes the Files of Small, Medium, and Large, depending on size, that are Unsorted(1),
	//Sorted(2), and Reserved Sort(3)
	public static void makeSomeFiles(int size, int type) throws IOException {
		for(int i = 1; i < 31; i++) {
			try {
				if(type == 1) {
					file = new File("Unsorted" + i + "_" + size + ".txt");
					fillFiles(size, type);
				}else if(type == 2) {
					file = new File("Sorted" + i + "_" + size + ".txt");
					fillFiles(size, type);
				}else if(type == 3) {
					file = new File("ReverseSorted" + i + "_" + size + ".txt");
					fillFiles(size, type);
				}else {
					System.out.println("Nothing should get to here");
				}
				
			} catch(IOException e){
				System.out.println("Something went wrong :(");
				e.printStackTrace();
			}
		}
		
	}
	
	//Fills the Created Files with random numbers
	public static void fillFiles(int size, int type) throws IOException {
		FileWriter writeFiles = new FileWriter(file);
		ArrayList<Integer> content = new ArrayList<>();
		
		content = makeContent(size);
		
		//if type 2 content will get sorted
		//if type 3 content will got reversed sorted
		if(type == 1) {
		}else if(type == 2) {
			Collections.sort(content);
		}else if(type == 3) {
			Collections.sort(content, Collections.reverseOrder());
		}else {
			System.out.println("File has no content");
		}
		
		//writes content to file
		for(int i = 0; i <content.size(); i++) {
			writeFiles.write(content.get(i) + "\n");
			//writeFiles.write((i+1) + ": " + content.get(i) + "\n");
		}
		writeFiles.close();
	}
	
	//Creates and returns an array list of random number form 0 to 9,999
	//Used as contents of the files
	public static ArrayList<Integer> makeContent(int size) {
		int min = 0;
		int max = 10000;
		ArrayList<Integer> content = new ArrayList<>();
		
		for(int i = 0; i < size; i++) {
			content.add((int) (Math.random()*(max - min))+min);
		}
		return content;
	}

}
