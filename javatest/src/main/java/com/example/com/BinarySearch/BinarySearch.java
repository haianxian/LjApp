package com.example.com.BinarySearch;

/**
 * Created by 13717 on 2018/7/5.
 */

public class BinarySearch {
    public static void main(String args[]){
        int arr[] = {1,3,5,7,9,13};
        // 非递归
        System.out.println(binarySearch(3,arr));
        System.out.println(binarySearch(arr,5,0, arr.length-1));
        System.out.println(111);
    }

    private static int binarySearch(int num,int arr[]){
        int begin = 0;
        int end = arr.length-1;

        while(begin<= end){
            int mid = (begin+end)/2;
            if(arr[mid]>num){
                end =mid-1;
            }else if(arr[mid]<num){
                begin =mid+1;
            } else if(arr[mid] == num){
                return mid;
            }
        }
       return -1;
    }

    // 递归
    private static int binarySearch(int arr[],int num, int begin,int end){
             int mid = (begin+end)/2;
             if(num< arr[begin] || num>arr[end]){
                    return -1;
             }
             if(arr[mid]>num){
                return binarySearch(arr,num,begin, mid-1);
             } else if(arr[mid] < num){
                 return binarySearch(arr,num,mid+1, end);
             } else {
                 return mid;
             }
    }
    public void bubbleSort(int array[]) {
        int t = 0;
        for (int i = 0; i < array.length - 1; i++)
            for (int j = 0; j < array.length - 1 - i; j++)
                if (array[j] > array[j + 1]) {
                    t = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = t;
                }
    }

}
