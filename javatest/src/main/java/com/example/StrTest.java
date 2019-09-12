package com.example;

public class StrTest {
    public static void main(String args[]) {
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

//        List<String> stringList = new ArrayList<>();
//        for(int i=0;i<10;i++){
//            stringList.add(i+"");
//        }
//
//        for(int i=0; i< stringList.size();i++){
//            String str = stringList.get(i);
//            if("2".equals(str)){
//                str = stringList.get(i)+"222";
//            }
//        }


//        BigDecimal bg = new BigDecimal(2.35).setScale(2, RoundingMode.UP);
//        double num = bg.doubleValue();
//        if (Math.round(num) - num == 0) {
//            System.out.println("xxxxxx>>>"+String.valueOf((long) num));
//        }
//        System.out.println("hhhh>>"+String.valueOf(num));


//        double number = 1223333330.0001;
//
//        System.out.println(number);							//12.0
//
//        System.out.println(Double.toString(number));		//12.0
//
//        DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
//        System.out.println(decimalFormat.format(number));
//        for(int i=0; i< 5;i++){
//            if(i == 1){
//                break;
//            }
//            System.out.println("打印出的数据>>"+i);
//        }
//
        int a = 0;
        int b = 1;
        if(a == 0){
           test(b);
           System.out.println("ceshi1111111111");
        }

//        int i=3;
//        if(i== 2){
//            System.out.println("2222");
//        } else if(i==0){
//            System.out.println("0000");
//        } else if(i == 3){
//            System.out.println("333");
//        }

//        for(int i=0;i < 5;i++){
//            if(i == 1) break;
//            System.out.println("输出结果>>"+i);
//        }

        System.out.println("xxxx>>"+getResult(0));
    }

    private static void test(int b){
        if(b == 1){
            return;
        }
        System.out.println("ceshi2222");
    }

    // getResult和getResultBreak结果是一样的
   public static int getResult(int aa){
        int result=0;
        for(int i=0;i< 3;i++){
            if(aa < i){
               return result = i;
            }
            System.out.println("ceshi>>"+i);
        }
        return result;
   }


    public static int getResultBreak(int aa){
        int result=0;
        for(int i=0;i< 3;i++){
            if(aa < i){
               result = i;
               break;
            }
        }
        return result;
    }
}
