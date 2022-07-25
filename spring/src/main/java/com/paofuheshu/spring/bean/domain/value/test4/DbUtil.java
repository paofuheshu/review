package com.paofuheshu.spring.bean.domain.value.test4;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/25 19:00
 * @des
 */
public class DbUtil {

    public static Map<String, Object> getMailInfoFromDb() {
        Map<String, Object> result = new HashMap<>();
        result.put("mail.username", UUID.randomUUID().toString());
        return result;
    }
}
