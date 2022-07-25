package com.paofuheshu.spring.bean.test.autowirecandidate;

import com.paofuheshu.spring.bean.domain.primary.PrimaryNormalBean;
import com.paofuheshu.spring.utils.IocUtil;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/11 22:05
 * @des 在primaryTest的测试中  当容器中某种类型的bean存在多个的时候，此时如果我们从容器中查找这种类型的bean的时候，
 * 会抛出异常：org.springframework.beans.factory.NoUniqueBeanDefinitionException
 * 原因：当从容器中按照类型查找一个bean对象的时候，容器中却找到了多个匹配的bean，此时spring
 * 不知道如何选择了，处于懵逼状态，就会报这个异常。
 * 这种异常主要出现在2种场景中：
 * 场景1：从容器容器中查找符合指定类型的bean，对应BeanFactory下面的方法：
 * <T> T getBean(Class<T> requiredType) throws BeansException;
 * 场景2：自动注入方式设置为byType的时候
 * 在primaryTest中可以通过primary属性来指定一个主要的bean，当从容器中查找的时候，如果有多个候选的bean
 * 符合查找的类型，此时容器将返回primary="true"的bean对象。
 * 此处介绍另一种方法来解决这个问题
 * spring还有一种方法也可以解决这个问题，可以设置某个bean是否在自动注入的时候是否为作为候选
 * bean，通过bean元素的autowire-candidate属性类配置，如下：
 * <bean id="serviceA" class="com.paofuheshu.spring.bean.domain.primary.PrimaryNormalBean$ServiceA" autowire-candidate="false" />
 * autowire-candidate：设置当前bean在被其他对象作为自动注入对象的时候，是否作为候选bean，默认值是true。
 * 举例说明：在primaryTest中  注册了两个serviceA和serviceB  在按照IService类型去查找bean时  会查找到这两个bean，此时就会报错，
 * 我们可以将其中的某一个bean的primary属性设置为true,或者使用下面的解决方案
 * 使用autowire-candidate属性  需要注意的是：autowire-candidate属性的默认值是true，所以此时查找到的bean仍然为两个
 * 此时我们需要只保留一个bean的autowire-candidate属性为true 并将其他bean的autowire-candidate属性设置为false
 * 使其他bean对象不在作为一个候选者 也可以解决问题
 * 详细配置见autowireCandidate.xml
 */
public class AutowireCandidateTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = IocUtil.context("autowireCandidate/autowireCandidate.xml");
        PrimaryNormalBean.IService bean = context.getBean(PrimaryNormalBean.IService.class);
        System.out.println(bean);
    }

    /**
     * autowire-candidates属性解析源码
     * beans元素是xml中定义bean的根元素，beans元素有个default-autowire-candidates属性，用于定义
     * 哪些bean可以作为候选者，default-autowire-candidates的值是个通配符如：
     * default-autowire-candidates="*Service"
     * bean元素的autowire-candidate属性，这个属性有3个可选值：
     * default：这个是默认值，autowire-candidate如果不设置，其值就是default
     * true：作为候选者
     * false：不作为候选者
     * spring中由beans元素的default-autowire-candidates和bean元素的autowire-candidate来决定最终
     * bean元素autowire-candidate的值，看一下bean元素autowire-candidates的解析源码：
     * 源码位置： org.springframework.beans.factory.xml.BeanDefinitionParserDelegate#parseBeanDefinitionAttributes
     * 主要代码如下
     */
        // 获取bean元素的autowire-candidate元素，autowire-candidate如果不设置，其值就是default
//        String autowireCandidate = ele.getAttribute(AUTOWIRE_CANDIDATE_ATTRIBUTE);
        // 判断bean元素的autowire-candidate元素是否等于"default"或者是否等于""
//        if (isDefaultValue(autowireCandidate)) {
            // 获取beans元素default-autowire-candidates属性值
//            String candidatePattern = this.defaults.getAutowireCandidates();
            // 判断获取beans元素default-autowire-candidates属性值是否为空，default-autowire- candidates默认值就是null
//            if (candidatePattern != null) {
                // 判断bean的名称是否和default-autowire-candidates的值匹配，如果匹配就将bean的 autowireCandidate置为true，否则置为false
//                String[] patterns = StringUtils.commaDelimitedListToStringArray(candidatePattern);
//                bd.setAutowireCandidate(PatternMatchUtils.simpleMatch(patterns, beanName));
//            }
//        } else {
            // 判断bean的autowire-candidate的值是否等于"true"
//            bd.setAutowireCandidate(TRUE_VALUE.equals(autowireCandidate));
//        }
//        如果上面判断都没有进去，autowireCandidate属性默认值就是true，这个在下面定义的：
//        org.springframework.beans.factory.support.AbstractBeanDefinition#autowireCandidate
//        private boolean autowireCandidate = true;

}
