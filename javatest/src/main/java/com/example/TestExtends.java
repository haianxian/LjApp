package com.example;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by 13717 on 2018/2/1.
 */

public class TestExtends {
    public static void main(String args[]){
//       new B().fun1();
//       new B().fun2();
//        new A().fun1();
//        String text = "Hi，我是xxx,我的标签是技术宅、游戏宅、发哥,很高兴和你一起看片";
//        System.out.println(">>>>>"+text.indexOf("我的标签是"));
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("4");
        list.add("1");
        list.add("4");
        LinkedHashSet<String> hashSet = new LinkedHashSet<>(list.size());
        hashSet.addAll(list);
        list.clear();
        list.addAll(hashSet);
        for(int i=0; i< list.size();i++){
            String str = list.get(i);
            System.out.print(str);
        }
    }
}
