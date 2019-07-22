package util;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.Future;

public class KafkaProducer {
    private final Producer<String, String> producer;
    private final Properties properties;

    public KafkaProducer() {
        properties = new Properties();
        properties.put("bootstrap.servers", "127.0.0.1:9092");
        properties.put("acks", "all");
        properties.put("retries", 0);
        properties.put("batch.size", 16384);
        properties.put("linger.ms", 1);
        properties.put("buffer.memory", 33554432);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new org.apache.kafka.clients.producer.KafkaProducer<>(properties);
    }

    public void setServers(String servers) {
        properties.put("bootstrap.servers", servers);
    }
    public void setAcks(String acks) {
        properties.put("acks", acks);
    }
    public void setRetries(Integer retries) {
        properties.put("retries", retries);
    }
    public void setBatchSize(Integer batchSize) {
        properties.put("batch.size", batchSize);
    }
    public void setLingerMs(Integer lingerMs) {
        properties.put("linger.ms", lingerMs);
    }
    public void setBufferMemory(Integer bufferMemory) {
        properties.put("buffer.memory", bufferMemory);
    }
    public void setKeySerializer(String keySerializer) {
        properties.put("key.serializer", keySerializer);
    }
    public void setValueSerializer(String valueSerializer) {
        properties.put("value.serializer", valueSerializer);
    }

    public Future<RecordMetadata> send(String topic, String key, String data) {
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, key, data);
        return producer.send(producerRecord, new SendFailCallback());
    }
    public Future<RecordMetadata> send(String topic, String data) {
        return send(topic, "", data);
    }

    public static void main (String[] args) throws InterruptedException {
        KafkaProducer producer = new KafkaProducer();
        String topic = "test";
        String key = "";
        while (true) {
            Thread.sleep(2000);
            System.out.print("输入消息:");
            Scanner scanner = new Scanner(System.in);
            String data = scanner.next();
            if(data.equals("^")) break;
            producer.send(topic, key, data);
        }
    }
}
