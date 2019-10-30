import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class highestScore {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> newList = new ArrayList<>();
        ArrayList<Integer> reversedNewList = new ArrayList<>();

        list.add(45);
        list.add(2);
        list.add(20);
        list.add(100);
        list.add(50);
        list.add(87);
        list.add(23);
        list.add(89);
        list.add(5);
        list.add(33);

        System.out.println(list);
//        System.out.println(Collections.max(list));

        //Adds Values from one array to another array matching index:
//        for (int i = 0; i < list.size(); i++) {
////            newList.add(i, list.get(i));
////        }
////        System.out.println(newList);

        //Gets Largest Value in array:
//        int largest = list.get(0);
//        System.out.println(largest);
//        for (int i = 0; i < list.size(); i++) {
//            if (list.get(i) > largest) {
//                largest = list.get(i);
//            }
//        }
//        System.out.println(largest);

        //finding largest and pushing that element to new array:

//        int largest = list.get(0);
//        for (int i = 0; i < list.size(); i++) {
//            if (list.get(i) > largest) {
//                largest = list.get(i);
//            }
//            newList.add(largest);
//        }
//        System.out.println("This is newList Array: " + newList);


        //Sorting list:
        for (int i = 0; i < list.size(); i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if(list.get(i) > list.get(j)) {
                    int currentElement = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, currentElement);
                }
            }
        }

        for (int i: list) {
            newList.add(i);;
        }
        System.out.println(newList);

        for (int i = newList.size() - 1; i >= 0; i--) {
            reversedNewList.add(newList.get(i));
        }

        System.out.println(reversedNewList);


//        findLargest obj = new findLargest();
//        int[] arr={12,45,1,-1,45,54,23,5,0,-10, 100, 88, 34};
//        obj.find3largest(arr);


        //

        /**
         * Working on this function to sort out topScoringWords
         */


//        public ArrayList<String> getWordSuggestions(String word, int n, double commonPercent, int topN) {
//            ArrayList<TopScoringWord> topScoringWords = new ArrayList<>();
//            try {
//                Scanner dictionary = new Scanner(new FileReader(filename));
//                while(dictionary.hasNext()) {
//                    String dictionaryWord = dictionary.next();
//                    boolean lengthQualifies = dictionaryWord.length() <= (word.length() + n) && dictionaryWord.length() >= (word.length() - n);
//                    double commonScore = getCommonPercentage(dictionaryWord, word);
//                    if (lengthQualifies  && commonScore >= commonPercent) {
//                        double similarityMetricScore = getSimilarityMetric(dictionaryWord, word);
//                        TopScoringWord topScorer = new TopScoringWord();
//                        topScorer.name = dictionaryWord;
//                        topScorer.score = similarityMetricScore;
//                        topScoringWords.add(topScorer);
//                    }
//                }
//                dictionary.close();
//            } catch (FileNotFoundException e) { }
//            ArrayList<String> finalist = new ArrayList<>();
//            for (int i = 0; i < topScoringWords.size(); i++) {
////            System.out.println(topScoringWords.get(i).name);
////            System.out.println(topScoringWords.get(i).score);
//                for (int j = topScoringWords.size() - 1; j > i; j--) {
//                    if(topScoringWords.get(i).score > topScoringWords.get(j).score) {
//                        double currentElement = topScoringWords.get(i).score;
//                        topScoringWords.set(i, topScoringWords.get(j));
//                        topScoringWords.set(j, currentElement);
//                    }
//                }
//            }
////        System.out.println(topScoringWords.size());
////        System.out.println(topScoringWords.getClass());
////        System.out.println(topScoringWords);
////        for(TopScoringWord topScoringWord: topScoringWords) {
////
////            //TODO: add logic to only add the topN
////            // currently it will add every word to the finalists list
////            // we only want to include the topN
//////            if (topScoringWord.isInTopN) {
//////                finalist.add(topScoringWord.name);
//////            }
////            finalist.add(topScoringWord.name);
////        }
////        System.out.println("This is finalist from getwordsuggestions: " + finalist);
//            return finalist;
//        }
//
//        private class TopScoringWord {
//            String name;
//            double score;
//        }
    }
}
