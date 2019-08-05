package user;

import kafka.KafkaProducer;

import java.util.Scanner;

public class Sender {
    public static void main(String[] args) {
        String message = "This is a message.";
        KafkaProducer producer = new KafkaProducer();
        producer.send("user","test", message);
        System.out.println("Default message sent.");
        System.out.println("Please input your message:");
        while(true){
            Scanner scanner = new Scanner(System.in);
            String data = scanner.next();
            if("^".equals(data)) break;
            producer.send("user",null, data);
        }
    }
}
