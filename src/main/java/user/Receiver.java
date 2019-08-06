package user;

import kafka.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.time.Duration;

public class Receiver {
    public static void main(String[] args) {
        KafkaConsumer consumer = new KafkaConsumer();
        consumer.subscribe("user");
        System.out.println("Ready to receive.");
        try {
            while(true) {
                ConsumerRecords<String, String> records = consumer.consume(Duration.ofSeconds(60));
                if(records == null || records.isEmpty()) {
                    System.out.println("No more message, process terminated.");
                    break;
                }
                for(ConsumerRecord<String, String> record:records) {
                    System.out.println(record.value());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                consumer.commitSync();
            } finally {
                consumer.close();
            }
        }
    }
}
