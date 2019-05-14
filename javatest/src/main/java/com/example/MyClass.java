package com.example;

public class MyClass {
    public static void main(String args[]){
//        //获取当前日期
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date today = new Date();
//        String endDate = sdf.format(today);//当前日期
//
//        //获取三十天前日期
//        Calendar theCa = Calendar.getInstance();
//        theCa.setTime(today);
//        theCa.add(theCa.DATE, -30);//最后一个数字30可改，30天的意思
//        Date start = theCa.getTime();
//        String startDate = sdf.format(start);//三十天之前日期
//
//        System.out.println(startDate);
//
//        long currentTime = System.currentTimeMillis();


//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date=new Date();
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
////        JAVA获取当前时间的前一天
//        calendar.add(Calendar.DAY_OF_MONTH, -1);
//        date = calendar.getTime();
//        System.out.println(sdf.format(date));
//        List a = new ArrayList<>();
//        List a = new LinkedList<>();
//        a.add("1");
//        a.add("2");
//        Random r = new Random();
//        System.out.println(r.nextInt(2));





//        List<String> all = new ArrayList<>();
//        all.add("1");
//        all.add("2");
//        all.add("3");
//        all.add("4");
//        all.add("5");
//
//        List<String> unList = new ArrayList<>();
//        unList.add("2");
//        unList.add("3");
//
//        Set<String> set = new HashSet<>();
//        for(int i=0;i < all.size();i++){
//            String str = all.get(i);
//            boolean isAdd  = true;
//            for(int j=0; j< unList.size();j++){
//                String str1 = unList.get(j);
//                if(str.equals(str1)){
//                    isAdd = false;
//                    System.out.println("1111>>>>"+str);
//                    break;
//                }
//            }
//            System.out.println("2222>>>>");
//            if(isAdd){
//                set.add(str);
//            }
//        }
//        System.out.println("3333>>>>"+set);
//       int sendMsgCount=1;
//       int receMsgCount=0;
//       int totalMsgCount = 1;
//
//        if(sendMsgCount >= 1 && receMsgCount>=1){
//            if(totalMsgCount > 5){
//              System.out.println("aaaa");
//            }
//        }
        for(int i=0; i< 10;i++){
            System.out.println("aaaa");
            if(i == 1){
                return;
            }
            System.out.println("bbbb");
        }
        System.out.println("cccc");
    }
}
