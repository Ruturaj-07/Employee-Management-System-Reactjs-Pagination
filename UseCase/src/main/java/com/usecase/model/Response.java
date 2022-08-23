package com.usecase.model;

public class Response<T> {
	
	private int response;
	private T record;
	
	public int getResponse() {
		return response;
	}
	public void setResponse(int response) {
		this.response = response;
	}
	public T getRecord() {
		return record;
	}
	public void setRecord(T record) {
		this.record = record;
	}
	@Override
	public String toString() {
		return "Response [response=" + response + ", record=" + record + "]";
	}
		
}
