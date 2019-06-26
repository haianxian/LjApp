package com.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
//从总列表中抛去未选中，摘出选中的在原列表中的下表位置
public class TestDoubleLoop {
    public static void main(String args[]){
        Set<Integer> hashSet = new HashSet<>();
        List<String> allList = new ArrayList<>();
        allList.add("1");
        allList.add("2");
        allList.add("3");
        allList.add("4");
        allList.add("5");
        allList.add("6");

        List<String> unList = new ArrayList<>();
        unList.add("1");
        unList.add("2");
        unList.add("3");

        for(int i=0;i < allList.size();i++){
            String a = allList.get(i);
            boolean add = true;
            for(int j=0;j <unList.size();j++){
                String b = unList.get(j);
                if(a.equals(b)){
                    add = false;
                    break;
                }
                System.out.println("111111>>"+b);
            }
            System.out.println("222222>>"+a);
            if(add){
                hashSet.add(i);
            }
        }

        Iterator<Integer> iterator = hashSet.iterator();
        while (iterator.hasNext()){
           int position = iterator.next();
            System.out.println("33333>>"+position);
        }
    }
}
