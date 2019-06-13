package com.example;

import java.util.ArrayList;
import java.util.List;

public class StrTest {
    public static void main(String args[]){
//        List<Integer> list = new ArrayList<>();
//        for(int i=0;i<20;i++){
//            list.add(i);
//        }
//        String result="";
//        for(int i=0;i<list.size();i++){
//            result +=list.get(i);
//            if(i <list.size()-1){
//                result+=",";
//            }
//        }
//        System.out.println(">>>>>"+result);

        List<String> stringList = new ArrayList<>();
        for(int i=0;i<10;i++){
            stringList.add(i+"");
        }

        for(int i=0; i< stringList.size();i++){
            String str = stringList.get(i);
            if("2".equals(str)){
                str = stringList.get(i)+"222";
            }
        }

    }
}
