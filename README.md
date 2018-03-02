# HTTP/2 with Spring Boot based on jetty9

**NOTE:** you have to use OpenJDK to tun this sample. More details: TBD

## Building this project


From the command line, run
```bash
$ mvn clean install
```

## Running this project

From the command line, run
```bash
$ mvn spring-boot:run
```

From the command line, run
```bash
 java -Xbootclasspath/p:$HOME/.m2/repository/org/mortbay/jetty/alpn/alpn-boot/8.1.12.v20180117/alpn-boot-8.1.12.v20180117.jar -jar target/http2-0.0.1-SNAPSHOT.jar 
```

In your IDE, you have to run the `Http2Application` class and customize your run configuration to add a JVM argument:

```bash
 -Xbootclasspath/p:$HOME/.m2/repository/org/mortbay/jetty/alpn/alpn-boot/8.1.12.v20180117/alpn-boot-8.1.12.v20180117.jar
```

## How to use

See [https://localhost:8443](https://localhost:8443)

or use command line 

```bash
curl --http2 -k -v https://localhost:8443
```
