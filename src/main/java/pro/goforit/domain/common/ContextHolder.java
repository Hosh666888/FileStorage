package pro.goforit.domain.common;

/**
 * @author: Double>J
 * @email: zjj20001031@foxmail.com
 * @editTime: 2022/12/2 14:18
 * @desc:
 **/
public class ContextHolder {

    //baseDir+homeDir(uid)    /opt/FileStorage/data/uid
    private static InheritableThreadLocal<String> homeDir = new InheritableThreadLocal();

    public static void setHomeDir(String dir){
        homeDir.remove();
        homeDir.set(dir);
    }

    public static String getHoneDir(){
        return homeDir.get();
    }

    public static void clear(){
        homeDir.remove();;
    }



}
