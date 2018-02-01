package com.lyl.frame.utils;

import java.io.File;

/**
 * @author lyl
 * @date 2018/1/17.
 */

public class FileUtils {

    public static boolean exists(String path){
        File file=new File(path);
        if (file.exists()){
            return true;
        }
        return false;
    }
}
