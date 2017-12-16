package com.nettyKafka.kafka;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import org.springframework.stereotype.Component;

import com.nettyKafka.utils.LogUtil;
/**
 * 
 * @author Tamal Basu
 *
 */
@Component
public class KafkaProducer {
	private static final LogUtil log = new LogUtil(KafkaProducer.class);
	private  String LogMessage;
	 public  String getLogMessage() {
		return LogMessage;
	}

	public void setLogMessage(String logMessage) {
		LogMessage = logMessage;
	}
	private static Producer<String, String> gProducer;
	 	
	
	    private static KafkaProducer gKafkaProducer;
	    private int total = 0;
	    static {
	        try {
	            gKafkaProducer = new KafkaProducer();
	        } catch (final Exception theEx) {
	        	log.fatal("Could not initialize Kafka -"+theEx);
	        }
	    }
	    public static void initialize() throws Exception {
	        final Properties theProducerProperties = new Properties();
	        theProducerProperties.put("metadata.broker.list", "localhost:9092");
	        theProducerProperties.put("zk.connect", "localhost:2181");
	        theProducerProperties.put("serializer.class", "kafka.serializer.StringEncoder");
	        theProducerProperties.put("request.required.acks", "1");
	        theProducerProperties.put("partitioner.class", "com.nettyKafka.kafka.SimplePartitioner");     
	        final ProducerConfig producerConfig = new ProducerConfig(theProducerProperties);
	        gProducer = new Producer<>(producerConfig);
	    }
	    
	    public static KafkaProducer getInstance() throws Exception {
	        if (gKafkaProducer == null) {
	            throw new Exception("Kafka Producer not initialized");
	        }
	        return gKafkaProducer;
	    }
	    public void publishMessage(final List<KeyedMessage<String, String>> aKeyedMsg) {
	    	for(int i=0;i<aKeyedMsg.size();i++){
	    		log.info("message"+aKeyedMsg.get(i));
	    	}
	    
	    	
	        this.total = this.total + aKeyedMsg.size();
	        log.info("PUBLISHED :" + this.total);        
	        gProducer.send(aKeyedMsg);
	    }
	    public void publishMessage( KeyedMessage<String, String> aKeyedMsg) {
	    	log.info("DELETE PUBLISHED :" + aKeyedMsg);
	         gProducer.send(aKeyedMsg);
	    }
	    private void closeProducer() {
	        gProducer.close();
	    }
	  
	    @SuppressWarnings("static-access")
		public  void Kafka()throws Exception {
	    	final KafkaProducer kafka = KafkaProducer.getInstance();
	    	 kafka.initialize();
	    	 final KeyedMessage<String, String> keyedMsg = new KeyedMessage<>("nettyKafka", getLogMessage());
	    	 kafka.publishMessage(keyedMsg);
	    	 final List<KeyedMessage<String, String>> theList = new ArrayList<>();
	    	 for (int i = 0; i < 1; i++) {
	             final KeyedMessage<String, String> keymsg1 = new KeyedMessage<>("nettyKafka", String.valueOf(i),getLogMessage());
	             theList.add(keymsg1);
	    	 }
	         kafka.publishMessage(theList);
	         kafka.closeProducer();
	    }
}
