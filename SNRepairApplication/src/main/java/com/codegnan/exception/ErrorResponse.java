package com.codegnan.exception;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
	
	Date date;
	int code;
	String status;
	String message;
	List<String> stackTrace;
	
	public ErrorResponse() {
		super();
		date = new Date();
	}
	public ErrorResponse(HttpStatus status, String message , List<String> stackTrace) {
		super();
		date = new Date();
		this.code = status.value();
		this.status = status.name();
		this.message = message;
		this.stackTrace = stackTrace;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public List<String> getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(List<String> stackTrace) {
        this.stackTrace = stackTrace;
    }
	@Override
	public String toString() {
		return "ErrorResponse [date=" + date + ", code=" + code + ", status=" + status + ", message=" + message  +", stackTrace=" + stackTrace + "]";
	}


}
