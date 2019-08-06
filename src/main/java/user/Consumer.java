package user;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import util.KafkaClient;

import java.time.Duration;
import java.util.Collections;

public class Consumer {
    public static void main(String[] args) {
        KafkaClient client = new KafkaClient();
        KafkaConsumer<String, String> consumer = client.getConsumer();
        consumer.subscribe(Collections.singletonList("user"));
        try{
            while(true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(10));
                for(ConsumerRecord record:records) {
                    System.out.println(record.value());
                }
            }
        } finally {
            consumer.close();
        }
    }
}
