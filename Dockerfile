FROM gradle:5.4.1-jdk8 as builder
COPY . /app
WORKDIR /app
RUN gradle build
RUN tar xf build/distributions/yahoo-finance-retriever-0.1.0-SNAPSHOT.tar

FROM openjdk:8u212-jre
COPY --from=builder /app/yahoo-finance-retriever-0.1.0-SNAPSHOT /
ENTRYPOINT ["/bin/yahoo-finance-retriever", "run-universe", "NYSE"]
