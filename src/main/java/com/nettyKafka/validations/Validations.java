package com.nettyKafka.validations;

import java.util.ArrayList;
import java.util.List;

import com.nettyKafka.bean.RequestInputs;
import com.nettyKafka.utils.LogUtil;
/**
 * 
 * @author Tamal Basu
 *
 */
public class Validations {
	private static final LogUtil log = new LogUtil(Validations.class);  
	
	/**
	 * 
	 * @param req
	 * @return
	 */
	public List<String> nettyKafkaValidations(RequestInputs req){
		List<String> msg=new ArrayList<>();
      msg.add("success");
      msg.add("no Error");
		try {
			if(req.getId()==null){
				log.info("Default Id set to 0");
				req.setId("0");
			}
			Integer.parseInt(req.getId());
		} catch (NumberFormatException e) {
			e.printStackTrace();
			msg.set(0, "failed");
			msg.set(1, "Id should be a number");
			return msg;
		}
		if(req.getName()==null){
			msg.set(0, "failed");
			msg.set(1, "Name is mandatory");
			return msg;
		}
        if(req.getName().matches(".*[0-9].*")){
        	msg.set(0, "failed");
			msg.set(1, "Name should not contain number");
			return msg;
        }
        if(req.getEmail()==null){
			msg.set(0, "failed");
			msg.set(1, "Email is mandatory");
			return msg;
		}
        if(!req.getEmail().matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")){
        	msg.set(0, "failed");
			msg.set(1, "email should be in pattern of **@**.**");
			return msg;
        }
      
		return msg;
		
	
	
	
	}

}
