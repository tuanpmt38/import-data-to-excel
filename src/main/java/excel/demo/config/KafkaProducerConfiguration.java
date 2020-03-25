package excel.demo.config;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaProducerConfiguration {

  @Value(value = "${spring.kafka.producer.bootstrap-servers}")
  private String bootstrapAddress;

  /**
   * Producer configs map.
   *
   * @return the map
   */
  @Bean
  public Map<String, Object> producerConfigs() {
    Map<String, Object> props = new HashMap<>();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    return props;
  }

  /**
   * Producer factory producer factory.
   *
   * @return the producer factory
   */
  @Bean
  public ProducerFactory<String, ? extends Object> producerFactory() {
    return new DefaultKafkaProducerFactory<>(producerConfigs());
  }

  /**
   * Kafka template kafka template.
   *
   * @return the kafka template
   */
  @Bean
  public KafkaTemplate<String, ? extends Object> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }

}
