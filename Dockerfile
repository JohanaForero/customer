FROM amazoncorretto:21.0.4-alpine3.20

EXPOSE 8090

ENV JAVA_OPTS=" -Duser.timezone=America/Bogota"

WORKDIR /app

COPY target/sunbelt-0.0.1-SNAPSHOT.jar /app/sunbelt.jar

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS  -jar /app/sunbelt.jar" ]