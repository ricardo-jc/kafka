package kafka;

import lombok.extern.java.Log;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;
import java.util.concurrent.Future;

@Log
public class KafkaProducer implements Serializable {
    private final Producer<String, String> producer;
    public KafkaProducer(){
        Properties producerProperties = new Properties();
        try(InputStream input = KafkaProducer.class.getResourceAsStream("/config/producer.properties")) {
            producerProperties.load(input);
        }catch(IOException e){
            e.printStackTrace();
        }
        producer = new org.apache.kafka.clients.producer.KafkaProducer<>(producerProperties);
    }
    public Future<RecordMetadata> send(String topic, String key, String data) {
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, key, data);
        return producer.send(producerRecord, (x, y) -> {
            if(x != null) System.out.println("message send success.");
            if(y != null) y.printStackTrace();
        });
    }
}
