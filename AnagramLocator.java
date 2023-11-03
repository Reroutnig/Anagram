/**************************************************************/
/* Nishat Quyoum */
/* Student ID: 015087722 */
/* CS 3310, Fall 2023 */
/* Programming Assignment 2 */
/* Class for taking an input file and find the anagrams in the text file */
/**************************************************************/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AnagramLocator {
 
/************************************************************************************************/
/* Method: readWordsFromFile
/* Purpose: Reads words from a file and returns a list of words.
/* Parameters:
/*   String fileName: The name of the file to read words from.
/* Returns:
/*   List<String>: A list of words read from the file.
/* Throws:
/*   IOException: If an error occurs while reading the file.
/************************************************************************************************/
    public static List<String> readWordsFromFile(String fileName) throws IOException {
        List<String> wordsList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            // Read each line from the file and add it to the list after trimming and converting to lowercase
            while ((line = br.readLine()) != null) {
                wordsList.add(line.trim().toLowerCase());
            }
        }
        return wordsList;
    }
/************************************************************************************************/
/* Method: findAnagramGroups
/* Purpose: Finds anagram groups from a list of words.
/* Parameters:
/*   List<String> wordsList: A list of words to find anagram groups from.
/* Returns:
/*   Map<String, List<String>>: A map where keys are sorted anagrams and values are lists of anagrams.
/************************************************************************************************/
    public static Map<String, List<String>> findAnagramGroups(List<String> wordsList) {
        // Map to store anagram groups where keys are sorted anagrams and values are lists of anagrams
        Map<String, List<String>> anagramGroups = new HashMap<>();
        for (String word : wordsList) {
            // Convert word to char array, sort it, and create a string from the sorted characters
            char[] charArray = word.toCharArray();
            Arrays.sort(charArray);
            String sortedWord = new String(charArray);
            // Check if the sorted word is already a key in the map, if not, add a new key-value pair
            if (!anagramGroups.containsKey(sortedWord)) {
                anagramGroups.put(sortedWord, new ArrayList<>());
            }
            anagramGroups.get(sortedWord).add(word);
        }
        return anagramGroups;
    }
/************************************************************************************************/
/* Method: printAnagramGroups
/* Purpose: Prints the anagram groups found in the given map.
/* Parameters:
/*   Map<String, List<String>> anagramGroups: A map where keys are sorted anagrams and values are lists of anagrams.
/* Returns: None
/************************************************************************************************/
    public static void printAnagramGroups(Map<String, List<String>> anagramGroups) {
        for (List<String> anagramGroup : anagramGroups.values()) {
         // Iterate through the map values (lists of anagrams) and print groups with more than one word  
            if (anagramGroup.size() > 1) {
                System.out.println("Original word: " + anagramGroup.get(0));
                System.out.println("Anagrams: " + String.join(", ", anagramGroup.subList(1, anagramGroup.size())));
                System.out.println();
            }
        }
    }
/************************************************************************************************/
/* Method: main
/* Purpose: Entry point of the program. Reads a file, finds anagram groups, and prints the results.
/* Parameters:
/*   String[] args: Command-line arguments, expects a file name as input.
/* Returns: None
/************************************************************************************************/
    public static void main(String[] args) {
         // Check if a file name is provided as a command-line argument
        if (args.length < 1) {
            System.out.println("Usage: java AnagramDetector <filename>");
            return;
        }

        String fileName = args[0];
        try {
             // Read words from the file
            List<String> wordsList = readWordsFromFile(fileName);
            // Find anagram groups from the list of words
            Map<String, List<String>> anagramGroups = findAnagramGroups(wordsList);
            // Print the anagram groups
            printAnagramGroups(anagramGroups);
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
}

