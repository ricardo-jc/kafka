package util;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

public class KafkaClient implements Serializable {
    private KafkaConsumer<String, String> consumer = null;
    private KafkaProducer<String, String> producer = null;

    public KafkaConsumer<String, String> getConsumer() {
        if (consumer != null) return consumer;
        Properties consumerProperties = new Properties();
        try(InputStream input = kafka.KafkaConsumer.class.getResourceAsStream("/config/consumer.properties")) {
            consumerProperties.load(input);
        }catch(IOException e){
            e.printStackTrace();
        }
        return consumer = new KafkaConsumer<>(consumerProperties);
    }
    public KafkaProducer<String, String> getProducer() {
        if(producer != null) return producer;
        Properties producerProperties = new Properties();
        try(InputStream input = kafka.KafkaProducer.class.getResourceAsStream("/config/producer.properties")) {
            producerProperties.load(input);
        }catch(IOException e){
            e.printStackTrace();
        }
        return producer = new KafkaProducer<>(producerProperties);
    }
}
