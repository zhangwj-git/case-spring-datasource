package com.zhang.springcase.datasource.transactional;

import com.zhang.springcase.datasource.jdbc.DynamicDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;

/**
 *
 */
@Slf4j
public class TransactionMethodInterceptor implements MethodInterceptor{

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object bean = invocation.getThis();
        Method method = invocation.getMethod();
        log.info("==> Invoke bean class is: [{}] ", bean.getClass().getName());
        log.info("==> Invoke Method is: [{}.{}]", bean.getClass().getName(), method.getName());
        Transactional beanTransactional = bean.getClass().getAnnotation(Transactional.class);
        Transactional methodTransactional = method.getAnnotation(Transactional.class);
        log.info("==> Class  annotation is: [{}]", beanTransactional);
        log.info("==> Method annotation is: [{}]", methodTransactional);
        boolean isRead = (methodTransactional != null && methodTransactional.readOnly())
                ||(beanTransactional != null && beanTransactional.readOnly());
        if (isRead) {
            DynamicDataSource.setRead();
        }else {
            DynamicDataSource.setWrite();
        }

        Object result;
        try {
            result = invocation.proceed();
        } finally {
            DynamicDataSource.setWrite();
        }
        return result;
    }
}
