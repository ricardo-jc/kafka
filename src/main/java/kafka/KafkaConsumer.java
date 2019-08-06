package kafka;

import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class KafkaConsumer implements Serializable {
    private final org.apache.kafka.clients.consumer.KafkaConsumer<String, String> consumer;
    public KafkaConsumer(){
        Properties consumerProperties = new Properties();
        try(InputStream input = KafkaConsumer.class.getResourceAsStream("/config/consumer.properties")) {
            consumerProperties.load(input);
        }catch(IOException e){
            e.printStackTrace();
        }
        consumer = new org.apache.kafka.clients.consumer.KafkaConsumer<>(consumerProperties);
    }
    public void subscribe(String topic) {
        consumer.subscribe(Collections.singletonList(topic));
    }
    public ConsumerRecords<String, String> consume(Duration timeout) {
        return consumer.poll(timeout);
    }
    public void commitSync() {
        consumer.commitSync();
    }
    public void commitAsync() {
        consumer.commitAsync();
    }
    public void close() {
        consumer.close();
    }
}
