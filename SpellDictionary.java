import java.util.HashSet;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *  Class to store a spelling dictionary using HashSet. Provide methods that 
 *  check if the HashSet contains certain words, and find near misses of a 
 *  wrongly spelt word in the dictionary.
 *  @author Wantong Wu
 *  @version Fall 2022
 *  References: 
 *        - https://www.digitalocean.com/community/tutorials/
 *          java-remove-character-string
 *        - https://www.geeksforgeeks.org
 *          /swapping-pairs-of-characters-in-a-string-in-java/
 */
public class SpellDictionary {
  /** Stores the dictionary words */
  private HashSet<String> words;

  /** 
   *  Constructor sets up the set of words
   *  @param filename The word list file
   */
  public SpellDictionary(String filename) {
    // Create a new HashSet and assign it to attribute words
    words = new HashSet<>();

    // Add the words in file to HashSet
    try {
      Scanner input = new Scanner(new File(filename));
      while (input.hasNextLine()) {
        String word = input.nextLine();
        word = word.toLowerCase();
        words.add(word);
      }
    } catch (FileNotFoundException e) {
      System.out.println("File Not Located");
    }
  }

  /** 
   *  Check whether the dictionary contains word of a particular spelling
   *  @param word The word that the method searches in dictionary
   *  @return T/F: if the dictionary has the word
   */
  public boolean containsWord(String word) {
    word = word.toLowerCase();
    return this.words.contains(word);
  }
  
  /** 
   *  Find the near misses of the word if it is spelt wrongly
   *  @param word The wrongly spelt word, target for near misses search
   *  @return ArrayList<String> arraylist that contains all possible near misses
   */
  public ArrayList<String> nearMisses(String word) {
    // Create arraylist for storing words
    ArrayList<String> nearWords = new ArrayList<>();

    // Prepare for search
    word = word.toLowerCase();
    String miss = word;
    String alphebet = "abcdefghijklmnopqrstuvwxyz";

    // Construct possible near misses and check them in the dictionary
    for (int i = 0; i < word.length(); i++) {
      // Deletions
      miss = word.substring(0, i) + word.substring(i+1);
      if (!nearWords.contains(miss) && containsWord(miss)) {
          nearWords.add(miss);
      }
      
      for (int j = 0; j < alphebet.length(); j++) {
        // Insertions
        int m = i;
        miss = word.substring(0, m) + String.valueOf(alphebet.charAt(j)) 
                + word.substring(m);
        if (!nearWords.contains(miss) && containsWord(miss)) {
          nearWords.add(miss);
        }
        if (i == word.length() - 1) {
          m = i + 1;
          miss = word.substring(0, m) + String.valueOf(alphebet.charAt(j)) 
                + word.substring(m);
          if (!nearWords.contains(miss) && containsWord(miss)) {
            nearWords.add(miss);
          }
          m = i;
        }

        // Substitutions
        miss = word.substring(0, m) + String.valueOf(alphebet.charAt(j)) 
                + word.substring(m + 1);
        if (!nearWords.contains(miss) && containsWord(miss)) {
          nearWords.add(miss);
        }
      }
    }

    for (int i = 0; i < word.length() - 1; i++) {
      // Transpositions
      char[] ch = word.toCharArray();
      char temp = ch[i];
      ch[i] = ch[i + 1];
      ch[i + 1] = temp;
      miss = new String(ch);
      if (!nearWords.contains(miss) && containsWord(miss)) {
          nearWords.add(miss);
      }

      // Splits
      miss = word.substring(0, i + 1) + " " + word.substring(i + 1);
      if (!nearWords.contains(miss) && containsWord(word.substring(0, i + 1)) 
          && containsWord(word.substring(i + 1))) {
          nearWords.add(miss);
      }
    }
    
    return nearWords;
  }
}