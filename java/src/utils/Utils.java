package utils;

public class Utils{
    public static int findIndex(Object[] arr, Object item){
        for (int i = 0; i < arr.length; i++){
            if (arr[i].equals(item)) return i;
        }
        return -1;
    }
}