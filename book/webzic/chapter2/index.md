第一章 小控件
=====

## 类加载器

动态加载，[javaassist实例](http://yonglin4605.iteye.com/blog/1396494) 

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