package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * list转成String，用逗号拼接
 */
public class TestAddStr {
    public static void main(String[] args){
//第一种
//        StringBuffer sb = new StringBuffer();
//        List<String> list = new ArrayList<>();
//        list.add("1");
//        list.add("2");
//        list.add("3");
//        list.add("4");
//        for(int i=0; i<list.size();i++){
//            String a = list.get(i);
//            if(i == 0){
//                sb.append(a);
//            } else {
//                sb.append(","+a);
//            }
//        }
//        System.out.println("输出结果>>"+sb.toString());

// 第二种
//        String result="";
//        List<String> list = new ArrayList<>();
//        list.add("1");
//        list.add("2");
//        list.add("3");
//        list.add("4");
//        for(int i=0; i<list.size();i++){
//            if(!"".equals(result)){
//                result +=",";
//            }
//            result += list.get(i);
//        }
//        System.out.println("输出结果>>"+result);
//第三种
        StringBuffer sb = new StringBuffer();
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        for(String str:list){
            if(sb.length() == 0){
                sb.append(str);
            } else {
                sb.append(","+str);
            }
        }
        System.out.println("输出结果>>"+sb.toString());
    }
}
