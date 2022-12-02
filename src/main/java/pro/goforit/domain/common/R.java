package pro.goforit.domain.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Double>J
 * @email: zjj20001031@foxmail.com
 * @editTime: 2022/12/2 14:15
 * @desc:
 **/
@Data
public class R<T> implements Serializable {

    private static final long serialVersionUID = 8479738461118536040L;

    private static final Integer CODE_SUCCESS = 0;
    private static final Integer CODE_FAIL = -1;


    private Integer code;
    private T data;
    private String message;


    public R(Integer code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }


    public static R<String> ok(){
        return ok(null);
    }

    public static <T>  R<T> ok(T data){
        return ok(data, "请求成功");
    }

    public static <T> R<T> ok(T data,String message){
        return new R<>(CODE_SUCCESS,data,message);
    }

    public static R<String> fail(String message){
        return new R<>(CODE_FAIL,null,message);
    }
}
