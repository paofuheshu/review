什么是父子容器
创建spring容器的时候，可以给当前容器指定一个父容器。

BeanFactory的方式
// 创建父容器
parentFactory DefaultListableBeanFactory parentFactory = new DefaultListableBeanFactory();
// 创建一个子容器childFactory
DefaultListableBeanFactory childFactory = new DefaultListableBeanFactory();
// 调用setParentBeanFactory指定父容器
childFactory.setParentBeanFactory(parentFactory);

ApplicationContext的方式
// 创建父容器
AnnotationConfigApplicationContext parentContext = new AnnotationConfigApplicationContext();
// 启动父容器
parentContext.refresh();
// 创建子容器
AnnotationConfigApplicationContext childContext = new AnnotationConfigApplicationContext();
// 给子容器设置父容器
childContext.setParent(parentContext);
// 启动子容器
childContext.refresh();

父子容器特点
1. 父容器和子容器是相互隔离的，他们内部可以存在名称相同的bean
2. 子容器可以访问父容器中的bean，而父容器不能访问子容器中的bean
3. 调用子容器的getBean方法获取bean的时候，会沿着当前容器开始向上面的容器进行查找，直到找到对应的bean为止
4. 子容器中可以通过任何注入方式注入父容器中的bean，而父容器中是无法注入子容器中的bean，原因是第2点

父子容器使用注意点
我们使用容器的过程中，经常会使用到的一些方法，这些方法通常会在下面的两个接口中
org.springframework.beans.factory.BeanFactory
org.springframework.beans.factory.ListableBeanFactory

BeanFactory接口，是spring容器的顶层接口，这个接口中的方法是支持容器嵌套结构查找的，比如我
们常用的getBean方法，就是这个接口中定义的，调用getBean方法的时候，会从沿着当前容器向上查
找，直到找到满足条件的bean为止。

而ListableBeanFactory这个接口中的方法是不支持容器嵌套结构查找的
获取指定类型的所有bean名称，调用这个方法的时候只会返回当前容器中符合条件的bean，而不会去
递归查找其父容器中的bean。

有没有方式解决ListableBeanFactory接口不支持层次查找的问题？
spring中有个工具类就是解决这个问题的
org.springframework.beans.factory.BeanFactoryUtils
这个类中提供了很多静态方法，有很多支持层次查找的方法，名称中包含有Ancestors 的都是支持层次查找的。

springmvc中只使用一个容器是否可以？
只使用一个容器是可以正常运行的。

那么springmvc中为什么需要用到父子容器？
通常我们使用springmvc的时候，采用3层结构，controller层，service层，dao层；父容器中会包含
dao层和service层，而子容器中包含的只有controller层；这2个容器组成了父子容器的关系，
controller层通常会注入service层的bean。
采用父子容器可以避免有些人在service层去注入controller层的bean，导致整个依赖层次是比较混乱的。
父容器和子容器的需求也是不一样的，比如父容器中需要有事务的支持，会注入一些支持事务的扩展组
件，而子容器中controller完全用不到这些，对这些并不关心，子容器中需要注入一下springmvc相关的
bean，而这些bean父容器中同样是不会用到的，也是不关心一些东西，将这些相互不关心的东西隔
开，可以有效的避免一些不必要的错误，而父子容器加载的速度也会快一些。