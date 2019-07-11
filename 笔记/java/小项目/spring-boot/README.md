## springboot

测试spring + boot + Jenkins+docker自动构建

配置pom
 <plugin>
        <groupId>com.spotify</groupId>
            <artifactId>docker-maven-plugin</artifactId>
            <version>0.4.13</version>
            <configuration>
               <!--<imageName>${docker.image.prefix}/${project.artifactId}</imageName>-->
               <imageName>mytest/springboottest</imageName>
               <dockerDirectory>src/main/resources</dockerDirectory>
                <resources>
                    <resource>
                         <targetPath>/</targetPath>
                         <directory>${project.build.directory}</directory>
                         <include>${project.build.finalName}.jar</include>
                    </resource>
                 </resources>
                </configuration>
            </plugin>
            <!-- end::plugin[] -->
        </plugins>
        <!--<finalName>gs-spring-boot-docker-master</finalName>-->
    </build>
编写Dockerfile文件
    FROM frolvlad/alpine-oraclejdk8:slim
    VOLUME /tmp
    ADD springboot-0.0.1-SNAPSHOT.jar app.jar
    RUN sh -c 'touch /app.jar'
    ENV JAVA_OPTS=""
    ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]


构建指令
    mvn docker:build
        
报错处理
"C:\Program Files\Java\jdk1.8.0_121\bin\java.exe" -Dmaven.home=C:\apache-maven-3.0.5 -Dclassworlds.conf=C:\apache-maven-3.0.5\bin\m2.conf "-javaagent:D:\Program Files (x86)\idea\idea\IntelliJ IDEA 2018.1.3\lib\idea_rt.jar=58938:D:\Program Files (x86)\idea\idea\IntelliJ IDEA 2018.1.3\bin" -Dfile.encoding=UTF-8 -classpath C:\apache-maven-3.0.5\boot\plexus-classworlds-2.4.jar org.codehaus.classworlds.Launcher -Didea.version=2018.1.3 -s C:\apache-maven-3.0.5\conf\settings.xml -Dmaven.repo.local=D:\Maven com.spotify:docker-maven-plugin:0.4.13:build -f pom.xml
这个是在执行的过程中，docker未开启
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal com.spotify:docker-maven-plugin:0.4.13:build (default-cli) on project springboot: Exception caught: Timeout: GET http://192.168.99.100:2376/version: com.spotify.docker.client.shaded.javax.ws.rs.ProcessingException: org.apache.http.conn.ConnectTimeoutException: Connect to 192.168.99.100:2376 [/192.168.99.100] failed: connect timed out -> [Help 1]
[ERROR] 
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR] 
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoExecutionException




springboot到此结束！