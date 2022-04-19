package sortingAlgorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class writeFiles {
	public static void main(String[] args) {
		File fileName = new File("FileData.csv"); //grabs the file from specific location
		ArrayList<Integer> dataOne = new ArrayList<>(); //temp to hold data for merge sort
		ArrayList<Integer> dataTwo = new ArrayList<>();//temp to hold data for quick sort
		ArrayList<Integer> dataThree = new ArrayList<>(); //temp to hold data for heap sort
		List<String> allFiles = new ArrayList<>(); // holds the names of the 270 files
		allFiles = Main.makefileArray();
		
		try(
			//write to the csv file to hold the time data
			PrintWriter writer = new PrintWriter(fileName)){
			StringBuilder build = new StringBuilder();
			
			//First Row which will be the header
			build.append("File Name"); 
			build.append(',');
			build.append("Merge Sort Time");
			build.append(',');
			build.append("Quick Sort Time");
			build.append(',');
			build.append("Heap Sort Time");
			build.append('\n');
			
			//Walks through the array with all the names of the files
			for(int i = 0; i < allFiles.size(); i++) {
				//writes all the name of the files under the first column, File Name
				build.append(allFiles.get(i));
				build.append(',');

				//write all the merge sort times under the second column, Merge Sort Time
				dataOne = Main.ReadFile(allFiles.get(i));
				long startOne = System.currentTimeMillis();
				mergeSort(dataOne);
				long endOne = System.currentTimeMillis();
				//System.out.println("finished merge file: " + allFiles.get(i));
				build.append(endOne-startOne); //the run time for merge sort 
				build.append(',');
				
				//write all the quick sort time under the third column, Quick Sort Time
				dataTwo = Main.ReadFile(allFiles.get(i));
				long startTwo = System.currentTimeMillis();
				quickSort(dataTwo);
				long endTwo = System.currentTimeMillis();
				//System.out.println("finished quick file: " + allFiles.get(i));
				build.append(endTwo-startTwo);//the run time for quick sort
				build.append(',');
				
				//write all the heap sort times under the fourth column, Heap Sort Time
				dataThree = Main.ReadFile(allFiles.get(i));
				long startThree = System.currentTimeMillis();
				heapSort(dataThree);
				long endThree = System.currentTimeMillis();
				//System.out.println("finished heap file: " + allFiles.get(i));
				build.append(endThree-startThree); //the run time for heap sort
				build.append('\n');
			}
			
			//write the data to the csv file
			writer.write(build.toString());
			
			System.out.println("done!"); //test to see when it should be done with all sorting
			
			writer.close();
		
			//will throw an exception for problems
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	//Sorts the given array "info" by merge sort
	//this method will keep breaking the array down till it has size one
	//then will call merge to put the list back together in a sorted order
	static void mergeSort(ArrayList<Integer> info) {
		ArrayList<Integer> tempB = new ArrayList<>();
		ArrayList<Integer> tempC = new ArrayList<>();
				
		if(info.size() > 1) { //list needs to have more than one element in it
			int median = (int)Math.floor(info.size()/2); //finds the median for the upper and lower bound problem
					
			//fills our first temp array with data
			for(int i = 0; i <= median - 1; i++) {
				tempB.add(info.get(i));
			}
			//fills second temp array with values
			for(int j = median; j < info.size(); j++ ){
				tempC.add(info.get(j));
			}
					
			//call method again to further break down arrays
			mergeSort(tempB);
			mergeSort(tempC);
			merge(tempB, tempC, info); //put elements of the array back in sorted order
		}
				
		//checks to see if array is sorted
//		for(int a = 0; a < info.size() -1; a++) {
//			if(info.get(a) > info.get(a + 1)) {
//				System.out.println("Error!");
//			}
//		}
				
	}
	
	//merges all the arrays back together in a sorted order
	private static void merge(ArrayList<Integer> tempB, ArrayList<Integer> tempC, ArrayList<Integer> tempA) {
		//counter variables for storing elements into the three different arrays
		int i = 0;
		int j = 0;
		int k = 0;
				
		while((i < tempB.size()) && (j < tempC.size())) {
			if(tempB.get(i) <= tempC.get(j)) {
				tempA.set(k, tempB.get(i));
					i++;
			}else {
				tempA.set(k, tempC.get(j));
				j++;
			}
			k++;
		}
		if(i == tempB.size()) {
			while(j < tempC.size()) {
				tempA.set(k, tempC.get(j));
				j++;
				k++;
			}
		}else {
			while(i < tempB.size()) {
				tempA.set(k, tempB.get(i));
				i++;
				k++;
			}
		}
	}
			
	//Will sort a subarray by quicksort
	public static void quickSort(List<Integer> list) {
		Integer leftPoint = 0; //l or left most point of array
		Integer rightPoint = list.size(); //r or right most point of array
		Integer splitPositionValue; // s or split the array by the pointer value
				
		if(leftPoint < rightPoint){
			//finds the middle value
			int median = (int)Math.floor(list.size()/2);
			int middleValue = list.get(median);
				
			//sets the split position for further sorting
			splitPositionValue = threeWayPartition(list, middleValue);
					
			//call function again to further the left and right side of the split value
			quickSort(list.subList(leftPoint, splitPositionValue));
			quickSort(list.subList(splitPositionValue + 1, rightPoint));
					
		}

		//checks to see if everything is sorted 
		//for(int a = 0; a < list.size() -1; a++) {
		//if(list.get(a) > list.get(a + 1)) {
		//	System.out.println("Error!"); // print Error if values are not sorted
		//}
		//}
	}
			
	//three way partition from link provided in assignment details
	public static Integer threeWayPartition(List<Integer> list, Integer midValue) {
		int i = 0;
		int j = 0; 
		int k = list.size() - 1;//rightPoint
				
		while(j <= k) {
			//finds if the value is less than the middle value and swaps
			if(list.get(j) < midValue) {
				Collections.swap(list, i, j);
				i++;
				j++;
				//finds if the value is greater than the middle value and swaps
			}else if(list.get(j) > midValue) {
				Collections.swap(list, j, k);
				k--;
			}else {//if value is equal to middle value
				j++;
			}
		}
		return k; //returns the splitValue
	}

			
	//Will transfer the array to a heap
	//Swaps the root with the last position and decrements the array to reheap the new array
	//It is recursive so in the end you will end up with a sorted heap
	public static List<Integer> heapSort(List<Integer> content) {
		content = heapBottomUp(content);
				
		for(int i = content.size() - 1; i >= 1; i--) {
			Collections.swap(content, i, 0);
					heapem(content.subList(0, i), i, 0); //will not heap all the way down the heap
			}
		
		//checks to see if everything is sorted 
//		for(int a = 0; a < content.size() -1; a++) {
//			if(content.get(a) > content.get(a + 1)) {
//				System.out.println("Error!"); // print Error if values are not sorted
//			}
//		}
		return content;
	}
			
	//construct a heap from elements of a given array
	//referred to the textbook
	public static List<Integer> heapBottomUp(List<Integer> list) {
		for(int i = (int)Math.floor((list.size())/2);  i >= 1; i--) {
			int k = i - 1; //added minus one so it wont walk off the array
			int v = list.get(k);
			boolean heapCheck = false;
			while((heapCheck == false) && ((2 * k) < list.size())){
				int j = 2 * k;
				if(j < list.size() -1) {
					if(list.get(j) < list.get(j +1)) {
						j++;
					}
				}
				if(v >= list.get(j)) {
					heapCheck = true;
				}else {
					list.set(k, list.get(j));
					k=j;
				}
			}
			list.set(k, v);
		}
		return list;
	}
	
	//Similar to Heap bottom Up but will not run through the whole heap
	public static List<Integer> heapem(List<Integer> list, int last, int front){
		int max = front;
		int leftPoint = 2 * front + 1;
		int rightPoint = 2 * front + 2;
				
		if((leftPoint < last) && (list.get(leftPoint) > list.get(max))){
			max = leftPoint;
		}
		if((rightPoint < last) && (list.get(rightPoint) > list.get(max))) {
			max = rightPoint;
		}
		if(max != front) {
			Collections.swap(list, front, max);
			heapem(list, last, front);
		}
		return list;
	}

}
