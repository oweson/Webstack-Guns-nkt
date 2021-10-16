FROM openjdk:8-jdk-alpine
MAINTAINER oweson@163.com
ARG version
ENV LANG en_US.UTF-8
COPY target/Webstack-Guns-nkt-1.0.jar /root/
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone
CMD ["java","-jar","/root/Webstack-Guns-nkt-1.0.jar"]
EXPOSE 2023