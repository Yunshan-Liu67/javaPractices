package BinarySearch;


import java.util.Arrays;
import java.util.List;
import java.io.*;



public class Test {
    public static void main(String args[]) {
        Algo ob = new Algo();
        List<Integer> arr = Arrays.asList(2, 5, 8, 12, 16, 23, 38, 56, 72, 91);
        Integer index = ob.binarySearch(arr, 38); //test failed
        System.out.println(index);
    }
}
