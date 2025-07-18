package com.example.studentapp.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.example.studentapp.service.*.*(..))")
    public void logBeforeServiceMethods(JoinPoint joinPoint) {
        System.out.println("Before: " + joinPoint.getSignature());
    }

    @AfterReturning(pointcut = "execution(* com.example.studentapp.service.*.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("Returned from: " + joinPoint.getSignature() + " with result: " + result);
    }

    @AfterThrowing(pointcut = "execution(* com.example.studentapp.service.*.*(..))", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        System.out.println("Exception in: " + joinPoint.getSignature() + " - " + ex.getMessage());
    }
}
