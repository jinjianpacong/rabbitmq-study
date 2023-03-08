package com.example.demo.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 2014080902211金泂枞
 * @Date: 2023/02/25/17:05
 * @Description:
 */
public class MyBeanFactoryProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//        BeanDefinition product = beanFactory.getBeanDefinition("product");
//        System.out.println(product.getBeanClassName());
//        for (PropertyValue value : product.getPropertyValues().getPropertyValueList()) {
//            System.out.println(value.getValue());
//        }
//
    }
}
