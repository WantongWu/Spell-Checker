import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/** 
 * Check spelling of a word, several words, or words in a file through SpellChecker.java. 
 * Main.java contains testers.
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
