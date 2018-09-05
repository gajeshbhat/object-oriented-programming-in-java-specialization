package spelling;

import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * A class that implements the Dictionary interface using a LinkedList
 *
 */
public class DictionaryLL implements Dictionary 
{

	LinkedList<String> dict= new LinkedList<String>();
 

    /** Add this word to the dictionary.  Convert it to lowercase first
     * for the assignment requirements.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
    public boolean addWord(String word) {
    	String lowerCaseWord = word.toLowerCase();
    	boolean isAlradyExist = false;
    	
    	if(isWord(lowerCaseWord)) {
    		isAlradyExist = true;
    	}
    	
    	if(!(isAlradyExist)) {
    		return dict.add(lowerCaseWord);
    	}
    	return false;
  }


    /** Return the number of words in the dictionary */
    public int size()
    {
       return dict.size();
    }

    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) {
    	String lowerCaseWord = s.toLowerCase();
    	boolean isExist = false;
    	
    	if(!(lowerCaseWord.equals(null))) {
    		isExist = dict.contains(lowerCaseWord);
    	}
    	
    	if(isExist) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }

    
}
