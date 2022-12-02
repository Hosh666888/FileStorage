package pro.goforit.util;

import org.springframework.util.Assert;

/**
 * @author: Double>J
 * @email: zjj20001031@foxmail.com
 * @editTime: 2022/12/2 14:34
 * @desc:
 **/
public class FileUtil {

    /**
     *  获取文件后缀
     * @param filename
     * @return
     */
    public static String getFileExtend(String filename){
        Assert.hasText(filename,"filename is empty.");
        int lastIndexOf = filename.lastIndexOf(".");
        if (lastIndexOf<0){
            return "unknown";
        }else{
            return filename.substring(lastIndexOf+1);
        }

    }

}
