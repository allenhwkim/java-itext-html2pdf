## Requirements
1. Install JDK 1.8 (You might already have it installed)
2. Install Maven 3.8 (https://phoenixnap.com/kb/install-maven-windows)
3. set JAVA_HOME and PATH. The following is for GitBash
    ```
    $ cat ~/.bashrc
    export JAVA_HOME=/c/Program\ Files/Java/jdk1.8.0_331
    export CLASSPATH=$CLASSPATH:/c/Users/allen.kim1/projects/jars/licensing-base-4.0.3.jar
    export PATH=$JAVA_HOME/bin:/c/Users/allen.kim1/apache-maven-3.8.6/bin/mvn:$PATH 
    ```

## To Start
```
$ git clone https://github.com/allenhwkim/java-itext-html2pdf.git 
$ cd java-itext-html2pdf
$ # 
$ mvn install
$ mvn exec:java -Dexec.mainClass=App
$ # open a browser and visit http://localhost:8080
$ # click "submit" button to see pdf generated from html
```
