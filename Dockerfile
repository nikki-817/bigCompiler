FROM openjdk:8
WORKDIR /
COPY src /
COPY target /
RUN javac -encoding UTF-8 -sourcepath ./src -d ./target ./src/com/company/*.java



