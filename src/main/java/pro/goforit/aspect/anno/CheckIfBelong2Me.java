package pro.goforit.aspect.anno;

import java.lang.annotation.*;

/**
 * @author: Double>J
 * @email: zjj20001031@foxmail.com
 * @editTime: 2022/12/2 15:35
 * @desc:
 **/
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckIfBelong2Me {
    String path();
}
