import java.util.Properties;

public class SimpleConsumer {
    private final ConsumerConnector consumer;
    private final String topic;

    public SimpleConsumer(String zookeeper, String groupId, String topic) {
        Properties properties = new Properties();
        //定义连接zookeeper信息
        properties.put("zookeeper.connect", zookeeper);
        //定义Consumer所有的groupId
        properties.put("group.id", groupId);
        properties.put("zookeeper.session.timeout.ms", "500");
        
    }
}
