package com.paofuheshu.spring.bean.domain.annotation;

import java.util.Map;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/14 18:21
 * @des
 */
@Ann9("用在了类上")
@Ann9_0(0)
public class UseAnn9<@Ann9("用在了类变量类型V1上") @Ann9_0(1) V1, @Ann9("用在了类变量类型V2上") @Ann9_0(2) V2> {

    @Ann9("用在了字段上")
    @Ann9_0(3)
    private String name;

    private Map<@Ann9("用在了泛型类型上,String") @Ann9_0(4) String, @Ann9("用在了 泛型类型上,Integer") @Ann9_0(5) Integer> map;

    @Ann9("用在构造方法上")
    @Ann9_0(6)
    public UseAnn9(String name) {
        this.name = name;
    }

    public UseAnn9() {
    }

    @Ann9("用在了返回值上")
    @Ann9_0(7)
    public String m1(@Ann9("用在了参数上") @Ann9_0(8) String name) {
        return null;
    }
}
