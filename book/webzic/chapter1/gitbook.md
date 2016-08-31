Gitbook的安装与使用
=======

## 引言

![gitbook](img/gitbook.png)

最近要把平常编程的思路和心得记录下来，既然要写，那么就要geek一点，gitbook和普通的文本编辑器不一样，它可以像git一样，记录下你每次的编辑情况，单凭这一点就可以让有程序员强迫症的我欣喜若狂，更何况gitbook可以和github 360°无死角地配合，而且可以很方便地导出pdf等电子书格式。因此我要写点东西，gitbook成为了首选。


## 安装

* 安装nodejs

    官网：[https://nodejs.org](https://nodejs.org)，下载安装即可

* 安装gitbook

    安装好了nodejs之后，打开Node.js的程序控制台
    
    安装：
    > npm install -g gitbook-cli
    
    检查是否安装成功：
    > gitbook -V
    
    
    
* 安装可视化的gitbook编辑器，用这个东西的话就不用上面的代码行了

    [Windows版本](https://www.gitbook.com/editor/windows)
    

## gitbook使用

* 准备一个目录 

    > cd C:\lish\Study\Gitbook\Webzic
    
    
* 初始化

    > gitbook init
    
    初始化之后我们可以看到为我们生成了README.md和SUMMARY.md这两个文件
    
    README.md 这个文件相当于一本Gitbook的简介。
    
    SUMMARY.md 这个文件是一本书的目录结构
    
    
* 写作

    接下来你就可以创建单独的.md文件开始写作了，具体语法请参考markdown的语法规范。
    
    
* 预览

    > gitbook serve
    
    默认使用4000端口，如果要使用其他端口
    
    > gitbook serve -p 8080
    
    在浏览器中打开`http://localhost:8080`就可以预览了。这时候你会发现根目录下面多了一个`_book`的文件夹，这个就是预览时候的静态html文件.
    