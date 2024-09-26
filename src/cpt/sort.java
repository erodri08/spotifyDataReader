package cpt;
import java.io.*;
import java.util.Arrays;


public class sort {
    public static void mergeSort(SpotifyData[] arr) {
        SpotifyData[] temp = new SpotifyData[arr.length];
        mergeSortHelper(arr, 0, arr.length - 1, temp);
    }

    private static void mergeSortHelper(SpotifyData[] arr, int from, int to, SpotifyData[] temp) {
        if (to - from >= 1) {
            int mid = (from + to) / 2;
            mergeSortHelper(arr, from, mid, temp);
            mergeSortHelper(arr, mid + 1, to, temp);
            merge(arr, from, mid, to, temp);
        }
    }

    /**
    * Sorts the Data by artist name in alphabetical ordee
    * @param from  The lowest number
    * @param mid  The middle number
    * @param to  The highest number
    * @author E. Rodrigues
    */
    private static void merge(SpotifyData[] arr, int from, int mid, int to, SpotifyData[] temp) {
        int i = from;
        int j = mid + 1;
        int k = from;

        while (i <= mid && j <= to) {
            if (arr[i].getStrArtistName().compareTo(arr[j].getStrArtistName()) <= 0) {
                temp[k] = arr[i];
                i++;
            } else {
                temp[k] = arr[j];
                j++;
            }
            k++;
        }

        while (i <= mid) {
            temp[k] = arr[i];
            i++;
            k++;
        }

        while (j <= to) {
            temp[k] = arr[j];
            j++;
            k++;
        }

        for (k = from; k <= to; k++) {
            arr[k] = temp[k];
        }
    }
}
