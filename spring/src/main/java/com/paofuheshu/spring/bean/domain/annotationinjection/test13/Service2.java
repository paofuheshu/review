package com.paofuheshu.spring.bean.domain.annotationinjection.test13;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/20 22:19
 * @des 使用了@Primary，表示这是个主要的候选者
 */
@Component
@Primary
public class Service2 implements IService {
}
