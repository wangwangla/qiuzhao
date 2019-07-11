# Spring  Mvc搭建过程

- maven

  ![1554093157264](E:\专题\java\Spring MVC\1554093157264.png)

![1554093199879](E:\专题\java\Spring MVC\1554093199879.png)

![1554093228521](E:\专题\java\Spring MVC\1554093228521.png)

![1554093251445](E:\专题\java\Spring MVC\1554093251445.png)



![1554093428503](E:\专题\java\Spring MVC\1554093428503.png)

pom文件

```
<dependency>
  <groupId>junit</groupId>
  <artifactId>junit</artifactId>
  <version>4.11</version>
  <scope>test</scope>
</dependency>
  <!--日志-->
<dependency>
  <groupId>org.slf4j</groupId>
  <artifactId>slf4j-log4j12</artifactId>
  <version>1.7.10</version>
</dependency>
  <!--J2EE-->
  <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>3.1.0</version>
  </dependency>
  <dependency>
      <groupId>javax.servlet.jsp</groupId>
      <artifactId>jsp-api</artifactId>
      <version>2.2</version>
  </dependency>
  <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>jstl</artifactId>
      <version>1.2</version>
  </dependency>
  <!--mysql驱动包-->
  <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>5.1.35</version>
  </dependency>
  <!--springframework-->
  <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-web</artifactId>
      <version>4.2.6.RELEASE</version>
  </dependency>
  <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-webmvc</artifactId>
      <version>4.2.6.RELEASE</version>
  </dependency>
  <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>4.2.6.RELEASE</version>
  </dependency>
  <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-test</artifactId>
      <version>4.2.6.RELEASE</version>
  </dependency>
  <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-jdbc</artifactId>
      <version>4.2.6.RELEASE</version>
  </dependency>
  <dependency>
      <groupId>com.github.stefanbirkner</groupId>
      <artifactId>system-rules</artifactId>
      <version>1.16.1</version>
      <scope>test</scope>
  </dependency>
  <dependency>
      <groupId>org.aspectj</groupId>
      <artifactId>aspectjweaver</artifactId>
      <version>1.8.9</version>
  </dependency>
  <!--其他需要的包-->
  <dependency>
      <groupId>org.apache.commons</groupId>
      <artifactId>commons-lang3</artifactId>
      <version>3.4</version>
  </dependency>
  <dependency>
      <groupId>commons-fileupload</groupId>
      <artifactId>commons-fileupload</artifactId>
      <version>1.3.1</version>
  </dependency>
```

![1554093575541](E:\专题\java\Spring MVC\1554093575541.png)

![1554093697657](E:\专题\java\Spring MVC\1554093697657.png)

![1554094521672](E:\专题\java\Spring MVC\1554094521672.png)







- 非maven

  - 创建项目

  ![1554091810442](/1554091993699.png)

![1554092314214](/1554092314214.png)

![1554092376369](/1554092376369.png)

![1554092537465](E:\专题\java\Spring MVC\1554092537465.png)

![1554092636822](E:\专题\java\Spring MVC\1554092636822.png)



![1554092679574](E:\专题\java\Spring MVC\1554092679574.png)

![1554092753061](C:\temp\1554092753061.png)



![1554092781586](E:\专题\java\Spring MVC\1554092781586.png)



![1554092817616](E:\专题\java\Spring MVC\1554092817616.png)



![1554092848661](E:\专题\java\Spring MVC\1554092848661.png)



![1554092912526](E:\专题\java\Spring MVC\1554092912526.png)

![1554092939442](E:\专题\java\Spring MVC\1554092939442.png)

![1554092980719](E:\专题\java\Spring MVC\1554092980719.png)



![1554093048617](E:\专题\java\Spring MVC\1554093048617.png)



















