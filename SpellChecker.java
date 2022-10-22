import java.util.ArrayList;
import java.util.Scanner;

/**
 *  Class to check the spelling of the input texts. Contain two modes of checking:
 *  1) input several words to check each word; 2) input a file and check each 
 *  word in the file.
 *  @author Wantong Wu
 *  @version Fall 2022
 *  References: 
 *        - https://www.tutorialspoint.com/check-if-the-string-contains-only 
 *          -unicode-letters-in-java
 */
public class SpellChecker {
  
  /** Check the Spelling of input texts */
  public static void main(String[] args) {
    SpellDictionary dict = new SpellDictionary("words.txt");
    
    if (args.length == 0) {
      // Create Scanner and add the words in file to HashSet
      try {
        Scanner input = new Scanner(System.in);
        
        // Create arraylist for storing wrong words
        ArrayList<String> wrongWords = new ArrayList<>();
        while (input.hasNext()) {
          // Read the next word
          String word = input.next();

          // Check if the word contains only alphabets
          for (int i = 0; i < word.length(); i++) {
            if (Character.isLetter(word.charAt(i)) == false) {
              word = word.replace(Character. toString(word.charAt(i)), "");
            }
          }

          // Check for wrongly spelt words and provide suggestions
          if (!dict.containsWord(word) && !wrongWords.contains(word)) {
            wrongWords.add(word);
            suggestWrong(dict, word); // Function see below
          }
        }
      } catch (Exception e) {
        System.out.println("No File Located");
      }
    } else {
      for (String s: args) {
        if (dict.containsWord(s)) {
          System.out.println("\"" + s + "\" is spelt correctly.");
        } else {
          suggestWrong(dict, s);
        }
      }
    }
  }

  /** Print out the suggestions for the wrongly spelt words.
   *  @param dict SpellDictionary instance that contains dictionary
   *  @param word The wrongly spelt word to be checked
   */
  public static void suggestWrong(SpellDictionary dict, String word) {
    ArrayList<String> closeWords = dict.nearMisses(word);
    System.out.println("Not found: " + word);
    System.out.print("\t Suggestions:");
    for (String miss: closeWords) {
      System.out.print(" " + miss);
    }
    System.out.println();
  }
  
}