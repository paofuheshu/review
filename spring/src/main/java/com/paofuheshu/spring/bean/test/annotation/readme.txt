什么是注解？
代码中注释大家都熟悉，注释是给开发者看的，可以提升代码的可读性和可维护性，但是对于java编
译器和虚拟机来说是没有意义的，编译之后的字节码文件中是没有注释信息的；而注解和注释有点类
似，唯一的区别就是注释是给人看的，而注释是给编译器和虚拟机看的，编译器和虚拟机在运行的过程
中可以获取注解信息，然后可以根据这些注解的信息做各种想做的事情。比如：@Override就是一个注解，
加在方法上，标注当前方法重写了父类的方法，当编译器编译代码的时候，
会对@Override标注的方法进行验证，验证其父类中是否也有同样签名的方法，否则报错，通过这个注
解是不是增强了代码的安全性。
总的来说：注解是对代码的一种增强，可以在代码编译或者程序运行期间获取注解的信息，然后根据这
些信息做各种牛逼的事情。

注解如何使用？
3个步骤：
1. 定义注解
2. 使用注解
3. 获取注解信息做各种牛逼的事情

定义注解
关于注解的定义，先来几个问题：
1. 如何为注解定义参数？
2. 注解可以用在哪里？
3. 注解会被保留到什么时候？

定义注解语法
jdk中注解相关的类和接口都定义在 java.lang.annotation 包中。
注解的定义和我们常见的类、接口类似，只是注解使用 @interface 来定义，如下定义一个名称为
MyAnnotation 的注解：
public @interface MyAnnotation {}

注解中定义参数
注解有没有参数都可以，定义参数如下：
public @interface 注解名称{
    [public] 参数类型 参数名称1() [default 参数默认值];
    [public] 参数类型 参数名称2() [default 参数默认值];
    [public] 参数类型 参数名称n() [default 参数默认值];
}

注解中可以定义多个参数，参数的定义有以下特点：
1. 访问修饰符必须为public，不写默认为public
2. 该元素的类型只能是基本数据类型、String、Class、枚举类型、注解类型（体现了注解的嵌套效
果）以及上述类型的一位数组
3. 该元素的名称一般定义为名词，如果注解中只有一个元素，请把名字起为value（后面使用会带来
便利操作）
4. 参数名称后面的 () 不是定义方法参数的地方，也不能在括号中定义任何参数，仅仅只是一个特殊
的语法
5. default 代表默认值，值必须和第2点定义的类型一致
6. 如果没有默认值，代表后续使用注解时必须给该类型元素赋值

指定注解的使用范围：@Target
使用@Target注解定义注解的使用范围，如下：
@Target(value = {ElementType.TYPE,ElementType.METHOD})
public @interface MyAnnotation {}
上面指定了 MyAnnotation 注解可以用在类、接口、注解类型、枚举类型以及方法上面，自定义注解上
也可以不使用@Target注解，如果不使用，表示自定义注解可以用在任何地方。
看一下 @Target 源码：
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Target {
    ElementType[] value();
}
有一个参数value，是ElementType类型的一个数组，再来看一下 ElementType ，是个枚举，源码如下：
package java.lang.annotation;
/*注解的使用范围*/
public enum ElementType {
    /*类、接口、枚举、注解上面*/
    TYPE,
    /*字段上*/
    FIELD,
    /*方法上*/
    METHOD,
    /*方法的参数上*/
    PARAMETER,
    /*构造函数上*/
    CONSTRUCTOR,
    /*本地变量上*/
    LOCAL_VARIABLE,
    /*注解上*/
    ANNOTATION_TYPE,
    /*包上*/
    PACKAGE,
    /*类型参数上*/
    TYPE_PARAMETER,
    /*类型名称上*/
    TYPE_USE
}

指定注解的保留策略：@Retention
我们先来看一下java程序的3个过程
1. 源码阶段
2. 源码被编译为字节码之后变成class文件
3. 字节码被虚拟机加载然后运行
那么自定义注解会保留在上面哪个阶段呢？可以通过 @Retention 注解来指定，如：
@Retention(RetentionPolicy.SOURCE)
public @interface MyAnnotation {}
上面指定了 MyAnnotation 只存在于源码阶段，后面的2个阶段都会丢失。
来看一下@Retention
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Retention {
    RetentionPolicy value();
}
有一个value参数，类型为RetentionPolicy枚举，如下：
public enum RetentionPolicy {
    /*注解只保留在源码中，编译为字节码之后就丢失了，也就是class文件中就不存在了*/
    SOURCE,
    /*注解只保留在源码和字节码中，运行阶段会丢失*/
    CLASS,
    /*源码、字节码、运行期间都存在*/
    RUNTIME
}

使用注解
语法
将注解加载使用的目标上面，如下：
@注解名称(参数1=值1,参数2=值2,参数n=值n)
目标对象

定义注解示例见com/paofuheshu/spring/bean/domain/annotation包下方注解示例

注解信息的获取
为了运行时能准确获取到注解的相关信息，Java在 java.lang.reflect 反射包下新增了
AnnotatedElement 接口，它主要用于表示目前正在虚拟机中运行的程序中已使用注解的元素，通过该
接口提供的方法可以利用反射技术地读取注解的信息
Package：用来表示包的信息
Class：用来表示类的信息
Constructor：用来表示构造方法信息
Field：用来表示类中属性信息
Method：用来表示方法信息
Parameter：用来表示方法参数信息
TypeVariable：用来表示类型变量信息，如：类上定义的泛型类型变量，方法上面定义的泛型类型变量

AnnotatedElement常用方法
返回值                     方法名称                                                            说明
<A extends Annotation>    getAnnotation(Class<A> annotationClass)                           该元素如果存在指定类型的注解，则返回这些注解，否则返回 null。
Annotation[]              getAnnotations()                                                  返回此元素上存在的所有注解，包括从父类继承的
boolean                   isAnnotationPresent(Class<?extends Annotation> annotationClass)   如果指定类型的注解存在于此元素上，则返回true，否则返回 false。
Annotation[]              getDeclaredAnnotations()                                          返回直接存在于此元素上的所有注解，注意，不包括父类的注解，
                                                                                            调用者可以随意修改返回的数组；这不会对其他调用者返回的数组产生任何影响，没有则返回长度为0的数组
