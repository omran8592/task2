FROM openjdk

WORKDIR /application

COPY omran.java .

RUN javac omran.java

CMD java omran