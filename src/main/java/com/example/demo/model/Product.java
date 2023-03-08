package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 2014080902211金泂枞
 * @Date: 2023/02/25/14:39
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements ApplicationContextAware, BeanNameAware, BeanPostProcessor {
    private int id;
    private String name;
    private ApplicationContext applicationContext;
    private String beanName;
    public void init(){
        System.out.println("初始化...");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }

    @Override
    public void setBeanName(String name) {
        this.beanName=name;
    }
    public String getBeanName(String name) {
        return this.beanName;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
    public ApplicationContext getApplicationContext(ApplicationContext applicationContext) throws BeansException {
        return this.applicationContext;
    }
}
