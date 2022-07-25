package com.paofuheshu.spring.bean.domain.annotation;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/14 21:43
 * @des
 */
@Ann12(name = "酒精泡芙")
@Ann12(name = "泡芙和树")
public class UseAnn12 {

    @Ann11({
            @Ann12(name = "泡芙和树"),
            @Ann12(name = "酒精泡芙")
    })
    String name;
}
