FROM openjdk:8-jdk-alpine
ENV SPRING_PROFILES_ACTIVE ${SPRING_PROFILES_ACTIVE:-default}
ENV JAVA_OPTS ${JAVA_OPTS:-'-Xmx4096m'}
ENV DEBUG_OPTS ${DEBUG_OPTS}
ENV PORT ${PORT:-8080}

RUN adduser -D -g '' java

ADD target/*.jar /app.jar

VOLUME /tmp

RUN sh -c 'touch /app.jar'

USER java

EXPOSE ${PORT}

CMD java ${JAVA_OPTS} ${DEBUG_OPTS} -Djava.security.egd=file:/dev/./urandom -jar /app.jar
