package pro.goforit.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import pro.goforit.aspect.anno.CheckIfBelong2Me;
import pro.goforit.domain.common.ContextHolder;
import pro.goforit.util.ElUtil;

import java.lang.reflect.Method;

/**
 * @author: Double>J
 * @email: zjj20001031@foxmail.com
 * @editTime: 2022/12/2 15:34
 * @desc:
 **/
@Aspect
@Component
public class CheckIfBelong2MeAspect {

    @Around("@annotation(checkIfBelong2Me)")
    public Object around(ProceedingJoinPoint joinPoint, CheckIfBelong2Me checkIfBelong2Me) throws Throwable {


        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Object[] args = joinPoint.getArgs();
        String key =  checkIfBelong2Me.path();
        String s = ElUtil.parseKey(method, args, key);
        System.out.println("生成key--->"+s);

        String homeDir = ContextHolder.getHomeDir();
        String path = checkIfBelong2Me.path();
        if (!path.startsWith(homeDir)){
            throw new IllegalArgumentException("unable to manipulate other people's files.");
        }

        return joinPoint.proceed(args);
    }

}
