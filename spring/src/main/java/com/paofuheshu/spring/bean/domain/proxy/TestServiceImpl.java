package com.paofuheshu.spring.bean.domain.proxy;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/12 22:07
 * @des
 */
@Slf4j
public class TestServiceImpl implements TestService {
    @Override
    public void test() {
        log.info("testServiceImpl的test方法开始执行");
        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("testServiceImpl的test方法执行结束了");
    }
}
