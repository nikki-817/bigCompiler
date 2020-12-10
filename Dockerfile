FROM  williamyeh/java8:latest
WORKDIR /app
COPY src /app/src
RUN javac -encoding UTF-8 -sourcepath ./src -d ./target ./src/com/company/*.java



