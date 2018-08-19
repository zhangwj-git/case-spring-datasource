package com.zhang.springcase.datasource.transactional;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 */
public class TransactionalAnnotationAdvisor extends AbstractPointcutAdvisor implements BeanFactoryAware{

    private Advice advice;//通知

    private Pointcut pointcut;//切入点

    public TransactionalAnnotationAdvisor(Class<? extends Annotation> annotationClzzs){
        Set<Class<? extends Annotation>> annotationTypes = new LinkedHashSet<>(1);
        annotationTypes.add(annotationClzzs);
        this.advice = buildAdvice();
        this.pointcut = buildPointcut(annotationTypes);
    }

    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }

    @Override
    public Advice getAdvice() {
        return this.advice;
    }

    private Advice buildAdvice(){
        return new TransactionMethodInterceptor();
    }

    private Pointcut buildPointcut(Set<Class<? extends Annotation>> annotationTypes){
        ComposablePointcut composablePointcut = null;
        for (Class<? extends Annotation> annotationType : annotationTypes){
            Pointcut amp = new AnnotationMatchingPointcut(annotationType,Boolean.TRUE);
            Pointcut fmp = AnnotationMatchingPointcut.forMethodAnnotation(annotationType);
            if (composablePointcut == null){
                composablePointcut = new ComposablePointcut(amp).union(fmp);
            } else {
                composablePointcut.union(amp).union(fmp);
            }
        }
        return composablePointcut;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        if (this.advice instanceof BeanFactoryAware){
            ((BeanFactoryAware) this.advice).setBeanFactory(beanFactory);
        }
    }
}
