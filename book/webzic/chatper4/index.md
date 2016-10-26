第四章 模板引擎
==

## 设计目标


## 参考链接

http://java-source.net/open-source/template-engines

https://github.com/PebbleTemplates/pebble

## 准备知识

编译原理

自己必须要能够写一个小的编译器



## 一个TestServlet


```
package com;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class TestServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String content = "<h1 style='color:blue'>Hello you GOOOD</h1>";
        PrintWriter out = null;
        try {
            out = resp.getWriter();
            out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

```

## web.xml 配置

为了使资源文件可以直接解析，可以如下配置：

web.xml的目录结构如下 `../WEB-INF/web.xml `

Servlet 3.0没关系


```

<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://java.sun.com/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://java.sun.com/xml/ns/javaee
         http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
         version="3.0">
    <servlet>
        <servlet-name>testServlet</servlet-name>
        <servlet-class>com.TestServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>testServlet</servlet-name>
        <url-pattern>/*</url-pattern>
    </servlet-mapping>

    <servlet-mapping>
        <servlet-name>default</servlet-name>
        <url-pattern>/assets/*</url-pattern>
    </servlet-mapping>
</web-app>

```

## 还可以这么配置

```
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://java.sun.com/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://java.sun.com/xml/ns/javaee
         http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
         version="3.0">

    <servlet-mapping>
        <servlet-name>default</servlet-name>
        <url-pattern>*.html</url-pattern>
        <url-pattern>*.css</url-pattern>
        <url-pattern>*.js</url-pattern>
        <url-pattern>*.jpg</url-pattern>
        <url-pattern>*.jpeg</url-pattern>
        <url-pattern>*.png</url-pattern>
    </servlet-mapping>

    <servlet>
        <servlet-name>testServlet</servlet-name>
        <servlet-class>com.TestServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>testServlet</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>

</web-app>

```

Servlet 3.0一下需要注意，一个servlet-mapping中只能有一个url-pattern，所以需要将.js .css等分开写