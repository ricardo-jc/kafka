package util;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class KafkaConsumer {
    private final org.apache.kafka.clients.consumer.KafkaConsumer<String, String> consumer;
    private final Properties properties;

    public KafkaConsumer() {
        properties = new Properties();
        properties.put("bootstrap.servers", "127.0.0.1:9092");
        properties.put("group.id", "test");
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new org.apache.kafka.clients.consumer.KafkaConsumer<>(properties);
    }

    public void subscribe(String topic) {
        consumer.subscribe(Collections.singletonList(topic));
    }
    public ConsumerRecords<String, String> consume(Duration timeout) {
        return consumer.poll(timeout);
    }

    public static void main(String[] args) {
        KafkaConsumer consumer = new KafkaConsumer();
        consumer.subscribe("test");
        for(ConsumerRecord<String, String> record:consumer.consume(Duration.ofSeconds(10))) {
            System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
        }
    }
}
