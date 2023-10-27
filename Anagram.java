import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Anagram {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java AnagramDetector <filename>");
            return;
        }

        String fileName = args[0];
        Map<String, List<String>> anagramGroups = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String word = line.trim();
                char[] charArray = word.toLowerCase().toCharArray();
                Arrays.sort(charArray);
                String sortedWord = new String(charArray);

                if (!anagramGroups.containsKey(sortedWord)) {
                    anagramGroups.put(sortedWord, new ArrayList<>());
                }
                anagramGroups.get(sortedWord).add(word);
            }

            for (List<String> anagramGroup : anagramGroups.values()) {
                if (anagramGroup.size() > 1) {
                    System.out.println("Original word: " + anagramGroup.get(0));
                    System.out.println("Anagrams: " + String.join(", ", anagramGroup.subList(1, anagramGroup.size())));
                    System.out.println();
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
}
