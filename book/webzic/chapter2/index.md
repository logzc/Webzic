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
    
    
    