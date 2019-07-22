package util;

import lombok.extern.java.Log;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.logging.Level;

@Log
public class SendFailCallback implements Callback {
    @Override
    public void onCompletion(RecordMetadata matadata, Exception exception) {
        if (matadata != null) {
            log.info("message send success.");
        }
        if (exception != null) {
            log.log(Level.WARNING, exception.getMessage());
        }
    }
}
