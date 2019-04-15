package com.example.demo.aop.paramcopy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: g6
 * @Date: 2019/4/15 11:37
 */
@Aspect
@Configuration
public class ParamCopyAspect {

    private static Logger logger = LoggerFactory.getLogger(ParamCopyAspect.class);

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

    @Pointcut("@annotation(com.example.demo.annotation.XCopy)")
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
        return pjp.proceed(before(pjp));
    }

    public Object[] before(ProceedingJoinPoint pjp) throws IllegalAccessException, InstantiationException {
        Map<String,Object> parma = new HashMap<>();
        Object[] parameters = pjp.getArgs();
        String[] parameterNames = ((CodeSignature) pjp.getStaticPart().getSignature()).getParameterNames();
        Class[] parameterTypes = ((CodeSignature) pjp.getStaticPart().getSignature()).getParameterTypes();
        for(int i = 0;i<parameterNames.length;i++){
            if(parameters[i]==null){
                Object o = parameterTypes[i].newInstance();
                BeanUtils.copyProperties(parameters[0],o);
                parma.put(parameterNames[i],o);
            }else{
                parma.put(parameterNames[i],parameters[i]);
            }
        }
        Object[] args = new Object[parameterNames.length];
        for(int i=0;i<args.length;i++){
            args[i] = parma.get(parameterNames[i]);
        }
        return args;
    }

//    public Map<String,Object> getParameter(Object obj){
//        try {
//            //反射对象中的属性
//            Class clazz=obj.getClass();
//            Field[] fields= clazz.getDeclaredFields();
//            Map<String,Object> resultMap=new java.util.HashMap<>();
//            //遍历并返回
//            for(Field field:fields){
//                String fieldName=field.getName();
//                PropertyDescriptor pd=new PropertyDescriptor(fieldName,clazz);
//                Method readMethod = pd.getReadMethod();
//                Object resultObj= readMethod.invoke(obj);
//                resultMap.put(fieldName,resultObj);
//            }
//            return resultMap;
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//        return null;
//    }



}
