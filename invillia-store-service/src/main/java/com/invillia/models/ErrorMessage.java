package com.invillia.models;

import java.util.Calendar;
import java.util.Date;

public class ErrorMessage {
	
	private String description;
	private String documentation;
	private Date timestamp;
	
	public ErrorMessage(String description, String documentation) {
		super();
		this.description = description;
		this.documentation = documentation;
		this.timestamp = Calendar.getInstance().getTime();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDocumentation() {
		return documentation;
	}

	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}	
}