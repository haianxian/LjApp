package com.example;

public class TestNullClass {

    public static void main(String args[]){
//        String str=null;
//
//        if(str != null || str.length() != 0 && str.equals("123")){
//            System.out.println("aaaaaaaaaaaa");
//            //Exception in thread "main" java.lang.NullPointerException
//        }
        for(int i=0; i< 5;i++){
            System.out.println("aaaaaaaaaaaa111>>"+i);
            if(i == 3){
                System.out.println("aaaaaaaaaaaa>>"+i);
                break;
            }
        }
        System.out.println("bbbbbbbbbbbb>>");

//        for(int i=0; i< 5;i++){
//            System.out.println("aaaaaaaaaaaa111>>"+i);
//            if(i == 3){
//                System.out.println("aaaaaaaaaaaa>>"+i);
//                return;
//            }
//        }
    }
}
