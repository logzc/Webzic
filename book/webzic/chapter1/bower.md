Bower的安装与使用
=======

## 引言

![bower](img/bower.png)

Bower就相当于Maven,Gradle，只不过Maven和Gradle是用于Java的项目，而Bower是用于前端（html/css/js）的。



## 安装

* 安装nodejs

    官网：[https://nodejs.org](https://nodejs.org)，下载安装即可

    npm安装有时候汇报sha error，这时候使用国内镜像。
    
    镜像使用方法（三种办法任意一种都能解决问题，建议使用第三种，将配置写死，下次用的时候配置还在）:
    
    1.通过config命令
    
    npm config set registry http://registry.cnpmjs.org 
    npm info underscore （如果上面配置正确这个命令会有字符串response）
    2.命令行指定
    
    npm --registry http://registry.cnpmjs.org info underscore 
    3.编辑 ~/.npmrc 加入下面内容
    
    registry = http://registry.cnpmjs.org
    搜索镜像: http://cnpmjs.org

* 安装Git

    官网：[https://git-scm.com/downloads](https://git-scm.com/downloads)，下载安装即可


* 安装gitbook

    安装好了nodejs之后，打开Node.js的程序控制台
    
    安装：
    > npm install -g bower
    
    其中-g命令表示全局安装
    
    检查是否安装成功：
    > bower -v
    

    

## bower使用

* 帮助 

    > bower help
    
    > bower help init
    


* 准备一个目录 

    > cd C:/lish/Test
    
    在该目录下创建一个文件`.bowerrc`，这个文件是描述文件，内容如下：
    
    ```
    {
        "directory": "public/bower"
    }
    ```
    上面一句话就指定了bower下载来的库存放在public/bower目录下，不然默认会存放在bower_components，名字太长丑死了。
    
    
    
* 初始化

    > bower init
    
    根据提示一个一个输入就行了
    
    完成之后会生成一个bower.json的文件，这个文件就是记录的名字，作者，依赖项等信息，相当于pom.xml和build.gradle文件。
    
* 安装库

    > bower install jquery --save
    
    bower会把最新版的jquery下载下来，`--save`表示会把jquery的信息写到bower.json中去，并不是临时的下载。这时候public/bower中会出现jquery，并且bower.json中多了以下配置：
    ```
    "dependencies": {
        "jquery": "^3.1.0"
      }
    ```
    
    
* 多版本管理

    > bower install jquery-1x=jquery#1.9.1 --save
    
    对应在bower.json中会生成
    "jquery-1x": "juqery#1.9.1"
    
    
* 更新

    > bower update
    
    当你修改了bower.json中的依赖项版本时，你需要用这个命令去更新一下。
    
* 查看某个库可用的版本

    > bower info jquery
    
* 包的查找，模糊查找
    
    我要去找一个datepicker，https://github.com/eternicode/bootstrap-datepicker，但是不知道在bower中叫什么，那么就尝试这样搜索：
    
    > bower search bootstrap-datepicker
    
    我们发现第一个就是这个作者的，于是下载
    
    > bower install bootstrap-datepicker --save
    
* 包的卸载

    > bower uninstall jquery
    
    
    
    

    
    
