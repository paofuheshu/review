package com.paofuheshu.spring.bean.domain.annotationinjection.test11;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/20 22:12
 * @des
 */
@Component
public class InjectService {

    private IService s1;
    private IService s2;

    @Autowired
    public void injectBean(@Qualifier("service2") IService s1, @Qualifier("service1") IService s2) {
         this.s1 = s1;
         this.s2 = s2;
    }

    @Override
    public String toString() {
        return "InjectService{" +
                "s1=" + s1 +
                ", s2=" + s2 +
                '}';
    }
}
