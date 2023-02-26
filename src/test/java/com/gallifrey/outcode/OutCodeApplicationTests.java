package com.gallifrey.outcode;

import com.gallifrey.outcode.dao.AlphaDao;
import com.gallifrey.outcode.service.AlphaService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = OutCodeApplication.class)

//实现ApplicationContextAware接口获取 spring IOC容器
class OutCodeApplicationTests implements ApplicationContextAware {

    //  spring IOC容器
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
    }

    //   获取 spring IOC容器
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    //      测试在容器中获取类
    @Test
    public void testApplicationContext() {

        System.out.println(applicationContext);
        AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);
        System.out.println(alphaDao.select());
        alphaDao = applicationContext.getBean("alphaHibernate", AlphaDao.class);
        System.out.println(alphaDao.select());
    }

    //  测试实例化，初始化，销毁化注解   测试默认单例模式
    @Test
    public void testBeanManagement() {
        AlphaService alphaService = applicationContext.getBean(AlphaService.class);
        System.out.println(alphaService);
        alphaService = applicationContext.getBean(AlphaService.class);
        System.out.println(alphaService);
    }

    //  测试Bean配置类
    @Test
    public void testBeanConfig() {
        SimpleDateFormat simpleDateFormat = applicationContext.getBean(SimpleDateFormat.class);
        System.out.println(simpleDateFormat.format(new Date()));
    }

    //  测试依赖注入
    @Autowired
    private AlphaDao alphaDao;
    @Autowired
    @Qualifier("alphaHibernate")
    private AlphaDao alphaDao2;
    @Autowired
    private AlphaService alphaService;
    @Autowired
    private SimpleDateFormat simpleDateFormat;

    @Test
    public void testDI() {
        System.out.println(alphaDao);
        System.out.println(alphaDao2);
        System.out.println(alphaService);
        System.out.println(simpleDateFormat);
    }


}
