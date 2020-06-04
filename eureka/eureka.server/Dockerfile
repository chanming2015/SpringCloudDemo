# Creates skyline-eureka-server
#
# docker build -t docker.v2.aispeech.com/aispeech/skyline-eureka-server-base:1.0 .
FROM docker.v2.aispeech.com/aispeech/lyra-base:1.1
MAINTAINER maosen.xu@aispeech.com

ENV SERVER_PORT=8761
ENV JAVA_OPTS="$JAVA_OPTS -server -Xms256m -Xmx256m -Xss512k -XX:+UseG1GC -XX:+PrintGCDetails -Xloggc:/data/gc.log -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=100M -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/data"
ENTRYPOINT exec java $JAVA_OPTS -jar eureka.server-1.0.0.jar
# mvn package before
COPY target/eureka.server-1.0.0.jar /data