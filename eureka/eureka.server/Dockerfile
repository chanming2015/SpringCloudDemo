# Creates skyline-eureka-server
#
# docker build -t com.aispeech.skyline/eureka-server .
FROM java:openjdk-8-jre-alpine
MAINTAINER maosen.xu@aispeech.com

USER root
VOLUME ["/data"]
WORKDIR /data
RUN apk update && apk add curl tzdata && \
    cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && \
    echo "Asia/Shanghai" >  /etc/timezone

ENV SERVER_PORT=8761
ENV JAVA_OPTS="$JAVA_OPTS -server -Xmx256M -Xms256M -XX:NewRatio=2 -XX:SurvivorRatio=6 -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:+CMSParallelRemarkEnabled -XX:+CMSClassUnloadingEnabled -XX:LargePageSizeInBytes=32M -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70 -XX:SoftRefLRUPolicyMSPerMB=0 -XX:+HeapDumpOnOutOfMemoryError"
ENTRYPOINT exec java $JAVA_OPTS -jar eureka.server-1.0.0.jar
HEALTHCHECK --interval=15s --timeout=5s --retries=3 \ 
  CMD curl --silent --fail localhost:$SERVER_PORT/health || exit 1 
# mvn package before
COPY target/eureka.server-1.0.0.jar /data