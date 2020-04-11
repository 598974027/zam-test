FROM primetoninc/jdk:1.8
MAINTAINER zhangam
ADD DGS.N60-1.1.0.jar dgs.jar
ENTRYPOINT ["java","-jar","dgs.jar"]
CMD ["-url","http://113.57.170.58:63044/","-key","dgs","-path","DGS-N60","-local"]