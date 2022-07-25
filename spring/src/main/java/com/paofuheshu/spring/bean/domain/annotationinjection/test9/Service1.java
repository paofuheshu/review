package com.paofuheshu.spring.bean.domain.annotationinjection.test9;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/20 22:01
 * @des
 */
@Component
@Qualifier("tag1")
public class Service1 implements IService {
}
