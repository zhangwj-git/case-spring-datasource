package com.zhang.springcase.datasource.transactional;

import org.springframework.aop.framework.AbstractAdvisingBeanPostProcessor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Annotation;

/**
 *
 */
@Component
public class TransactionalPostProcessor extends AbstractAdvisingBeanPostProcessor implements BeanFactoryAware{

    private Class<? extends Annotation> transactionAnnotationType = Transactional.class;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        super.setBeforeExistingAdvisors(true);
        TransactionalAnnotationAdvisor transactionAdvisor = new TransactionalAnnotationAdvisor(transactionAnnotationType);
        transactionAdvisor.setBeanFactory(beanFactory);
        this.advisor = transactionAdvisor;
    }
}
