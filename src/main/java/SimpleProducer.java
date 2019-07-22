import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Scanner;

public class SimpleProducer {
    private final Producer<String, String> kafkaProducer;
    private static int key = 0;
    public final static String TOPIC = "JAVA_TOPIC";

    private SimpleProducer() {
        kafkaProducer = createKafkaProducer();
    }

    private Producer<String, String> createKafkaProducer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "127.0.0.1:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        return new KafkaProducer<>(props);
    }

    private void produce() {
        while (true) {
            System.out.print("输入消息:");
            Scanner scanner = new Scanner(System.in);
            String data = scanner.next();
            if(data.equals("^")) break;
            kafkaProducer.send(new ProducerRecord<>(TOPIC, String.valueOf(++key), data), (x, y) -> {
                if(x != null) {
                    System.out.println("message send success.");
                }
                if(y != null) {
                    System.out.println("message send failed.");
                    y.printStackTrace();
                }
            });
        }
    }

    public static void main(String[] args) {
        new SimpleProducer().produce();
    }
}