# Creates skyline-eureka-server
#
# docker build -t docker.v2.aispeech.com/aispeech/skyline-eureka-server-base:1.2 .
FROM docker.v2.aispeech.com/aispeech/lyra-base:1.4
MAINTAINER maosen.xu@aispeech.com

ENV SERVER_PORT=8761
ENV JAVA_OPTS="$JAVA_OPTS -server -Xms64m -Xmx128m -Xss512k -XX:+UseG1GC -XX:+PrintGCDetails -Xloggc:/data/gc.log -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=100M -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/data"
ENTRYPOINT tini java $JAVA_OPTS -jar eureka.server-1.0.0.jar
# mvn package before
COPY target/eureka.server-1.0.0.jar /data