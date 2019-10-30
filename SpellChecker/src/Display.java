public class Display {

    public static void askForFileName() {
        println("What file would you like to spell check?");
    }

    public static void problemsReadingFile() {
        println("Sorry, there was a problem reading the file name you provided. Goodbye :)");
    }

    public static void fileLooksGood() {
        println("You're file looks great, everything is spelled correctly already!");
    }

    public static void misspelledWord(String word) {
        println("The word " + word + " is misspelled.");
    }

    public static void text(String text) {
        println(text);
    }

    public static void commandNotRecognized() {
        println("Sorry, please enter a valid command: ");
    }

    public static void userOptions() {
        println("Press ‘r’ for replace, ‘a’ for accept as is, ‘t’ for type in manually.");
    }

    public static void programFinished(String newFilesName) {
        println("The spell checking of your file has been completed. A new file named " + newFilesName + " has been generated!");
        println("Good Bye! :)");
    }

    public static void userInputIsT() {
        println("Please type the word that will be used as the replacement in the output file");
    }

    private static void println(String s) {
        System.out.println(s);
    }
}

