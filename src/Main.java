import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            File file = new File("./src/Dictionary.txt");
            List<String> words = Files.readAllLines(Paths.get(file.getCanonicalPath()));

            long startTime = System.currentTimeMillis();
            List<AnagramCounter> anagramResults = new AnagramService().compute(words);
            long endTime = System.currentTimeMillis();

            System.out.println();
            System.out.println("Anagram Results (completed in " + (endTime - startTime) + " ms):");
            System.out.println();

            for (AnagramCounter anagramCounter : anagramResults) {
                System.out.println("Words with the character length of " + anagramCounter.getWordLength() +
                        " had " + anagramCounter.getCount() + " anagrams");
            }
        } catch (Exception e) {
            System.out.println("The following exception occurred:");
            System.out.println(e.getMessage());
        }
    }
}
