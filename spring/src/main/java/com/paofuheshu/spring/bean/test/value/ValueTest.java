package com.paofuheshu.spring.bean.test.value;

import com.paofuheshu.spring.bean.domain.value.test1.DbConfig;
import com.paofuheshu.spring.bean.domain.value.test1.MainConfig1;
import com.paofuheshu.spring.bean.domain.value.test2.MainConfig2;
import com.paofuheshu.spring.bean.domain.value.test2.DbUtil;
import com.paofuheshu.spring.bean.domain.value.test2.Email;
import com.paofuheshu.spring.bean.domain.value.test3.BeanMyScope;
import com.paofuheshu.spring.bean.domain.value.test3.MainConfig3;
import com.paofuheshu.spring.bean.domain.value.test3.User;
import com.paofuheshu.spring.bean.domain.value.test4.BeanRefreshScope;
import com.paofuheshu.spring.bean.domain.value.test4.MailService;
import com.paofuheshu.spring.bean.domain.value.test4.MainConfig4;
import com.paofuheshu.spring.bean.domain.value.test4.RefreshConfigUtil;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/25 18:13
 * @des
 */
public class ValueTest {

    public static void main(String[] args) throws InterruptedException {
//        test1();
//        test2();
//        test3();
        test4();
    }

    public static void test1() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MainConfig1.class);
        context.refresh();
        DbConfig dbConfig = context.getBean(DbConfig.class);
        System.out.println(dbConfig);
    }

    public static void test2() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//        ConfigurableEnvironment environment = context.getEnvironment();
//        System.out.println("系统环境变量为");
//        environment.getSystemEnvironment().forEach((k ,v) -> System.out.println(k + "--->" + v));
//        System.out.println("系统配置文件为");
//        environment.getSystemProperties().forEach((k ,v) -> System.out.println(k + "--->" + v));
//        System.out.println("===============");
//        System.out.println(environment.getPropertySources());
        // 模拟从db中获取配置信息
        Map<String, Object> mailInfoFromDb1 = DbUtil.getMailInfoFromDb1();
        Map<String, Object> mailInfoFromDb2 = DbUtil.getMailInfoFromDb2();
        // 将其丢在MapPropertySource中（MapPropertySource类是spring提供的一个类，是 PropertySource的子类）
        MapPropertySource mapPropertySource1 = new MapPropertySource("mail", mailInfoFromDb1);
        MapPropertySource mapPropertySource2 = new MapPropertySource("mail", mailInfoFromDb2);
        // 将mailPropertySource丢在Environment中的PropertySource列表的第一个中，让优先级最高
        context.getEnvironment().getPropertySources().addFirst(mapPropertySource1);
        context.getEnvironment().getPropertySources().addFirst(mapPropertySource2);
        context.register(MainConfig2.class);
        context.refresh();
        Email email = context.getBean(Email.class);
        System.out.println(email);
    }

    /**
     * 从输出的前2行可以看出：
     * 1. 调用context.getBean(User.class)从容器中获取bean的时候，此时并没有调用User的构造函数去
     * 创建User对象
     * 2. 第二行输出的类型可以看出，getBean返回的user对象是一个cglib代理对象。
     * 后面的日志输出可以看出，每次调用user.getUsername方法的时候，内部自动调用了
     * BeanMyScope#get 方法和 User的构造函数。
     * 通过上面的案例可以看出，当自定义的Scope中proxyMode=ScopedProxyMode.TARGET_CLASS的
     * 时候，会给这个bean创建一个代理对象，调用代理对象的任何方法，都会调用这个自定义的作用域实现
     * 类（上面的BeanMyScope）中get方法来重新来获取这个bean对象。
     * @throws InterruptedException InterruptedException
     */
    public static void test3() throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 将自定义作用域注册到spring容器中
        context.getBeanFactory().registerScope(BeanMyScope.SCOPE_MY, new BeanMyScope());
        context.register(MainConfig3.class);
        context.refresh();

        System.out.println("从容器中获取User对象");
        User user = context.getBean(User.class);
        System.out.println("user对象的class为：" + user.getClass());

        System.out.println("多次调用user的getUsername感受一下效果\n");

        for (int i = 1; i <= 3; i++) {
            System.out.printf("********\n第%d次开始调用getUsername%n", i);
            System.out.println(user.getUsername());
            System.out.printf("第%d次调用getUsername结束\n********\n%n", i);
        }
    }

    public static void test4() throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getBeanFactory().registerScope(BeanRefreshScope.SCOPE_REFRESH, BeanRefreshScope.getInstance());
        context.register(MainConfig4.class);
        // 刷新mail的配置到Environment
        RefreshConfigUtil.refreshMailPropertySource(context);
        context.refresh();
        MailService mailService = context.getBean(MailService.class);
        System.out.println("配置未更新的情况下,输出3次");
        for (int i = 0; i < 3; i++) {
            System.out.println(mailService);
//            TimeUnit.MILLISECONDS.sleep(20000);
        }

        System.out.println("模拟3次更新配置效果");
        for (int i = 0; i < 3; i++) {
            RefreshConfigUtil.updateDbConfig(context);
            System.out.println(mailService);
//            TimeUnit.MILLISECONDS.sleep(20000);
        }
    }
}
