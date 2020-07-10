# import-data-to-excel
1.demo example java,
- excel,
- elasticsearch,
- kafka,
- auditting log history
2.Kafka stream 
- brand: demo-kafka-stream-consumer (input)
- brand: demo-kafka-stream (output)

---------------
#############################
# config kafka strean cloud #
#############################

* Link follow: https://dzone.com/articles/spring-cloud-stream-with-kafka
Add dependence:
<dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-stream</artifactId>
  </dependency>
  <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-stream-kafka</artifactId>
  </dependency>

Config file application.properties 
spring:
  cloud:
    stream:
      kafka:
        binder:
          brokers: localhost:9092
      bindings:
        in-put:
          destination: demo-excel // ~topic

spring:
    cloud:
      stream:
        kafka:
          binder:
            brokers: localhost:9092
        bindings:
          out-put:
            destination: demo-excel

Thêm config bằng cách thêm annotation 
@EnableBinding(GreetingsStreams.class)
public class StreamsConfig {
}

Để úng dụng có thể giao tiếp với Kafka, thì cần xác định luồng ra để viết message cho Kafka topic, và luồng đến để đọc message từ kafka topic.
@Input(INPUT)
SubscribableChannel input();
// method xác định luồng đến 

@Output(OUTPUT)
MessageChannel outboundGreetings();
// method xác định luồng ra để 

##############
# end config #
##############

