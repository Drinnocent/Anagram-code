import java.io.IOException;
import java.util.*;

public class AnagramService {
    private  List<TreeSet<String>> wordsList=new ArrayList<>();
    private  List<List<String>> sortedWords=new ArrayList<>() ;

    public List<AnagramCounter> compute(List<String> words) throws IOException {
         addUniqueWords(words);
         sortWords(wordsList);


        //Counting Anagrams and storing them in the
        List<AnagramCounter> anagrams = new ArrayList<>();
        int countAnagrams = 0;
        int anagramLength = 0;
        for (List<String> word : sortedWords) {
            List<String> alreadyExist = new ArrayList<>();
            for (String w : word) {
                if (Collections.frequency(word, w) > 1 && !alreadyExist.contains(w)) { //check frequency and avoid repetio
                    countAnagrams++;
                    anagramLength = w.length();
                    alreadyExist.add(w);
                }
            }

            if (countAnagrams != 0) {
                anagrams.add(new AnagramCounter(anagramLength, countAnagrams));
                countAnagrams = 0;
                anagramLength = 0;
            }

        }
        return anagrams;

    }

    //this method store words of same length but all sorted
    public void sortWords(List<TreeSet<String>> wordsList) {
        for (TreeSet<String> word : wordsList) {
            List<String> sortedCharacters = new ArrayList<>(); //sort each each string using character array
            for (String w : word) {
                char[] tempArray = w.toCharArray();
                Arrays.sort(tempArray);
                sortedCharacters.add(String.copyValueOf(tempArray));
            }
            sortedWords.add(sortedCharacters);
        }

    }

    public void addUniqueWords(List<String> words) {
        List<Integer> wordLengths = new ArrayList<>();
        //Adding the lengths in the array greater than one
        for (String w : words) {
            if (!wordLengths.contains(w.length()) && w.length()>1)
                wordLengths.add(w.length());
        }
        Collections.sort(wordLengths); //sort the number within the array

        //Adding words in Tree set in order to maintain unique words without duplicates
        for (Integer count : wordLengths) {
            TreeSet<String> tempArr = new TreeSet<>();
            for (String word : words) {
                if (count == word.length()) {
                    tempArr.add(word);
                }
            }
//            words.stream().forEach((w) -> {
//                if (count == w.length())
//                    tempArr.add(w);
//            });

            wordsList.add(tempArr);

        }
    }
}
