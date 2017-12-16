package com.nettyKafka.services;

import java.util.ArrayList;
import java.util.List;

import com.nettyKafka.bean.RequestInputs;
import com.nettyKafka.beans.Employee.Person;
import com.nettyKafka.kafka.KafkaProducer;
import com.nettyKafka.utils.LogUtil;
import com.nettyKafka.validations.Validations;

public class NettyKafkaService {
private static final LogUtil log = new LogUtil(NettyKafkaService.class);  

	public List<String> ProtoInstance(RequestInputs req) {
		List<String> msg=new ArrayList<>();
        
	msg =	new Validations().nettyKafkaValidations(req);
	if(msg.get(0).equalsIgnoreCase("failed")){
		log.info("Obj not able to send to kafka--"+msg.get(1));
		
	}else{
        Person Employee =
        		  Person.newBuilder()
        		    .setId(Integer.parseInt(req.getId()))
        		    .setName(req.getName())
        		    .setEmail(req.getEmail())
        		    .build();
      KafkaProducer kafkaProducer=new KafkaProducer();
        kafkaProducer.setLogMessage("{\"Object\":"+Employee.toString()+"}");
		try {
			kafkaProducer.Kafka();
		} catch (Exception e) {
			log.error("Error in kafka calling");
			e.printStackTrace();
		}
		
	}
	
	return msg;
	}
	
}
