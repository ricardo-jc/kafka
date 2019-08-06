package user;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import util.KafkaClient;

import java.util.Scanner;

public class Producer {
    public static void main(String[] args) {
        KafkaClient client = new KafkaClient();
        KafkaProducer<String, String> producer = client.getProducer();
        try (Scanner scanner = new Scanner(System.in)){
            while (scanner.hasNext()) {
                String message = scanner.next();
                producer.send(new ProducerRecord<>("user", null, message), (x, y) -> {
                    if (x != null) System.out.println("send success.");
                    if (y != null) y.printStackTrace();
                });
            }
        }
    }
}
