package com.paofuheshu.spring.bean.domain.dependon;

import lombok.Data;
import org.springframework.beans.factory.DisposableBean;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/11 19:30
 * @des  通过depend-on来干预bean创建和销毁顺序
 */
@Data
public class DependOnBean {

    public static class Bean1 implements DisposableBean {
        public Bean1() { System.out.println(this.getClass() + " constructor!");
        }

        @Override public void destroy() throws Exception {
            System.out.println(this.getClass() + " destroy()");
        }
    }

    public static class Bean2 implements DisposableBean {
        public Bean2() {
            System.out.println(this.getClass() + " constructor!");
        }
        @Override public void destroy() throws Exception {
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
