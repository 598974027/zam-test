#FROM primetoninc/jdk:1.8
#MAINTAINER zhangam
#ENTRYPOINT ["curl"]
#CMD ["www.baidu.com"]
FROM primetoninc/jdk:1.8
MAINTAINER zhangam
ADD demo-0.0.1-SNAPSHOT.jar demo.jar
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ENTRYPOINT ["java","-jar","demo.jar"]