FROM ubuntu:latest
LABEL authors="waelb"

ENTRYPOINT ["top", "-b"]
services:
  zipkin:
    container_name: zipkin
    image: openzipkin/zipkin
    ports:
      - "9411:9411"
    networks:
      - microservices-net
        kafka:
          image: confluentinc/cp-kafka:latest
          container_name: ms_kafka
          ports:
            - "9092:9092"
          depends_on:
            - zookeeper
          environment:
            KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
            KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR: 1
            KAFKA_TRANSACTION_STATE_LOG_MIN_ISR: 1
            KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
            KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: PLAINTEXT:PLAINTEXT,PLAINTEXT_HOST:PLAINTEXT
            KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092
          networks:
            - microservices-net
              mail-dev:
                container_name: ms-mail-dev
                image: maildev/maildev
                ports:
                  - 1080:1080
                  - 1025:1025
