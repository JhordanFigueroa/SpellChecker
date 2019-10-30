import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class TestingEnvironment {
    public static void main(String[] args) {
//        WordRecommender recommender = new WordRecommender("newDictionary");
//        ArrayList<String> words = recommender.getWordSuggestions("basteu", 2, 0.5,5);
//        System.out.println(recommender.prettyPrint(words));

        String[] strs = {"jhordan", "jorge", "alex", "luvina", "selene"};

        ArrayList<String> names = new ArrayList<>();
        names.addAll(Arrays.asList(strs));
//        for (String name : names) {
//            System.out.println(name);
//        }
        names.forEach(name -> {
            System.out.println(name);
        });
    }
}

