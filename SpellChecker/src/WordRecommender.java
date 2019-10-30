import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class WordRecommender {
    private String filename;
    public WordRecommender(String fileName) {
        filename = fileName;
    }

    public double getSimilarityMetric(String word1, String word2) {
        int left = getLeftSimilarity(word1, word2);
        int right = getRightSimilarity(word1, word2);
        double average = (left + right) / 2.0;
        return average;
    }

    public ArrayList<String> getWordSuggestions(String word, int n, double commonPercent, int topN) {
        ArrayList<TopScoringWord> topScoringWords = new ArrayList<>();
        try {
            Scanner dictionary = new Scanner(new FileReader(filename));
            while(dictionary.hasNext()) {
                String dictionaryWord = dictionary.next();
                boolean lengthQualifies = dictionaryWord.length() <= (word.length() + n) && dictionaryWord.length() >= (word.length() - n);
                double commonScore = getCommonPercentage(dictionaryWord, word);
                if (lengthQualifies  && commonScore >= commonPercent) {
                    double similarityMetricScore = getSimilarityMetric(dictionaryWord, word);
                    TopScoringWord topScorer = new TopScoringWord();
                    topScorer.name = dictionaryWord;
                    topScorer.score = similarityMetricScore;
                    topScoringWords.add(topScorer);
                }
            }
            dictionary.close();
        } catch (FileNotFoundException e) { }
        for (int i = 0; i < topScoringWords.size(); i++) {
            for (int j = topScoringWords.size() - 1; j > i; j--) {
                if(topScoringWords.get(i).score > topScoringWords.get(j).score) {
                    TopScoringWord currentElement = topScoringWords.get(i);
                    topScoringWords.set(i, topScoringWords.get(j));
                    topScoringWords.set(j, currentElement);
                }
            }
        }
        ArrayList<String> finalist = new ArrayList<>();
        topScoringWords.forEach((topScoringWord)-> finalist.add(topScoringWord.name));
        int lastIndex;
        if (finalist.size() == 1) {
            lastIndex = 1;
        } else {
            lastIndex = finalist.size() - 1;
        }
        int nIndex = lastIndex - topN;
        if (nIndex < 0) {
            nIndex = 0;
        }
        ArrayList<String> topWords = new ArrayList<>();
        finalist.subList(nIndex ,lastIndex).forEach((s -> topWords.add(s)));
        return topWords;
    }

    public ArrayList<String> getWordsWithCommonLetters(String word, ArrayList<String> listOfWords, int n) {
        ArrayList<String> words = new ArrayList<>();
        for(String w: listOfWords) {
            int numInCommon = lettersInCommon(w,word);
            if (numInCommon >= n) {
                words.add(w);
            }
        }
        return  words;
    }

    public String prettyPrint(ArrayList<String> list) {
        String text = "";
        int num = 1;
        for(String word: list) {
            text += Integer.toString(num) + ". "+ word + "\n";
            num++;
        }
        return text;
    }

    private class TopScoringWord {
        String name;
        double score;
    }

    public ArrayList<String> uniqueCharacters(String word) {
        ArrayList<String> listOfUniqueChars = new ArrayList<>();
        String[] letters = word.split("");
        for(String letter: letters) {
            if (!listOfUniqueChars.contains(letter)) {
                listOfUniqueChars.add(letter);
            }
        }
        return listOfUniqueChars;
    }

    public double getCommonPercentage(String word1, String word2) {
        ArrayList<String> w1 = uniqueCharacters(word1);
        ArrayList<String> w2 = uniqueCharacters(word2);
        int numerator = lettersInCommon(w1, w2).size();
        int denominator = uniqueLetters(w1, w2).size();
        double fraction = (double) numerator/denominator;
        return fraction;
    }

    public ArrayList<String> uniqueLetters(ArrayList<String> w1, ArrayList<String> w2) {
        ArrayList<String> listOfUniqueLetters = new ArrayList<>();
        for (String letter: w1) {
            if(!listOfUniqueLetters.contains(letter)) {
                listOfUniqueLetters.add(letter);
            }
        }
        for (String letter: w2) {
            if(!listOfUniqueLetters.contains(letter)) {
                listOfUniqueLetters.add(letter);
            }
        }
        return  listOfUniqueLetters;
    }

    public ArrayList<String> lettersInCommon(ArrayList<String> w1, ArrayList<String> w2) {
        ArrayList<String> commonLetters = new ArrayList<>();
        if (w1.size() > w2.size()) {
            for(String letter: w2) {
                if (w1.contains(letter) && !commonLetters.contains(letter)) {
                    commonLetters.add(letter);
                }
            }
        } else {
            for(String letter: w1) {
                if (w2.contains(letter) && !commonLetters.contains(letter)) {
                    commonLetters.add(letter);
                }
            }
        }
        return commonLetters;
    }

    public int lettersInCommon(String w1, String w2) {
        ArrayList<String> commonLetters = new ArrayList<>();
        if (w1.length() > w2.length()) {
            for(String letter: w2.split("")) {
                if (w1.contains(letter) && !commonLetters.contains(letter)) {
                    commonLetters.add(letter);
                }
            }
        } else {
            for(String letter: w1.split("")) {
                if (w2.contains(letter) && !commonLetters.contains(letter)) {
                    commonLetters.add(letter);
                }
            }
        }
        return commonLetters.size();
    }

    private String[] reverseWord(String word) {
        String[] words = word.split(" ");
        String reversedString = "";
        for (int i = 0; i < words.length; i++) {
            String wordSplit = words[i];
            String reverseWord = "";
            for (int j = word.length()-1; j >= 0; j--)
            {
                reverseWord = reverseWord + word.charAt(j);
            }
            reversedString = reversedString + reverseWord;
        }
        return reversedString.split("");
    }

    private int getLeftSimilarity(String word1, String word2) {
        int count = 0;
        String[] w1Members = word1.split("");
        String[] w2Members = word2.split("");

        if (w1Members.length > w2Members.length) {
            for(int i = 0; i < w2Members.length; i++) {
                if (w2Members[i].equals(w1Members[i])) {
                    count ++;
                }
            }
        } else {
            for(int i = 0; i < w1Members.length; i++) {
                if (w1Members[i].equals(w2Members[i])) {
                    count ++;
                }
            }
        }
        return count;
    }

    private int getRightSimilarity(String word1, String word2) {
        int count = 0;
        String[] word1List = reverseWord(word1);
        String[] word2List = reverseWord(word2);
        if (word1List.length > word2List.length) {
            for(int index = 0; index < word2List.length; index++) {
                if (word2List[index].equals(word1List[index])) {
                    count ++;
                }
            }
        } else {
            for(int index = 0; index < word1List.length; index++) {
                if (word1List[index].equals(word2List[index])) {
                    count ++;
                }
            }
        }
        return count;
    }
}
