package com.paofuheshu.spring.bean.domain.dependon;

import lombok.Data;
import org.springframework.beans.factory.DisposableBean;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/11 19:02
 * @des  无任何依赖的bean创建的顺序
 */
@Data
public class NormalBean {

    /**
     * DisposableBean接口，这个是spring容器提供的一个接口，这个接口中有个
     * destroy方法，我们的bean类可以实现这个接口，当我们调用容器的close方法关闭容器的时候，
     * spring会调用容器中所有bean的destroy方法，用来做一些清理的工作，
     */
    public static class Bean1 implements DisposableBean {

        public Bean1() {
            System.out.println(this.getClass() + " constructor!");
        }

        @Override
        public void destroy() throws Exception {
            System.out.println(this.getClass() + " destroy()");
        }
    }

    public static class Bean2 implements DisposableBean {

        public Bean2() {
            System.out.println(this.getClass() + " constructor!");
        }

        @Override
        public void destroy() throws Exception {
            System.out.println(this.getClass() + " destroy()");
        }
    }

    public static class Bean3 implements DisposableBean {

        public Bean3() {
            System.out.println(this.getClass() + " constructor!");
        }

        @Override
        public void destroy() throws Exception {
            System.out.println(this.getClass() + " destroy()");
        }
    }
}
