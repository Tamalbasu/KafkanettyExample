package com.nettyKafka.bean;
/**
 * 
 * @author Tamal Basu
 *
 */
public class RequestInputs {
	 private String Id;
       private String name;
 private String Email;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}
}
