package com.yunji.dango.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogAop{
    private Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @Pointcut("execution(* com.yunji.dango.*.controller..* (..)) || execution(* com.yunji.dango.*.service.impl..* (..)) || execution(* com.yunji.dango.secondhouse.contorller..* (..))")
    private void controlAndServiceMethod() {}

    @Before("controlAndServiceMethod()")
    public void before(JoinPoint call){
        String className = call.getTarget().getClass().getName();
        String methodName = call.getSignature().getName();
        Object[] args = call.getArgs();
        StringBuilder argString = new StringBuilder();
        for (Object arg : args) {
            argString.append(arg + "__");
        }
        this.logger.info(className + "." + methodName + " 开始执行---入参:" + argString);
    }

    @AfterReturning(value="controlAndServiceMethod()", returning="returnValue")
    public void after(JoinPoint call, Object returnValue){
        String className = call.getTarget().getClass().getName();
        String methodName = call.getSignature().getName();
        this.logger.info(className + "." + methodName + " 执行完毕---返回参数:" + returnValue);
    }
}
