package com.paofuheshu.spring.bean.domain.value.test2;

import java.util.HashMap;
import java.util.Map;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/25 18:25
 * @des
 */
public class DbUtil {

    /**
     * 模拟从db中获取邮件配置信息
     * @return  Map
     */
    public static Map<String, Object> getMailInfoFromDb1() {
        Map<String, Object> result = new HashMap<>();
        result.put("mail.host", "smtp.qq.com");
        result.put("mail.username", "泡芙和树");
        result.put("mail.password", "123");
        return result;
    }

    public static Map<String, Object> getMailInfoFromDb2() {
        Map<String, Object> result = new HashMap<>();
        result.put("mail.host", "smtp.qq.com");
        result.put("mail.username", "酒精泡芙");
        result.put("mail.password", "123");
        return result;
    }
}
