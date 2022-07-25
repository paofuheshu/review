上面是我们原本的程序，只涉及到两个接口实现类的方法调用，十分简单，但突然有个需求：调用IService接口中的任何方法的时候，需要记录方法的耗时。
IService接口有2个实现类ServiceA和ServiceB，我们可以在这两个类的所有方法中加上统计耗时的代
码，如果IService接口有几十个实现，是不是要修改很多代码，所有被修改的方法需重新测试？是不是
非常痛苦，不过上面这种修改代码的方式倒是可以解决问题，只是增加了很多工作量（编码 & 测试）
突然有一天，又多了一个需求，要将这些耗时统计发送到监控系统用来做监控报警使用。
此时是不是又要去一个修改上面的代码？又要去测试？此时的系统是难以维护
还有假如上面这些类都是第三方以jar包的方式提供给我们的，此时这些类都是class文件，此时我们无法去修改源码。

解决方法：可以为IService接口创建一个代理类，通过这个代理类来间接访问IService接口的实现类，在这个代理类中去做耗时及发送至监控的代码

假如现在我们又多了一个需求：
需要给系统中所有接口都加上统计耗时的功能，若按照上面的方式，我们需要给每个接口创建一个代理类，
此时代码量和测试的工作量也是巨大的，那么我们能不能写一个通用的代理类，来满足上面的功能呢？
此时可以通过代理来实现
通用代理的2种实现：
1. jdk动态代理
2. cglib代理

jdk动态代理：
jdk中为实现代理提供了支持，主要用到2个类：
java.lang.reflect.Proxy
java.lang.reflect.InvocationHandler
jdk自带的代理使用上面有个限制，只能为接口创建代理类，如果需要给具体的类创建代理类，需要用cglib
java.lang.reflect.Proxy：
这是jdk动态代理中主要的一个类，里面有一些静态方法会经常用到
getProxyClass方法:为指定的接口创建代理类，返回代理类的Class对象
public static Class<?> getProxyClass(ClassLoader loader, Class<?>... interfaces)
参数说明：
loader：定义代理类的类加载器
interfaces：指定需要实现的接口列表，创建的代理默认会按顺序实现interfaces指定的接口

newProxyInstance方法:创建代理类的实例对象
public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h)
这个方法先为指定的接口创建代理类，然后会生成代理类的一个实例，最后一个参数比较特殊，是InvocationHandler类型的，这个是个接口如下：
public Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
上面方法会返回一个代理对象，当调用代理对象的任何方法的时候，会就被 InvocationHandler 接口的 invoke 方法处理，所以主要代码需要卸载 invoke 方法中，

isProxy方法：判断指定的类是否是一个代理类
public static boolean isProxyClass(Class<?> cl)

getInvocationHandler方法：获取代理对象的 InvocationHandler 对象
public static InvocationHandler getInvocationHandler(Object proxy) throws IllegalArgumentException

创建代理：方式一
步骤
1.调用Proxy.getProxyClass方法获取代理类的Class对象
2.使用InvocationHandler接口创建代理类的处理器
3.通过代理类和InvocationHandler创建代理对象
4.上面已经创建好代理对象了，接着我们就可以使用代理对象了

创建代理：方式二
步骤
1.使用InvocationHandler接口创建代理类的处理器
2.使用Proxy类的静态方法newProxyInstance直接创建代理对象
3.使用代理对象

Proxy使用注意
1. jdk中的Proxy只能为接口生成代理类，如果你想给某个类创建代理类，那么Proxy是无能为力的，
此时需要用到cglib了。
2. Proxy类中提供的几个常用的静态方法需要掌握
3. 通过Proxy创建代理对象，当调用代理对象任意方法时候，会被InvocationHandler接口中的
invoke方法进行处理，这个接口内容是关键

cglib代理详解
什么是cglib
jdk动态代理只能为接口创建代理，使用上有局限性。实际的场景中我们的类不一定有接口，此时如果我
们想为普通的类也实现代理功能，我们就需要用到cglib来实现了。
cglib是一个强大、高性能的字节码生成库，它用于在运行时扩展Java类和实现接口；本质上它是通过动
态的生成一个子类去覆盖所要代理的类（非final修饰的类和方法）。Enhancer可能是CGLIB中最常用的
一个类，和jdk中的Proxy不同的是，Enhancer既能够代理普通的class，也能够代理接口。Enhancer创
建一个被代理对象的子类并且拦截所有的方法调用（包括从Object中继承的toString和hashCode方
法）。Enhancer不能够拦截final方法，例如Object.getClass()方法，这是由于Java final方法语义决定
的。基于同样的道理，Enhancer也不能对final类进行代理操作。

CGLIB和Java动态代理的区别
1. Java动态代理只能够对接口进行代理，不能对普通的类进行代理（因为所有生成的代理类的父类为
Proxy，Java类继承机制不允许多重继承）；CGLIB能够代理普通类；
2. Java动态代理使用Java原生的反射API进行操作，在生成类上比较高效；CGLIB使用ASM框架直接
对字节码进行操作，在类的执行过程中比较高效
具体代码实现和类见proxy包下方的测试类和对象