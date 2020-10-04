package poc.kafka;

import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;
 
@Component
public class Listener {
	
	private static Logger LOGGER = LoggerFactory.getLogger(Listener.class);
	
	private final String topicName = "test";
	
	
    public CountDownLatch countDownLatch1 = new CountDownLatch(3);
    public CountDownLatch countDownLatch2 = new CountDownLatch(3);
    public CountDownLatch countDownLatch3 = new CountDownLatch(3);
 

    
    @KafkaListener(id = "id1", topicPartitions = { @TopicPartition(topic = topicName, partitions = { "0" }) })
    public void listenPartition0(ConsumerRecord<?, ?> record) {
    	LOGGER.info("Listener Id1, Thread ID: " + Thread.currentThread().getId());
    	LOGGER.info("Received: " + record);
        countDownLatch1.countDown();
    }
 
    @KafkaListener(id = "id2", topicPartitions = { @TopicPartition(topic = topicName, partitions = { "1" }) })
    public void listenPartition1(ConsumerRecord<?, ?> record) {
    	LOGGER.info("Listener Id2, Thread ID: " + Thread.currentThread().getId());
    	LOGGER.info("Received: " + record);
        countDownLatch2.countDown();
    }
 
    @KafkaListener(id = "id3", topicPartitions = { @TopicPartition(topic = topicName , partitions = { "2" }) })
    public void listenPartition2(ConsumerRecord<?, ?> record) {
    	LOGGER.info("Listener Id3, Thread ID: " + Thread.currentThread().getId());
    	LOGGER.info("Received: " + record);
        countDownLatch3.countDown();
    }
}