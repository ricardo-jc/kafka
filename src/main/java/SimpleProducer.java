import java.util.Properties;

public class SimpleProducer {
    private static Producer<Integer, String> producer;
    private final Properties properties = new Properties();
    public SimpleProducer() {
        //定义连接的broker list
        properties.put("metadata.broker.list", "192.168.1.216:9092");
        //定义序列化类，Java中对象传输前要序列化
        properties.put("serializer.class","kafka.serializer.StringEncoder");
        producer = new Producer<Integer, String>(new ProducerConfig(properties));
    }
    public static void main(String[] args) {
        SimpleProducer sp = new SimpleProducer();
        //定义topic
        String topic = "myTopic";
        //定义发给topic的信息
        String messageStr = "This is a message."；
        //构建消息对象
        KeyedMessage<Integer, String> data = new KeyedMessage<Integer, String>(topic, messageStr);
        //推送到broker
        producer.send(data);
        producer.close();
    }
}
