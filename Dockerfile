FROM openjdk:10-jre-slim
RUN mkdir -p /app/leaderboard-service/
COPY build/libs/leaderboard.jar /app/leaderboard-service/leaderboard.jar
RUN apt-get update -y
RUN apt-get install -y vim
RUN apt-get install curl -y
RUN apt-get install -y procps
RUN apt-get install htop -y
RUN apt-get update -y
RUN java -version
VOLUME /tmp
RUN bash -c 'touch /app/leaderboard-service/leaderboard.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/leaderboard-service/leaderboard.jar"]
EXPOSE 8004
