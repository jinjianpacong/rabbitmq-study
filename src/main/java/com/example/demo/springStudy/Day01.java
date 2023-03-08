package com.example.demo.springStudy;

import com.example.demo.model.Product;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 2014080902211金泂枞
 * @Date: 2023/02/25/14:41
 * @Description:
 */
public class Day01 {
    public static void main(String[] args) {
        ApplicationContext ac=new ClassPathXmlApplicationContext("application.xml");
        Product product = ac.getBean(Product.class);
        System.out.println(product.getBeanName());
    }
}
