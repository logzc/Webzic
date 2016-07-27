第一章 小控件
=====

## 类加载器

动态加载，[javaassist例子](http://yonglin4605.iteye.com/blog/1396494) 

运行时扫描Annotation, [Scanning Java Annotations at Runtime](https://bill.burkecentral.com/2008/01/14/scanning-java-annotations-at-runtime/)


查看Java classpath

```java

public class Main {
    public static void main(String[] args){

        String pathString=System.getProperty("java.class.path");
        String[] paths=pathString.split(";");
        for (String p:paths){
            System.out.println(p);
        }
    }
}


```


## 在java8中遍历一个目录下的所有文件

    File directory=...
    
    Files.walk(directory.toPath()).filter(path -> Files.isRegularFile(path)).map(Path::toFile).collect(Collectors.toList());
    
    
## jar实用的操作方法

   [JAVA操作jar文件的实用工具类](http://javasam.iteye.com/blog/1486803)
    
    
## ServletRequest中获取有用信息

    // Example: http://myhost:8080/people?lastname=Fox&age=30
    
    String uri = request.getScheme() + "://" +   // "http" + "://
                 request.getServerName() +       // "myhost"
                 ":" +                           // ":"
                 request.getServerPort() +       // "8080"
                 request.getRequestURI() +       // "/people"
                 "?" +                           // "?"
                 request.getQueryString();       // "lastname=Fox&age=30"
                 
                 
                 
## Spring RequestMapping 的工作原理


## 查看java的字节码

    "C:\Program Files\Java\jdk1.8.0_05\bin\javap" -c -v C:\lish\Group\Logzc\Webzic\library\build\classes\test\com\logzc\webzic\reflection\method\MethodTestBean0.class

