package com.paofuheshu.spring.bean.domain.scope_dependson_importResource_lazy.test2;

import org.springframework.stereotype.Component;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/23 14:06
 * @des
 */
@Component
public class Service2 {

    public Service2() { System.out.println("create Service2"); }
}
