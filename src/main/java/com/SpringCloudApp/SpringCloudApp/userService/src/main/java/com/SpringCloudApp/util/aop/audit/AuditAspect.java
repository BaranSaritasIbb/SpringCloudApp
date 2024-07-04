package com.SpringCloudApp.util.aop.audit;

import com.SpringCloudApp.dto.request.UserRequest;
import com.SpringCloudApp.model.Users;
import com.SpringCloudApp.model.audit.AuditLog;
import com.SpringCloudApp.repository.AuditLogRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class AuditAspect {

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Pointcut("execution(* com.SpringCloudApp.service.Impl.UserServiceImpl.*(..)) " +
            "&& !execution(* com.SpringCloudApp.service.Impl.UserServiceImpl.get*(..))")
    public void userServiceMethods() {}

    @AfterReturning(pointcut = "userServiceMethods()", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        // Operasyon tipini belirle
        String operation = "";
        if (methodName.startsWith("save")) {
            operation = "INSERT";
        } else if (methodName.startsWith("update")) {
            operation = "UPDATE";
        } else if (methodName.startsWith("delete")) {
            operation = "DELETE";
        }


        AuditLog auditLog = new AuditLog();
        auditLog.setEntityName("Employee");
        auditLog.setEntityId(((UserRequest) args[0]).getId());
        auditLog.setOperation(operation);
        auditLog.setOldValue("");
        auditLog.setNewValue(args[0].toString());
        auditLog.setChangedBy("System");
        auditLog.setChangeTime(LocalDateTime.now());

        auditLogRepository.save(auditLog);
    }
}
