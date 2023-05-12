package com.jjh.cleverai.aspect;//package com.jjh.cleverai.aspect;
//
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//
//@Aspect
//@Component
//public class ConvertParamAspect {
//
//    private static final String SIGNATURE = "SIGNATURE";
//
//    @Around("@annotation(com.jjh.cleverai.annotation.ConvertParam)")
//    public Object aroundCreatePost(ProceedingJoinPoint joinPoint) throws Throwable {
//        // 获取请求参数
//        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        if (requestAttributes != null) {
//            HttpServletRequest request = requestAttributes.getRequest();
//        }
//
//        Object[] args = joinPoint.getArgs();
//        // 继续执行方法
//        if (arg != null) {
//        Object arg = args[0];
//
//        }
////        return joinPoint.proceed(argsgs);
//        return joinPoint.proceed(args);
//    }
//}
