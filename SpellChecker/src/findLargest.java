import java.util.Arrays;
import java.io.*;

public class findLargest {
    void find3largest(int[] arr)
    {
        Arrays.sort(arr); //It uses Tuned Quicksort with
        //avg. case Time complexity = O(nLogn)
        int n = arr.length;
        int check = 0, count = 1;

        for(int i = 1; i <= n; i++){

            if(count<4){
                if(check!=arr[n-i])
                {
                    // to handle duplicate values
                    System.out.print(arr[n-i]+" ");
                    check = arr[n-i];
                    count++;
                }
            }
            else
                break;
        }

    }
}

//Sorrting Array without colletions
//    ArrayList < Integer > arraylist = new ArrayList < Integer > ();
//
//        arraylist.add(10010);
//        arraylist.add(5);
//        arraylist.add(4);
//        arraylist.add(2);
//
//        for (int i = 0; i < arraylist.size(); i++) {
//
//            for (int j = arraylist.size() - 1; j > i; j--) {
//                if (arraylist.get(i) > arraylist.get(j)) {
//
//                    int tmp = arraylist.get(i);
//                    arraylist.set(i,arraylist.get(j));
//                    arraylist.set(j,tmp);
//
//                }
//
//            }
//
//        }
//        for (int i: arraylist) {
//            System.out.println(i);
//        }