package pro.goforit.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pro.goforit.domain.common.R;

import java.util.List;

/**
 * @author: Double>J
 * @email: zjj20001031@foxmail.com
 * @editTime: 2022/12/2 14:52
 * @desc:
 **/
@RestControllerAdvice
@Slf4j
@Component
public class CustomExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    public R<String> handleRuntimeException(RuntimeException e){
        log.error(e.getMessage(),e);
        return R.fail(e.getMessage());
    }

    @ExceptionHandler(value = BindException.class)
    public R<String> handleMethodArgumentNotValidException(BindException e){
        log.error("参数绑定异常:{}",e.getMessage(),e);
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        StringBuilder sb = new StringBuilder();
        for (FieldError field : fieldErrors) {
            sb.append(field.getField())
                    .append(":")
                    .append(field.getDefaultMessage())
                    .append(",");
        }
        log.error("参数绑定异常:{} 在绑定时出现异常",sb);
        return R.fail(String.format("参数绑定异常:%s ",sb));
    }



    @ExceptionHandler(value = Exception.class)
    public R<String> handleException(Exception e){
        log.error(e.getMessage(),e);
        return R.fail(e.getMessage());
    }

}
