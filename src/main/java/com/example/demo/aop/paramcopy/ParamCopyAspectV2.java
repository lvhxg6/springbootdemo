package com.example.demo.aop.paramcopy;

import com.example.demo.annotation.BeanCopy;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: g6
 * @Date: 2019/4/15 11:37
 */
@Aspect
@Configuration
public class ParamCopyAspectV2 {

    private static Logger logger = LoggerFactory.getLogger(ParamCopyAspectV2.class);

//    @Before(" execution(* com.example.demo.service.*.*(..))")
//    public void before(JoinPoint joinPoint){
//        logger.info(" Check for user access ");
//        logger.info(" Allowed execution for {}", joinPoint);
//        Object[] argValuess = joinPoint.getArgs();
//        String[] parameterNames = ((CodeSignature) joinPoint.getStaticPart().getSignature()).getParameterNames();
//        for(int i=0;i<parameterNames.length;i++){
//            logger.info(parameterNames[i]+","+argValuess[i]);
//        }
//    }

//    @Around(" execution(* com.example.demo.service.*.*(..))")

    @Pointcut("execution(* com.example.demo.service.*.*(..))")
    private void PackagePointCut(){}

    @Pointcut("@annotation(com.example.demo.annotation.BeanCopy)")
    private void AnnotationPointCut(){}


    /**
     * 基于注解的AOP切面编程
     * @param pjp
     * @return
     * @throws Throwable
     */
//    @Around("@annotation(com.example.demo.annotation.XCopy)")
//    @Around(value = "PackagePointCut()")
    @Around(value = "PackagePointCut() && AnnotationPointCut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{

        Object[] args = pjp.getArgs();
        Class<?>[] argTypes = new Class[args.length];

        for (int i = 0; i < args.length; i++) {
            argTypes[i] = args[i].getClass();
        }

        Method method = null;
        try {
            method = pjp.getTarget().getClass()
                    .getMethod(pjp.getSignature().getName(), argTypes);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }

        BeanCopy copy = method.getAnnotation(BeanCopy.class);
        Class<?> returnType = method.getReturnType();

        Object proceed = null;

        Class downTargetClazz = copy.downTargetClazz();
        Class upTargetClazz = copy.upTargetClazz();

        if(Object.class!=downTargetClazz){
            before(pjp,copy.downTargetClazz());
        }
        proceed = pjp.proceed(pjp.getArgs());
        if(Object.class!= upTargetClazz){
            try{
                after(proceed,upTargetClazz,returnType);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return proceed;
    }

    private void after(Object proceed,Class upTargetClazz,Class proceedType) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        Object o = upTargetClazz.newInstance();
        BeanUtils.copyProperties(proceed,o);
        Field[] declaredFields = proceedType.getDeclaredFields();
        Field field = null;
        Method setMethod = null;
        for(int i=0;i<declaredFields.length;i++){
            System.out.print(declaredFields[i].getType()+"      "+proceedType+"\r\n");
            if(declaredFields[i].getType()==upTargetClazz){
                String name = declaredFields[i].getName();
                String methodName = "set" + name.substring(0,1).toString().toUpperCase() + name.substring(1, name.length()).toString();
                setMethod = proceedType.getDeclaredMethod(methodName, upTargetClazz);
                break;
            }
        }
        if(setMethod==null){
            return;
        }
        setMethod.invoke(proceed,o);
    }

    /**
     * Version 2
     * @param pjp
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public void before(ProceedingJoinPoint pjp,Class clazz) throws IllegalAccessException, InstantiationException {
        Object[] parameters = pjp.getArgs();
        String[] parameterNames = ((CodeSignature) pjp.getStaticPart().getSignature()).getParameterNames();
        Object obj = clazz.newInstance();
        BeanUtils.copyProperties(parameters[0],obj);
        GlobalBussessBeanContainer.putBean2Container(parameterNames[0],obj);
    }

}
