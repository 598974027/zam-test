FROM primetoninc/jdk:1.8
MAINTAINER zhangam
ADD CLB.N60-1.1.0.jar clb.jar
ENTRYPOINT ["java","-jar","clb.jar"]
CMD ["-url","http://113.57.170.58:63044/","-key","clb","-path","CLB-N60","-local"]