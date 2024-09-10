package BinarySearch;


import java.util.List;

public class Algo {
    Integer binarySearch(List<Integer> arrList, Integer value){
        Integer low = 0;
        Integer high = arrList.size() - 1;
        while (low<= high){
            Integer mid = (low+high) / 2;

            if (arrList.get(mid) == value){
                return mid;
            }

            // If x greater, ignore left half
            if (arrList.get(mid) < value) {
                low = mid + 1;
            }
                // If x is smaller, ignore right half
            else {
                high = mid - 1;
            }
        }
    return  -1;
    }

}
