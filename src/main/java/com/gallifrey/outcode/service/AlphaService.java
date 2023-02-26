package com.gallifrey.outcode.service;

import com.gallifrey.outcode.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
//修改默认的单例模式为多例模式
@Scope("prototype")
public class AlphaService {

    //    依赖注入有三种方法，不推荐字段注入(IOC耦合过高，循环依赖)，尝试构造器注入，或者setter注入(适合可选对象注入)
    @Autowired
    private AlphaDao alphaDao;


//    构造器依赖注入(构造器注入适用于强制对象注入)  官方推荐
//    private  final  AlphaDao alphaDao;
//
//    @Autowired
//    public AlphaService(AlphaDao alphaDao){
//        this.alphaDao=alphaDao;
//    }


    public AlphaService() {
        System.out.println("实例化AlphaService");
    }

    //构造器后使用
    @PostConstruct
    public void init() {
        System.out.println("初始化AlphaService");
    }

    //销毁前使用
    @PreDestroy
    public void destroy() {
        System.out.println("销毁AlphaService");
    }

    public String find() {
        return alphaDao.select();
    }
}
