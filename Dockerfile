FROM openjdk:11-jre-slim-buster
RUN useradd -ms /bin/bash sefile
USER sefile
WORKDIR /home/sefile
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar -XX:+UseParallelGC -Xloggc:/home/sefile/gc.log -XX:+UseStringDeduplication -XX:+UseStringCache -XX:+OptimizeStringConcat"]