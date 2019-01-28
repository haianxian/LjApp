package com.lj.app.tools.utils;

import android.os.Environment;

import com.lj.app.base.app.MyApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13717 on 2017/12/5.
 */

public class FileUtils {
    //获取闪屏路径
    public static File getCacheVideoAbtFile(){
        File filesDir;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            filesDir= MyApplication.getInstance().getExternalFilesDir("videoabt");
        }else {
            filesDir=new File(MyApplication.getInstance().getFilesDir(),"videoabt");
            filesDir.mkdir();
        }
        return filesDir;
    }

    public static String getCacheVideoAbtPath(){
        return getCacheVideoAbtFile().getAbsolutePath();
    }

    public static String SDPATH = Environment.getExternalStorageDirectory() + "/ACrash/";//文件夹路径
    public static File file = new File(SDPATH);

    public static void CreateDir() {
        if (!file.exists()) {//创建文件夹
            file.mkdirs();
        }
    }

    /**
     * 检查路径是否存在
     *
     * @param path
     * @return
     */
    public static boolean checkFilePathExists(String path) {
        return new File(path).exists();
    }

    /**
     * 从assets 文件夹中获取文件并读取数据(一行一行读)
     * @param fileName
     * @return
     */
    public static List<String> getFromAssets(String fileName){
        List<String> readList = new ArrayList<String>();
        String result="";

        try {
            InputStream in = MyApplication.getInstance().getResources().getAssets().open(fileName);
            InputStreamReader isr = new InputStreamReader(in, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line="";
            String[] arrs= null;
            while((line = br.readLine()) != null){
                readList.add(line);
            }
            br.close();
            isr.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return readList;

    }

}
