import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SpellChecker {
    private boolean isUserReplacingWord = false;
    private Scanner scanner;
    private ArrayList<String> wordsInOriginalFile;
    private ArrayList<String> wordsInDictionary;
    private HashMap<Integer, String> wordsToCorrect;
    private WordRecommender recommender;
    private ArrayList<String> suggestedWords;

    public SpellChecker() {
        this.scanner = new Scanner(System.in);
        this.wordsInOriginalFile = new ArrayList<>();
        this.wordsInDictionary = new ArrayList<>();
        this.wordsToCorrect = new HashMap<>();
        this.recommender = new WordRecommender("engDictionary");
        this.suggestedWords = new ArrayList<>();
    }

    public void start() {
        Display.askForFileName();
        String userInputFilename = scanner.nextLine();
        boolean fileExists = FileHandler.fileExists(userInputFilename);
        if (fileExists) {
            analyzeFile(userInputFilename);
        } else {
            Display.problemsReadingFile();
        }
    }

    private void analyzeFile(String originalFileName) {
        makeArrayOfOriginalWords(originalFileName);
        makeArrayOfDictionaryWords();
        logMisspelledWords();
        boolean fileContainsMisspellings = wordsToCorrect.size() != 0;
        if (fileContainsMisspellings) {
            correctMisspelledWords();
            String newFileName = originalFileName + "_chk";
            createNewFile(newFileName);
            Display.programFinished(newFileName);
        } else {
            Display.fileLooksGood();
            String newFileName = originalFileName + "_chk";
            createNewFile(newFileName);
            Display.programFinished(newFileName);
        }
        String newFileName = originalFileName + "_chk";
        createNewFile(newFileName);
    }

    private void correctMisspelledWords() {
        wordsToCorrect.forEach((key, value) -> showOptionsToCorrect(value, key));
    }

    private void showOptionsToCorrect(String word, int indexOfWord) {
        Display.misspelledWord(word);
        displaySuggestions(word);
        Display.userOptions();
        String userInput = scanner.nextLine();
        if (userInput.equals("r")) {
            isUserReplacingWord = true;
            handleReplaceWord(word, indexOfWord);
        } else if (userInput.equals("a")) {
        } else if (userInput.equals("t")) {
            isUserReplacingWord = true;
            Display.userInputIsT();
            handleManualTypingOfWord(indexOfWord);
        } else {
            if (!isUserReplacingWord) {
                Display.commandNotRecognized();
                showOptionsToCorrect(word, indexOfWord);
            }
        }
    }

    private void displaySuggestions(String word) {
        suggestedWords = recommender.getWordSuggestions(word, 3, 0.8, 3);
        Display.text("Here are some suggestions: ");
        Display.text(recommender.prettyPrint(suggestedWords));
    }

    private void handleReplaceWord(String word, int indexOfWord) {
        Display.text("Please choose a number: ");
        int userInput = scanner.nextInt();
        if (userInput - 1 >= suggestedWords.size()) {
            Display.text("Sorry invalid input, pick a number in range!");
            handleReplaceWord(word, indexOfWord);
        }
        String incomingWord = suggestedWords.get(userInput - 1);
        wordsInOriginalFile.remove(indexOfWord);
        wordsInOriginalFile.add(indexOfWord, incomingWord);
        isUserReplacingWord = false;
    }

    private void handleManualTypingOfWord(int indexToReplace) {
        String newWord = scanner.nextLine();
        wordsInOriginalFile.remove(indexToReplace);
        wordsInOriginalFile.add(indexToReplace, newWord);
        isUserReplacingWord = false;
    }

    private void makeArrayOfOriginalWords(String originalFileName) {
        try {
            Scanner originalFile = new Scanner(new FileReader(originalFileName));
            while (originalFile.hasNext()) {
                String nextWord = originalFile.next();
                wordsInOriginalFile.add(nextWord);
            }
            originalFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void makeArrayOfDictionaryWords() {
        try {
            Scanner dictionary = new Scanner(new FileReader("engDictionary"));
            while (dictionary.hasNext()) {
                String nextWord = dictionary.next();
                this.wordsInDictionary.add(nextWord);
            }
            dictionary.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void logMisspelledWords() {
        for (int i = 0; i < wordsInOriginalFile.size(); i++) {
            String word = wordsInOriginalFile.get(i);
            if (!wordsInDictionary.contains(word)) {
                this.wordsToCorrect.put(i, word);
            }
        }
    }

    private void createNewFile(String newFileName) {
        String text = "";
        for (String word : wordsInOriginalFile) {
            text += word + " ";
        }
        FileHandler.writeNewFile(newFileName, text);
    }
}

