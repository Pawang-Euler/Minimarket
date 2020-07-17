package org.pawaneuler.DataTypes.Rule;

// Java program to print all combination of size r in an array of size n 
// Modificate by Marissa Nur Amalia
// 11 July 2020

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List; 

public class Combinations { 

	/* arr[] ---> Input Array 
	data[] ---> Temporary array to store current combination 
	start & end ---> Staring and Ending indexes in arr[] 
	index ---> Current index in data[] 
	r ---> Size of a combination to be printed */
	public static void combinationUtil(ArrayList<ArrayList<String>> result, List<String> candidate, String data[], int start, 
								int end, int index, int r) 
	{ 
		// Current combination is ready to be printed, print it 
		if (index == r) 
		{
            ArrayList<String> temp = new ArrayList<String>();
            for (int j=0; j<r; j++)
                temp.add(data[j]); 
			result.add(temp); 
			return; 
		} 

		// replace index with all possible elements. The condition 
		// "end-i+1 >= r-index" makes sure that including one element 
		// at index will make a combination with remaining elements 
        // at remaining positions
        int i = 0;
        for (Iterator<String> iter = candidate.iterator(); iter.hasNext(); ) {
            String string = iter.next();
            if (i >= start && i <= end && (end - i + 1 >= r - index) ) {
                data[index] = string;
                combinationUtil(result, candidate, data, i+1, end, index+1, r); 
			}
			i++;
            
        }
    }
        
	// The main function that prints all combinations of size r 
	// in arr[] of size n. This function mainly uses combinationUtil() 
	public static ArrayList<ArrayList<String>> getCombination(ArrayList<String> candidate, int r) 
	{ 
		// A temporary array to store all combination one by one 
		String data[] = new String[r];
		int n = candidate.size(); 
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		// get all combination using temprary array 'data[]' 
		combinationUtil(result,candidate, data, 0, n-1, 0, r); 
		return result;
	} 

    public static ArrayList<ArrayList<String>> getAllCombination(ArrayList<String> candidate) {
		
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		for (int i = 1; i < candidate.size(); i++) {
			result.addAll(getCombination(candidate, i));
		}
		return result;
	}
} 

/* This code is contributed by Devesh Agrawal */

