package com.yihaomen.springboot.mongodb.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document(collection = "onoffline_message")
@Data
@NoArgsConstructor
@ToString
public class OnOfflineMsg implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;

	private Date timestamp;
	
	private String mac;

	private String routerMac;
	
	private Integer status;
	
	private Integer userId;

	public OnOfflineMsg(Date timestamp, String mac, String routerMac, Integer status, Integer userId) {
		this.timestamp = timestamp;
		this.mac = mac;
		this.routerMac = routerMac;
		this.status = status;
		this.userId = userId;
	}
	
	

}
