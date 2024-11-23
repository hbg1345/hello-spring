package hello.hello_spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {
    @Around("execution(* hello.hello_spring.service..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());      
        try {
            return joinPoint.proceed();
        }
        finally{
            long end = System.currentTimeMillis();
            long timeMs = end - start;
            System.out.println("End: " +joinPoint.toString() +" " + timeMs + "ms");
        }  
    };
}
