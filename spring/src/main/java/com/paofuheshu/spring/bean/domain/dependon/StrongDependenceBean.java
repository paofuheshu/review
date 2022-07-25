package com.paofuheshu.spring.bean.domain.dependon;

import lombok.Data;
import org.springframework.beans.factory.DisposableBean;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/11 19:12
 * @des  强依赖的bean创建和销毁顺序
 * 创建Bean2的时候需要传入一个bean1对象，对bean1产生了强依赖
 * 创建Bean3的时候需要传入一个bean2对象，对bean2产生了强依赖
 * 此时的依赖关系是bean3->bean2->bean1
 */
@Data
public class StrongDependenceBean {

    public static class Bean1 implements DisposableBean {

        public Bean1() {
            System.out.println(this.getClass() + " constructor!");
        }

        @Override
        public void destroy() throws Exception {
            System.out.println(this.getClass() + " destroy()");
        }
    }

    /**
     * 创建Bean2的时候需要传入一个bean1对象，对bean1产生了强依赖
     */
    public static class Bean2 implements DisposableBean {

        private Bean1 bean1;

        public Bean2(Bean1 bean1) {
            this.bean1 = bean1;
            System.out.println(this.getClass() + " constructor!");
        }

        @Override
        public void destroy() throws Exception {
            System.out.println(this.getClass() + " destroy()");
        }
    }

    /**
     * 创建Bean3的时候需要传入一个bean2对象，对bean2产生了强依赖
     */
    public static class Bean3 implements DisposableBean {

        private Bean2 bean2;

        public Bean3(Bean2 bean2) {
            this.bean2 = bean2;
            System.out.println(this.getClass() + " constructor!");
        }

        @Override
        public void destroy() throws Exception {
            System.out.println(this.getClass() + " destroy()");
        }
    }
}
