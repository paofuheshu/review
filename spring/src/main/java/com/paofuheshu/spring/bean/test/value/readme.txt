@Value的用法
系统中需要连接db，连接db有很多配置信息。
系统中需要发送邮件，发送邮件需要配置邮件服务器的信息。
还有其他的一些配置信息。
我们可以将这些配置信息统一放在一个配置文件中，上线的时候由运维统一修改。
那么系统中如何使用这些配置信息呢，spring中提供了@Value注解来解决这个问题。
通常我们会将配置信息以key=value的形式存储在properties配置文件中。
通过@Value("${配置文件中的key}")来引用指定的key对应的value。

@Value使用步骤
步骤一：使用@PropertySource注解引入配置文件
将@PropertySource放在类上面，如下
@PropertySource({"配置文件路径1","配置文件路径2"...})
@PropertySource注解有个value属性，字符串数组类型，可以用来指定多个配置文件的路径。

步骤二：使用@Value注解引用配置文件的值
通过@Value引用上面配置文件中的值：
语法
@Value("${配置文件中的key:默认值}")
@Value("${配置文件中的key}")
如：
@Value("${password:123}")
上面如果password不存在，将123作为值
@Value("${password}")
上面如果password不存在，值为${password}


最终解析@Value的过程：
1. 将@Value注解的value参数值作为Environment.resolvePlaceholders方法参数进行解析
2. Environment内部会访问MutablePropertySources来解析
3. MutablePropertySources内部有多个PropertySource，此时会遍历PropertySource列表，调用 PropertySource.getProperty方法来解析key对应的值

通过上面过程，如果我们想改变@Value数据的来源，只需要将配置信息包装为PropertySource对象，
丢到Environment中的MutablePropertySources内部就可以了。