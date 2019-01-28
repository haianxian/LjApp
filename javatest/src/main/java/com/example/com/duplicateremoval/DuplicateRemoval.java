package com.example.com.duplicateremoval;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by 13717 on 2018/7/4.
 */

public class DuplicateRemoval {
    public static void main(String args[]){
        int a=0;
        int b=5;
        int c = a+b;
        System.out.println(c);
       List<String> list = new ArrayList<>();
        for(int i=0;i<1000;i++){
            if(i ==0 || i== 1 || i==2 || i== 3){
                list.add("aaa");
            } else {
                list.add("hhh"+i);
            }

        }

        long time = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            duplicationRemovel(list);
        }
        long time1 = System.currentTimeMillis();
        System.out.println("time1:"+(time1-time));

        for (int i = 0; i < 1000000; i++) {
            duplicationRemovel2(list);
        }
        long time2 = System.currentTimeMillis();
        System.out.println("time2:"+(time2-time1));

        for (int i = 0; i < 1000000; i++) {
            duplicationRemovel3(list);
        }
        long time3 = System.currentTimeMillis();
        System.out.println("time3:"+(time3-time2));
    }

    // hashSet
    private static void duplicationRemovel(List<String> list){
        HashSet hashSet = new HashSet(list.size());
        hashSet.addAll(list);
        list.clear();
        list.addAll(hashSet);
    }

    // LinKhashSet
    private static void duplicationRemovel2(List<String> list){
        LinkedHashSet linkedHashSet = new LinkedHashSet(list.size());
        linkedHashSet.addAll(list);
        list.clear();
        list.addAll(list);
    }

    // list
    private static void duplicationRemovel3(List<String> list){
        List<String> tempList = new ArrayList<String>(list.size());
        for(String str: list){
            if(!tempList.contains(str)){
                tempList.add(str);
            }
        }
        list.clear();
        list.addAll(tempList);
    }
}
