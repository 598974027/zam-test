FROM primetoninc/jdk:1.8
ADD ROOT/producer-1.0-SNAPSHOT.jar zam.jar
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ENTRYPOINT ["java","-jar","zam.jar"]