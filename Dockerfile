FROM openjdk:8
WORKDIR /app
COPY src /app/src
RUN javac -encoding UTF-8 -sourcepath ./app/src -d ./target ./app/src/com/company/*.java



