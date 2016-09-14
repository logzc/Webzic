java命令行常用命令
==




## jar

实验目录结构：

build

---- test

-------- Test.class

-------- Hello.class

src

---- test

-------- Test.java

-------- Hello.java

lib

---- common.jar

---- xxx.jar

* 编译

> javac -d build/ src/test/*.java

这条命令会把src/test下面的所有java文件编译到build下面，包名依旧对应

* 打jar包

> jar cvf lib/xxx.jar -C build .

这个jar里面的MANIFEST.MF都是默认的值。

* 查看jar包中内容

> jar tf lib/common.jar

* 解压jar包,到当前目录下，如果要到指定目录解压，那么最好cd过去

> jar xf lib/xxx.jar

当然，反正jar是压缩包，用unzip随便玩儿。

> unzip lib/xxx.jar -d tmp

* 运行

> java -cp "lib/*" test.Hello

* 添加MANIFEST.MF 放进tmp文件夹

```
Manifest-Version: 1.0  
Created-By: lishuang
Main-Class: test.Hello

```

> jar cvfm lib/xxx.jar tmp/MANIFEST.MF -C build .

* 运行带有Main class的jar包

> java -jar lib/xxx.jar

