package pro.goforit.domain.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author: Double>J
 * @email: zjj20001031@foxmail.com
 * @editTime: 2022/12/2 14:16
 * @desc:
 **/
@Data
public class FileBaseVO implements Serializable {
    private static final long serialVersionUID = 1404059703737849781L;

    //名称
    private String name;

    //是否是文件夹
    private boolean ifDir;

    //文件后缀
    private String extend;

    //真是存储路径
    private String absolutePath;

    private long lastModify;

    private long totalSpace;



}
