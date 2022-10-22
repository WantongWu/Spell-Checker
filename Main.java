import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/** 
 * Assignment 3 contains 3 phases. The first 2 phases build a dictionary using 
 * HashSet and check wrongly spelt words based on the dictionary. The third phase
 * compares the running time between HashSet and ArrayList.
 * @author  Wantong Wu
 * @version Fall 2022
 * @since   2022/09/26
 * References: N/A
 */

public class Main {
  public static void main(String[] args) {
    SpellDictionary dict = new SpellDictionary("words.txt");

    // test SpellDictionary class methods
    testSpellDictionary(dict);

    // Compare the running time of the two dictionaries
    Timer testTimer = new Timer();

    // Check dictionaryV1 (HashSet)
    SpellDictionary dictV1 = new SpellDictionary("words.txt");
    try {
      Scanner input = new Scanner(new File("words.txt"));
      testTimer.start();
      while (input.hasNextLine()) {
        String word = input.nextLine();
        dictV1.containsWord(word);
      }
      double timeV1 = testTimer.stop();
      System.out.println("HashSet Version SpellDictionary Running Time: " 
                         + timeV1);
    } catch (FileNotFoundException e) {
      System.out.println("File Not Located");
    }
    
    // CAREFUL RUNNING CODES BELOW (line 43-57) - RUNNING TIME APPROX 18 secs
    // Check dictionaryV2 (SimpleSet)
    SpellDictionaryV2 dictV2 = new SpellDictionaryV2("words.txt");
    try {
      Scanner input = new Scanner(new File("words.txt"));
      testTimer.restart();
      while (input.hasNextLine()) {
        String word = input.nextLine();
        dictV2.containsWord(word);
      }
      double timeV2 = testTimer.stop();
      System.out.println("SimpleSet Version SpellDictionary Running Time: " 
                         + timeV2);
    } catch (FileNotFoundException e) {
      System.out.println("File Not Located");
    }
  }

  /** Test if the SpellDictionary class works correctly
   *  @param dict an instance of SpellDictionary class
   */
  public static void testSpellDictionary(SpellDictionary dict) {
    TestCode.beginTest("Test SpellDictionary");
    TestCode.subTest("Deletion", dict.nearMisses("xe").contains("axe"));
    TestCode.subTest("Insertion", dict.nearMisses("cring").contains("cringe"));
    TestCode.subTest("Substitution", 
                     dict.nearMisses("CHAxacteR").contains("character"));
    TestCode.subTest("Transposition", 
                     dict.nearMisses("mono").contains("moon"));
    TestCode.subTest("Split", 
                     dict.nearMisses("bluecheese").contains("blue cheese"));
    TestCode.concludeTest();
  }
}