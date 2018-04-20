package com.sdtz.adlet.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class LoggerBean {
	
	@Before("within(com.sdtz.adlet.service..*)")
	public void loggerController(JoinPoint joinPoint){
		Object target = joinPoint.getTarget();
        String opreate = joinPoint.getSignature().getName();  
        System.out.println("控制器:" + target.getClass().getSimpleName()); 
        System.out.println("方法" + opreate);  
	}
	
}
