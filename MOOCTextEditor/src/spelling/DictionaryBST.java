package spelling;

import java.util.TreeSet;

/**
 * @author UC San Diego Intermediate MOOC team
 *
 */
public class DictionaryBST implements Dictionary 
{
   private TreeSet<String> dict = new TreeSet<String>();
	
    // TODO: Implement the dictionary interface using a TreeSet.  
 	// You'll need a constructor here
	
    
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
